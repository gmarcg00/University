/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose Maria
 */

@Entity
@Table(name="jugador")

public class Jugador implements Serializable{
    
    @Id
    @NotNull
    @Column(name="Nombre", unique = true)
    private String nombre;
    
    @Column(name="Equipo", nullable = false)
    private String equipo;
    
    @Column(name="Nacionalidad")
    private String nacionalidad;
        
    @Column(name="Valor")
    private double valor;
    
    @Column(name="PuntuacionSemanal")
    private int puntuacionSemanal;
    
    @Column(name="PuntuacionTotal")
    private int puntuacionTotal;
    
    @Column(name="Disponible")
    private int disponible;

    @Column(name = "Posicion")
    private String posicion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getPuntuacionSemanal() {
        return puntuacionSemanal;
    }

    public void setPuntuacionSemanal(int puntuacionSemanal) {
        this.puntuacionSemanal = puntuacionSemanal;
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nombre);
        hash = 89 * hash + Objects.hashCode(this.equipo);
        hash = 89 * hash + Objects.hashCode(this.nacionalidad);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 89 * hash + this.puntuacionSemanal;
        hash = 89 * hash + this.puntuacionTotal;
        hash = 89 * hash + this.disponible;
        hash = 89 * hash + Objects.hashCode(this.posicion);
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
        final Jugador other = (Jugador) obj;
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (this.puntuacionSemanal != other.puntuacionSemanal) {
            return false;
        }
        if (this.puntuacionTotal != other.puntuacionTotal) {
            return false;
        }
        if (this.disponible != other.disponible) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.equipo, other.equipo)) {
            return false;
        }
        if (!Objects.equals(this.nacionalidad, other.nacionalidad)) {
            return false;
        }
        if (!Objects.equals(this.posicion, other.posicion)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
