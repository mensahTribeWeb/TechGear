package com.example.demo.validators;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom validator for ensuring that adding or updating a product won't lower
 * the associated part's inventory below the minimum.
 */
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
    @Autowired
    private ApplicationContext context;
    public static  ApplicationContext myContext;
    /**
     * Initializes the custom validator.
     *
     * @param constraintAnnotation The constraint annotation.
     */
    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    /**
     * Validates whether adding or updating a product would cause any associated
     * part's inventory to fall below the minimum.
     *
     * @param product                The product to be validated.
     * @param constraintValidatorContext The context in which the validation is executed.
     * @return True if the validation passes; otherwise, false.
     */
    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if(context==null) return true;
        if(context!=null)myContext=context;
        ProductService repo = myContext.getBean(ProductServiceImpl.class);
        if (product.getId() != 0) {
            Product myProduct = repo.findById((int) product.getId());
            for (Part p : myProduct.getParts()) {
                if(p instanceof InhousePart){
                    InhousePart inhousePart = new InhousePart(p.getId(), p.getName(), p.getPrice(), p.getInv(), p.getMinInv(), p.getMaxInv(), p.getProducts() ,((InhousePart) p).getPartId());
                }
                if ((p.getInv() - p.getMinInv())<(product.getInv()-myProduct.getInv())) {
                    return false;
                }
            }
            return true;
        }
        else{
                return true;
            }
    }
}
