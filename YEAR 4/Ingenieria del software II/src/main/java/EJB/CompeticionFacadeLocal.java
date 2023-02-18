/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Competicion;

/**
 *
 * @author Jose Maria
 */
@Local
public interface CompeticionFacadeLocal {

    void create(Competicion competicion);

    void edit(Competicion competicion);

    void remove(Competicion competicion);

    Competicion find(Object id);

    List<Competicion> findAll();

    List<Competicion> findRange(int[] range);

    int count();
    
}
