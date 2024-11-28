package constants;

import org.openqa.selenium.WebDriver;

public abstract class Constants {
    private Constants(){
        throw new IllegalStateException("Utility class");
    }

    /** MS **/

    // VARIALES DE RESPUESTA DE PETICION
    public static String bodyrest = "";
    public static int codigoStatus = 0;

    // VARIABLES DE CAPTURA DE CAMPOS DEL BODYREST
    public static String pathMS = "";
    public static String pathFinal = "";

    /** WEB **/
    public static WebDriver driverWeb;
    public static String rutaDescarga = "";
    public static boolean buscarPorID = false;
    public static boolean pruebaFront = false;

    /** OTROS **/
}
