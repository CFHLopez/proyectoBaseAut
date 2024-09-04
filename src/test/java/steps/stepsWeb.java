package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.pageComputers;

import static utils.web.*;

public class stepsWeb {
    @Given("Ingreso a la web {string}")
    public void ingresoALaWeb(String url) {
        levantarWeb(url);
    }

    @Given("Visualizo la web {string}")
    public void visualizoLaWeb(String url) {
        esperar(5);
        validarPaginaActual(url);
    }

    @Given("Visualizo el titulo {string}")
    public void visualizoElTitulo(String titulo) {
        pageComputers.pageObject().validarTitulo(titulo);
    }

    @And("Visualizo el boton Filter")
    public void visualizoElBotonFilter() {
        pageComputers.pageObject().viewClickBtn("Filter",false);
    }

    @And("Visualizo el boton Add")
    public void visualizoElBotonAdd() {
        pageComputers.pageObject().viewClickBtn("Add",false);
    }

    @And("Visualizo el filtro")
    public void visualizoElFiltro() {
        pageComputers.pageObject().viewFiltro();
    }

    @And("Visualizo la columna {string}")
    public void visualizoLaColumna(String columna) {
        pageComputers.pageObject().validarColumnaTabla(columna);
    }

    @And("Visualizo la tabla Computers")
    public void visualizoLaTablaComputers() {
        pageComputers.pageObject().viewTabla();
    }

    @And("Visualizo la columna {string} con valor {string}")
    public void visualizoLaColumnaConValor(String columna, String valor) {
        pageComputers.pageObject(). validarDatoColumna(columna,valor);
    }
}
