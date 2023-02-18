/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.EquipoJuego;
import modelo.Liga;
import modelo.Usuario;

/**
 *
 * @author Jose Maria
 */
@Named("perfil")
@ViewScoped

public class ControladorMiPerfil implements Serializable {

    private Usuario user;
    private EquipoJuego miEquipo;
    private Liga liga;

    @PostConstruct
    public void init() {
        user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        miEquipo = (EquipoJuego) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("miequipo");
        liga = (Liga) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("liga");

    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "./registro.xhtml";
    }


    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public EquipoJuego getMiEquipo() {
        return miEquipo;
    }

    public void setMiEquipo(EquipoJuego miEquipo) {
        this.miEquipo = miEquipo;
    }

    public Liga getLiga() {
        return liga;
    }

    public void setLiga(Liga liga) {
        this.liga = liga;
    }

}
