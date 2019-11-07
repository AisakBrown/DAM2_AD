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
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author isaac
 */
public class MetodosManejoUSB {

    private File directorioBackUp;
    private File archivoACopiar;
    private VentanaPrincipal ventanaPrincipal;

    public File getArchivoACopiar() {
        return archivoACopiar;
    }

    public MetodosManejoUSB(String rutaArchivoCopiar, String rutaArchivoDestino) throws IOException {
        archivoACopiar = new File(rutaArchivoCopiar);
        directorioBackUp = new File(rutaArchivoDestino + "\\BackUp");
        if (!directorioBackUp.exists()) {
            Files.createDirectory(directorioBackUp.toPath());
        }
        directorioBackUp = new File(directorioBackUp.getAbsolutePath() + archivoACopiar.getAbsolutePath().substring(archivoACopiar.getAbsolutePath().lastIndexOf("\\")));
    }

    public int borrarFicheros(ArrayList<File> lista) {
        int totalFicherosBorrados = 0;
        for (java.util.Iterator<java.io.File> iterator = lista.iterator(); iterator.hasNext();) {
            File file = iterator.next();
            if (file.delete()) {
                totalFicherosBorrados++;
            }
        }
        return totalFicherosBorrados;
    }

    public long espacioTotalDispositivo(File file) {
        long espacioDisponible = file.getFreeSpace();
        return espacioDisponible / 1048576;
    }

    public ArrayList<File> buscarFicherosDuplicados(File directorioEnElQueBuscar) {
        ArrayList<File> archivosDuplicados = new ArrayList<>();
        File[] contenidoDirectorioBuscar = directorioEnElQueBuscar.listFiles();

        for (File file : contenidoDirectorioBuscar) {
            if (compararArchivoConDirectorios(file, directorioEnElQueBuscar)) {
                archivosDuplicados.add(file);
            } else if (file.isDirectory()) {
                ArrayList<File> listaRecursiva = buscarFicherosDuplicados(file);
                if (listaRecursiva != null) {
                    for (File file1 : listaRecursiva) {
                        archivosDuplicados.add(file1);
                    }
                }

            }
        }
        return archivosDuplicados;
    }

    public boolean compararArchivoConDirectorios(File archivoComparar, File directorioBuscar) {
        File[] archivosDirectorio = directorioBuscar.listFiles();
        boolean duplicado = false;

        if (directorioBuscar.isDirectory()) {
            for (File file : archivosDirectorio) {
                if (duplicado == true) {
                } else {
                    if (file.getName().equalsIgnoreCase(archivoComparar.getName()) && file.getTotalSpace() == archivoComparar.getTotalSpace() && !file.getAbsolutePath().equalsIgnoreCase(archivoComparar.getAbsolutePath())) {
                        duplicado = true;
                    } else {
                        duplicado = compararArchivoConDirectorios(archivoComparar, file);
                    }
                }
            }
        } else {
            if (directorioBuscar.getName().equalsIgnoreCase(archivoComparar.getName()) && directorioBuscar.getTotalSpace() == archivoComparar.getTotalSpace() && !directorioBuscar.getAbsolutePath().equalsIgnoreCase(archivoComparar.getAbsolutePath())) {
                duplicado = true;
            }
        }
        return duplicado;
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
        ventanaPrincipal = new VentanaPrincipal();
        for (File file : listaFicheros) {
            File archivoEnBackUp = new File(arreglarRutaParaBackUp(file));
            if (file.isDirectory()) {
                Files.createDirectory(archivoEnBackUp.toPath());
            } else {
                ventanaPrincipal.mostrarArchivos(archivoEnBackUp.getAbsolutePath());
                Files.copy(file.toPath(), archivoEnBackUp.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            updateProgress(progreso, file.getAbsolutePath());
            progreso++;
        }
        return totalArchivosCopiados;
    }

    public String arreglarRutaParaBackUp(File finalRuta) {
        String rutaArreglada = directorioBackUp.getAbsolutePath() + finalRuta.getAbsolutePath().substring(archivoACopiar.getAbsolutePath().indexOf(archivoACopiar.getName()) + archivoACopiar.getName().length());
        return rutaArreglada;
    }

    private void updateProgress(int valorBarraProgreso, String fichero) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ventanaPrincipal.mostrarArchivos(fichero);
                ventanaPrincipal.aumentarProgreso(valorBarraProgreso);
                ventanaPrincipal.repaint();
                System.out.println(valorBarraProgreso);
                
            }
        });
    }
}
