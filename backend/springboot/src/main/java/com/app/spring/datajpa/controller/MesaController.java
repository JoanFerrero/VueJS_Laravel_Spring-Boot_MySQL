package com.app.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.spring.datajpa.model.Mesa;
import com.app.spring.datajpa.repository.MesaRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MesaController {

	@Autowired
	MesaRepository mesaRepository;

	@GetMapping("/mesa")
	public ResponseEntity<List<Mesa>> getAllMesas(@RequestParam(required = false) String nombre) {
		try {
			List<Mesa> mesas = new ArrayList<Mesa>();

			if (nombre == null)
				mesaRepository.findAll().forEach(mesas::add);
			else
				mesaRepository.findByNombreContaining(nombre).forEach(mesas::add);

			if (mesas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(mesas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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

	@PostMapping("/mesa")
	public ResponseEntity<Mesa> createMesa(@RequestBody Mesa mesa) {
		try {
			Mesa _mesa = mesaRepository.save(new Mesa(mesa.getNombre(), mesa.getCategoria(), mesa.getCapacidad()));
			return new ResponseEntity<>(_mesa, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/mesa/{id}")
	public ResponseEntity<Mesa> updateMesa(@PathVariable("id") long id, @RequestBody Mesa mesa) {
		Optional<Mesa> mesaData = mesaRepository.findById(id);

		if (mesaData.isPresent()) {
			Mesa _mesa = mesaData.get();
			_mesa.setNombre(mesa.getNombre());
			_mesa.setCategoria(mesa.getCategoria());
			_mesa.setCapacidad(mesa.getCapacidad());
			return new ResponseEntity<>(mesaRepository.save(_mesa), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/mesa/{id}")
	public ResponseEntity<HttpStatus> deleteMesa(@PathVariable("id") long id) {
		try {
			mesaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
