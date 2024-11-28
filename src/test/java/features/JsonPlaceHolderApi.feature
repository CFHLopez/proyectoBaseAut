# Autor: Christian F. H. López Chávez
# Automatizador: Christian F. H. López Chávez
# Fecha: 27/11/2024
# Última modificación: 27/11/2024

Feature: Prueba de Api/MS

  #PRIMER CASO
  @PruebaGET
  Scenario: Peticion get al servicio
    Given Seteo la URL "https://jsonplaceholder.typicode.com/todos/"
    And Realizo una peticion "GET" al servicio
    When Valido el campo Codigo Status con valor "200"
    Then Valido la respuesta de la peticion
    And Valido el campo "id" con valor "8"
    And Valido el campo "title" con valor "quo adipisci enim quam ut ab"
    And Valido el campo "id" con valor "1"
    And Valido el campo "title" con valor "delectus aut autem"
    And Valido el campo "id" con valor "69"
    And Valido el campo "title" con valor "doloribus sint dolorum ab adipisci itaque dignissimos aliquam suscipit"