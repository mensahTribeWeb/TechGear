package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 *
 */
@Controller
public class AddProductController {
    @Autowired
    private ApplicationContext context;
    private final PartService partService;
    private List<Part> theParts;
    private static Product product1;
    private Product product;
    private  final ProductService productService;
    private int initProductInventory;
    private List<Part> assoParts = new ArrayList<>();

    @GetMapping("/showFormAddProduct")
    public String showFormAddPart(Model theModel) {
        theModel.addAttribute("parts", partService.findAll());
        product = new Product();
        product1=product;
        theModel.addAttribute("product", product);

        List<Part>availParts=new ArrayList<>();
        for(Part p: partService.findAll()){
            if(!product.getParts().contains(p))availParts.add(p);
        }
        theModel.addAttribute("availparts",availParts);
        theModel.addAttribute("assparts",product.getParts());
        return "productForm";
    }

        @PostMapping("/showFormAddProduct")
        public String submitForm(@Valid Product product, BindingResult bindingResult){
            if( bindingResult.hasErrors()){
                return "productForm";
            }
            int productInventory = product.getInv();
            int newProductInv = productInventory - initProductInventory;

            System.out.println(newProductInv);
            System.out.println(assoParts);
            for(Part p : product1.getParts()) {
                System.out.println("-------------");
                System.out.println(p.getMinInv());
                System.out.println(p.getMaxInv());
                System.out.println("-------------");
                if(newProductInv > (p.getInv() - p.getMinInv()) || newProductInv < (p.getInv() - p.getMinInv())){
                    return "error-page";
                }
            }
            product.getParts().addAll(product1.getParts());
            for(Part p : product.getParts()){
                p.setInv(p.getInv() - newProductInv);
                partService.save(p);
            }

            for (Part p : partService.findAll()) {
                int newPartInv = p.getInv() - newProductInv;
                p.setInv(newPartInv);
                partService.save(p);
            }
            productService.save(product);
            return "redirect:/";
        }

    @GetMapping("/showProductFormForUpdate")
    public String showProductFormForUpdate(@RequestParam("productID") int theId, Model theModel) {
        theModel.addAttribute("parts", partService.findAll());
        ProductService repo = context.getBean(ProductServiceImpl.class);
        Product theProduct = repo.findById(theId);
        product1=theProduct;
        initProductInventory = product1.getInv();

        theModel.addAttribute("product", theProduct);
        theModel.addAttribute("assparts",theProduct.getParts());
        List<Part>availParts=new ArrayList<>();
        for(Part p: partService.findAll()){
            if(!theProduct.getParts().contains(p))availParts.add(p);
        }
        theModel.addAttribute("availparts",availParts);
        //send over to our form
        return "productForm";
    }

    @GetMapping("/deleteproduct")
    public String deleteProduct(@RequestParam("productID") int theId, Model theModel) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product product2=productService.findById(theId);
        for(Part part:product2.getParts()){
            part.getProducts().remove(product2);
            partService.save(part);
        }
        product2.getParts().removeAll(product2.getParts());
        productService.save(product2);
        productService.deleteById(theId);

        return "confirmationdeleteproduct";
    }

    public AddProductController(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }
// make the add and remove buttons work

    @GetMapping("/associatepart")
    public String associatePart(@Valid @RequestParam("partID") int theID, Model theModel){
        if (product1.getName()==null) {
            return "saveproductscreen";
        }
        else{
        product1.getParts().add(partService.findById(theID));
        partService.findById(theID).getProducts().add(product1);
            return getString(theID, theModel);
        }
 //        return "confirmationassocpart";
    }
    @GetMapping("/removepart")
    public String removePart(@RequestParam("partID") int theID, Model theModel){
        theModel.addAttribute("product", product);

        product1.getParts().remove(partService.findById(theID));
        partService.findById(theID).getProducts().remove(product1);
        return getString(theID, theModel);
    }

    private String getString(@RequestParam("partID") int theID, Model theModel) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        productService.save(product1);
        partService.save(partService.findById(theID));
        theModel.addAttribute("product", product1);
        theModel.addAttribute("assparts",product1.getParts());
        List<Part> availParts=new ArrayList<>();
        for(Part p: partService.findAll()){
            if(!product1.getParts().contains(p))availParts.add(p);
        }
        theModel.addAttribute("availparts",availParts);
        return "productForm";
    }
}
