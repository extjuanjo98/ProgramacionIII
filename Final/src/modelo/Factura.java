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
public class Factura implements Serializable{
    private int indentificador;
    private String dniCliente;
    private float importeTotal;

    public Factura(int indentificador, String dniCliente, float importeTotal) {
        this.indentificador = indentificador;
        this.dniCliente = dniCliente;
        this.importeTotal = importeTotal;
    }

    public int getIndentificador() {
        return indentificador;
    }

    public void setIndentificador(int indentificador) {
        this.indentificador = indentificador;
    }

    public String getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(String dniCliente) {
        this.dniCliente = dniCliente;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }
    
    
    
}
