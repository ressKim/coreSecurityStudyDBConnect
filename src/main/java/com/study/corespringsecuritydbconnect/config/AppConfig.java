package com.study.corespringsecuritydbconnect.config;

import com.study.corespringsecuritydbconnect.repository.ResourcesRepository;
import com.study.corespringsecuritydbconnect.service.SecurityResourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfig {

    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository) {
        SecurityResourceService securityResourceService = new SecurityResourceService(resourcesRepository);
        return securityResourceService;
    }
}
