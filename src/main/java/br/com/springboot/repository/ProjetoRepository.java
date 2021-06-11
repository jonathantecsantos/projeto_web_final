package br.com.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.springboot.domain.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

    Optional<Projeto> findByName(String nome);
    
}
