package com.example.demo.repositories;

import com.example.demo.domain.AboutInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends CrudRepository<AboutInfo, Long> {
}
