package utils;

import org.apache.http.util.Asserts;

import java.util.List;

import static constants.Constants.*;
import static io.restassured.path.json.JsonPath.from;

public class GenericosMS {
    public static void validarCampo (String campo,String valor){
        String path = pathMS+campo+pathFinal;
        System.out.println(path);
        try {
            if (isList(path)){
                validarListaString(path,valor);
            } else {
                validarCampoOnly(path,valor);
            }
        } catch (Exception e){
            System.exit(1);
        }
    }

    private static void validarCampoOnly (String path, String valor){
        try {
            if(isNumerico(path)){
                validarIgualdadCampoInt(path,valor);
            } else if (isFloat(path)){
                validarIgualdadCampoFloat(path,valor);
            } else {
                validarIgualdadCampoString(path,valor);
            }
        } catch (Exception e){
            System.exit(1);
        }
    }

    /** isNumerico
     * Se inspecciona el resultado en el campo get del MS
     * Se valida si es un resultado numerico
     */
    public static boolean isNumerico(String get){
        boolean resultado;
        try {
            int resultadoInt = from(bodyrest).getInt(get);
            System.out.println("Int: "+resultadoInt);
            resultado = true;
        } catch (Exception e){
            resultado = false;
        }
        return resultado;
    }

    /** isFloat
     * Se inspecciona el resultado en el campo get del MS
     * Se valida si es un resultado punto flotante
     */
    public static boolean isFloat(String get){
        boolean resultado;
        try {
            float resultadoFloat = from(bodyrest).get(get);
            System.out.println("Float: "+resultadoFloat);
            resultado = true;
        } catch (RuntimeException excepcion){
            resultado = false;
        }
        return resultado;
    }

    /** isList
     * Se inspecciona el resultado en el campo get del MS
     * Se valida si es un resultado lista
     */
    private static boolean isList(String path){
        try {
            List<String> listaIds = from(bodyrest).get(path);
            boolean esLista = listaIds != null && listaIds.size() != 1;
            System.out.println("Lista?: "+esLista);
            return esLista;
        } catch (Exception e){
            return false;
        }
    }

    /** validarIgualdadCampoInt
     * Dada la validación isNumerico
     * Se valida si el resultado es igual aL valorEsperado
     */
    private static void validarIgualdadCampoInt(String path, String valor){
        Asserts.check(from(bodyrest).getInt(path) == Integer.parseInt(valor),
                "Ok -> "+path+" : "+valor);
    }

    /** validarIgualdadCampoFloat
     * Dada la validación isFloat
     * Se valida si el resultado es igual a valorEsperado
     */
    private static void validarIgualdadCampoFloat(String path, String valor){
        Asserts.check(from(bodyrest).getFloat(path) == Float.parseFloat(valor),
                "Ok -> "+path+" : "+valor);
    }

    /** validarResultadoCampoString
     * Dado que paso por isNumeros e isFloat sin exito
     * Se valida el resultado en String
     * Se valida si el resultado es igual a valorEsperado
     */
    private static void validarIgualdadCampoString(String path, String valor){
        Asserts.check(from(bodyrest).getString(path).equals(valor),
                "Ok -> "+path+" : "+valor);
    }

    public static void validarStatusCode (String valor){
        Asserts.check(Integer.parseInt(valor) == codigoStatus,
                "Ok -> "+codigoStatus);
    }

    /** validarTituloRespuestaMS
     * Se valida si el tituloBuscado existe en la respuesta del MS
     */
    public static boolean validarTituloRespuestaMS (String tituloBuscado){
        String respuesta = bodyrest.replaceAll(" ","");
        return (respuesta.contains("\"" + tituloBuscado + "\":{") || respuesta.contains("\"" + tituloBuscado + "\":["));
    }

    private static void validarCampoID_String(String path, String valor){
        boolean campoEncontrado = false;
        List<String> listaID = from(bodyrest).get(path);
        for (String campo2 : listaID){
            System.out.println(campo2);
            if (campo2.equals(valor)){
                campoEncontrado = true;
                break;
            }
        }
        Asserts.check(campoEncontrado,"OK -> "+valor);
    }

    private static void validarCampoID_Int(String path, String valor){
        boolean campoEncontrado = false;
        List<Integer> listaID = from(bodyrest).get(path);
        for (int campo2 : listaID){
            System.out.println(campo2);
            if (campo2 == Integer.parseInt(valor)){
                campoEncontrado = true;
                break;
            }
        }
        Asserts.check(campoEncontrado,"OK -> "+valor);
    }

    private static void validarCampoID_Float(String path, String valor){
        boolean campoEncontrado = false;
        List<Float> listaID = from(bodyrest).get(path);
        for (float campo2 : listaID){
            System.out.println(campo2);
            if (campo2 == Integer.parseInt(valor)){
                campoEncontrado = true;
                break;
            }
        }
        Asserts.check(campoEncontrado,"OK -> "+valor);
    }

    private static void validarListaString (String path,String valor){
        try {
            List<String> listaID = from(bodyrest).get(path);
            String primerID = listaID.get(0);
            System.out.println(primerID);
            validarCampoID_String(path,valor);
        } catch (Exception e){
            validarListaInt(path,valor);
        }
    }

    private static void validarListaInt (String path,String valor){
        try {
            List<Integer> listaID = from(bodyrest).get(path);
            int primerID = listaID.get(0);
            System.out.println(primerID);
            validarCampoID_Int(path,valor);
        } catch (Exception e){
            validarListaFloat(path,valor);
        }
    }

    private static void validarListaFloat (String path,String valor){
        try {
            List<Float> listaID = from(bodyrest).get(path);
            Float primerID = listaID.get(0);
            System.out.println(primerID);
            validarCampoID_Float(path,valor);
        } catch (Exception e){
            System.exit(1);
        }
    }
}
