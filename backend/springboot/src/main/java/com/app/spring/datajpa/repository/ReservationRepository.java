package com.app.spring.datajpa.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.spring.datajpa.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // All Reservations
    @Query(value = "SELECT r.id, r.fecha_reserva, r.categoria, r.mesa_id, r.user_id, r.accepted FROM `reservations` r INNER JOIN users u ON u.id=r.user_id WHERE u.username=  :username ", nativeQuery = true)
    List<Reservation> findAll(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM reservations r WHERE r.mesa_id = :id AND r.categoria = :category", nativeQuery = true)
    Integer mesaAvailable(@Param("id") Long id, @Param("category") String category);

    @Query(value = "SELECT * FROM reservations r WHERE r.id = :id AND r.user_id = (SELECT u.id FROM users u where u.username = :username)", nativeQuery = true)
    List<Reservation> findId(@Param("id") Long id, @Param("username") String username );
}
