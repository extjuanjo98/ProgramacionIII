/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author JJSal
 */
public class Cliente implements Serializable{
    private String dni;
    private String nombre;
    private String apellido;
    private String direccion;
    private String estado;
    private ArrayList <Factura> coleccionDeFacturas;

    public Cliente(String dni, String nombre, String apellido, String direccion, String estado, ArrayList<Factura> coleccionDeFacturas) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.estado = estado;
        this.coleccionDeFacturas = coleccionDeFacturas;
    }
    public Cliente(String dni, String nombre, String apellido, String direccion, String estado) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.estado = estado;
        this.coleccionDeFacturas = new ArrayList <>();
    }
    

    

    public String ImprimeClinte(){
        return String.format("|%9s|%25s|%20s|%40s|%8s|",this.dni,this.nombre,this.apellido,this.direccion,this.estado);        
    }
    
    
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Factura> getColeccionDeFacturas() {
        return coleccionDeFacturas;
    }

    public void setColeccionDeFacturas(ArrayList<Factura> coleccionDeFacturas) {
        this.coleccionDeFacturas = coleccionDeFacturas;
    }

    public boolean dniIguales(String dni) {
        return(this.dni.equals(dni));
    }

    public void darBaja() {
        this.estado="inactivo";
    }

    public void cambiarEstado() {        
        this.estado="activo";        
    }
    
}
