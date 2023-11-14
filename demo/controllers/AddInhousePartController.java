package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.service.InhousePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Controller class for handling operations related to adding and saving Inhouse Parts.
 */
@Controller
public class AddInhousePartController {

    @Autowired
    private InhousePartService inhousePartService;

    /**
     * Displays the form for adding a new Inhouse Part.
     *
     * @param theModel The model to add attributes for the view.
     * @return The name of the HTML template for the Inhouse Part form.
     */
    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel) {
        InhousePart inhousepart = new InhousePart();
        theModel.addAttribute("inhousepart", inhousepart);
        return "InhousePartForm";
    }

    /**
     * Handles the submission of the Inhouse Part form.
     *
     * @param inhousePart   The Inhouse Part to be added, automatically populated from the form.
     * @param bindingResult The binding result for validation.
     * @return The appropriate view based on validation results, "InhousePartForm" if there are errors,
     * "confirmationaddpart" if the form is successfully submitted and the Inhouse Part is saved.
     */
    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart inhousePart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "InhousePartForm";
        } else {
            inhousePartService.save(inhousePart);
            return "confirmationaddpart";
        }
    }

    /**
     * Handles saving an Inhouse Part, performing additional validation.
     *
     * @param inhousePart   The Inhouse Part to be saved.
     * @param bindingResult The binding result for validation.
     * @return The appropriate view based on validation results.
     */
    @PostMapping("/addInhousePart")
    public String addInhousePart(@Valid @ModelAttribute("inhousepart") InhousePart inhousePart,
                                 BindingResult bindingResult,
                                 Model theModel) {
        if (bindingResult.hasErrors()) {
            return "InhousePartForm";
        }
        if (!inhousePart.isInvValid()) {
            theModel.addAttribute("errorMessage", "Error: Inventory couldn't be larger than the maximum or smaller than the minimum.");
            return "error-page"; // Redirect to an error page
        }
        inhousePartService.save(inhousePart);
        return "success-page"; // Redirect to a success page
    }

    /**
     * Handles the creation or update of an In-house part.
     *
     * This method is mapped to the POST request at "/createOrUpdateInhousePart". It validates the provided InhousePart object,
     * checks and sets valid values for minInv and maxInv if they are null, and ensures that the inventory is within the
     * specified minimum and maximum values. If all checks pass, the InhousePart is saved. If any errors occur during this process,
     * an error message is displayed.
     *
     * @param inhousePart    The InhousePart object to be created or updated.
     * @param bindingResult  The result of the validation process.
     * @param theModel       The Model for adding attributes or error messages.
     * @return               A string indicating the view to display based on the result of the operation.
     */
    @PostMapping("/createOrUpdateInhousePart")
    public String createOrUpdateInhousePart(@Valid @ModelAttribute("inhousePart") InhousePart inhousePart, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return "InhousePartForm";
        }

        // Check and set valid values for minInv and maxInv if they are null
        if (inhousePart.getMinInv() == null) {
            inhousePart.setMinInv(1); // Provide a default or valid value
        }

        if (inhousePart.getMaxInv() == null) {
            inhousePart.setMaxInv(1000); // Provide a default or valid value
        }

        // Check the condition that inventory is within the min and max values
        if (!inhousePart.isInvValid()) {
            theModel.addAttribute("errorMessage", "Error: Inventory couldn't be larger than the maximum or smaller than the minimum.");
            return "error-page"; // Redirect to an error page
        }

        // Now, you can save the inhousePart
        try {
            inhousePartService.save(inhousePart);
            return "success-page"; // Redirect to a success page
        } catch (Exception e) {
            // Handle the exception, e.g., log it or return an error page
            return "error-page";
        }
    }

}
