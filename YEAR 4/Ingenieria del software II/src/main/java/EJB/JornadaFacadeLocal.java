/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.Jornada;

/**
 *
 * @author Jose Maria
 */
@Local
public interface JornadaFacadeLocal {

    void create(Jornada jornada);

    void edit(Jornada jornada);

    void remove(Jornada jornada);

    Jornada find(Object id);

    List<Jornada> findAll();

    List<Jornada> findRange(int[] range);

    int count();
    
}
