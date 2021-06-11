package br.com.springboot.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.springboot.domain.Projeto;
import br.com.springboot.dto.ProjetoDTO;

public class ProjetoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProjetoDTO convertToProjetoDTO(Projeto projeto){
        ProjetoDTO projetoDTO = modelMapper.map(projeto,ProjetoDTO.class);
        return projetoDTO;
    }

    public Projeto convertFromProjetoDTO(ProjetoDTO projetoDTO){
        Projeto projeto = modelMapper.map(projetoDTO, Projeto.class);
        return projeto;
    }


    
}
