package com.sti.maven.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sti.maven.dao.AccountDao;
import com.sti.maven.dao.CustomerDao;
import com.sti.maven.dao.TransactionDao;
import com.sti.maven.dao.impl.AccountDaoImpl;
import com.sti.maven.dao.impl.CustomerDaoImpl;
import com.sti.maven.dao.impl.TransactionImpl;


@Configuration
public class DaoSpringConfig {

	@Bean
	public CustomerDao customerDao() {
		return new CustomerDaoImpl();
	}
	
	@Bean
	public AccountDao accountDao() {
		return new AccountDaoImpl();
	}
	
	@Bean
	public TransactionDao transactionDao() {
		return new TransactionImpl();
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
                .allowedHeaders("*");
            }
        };
    }
	
	 @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }

	
}
