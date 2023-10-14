package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.InhousePartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom validation class that checks if the price of a product is greater than or equal to the sum of the prices of its parts.
 *
 * <p>
 * This validator is used in conjunction with the {@link ValidProductPrice} annotation to ensure that a product's price is
 * valid based on the prices of its associated parts. If the product's price is less than the total price of its parts,
 * a validation error will be raised.
 * </p>
 *
 * <p>
 * This class retrieves the necessary data from the Spring application context to perform the validation logic.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>
 * {@code
 * @ValidProductPrice
 * public class MyEntity {
 *     // ...
 * }
 * }
 * </pre>
 *
 * @see javax.validation.ConstraintValidator
 * @see com.example.demo.validators.ValidProductPrice
 */
public class PriceProductValidator implements ConstraintValidator<ValidProductPrice, Product> {
    @Autowired
    private ApplicationContext context;

    public static  ApplicationContext myContext;

    @Override
    public void initialize(ValidProductPrice constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    /**
     * Validates whether the product's price is greater than or equal to the sum of its associated parts' prices.
     *
     * @param product                  The product to validate.
     * @param constraintValidatorContext The context in which the validation is performed.
     * @return {@code true} if the product's price is valid, {@code false} otherwise.
     */
    @Override
    public boolean isValid(Product product, ConstraintValidatorContext constraintValidatorContext) {
        if(context==null) return true;
        if(context!=null)myContext=context;
        ProductService repo = myContext.getBean(ProductServiceImpl.class);
        double sumPartsPrice = 0;
        if (product.getId() != 0) {
            Product myProduct = repo.findById((int) product.getId());
            for (Part p : myProduct.getParts()) sumPartsPrice = sumPartsPrice + p.getPrice();
            if (product.getPrice() >= sumPartsPrice) {
                return true;
            } else {
                return false;
            }
        }
        else {
            return true;
        }
    }
}
