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
public class AddInhousePartController{

    @Autowired
    private InhousePartService inhousePartService;

    /**
     * Displays the form for adding a new Inhouse Part.
     *
     * @param theModel The model to add attributes for the view.
     * @return The name of the HTML template for the Inhouse Part form.
     */
    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel){
        InhousePart inhousepart= new InhousePart();
        theModel.addAttribute("inhousepart",inhousepart);
        return "InhousePartForm";
    }
    /**
     * Handles the submission of the Inhouse Part form.
     *
     * @param inhousePart    The Inhouse Part to be added, automatically populated from the form.
     * @param bindingResult  The binding result for validation.
     * @return The appropriate view based on validation results, "InhousePartForm" if there are errors,
     *         "confirmationaddpart" if the form is successfully submitted and the Inhouse Part is saved.
     */
    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart inhousePart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "InhousePartForm";
        } else {
            // You don't need to manually create the part or get it from the context
            inhousePartService.save(inhousePart);
            return "confirmationaddpart";
        }
    }
    /**
     * Handles saving an Inhouse Part, performing additional validation.
     *
     * @param inhousePart    The Inhouse Part to be saved.
     * @param bindingResult  The binding result for validation.
     * @return The appropriate view based on validation results.
     */
    @PostMapping("/saveInhousePart")
    public String saveInhousePart(@Valid @ModelAttribute("inhousepart") InhousePart inhousePart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "InhousePartForm";
        }

        if (!inhousePart.isInvValid()) {
            bindingResult.rejectValue("inv", "inventory.invalid", "Inventory is outside the valid range.");
            return "InhousePartForm";
        }

        // Save the inhouse part
        inhousePartService.save(inhousePart);

        return "redirect:/inhouseparts/list";
    }
}
