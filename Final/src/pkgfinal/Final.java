/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import vista.Vista;

/**
 *
 * @author Juanjo Salvo Mateos
 */
public class Final {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
