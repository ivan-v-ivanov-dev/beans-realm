package com.personal.beans.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static com.personal.beans.constants.Constants.POSTGRESQL_CONNECTION_POOL;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.personal.beans.repository.postgres",
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager")
public class PostgresConfig {

    @Value("${spring.datasource-postgres.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource-postgres.url}")
    private String dataBaseUrl;
    @Value("${spring.datasource-postgres.username}")
    private String dataBaseUsername;
    @Value("${spring.datasource-postgres.password}")
    private String dataBasePassword;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName(POSTGRESQL_CONNECTION_POOL);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(dataBaseUrl);
        hikariConfig.setUsername(dataBaseUsername);
        hikariConfig.setPassword(dataBasePassword);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.personal.beans.models");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(adapter);
        return factory;
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager(LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(postgresEntityManagerFactory.getObject());
        return manager;
    }
}
