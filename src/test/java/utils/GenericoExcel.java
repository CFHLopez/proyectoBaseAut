package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static constants.Constants.*;
import static utils.MetodosGenericos.*;

public class GenericoExcel {
    public GenericoExcel (){}

    private static FileInputStream archivoExcel;
    private static XSSFWorkbook libroExcel;
    private static XSSFSheet hojaExcel;
    private static FormulaEvaluator evaluador;
    private static int numeroColumna = 0;
    private static String nombreHojaActual;

    /**
     * abrirExcelEnPagina
     * Se obtiene la ruta del archivo
     * Se carga la informacion de la hoja señalada
     */
    private static void abrirExcelEnPagina (String nombreHoja){
        try {
            libroExcel = new XSSFWorkbook(archivoExcel);
            hojaExcel = libroExcel.getSheetAt(retornarNumHoja(nombreHoja));
            evaluador = libroExcel.getCreationHelper().createFormulaEvaluator();
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
        }
    }

    /** retornarNumHoja
     * Valida que el nombre de hoja este presente en el excel
     * retorna el numero de la hoja buscada
     */
    private static int retornarNumHoja(String nombreHoja){
        int numHoja = 0;
        try {
            for (int i=0;i<libroExcel.getNumberOfSheets();i++){
                XSSFSheet hojaExcel = libroExcel.getSheetAt(i);
                String hojaObtenida = hojaExcel.getSheetName();
                imprimirConsolaMsjPositivoVerde(i+".- "+hojaObtenida);
                if (hojaObtenida.trim().equals(nombreHoja)){
                    numHoja = i;
                    break;
                }
            }
            return numHoja;
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
            return numHoja;
        }
    }

    /** cerrarExcel
     * Cierra el excel abierto
     */
    private static void cerrarExcel(){
        try {
            if (libroExcel != null){
                libroExcel.close();
            }
            if (archivoExcel != null){
                archivoExcel.close();
            }
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
        }
    }

    /** retornarValorCelda
     * Dada la celda capturada
     * Se retorna el valor capturado seteado a String
     */
    private static String retornarValorCelda (Cell celda){
        if (celda == null){
            return "";
        }
        switch (celda.getCellType()){
            case STRING:
                return celda.getStringCellValue().trim();
            case NUMERIC:
                return retornarValorCeldaNumeric(celda);
            case BOOLEAN:
                return String.valueOf(celda.getBooleanCellValue());
            case FORMULA:
                return celda.getCellFormula();
            case BLANK:
                return "";
            default:
                return " ";
        }
    }

    private static String retornarValorCeldaNumeric (Cell celda){
        CellValue valorCelda = evaluador.evaluate(celda);
        if (DateUtil.isCellDateFormatted(celda)){
            CellStyle stiloCelda = celda.getCellStyle();
            String formatoFechaCelda = stiloCelda.getDataFormatString();
            String javaFormatoFecha = retornarFormatoFechaExcel(formatoFechaCelda);
            Date date = celda.getDateCellValue();
            SimpleDateFormat sdf = new SimpleDateFormat(javaFormatoFecha);
            return sdf.format(date);
        } else {
            int celdaInt = (int) valorCelda.getNumberValue();
            return Integer.toString(celdaInt);
        }
    }

    private static String retornarFormatoFechaExcel (String excelFormat){
        imprimirConsolaMsjPositivoMorado("FORMATO: "+excelFormat+".");
        if (excelFormat.contains("m/d/yy h:mm")){
            return "dd-MM-yyyy H:mm";
        }
        if (excelFormat.contains("m/d/yy hh:mm")){
            return "dd-MM-yyyy H:mm";
        }
        if (excelFormat.contains("yyyy-MM-dd") || excelFormat.contains("yyyy/MM/dd")){
            return "yyyy-MM-dd";
        }
        if (excelFormat.contains("mmm dd, yyyy")){
            return "MMM dd, yyyyy";
        }
        if (excelFormat.contains("h:mm:ss")){
            return "H:mm:ss";
        }
        return "dd-MM-yyyy";
    }

