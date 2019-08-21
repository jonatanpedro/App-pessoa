package br.com.zeroquo.appPessoa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/**")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
        registry
                .addMapping("/v2/api-docs")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
        registry
                .addMapping("/swagger-ui.html")
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
