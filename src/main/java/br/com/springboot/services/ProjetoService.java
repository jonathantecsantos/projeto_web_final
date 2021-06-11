package br.com.springboot.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.springboot.domain.Projeto;
import br.com.springboot.exceptions.ExistingProjetoSameNameException;
import br.com.springboot.repository.ProjetoRepository;
import javassist.NotFoundException;

public class ProjetoService {

    
    @Autowired
    private ProjetoRepository projetoRepository;

    public Projeto createProjeto(Projeto projeto) throws ExistingProjetoSameNameException{
        if (projetoRepository.findByName(projeto.getNome()).isPresent())
            throw new ExistingProjetoSameNameException("Ja existe um projeto com esse nome");
            return projetoRepository.save(projeto);
    }

    public Projeto updateProjeto(Long id, Projeto projeto){
        projeto.setId(id);
        return projetoRepository.save(projeto);  
    }

    public List<Projeto> listAllProjetos(){
        return projetoRepository.findAll();
    }

    public Projeto findById(Long id) throws NotFoundException{
        return projetoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe um projeto com esse identificador!"));
    }

    public void deleteProjeto(Long id) {
        Projeto projetoToDelete = projetoRepository.findById(id).get();
        projetoRepository.delete(projetoToDelete);
    }


    
}
