package org.document.common;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan
public class Common {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}