package com.example.demo.repositories;

import com.example.demo.domain.AboutInfo;
import org.springframework.data.repository.CrudRepository;

public interface AboutRepository extends CrudRepository<AboutInfo, Long> {
}
