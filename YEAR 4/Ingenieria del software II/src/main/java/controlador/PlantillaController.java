/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Jose Maria
 */
@Named
@ViewScoped

public class PlantillaController implements Serializable {

    public PlantillaController() {
    }

    public void verificarYMostrar() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("liga");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("miequipo");
    }
}
