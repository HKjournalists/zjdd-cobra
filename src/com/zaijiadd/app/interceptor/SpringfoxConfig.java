package com.zaijiadd.app.interceptor;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * 使用注解的方式来扫描API
 * 无需在Spring的xml配置文件来配置，由 @see @EnableWebMvc 代替
 * <p/>
 * <p> @author 刘新宇
 * <p/>
 * <p> @date 2015年4月26日 下午1:18:48
 * <p> @version 0.0.1
 */
@Configuration
@EnableWebMvc
@EnableSwagger
public class SpringfoxConfig extends WebMvcConfigurerAdapter {

    /**
     * Project Name
     */
    public static String PROJECT_NAME;

    static {
        String projectName = System.getProperty("user.dir");
        if (projectName.contains("hotel")) {
            int end = projectName.indexOf(File.separator, projectName.indexOf("hotel"));
            PROJECT_NAME = projectName.substring(projectName.lastIndexOf(File.separator, projectName.indexOf("hotel")) + 1, end == -1 ? projectName.length() : end);
        } else {
            PROJECT_NAME = "hotel-server";
        }
    }

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    /**
     * 链式编程 来定制API样式
     * 后续会加上分组信息
     *
     * @return
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns(".*")
                .apiVersion("0.0.1");
        //.swaggerGroup(PROJECT_NAME);
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                PROJECT_NAME + " API",
                PROJECT_NAME + " 后台API文档",
                "http://127.0.0.1:9081/api",
                "your@company.com",
                "MTA License",
                "MTA API License URL"
        );
        return apiInfo;
    }
}