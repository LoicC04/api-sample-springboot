# Authentification OpenId Connect

## Badges



## Fonctionnalités dans ce Projet

* 3 webservices :
  - `/api/final` : Web service Hello world
  - `/api/call/boo` : Web service qui renvoie "Bou !"
  - `/api/call/callAnotherService` : Web service qui appelle ensuite `/api/final` en transmettant le token d'authentification
* L'exposition d'une page static HTML d'accueil
* L'exposition d'une interface web Swagger pour tester les appels rapidement
* Un LoggerInterceptor : Permet de logguer les requêtes sortantes demandées par le `restTemplate`, leurs headers et les codes retours
* Des filtres : 
  - RequestTraceFilter : Permet de logguer les requêtes entrantes de l'application et leurs réponses
  - MdcLogFilter : Enrichi les logs avec un identifiant unique de requête
* Un système de log Logback

# Usage

## Local
* Démarrage de l'application :
  - `mvn spring-boot:run`
  - `java -jar poc-spring-openid.jar`
* Accès à l'application : http://localhost:8080/
