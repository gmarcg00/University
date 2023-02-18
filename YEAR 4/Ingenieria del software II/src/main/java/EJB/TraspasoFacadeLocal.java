/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Traspaso;

/**
 *
 * @author Jose Maria
 */
@Local
public interface TraspasoFacadeLocal {

    void create(Traspaso traspaso);

    void edit(Traspaso traspaso);

    void remove(Traspaso traspaso);

    Traspaso find(Object id);

    List<Traspaso> findAll();

    List<Traspaso> findRange(int[] range);

    int count();
    
}
