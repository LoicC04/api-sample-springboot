package fr.carneyandco.sample.api.config.client;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Classe permettant d'intercepter toutes les requête HTTP de l'application.
 */
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    private final String prefix;

    public LoggingRequestInterceptor() {
        this.prefix = "";
    }

    public LoggingRequestInterceptor(String prefix) {
        super();
        this.prefix = (prefix != null && !prefix.trim().isEmpty()) ? prefix : "";
    }

    /**
     * Intercepteur pour logger les requêtes.
     *
     * @see ClientHttpRequestInterceptor#intercept(HttpRequest, byte[], ClientHttpRequestExecution)
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        LogInfo logRequest = traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        LogInfo logResponse = traceResponse(response);
        LOG.info("{} {} - {} - Request = {} - Response = [{}]", this.prefix, logRequest.getMainData(), logResponse.getMainData(), logRequest.getHeaderAndBody(), logResponse.getHeaderAndBody());
        return response;
    }

    private LogInfo traceRequest(HttpRequest request, byte[] body) throws IOException {
        return new LogInfo(String.format("%s %s", request.getMethod(), request.getURI()), String.format("[%s] ==> %s", request.getHeaders(), IOUtils.toString(body, StandardCharsets.UTF_8.toString())));
    }

    private LogInfo traceResponse(ClientHttpResponse response) throws IOException {
        String responseBody = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);
        return new LogInfo(String.format("%s %s", response.getStatusCode(), response.getStatusText()), String.format("[%s] ==> %s", response.getHeaders(), responseBody));
    }

    class LogInfo {
        private String mainData;
        private String headerAndBody;

        public LogInfo(String mainData, String headerAndBody) {
            this.mainData = mainData;
            this.headerAndBody = headerAndBody;
        }

        public String getMainData() {
            return this.mainData;
        }

        public String getHeaderAndBody() {
            return this.headerAndBody;
        }
    }

}