    private static void buscarIDColumna (String valorBuscado){
        try {
            for (int i=1;i<hojaExcel.getLastRowNum()+1;i++){
                String valorEncontrado = retornarValorCelda(hojaExcel.getRow(i).getCell(numeroColumna));
                imprimirConsolaMsjPositivoVerde(valorEncontrado);
                if (valorEncontrado.equals(valorBuscado)){
                    buscarPorID = true;
                    filaBuscadaOp1 = i;
                    imprimirConsolaMsjPositivoVerde("VALOR ENCONTRADO");
                    break;
                } else if (i == hojaExcel.getLastRowNum()){
                    System.exit(1);
                }
            }
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
        } finally {
            cerrarExcel();
        }
    }

    private static void buscarEnColumnaPorID(String valorBuscado){
        try {
            String valorEncontrado = retornarValorCelda(hojaExcel.getRow(filaBuscadaOp1).getCell(numeroColumna));
            imprimirConsolaMsjPositivoMorado(valorEncontrado+".");
            imprimirConsolaMsjPositivoMorado(valorBuscado+".");
            Assert.assertEquals(valorBuscado.trim(),valorEncontrado);
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
        } finally {
            cerrarExcel();
        }
    }

    public static void validarArchivoExcel (String rutaArchivo){
        try {
            File archivo = new File(rutaArchivo);
            if (archivo.exists() && archivo.isFile() && !obtenerPesoArchivo(archivo).isEmpty()){
                archivoExcel = new FileInputStream(rutaArchivo);
                imprimirConsolaMsjPositivoVerde("Archivo Encontrado");
            } else {
                imprimirConsolaMsjNegativo("Archivo NO existe");
                System.exit(1);
            }
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
        }
    }

    public static void validarNombreHojaExcel (String hojaBuscada){
        try {
            abrirExcelEnPagina("");
            for (int i=0;i<libroExcel.getNumberOfSheets();i++){
                Sheet ventana = libroExcel.getSheetAt(i);
                String hojaObtenida = ventana.getSheetName();
                if (hojaObtenida.equals(hojaBuscada)){
                    nombreHojaActual = hojaBuscada;
                    Assert.assertEquals(hojaObtenida,hojaBuscada);
                    break;
                } else if (i == libroExcel.getNumberOfSheets()-1){
                    System.exit(1);
                }
            }
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
        } finally {
            cerrarExcel();
        }
    }

    public static void validarNombreColumnaEnExcel (String nombreColumna){
        String valorEncontrado;
        boolean campoEncontrado = false;
        try {
            abrirExcelEnPagina(nombreHojaActual);
            for (int i=0;i<hojaExcel.getLastRowNum();i++){
                Row filaExcel = hojaExcel.getRow(i);
                for (int j=0;j<filaExcel.getLastCellNum();j++){
                    valorEncontrado = retornarValorCelda(filaExcel.getCell(j));
                    imprimirConsolaMsjPositivoVerde((j+1)+".- "+valorEncontrado);
                    if (nombreColumna.equals(valorEncontrado)){
                        campoEncontrado = true;
                        numeroColumna = j;
                        break;
                    }
                }
                if (campoEncontrado){
                    break;
                } else if (i == hojaExcel.getLastRowNum()-1){
                    imprimirConsolaMsjNegativo("Columna NO encontrada");
                    System.exit(1);
                }
            }
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
        } finally {
            cerrarExcel();
        }
    }

    public static void validarCampoColumnaFila (String nombreColumna, String valorFila){
        validarNombreColumnaEnExcel(nombreColumna);
        if (buscarPorID){
            buscarEnColumnaPorID(valorFila);
        } else {
            buscarIDColumna(valorFila);
        }
    }
}
