package com.JokeApp.Project.repository;

import com.JokeApp.Project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM categories c WHERE c.name = :name", nativeQuery = true)
    Optional<Category> findByName(@Param("name") String name);
}
