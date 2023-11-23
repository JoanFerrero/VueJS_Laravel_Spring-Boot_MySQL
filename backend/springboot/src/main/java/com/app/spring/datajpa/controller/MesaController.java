package com.app.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.spring.datajpa.model.Mesa;
import com.app.spring.datajpa.model.MesaQueryParam;
import com.app.spring.datajpa.repository.MesaRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class MesaController {

	@Autowired
	MesaRepository mesaRepository;

	@GetMapping("/mesa")
	public ResponseEntity<List<Mesa>> getAllMesas(@ModelAttribute MesaQueryParam mesaQueryParam) {
		try {
			mesaQueryParam.setName_mesa(mesaQueryParam.getName_mesa() + '%');
			List<Mesa> mesas = new ArrayList<Mesa>();
			if (mesaQueryParam.getCategories().length > 0) {
				mesaRepository
				.findCategoriesOnMesa(mesaQueryParam.getCategories(), mesaQueryParam.getName_mesa())
				.forEach(mesas::add);
			} else {
				mesaRepository.findActive(mesaQueryParam.getName_mesa()).forEach(mesas::add);
			}

			return new ResponseEntity<>(mesas, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/mesa/{id}")
	public ResponseEntity<Mesa> getMesaById(@PathVariable("id") long id) {
		Optional<Mesa> mesaData = mesaRepository.findById(id);

		if (mesaData.isPresent()) {
			return new ResponseEntity<>(mesaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
