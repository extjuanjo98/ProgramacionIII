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
public class OrdenarClientePorApellidos implements Comparator<Cliente> {
    @Override
    public int compare(Cliente o1, Cliente o2) {
        if(!o1.getApellido().equals(o2.getApellido())){
            return o1.getApellido().compareTo(o2.getApellido());
        }else{
            return o1.getNombre().compareTo(o2.getNombre());
        }
    }    
}
