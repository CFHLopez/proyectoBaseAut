package pages;

import org.apache.http.util.Asserts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static constants.Constants.buscarPorID;
import static constants.Constants.driverWeb;
import static utils.web.*;

public class pageWeb implements page {

    public pageWeb() {
        PageFactory.initElements(driverWeb, this);
    }

    /** ELEMENTOS WEB **/
    @FindAll({
            @FindBy(xpath = "//section//h1"),
            @FindBy(xpath = "//a[@class='fill']"),
            @FindBy(xpath = "//h1"),
            @FindBy(xpath = "//h2"),
            @FindBy(xpath = "//h3")
    })
    private List<WebElement> titulos;

    @FindBy(xpath = "//a[@id='add']")
    private WebElement btnAdd;

    @FindBy(xpath = "//input[@class='btn primary']")
    private WebElement btnFilter;

    @FindBy(xpath = "//input[@id='searchbox']")
    private WebElement filtroBuscar;

    @FindBy(xpath = "//table[contains(@class,'computers')]")
    private WebElement tablaComputers;

    @FindAll({
            @FindBy(xpath = "//table//th"),
            @FindBy(xpath = "//li//a")
    })
    private List<WebElement> listaColumnas;

    @FindBy(xpath = "//table//td")
    private List<WebElement> listaGrilla;

    @FindBy(xpath = "//li//a")
    private List<WebElement> listaLinks;

    /** FUNCIONES **/

    public void validarTitulo (String titulo){
        Asserts.check(encontrarTextoEnLista(titulos, titulo, false),"OK -> "+titulo);
    }

    public void viewClickBtn (String nombreBtn,boolean presionar){
        WebElement btn = btnFilter;
        if (nombreBtn.equalsIgnoreCase("add")){
            btn = btnAdd;
        }
        Asserts.check(viewObjeto(btn,2),"OK -> "+nombreBtn);
        enmarcarObjeto(btn);
        System.out.println("Enmarcar");
        desenmarcarObjeto(btn);
        if (presionar){
            btn.click();
        }
    }

    public void viewFiltro (){
        Asserts.check(viewObjeto(filtroBuscar,2),"OK -> Filtro");
        enmarcarObjeto(filtroBuscar);
        esperar(2);
        desenmarcarObjeto(filtroBuscar);
    }

    public void viewTabla (){
        Asserts.check(viewObjeto(tablaComputers,2),"OK -> Tabla");
        enmarcarObjeto(tablaComputers);
        esperar(2);
        desenmarcarObjeto(tablaComputers);
    }

    public void validarColumnaTabla (String columna){
        Asserts.check(encontrarColumnaEnLista(listaColumnas,columna),"OK -> "+columna);
    }

    public void validarDatoColumna (String columna, String valor){
        validarColumnaTabla(columna);
        if (!buscarPorID || columna.equals("Computer name")){
            buscarPorID = false;
            encontrarID_ColumnaX(listaGrilla,valor,columna.equals("Computer name"));
        } else {
            validarValorDadoID_EnGrilla(listaGrilla,valor);
        }
    }

    public void validarLink (String nombreLink){
        Asserts.check(encontrarColumnaEnLista(listaLinks,nombreLink),"OK -> "+nombreLink);
    }

    public void presionarLink (String nombreLink){
        for (WebElement link : listaLinks){
            String texto = link.getText();
            if (texto.equals(nombreLink)){
                link.click();
                esperar(5);
            }
        }
    }
}
