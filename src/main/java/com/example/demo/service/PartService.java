package com.example.demo.service;

import com.example.demo.domain.Part;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service interface for managing Part entities.
 */
@Service
public interface PartService {
    List<Part> findAll();
    Part findById(int theId);
    void save(Part thePart);
    void deleteById(int theId);
    List<Part> listAll(String keyword);
    Part getPartWithProducts(Long partId);
}

