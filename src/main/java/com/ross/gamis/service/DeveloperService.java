package com.ross.gamis.service;

import org.springframework.stereotype.Service;

import com.ross.gamis.domain.Developer;
import com.ross.gamis.repository.DeveloperRepository;

import java.util.List;

@Service
public class DeveloperService {
    private final DeveloperRepository developerRepository;

    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }
    
    public Developer getDeveloperById(Long id) {
        return developerRepository.getDeveloperById(id);
    }

    public List<Developer> getDevelopers() {
        return developerRepository.getDevelopers();
    }
}
