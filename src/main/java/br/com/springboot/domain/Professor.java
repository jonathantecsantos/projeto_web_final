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
@Table(name = "professores")


public class Professor extends User {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO) 
   private Long id;
   
   @Column(name = "name")
   private String name;
   
   @Column(name = "carga horaria")
   private int carga;

   public Professor(String nome,int carga){
       this.name = nome;
       this.carga = carga;
   }

   public String getName(){
       return this.name;
   }

   public void setId(Long novoId){
       this.id = novoId;
   }
   
}
