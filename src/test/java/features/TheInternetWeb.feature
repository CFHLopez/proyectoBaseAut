# Autor: Christian F. H. López Chávez
# Automatizador: Christian F. H. López Chávez
# Fecha: 27/11/2024
# Última modificación: 27/11/2024

Feature: Pruebas Front
  Background:
    Given Ingreso a la web "https://the-internet.herokuapp.com/"
    And Visualizo la web "https://the-internet.herokuapp.com/"

    #CASO 1
  @Prueba2Front1
  Scenario: Levantar web computer
    Given Visualizo el titulo "Welcome to the-internet"
    And Visualizo el titulo "Available Examples"
    And Visualizo el link "A/B Testing"
    And Visualizo el link "Broken Images"
    And Visualizo el link "Context Menu"
    And Visualizo el link "Disappearing Elements"
    When Presiono el link "Disappearing Elements"
    Then Visualizo la web "https://the-internet.herokuapp.com/disappearing_elements"
    And Visualizo el titulo "Disappearing Elements"