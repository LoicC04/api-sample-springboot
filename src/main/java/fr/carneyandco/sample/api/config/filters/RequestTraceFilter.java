package fr.carneyandco.sample.api.config.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

public class RequestTraceFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(RequestTraceFilter.class);

    /**
     * Create a new {@link RequestTraceFilter} instance.
     */
    public RequestTraceFilter() {
        super();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws
            ServletException,
            IOException {
        LOG.trace("RequestTraceFilter");

        long start = System.currentTimeMillis();


        // Wrapping I/O
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        // Call other filters
        filterChain.doFilter(requestWrapper, responseWrapper);

        // REQUEST
        String requestUrl = requestWrapper.getRequestURL().toString();
        HttpHeaders requestHeaders = new HttpHeaders();
        Enumeration headerNames = requestWrapper.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            requestHeaders.add(headerName, requestWrapper.getHeader(headerName));
        }
        String authorization = requestWrapper.getHeader("Authorization");
        String distributeur = "UNKNOWN";
        String issuer = "UNKNOWN";
        if (StringUtils.isNotBlank(distributeur) && StringUtils.startsWith(authorization, "Bearer") && authorization.contains(".")) {
            String token = authorization.split(" ")[1];
            String payload = token.split("\\.")[1];
            payload = new String(Base64.decodeBase64(payload));
            distributeur = new ObjectMapper().readTree(payload).path("entite").textValue();
            issuer = new ObjectMapper().readTree(payload).path("iss").textValue();
        }
        HttpMethod httpMethod = HttpMethod.valueOf(requestWrapper.getMethod());

        String requestBody = IOUtils.toString(requestWrapper.getContentAsByteArray(), String.valueOf(StandardCharsets.UTF_8));
        if (StringUtils.isNotBlank(requestBody)) {
            // Suppression des sauts de ligne pour des logs plus exploitables
            requestBody = requestBody.replaceAll(System.lineSeparator(), "");
        }


        // RESPONSE
        HttpStatus responseStatus = HttpStatus.valueOf(responseWrapper.getStatus());
        HttpHeaders responseHeaders = new HttpHeaders();
        for (String headerName : responseWrapper.getHeaderNames()) {
            responseHeaders.add(headerName, responseWrapper.getHeader(headerName));
        }
        String responseBody = IOUtils.toString(responseWrapper.getContentInputStream(), StandardCharsets.UTF_8);
        if (StringUtils.isNotBlank(responseBody)) {
            responseBody = responseBody.replaceAll(System.lineSeparator(), "");
        }

        long duree = System.currentTimeMillis() - start;

        LOG.info("{} {} - {} - {}ms - [{} {}] - Request = [{}] {} - Response = [{}] {}", httpMethod, requestUrl, responseStatus, duree, distributeur, issuer, requestHeaders, requestBody, responseHeaders, responseBody);

        responseWrapper.copyBodyToResponse();
    }

}