/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.JugadorJuegoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.EquipoJuego;
import modelo.JugadorJuego;
import modelo.Liga;

/**
 *
 * @author Jose Maria
 */
@Named
@ApplicationScoped

public class ListaJugadoresJuego implements Serializable {

    private List<JugadorJuego> jugadores;
    private List<JugadorJuego> jugador;
    private Liga lig;
    private EquipoJuego miEquipo;

    public Liga getLig() {
        return lig;
    }

    @EJB
    private JugadorJuegoFacadeLocal jugadorEJB;

    @PostConstruct
    public void init() {
        lig = (Liga) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("liga");
        miEquipo = (EquipoJuego) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("miequipo");
        jugadores = new ArrayList<>();
        jugador = new ArrayList<>();
        jugador = jugadorEJB.findAll();
        for (int i = 0; i < jugador.size(); i++) {
            if (jugador.get(i).getNombreLiga().getNombre().equals(lig.getNombre())) {
                if (jugador.get(i).getEquipoJuego() != null) {
                    if (!jugador.get(i).getEquipoJuego().getNombre().equals(miEquipo.getNombre()) && jugador.get(i).getEnVenta() == 1) {
                        jugadores.add(jugador.get(i));
                    }
                }
            }
        }

        List<JugadorJuego> miLiga = new ArrayList<>();
        miLiga = jugadoresAzar();
        quitarVenta(miLiga);

        obtenOnce(miLiga);

    }

    public void quitarVenta(List<JugadorJuego> jugadoresLibres) {
        for (int i = 0; i < jugadoresLibres.size(); i++) {
            jugadoresLibres.get(i).setEnVenta(0);
            jugadorEJB.edit(jugadoresLibres.get(i));
        }
    }

    public List<JugadorJuego> jugadoresAzar() {
        List<JugadorJuego> miLiga = new ArrayList<>();
        for (int i = 0; i < jugador.size(); i++) {
            if (jugador.get(i).getNombreLiga().getNombre().equals(lig.getNombre()) && jugador.get(i).getEquipoJuego() == null) {
                miLiga.add(jugador.get(i));
            }
        }
        return miLiga;
    }

    public void obtenOnce(List<JugadorJuego> jugadoresLibres) {
        List<Integer> numeros = new ArrayList<>();
        EquipoJuego agentesLibres = new EquipoJuego();
        agentesLibres.setNombre("Agentes Libres");

        for (int i = 0; i < 5; i++) {
            int random = (int) Math.floor(Math.random() * jugadoresLibres.size());
            if (!numeros.contains(random)) {
                numeros.add(random);
            } else {
                boolean libre = false;
                do {
                    random = (int) Math.floor(Math.random() * jugadoresLibres.size());
                    if (!numeros.contains(random)) {
                        numeros.add(random);
                        libre = true;
                    }
                } while (!libre);
            }
        }

        for (int i = 0; i < 5; i++) {
            jugadoresLibres.get(numeros.get(i)).setEnVenta(1);
            jugadorEJB.edit(jugadoresLibres.get(numeros.get(i)));
            jugadoresLibres.get(numeros.get(i)).setEquipoJuego(agentesLibres);
            jugadores.add(jugadoresLibres.get(numeros.get(i)));
        }

    }

    public List<JugadorJuego> getJugadores(int n) {
        List<JugadorJuego> numJugadores = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            numJugadores.add(jugadores.get(i));
        }
        
        return numJugadores;
    }

    public List<JugadorJuego> getJugador() {
        return jugador;
    }

    public void setJugador(List<JugadorJuego> jugador) {
        this.jugador = jugador;
    }

    public List<JugadorJuego> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorJuego> jugadores) {
        this.jugadores = jugadores;
    }

}
