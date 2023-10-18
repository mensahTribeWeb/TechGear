package com.example.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Project: demoDarbyFrameworks2-master
 * Package: com.example.demo.domain
 * <p>
 * User: carolyn.sher
 * Date: 6/24/2022
 * Time: 3:44 PM
 * <p>
 * Created with IntelliJ IDEA
 * To change this template use File | Settings | File Templates.
 */
class PartTest {
    Part partIn;
    Part partOut;
    @BeforeEach
    void setUp() {
        partIn=new InhousePart();
        partOut=new OutsourcedPart();
    }

    @Test
    void getId() {
        Long idValue=4L;
        partIn.setId(idValue);
        assertEquals(partIn.getId(), idValue);
        partOut.setId(idValue);
        assertEquals(partOut.getId(), idValue);
    }

    @Test
    void setId() {
        Long idValue=4L;
        partIn.setId(idValue);
        assertEquals(partIn.getId(), idValue);
        partOut.setId(idValue);
        assertEquals(partOut.getId(), idValue);
    }

    @Test
    void getName() {
        String name="test inhouse part";
        partIn.setName(name);
        assertEquals(name,partIn.getName());
        name="test outsourced part";
        partOut.setName(name);
        assertEquals(name,partOut.getName());
    }

    @Test
    void setName() {
        String name="test inhouse part";
        partIn.setName(name);
        assertEquals(name,partIn.getName());
        name="test outsourced part";
        partOut.setName(name);
        assertEquals(name,partOut.getName());
    }

    @Test
    void getPrice() {
        double price=1.0;
        partIn.setPrice(price);
        assertEquals(price,partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price,partOut.getPrice());
    }

    @Test
    void setPrice() {
        double price=1.0;
        partIn.setPrice(price);
        assertEquals(price,partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price,partOut.getPrice());
    }

    @Test
    void getInv() {
        int inv=5;
        partIn.setInv(inv);
        assertEquals(inv,partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv,partOut.getInv());
    }

    @Test
    void setInv() {
        int inv=5;
        partIn.setInv(inv);
        assertEquals(inv,partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv,partOut.getInv());
    }

    @Test
    void getProducts() {
        Product product1= new Product();
        Product product2= new Product();
        Set<Product> myProducts= new HashSet<>();
        myProducts.add(product1);
        myProducts.add(product2);
        partIn.setProducts(myProducts);
        assertEquals(myProducts,partIn.getProducts());
        partOut.setProducts(myProducts);
        assertEquals(myProducts,partOut.getProducts());
    }

    @Test
    void setProducts() {
        Product product1= new Product();
        Product product2= new Product();
        Set<Product> myProducts= new HashSet<>();
        myProducts.add(product1);
        myProducts.add(product2);
        partIn.setProducts(myProducts);
        assertEquals(myProducts,partIn.getProducts());
        partOut.setProducts(myProducts);
        assertEquals(myProducts,partOut.getProducts());
    }

    @Test
    void testToString() {
        String name="test inhouse part";
        partIn.setName(name);
        assertEquals(name,partIn.toString());
        name="test outsourced part";
        partOut.setName(name);
        assertEquals(name,partOut.toString());
    }

    @Test
    void testEquals() {
        partIn.setId(1l);
        Part newPartIn=new InhousePart();
        newPartIn.setId(1l);
        assertEquals(partIn,newPartIn);
        partOut.setId(1l);
        Part newPartOut=new OutsourcedPart();
        newPartOut.setId(1l);
        assertEquals(partOut,newPartOut);

    }

    @Test
    void testHashCode() {
        partIn.setId(1l);
        partOut.setId(1l);
        assertEquals(partIn.hashCode(),partOut.hashCode());
    }

    @Test
    public void testMinimumInventory() {
        int minimumInv = 10; // Set the minimum inventory value
        Part partIn = new InhousePart();
        partIn.setMinInv(minimumInv);

        assertEquals(minimumInv, partIn.getMinInv());
    }

    @Test
    public void testMaximumInventory() {
        int maximumInv = 100; // Set the maximum inventory value
        Part partOut = new OutsourcedPart();
        partOut.setMaxInv(maximumInv);

        assertEquals(maximumInv, partOut.getMaxInv());
    }

    @Test
    void testIsInvValidWithinRange() {
        Part partIn = new InhousePart();
        partIn.setMinInv(5);
        partIn.setMaxInv(10);
        partIn.setInv(7);
        assertTrue(partIn.isInvValid());

        Part partOut = new OutsourcedPart();
        partOut.setMinInv(5);
        partOut.setMaxInv(10);
        partOut.setInv(7);
        assertTrue(partOut.isInvValid());
    }

    @Test
    void testIsInvValidBelowMin() {
        Part partIn = new InhousePart();
        partIn.setMinInv(5);
        partIn.setMaxInv(10);
        partIn.setInv(3);
        assertFalse(partIn.isInvValid());

        Part partOut = new OutsourcedPart();
        partOut.setMinInv(5);
        partOut.setMaxInv(10);
        partOut.setInv(3);
        assertFalse(partOut.isInvValid());
    }

    @Test
    void testIsInvValidAboveMax() {
        Part partIn = new InhousePart();
        partIn.setMinInv(5);
        partIn.setMaxInv(10);
        partIn.setInv(12);
        assertFalse(partIn.isInvValid());

        Part partOut = new OutsourcedPart();
        partOut.setMinInv(5);
        partOut.setMaxInv(10);
        partOut.setInv(12);
        assertFalse(partOut.isInvValid());
    }

}