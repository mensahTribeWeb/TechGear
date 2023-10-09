package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
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
    public String showFormAddOutsourcedPart(Model theModel){
        Part part=new OutsourcedPart();
        theModel.addAttribute("outsourcedpart",part);
        return "OutsourcedPartForm";
    }
    /**
     * Handles the submission of the Outsourced Part form.
     *
     * @param part            The Outsourced Part to be added.
     * @param bindingResult   The binding result for validation.
     * @param theModel        The model to add attributes for the view.
     * @return The appropriate view based on validation results.
     */
    @PostMapping("/showFormAddOutPart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, BindingResult bindingResult, Model theModel){
        theModel.addAttribute("outsourcedpart",part);
        if(bindingResult.hasErrors()){
            return "OutsourcedPartForm";
        }
        else{
        OutsourcedPartService repo=context.getBean(OutsourcedPartServiceImpl.class);
        OutsourcedPart op=repo.findById((int)part.getId());
        if(op!=null)part.setProducts(op.getProducts());
            repo.save(part);
        return "confirmationaddpart";}
    }
    /**
     * Handles saving an Outsourced Part, performing additional validation.
     *
     * @param outsourcedPart  The Outsourced Part to be saved.
     * @param bindingResult   The binding result for validation.
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


}
