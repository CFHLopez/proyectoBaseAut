Feature: Pruebas Front
  Background:
    Given Ingreso a la web "https://computer-database.gatling.io/computers"
    And Visualizo la web "https://computer-database.gatling.io/computers"

    #CASO 1
  @PruebaFront1
  Scenario: Levantar web computer
    Given Visualizo el titulo "Computer database"
    And Visualizo el titulo "574 computers found"
    And Visualizo el boton Filter
    And Visualizo el boton Add
    And Visualizo el filtro
    And Visualizo la tabla Computers
    And Visualizo la columna "Computer name"
    And Visualizo la columna "Introduced"
    When Visualizo la columna "Discontinued"
    Then Visualizo la columna "Company"

  @PruebaFront2
  Scenario: Validar datos de la tabla
    Given Visualizo el titulo "Computer database"
    And Visualizo la tabla Computers
    And Visualizo la columna "Computer name" con valor "AN/FSQ-32"
    And Visualizo la columna "Introduced" con valor "01 Jan 1960"
    And Visualizo la columna "Discontinued" con valor "-"
    And Visualizo la columna "Company" con valor "IBM"
    And Visualizo la columna "Computer name" con valor "ASCI Purple"
    And Visualizo la columna "Introduced" con valor "01 Jan 2005"
    And Visualizo la columna "Discontinued" con valor "-"
    And Visualizo la columna "Company" con valor "IBM"
    And Visualizo la columna "Computer name" con valor "ASCI Blue Pacific"
    And Visualizo la columna "Introduced" con valor "01 Jan 1998"
    When Visualizo la columna "Discontinued" con valor "-"
    Then Visualizo la columna "Company" con valor "IBM"