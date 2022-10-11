package com.implementationcms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ImplementationCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImplementationCmsApplication.class, args);
    }

}
