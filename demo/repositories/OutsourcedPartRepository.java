package com.example.demo.repositories;

import com.example.demo.domain.OutsourcedPart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 *
 *
 */
@Repository
public interface OutsourcedPartRepository extends CrudRepository<OutsourcedPart,Long> {
    OutsourcedPart findByName(String outTest);
}
