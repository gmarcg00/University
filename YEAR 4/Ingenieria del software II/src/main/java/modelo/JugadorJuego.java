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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jose Maria
 */
@Entity
@Table(name = "jugadorjuego")

public class JugadorJuego implements Serializable {

    @Id
    @NotNull
    @Column(name = "IdJugador")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJugador;

    @Column(name="EnVenta")
    private int enVenta;
    
    @Column(name="posicionJuego")
    private int posicionJuego;

    @OneToOne
    @JoinColumn(name="EquipoJuego", nullable = true)
    private EquipoJuego equipoJuego;
    
    @ManyToOne
    @JoinColumn(name="NombreJugador", nullable = false)
    private Jugador nombreJugador;
    
    @JoinColumn(name="NombreLiga", nullable = false)
    private Liga nombreLiga;

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public int getEnVenta() {
        return enVenta;
    }

    public void setEnVenta(int enVenta) {
        this.enVenta = enVenta;
    }

    public int getPosicionJuego() {
        return posicionJuego;
    }

    public void setPosicionJuego(int posicionJuego) {
        this.posicionJuego = posicionJuego;
    }

    public EquipoJuego getEquipoJuego() {
        return equipoJuego;
    }

    public void setEquipoJuego(EquipoJuego equipoJuego) {
        this.equipoJuego = equipoJuego;
    }

    public Jugador getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(Jugador nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public Liga getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(Liga nombreLiga) {
        this.nombreLiga = nombreLiga;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idJugador;
        hash = 37 * hash + this.enVenta;
        hash = 37 * hash + this.posicionJuego;
        hash = 37 * hash + Objects.hashCode(this.equipoJuego);
        hash = 37 * hash + Objects.hashCode(this.nombreJugador);
        hash = 37 * hash + Objects.hashCode(this.nombreLiga);
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
        final JugadorJuego other = (JugadorJuego) obj;
        if (this.idJugador != other.idJugador) {
            return false;
        }
        if (this.enVenta != other.enVenta) {
            return false;
        }
        if (this.posicionJuego != other.posicionJuego) {
            return false;
        }
        if (!Objects.equals(this.equipoJuego, other.equipoJuego)) {
            return false;
        }
        if (!Objects.equals(this.nombreJugador, other.nombreJugador)) {
            return false;
        }
        if (!Objects.equals(this.nombreLiga, other.nombreLiga)) {
            return false;
        }
        return true;
    }

    
}
