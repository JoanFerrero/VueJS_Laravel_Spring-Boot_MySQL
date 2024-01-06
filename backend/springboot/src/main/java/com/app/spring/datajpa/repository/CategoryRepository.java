package com.app.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.app.spring.datajpa.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT c.id, c.name_category, c.photo FROM categories c INNER JOIN mesas_categories mc ON c.id=mc.category_id INNER JOIN mesas m ON m.id=mc.mesa_id WHERE c.name_category != ALL (SELECT r.categoria FROM reservations r WHERE r.mesa_id = :mesaID) AND m.id = :mesaID GROUP BY c.id", nativeQuery = true)
    List<Category> findAllMesa(@Param("mesaID") Integer mesaID);
}
