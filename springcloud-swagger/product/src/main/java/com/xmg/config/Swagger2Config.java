package com.xmg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2API文档的配置
 * @author makui
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(globalRequestParameters())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xmg"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes();
    }

    private List<Parameter> globalRequestParameters() {
        List<Parameter> list = new ArrayList<>();
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        final Parameter parameter = parameterBuilder.name("Authorization")
                .description("认证令牌,登录接口获取")
                .modelRef(new ModelRef("string"))
                .defaultValue("token")
                .parameterType("header")
                .build();
        list.add(parameter);
        return list;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("product接口")
                .description("product接口")
                .contact(new Contact("xmg", "https://github.com/makui12315", "kuim123@qq.com"))
                .version("1.0")
                .build();
    }
}
