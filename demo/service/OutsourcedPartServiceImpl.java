package com.example.demo.service;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.repositories.OutsourcedPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Outsourced Parts in the system.
 * This service provides methods for retrieving, saving, and deleting Outsourced Parts.
 */
@Service
public class OutsourcedPartServiceImpl implements OutsourcedPartService{
    private OutsourcedPartRepository partRepository;

    @Autowired
    public OutsourcedPartServiceImpl(OutsourcedPartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<OutsourcedPart> findAll() {
        return (List<OutsourcedPart>) partRepository.findAll();
    }

    @Override
    public OutsourcedPart findById(int theId) {
        Long theIdl=(long)theId;
        Optional<OutsourcedPart> result = partRepository.findById(theIdl);

        OutsourcedPart thePart = null;

        if (result.isPresent()) {
            thePart = result.get();
        }
        else {
            // we didn't find the OutSourced id
            //throw new RuntimeException("Did not find part id - " + theId);
            return null;
        }

        return thePart;
    }

    @Override
    public void save(OutsourcedPart thePart) {
        partRepository.save(thePart);

    }

    @Override
    public void deleteById(int theId) {
        Long theIdl=(long)theId;
        partRepository.deleteById(theIdl);
    }
    /**
     * Adds an Outsourced Part to the system with inventory validation.
     *
     * @param outsourcedPart The Outsourced Part to add.
     * @return True if the part is added successfully, otherwise false.
     * @throws IllegalArgumentException If inventory is outside the valid range.
     */
    public boolean addOutsourcedPart(OutsourcedPart outsourcedPart) {
        int minInv = outsourcedPart.getMinInv();
        int maxInv = outsourcedPart.getMaxInv();
        int inventory = outsourcedPart.getInv();

        if (inventory < minInv) {
            throw new IllegalArgumentException("Inventory is smaller than the minimum.");
        }

        if (inventory > maxInv) {
            throw new IllegalArgumentException("Inventory is larger than the maximum.");
        }

        // Add the OutsourcedPart to system and save it to the repository
        partRepository.save(outsourcedPart);

        System.out.println("Part added successfully");
        return true;
    }

}
