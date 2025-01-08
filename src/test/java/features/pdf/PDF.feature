# Autor: Christian F. H. López Chávez
# Automatizador: Christian F. H. López Chávez
# Fecha: 26/12/2024
# Última modificación: 26/12/2024

Feature: Prueba de PDF lectura

#PRIMER CASO
  @PruebaLecturaPDF
  Scenario: Validar contenido del pdf
    Given Valido el nombre del archivo descargado "cvsamples.pdf"
    When Valido el contenido del PDF
    Then Valido los titulos del PDF
    | titulo                    |
    | Curriculum Vitae          |
    | Tips and Samples          |
    | THE BASICS                |
    | FORMATTING                |
    | DESCRIBE YOUR EXPERIENCES |
    | SECTIONS TO INCLUDE       |
