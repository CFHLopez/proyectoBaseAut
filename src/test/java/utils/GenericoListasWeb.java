package utils;

import org.apache.http.util.Asserts;
import org.openqa.selenium.WebElement;

import java.util.List;

import static constants.Constants.*;
import static utils.GenericoObjetosWeb.*;
import static utils.MetodosGenericos.esperar;

public class GenericoListasWeb {

    private static int cantidadColumsGrilla1 = 0;
    private static int numColumTabla1 = 0;

    /** encontrarTextoEnLista
     * Recorre la lista de elementos Web
     * Valida si encuentra el valor "valorBuscado"
     * Retorna
     * true -> si encuentra el valor
     * false -> si no hay coincidencia
     */
    public static boolean encontrarTextoEnLista (List<WebElement> listaNombres, String valorBuscado, boolean presionar){
        boolean respuesta = false;
        for (WebElement element : listaNombres){
            String valorGetText = element.getText();
            String valorValue = element.getText();
            System.out.println(valorGetText);
            System.out.println(valorValue);
            if (valorGetText.equals(valorBuscado) || valorValue.equals(valorBuscado)){
                focusAlElemento(element);
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
                focusAlElemento(elementTitulo);
                enmarcarObjeto(elementTitulo);
                esperar(2);
                desenmarcarObjeto(elementTitulo);
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }

    /** encontrarID_ColumnaX
     * Recorre la grilla en la columna capturada en la funcion encontrarTituloEnLista
     * Recorre la columna hasta encontrar el valorBuscado
     */
    public static void encontrarID_ColumnaX(List<WebElement> listaGrilla, String valorBuscado,boolean buscarID) {
        int fila = 0;
        try {
            boolean encontrado = false;
            if (listaGrilla.isEmpty()){
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
                    focusAlElemento(listaGrilla.get(i));
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

    /** validarValorDadoID_EnGrilla
     * Validamos el valor encontrado en la columna capturada en la funcion encontrarTituloEnLista
     * Captura la columna y fila en la posición dado un Numero de Operación
     */
    public static void validarValorDadoID_EnGrilla(List<WebElement> listaGrilla, String valorBuscado) {
        int avanceFila = filaBuscadaOp1 * cantidadColumsGrilla1;
        int filaFinal = numColumTabla1 + avanceFila;
        try {
            WebElement elementFila = listaGrilla.get(filaFinal);
            String valorFila = elementFila.getText();
            focusAlElemento(elementFila);
            enmarcarObjeto(elementFila);
            Asserts.check(valorFila.equals(valorBuscado),"OK -> "+valorBuscado);
            desenmarcarObjeto(elementFila);
        } catch (Exception e){
            System.exit(1);
        }
    }
}
