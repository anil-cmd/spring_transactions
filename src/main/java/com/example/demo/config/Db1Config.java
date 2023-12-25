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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
basePackages = {"com.example.demo.repo"})
public class Db1Config {
	
	@Primary
	@Bean(name = "datasource")
	@ConfigurationProperties(prefix = "spring.db1.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("datasource") DataSource dataSource) {
    	
    	HashMap<String, Object> properties = new HashMap<>();
    	properties.put("hibernate.hbm2ddl.auto", "update");
//    	properties.put("spring.db1.datasource.url", "jdbc:mysql://localhost:3306/sbms36")
    	
//    		spring.db1.datasource.driver-class-name=com.mysql.jdbc.Driver

    	return builder.dataSource(dataSource)
    			.properties(properties)
    			.packages("com.example.demo.entity")
    			.persistenceUnit("db1")
    			.build();
    }
    
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
    	
    	return new JpaTransactionManager(entityManagerFactory);
		
	}
	

}
