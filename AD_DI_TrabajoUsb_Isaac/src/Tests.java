
import Modelo.MetodosManejoUSB;
import java.io.File;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author isaac
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        MetodosManejoUSB m = new MetodosManejoUSB("", "");
        m.hacerBackupBienPoncho("C:\\Users\\isaac\\AndroidStudioProjects\\BotonMusicaDesgracias\\app\\src\\main\\res", "C:\\Users\\isaac\\Documents\\BackUp");
    }
    
}
