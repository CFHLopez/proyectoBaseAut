# Autor: Christian F. H. López Chávez
# Automatizador: Christian F. H. López Chávez
# Fecha: 27/11/2024
# Última modificación: 27/11/2024

Feature: Pruebas Front
  Background:
    Given Ingreso a la web "https://opencart.abstracta.us/"
    And Visualizo la web "https://opencart.abstracta.us/"

    #CASO 1
  @Prueba2Front1
  Scenario: Levantar web computer
    Given Visualizo el titulo "Your Store"
    And Visualizo la columna "Desktops"
    And Visualizo la columna "Components"
    And Visualizo la columna "Tablets"
    And Visualizo la columna "Software"
    And Visualizo la columna "Cameras"
    When Presiono la columna "Software"
    Then Visualizo el titulo "Software"