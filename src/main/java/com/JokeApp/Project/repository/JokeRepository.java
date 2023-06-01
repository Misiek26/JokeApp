package com.JokeApp.Project.repository;

import com.JokeApp.Project.model.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {

    @Query("SELECT j FROM Joke j INNER JOIN j.category c WHERE c.name = ?1")
    List<Joke> findAllByCategory(String categoryName);
}
