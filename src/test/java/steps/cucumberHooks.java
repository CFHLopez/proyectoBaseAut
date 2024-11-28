package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static constants.Constants.*;

public class cucumberHooks {
    @Before
    public void beforeScenario() {
        System.out.println("Inicia Prueba");
    }

    @After
    public void afterScenario() {
        pathMS = "";
        pathFinal = "";
        bodyrest = "";
        codigoStatus = 0;
        buscarPorID = false;
        System.out.println("Prueba finalizada");
        if (pruebaFront){
            driverWeb.quit();
        }
    }
}
