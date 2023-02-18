/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Liga;

/**
 *
 * @author Jose Maria
 */
@Stateless
public class LigaFacade extends AbstractFacade<Liga> implements LigaFacadeLocal {

    @PersistenceContext(unitName = "INSO2")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigaFacade() {
        super(Liga.class);
    }
    
}
