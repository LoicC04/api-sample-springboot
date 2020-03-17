package fr.carneyandco.sample.api.controllers;

import fr.carneyandco.sample.api.services.MySimpleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple Controller possédant une méthode HelloWorld.
 */
@RestController
@RequestMapping("/api/final")
public class FinalController {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FinalController.class);

    /**
     * Taitements métier.
     */
    private final MySimpleService service;

    @Autowired
    public FinalController(MySimpleService service) {
        this.service = service;
    }

    /**
     * Webservices HelloWorld.
     *
     * @return Un objet Json HelloWorld.
     */
    @GetMapping
    public String hello(){
        LOG.info("Hello world !");
        this.service.traitement();
        return "{\"message\":\"Hello world !\"}";
    }

}
