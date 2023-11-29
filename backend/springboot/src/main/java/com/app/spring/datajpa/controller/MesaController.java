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
			Integer offset = (mesaQueryParam.getPage() - 1) * mesaQueryParam.getLimit();

			//Only Category
			if (mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getOrder() == 0 && mesaQueryParam.getCapacity() == 0) {
				mesaRepository.findCategoriesOnMesa(mesaQueryParam.getCategories(), mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);

			//Only Capacity
			} else if(mesaQueryParam.getCapacity() > 0 && mesaQueryParam.getCategories().length == 0 && mesaQueryParam.getOrder() == 0){
				mesaRepository.findCapasityMesa(mesaQueryParam.getCapacity(), mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);

			//Only Order
			} else if(mesaQueryParam.getCategories().length == 0 && mesaQueryParam.getCapacity() == 0 && mesaQueryParam.getOrder() > 0) {
				if(mesaQueryParam.getOrder() == 1) {
					mesaRepository.findOrderASC(mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);
				} else {
					mesaRepository.findOrderDESC( mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);
				}

			//Capacity with Order
			} else if(mesaQueryParam.getCategories().length == 0 && mesaQueryParam.getCapacity() > 0 && mesaQueryParam.getOrder() > 0) {
				if(mesaQueryParam.getOrder() == 1) {
					mesaRepository.findCapacityASC(mesaQueryParam.getCapacity(), mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);
				} else {
					mesaRepository.findCapacityDESC(mesaQueryParam.getCapacity(), mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);
				}

			//Category with Order
			} else if(mesaQueryParam.getCategories().length == 0 && mesaQueryParam.getCapacity() == 0 && mesaQueryParam.getOrder() > 0) {
				if(mesaQueryParam.getOrder() == 1) {
					mesaRepository.findCategoriesASC(mesaQueryParam.getCategories(), mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);
				} else {
					mesaRepository.findCategoriesDESC(mesaQueryParam.getCategories(), mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);
				}

			//category with capacity
			} else if(mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getCapacity() > 0 && mesaQueryParam.getOrder() == 0) {
				mesaRepository.findCategoryWithCapacityOnMesa(mesaQueryParam.getCategories(), mesaQueryParam.getCapacity(), mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);

			//category with capacity and order
			} else if(mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getCapacity() > 0 && mesaQueryParam.getOrder() > 0) {
				mesaRepository.findCategoryWithCapacityOnMesa(mesaQueryParam.getCategories(), mesaQueryParam.getCapacity(), mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);
			
			//All Mesa	
			} else {
				mesaRepository.findActive(mesaQueryParam.getName_mesa(), mesaQueryParam.getLimit(), offset).forEach(mesas::add);
			}

			return new ResponseEntity<>(mesas, HttpStatus.OK);
		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/mesaPaginate")
	public ResponseEntity<Integer> getAllMesasPagination(@ModelAttribute MesaQueryParam mesaQueryParam) {
		try {
			mesaQueryParam.setName_mesa(mesaQueryParam.getName_mesa() + '%');
			Integer total = 0;
			if(mesaQueryParam.getCategories().length == 0 && mesaQueryParam.getCapacity() > 0){
				total = mesaRepository.findByCapacityPaginate(mesaQueryParam.getCapacity(), mesaQueryParam.getName_mesa());
			} else if (mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getCapacity() == 0) {
				total = mesaRepository.findCategoriesOnMesaPaginate(mesaQueryParam.getCategories(), mesaQueryParam.getName_mesa());
			} else if (mesaQueryParam.getCategories().length > 0 && mesaQueryParam.getCapacity() > 0){
				total = mesaRepository.findByCapacityAndCategoriesPaginate(mesaQueryParam.getCapacity(),mesaQueryParam.getCategories(), mesaQueryParam.getName_mesa());
			} else {
				total = mesaRepository.findActivePaginate(mesaQueryParam.getName_mesa());
			}
			return new ResponseEntity<>(total, HttpStatus.OK);
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

	@GetMapping("/mesaInfinite")
	public ResponseEntity<List<Mesa>> getGigMesas(@ModelAttribute MesaQueryParam mesaQueryParam) {
		try {
			List<Mesa> mesas = new ArrayList<Mesa>();
			Integer limit = mesaQueryParam.getPage() * mesaQueryParam.getLimit();
			mesaRepository.findBigTables(limit).forEach(mesas::add);
			return new ResponseEntity<>(mesas, HttpStatus.OK);

		} catch (Exception e) {
			System.err.println(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
