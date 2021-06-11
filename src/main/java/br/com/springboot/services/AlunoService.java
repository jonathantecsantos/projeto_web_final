package br.com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.springboot.domain.Aluno;
import br.com.springboot.exceptions.ExistingAlunoSameNameException;
import br.com.springboot.repository.AlunoRepository;
import javassist.NotFoundException;

public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno createAluno(Aluno aluno) throws ExistingAlunoSameNameException{
        if (alunoRepository.findByName(aluno.getName()).isPresent())
            throw new ExistingAlunoSameNameException("Ja existe aluno com esse nome");
            return alunoRepository.save(aluno);
    }

    public Aluno updateAluno(Long id, Aluno aluno){
        aluno.setId(id);
        return alunoRepository.save(aluno);  
    }

    public List<Aluno> listAllAlunos(){
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id) throws NotFoundException{
        return alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe um aluno com esse identificador!"));
    }

    public void deleteAluno(Long id) {
        Aluno alunoToDelete = alunoRepository.findById(id).get();
        alunoRepository.delete(alunoToDelete);
    }
    
}
