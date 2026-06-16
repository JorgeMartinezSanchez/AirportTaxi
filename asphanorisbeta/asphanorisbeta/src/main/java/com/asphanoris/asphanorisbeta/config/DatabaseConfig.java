package com.asphanoris.asphanorisbeta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.asphanoris.asphanorisbeta.repository")
@EnableTransactionManagement
public class DatabaseConfig {
    // La configuración real está en application.properties
    // Esta clase solo habilita JPA y transacciones
}