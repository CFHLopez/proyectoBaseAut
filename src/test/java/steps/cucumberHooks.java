package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static constants.Constants.*;
import static utils.GenericoObjetosWeb.quitDriverWeb;
import static utils.MetodosGenericos.imprimirConsolaMsjPositivoMorado;

public class cucumberHooks {
    @Before
    public void beforeScenario() {
        imprimirConsolaMsjPositivoMorado("Inicia Prueba");
    }

    @After
    public void afterScenario() {
        pathMS = "";
        pathFinal = "";
        bodyrest = "";
        codigoStatus = 0;
        buscarPorID = false;
        imprimirConsolaMsjPositivoMorado("Prueba finalizada");
        if (pruebaFront){
            quitDriverWeb();
        }
    }
}
