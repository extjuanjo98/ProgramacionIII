/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Comparator;

/**
 *
 * @author JJSal
 */
public class OrdenarProductoPorIvaPrecio implements Comparator<Producto> {
    @Override
    public int compare(Producto o1, Producto o2) {
        if(!(o1.getIva()==o2.getIva())){
            return (int)((o1.getIva()*100) - (o2.getIva()*100));
        }else{
            return (int)((o1.getPrecio()*100) - (o2.getPrecio()*100));
        }
    }    
}

