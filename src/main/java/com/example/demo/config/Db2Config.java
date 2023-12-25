package com.example.demo.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory2",
basePackages = {"com.example.demo.repo2"})
public class Db2Config {
	

	@Bean(name = "datasource2")
	@ConfigurationProperties(prefix = "spring.db2.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	

    @Bean(name = "entityManagerFactory2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory2(EntityManagerFactoryBuilder builder, @Qualifier("datasource2") DataSource dataSource) {
    	
    	HashMap<String, Object> properties = new HashMap<>();
    	properties.put("hibernate.hbm2ddl.auto", "update");

    	return builder.dataSource(dataSource)
    			.properties(properties)
    			.packages("com.example.demo.entity2")
    			.persistenceUnit("db2")
    			.build();
    }
    

    @Bean(name = "transactionManager2")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory2") EntityManagerFactory entityManagerFactory2) {
    	
    	return new JpaTransactionManager(entityManagerFactory2);
		
	}
	

}
