package br.com.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.springboot.domain.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {

        Optional<Professor> findByName(String name);
    
}
