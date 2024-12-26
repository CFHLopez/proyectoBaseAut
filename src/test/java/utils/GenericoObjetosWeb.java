package utils;

import org.apache.http.util.Asserts;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import static utils.MetodosGenericos.esperar;

public class GenericoObjetosWeb {

    private static GenericoLevantarWeb driverManager = new GenericoLevantarWeb();

    public GenericoObjetosWeb(){}

    public static WebDriver getDriverWeb(){
        return driverManager.getWebDriver();
    }

    public static void quitDriverWeb() {
        try {
            driverManager.getWebDriver().quit();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("[quitDriverWeb] Error al eliminar sesión de WebDriver: "+e.getMessage());
        }
    }

    public static void setUpWeb (String navegador, String url){
        driverManager.levantarWeb(navegador,url);
    }

    public static void validarPaginaActual (String url){
        Asserts.check(getDriverWeb().getCurrentUrl().equals(url),"OK -> "+url);
    }

    public static void enmarcarObjeto(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor)getDriverWeb();
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
            JavascriptExecutor js = (JavascriptExecutor)getDriverWeb();
            for(int i = 0; i < 3; ++i) {
                esperar(1);
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
            }
        } catch (Exception e) {
            System.exit(1);
        }
    }

    public static void focusAlElemento (WebElement element){
        ((JavascriptExecutor)getDriverWeb()).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});",element);
    }

    public static boolean verObjeto(WebElement objeto, long segundos) {
        try {
            System.out.println("Buscamos el objeto:" + objeto + ", esperamos " + segundos + " segundos, hasta que aparezca.");
            WebDriverWait wait = new WebDriverWait(getDriverWeb(), segundos);
            wait.until(ExpectedConditions.visibilityOf(objeto));
            System.out.println("Se encontró objeto: " + objeto + ", se retorna true.");
            return true;
        } catch (Exception var4) {
            System.out.println("No se encontró objeto: " + objeto + ", se retorna false.");
            return false;
        }
    }
}
