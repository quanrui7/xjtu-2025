package com.mybatisplusdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = {"com.mybatisplusdemo.mapper"})
public class MybatisPlusDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(MybatisPlusDemoApplication.class, args);

    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(MybatisPlusDemoApplication.class);
    }
}
