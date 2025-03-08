package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.example.demo.service.KeyVaultService;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private final KeyVaultService keyVaultService;

    public DataSourceConfig(KeyVaultService keyVaultService) {
        this.keyVaultService = keyVaultService;
    }

    @Bean
    public DataSource dataSource() {
        keyVaultService.printDatabaseCredentials();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // Set Database Connection Properties from Key Vault
        dataSource.setUrl("jdbc:postgresql://firewallmgmt-dev-weu-psql.postgres.database.azure.com:5432/postgres?sslmode=require");
        dataSource.setUsername(keyVaultService.getSecret("db-username"));
        dataSource.setPassword(keyVaultService.getSecret("db-password"));
        dataSource.setDriverClassName("org.postgresql.Driver");

        return dataSource;
    }
}
