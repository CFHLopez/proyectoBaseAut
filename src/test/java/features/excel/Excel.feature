# Autor: Christian F. H. López Chávez
# Automatizador: Christian F. H. López Chávez
# Fecha: 26/12/2024
# Última modificación: 26/12/2024

Feature: Prueba de Excel sobre Datos Clientes

#PRIMER CASO
  @PruebaLecturaExcel
  Scenario: Validar columnas del excel de prueba
    Given Valido el nombre del excel descargado "PruebaExcel.xlsx"
    When Valido la hoja con nombre "Hoja1"
    Then Valido las columnas del excel
    | columna                   |
    | ID                        |
    | Nombre completo           |
    | Fecha de nacimiento       |
    | Dirección                 |
    | Localidad y Código postal |
    | Teléfono                  |
    | Correo electrónico        |
    | Fecha de alta             |
    | Grupo de clientes         |

#SEGUNDO CASO
  @PruebaLecturaExcel2
  Scenario: Validar columnas con datos en el excel de prueba
    Given Valido el nombre del excel descargado "PruebaExcel.xlsx"
    When Valido la hoja con nombre "Hoja1"
    Then Valido las columnas con datos del excel
      | columna                   | dato                        |
      | ID                        | C0015                       |
      | Nombre completo           | Jacinta Montenegro Garcés   |
      | Fecha de nacimiento       | 13-03-1994                  |
      | Dirección                 | 59 Ridgewood Ave.           |
      | Localidad y Código postal | Reynoldsburg, OH 43068      |
      | Teléfono                  | (969) 383-4277              |
      | Correo electrónico        | yangyan@yahoo.ca            |
      | Fecha de alta             | 06-04-2007 6:15             |
      | Grupo de clientes         | D                           |
      | ID                        | C0050                       |
      | Nombre completo           | Abel Alex Cueto López       |
      | Fecha de nacimiento       | 07-09-1974                  |
      | Dirección                 | 94 The Avenue               |
      | Localidad y Código postal | BRIGHTON                    |
      | Teléfono                  | (951) 953-8812              |
      | Correo electrónico        | jgmyers@icloud.com          |
      | Fecha de alta             | 18-06-2009 6:22             |
      | Grupo de clientes         | A                           |
