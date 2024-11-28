package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.page;

import static constants.Constants.pruebaFront;
import static utils.web.*;

public class stepsWeb {
    @Given("Ingreso a la web {string}")
    public void ingresoALaWeb(String url) {
        pruebaFront = true;
        levantarWeb("Edge",url);
    }

    @Given("Visualizo la web {string}")
    public void visualizoLaWeb(String url) {
        esperar(5);
        validarPaginaActual(url);
    }

    @Given("Visualizo el titulo {string}")
    public void visualizoElTitulo(String titulo) {
        page.pageObject().validarTitulo(titulo);
    }

    @And("Visualizo el boton Filter")
    public void visualizoElBotonFilter() {
        page.pageObject().viewClickBtn("Filter",false);
    }

    @And("Visualizo el boton Add")
    public void visualizoElBotonAdd() {
        page.pageObject().viewClickBtn("Add",false);
    }

    @And("Visualizo el filtro")
    public void visualizoElFiltro() {
        page.pageObject().viewFiltro();
    }

    @And("Visualizo la columna {string}")
    public void visualizoLaColumna(String columna) {
        page.pageObject().validarColumnaTabla(columna);
    }

    @When("Presiono la columna {string}")
    public void presionoLaColumna(String link) {
        page.pageObject().presionarLink(link);
    }

    @And("Visualizo la tabla Computers")
    public void visualizoLaTablaComputers() {
        page.pageObject().viewTabla();
    }

    @And("Visualizo la columna {string} con valor {string}")
    public void visualizoLaColumnaConValor(String columna, String valor) {
        page.pageObject().validarDatoColumna(columna,valor);
    }

    @And("Visualizo el link {string}")
    public void visualizoElLink(String link) {
        page.pageObject().validarLink(link);
    }

    @When("Presiono el link {string}")
    public void presionoElLink(String link) {
        page.pageObject().presionarLink(link);
    }
}
