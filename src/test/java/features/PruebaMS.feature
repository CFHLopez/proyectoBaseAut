# Autor: Christian F. H. López Chávez
# Automatizador: Christian F. H. López Chávez
# Fecha: 04/09/2024
# Última modificación: 27/11/2024

Feature: Prueba de Api/MS

  #PRIMER CASO
  @PruebaGET
  Scenario: Peticion get al servicio
    Given Seteo la URL "https://pokeapi.co/api/v2/pokemon?limit=151"
    And Realizo una peticion "GET" al servicio
    When Valido el campo Codigo Status con valor "200"
    Then Valido la respuesta de la peticion

  #SEGUNDO CASO
  @PruebaGET2
  Scenario Outline: Peticion get al servicio validando campos
    Given Seteo la URL "https://pokeapi.co/api/v2/pokemon?limit=151"
    And Realizo una peticion "GET" al servicio
    And Valido el campo Codigo Status con valor "200"
    And Valido la respuesta de la peticion
    And Valido el campo "count" con valor "1302"
    When Valido el titulo "results"
    Then Valido el campo "name" con valor "<nombrePKM>"

    Examples:
    | nombrePKM |
    | bulbasaur |
    | ninetales |
    | pikachu   |
    | rhydon    |
    | mewtwo    |
    | dragonair |

  #TERCER CASO
  @PruebaGET3
  Scenario Outline: Peticion get al servicio validando campos de arreglos
    Given Seteo la URL "https://pokeapi.co/api/v2/pokemon-form/1/"
    And Realizo una peticion "GET" al servicio
    And Valido el campo Codigo Status con valor "200"
    And Valido la respuesta de la peticion
    And Valido el campo "name" con valor "bulbasaur"
    And Valido el campo "form_order" con valor "1"
    And Valido el campo "id" con valor "1"
    And Valido el campo "is_mega" con valor "false"
    And Valido el titulo "pokemon"
    And Valido el campo "name" con valor "bulbasaur"
    And Valido el titulo "types"
    And Valido el campo "slot" con valor "<numSlot>"
    And Valido el subtitulo "type"
    And Valido el campo "name" con valor "<nomType>"
    When Valido el titulo "version_group"
    Then Valido el campo "name" con valor "red-blue"

    Examples:
      | numSlot | nomType |
      | 1       | grass   |
      | 2       | poison  |