/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.VentanaPrincipal;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

/**
 *
 * @author isaac
 */
public class BackUp {
    
    private File directorioBackUp;
    private File archivoACopiar;
    
    public BackUp(String rutaArchivoCopiar, String rutaArchivoDestino) throws IOException {
        archivoACopiar = new File(rutaArchivoCopiar);
        directorioBackUp = new File(rutaArchivoDestino + "\\BackUp");
        if (!directorioBackUp.exists()) {
            Files.createDirectory(directorioBackUp.toPath());
        }
        directorioBackUp = new File(directorioBackUp.getAbsolutePath() + archivoACopiar.getAbsolutePath().substring(archivoACopiar.getAbsolutePath().lastIndexOf("\\")));
    }
    
    public ArrayList<File> obtenerArchivosFormaRecursiva(String ruta) throws IOException {
        File directorio = new File(ruta);
        File[] lista = directorio.listFiles();
        ArrayList<File> listaArchivosCopiados = new ArrayList<>();
        if (!directorioBackUp.exists()) {
            Files.createDirectory(directorioBackUp.toPath());
        }

        for (File file : lista) {
            String rutaArreglada = arreglarRutaParaBackUp(file);
            File archivoEnBackUp = new File(rutaArreglada);

            if (!archivoEnBackUp.exists()) {
                if (file.isDirectory()) {
                    listaArchivosCopiados.add(file);
                    ArrayList<File> ficherosRecursivos = obtenerArchivosFormaRecursiva(file.getAbsolutePath());
                    for (File fileRecursivo : ficherosRecursivos) {
                        listaArchivosCopiados.add(fileRecursivo);
                    }
                } else {
                    listaArchivosCopiados.add(file);
                }
            }
        }
        return listaArchivosCopiados;
    }
    
    public int copiarArchivosDesdeLista(ArrayList<File> listaFicheros) throws IOException {
        int totalArchivosCopiados = listaFicheros.size();
        int progreso = 1;
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        for (File file : listaFicheros) {
            File archivoEnBackUp = new File(arreglarRutaParaBackUp(file));
            if (file.isDirectory()) {
                Files.createDirectory(archivoEnBackUp.toPath());
            } else {
                ventanaPrincipal.mostrarArchivos(archivoEnBackUp.getAbsolutePath());
                Files.copy(file.toPath(), archivoEnBackUp.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            ventanaPrincipal.aumentarProgreso(progreso);
            progreso++;
        }
        return totalArchivosCopiados;
    }
    
    public String arreglarRutaParaBackUp(File finalRuta) {
        String rutaArreglada = directorioBackUp.getAbsolutePath() + finalRuta.getAbsolutePath().substring(archivoACopiar.getAbsolutePath().indexOf(archivoACopiar.getName()) + archivoACopiar.getName().length());
        return rutaArreglada;
    }
}
