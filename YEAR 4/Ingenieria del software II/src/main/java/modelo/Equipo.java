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
@Table (name="equipo")

public class Equipo implements Serializable{
    
    @Id
    @NotNull
    @Column(name="Nombre")
    private String nombre;
    
    @NotNull
    @Column(name="PosicionLiga")
    private int posicionLiga;
    
    @NotNull
    @Column(name="PuntuacionTotal")
    private int puntuacionTotal;
    
    @NotNull
    @JoinColumn(name="Competicion")
    private Competicion competicion;

    public String getNombre() {
        return nombre;
    }

    public int getPosicionLiga() {
        return posicionLiga;
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPosicionLiga(int posicionLiga) {
        this.posicionLiga = posicionLiga;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + this.posicionLiga;
        hash = 79 * hash + this.puntuacionTotal;
        hash = 79 * hash + Objects.hashCode(this.competicion);
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
        final Equipo other = (Equipo) obj;
        if (this.posicionLiga != other.posicionLiga) {
            return false;
        }
        if (this.puntuacionTotal != other.puntuacionTotal) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.competicion, other.competicion)) {
            return false;
        }
        return true;
    }
    
    
    
}
