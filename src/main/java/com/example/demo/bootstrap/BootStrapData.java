package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class serves as a Spring Boot component that implements the CommandLineRunner
 * interface. It is responsible for initializing and adding sample inventory data to the
 * application's database. The class checks if both parts and products lists are empty
 * before adding the sample inventory upon application startup.
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    /**
     * Constructor for the BootStrapData class. It injects the required repositories
     * (PartRepository, ProductRepository, and OutsourcedPartRepository) to access and
     * manipulate data in the application's database.
     *
     * @param partRepository           The repository for managing Part entities.
     * @param productRepository        The repository for managing Product entities.
     * @param outsourcedPartRepository The repository for managing OutsourcedPart entities.
     */
    @Autowired
    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    /**
     * This method is invoked when the Spring Boot application starts. It checks if both
     * parts and products lists are empty in the database. If they are empty, it adds
     * sample inventory data, including parts and products. It also checks for the
     * existence of an OutsourcedPart with the name "out test" and prints its company
     * name to the console if found.
     *
     * @param args Command line arguments (not used in this method).
     * @throws Exception If there is an exception during the execution of the method.
     */
    @Override
    public void run(String... args) throws Exception {
        if (partRepository.count() == 0 && productRepository.count() == 0) {
            addSampleInventory();
        }

        OutsourcedPart thePart = outsourcedPartRepository.findByName("out test");

        if (thePart != null) {
            System.out.println(thePart.getCompanyName());
        } else {
            System.out.println("No OutsourcedPart with the name 'out test' found.");
        }
        List<OutsourcedPart> outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for (OutsourcedPart part : outsourcedParts) {
            System.out.println(part.getName() + " " + part.getCompanyName());
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products: " + productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts: " + partRepository.count());
        System.out.println(partRepository.findAll());
    }
    /**
     * This method initializes and adds sample inventory data to the database if both
     * parts and products lists are empty. It populates the database with sample
     * parts and products, including an OutsourcedPart with the name "out test."
     *
     * If the database is not empty, this method does not perform any actions.
     */
    private void addSampleInventory() {
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("TechGear Shop");
        o.setName("out test");
        o.setInv(5);
        o.setMinInv(0); // Set minimum inventory
        o.setMaxInv(10); // Set maximum inventory
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);

            // Sample Parts
            InhousePart motherboard = new InhousePart("Motherboard", 50.0, 10);
            motherboard.setMinInv(0); // Set minimum inventory
            motherboard.setMaxInv(20); // Set maximum inventory
            InhousePart cpu = new InhousePart("CPU", 100.0, 15);
            cpu.setMinInv(0); // Set minimum inventory
            cpu.setMaxInv(30); // Set maximum inventory
            InhousePart ram = new InhousePart("RAM", 30.0, 20);
            ram.setMinInv(0); // Set minimum inventory
            ram.setMaxInv(40); // Set maximum inventory
            InhousePart hardDrive = new InhousePart("Hard Drive", 60.0, 12);
            hardDrive.setMinInv(0); // Set minimum inventory
            hardDrive.setMaxInv(24); // Set maximum inventory
            InhousePart graphicCard = new InhousePart("Graphic Card", 80.0, 8);
            graphicCard.setMinInv(0); // Set minimum inventory
            graphicCard.setMaxInv(16); // Set maximum inventory

        // Sample Products
        Product desktop = new Product("Desktop", 500.0, 5);
        desktop.setMinInv(0); // Set minimum inventory
        desktop.setMaxInv(10); // Set maximum inventory
        Product laptop = new Product("Laptop", 700.0, 7);
        laptop.setMinInv(0); // Set minimum inventory
        laptop.setMaxInv(14); // Set maximum inventory
        Product mobilePhone = new Product("Mobile Phone", 300.0, 10);
        mobilePhone.setMinInv(0); // Set minimum inventory
        mobilePhone.setMaxInv(20); // Set maximum inventory
        Product tablet = new Product("Tablet", 400.0, 8);
        tablet.setMinInv(0); // Set minimum inventory
        tablet.setMaxInv(16); // Set maximum inventory
        Product monitor = new Product("Monitor", 200.0, 15);
        monitor.setMinInv(0); // Set minimum inventory
        monitor.setMaxInv(30); // Set maximum inventory

        // Save the parts and products to the repositories
            partRepository.saveAll(List.of(motherboard, cpu, ram, hardDrive, graphicCard));
            productRepository.saveAll(List.of(desktop, laptop, mobilePhone, tablet, monitor));

}}
