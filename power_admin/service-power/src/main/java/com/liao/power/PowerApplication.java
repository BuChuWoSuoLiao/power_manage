package com.liao.power;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication(exclude = {PageHelperAutoConfiguration.class})
@EnableSwagger2WebMvc
@CrossOrigin    // 允许springboot跨域
public class PowerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerApplication.class, args);
    }

}
