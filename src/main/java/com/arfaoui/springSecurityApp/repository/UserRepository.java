package com.arfaoui.springSecurityApp.repository;

import com.arfaoui.springSecurityApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email) ;
}
