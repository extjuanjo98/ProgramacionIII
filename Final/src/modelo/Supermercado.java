/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author JJSal
 */
public class Supermercado {
    private ArrayList <Cliente> coleccionDeClientes= new ArrayList<>();
    private ArrayList <Ticket> coleccionDeTickets = new ArrayList<>();
    private ArrayList <Producto> coleccionDeProductos= new ArrayList<>();
    private String ruta;

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public ArrayList<Cliente> getColeccionDeClientes() {
        return coleccionDeClientes;
    }

    public void setColeccionDeClientes(ArrayList<Cliente> coleccionDeClientes) {
        this.coleccionDeClientes = coleccionDeClientes;
    }

    public ArrayList<Ticket> getColeccionDeTickets() {
        return coleccionDeTickets;
    }

    public void setColeccionDeTickets(ArrayList<Ticket> coleccionDeTickets) {
        this.coleccionDeTickets = coleccionDeTickets;
    }

    public ArrayList<Producto> getColeccionDeProductos() {
        return coleccionDeProductos;
    }

    public void setColeccionDeProductos(ArrayList<Producto> coleccionDeProductos) {
        this.coleccionDeProductos = coleccionDeProductos;
    } 

    
}
