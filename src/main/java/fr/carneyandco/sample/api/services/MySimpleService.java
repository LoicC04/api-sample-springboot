package fr.carneyandco.sample.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MySimpleService {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(MySimpleService.class);

    /**
     * Propriétés d'environnement Spring.
     */
    private Environment env;

    /**
     * Constructeur et injection Spring.
     *
     * @param env Propriétés d'environnement Spring.
     */
    public MySimpleService(Environment env) {
        this.env = env;
    }

    /**
     * Simple méthode de traitement.
     * @return false.
     */
    public boolean traitement() {
        LOG.info("Profiles : {}", env.getActiveProfiles());
        return false;
    }

}
