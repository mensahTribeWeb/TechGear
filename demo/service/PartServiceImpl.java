package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.repositories.PartRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing Part entities.
 */

@Service
public class PartServiceImpl implements PartService{
        private PartRepository partRepository;

        @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> findAll() {
        return (List<Part>) partRepository.findAll();
    }
    public List<Part> listAll(String keyword){
        if(keyword !=null){
            return partRepository.search(keyword);
        }
        return (List<Part>) partRepository.findAll();
    }
    /**
     * Retrieves a Part entity along with its associated products.
     *
     * @param partId The unique identifier of the Part entity.
     * @return The Part entity with its associated products, or null if not found.
     */
    @Override
    public Part getPartWithProducts(Long partId) {
        Part part = partRepository.findById((long) Math.toIntExact(partId)).orElse(null);

        if (part != null) {
            // Reattach the entity to initialize the collection
            Hibernate.initialize(part.getProducts());
        }

        return part;
    }

    @Override
    public Part findById(int theId) {
        Long theIdl=(long)theId;
        Optional<Part> result = partRepository.findById(theIdl);

        Part thePart = null;

        if (result.isPresent()) {
            thePart = result.get();
        }
        else {
            // we didn't find the part id
            throw new RuntimeException("Did not find part id - " + theId);
        }

        return thePart;
    }

    @Override
    public void save(Part thePart) {
            partRepository.save(thePart);

    }

    @Override
    public void deleteById(int theId) {
        Long theIdl=(long)theId;
        partRepository.deleteById(theIdl);
    }
}
