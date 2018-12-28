/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import java.io.FileNotFoundException;
import vista.Vista;

/**
 *
 * @author JJSal
 */
public class Final {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Vista v= new Vista();
        v.runMenu(  "1.-Archivos%n"+
                    "2.-Clientes%n"+
                    "3.-Resultados%n"+
                    "4.-Listados%n"+
                    "5.-Ventas%n"+
                    "s.-Salir%n");
        
    }
    
}
