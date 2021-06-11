package br.com.springboot.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "alunos")

public class Aluno extends User {
   
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO) 
   private Long id;
   
   @Column(name = "name")
   private String name;

   @Column(name = "matricula")
   private int matricula;

   public Aluno(String name,int matricula){
       
       this.name = name;
       this.matricula = matricula;
   }

  public String getName() {
    return this.name;
  }

public void setId(Long id2) {
    this.id = id2;
}
    
}
