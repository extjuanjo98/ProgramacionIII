/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 *
 * @author alce
 */
public class Vista {
    
    Scanner sc =new Scanner(System.in);
    
    
    public void runMenu(String menu){
        boolean salir=false;
        String opcion;
        
        //Arranque
            //Esto va en el controlador que no tienes hecho puto vago ahí te mueras
        try{
            Path pC= Rutas.pathToFileFolderOnDesktop(nombreCarpeta,nombreArchivo);
        }catch(IOException ex){
            
        }
        
        do{
            System.out.printf(menu);
            opcion=sc.nextLine();
            
            switch(opcion){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "s":
                case "S":
                    salir=true;
                    break;
                default:
                    System.err.printf("%n%nERROR: opcion no valida%n");
            }
        }while(!salir);
        
        //Salir del programa;
        
        
    }
}
