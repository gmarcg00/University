/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.EquipoJuego;

/**
 *
 * @author Jose Maria
 */
@Local
public interface EquipoJuegoFacadeLocal {

    void create(EquipoJuego equipoJuego);

    void edit(EquipoJuego equipoJuego);

    void remove(EquipoJuego equipoJuego);

    EquipoJuego find(Object id);

    List<EquipoJuego> findAll();

    List<EquipoJuego> findRange(int[] range);

    int count();
    
}
