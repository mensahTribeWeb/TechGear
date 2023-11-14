package com.example.demo.domain;

import com.example.demo.validators.ValidInhousePart;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

/**
 * Represents an in-house part in the system, extending the base Part class.
 * In-house parts are parts that are produced or maintained internally by the company.
 */
@Entity
@DiscriminatorValue("1")
@ValidInhousePart
public class InhousePart extends Part{
    int partId;

    public InhousePart() {
    }

    public InhousePart(long id, String name, double price, int inv, int partId) {
        super(id, name, price, inv);
        this.partId = partId;
    }
    /**
     * Constructor for creating an in-house part with specified attributes.
     *
     * @param name  The name of the in-house part.
     * @param price The price of the in-house part.
     * @param inv   The inventory quantity of the in-house part.
     */
    public InhousePart(String name, double price, int inv) {
        super(name, price, inv);
    }

    public InhousePart(long id, String name, double price, int inv, Integer minInv, Integer maxInv, Set<Product> products, int partId) {
        super(id, name, price, inv, minInv, maxInv, products);
        this.partId = partId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    @Override
    public String toString() {
        return "Part [id=" + id + ", name=" + name + ", price=" + price + ", inv=" + inv + "]";
    }
}
