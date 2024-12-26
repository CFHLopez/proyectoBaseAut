package pages.ms;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static constants.Constants.bodyrest;
import static constants.Constants.codigoStatus;
import static utils.MetodosGenericos.imprimirConsolaMsjNegativo;
import static utils.MetodosGenericos.imprimirConsolaMsjPositivoMorado;

public class microServicios {

    private static Response respuestaMS;
    public static void peticionMS (String urlMS, String peticion){
        switch (peticion.toLowerCase()){
            case "post":
                peticionPOST(urlMS);
                break;
            case "get":
                peticionGET(urlMS);
                break;
            case "put":
                peticionPUT(urlMS);
                break;
            case "delete":
                peticionDELETE(urlMS);
                break;
            default:
                imprimirConsolaMsjNegativo("Peticion NO valida");
        }
    }

    private static void obtenerRespuesta(){
        codigoStatus = respuestaMS.getStatusCode();
        bodyrest = respuestaMS.asString();
        imprimirConsolaMsjPositivoMorado(bodyrest);
    }

    private static void peticionGET(String urlMS){
        try {
            respuestaMS = RestAssured
                    .given()
                    .body("")
                    .get(urlMS)
                    .then()
                    .extract()
                    .response();
            obtenerRespuesta();
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
            System.exit(1);
        }
    }

    private static void peticionPOST(String urlMS){
        try {
            respuestaMS = RestAssured
                    .given()
                    .body("")
                    .post(urlMS)
                    .then()
                    .extract()
                    .response();
            obtenerRespuesta();
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
            System.exit(1);
        }
    }

    private static void peticionDELETE(String urlMS){
        try {
            respuestaMS = RestAssured
                    .given()
                    .body("")
                    .delete(urlMS)
                    .then()
                    .extract()
                    .response();
            obtenerRespuesta();
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
            System.exit(1);
        }
    }

    private static void peticionPUT(String urlMS){
        try {
            respuestaMS = RestAssured
                    .given()
                    .body("")
                    .put(urlMS)
                    .then()
                    .extract()
                    .response();
            obtenerRespuesta();
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
            System.exit(1);
        }
    }
}
