package pages.web;

import org.apache.http.util.Asserts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GenericoObjetosWeb;

import java.util.List;

import static constants.Constants.buscarPorID;
import static utils.GenericoListasWeb.*;
import static utils.GenericoObjetosWeb.*;
import static utils.MetodosGenericos.esperar;

public class pageComputersWeb implements pageComputers{

    public pageComputersWeb() {
        PageFactory.initElements(GenericoObjetosWeb.getDriverWeb(), this);
    }

    /** ELEMENTOS WEB **/
    @FindAll({
            @FindBy(xpath = "//section//h1"),
            @FindBy(xpath = "//a[@class='fill']")
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

    @FindBy(xpath = "//table//th")
    private List<WebElement> listaColumnas;

    @FindBy(xpath = "//table//td")
    private List<WebElement> listaGrilla;

    /** FUNCIONES **/

    public void validarTitulo (String titulo){
        Asserts.check(encontrarTextoEnLista(titulos, titulo, false),"OK -> "+titulo);
    }

    public void viewClickBtn (String nombreBtn,boolean presionar){
        WebElement btn = btnFilter;
        if (nombreBtn.equalsIgnoreCase("add")){
            btn = btnAdd;
        }
        Asserts.check(verObjeto(btn,2),"OK -> "+nombreBtn);
        enmarcarObjeto(btn);
        System.out.println("Enmarcar");
        desenmarcarObjeto(btn);
        if (presionar){
            btn.click();
        }
    }

    public void viewFiltro (){
        Asserts.check(verObjeto(filtroBuscar,2),"OK -> Filtro");
        enmarcarObjeto(filtroBuscar);
        esperar(2);
        desenmarcarObjeto(filtroBuscar);
    }

    public void viewTabla (){
        Asserts.check(verObjeto(tablaComputers,2),"OK -> Tabla");
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
}
