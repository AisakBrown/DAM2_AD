/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.AccesoQuijote;
import Modelo.MetodosQuijote;
import java.io.File;

/**
 *
 * @author isaac
 */
public class MainQuijote {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MetodosQuijote mq = new MetodosQuijote();
        System.out.println(mq.contadorLineas());
        //System.out.println(mq.escribirLineasAlReves().getAbsolutePath());
        //System.out.println(mq.buscarQuijote("F:\\Program Files (x86)").getPath());
        //System.out.println(mq.contadorPalabraQuijote("Quijote"));
        //System.out.println(mq.contarLetras());
        //System.out.println(mq.dividirQuijoteCapitulos().get(0));
        //File file = new File("F:\\Program Files (x86)\\Quijote\\quijote.txt");
        //System.out.println(mq.listaPalabras(file).toString());
        //System.out.println(mq.listarPalabrasVariosFicheros().toString());
    }
    
}
