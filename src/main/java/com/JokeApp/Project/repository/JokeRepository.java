package com.JokeApp.Project.repository;

import com.JokeApp.Project.model.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {

}
