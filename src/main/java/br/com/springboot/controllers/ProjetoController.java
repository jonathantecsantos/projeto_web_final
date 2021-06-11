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

import br.com.springboot.services.ProjetoService;
import br.com.springboot.domain.Projeto;
import br.com.springboot.dto.ProjetoDTO;
import br.com.springboot.dto.GenericResponseErrorDTO;
import br.com.springboot.exceptions.ExistingProjetoSameNameException;
import br.com.springboot.mapper.ProjetoMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;


@RestController
@RequestMapping(value = "/projetos",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@Api(value = "Projeto")

public class ProjetoController {
   
    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private ProjetoMapper projetoMapper;

    
    @GetMapping
    @ApiOperation(value = "Busca uma lista com todos os projetos")
    public List<ProjetoDTO> getProjetos(){
        List<Projeto> projetos = projetoService.listAllProjetos();
        return projetos.stream()
                        .map(projetoMapper::convertToProjetoDTO)
                        .collect(Collectors.toList());

    }
    

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um projeto pelo seu identificador")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(projetoMapper.convertToProjetoDTO(projetoService.findById(id)), HttpStatus.OK);
        } catch (NotFoundException e){
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    @ApiOperation(value = "Cria um novo projeto")
    public ResponseEntity<?> createProjeto(@RequestBody ProjetoDTO projetoDTO){
            try{
                Projeto projeto = projetoMapper.convertFromProjetoDTO(projetoDTO);
                return new ResponseEntity<>(projetoService.createProjeto(projeto),HttpStatus.CREATED);

            }catch (ExistingProjetoSameNameException e){
                return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
            }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um projeto a partir do seu identificador")
    public ProjetoDTO updateProjeto(@PathVariable("id") Long id, @RequestBody ProjetoDTO projetoDTO){
        Projeto projeto = projetoMapper.convertFromProjetoDTO(projetoDTO);
        return projetoMapper.convertToProjetoDTO(projetoService.updateProjeto(id, projeto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclui um projeto a partir do seu identificador")
    public void deleteAluno(@PathVariable Long id) {
        projetoService.deleteProjeto(id);
    } 

}
