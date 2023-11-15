package com.app.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.spring.datajpa.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
