package utils;

import org.apache.http.util.Asserts;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import static constants.Constants.*;

public class web {

    private static int cantidadColumsGrilla1 = 0;
    private static int numColumTabla1 = 0;
    private static int filaBuscadaOp1 = 0;

    public static void levantarWeb (String url){
        try {
            String extension = "";
            String sistemOp = System.getProperty("os.name").toLowerCase();
            if (!sistemOp.contains("mac") && !sistemOp.contains("linux")) {
                extension = ".exe";
            }
            File root = new File("driverNavegador");
            File driverPath = new File(root, "chromedriver"+extension);
            System.setProperty("webdriver.chrome.driver", driverPath.getAbsolutePath());

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.setCapability("acceptSslCerts", true);
            options.setCapability("acceptInsecureCerts", true);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(ChromeOptions.CAPABILITY,options);

            HashMap<String, Object> preferencias = new HashMap<>();
            preferencias.put("profile.default_content_settings.popups", 0);
            preferencias.put("profile.default_content_setting.values.notifications", 2);
            preferencias.put("profile.default_content_setting_values.automatic_downloads", 1);
            preferencias.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
            preferencias.put("download.default_directory",root.getAbsolutePath());
            rutaDescarga =root.getAbsolutePath();
            System.out.println("RUTA DESCARGA: "+ rutaDescarga);
            preferencias.put("download.prompt_for_download", false);

            LoggingPreferences logs = new LoggingPreferences();
            logs.enable("performance", Level.ALL);
            logs.enable("browser", Level.ALL);
            options.setCapability("loggingPrefs", logs);
            options.setExperimentalOption("prefs", preferencias);

            driverWeb = new ChromeDriver(options);
            capabilities.setBrowserName("Chrome");
            driverWeb.manage().window().maximize();
            driverWeb.get(url);
        } catch (Exception e){
            System.exit(1);
        }
    }

    public static void esperar(int segundos) {
        System.out.println("Inicia la espera de " + segundos + " segundos;");
        long start = System.nanoTime();
        long tiempoTranscurrido;

        do {
            long end = System.nanoTime();
            long microseconds = end - start;
            tiempoTranscurrido = TimeUnit.SECONDS.convert(microseconds, TimeUnit.NANOSECONDS);
        } while(tiempoTranscurrido < (long)segundos);

        System.out.println("Fin de la espera de " + segundos + " segundos;");
    }

    public static void validarPaginaActual (String url){
        Asserts.check(driverWeb.getCurrentUrl().equals(url),"OK -> "+url);
    }

    public static boolean encontrarTextoEnLista (List<WebElement> listaNombres,String valorBuscado, boolean presionar){
        boolean respuesta = false;
        for (WebElement element : listaNombres){
            String valorGetText = element.getText();
            String valorValue = element.getText();
            System.out.println(valorGetText);
            System.out.println(valorValue);
            if (valorGetText.equals(valorBuscado) || valorValue.equals(valorBuscado)){
                enmarcarObjeto(element);
                desenmarcarObjeto(element);
                if (presionar){
                    element.click();
                    esperar(2);
                }
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }

    public static void enmarcarObjeto(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor)driverWeb;
            for(int i = 0; i < 3; ++i) {
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 5px solid LimeGreen;");
                esperar(1);
            }
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public static void desenmarcarObjeto(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor)driverWeb;
            for(int i = 0; i < 3; ++i) {
                esperar(1);
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            }
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public static boolean viewObjeto(WebElement objeto, long segundos) {
        try {
            System.out.println("Buscamos el objeto:" + objeto + ", esperamos " + segundos + " segundos, hasta que aparezca.");
            WebDriverWait wait = new WebDriverWait(driverWeb, segundos);
            wait.until(ExpectedConditions.visibilityOf(objeto));
            System.out.println("Se encontró objeto: " + objeto + ", se retorna true.");
            return true;
        } catch (Exception var4) {
            System.out.println("No se encontró objeto: " + objeto + ", se retorna false.");
            return false;
        }
    }

    /** encontrarTituloEnLista
     * Recorre la lista de elementos (Lista titulos de tabla)
     * captura el numero de la posición en la lista (numero de columna)
     */
    public static boolean encontrarColumnaEnLista(List<WebElement> listaNombres, String nombreBuscado) {
        boolean respuesta = false;
        cantidadColumsGrilla1 = listaNombres.size();
        System.out.println(cantidadColumsGrilla1);
        for (int i = 0; i < listaNombres.size(); i++) {
            String texto = listaNombres.get(i).getText().replaceAll("\n", " ");
            texto = texto.replaceAll("/ ", "/");
            if (nombreBuscado.equalsIgnoreCase(texto)) {
                WebElement elementTitulo = listaNombres.get(i);
                numColumTabla1 = i;
                enmarcarObjeto(elementTitulo);
                esperar(2);
                desenmarcarObjeto(elementTitulo);
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }

    /** encontrarUnValorEnLaColumnaX
     * Recorre la grilla en la columna capturada en la funcion encontrarTituloEnLista
     * Recorre la columna hasta encontrar el valorBuscado
     */
    public static void encontrarID_ColumnaX(List<WebElement> listaGrilla, String valorBuscado,boolean buscarID) {
        int fila = 0;
        try {
            boolean encontrado = false;
            if (listaGrilla.size() == 0){
                System.exit(1);
            }
            for (int i=numColumTabla1;i<listaGrilla.size();i=i+cantidadColumsGrilla1) {
                String valorEncontrado = listaGrilla.get(i).getText();
                if (valorEncontrado.equals(valorBuscado)) {
                    encontrado = true;
                    if (buscarID){
                        filaBuscadaOp1 = fila;
                        buscarPorID = true;
                    }
                    enmarcarObjeto(listaGrilla.get(i));
                    esperar(2);
                    desenmarcarObjeto(listaGrilla.get(i));
                    break;
                }
                fila++;
            }
            Asserts.check(encontrado,"OK -> "+valorBuscado);
        } catch (Exception e){
            System.exit(1);
        }
    }

    /** validarValorDadaOpEnLaGrilla
     * Validamos el valor encontrado en la columna capturada en la funcion encontrarTituloEnLista
     * Captura la columna y fila en la posición dado un Numero de Operación
     */
    public static void validarValorDadoID_EnGrilla(List<WebElement> listaGrilla, String valorBuscado) {
        int avanceFila = filaBuscadaOp1 * cantidadColumsGrilla1;
        int filaFinal = numColumTabla1 + avanceFila;
        try {
            WebElement elementFila = listaGrilla.get(filaFinal);
            String valorFila = elementFila.getText();
            enmarcarObjeto(elementFila);
            Asserts.check(valorFila.equals(valorBuscado),"OK -> "+valorBuscado);
            desenmarcarObjeto(elementFila);
        } catch (Exception e){
            System.exit(1);
        }
    }
}
