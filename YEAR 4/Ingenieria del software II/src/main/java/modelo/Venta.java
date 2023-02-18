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
@Table(name="venta")

public class Venta implements Serializable{
    
    @Id
    @NotNull
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IdVenta")
    private int idVenta;
    
    @NotNull
    @Column(name="Valor")
    private double valor;
    
    @NotNull
    @JoinColumn(name="EquipoComprador")
    private Equipo equipoComprador;
    
    @NotNull
    @JoinColumn(name="EquipoVendedor")
    private Equipo equipoVendedor;
    
    @NotNull
    @JoinColumn(name="Jugador")
    private Jugador jugador;

    public int getIdVenta() {
        return idVenta;
    }

    public double getValor() {
        return valor;
    }

    public Equipo getEquipoComprador() {
        return equipoComprador;
    }

    public Equipo getEquipoVendedor() {
        return equipoVendedor;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setEquipoComprador(Equipo equipoComprador) {
        this.equipoComprador = equipoComprador;
    }

    public void setEquipoVendedor(Equipo equipoVendedor) {
        this.equipoVendedor = equipoVendedor;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.idVenta;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.valor) ^ (Double.doubleToLongBits(this.valor) >>> 32));
        hash = 71 * hash + Objects.hashCode(this.equipoComprador);
        hash = 71 * hash + Objects.hashCode(this.equipoVendedor);
        hash = 71 * hash + Objects.hashCode(this.jugador);
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
        final Venta other = (Venta) obj;
        if (this.idVenta != other.idVenta) {
            return false;
        }
        if (Double.doubleToLongBits(this.valor) != Double.doubleToLongBits(other.valor)) {
            return false;
        }
        if (!Objects.equals(this.equipoComprador, other.equipoComprador)) {
            return false;
        }
        if (!Objects.equals(this.equipoVendedor, other.equipoVendedor)) {
            return false;
        }
        if (!Objects.equals(this.jugador, other.jugador)) {
            return false;
        }
        return true;
    }
    
    
}
