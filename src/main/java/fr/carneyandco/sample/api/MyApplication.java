package fr.carneyandco.sample.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application.
 */
@SpringBootApplication
public class MyApplication {

    /**
     * Méthode d'exécution de l'application. Démarre le contexte Spring.
     *
     * @param args Arguments SpringBoot.
     */
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
