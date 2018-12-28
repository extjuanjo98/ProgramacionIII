/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author JJSal
 */
public class Ticket {
    private int identifcador;
    private float importeTotal;

    public Ticket(int identifcador, float importeTotal) {
        this.identifcador = identifcador;
        this.importeTotal = importeTotal;
    }

    public int getIdentifcador() {
        return identifcador;
    }

    public void setIdentifcador(int identifcador) {
        this.identifcador = identifcador;
    }

    public float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(float importeTotal) {
        this.importeTotal = importeTotal;
    }
    
}
