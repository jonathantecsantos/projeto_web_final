package br.com.springboot.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "projetos")

public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrição")
    private String descricao;

    @Column(name = "descrição")
    private Professor professor;

    private List<Aluno> alunos;

    public Projeto(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome(){
        return this.nome;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setId(Long id2) {
        this.id = id2;
    }
    

    
}
