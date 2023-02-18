/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.EquipoJuegoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.EquipoJuego;
import modelo.Liga;

/**
 *
 * @author guill
 */
@Named("listaEquipo")
@ViewScoped

public class ControladorLiga implements Serializable {

    private List<EquipoJuego> products;
    private Liga miLiga;
    private EquipoJuego miEquipo;

    @EJB
    private EquipoJuegoFacadeLocal equipoEJB;

    @Inject
    private ListaEquiposJuego service;

    @PostConstruct
    public void init() {
        service.init();
        products = service.getEquiposLiga();
        miLiga = (Liga) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("liga");

        ordenar();
        asignarPosiciones();

        //Actualizamos la persistencia
        actualizarPersistencia();

    }

    public void ordenar() {
        List<Integer> list = new ArrayList<>();
        List<EquipoJuego> ordenada = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            list.add(products.get(i).getPuntuacionTotal());
        }

        Collections.sort(list, Collections.reverseOrder());

        for (int i = 0; i < list.size(); i++) {
            for (int k = 0; k < products.size(); k++) {
                if (list.get(i).equals(products.get(k).getPuntuacionTotal())) {
                    ordenada.add(products.get(k));
                }
            }
        }

        products = ordenada;
    }

    public void asignarPosiciones() {
        for (int i = 0; i < products.size(); i++) {
            int posicion = i + 1;
            products.get(i).setPosicionLiga(posicion);
            equipoEJB.edit(products.get(i));

        }

    }

    private void actualizarPersistencia() {
        miEquipo = (EquipoJuego) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("miequipo");

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getNombre().equals(miEquipo.getNombre())) {
                miEquipo = products.get(i);
            }
        }

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("miequipo", miEquipo);
    }

    public List<EquipoJuego> getProducts() {
        return products;
    }

    public void setProducts(List<EquipoJuego> products) {
        this.products = products;
    }

    public Liga getMiLiga() {
        return miLiga;
    }

    public void setMiLiga(Liga miLiga) {
        this.miLiga = miLiga;
    }

}
