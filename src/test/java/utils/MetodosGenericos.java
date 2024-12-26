package utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

public class MetodosGenericos {

    private static final String ANSI_RESET = "\u001b[0m";

    public static void imprimirConsolaMsjPositivoVerde (String msj){
        String ANSI_GREEN = "\u001b[32m";
        System.out.println(ANSI_GREEN+msj+ANSI_RESET);
    }

    public static void imprimirConsolaMsjPositivoMorado (String msj){
        String ANSI_GREEN = "\u001b[36m";
        System.out.println(ANSI_GREEN+msj+ANSI_RESET);
    }

    public static void imprimirConsolaMsjNegativo (String msj){
        String ANSI_GREEN = "\u001b[35m";
        System.out.println(ANSI_GREEN+msj+ANSI_RESET);
    }

    public static void esperar (int segundos) {
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

    public static String obtenerPesoArchivo (File rutaArchivo){
        String pesoArchivo = "";
        DecimalFormat df = new DecimalFormat("#.00");
        float longitud = (float) rutaArchivo.length();
        if (longitud > 1.024E9F){
            pesoArchivo = df.format((double) (longitud/1.024E9F))+ " Gb";
        } else if (longitud > 1024000.0F){
            pesoArchivo = df.format((double) (longitud/1024000.0F))+ " Mb";
        } else if (longitud > 1024.0F){
            pesoArchivo = df.format((double) (longitud/1024.0F))+ " Kb";
        } else {
            pesoArchivo = df.format((double) longitud)+ " bytes";
        }
        imprimirConsolaMsjPositivoVerde(rutaArchivo+" : "+pesoArchivo);
        return pesoArchivo;
    }
}
