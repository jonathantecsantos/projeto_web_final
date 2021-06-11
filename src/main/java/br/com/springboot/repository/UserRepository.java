package br.com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    
}