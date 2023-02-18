/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.EquipoJuegoFacadeLocal;
import EJB.JugadorFacadeLocal;
import EJB.JugadorJuegoFacadeLocal;
import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.EquipoJuego;
import modelo.Jugador;
import modelo.JugadorJuego;
import modelo.Liga;
import modelo.Usuario;

/**
 *
 * @author Jose Maria
 */
@Named("ingresoEquipo")
@ViewScoped

public class ControladorIngresoEquipo implements Serializable {

    private EquipoJuego equipo;
    private Liga lig;
    private Usuario user;
    private List<Jugador> jugadores;
    private List<JugadorJuego> jugadoresJuego;
    private List<JugadorJuego> jugadoresJuegoLiga;

    @EJB
    private EquipoJuegoFacadeLocal equipoJuegoEJB;

    @EJB
    private JugadorFacadeLocal jugadorEJB;

    @EJB
    private JugadorJuegoFacadeLocal jugadorJuegoEJB;

    @PostConstruct
    public void init() {
        equipo = new EquipoJuego();
        user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        lig = (Liga) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("liga");
        jugadores = new ArrayList<>();
        jugadoresJuegoLiga = new ArrayList<>();

    }

    public String crear() throws SQLIntegrityConstraintViolationException {
        equipo.setPresupuesto(500000.00);
        equipo.setPuntuacionTotal(0);
        equipo.setNombreLiga(lig);
        equipo.setNombreUsuario(user);

        try {

            //Primero comprobamos si ya hay mas equipos en la liga y han sido creados los jugadoresjuego o aun no
            jugadoresJuego = jugadorJuegoEJB.findAll();
            boolean creados = false;
            int contador = 0;

            while (!creados && contador < jugadoresJuego.size()) {
                if (jugadoresJuego.get(contador).getNombreLiga().getNombre().equals(lig.getNombre())) {
                    creados = true;
                }
                contador++;
            }

            //Segun si ya existen o hay que crearlos actuamos de una forma u otra
            if (creados) {
                equipoJuegoEJB.create(equipo);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("miequipo", equipo);
                //Sacamos los jugadores de nuestra liga
                jugadoresLiga();
                //Anadimos los jugadores no usados a otra lista
                List<JugadorJuego> jugadoresLibres = new ArrayList<>();
                jugadoresLibres = obtenerLibres();
                //Seleccionamos 11 numeros al azar de la lista de jugadores libres donde ya se añaden a nuestro equipo en la base de datos
                obtenOnce(jugadoresLibres);
            } else {
                //Creamos los jugadores anadiendolos a una lista de donde 11 les cambiaremos el equipo
                List<JugadorJuego> anadir = new ArrayList<>();

                jugadores = jugadorEJB.findAll();

                for (int i = 0; i < jugadores.size(); i++) {
                    JugadorJuego jugador = new JugadorJuego();
                    jugador.setEnVenta(0);
                    jugador.setNombreLiga(lig);
                    jugador.setNombreJugador(jugadores.get(i));
                    anadir.add(jugador);
                }
                equipoJuegoEJB.create(equipo);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("miequipo", equipo);

                asignaJugadores(anadir);
            }

            return "paginaPrincipal.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre de equipo ya existe, prueba con otro"));
        }
        return "";
    }

    public void jugadoresLiga() {
        int contador = 0;

        while (contador < jugadoresJuego.size()) {
            if (jugadoresJuego.get(contador).getNombreLiga().getNombre().equals(lig.getNombre())) {
                jugadoresJuegoLiga.add(jugadoresJuego.get(contador));
            }
            contador++;
        }
    }

    public List<JugadorJuego> obtenerLibres() {
        List<JugadorJuego> libres = new ArrayList<>();
        int contador = 0;

        while (contador < jugadoresJuegoLiga.size()) {
            if (jugadoresJuegoLiga.get(contador).getEquipoJuego() == null) {
                libres.add(jugadoresJuegoLiga.get(contador));
            }
            contador++;
        }

        return libres;
    }

    public void obtenOnce(List<JugadorJuego> jugadoresLibres) {
        List<Integer> numeros = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
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

        int contador = 0;
        for (int i = 0; i < 11; i++) {
            jugadoresLibres.get(numeros.get(i)).setEquipoJuego(equipo);
            jugadoresLibres.get(numeros.get(i)).setPosicionJuego(contador);
            jugadoresLibres.get(numeros.get(i)).setEnVenta(0);
            jugadorJuegoEJB.edit(jugadoresLibres.get(numeros.get(i)));
            contador++;
        }

    }

    public void asignaJugadores(List<JugadorJuego> lista) {
        List<Integer> numeros = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            int random = (int) Math.floor(Math.random() * lista.size());
            if (!numeros.contains(random)) {
                numeros.add(random);
            } else {
                boolean libre = false;
                do {
                    random = (int) Math.floor(Math.random() * lista.size());
                    if (!numeros.contains(random)) {
                        numeros.add(random);
                        libre = true;
                    }
                } while (!libre);
            }
        }

        for (int i = 0; i < 11; i++) {
            lista.get(numeros.get(i)).setEquipoJuego(equipo);
            lista.get(numeros.get(i)).setPosicionJuego(i);
        }

        for (int i = 0; i < lista.size(); i++) {
            jugadorJuegoEJB.create(lista.get(i));
        }
    }

    public EquipoJuego getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoJuego equipo) {
        this.equipo = equipo;
    }

}
