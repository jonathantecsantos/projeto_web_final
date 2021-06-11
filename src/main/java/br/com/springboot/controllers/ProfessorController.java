package br.com.springboot.controllers;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.services.ProfessorService;
import br.com.springboot.domain.Professor;
import br.com.springboot.dto.ProfessorDTO;
import br.com.springboot.dto.GenericResponseErrorDTO;
import br.com.springboot.exceptions.ExistingProfessorSameNameException;
import br.com.springboot.mapper.ProfessorMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/professores",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@Api(value = "Professor")

public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ProfessorMapper professorMapper;
    
    @GetMapping
    @ApiOperation(value = "Busca uma lista com todos os professores")
    public List<ProfessorDTO> getProfessores(){
        List<Professor> professores = professorService.listAllProfessores();
        return professores.stream()
                        .map(professorMapper::convertToProfessorDTO)
                        .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um professor pelo seu identificador")
    public ResponseEntity<?> getProfessorById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(professorMapper.convertToProfessorDTO(professorService.findById(id)), HttpStatus.OK);
        } catch (NotFoundException e){
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    @ApiOperation(value = "Cria um novo professor")
    public ResponseEntity<?> createProfessor(@RequestBody ProfessorDTO professorDTO){
            try{
                Professor professor = professorMapper.convertFromProfessorDTO(professorDTO);
                return new ResponseEntity<>(professorService.createProfessor(professor),HttpStatus.CREATED);

            }catch (ExistingProfessorSameNameException e){
                return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
            }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um professor a partir do seu identificador")
    public ProfessorDTO updateProfessor(@PathVariable("id") Long id, @RequestBody ProfessorDTO professorDTO){
        Professor professor = professorMapper.convertFromProfessorDTO(professorDTO);
        return professorMapper.convertToProfessorDTO(professorService.updateProfessor(id, professor));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclui um professor a partir do seu identificador")
    public void deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
    } 
}
