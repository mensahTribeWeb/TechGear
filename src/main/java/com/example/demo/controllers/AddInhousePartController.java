package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.InhousePartServiceImpl;
import com.example.demo.service.PartService;
import com.example.demo.service.PartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Controller class for handling operations related to adding and saving Inhouse Parts.
 */
@Controller
public class AddInhousePartController{
    @Autowired
    private ApplicationContext context;

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
        InhousePart inhousepart=new InhousePart();
        theModel.addAttribute("inhousepart",inhousepart);
        return "InhousePartForm";
    }
    /**
     * Handles the submission of the Inhouse Part form.
     *
     * @param part             The Inhouse Part to be added.
     * @param theBindingResult The binding result for validation.
     * @param theModel         The model to add attributes for the view.
     * @return The appropriate view based on validation results.
     */
    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult theBindingResult, Model theModel){
        theModel.addAttribute("inhousepart",part);
        if(theBindingResult.hasErrors()){
            return "InhousePartForm";
        }
        else{
        InhousePartService repo=context.getBean(InhousePartServiceImpl.class);
        InhousePart ip=repo.findById((int)part.getId());
        if(ip!=null)part.setProducts(ip.getProducts());
        repo.save(part);

        return "confirmationaddpart";}
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
