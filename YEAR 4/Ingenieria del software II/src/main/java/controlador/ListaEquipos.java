/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.EquipoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import modelo.Equipo;

/**
 *
 * @author Jose Maria
 */
@Named
@ApplicationScoped

public class ListaEquipos implements Serializable {

    private List<Equipo> equipos;

    @EJB
    private EquipoFacadeLocal equipoEJB;

    @PostConstruct
    public void init() {
        equipos = new ArrayList<>();
        equipos = equipoEJB.findAll();
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

}
