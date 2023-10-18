package com.example.demo.domain;

import com.example.demo.validators.ValidOutsourcedPart;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Represents an outsourced part in the inventory management system.
 * This class is used to model parts that are purchased from external companies.
 */
@Entity
@DiscriminatorValue("2")
@ValidOutsourcedPart
public class OutsourcedPart extends Part{
    String companyName;

    public OutsourcedPart() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
