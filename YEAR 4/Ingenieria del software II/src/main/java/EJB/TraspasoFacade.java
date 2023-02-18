/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Traspaso;

/**
 *
 * @author Jose Maria
 */
@Stateless
public class TraspasoFacade extends AbstractFacade<Traspaso> implements TraspasoFacadeLocal {

    @PersistenceContext(unitName = "INSO2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TraspasoFacade() {
        super(Traspaso.class);
    }
    
}
