package com.app.spring.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.spring.datajpa.model.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
