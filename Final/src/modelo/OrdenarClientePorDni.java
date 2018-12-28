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
public class OrdenarClientePorDni  implements Comparator<Cliente> {
    @Override
    public int compare(Cliente o1, Cliente o2) {
    return o1.getDni().compareTo(o2.getDni()); 
    }
}