package fr.carneyandco.sample.api.config.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * SpringBoot configuration bean dedicated for client stuff.
 */
@Configuration
public class WebClientConfig {

    /**
     * A restTemplate with logging features.
     *
     * @return A restTemplate with logging features.
     */
    @Bean
    public RestTemplate restTemplate() {
        // Buffering ClientHttpResponse to read twice
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
        // The restTemplate
        RestTemplate restTemplate = new RestTemplate(factory);

        // The logging interceptor
        restTemplate.getInterceptors().add(new LoggingRequestInterceptor("Appel sortant - "));

        return restTemplate;
    }

}
