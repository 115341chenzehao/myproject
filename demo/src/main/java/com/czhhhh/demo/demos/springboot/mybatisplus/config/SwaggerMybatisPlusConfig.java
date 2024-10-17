package com.czhhhh.demo.demos.springboot.mybatisplus.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebFluxRequestHandlerProvider;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Swaager的配置类，启动Docket对象是其核心
 * 2、Knife4j也可以直接用这个配置类
 */
@Configuration
@EnableOpenApi //启用Swagger
public class SwaggerMybatisPlusConfig {
    @Bean(name = "MyBatisPlusDocket")
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.OAS_30) //swagger的版本
                .apiInfo(apiInfo3()).enable(true) //返回apiInfo的配置
                .groupName("Mybatis-Plus") //多个Swagger时，需要配置组名
                .select()
                // apis指定启用swagger接口---的包路径
                .apis(RequestHandlerSelectors.basePackage("com.czhhhh.demo.demos.springboot.mybatisplus.controller"))
                .paths(PathSelectors.any()) //匹配所有url--就是包路径下所有的url请求都配置swagger
                .build();

        return docket;
    }


    /**
     * ApiInfo---对于swagger文档的介绍
     * @return
     */
    private ApiInfo apiInfo3() {
        return new ApiInfoBuilder()
                .title("Mybatis-Plus的使用测试")
                .description("不多说了，这里是介绍")
                .contact(new Contact("czhhhh","https://czhhhhhtest.com","czhhhh@163.com"))
                .version("1.0")
                .build();
    }


    /**
     * 解决2.6.4以上的高版本springboot与springfox不兼容的问题
     * 出现空指针异常
     * @return
     */
    @Bean(value = "springfoxHandlerProviderBeanPostProcessor03")
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof WebMvcRequestHandlerProvider || bean instanceof WebFluxRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream()
                        .filter(mapping -> mapping.getPatternParser() == null)
                        .collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}
