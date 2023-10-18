package com.example.demo.domain;

import com.example.demo.validators.ValidDeletePart;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Abstract class representing a Part entity in the system.
 * This class serves as the base class for both InhousePart and OutsourcedPart classes.
 */
@Entity
@ValidDeletePart
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="part_type",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="Parts")
public abstract class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    @Min(value = 0, message = "Price value must be positive")
    double price;
    @Min(value = 0, message = "Inventory value must be positive")
    @Max(value = 100, message = "Inventory cannot exceed the maximum inventory value.")
    int inv;


    @Min(value = 0, message = "Minimum inventory value must be positive")
    @Column(name = "min_inv")
    private Integer minInv;


    @Min(value = 0, message = "Maximum inventory value must be positive")
    @Column(name = "max_inv")
    private Integer maxInv;

    @ManyToMany
    @JoinTable(name="product_part", joinColumns = @JoinColumn(name="part_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    Set<Product> products= new HashSet<>();

    public Part() {
    }

    public Part(String name, double price, int inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
    }

    public Part(long id, String name, double price, int inv) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
    }

    public Part(long id, String name, double price, int inv, Integer minInv, Integer maxInv, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInv = minInv;
        this.maxInv = maxInv;
        this.products = products;
    }

    /**
     * Verifies if the inventory falls between the minimum and maximum inventory values.
     *
     * @return True if the inventory is within the valid range, otherwise false.
     */
    public boolean isInvValid() {
        return inv >= minInv && inv <= maxInv;
    }
    /**
     * Retrieves the minimum inventory value.
     *
     * @return The minimum inventory value.
     */
    public Integer getMinInv() {
        return minInv;
    }
    /**
     * Sets the minimum inventory value.
     *
     * @param minInv The minimum inventory value to set.
     */
    public void setMinInv(Integer minInv) {
        this.minInv = minInv;
    }
    /**
     * Retrieves the maximum inventory value.
     *
     * @return The maximum inventory value.
     */
    public Integer getMaxInv() {
        return maxInv;
    }
    /**
     * Sets the maximum inventory value.
     *
     * @param maxInv The maximum inventory value to set.
     */
    public void setMaxInv(Integer maxInv) {
        this.maxInv = maxInv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id == part.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return name;
    }
}
