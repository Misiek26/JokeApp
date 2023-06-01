package com.JokeApp.Project.repository;

import com.JokeApp.Project.model.User;
import com.JokeApp.Project.model.UserJoke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJokesRepository extends JpaRepository<UserJoke, Long> {
    @Query(value = "SELECT j.id, c.name AS category, j.setup, j.punchline, j.created FROM jokes j INNER JOIN categories c ON j.category_id = c.id WHERE j.user_id = ?1", nativeQuery = true)
    List<UserJoke> findAllUserJokes(Long userId);
}
