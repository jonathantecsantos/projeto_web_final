package br.com.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.springboot.domain.Professor;
import br.com.springboot.exceptions.ExistingProfessorSameNameException;
import br.com.springboot.repository.ProfessorRepository;
import javassist.NotFoundException;


public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Professor createProfessor(Professor professor) throws ExistingProfessorSameNameException{
        if (professorRepository.findByName(professor.getName()).isPresent())
            throw new ExistingProfessorSameNameException("Ja existe professor com esse nome");
            return professorRepository.save(professor);
    }

    public Professor updateProfessor(Long id, Professor professor){
        professor.setId(id);
        return professorRepository.save(professor);  
    }

    public List<Professor> listAllProfessores(){
        return professorRepository.findAll();
    }

    public Professor findById(Long id) throws NotFoundException{
        return professorRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe um professor com esse identificador!"));
    }

    public void deleteProfessor(Long id) {
        Professor professorToDelete = professorRepository.findById(id).get();
        professorRepository.delete(professorToDelete);
    }

    
}
