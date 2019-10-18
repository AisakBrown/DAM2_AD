/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isaac
 */
public class MetodosQuijote {

    private File file;
    private AccesoQuijote aq;
    private String[] listaSignos;

    public MetodosQuijote() {//Iniciamos la clase de acceso a los ficheros en el constructor utilizando el metodo buscar Quijote
        file = new File(buscarQuijote("F:\\Program Files (x86)").getAbsolutePath());
        aq = new AccesoQuijote(file);

    }
    /**
     * Metodo simple que nos cuenta las lineas de un fichero
     * @return numero de lineas
     */
    public int contadorLineas() {
        int contador = 0;
        String linea;
        aq.abrirFicherosLectura(file);
        while ((linea = aq.leerLineaFichero()) != null) {
            contador++;
        }
        aq.cerrarFicherosLectura();
        return contador;
    }

    /**
     * Metodo que crea un hashmap con todas las palabras y cuenta el numero de veces que aparecen en el texto
     * @param File archivoPalabras
     * @return HashMap con las palabras
     */
    public HashMap listaPalabras(File archivoPalabras) {
        HashMap<String, Integer> listaPalabras = new HashMap<>();
        String[] listaSignos = {"\\,", "\\.", "\\;", "\\:", "\\?", "\\¿", "\\!", "\\¡"}; //Creamos u array con los signos para limpiar el texto
        aq.abrirFicherosLectura(archivoPalabras);
        String lineaLeida;
        String palabraLeida;
        while ((lineaLeida = aq.leerLineaFichero()) != null) {//Leemos linea a linea
            for (String signo : listaSignos) {
                lineaLeida = lineaLeida.replaceAll(signo, "");//Limpiamos cada linea
            }
            StringTokenizer tokens = new StringTokenizer(lineaLeida, " ");
            while (tokens.hasMoreTokens()) {//De cada linea leemos cada palabra separando con espacios
                if (listaPalabras.containsKey(palabraLeida = tokens.nextToken())) {//Si la palabra ya existe aumentamos el numero
                    int totalPalabras = (int) listaPalabras.get(palabraLeida);
                    totalPalabras++;
                    listaPalabras.replace(palabraLeida, totalPalabras);
                } else { //Si la palabra no existe la metemos en el hashmap con valor 1
                    listaPalabras.put(palabraLeida, 1);
                }
            }
        }
        aq.cerrarFicherosLectura();
        return listaPalabras;
    }

    /**
     * Aprovecho el metodo que me mete las palabras en un hashmap para buscar lo que se mete por parametro
     * @param La palabra que queremos buscar
     * @return el numero de veces que aparece la palabra en el texto
     */
    public int contadorPalabraQuijote(String palabraABuscar) {
        return (int) listaPalabras(file).get(palabraABuscar);
    }

    /**
     * 
     * @param disco donde va a buscar el quijote
     * @return El archivo donde esta el quijote
     */
    public File buscarQuijote(String disco) {
        File file = new File(disco);
        File[] listaDirectorios = file.listFiles();

        for (File directorio : listaDirectorios) {
            if (directorio.isDirectory()) {//Si es un directorio busco de forma recursiva
                file = new File(buscarQuijote(directorio.getAbsolutePath()).getAbsolutePath());
                if (file.getName().equalsIgnoreCase("quijote.txt")) {//Este if es para cuando me encuentra el archivo en una sub-busqueda
                    return file;
                }

            } else {
                if (directorio.getName().equalsIgnoreCase("Quijote.txt")) {
                    file = new File(directorio.getAbsolutePath());
                }
            }
        }
        return file;
    }

    public int contarLetras() {
        int contadorTotalLetras = 0;
        try {
            FileInputStream fis = new FileInputStream(file);
            while (fis.available() != 0) {
                fis.read();
                contadorTotalLetras++;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MetodosQuijote.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MetodosQuijote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contadorTotalLetras;
    }

    /**
     * Un metodo que nos crea un archivo con las lineas invertidas
     * @param ruta
     * @return el archvio con las lineas invertidas
     */
    public File escribirLineasAlReves(String ruta) {
        aq.abrirFicherosLectura(file);

        String linea;
        File ficheroEscritoAlReves = new File(ruta);
        aq.abrirFicherosEscritura(ficheroEscritoAlReves);
        while ((linea = aq.leerLineaFichero()) != null) {//Uso la clase StreamBuilder para invertir las palabras de forma simple
            StringBuilder invierteCadenas = new StringBuilder(linea);
            String cadenaInvertida = invierteCadenas.reverse().toString();
            aq.escribirLinea(cadenaInvertida);//escribimos la linea invertida en el nuevo fichero
        }
        aq.cerrarFicherosLectura();
        aq.cerrarFicherosEscritura();
        return ficheroEscritoAlReves;
    }
    
    /**
     * Metodo que nos crea un fichero por cada capitulo y nos devuelve un arrayList con cada capitulo
     * @return ArrayList con el archivo de cada capitulo
     */
    public ArrayList dividirQuijoteCapitulos(){
        aq.abrirFicherosLectura(file);
        String linea;
        int capitulo = 1;
        File nuevoCapitulo = null;
        ArrayList listaCapitulos = new ArrayList();
        boolean capituloCreado = false;
        while ((linea = aq.leerLineaFichero()) != null) {
            if(linea.contains("Capítulo")){//Leo cada linea y miro si contiene capitulo
                if(nuevoCapitulo != null){//Este if es para que no me salte nullPointer de la arraylist
                    listaCapitulos.add(nuevoCapitulo);
                }
                nuevoCapitulo = new File(file.getAbsolutePath() + "\\..\\" + capitulo + ".txt");
                capitulo++;
                capituloCreado = true;
                
                aq.abrirFicherosEscritura(nuevoCapitulo);
            }
            else if(capituloCreado){//este if es para que no me salte nullpointer ya que empieza por lineas que no son un capitulo
                aq.escribirLinea(linea);
            }
        }
        aq.cerrarFicherosEscritura();
        return listaCapitulos;
    }
    
    /**
     * Este metodo crea un hashmap con todas las palabras de los ficheros que estan en la misma carpeta
     * @return un hashmap con todas las palabras
     */
    public HashMap listarPalabrasVariosFicheros(){
        File directorioQuijote = new File(file.getAbsolutePath() + "\\..");
        File[] listaFicherosQuijote = directorioQuijote.listFiles();
        HashMap listaPalabrasDirectorios = new HashMap();
        for (File file1 : listaFicherosQuijote) {//cojo cada fichero del directorio, leo las palabras y las meto en el hashmap
            listaPalabrasDirectorios.putAll(listaPalabras(file1));
        }
        return listaPalabrasDirectorios;
    }
}
