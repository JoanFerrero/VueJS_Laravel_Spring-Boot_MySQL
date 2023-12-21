package com.app.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.spring.datajpa.model.Reservation;
import com.app.spring.datajpa.model.User;
import com.app.spring.datajpa.model.Mesa;
import com.app.spring.datajpa.repository.MesaRepository;
import com.app.spring.datajpa.repository.ReservationRepository;
import com.app.spring.datajpa.repository.UserRepository;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class ReservationController {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	MesaRepository mesaRepository;

    @Autowired
    private UserRepository UserRepository;


    @GetMapping("/reservation")
    public ResponseEntity<?> profile() {
        try {
            List<Reservation> reservations = new ArrayList<Reservation>();
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            reservationRepository.findAll(userDetails.getUsername()).forEach(reservations::add);
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/reservation/{id}")
    public ResponseEntity<?> CreateReservation(@RequestBody Reservation reservation, @PathVariable(required = true) Long id) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            User user = UserRepository.findByUsername(userDetails.getUsername()).get();
            if(user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Mesa mesa = mesaRepository.findById(id).get();
            if(mesa == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Integer reserva = reservationRepository.mesaAvailable(id, reservation.getCategoria());
            if( reserva != 0) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            
            reservation.setMesa(mesa);
            reservation.setUser(user);
            reservationRepository.save(reservation);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("reservation/{id}")
    public ResponseEntity<?> editReservation(@RequestBody Reservation reservation_, @PathVariable(required = true) Long id) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            List<Reservation> reservation = reservationRepository.findId(id, userDetails.getUsername());

            //Validacion si la reserva es tuya y existe la reserva
            if(reservation.size() == 0) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Reservation reservation1 = reservationRepository.findById(id).get();
            if(reservation1.getAccepted() == true) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            Integer reserva = reservationRepository.mesaAvailable(id, reservation_.getCategoria());
            if( reserva != 0) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            //Guardar cambios
            reservation1.setCategoria(reservation_.getCategoria());
            reservation1.setFecha_reserva(reservation_.getFecha_reserva());
            reservationRepository.save(reservation1);
            return new ResponseEntity<>(reservation1, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    
    @DeleteMapping("reservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable String id) {
        try {
            List<Reservation> reservations = new ArrayList<Reservation>();
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            reservationRepository.findAll(userDetails.getUsername()).forEach(reservations::add);
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
