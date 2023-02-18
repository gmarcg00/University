/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.JugadorJuegoFacadeLocal;
import EJB.LigaFacadeLocal;
import EJB.UsuarioFacadeLocal;
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
import modelo.Competicion;
import modelo.JugadorJuego;
import modelo.Liga;
import modelo.Usuario;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Jose Maria
 */
@Named
@ViewScoped

public class ControladorIngresoLiga implements Serializable {

    private Liga lig;
    private Liga ligComparacion;
    private Usuario user;

    @EJB
    private LigaFacadeLocal ligaEJB;

    @EJB
    private UsuarioFacadeLocal userEJB;

    @EJB
    private JugadorJuegoFacadeLocal jugadorEJB;

    @PostConstruct
    public void init() {
        lig = new Liga();
        ligComparacion = new Liga();
        user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }

    public String insertar() throws SQLIntegrityConstraintViolationException {
        Competicion compe = new Competicion();
        boolean anadido = false;

        compe.setNombreCompeticion("Liga Santander");
        lig.setCompleta(0);
        lig.setCompeticion(compe);

        try {
            ligaEJB.create(lig);

            user.setNombreLiga(lig);
            userEJB.edit(user);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Done", "Liga creada"));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("liga", lig);

            anadido = true;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre de liga ya existe, prueba con otro"));
        }

        if (anadido) {
            return "ingresoEquipo.xhtml?faces-redirect=true";
        } else {
            return "";
        }
    }

    public String ingreso() {
        List<Liga> ligas = new ArrayList<>();
        ligas = ligaEJB.findAll();
        int contador = 0;
        boolean autenticado = false;

        while (!autenticado && contador < ligas.size()) {
            if (ligas.get(contador).getNombre().equals(ligComparacion.getNombre())) {
                if (ligas.get(contador).getContraseña().equals(ligComparacion.getContraseña())) {
                    autenticado = true;
                    lig = ligas.get(contador);
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contraseña incorrecta"));
                }
            }
            contador++;
        }

        if (autenticado == true) {
            //Ahora comprobamos que la liga no este completa
            boolean completa = false;
            completa = comprobarLiga(lig);

            if (!completa) {
                user.setNombreLiga(lig);
                userEJB.edit(user);

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("liga", lig);
                return "./ingresoEquipo.xhtml?faces-redirect=true";
            } else {
                String ligasIncompletas = "";
                List<Liga> ligasLibres = ligasIncompletas();

                for (int i = 0; i < ligasLibres.size(); i++) {
                    ligasIncompletas = ligasIncompletas.concat(ligasLibres.get(i).getNombre() + "");
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Liga completa"));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ligas incompletas", ligasIncompletas));
                return "";
            }
        } else {
            FacesMessage message = new FacesMessage("Erroneo!");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return "";
        }
    }

    public Liga getLig() {
        return lig;
    }

    public void setLig(Liga lig) {
        this.lig = lig;
    }

    public Liga getLigComparacion() {
        return ligComparacion;
    }

    public void setLigComparacion(Liga ligComparacion) {
        this.ligComparacion = ligComparacion;
    }

    private boolean comprobarLiga(Liga liga) {
        List<JugadorJuego> jugadores = jugadorEJB.findAll();
        List<JugadorJuego> jugadoresLiga = new ArrayList<>();

        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getNombreLiga().getNombre().equals(liga.getNombre())) {
                jugadoresLiga.add(jugadores.get(i));
            }
        }

        int libres = 0;

        for (int i = 0; i < jugadoresLiga.size(); i++) {
            if (jugadoresLiga.get(i).getEquipoJuego() == null) {
                libres++;
            }
        }

        //Devuelve true si esta llena
        return libres < 11;
    }

    private List<Liga> ligasIncompletas() {
        List<Liga> ligas = ligaEJB.findAll();
        List<Liga> incompletas = new ArrayList<>();

        for (int i = 0; i < ligas.size(); i++) {
            if (!comprobarLiga(ligas.get(i))) {
                incompletas.add(ligas.get(i));
            }
        }

        return incompletas;
    }

}
