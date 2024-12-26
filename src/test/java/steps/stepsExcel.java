package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static constants.Constants.buscarPorID;
import static utils.GenericoExcel.*;

public class stepsExcel {

    private static final String rutaDefecto = "C:\\git\\proyectoBaseAut\\src\\test\\resources\\";
    @Given("Valido el nombre del excel descargado {string}")
    public void validoElNombreDelExcelDescargado(String nombreExcel) {
        validarArchivoExcel(rutaDefecto+nombreExcel);
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
}
