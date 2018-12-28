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
public class OrdenarProductoAlfabeticamente implements Comparator<Producto> {
    @Override
    public int compare(Producto o1, Producto o2) {
    return o1.getNombre().compareTo(o2.getNombre()); 
    }
}
