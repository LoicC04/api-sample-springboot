package fr.carneyandco.sample.api.config;

import fr.carneyandco.sample.api.config.filters.MdcLogFilter;
import fr.carneyandco.sample.api.config.filters.RequestTraceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <b>Configuration supplémentaire de Spring MVC.</b>
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<MdcLogFilter> mdcLogFilter() {
        FilterRegistrationBean<MdcLogFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new MdcLogFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }

    /**
     * Filtre applicatif :
     * <ul>
     *     <li>logguant les appels entrants et leur réponse.</li>
     *     <li>Enrichissant les Headers d'un identifiant unique de requête.</li>
     * </ul>
     *
     * @return Le filtre RequestTraceFilter.
     */
    @Bean
    public FilterRegistrationBean<RequestTraceFilter> loggingFilter() {
        FilterRegistrationBean<RequestTraceFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new RequestTraceFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }

}
