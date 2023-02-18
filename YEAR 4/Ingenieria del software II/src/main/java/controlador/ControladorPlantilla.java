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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.EquipoJuego;
import modelo.Jugador;
import modelo.JugadorJuego;

/**
 *
 * @author guill
 */
@Named("listaJugadores")
@ViewScoped

public class ControladorPlantilla implements Serializable {

    private List<JugadorJuego> products;
    private List<JugadorJuego> miPlantilla;
    private List<JugadorJuego> auxiliar;
    private EquipoJuego miEquipo;
    private Jugador reemplazo;
    private int selector;

    @Inject
    private ListaJugadoresJuego service;

    @EJB
    private JugadorJuegoFacadeLocal jugadorEJB;

    @PostConstruct
    public void init() {

        products = new ArrayList<>();
        auxiliar = new ArrayList<>();
        miPlantilla = new ArrayList<>();
        miEquipo = new EquipoJuego();
        miEquipo = (EquipoJuego) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("miequipo");
        reemplazo = new Jugador();

        products = jugadorEJB.findAll();

        int totalJugadores = products.size();

        for (int i = 0; i < totalJugadores; i++) {
            if (products.get(i).getEquipoJuego() != null) {
                if ((miEquipo.getNombre()).equals(products.get(i).getEquipoJuego().getNombre())) {
                    miPlantilla.add(products.get(i));
                }
            }
        }

        verificarVentas();
        colocarJugadores();

    }

    public void ponerVenta(JugadorJuego jugador) {
        jugador.setEnVenta(1);
        jugadorEJB.edit(jugador);
    }

    public void quitarVenta(JugadorJuego jugador) {
        jugador.setEnVenta(0);
        jugadorEJB.edit(jugador);
    }

    
    public void cambioPosicion() {
        List<JugadorJuego> aux = new ArrayList<>();
        JugadorJuego reemplazo = new JugadorJuego();

        //Primero buscamos al jugador dentro de nuestra plantilla
        reemplazo = buscarJugador();

        for (int i = 0; i < miPlantilla.size(); i++) {
            if (i == selector) {
                aux.add(reemplazo);
            } else if (reemplazo.getPosicionJuego() == i) {
                aux.add(miPlantilla.get(selector));
            } else {
                aux.add(miPlantilla.get(i));
            }
        }
        actualizarPosiciones(aux);
    }

    private JugadorJuego buscarJugador() {
        JugadorJuego objetivo = new JugadorJuego();

        for (int i = 0; i < miPlantilla.size(); i++) {
            if (miPlantilla.get(i).getNombreJugador().getNombre().equals(reemplazo.getNombre())) {
                objetivo = miPlantilla.get(i);
            }
        }

        return objetivo;
    }

    private void verificarVentas() {
        List<JugadorJuego> aux = new ArrayList<>();

        for (int i = 0; i < miPlantilla.size(); i++) {
            aux.add(miPlantilla.get(i));
            aux.get(i).setPosicionJuego(i);
            jugadorEJB.edit(aux.get(i));
        }

        miPlantilla = aux;
    }

    private void colocarJugadores() {
        List<JugadorJuego> aux = new ArrayList<>();

        for (int i = 0; i < miPlantilla.size(); i++) {
            int k = 0;
            while (miPlantilla.get(k).getPosicionJuego() != i) {
                k++;
            }
            aux.add(miPlantilla.get(k));
        }

        miPlantilla = aux;
    }

    private void actualizarPosiciones(List<JugadorJuego> aux) {
        for (int i = 0; i < miPlantilla.size(); i++) {
            miPlantilla.set(i, aux.get(i));
            miPlantilla.get(i).setPosicionJuego(i);
            jugadorEJB.edit(miPlantilla.get(i));
        }
    }

    public int getSelector() {
        return selector;
    }

    public void setSelector(int selector) {
        this.selector = selector;
    }

    public Jugador getReemplazo() {
        return reemplazo;
    }

    public void setReemplazo(Jugador reemplazo) {
        this.reemplazo = reemplazo;
    }

    public List<JugadorJuego> getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(List<JugadorJuego> auxiliar) {
        this.auxiliar = auxiliar;
    }

    public List<JugadorJuego> getProducts() {
        return products;
    }

    public void setProducts(List<JugadorJuego> products) {
        this.products = products;
    }

    public List<JugadorJuego> getMiPlantilla() {
        return miPlantilla;
    }

    public void setMiPlantilla(List<JugadorJuego> miPlantilla) {
        this.miPlantilla = miPlantilla;
    }

    public EquipoJuego getMiEquipo() {
        return miEquipo;
    }

    public void setMiEquipo(EquipoJuego miEquipo) {
        this.miEquipo = miEquipo;
    }

    public ListaJugadoresJuego getService() {
        return service;
    }

    public void setService(ListaJugadoresJuego service) {
        this.service = service;
    }

    public JugadorJuegoFacadeLocal getJugadorEJB() {
        return jugadorEJB;
    }

    public void setJugadorEJB(JugadorJuegoFacadeLocal jugadorEJB) {
        this.jugadorEJB = jugadorEJB;
    }

}
