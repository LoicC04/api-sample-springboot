package fr.carneyandco.sample.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Controller faisant appel à d'autres webservices.
 */
@RestController
@RequestMapping("/api/call")
public class CallingController {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CallingController.class);

    /**
     * Client RestTemplate permettant de transférer le token d'authentification Openid.
     */
    private RestTemplate restTemplate;

    /**
     * Constructeur principal de l'application.
     *
     * @param restTemplate Injection d'un client Rest.
     */
    @Autowired
    public CallingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Webservices appelant un second webservice.
     *
     * @return Une chaine de caractères.
     */
    @GetMapping("/callAnotherService")
    public String callAnotherService() {
        LOG.info("callAnotherService !");

        ResponseEntity<String> result = this.restTemplate.getForEntity("http://localhost:8080/api/final", String.class);

        String username = "Unknown";
        String message = "No message.";
        if (HttpStatus.OK.equals(result.getStatusCode())) {
            message = result.getBody();
        }
        return "You are : " + username + "\n" + message;
    }

}
