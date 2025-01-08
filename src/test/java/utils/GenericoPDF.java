package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static constants.Constants.*;
import static utils.MetodosGenericos.*;
import static utils.MetodosGenericos.imprimirConsolaMsjPositivoMorado;

public class GenericoPDF {
    public GenericoPDF() {}

    public static void lecturaPDF (){
        try (PDDocument document = PDDocument.load(archivoDescargado)){
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String contenido = pdfTextStripper.getText(document);
            imprimirConsolaMsjPositivoVerde(contenido);
            bodyrest = contenido;
        } catch (Exception e){
            imprimirConsolaMsjNegativo(e.getMessage());
        }
    }
}
