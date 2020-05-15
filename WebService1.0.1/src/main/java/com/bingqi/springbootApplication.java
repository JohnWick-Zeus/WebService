package com.bingqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @SpringBootApplication 标注一个主程序类，表明这是一个SpringBoot应用
 * 注意application类不要直接放在main/java下面，且要将该类放在最外侧，即包含所有子包
 */
@SpringBootApplication
@ComponentScan(nameGenerator = AnnotationBeanNameGenerator.class)
@EnableSwagger2 //增加Swagger2的扫描注解：
public class springbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(springbootApplication.class, args);
    }
}
