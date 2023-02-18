/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logic;

/**
 *
 * @author guill
 */
public class CambioTrienio {
    private int newTrienio;
    private int mes;
    private boolean isCambioTrienio;

    public void setNewTrienio(int newTrienio) {
        this.newTrienio = newTrienio;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setIsCambioTrienio(boolean isCambioTrienio) {
        this.isCambioTrienio = isCambioTrienio;
    }

    public int getNewTrienio() {
        return newTrienio;
    }

    public int getMes() {
        return mes;
    }

    public boolean isIsCambioTrienio() {
        return isCambioTrienio;
    }

    public CambioTrienio(int newTrienio, int mes, boolean isCambioTrienio) {
        this.newTrienio = newTrienio;
        this.mes = mes;
        this.isCambioTrienio = isCambioTrienio;
    }
    
    public CambioTrienio(){
        
    }
         
    
    
}
