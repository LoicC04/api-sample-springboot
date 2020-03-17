package fr.carneyandco.sample.api.config.api;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Bean de configuration de {@link io.swagger.models.Swagger}
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Value("${project.version}")
    private String version;

    /**
     * Configuration générale de Swagger.
     *
     * @return La configuration.
     */
    @Bean
    public Docket conf() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.carneyandco.sample.api"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Bean écrivant les méta-données décrivant l'API.
     *
     * @return Le bean {@link ApiInfo}.
     */
    @Bean
    public ApiInfo apiInfo() {
        final ApiInfoBuilder builder = new ApiInfoBuilder();
        builder
                .title("APIs PIMM")
                .description("Liste des APIs de l'application")
                .version(version);

        return builder.build();
    }
}
