package com.tutorial.bikestores;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletRegistration;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;


@SpringBootApplication
public class BikestoresApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BikestoresApplication.class, args);
//
//        printBeanNames(context);
//        System.out.println("=".repeat(200));
//
//
//        WebApplicationContext webApplicationContext = (WebApplicationContext) context;
//        printFilterRegistrations(webApplicationContext);
//        System.out.println("=".repeat(200));
//        printServletRegistrations(webApplicationContext);
//        System.out.println("=".repeat(200));
    }

    private static void printBeanNames(ConfigurableApplicationContext context) {
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }

    private static void printServletRegistrations(WebApplicationContext webApplicationContext) {
        for (ServletRegistration servletRegistration : webApplicationContext.getServletContext().getServletRegistrations().values()) {
            System.out.print(servletRegistration.getClassName());
            System.out.println(" :   " + servletRegistration.getMappings());
            System.out.println("-".repeat(100));
        }
    }

    private static void printFilterRegistrations(WebApplicationContext webApplicationContext) {
        for (FilterRegistration filterRegistration : webApplicationContext.getServletContext().getFilterRegistrations().values()) {
            System.out.print(filterRegistration.getServletNameMappings());
            System.out.println(" :   " + filterRegistration.getUrlPatternMappings());
            System.out.println("-".repeat(100));
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}