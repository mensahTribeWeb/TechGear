package com.example.demo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/**
 * Represents a concrete part in the inventory management system. Concrete parts are
 * physical parts with a specific name, price, and inventory level.
 *
 * <p>This class is annotated with {@code @Entity} to mark it as a JPA entity, and
 * {@code @DiscriminatorValue("0")} to specify the discriminator value when using
 * inheritance strategies. It extends the {@code Part} class to inherit common
 * attributes and behavior since part is abstract.
 */
@Entity
@DiscriminatorValue("0")
public class ConcretePart extends Part {

    public ConcretePart() {
    }
    /**
     * Parameterized constructor to create a ConcretePart with the specified attributes.
     *
     * @param name  The name of the concrete part.
     * @param price The price of the concrete part.
     * @param inv   The inventory level of the concrete part.
     */
    public ConcretePart(String name, double price, int inv) {
        super(name, price, inv);
    }

}
