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

import br.com.springboot.services.AlunoService;
import br.com.springboot.domain.Aluno;
import br.com.springboot.dto.AlunoDTO;
import br.com.springboot.dto.GenericResponseErrorDTO;
import br.com.springboot.exceptions.ExistingAlunoSameNameException;
import br.com.springboot.mapper.AlunoMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/alunos",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@Api(value = "Aluno")

public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AlunoMapper alunoMapper;



    @GetMapping
    @ApiOperation(value = "Busca uma lista com todos os alunos")
    public List<AlunoDTO> getAlunos(){
        List<Aluno> alunos = alunoService.listAllAlunos();
        return alunos.stream()
                        .map(alunoMapper::convertToAlunoDTO)
                        .collect(Collectors.toList());

    }
    

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um aluno pelo seu identificador")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(alunoMapper.convertToAlunoDTO(alunoService.findById(id)), HttpStatus.OK);
        } catch (NotFoundException e){
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    @ApiOperation(value = "Cria um novo aluno")
    public ResponseEntity<?> createAluno(@RequestBody AlunoDTO alunoDTO){
            try{
                Aluno aluno = alunoMapper.convertFromAlunoDTO(alunoDTO);
                return new ResponseEntity<>(alunoService.createAluno(aluno),HttpStatus.CREATED);

            }catch (ExistingAlunoSameNameException e){
                return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
            }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um aluno a partir do seu identificador")
    public AlunoDTO updateAluno(@PathVariable("id") Long id, @RequestBody AlunoDTO alunoDTO){
        Aluno aluno = alunoMapper.convertFromAlunoDTO(alunoDTO);
        return alunoMapper.convertToAlunoDTO(alunoService.updateAluno(id, aluno));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclui um aluno a partir do seu identificador")
    public void deleteAluno(@PathVariable Long id) {
        alunoService.deleteAluno(id);
    } 
}
