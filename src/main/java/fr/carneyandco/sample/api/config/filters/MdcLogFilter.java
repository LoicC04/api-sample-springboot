package fr.carneyandco.sample.api.config.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Filtre appelé à chaque appel web pour tracer les appels à l'api
 */
public class MdcLogFilter extends GenericFilterBean {

    public static final String REQUEST_ID = "requestId";
    private static final Logger LOG = LoggerFactory.getLogger(MdcLogFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOG.trace("MdcLogFilter");
        try {
            MDC.put(REQUEST_ID, UUID.randomUUID().toString());
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}