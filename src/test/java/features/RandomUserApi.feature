# Autor: Christian F. H. López Chávez
# Automatizador: Christian F. H. López Chávez
# Fecha: 27/11/2024
# Última modificación: 27/11/2024

Feature: Prueba de Api/MS

  #PRIMER CASO
  @PruebaGET
  Scenario: Peticion get al servicio
    Given Seteo la URL "https://randomuser.me/api/"
    And Realizo una peticion "GET" al servicio
    When Valido el campo Codigo Status con valor "200"
    Then Valido la respuesta de la peticion
    And Valido el titulo "results"
    And Valido el campo "gender" con valor
    And Valido el subtitulo "name"
    And Valido el campo "title" con valor
    And Valido el campo "first" con valor
    And Valido el campo "last" con valor
    And Valido el subtitulo "dob"
    And Valido el campo "date" con valor
    And Valido el campo "age" con valor