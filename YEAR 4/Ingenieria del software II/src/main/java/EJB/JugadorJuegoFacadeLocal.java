/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Local;
import modelo.JugadorJuego;

/**
 *
 * @author Jose Maria
 */
@Local
public interface JugadorJuegoFacadeLocal {

    void create(JugadorJuego jugadorJuego);

    void edit(JugadorJuego jugadorJuego);

    void remove(JugadorJuego jugadorJuego);

    JugadorJuego find(Object id);

    List<JugadorJuego> findAll();

    List<JugadorJuego> findRange(int[] range);

    int count();
    
}
