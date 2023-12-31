package com.shrall.tutorialspringsecurity.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shrall.tutorialspringsecurity.entities.Role;
import com.shrall.tutorialspringsecurity.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    User findByRole(Role role);
}
