package br.com.springboot.settings;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.springboot.mapper.ProfessorMapper;

@Configuration
public class ProfessorMapperConfig {
 
    /*@Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }*/

    @Bean
    public ProfessorMapper professorMapper() {
        return new ProfessorMapper();
    }
}
