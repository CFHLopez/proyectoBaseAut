package utils;

import org.apache.http.util.Asserts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import static constants.Constants.*;
import static utils.GenericoObjetosWeb.*;

public class GenericoLevantarWeb {

    private static final File root = new File("driverNavegador");

    private WebDriver webDriver;

    protected WebDriver getWebDriver(){
        return this.webDriver;
    }

    private static void setearDriver(String navegador){
        switch (navegador){
            case "Chrome":
            case "ChromeDriverless":
                webDriverNoRemoto("chromedriver","chrome");
                break;
            case "Edge":
                webDriverNoRemoto("msedgedriver","edge");
                break;
        }
    }

    private static void webDriverNoRemoto(String nombreDriver, String nombreWeb){
        String extension = "";
        String sistemOp = System.getProperty("os.name").toLowerCase();
        if (!sistemOp.contains("mac") && !sistemOp.contains("linux")) {
            extension = ".exe";
        }
        File driverPath = new File(root, nombreDriver+extension);
        System.setProperty("webdriver."+nombreWeb+".driver", driverPath.getAbsolutePath());
    }

    protected void levantarWeb (String navegador, String url){
        try {
            setearDriver(navegador);
            Map<String, Object> preferencias = new HashMap<>();
            DesiredCapabilities capabilities = new DesiredCapabilities();

            preferencias.put("profile.default_content_settings.popups", 0);
            preferencias.put("profile.default_content_setting.values.notifications", 2);
            preferencias.put("profile.default_content_setting_values.automatic_downloads", 1);
            preferencias.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
            preferencias.put("download.default_directory", root.getAbsolutePath());
            preferencias.put("download.prompt_for_download", false);

            LoggingPreferences logs = new LoggingPreferences();
            logs.enable("performance", Level.ALL);
            logs.enable("browser", Level.ALL);

            switch (navegador){
                case "Chrome":
                case "ChromeDriverless":
                    ChromeOptions opChrome = new ChromeOptions();
                    opChrome.addArguments("--allow-running-insecure-content");
                    // HABILITAR WEB NO SEGURA
                    opChrome.addArguments("--guest");
                    opChrome.setCapability("loggingPrefs", logs);
                    opChrome.setExperimentalOption("prefs", preferencias);
                    capabilities.setCapability(ChromeOptions.CAPABILITY,opChrome);
                    webDriver = new ChromeDriver(opChrome);
                    capabilities.setBrowserName("Chrome");
                    break;
                case "Edge":
                    EdgeOptions opEdge = new EdgeOptions();
                    // HABILITAR WEB NO SEGURA
                    opEdge.setCapability("ms:edgeOptions",new HashMap<String,Object>(){
                        {
                            put("args", Arrays.asList("--ignore-certificate-errors","--allow-running-insecure-content"));
                        }
                    });
                    webDriver = new EdgeDriver(opEdge);
                    capabilities.setBrowserName("Microsoft Edge");
                    break;
                default:
                    System.exit(1);
            }

            capabilities.setBrowserName(navegador);
            webDriver.manage().window().maximize();
            webDriver.get(url);
        } catch (Exception e){
            System.exit(1);
        }
    }
}
