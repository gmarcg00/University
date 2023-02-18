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
@Table(name="traspaso")

public class Traspaso implements Serializable{
    
    @Id
    @NotNull
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IdTraspaso")
    private int idTraspaso;
    
    @NotNull
    @Column(name="Valor")
    private double valor;
    
    @NotNull
    @JoinColumn(name="IdEquipoComprador")
    private Equipo idEquipoComprador;
    
    @NotNull
    @JoinColumn(name="IdEquipoVendedor")
    private Equipo idEquipoVendedor;
    
    @NotNull
    @JoinColumn(name="IdJugador")
    private Jugador idJugador;

    public int getIdTraspaso() {
        return idTraspaso;
    }

    public double getValor() {
        return valor;
    }

    public Equipo getIdEquipoComprador() {
        return idEquipoComprador;
    }

    public Equipo getIdEquipoVendedor() {
        return idEquipoVendedor;
    }

    public Jugador getIdJugador() {
        return idJugador;
    }

    public void setIdTraspaso(int idTraspaso) {
        this.idTraspaso = idTraspaso;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setIdEquipoComprador(Equipo idEquipoComprador) {
        this.idEquipoComprador = idEquipoComprador;
    }

    public void setIdEquipoVendedor(Equipo idEquipoVendedor) {
        this.idEquipoVendedor = idEquipoVendedor;
    }

    public void setIdJugador(Jugador idJugador) {
        this.idJugador = idJugador;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idTraspaso;
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 79 * hash + Objects.hashCode(this.idEquipoComprador);
        hash = 79 * hash + Objects.hashCode(this.idEquipoVendedor);
        hash = 79 * hash + Objects.hashCode(this.idJugador);
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
        final Traspaso other = (Traspaso) obj;
        if (this.idTraspaso != other.idTraspaso) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.idEquipoComprador, other.idEquipoComprador)) {
            return false;
        }
        if (!Objects.equals(this.idEquipoVendedor, other.idEquipoVendedor)) {
            return false;
        }
        if (!Objects.equals(this.idJugador, other.idJugador)) {
            return false;
        }
        return true;
    }
    
    
}
