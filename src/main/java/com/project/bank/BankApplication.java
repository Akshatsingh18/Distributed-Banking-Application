package com.project.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.project.bank.model")
@EnableJpaRepositories(basePackages = "com.project.bank.Interface")
public class BankApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // This is what tells Tomcat how to run your Boot app
        return application.sources(BankApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }
}
