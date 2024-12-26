# Autor: Christian F. H. López Chávez
# Automatizador: Christian F. H. López Chávez
# Fecha: 27/11/2024
# Última modificación: 27/11/2024

Feature: Prueba de Api/MS

  #PRIMER CASO
  @PruebaGET
  Scenario: Peticion get al servicio
    Given Seteo la URL "https://www.swapi.tech/api/people/10"
    And Realizo una peticion "GET" al servicio
    When Valido el campo Codigo Status con valor "200"
    Then Valido la respuesta de la peticion
    And Valido el campo "message" con valor "ok"
    And Valido el titulo "result"
    And Valido el campo "description" con valor "A person within the Star Wars universe"
    And Valido el subtitulo "properties"
    And Valido el campo "name" con valor "Obi-Wan Kenobi"
    And Valido el campo "gender" con valor "male"