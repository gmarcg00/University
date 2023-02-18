/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.EquipoJuego;

/**
 *
 * @author Jose Maria
 */
@Stateless
public class EquipoJuegoFacade extends AbstractFacade<EquipoJuego> implements EquipoJuegoFacadeLocal {

    @PersistenceContext(unitName = "INSO2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoJuegoFacade() {
        super(EquipoJuego.class);
    }
    
}
