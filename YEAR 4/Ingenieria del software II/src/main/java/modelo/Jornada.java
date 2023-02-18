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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose Maria
 */

@Entity
@Table(name="jornada")

public class Jornada implements Serializable{
    
    @Id
    @NotNull
    @Column(name="NumJornada")
    private int numJornada;
    
    @Column(name="Fecha")
    private Date fecha;
    
    @Column(name="Finalizada")
    private int finalizada;
    
    @NotNull
    @JoinColumn(name="NombreCompeticion")
    private Competicion competicion;

    public int getNumJornada() {
        return numJornada;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getFinalizada() {
        return finalizada;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFinalizada(Byte finalizada) {
        this.finalizada = finalizada;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.numJornada;
        hash = 43 * hash + Objects.hashCode(this.fecha);
        hash = 43 * hash + Objects.hashCode(this.finalizada);
        hash = 43 * hash + Objects.hashCode(this.competicion);
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
        final Jornada other = (Jornada) obj;
        if (this.numJornada != other.numJornada) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.finalizada, other.finalizada)) {
            return false;
        }
        if (!Objects.equals(this.competicion, other.competicion)) {
            return false;
        }
        return true;
    }
    
    
}
