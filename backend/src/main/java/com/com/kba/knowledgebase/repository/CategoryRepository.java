package com.com.kba.knowledgebase.repository;

import com.com.kba.knowledgebase.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
