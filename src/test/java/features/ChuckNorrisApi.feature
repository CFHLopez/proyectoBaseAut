# Autor: Christian F. H. López Chávez
# Automatizador: Christian F. H. López Chávez
# Fecha: 27/11/2024
# Última modificación: 27/11/2024

Feature: Prueba de Api/MS

  #PRIMER CASO
  @PruebaGET
  Scenario: Peticion get al servicio
    Given Seteo la URL "https://api.chucknorris.io/jokes/random"
    And Realizo una peticion "GET" al servicio
    When Valido el campo Codigo Status con valor "200"
    Then Valido la respuesta de la peticion
    And Valido el campo "icon_url" con valor
    And Valido el campo "id" con valor
    And Valido el campo "url" con valor
    And Valido el campo "value" con valor