/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author isaac
 */
public class Filtros {
    FilenameFilter filtroImagenes = new FilenameFilter() {
        @Override
        public boolean accept(File file, String nombre) {
            return nombre.endsWith(".png") || nombre.endsWith(".jpg") || nombre.endsWith(".tiff");
        }
    };
    
    FilenameFilter filtroVideos = new FilenameFilter() {
        @Override
        public boolean accept(File file, String nombre) {
            return nombre.endsWith(".avi") || nombre.endsWith(".mp4") || nombre.endsWith(".mkv");
        }
    };
    
    FilenameFilter filtroDocumentos = new FilenameFilter() {
        @Override
        public boolean accept(File file, String nombre) {
            return nombre.endsWith(".doc") || nombre.endsWith(".txt") || nombre.endsWith(".pdf");
        }
    };
    
    //metodos
    public File[] listarImagenes(File file){
        File[] listaDirectorios = file.listFiles(filtroImagenes);
        return listaDirectorios;
    }
    
    public File[] listarVideos(File file){
        File[] listaDirectorios = file.listFiles(filtroVideos);
        return listaDirectorios;
    }
    
    public File[] listarDocumentos(File file){
        File[] listaDirectorios = file.listFiles(filtroDocumentos);
        return listaDirectorios;
    }
}
