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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.EquipoJuego;
import modelo.JugadorJuego;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Jose Maria
 */
@Named("listaMercado")
@ViewScoped

public class ControladorListaMercado implements Serializable {

    private List<JugadorJuego> products;
    private List<JugadorJuego> jugadoresVenta;
    private EquipoJuego miEquipo;

    @EJB
    private JugadorJuegoFacadeLocal jugadorEJB;

    @EJB
    private EquipoJuegoFacadeLocal equipoEJB;

    @Inject
    private ListaJugadoresJuego service;

    @PostConstruct
    public void init() {
        miEquipo = (EquipoJuego) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("miequipo");
        service.init();
        products = service.getJugadores();
        jugadoresVenta = new ArrayList<>();
    }

    public void getJugadores(int n) {
        products = service.getJugadores(n);
        jugadoresVenta = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (!products.get(i).getEquipoJuego().getNombre().equals(miEquipo.getNombre())) {
                jugadoresVenta.add(products.get(i));
            }
        }
    }

    public String ventaJugador(JugadorJuego jugador) {
        if (jugador.getNombreJugador().getValor() <= miEquipo.getPresupuesto()) {
            EquipoJuego suEquipo = jugador.getEquipoJuego();
            double nuevoPresupuestoMio = miEquipo.getPresupuesto() - jugador.getNombreJugador().getValor();
            double nuevoPresupuestoSuyo = suEquipo.getPresupuesto() + jugador.getNombreJugador().getValor();

            miEquipo.setPresupuesto(nuevoPresupuestoMio);
            suEquipo.setPresupuesto(nuevoPresupuestoSuyo);
            equipoEJB.edit(miEquipo);
            if (!suEquipo.getNombre().toString().equals("Agentes Libres")) {
                equipoEJB.edit(suEquipo);
            }

            int posicion = obtenerTotalEquipo();
            jugador.setEnVenta(0);
            jugador.setEquipoJuego(miEquipo);
            jugador.setPosicionJuego(posicion);
            jugadorEJB.edit(jugador);

            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getNombreJugador().equals(jugador.getNombreJugador())) {
                    products.remove(i);
                }
            }

            int puntuacionTotal = miEquipo.getPuntuacionTotal() + jugador.getNombreJugador().getPuntuacionTotal();
            miEquipo.setPuntuacionTotal(puntuacionTotal);

            equipoEJB.edit(miEquipo);
            return "mercado.xhtml?faces-redirect=true";

        } else {
            FacesMessage message = new FacesMessage("ERROR", "No te creas el PSG que no tienes pasta.");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
        return "";
    }

    public List<JugadorJuego> getProducts() {
        return products;
    }

    private int obtenerTotalEquipo() {
        List<JugadorJuego> totalEquipo = new ArrayList<>();
        List<JugadorJuego> jugadoresTotales = jugadorEJB.findAll();

        for (int i = 0; i < jugadoresTotales.size(); i++) {
            if (jugadoresTotales.get(i).getEquipoJuego() != null) {
                if (jugadoresTotales.get(i).getEquipoJuego().equals(miEquipo)) {
                    totalEquipo.add(jugadoresTotales.get(i));
                }
            }
        }

        return totalEquipo.size();
    }

    public List<JugadorJuego> getProducts(int n) {
        List<JugadorJuego> jugadores = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            jugadores.add(products.get(i));
        }
        return jugadores;
    }

    public void setProducts(List<JugadorJuego> products) {
        this.products = products;
    }

    public List<JugadorJuego> getJugadoresVenta() {
        return jugadoresVenta;
    }

    public void setJugadoresVenta(List<JugadorJuego> jugadoresVenta) {
        this.jugadoresVenta = jugadoresVenta;
    }

}
