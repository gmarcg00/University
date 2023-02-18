/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CompeticionFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Competicion;

/**
 *
 * @author Jose Maria
 */
@Named
@ViewScoped

public class AltaCompeticionControlador implements Serializable{
    
    private Competicion Comp;
    
    @EJB
    private CompeticionFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        Comp = new Competicion();
    }
    
    public void insertar(){
        usuarioEJB.create(Comp);
    }

    public Competicion getComp() {
        return Comp;
    }

    public void setComp(Competicion Comp) {
        this.Comp = Comp;
    }
    
}
