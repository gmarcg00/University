/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.EquipoJuegoFacadeLocal;
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
 * @author guill
 */
@Named
@ApplicationScoped

public class ListaEquiposJuego implements Serializable {

    private Liga liga;

    private List<EquipoJuego> equipos;
    private List<EquipoJuego> equiposLiga;

    @EJB
    private EquipoJuegoFacadeLocal equipoEJB;

    @EJB
    private JugadorJuegoFacadeLocal jugadorEJB;

    @PostConstruct
    public void init() {
        equipos = new ArrayList<>();

        equipos = equipoEJB.findAll();
    }
    
    public List<EquipoJuego> getEquiposLiga() {
        liga = (Liga) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("liga");

        //Anadimos a una lista los equipos de nuestra liga
        equiposLiga = equiposLiga();
        
        //Calculamos la puntuacion de cada equipo
        calculaPuntuacion();
        
        return equiposLiga;
    }

    public List<EquipoJuego> equiposLiga() {
        List<EquipoJuego> misEquipos = new ArrayList<>();

        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i).getNombreLiga().getNombre().equals(liga.getNombre())) {
                misEquipos.add(equipos.get(i));
            }
        }

        return misEquipos;
    }

    private void calculaPuntuacion() {
        List<JugadorJuego> jugadores = new ArrayList<>();
        jugadores = jugadorEJB.findAll();

        for (int i = 0; i < equiposLiga.size(); i++) {
            int puntuacionTotal = 0;

            for (int k = 0; k < jugadores.size(); k++) {
                if (jugadores.get(k).getEquipoJuego() != null) {
                    if (jugadores.get(k).getEquipoJuego().getNombre().equals(equiposLiga.get(i).getNombre())) {
                        puntuacionTotal += jugadores.get(k).getNombreJugador().getPuntuacionTotal();
                    }
                }
            }

            equiposLiga.get(i).setPuntuacionTotal(puntuacionTotal);
        }

    }   

    public void setEquiposLiga(List<EquipoJuego> equiposLiga) {
        this.equiposLiga = equiposLiga;
    }

    public List<EquipoJuego> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<EquipoJuego> equipos) {
        this.equipos = equipos;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }
    
    

}
