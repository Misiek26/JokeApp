package com.JokeApp.Project.repository;

import com.JokeApp.Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
