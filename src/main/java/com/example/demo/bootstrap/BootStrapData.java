package com.example.demo.bootstrap;

import com.example.demo.domain.ConcretePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
        // Check if both parts and products lists are empty before adding the sample inventory
        if (partRepository.count() == 0 && productRepository.count() == 0) {
            // Add sample inventory
            addSampleInventory();
        }


        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        if (thePart != null) {
        System.out.println(thePart.getCompanyName());
        } else {
            System.out.println("No OutsourcedPart with the name 'out test' found.");
        }

        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
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
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);

            // Sample Parts
            Part motherboard = new ConcretePart("Motherboard", 50.0, 10);
            Part cpu = new ConcretePart("CPU", 100.0, 15);
            Part ram = new ConcretePart("RAM", 30.0, 20);
            Part hardDrive = new ConcretePart("Hard Drive", 60.0, 12);
            Part graphicCard = new ConcretePart("Graphic Card", 80.0, 8);

            // Sample Products
            Product desktop = new Product("Desktop", 500.0, 5);
            Product laptop = new Product("Laptop", 700.0, 7);
            Product mobilePhone = new Product("Mobile Phone", 300.0, 10);
            Product tablet = new Product("Tablet", 400.0, 8);
            Product monitor = new Product("Monitor", 200.0, 15);

            // Save the parts and products to the repositories
            partRepository.saveAll(List.of(motherboard, cpu, ram, hardDrive, graphicCard));
            productRepository.saveAll(List.of(desktop, laptop, mobilePhone, tablet, monitor));

}}
