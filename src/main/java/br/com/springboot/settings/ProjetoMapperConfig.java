package br.com.springboot.settings;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.springboot.mapper.ProjetoMapper;

@Configuration
public class ProjetoMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ProjetoMapper projetoMapper() {
        return new ProjetoMapper();
    }
    
}
