package br.com.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponseErrorDTO {
    private String error;

    public GenericResponseErrorDTO(String erro){
        this.error = erro;
    }

    public String getErro(){
        return this.error;
    }
}

