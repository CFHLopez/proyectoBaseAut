package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.util.Asserts;

import static constants.Constants.*;
import static utils.GenericosMS.*;
import static utils.microServicios.peticionMS;

public class stepsMicroApi {
    private static String urlMS = "";
    @Given("Seteo la URL {string}")
    public void seteoLaURL(String url) {
        urlMS=url;
    }

    @And("Realizo una peticion {string} al servicio")
    public void realizoUnaPeticionAlServicio(String peticion) {
        pathMS = "";
        bodyrest = "";
        pathFinal = "";
        codigoStatus = 0;
        peticionMS(urlMS,peticion);
    }

    @When("Valido el campo {string} con valor {string}")
    public void validoElCampoConValor(String campo, String valor) {
        validarCampo(campo,valor);
    }

    @Then("Valido la respuesta de la peticion")
    public void validoLaRespuestaDeLaPeticion() {
        Asserts.notNull(bodyrest,"Respuesta:\n"+bodyrest);
    }

    @When("Valido el titulo {string}")
    public void validoElTitulo(String titulo) {
        Asserts.check(validarTituloRespuestaMS(titulo),"Ok -> "+titulo);
        pathMS = titulo+".";
        tituloGuardado = titulo;
    }

    private String tituloGuardado = "";
    @And("Valido el subtitulo {string}")
    public void validoElSubtitulo(String subtitulo) {
        Asserts.check(validarTituloRespuestaMS(subtitulo),"Ok -> "+subtitulo);
        pathMS = tituloGuardado+"."+subtitulo+".";
    }

    @And("Valido el campo Codigo Status con valor {string}")
    public void validoElCampoCodigoStatusConValor(String valor) {
        validarStatusCode(valor);
    }
}
