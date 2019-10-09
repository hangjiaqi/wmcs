package com.laurus.wmcs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.laurus.wmcs.mapper")
public class WmcsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmcsApplication.class, args);
    }

}
