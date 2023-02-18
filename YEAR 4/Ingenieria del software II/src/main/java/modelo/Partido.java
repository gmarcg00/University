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
@Table(name="partido")

public class Partido implements Serializable{
    
    @Id
    @NotNull
    @Column(name="IdPartido")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idPartido;
    
    @Column(name="Fecha")
    private Date fecha;
    
    @NotNull
    @JoinColumn(name="NombreEquipoLocal")
    private Equipo nombreEquipoLocal;
    
    @NotNull
    @JoinColumn(name="NombreEquipoVisitante")
    private Equipo nombreEquipoVisitante;
    
    @NotNull
    @JoinColumn(name="NumJornada")
    private Jornada numJornada;

    public int getIdPartido() {
        return idPartido;
    }

    public Date getFecha() {
        return fecha;
    }

    public Equipo getNombreEquipoLocal() {
        return nombreEquipoLocal;
    }

    public Equipo getNombreEquipoVisitante() {
        return nombreEquipoVisitante;
    }

    public Jornada getNumJornada() {
        return numJornada;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNombreEquipoLocal(Equipo nombreEquipoLocal) {
        this.nombreEquipoLocal = nombreEquipoLocal;
    }

    public void setNombreEquipoVisitante(Equipo nombreEquipoVisitante) {
        this.nombreEquipoVisitante = nombreEquipoVisitante;
    }

    public void setNumJornada(Jornada numJornada) {
        this.numJornada = numJornada;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idPartido;
        hash = 11 * hash + Objects.hashCode(this.fecha);
        hash = 11 * hash + Objects.hashCode(this.nombreEquipoLocal);
        hash = 11 * hash + Objects.hashCode(this.nombreEquipoVisitante);
        hash = 11 * hash + Objects.hashCode(this.numJornada);
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
        final Partido other = (Partido) obj;
        if (this.idPartido != other.idPartido) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.nombreEquipoLocal, other.nombreEquipoLocal)) {
            return false;
        }
        if (!Objects.equals(this.nombreEquipoVisitante, other.nombreEquipoVisitante)) {
            return false;
        }
        if (!Objects.equals(this.numJornada, other.numJornada)) {
            return false;
        }
        return true;
    }
    
    
    
}
