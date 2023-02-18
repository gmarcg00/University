/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.PartidoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import modelo.Equipo;
import modelo.Partido;

/**
 *
 * @author Jose Maria
 */
@Named("listaPartidos")
@ViewScoped

public class ControladorJornada implements Serializable {

    private List<Partido> partidos;
    private List<Equipo> products;

    @EJB
    private PartidoFacadeLocal partidoEJB;

    @Inject
    private ListaEquipos service;

    @PostConstruct
    public void init() {
        products = service.getEquipos();
        partidos = new ArrayList<>();
    }

    public List<Partido> pedirJornada(int n, int m) {
        Partido partido = new Partido();
        int total = partidoEJB.count() + 1;
        int i = 1;
        int j = 0;
        
        while(i<total && j<m) {
            partido = partidoEJB.find(i);
            if (partido.getNumJornada().getNumJornada() == n) {
                partidos.add(partido);
                j++;
            }
            i++;
        }
        return partidos;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public List<Equipo> getProducts() {
        return products;
    }

    public void setProducts(List<Equipo> products) {
        this.products = products;
    }

    

}
