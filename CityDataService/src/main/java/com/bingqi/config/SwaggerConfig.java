package com.bingqi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger2的配置类
 * written by zhanggong
 */
@Configuration
@EnableSwagger2 // 是否开启swagger，正式环境可以关闭
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bingqi.controller")) //要扫描的Controller层
                .paths(PathSelectors.any()) // and by paths
                .build()
                .apiInfo(apiInfo());
    }
    @Value("${server.address}")private String address;
    @Value("${server.port}")private String port;
    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WeatherDataService天气数据服务")
                .description("Author：zhanggong Tel：13140727620") // 任意，请稍微规范点
                .termsOfServiceUrl(address+":"+port+"/swagger-ui.html") // 将“url”换成自己的ip:port
                .version("1.0.0")
                .build();
    }
}
