/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isaac
 */
public class AccesoQuijote {
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;
    private File file;

    public AccesoQuijote(File file) {
        this.file = file;
    }
    
    public void abrirFicherosEscritura(File archivoEscritura){
        try {
            fw = new FileWriter(archivoEscritura);
            bw = new BufferedWriter(fw);
        } catch (IOException ex) {
            Logger.getLogger(AccesoQuijote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escribirLinea(String linea){
        try {
            bw.write(linea);
        } catch (IOException ex) {
            Logger.getLogger(AccesoQuijote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarFicherosEscritura(){
        try {
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(AccesoQuijote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrirFicherosLectura(File file){
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccesoQuijote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String leerLineaFichero(){
        String lineaLeida = null;
        try {
            lineaLeida = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(AccesoQuijote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  lineaLeida;
    }
    
    public void cerrarFicherosLectura(){
        try {
            br.close();
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(AccesoQuijote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
