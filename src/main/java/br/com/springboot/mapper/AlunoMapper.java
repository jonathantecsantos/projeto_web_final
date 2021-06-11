package br.com.springboot.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.springboot.domain.Aluno;
import br.com.springboot.dto.AlunoDTO;

public class AlunoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AlunoDTO convertToAlunoDTO(Aluno aluno){
        AlunoDTO alunoDTO = modelMapper.map(aluno,AlunoDTO.class);
        return alunoDTO;
    }

    public Aluno convertFromAlunoDTO(AlunoDTO alunoDTO){
        Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);
        return aluno;
    }
    
}
