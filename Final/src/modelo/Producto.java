/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author JJSal
 */
public class Producto implements Serializable{
    private String nombre;
    private float precio;
    private float iva;
    private int numUnidades;

    public Producto(String nombre, float precio, float iva, int numUnidades) {        
        this.nombre = nombre;
        this.precio = precio;
        this.iva = iva;
        this.numUnidades = numUnidades;
    }
    public String ImprimeProducto(){
        return String.format("|%30s|%10.2f|%10.2f|%8d|",this.nombre,this.precio,this.iva,this.numUnidades);        
    }
    
    
    public String imprimeHTML() {
        String resultado;
        resultado=String.format("<TR>"
            +"<TD>%s</TD>" //nombre
            +"<TD>%.2f</TD>" //precio
            +"<TD>%.2f</TD>" //iva
            +"<TD>%d</TD>" //unidades
            +"</TR>",
            this.nombre,
            this.precio,
            this.iva,
            this.numUnidades );
        
        return resultado;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public int getNumUnidades() {
        return numUnidades;
    }

    public void setNumUnidades(int numUnidades) {
        this.numUnidades = numUnidades;
    }

    public boolean comprobarSiExiste(String producto) {
        return (this.nombre.equals(producto));
    }

    public boolean comprobarCantidad(String producto, int cantidad) {
        if(this.nombre.equals(producto)){
            return (this.numUnidades>=cantidad);
        }
        return false;
    }

    public void comprar(String producto, int cantidad) {
        if(this.nombre.equals(producto)){
            this.numUnidades-=cantidad;
        }        
    }
    
    
}
