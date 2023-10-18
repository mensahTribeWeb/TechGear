package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.service.OutsourcedPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Controller class for handling operations related to adding and saving Outsourced Parts.
 */
@Controller
public class AddOutsourcedPartController {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private OutsourcedPartService outsourcedPartService;

    /**
     * Displays the form for adding a new Outsourced Part.
     *
     * @param theModel The model to add attributes for the view.
     * @return The name of the HTML template for the Outsourced Part form.
     */
    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel) {
        theModel.addAttribute("outsourcedpart", new OutsourcedPart());
        return "OutsourcedPartForm";
    }

    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart outsourcedPart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "OutsourcedPartForm";
        } else {
            outsourcedPartService.save(outsourcedPart);
            return "confirmationaddpart";
        }
    }
    /**
     * Handles saving an Outsourced Part, performing additional validation.
     *
     * @param outsourcedPart   The Outsourced Part to be saved.
     * @param bindingResult    The binding result for validation.
     * @return The appropriate view based on validation results.
     */
    @PostMapping("/saveOutsourcedPart")
    public String saveOutsourcedPart(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart outsourcedPart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "OutsourcedPartForm";
        }

        if (!outsourcedPart.isInvValid()) {
            bindingResult.rejectValue("inv", "inventory.invalid", "Inventory is outside the valid range.");
            return "OutsourcedPartForm";
        }

        // Save the outsourced part
        outsourcedPartService.save(outsourcedPart);

        return "redirect:/outsourcedparts/list";
    }
    @PostMapping("/addOutsourcedPart")
    public String addOutsourcedPart(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart outsourcedPart,
                                    BindingResult bindingResult,
                                    Model theModel) {
        System.out.println("........................................................entry add outsource part");
        if (bindingResult.hasErrors()) {
            // There are validation errors
            return "OutsourcedPartForm";
        } else if (!outsourcedPart.isInvValid()) {
            // Inventory validation failed
            theModel.addAttribute("errorMessage", "Error: Inventory couldn't be larger than the maximum or smaller than the minimum.");
            return "error-page"; // Redirect to an error page
        } else {
            // No errors, save the outsourced part
            outsourcedPartService.save(outsourcedPart);
            return "success-page"; // Redirect to a success page
        }
    }
}
