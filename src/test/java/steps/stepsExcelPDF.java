package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

import static constants.Constants.bodyrest;
import static constants.Constants.buscarPorID;
import static utils.GenericoExcel.*;
import static utils.GenericoPDF.*;
import static utils.MetodosGenericos.*;

public class stepsExcelPDF {

    private static final String rutaDefecto = "C:\\git\\proyectoBaseAut\\src\\test\\resources\\";

    @Given("Valido el nombre del archivo descargado {string}")
    public void validoElNombreDelArchivoExcelDescargado(String nombreArchivo) {
        validarArchivo(rutaDefecto+nombreArchivo);
    }

    @When("Valido la hoja con nombre {string}")
    public void validoLaHojaConNombre(String nombreHoja) {
        validarNombreHojaExcel(nombreHoja);
    }

    @Then("Valido las columnas del excel")
    public void validoLasColumnasDelExcel(List<String> columnas) {
        for (String columna : columnas){
            if (!columna.equals("columna")){
                validarNombreColumnaEnExcel(columna);
            }
        }
    }

    @Then("Valido las columnas con datos del excel")
    public void validoLasColumnasConDatosDelExcel(List<List<String>> filas) {
        for (int i = 1;i<filas.size();i++){
            List <String> fila = filas.get(i);
            if (fila.get(0).equals("ID")){
                buscarPorID = false;
            }
            validarCampoColumnaFila(fila.get(0),fila.get(1));
        }
    }

    @When("Valido el contenido del PDF")
    public void validoElContenidoDelPDF() {
        lecturaPDF();
    }

    @Then("Valido los titulos del PDF")
    public void validoLosTitulosDelPDF(List<String> titulos) {
        for (String titulo : titulos){
            if (!titulo.equals("titulo") && !bodyrest.contains(titulo)){
                imprimirConsolaMsjNegativo("Titulo NO encontrado");
            } else if (!titulo.equals("titulo")){
                Assert.assertTrue(bodyrest.contains(titulo));
                imprimirConsolaMsjPositivoVerde("OK -> "+titulo);
            }
        }
    }
}
