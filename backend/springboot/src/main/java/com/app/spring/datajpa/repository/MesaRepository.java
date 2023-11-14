package com.app.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.spring.datajpa.model.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
	List<Mesa> findByNombreContaining(String nombre);
}
