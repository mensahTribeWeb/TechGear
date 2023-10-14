package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling part-related operations, including updating and deleting parts.
 */
@Controller
public class AddPartController {
    @Autowired
    private ApplicationContext context;

    /**
     * Displays the form for updating a part (in-house or outsourced).
     *
     * @param theId     The ID of the part to be updated.
     * @param theModel  The model to add attributes for the view.
     * @return The name of the HTML template for the part update form.
     */
    @GetMapping("/showPartFormForUpdate")
    public String showPartFormForUpdate(@RequestParam("partID") int theId,Model theModel){

        PartService repo=context.getBean(PartServiceImpl.class);
        OutsourcedPartService outsourcedrepo=context.getBean(OutsourcedPartServiceImpl.class);
        InhousePartService inhouserepo=context.getBean(InhousePartServiceImpl.class);

        boolean inhouse=true;
        List<OutsourcedPart> outsourcedParts=outsourcedrepo.findAll();
        for(OutsourcedPart outsourcedPart:outsourcedParts) {
            if(outsourcedPart.getId()==theId)inhouse=false;
        }
        String formtype;
        if(inhouse){
            InhousePart inhousePart=inhouserepo.findById(theId);
            theModel.addAttribute("inhousepart",inhousePart);
            formtype="InhousePartForm";
        }
        else{
            OutsourcedPart outsourcedPart=outsourcedrepo.findById(theId);
            theModel.addAttribute("outsourcedpart",outsourcedPart);
            formtype="OutsourcedPartForm";
        }
        return formtype;
    }
    /**
     * Deletes a part by its ID if it's not associated with any products.
     *
     * @param theId     The ID of the part to be deleted.
     * @param theModel  The model for adding attributes to the view.
     * @return The name of the confirmation view if the part is deleted successfully,
     *         or an error view if the part is associated with products.
     */
    @GetMapping("/deletepart")
    public String deletePart(@Valid @RequestParam("partID") int theId,  Model theModel){
        PartService repo = context.getBean(PartServiceImpl.class);
        Part part=repo.findById(theId);
        if(part.getProducts().isEmpty()){
            repo.deleteById(theId);
            return "confirmationdeletepart";
        }
        else{
            return "negativeerror";
        }
    }
}
