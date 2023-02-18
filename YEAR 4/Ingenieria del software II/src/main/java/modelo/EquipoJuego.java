/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose Maria
 */

@Entity
@Table(name="equipojuego")

public class EquipoJuego implements Serializable{
    
    @Id
    @Column(name="Nombre", nullable = false, unique = true)
    private String nombre;
    
    @NotNull
    @Column(name="PosicionLiga")
    private int posicionLiga;
        
    @NotNull    
    @Column(name="PuntuacionTotal")
    private int puntuacionTotal;
    
    @NotNull    
    @Column(name="Presupuesto")
    private double presupuesto;
    
    @JoinColumn(name="nombreUsuario", nullable = false)
    private Usuario nombreUsuario;
    
    @JoinColumn(name="nombreLiga", nullable = false)
    private Liga nombreLiga;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicionLiga() {
        return posicionLiga;
    }

    public void setPosicionLiga(int posicionLiga) {
        this.posicionLiga = posicionLiga;
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Usuario getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(Usuario nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Liga getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(Liga nombreLiga) {
        this.nombreLiga = nombreLiga;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nombre);
        hash = 89 * hash + this.posicionLiga;
        hash = 89 * hash + this.puntuacionTotal;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.presupuesto) ^ (Double.doubleToLongBits(this.presupuesto) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.nombreUsuario);
        hash = 89 * hash + Objects.hashCode(this.nombreLiga);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EquipoJuego other = (EquipoJuego) obj;
        if (this.posicionLiga != other.posicionLiga) {
            return false;
        }
        if (this.puntuacionTotal != other.puntuacionTotal) {
            return false;
        }
        if (Double.doubleToLongBits(this.presupuesto) != Double.doubleToLongBits(other.presupuesto)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.nombreUsuario, other.nombreUsuario)) {
            return false;
        }
        if (!Objects.equals(this.nombreLiga, other.nombreLiga)) {
            return false;
        }
        return true;
    }

    
}
