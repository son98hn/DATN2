package com.example.datn.repository;

import com.example.datn.entity.CategoryParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryParentRepository extends JpaRepository<CategoryParentEntity, Long> {
    CategoryParentEntity findByCode(String code);
}
