package com.ross.gamis.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ross.gamis.converter.DeveloperConverter;
import com.ross.gamis.converter.GameConverter;
import com.ross.gamis.converter.StoreConverter;
import com.ross.gamis.service.StoreService;

@Configuration
public class AppConfig {
    private final StoreService storeService;

    public AppConfig(StoreService storeService) {
        this.storeService = storeService;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public GameConverter gameConverter() {
        return new GameConverter(storeService);
    }

    @Bean
    public StoreConverter storeConverter() {
        return new StoreConverter();
    }

    @Bean
    public DeveloperConverter developerConverter() {
        return new DeveloperConverter();
    }
}
