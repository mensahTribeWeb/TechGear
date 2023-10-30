package com.example.demo.repositories;

import com.example.demo.domain.InhousePart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 *
 *
 */
@Repository
public interface InhousePartRepository extends CrudRepository<InhousePart,Long> {
}
