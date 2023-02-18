/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import modelo.JugadorJuego;
import modelo.Liga;

/**
 *
 * @author Jose Maria
 */
public class ListaJugadoresJuegoTest extends TestCase {

    public ListaJugadoresJuegoTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getJugadores method, of class ListaJugadoresJuego.
     */
    public void testGetJugadores_int() {
        int n = 1;
        
        ListaJugadoresJuego instance = new ListaJugadoresJuego();
        List<JugadorJuego> jugadores = new ArrayList<>();
        JugadorJuego jugador = new JugadorJuego();
        jugador.setIdJugador(1);
        jugadores.add(jugador);
        instance.setJugadores(jugadores);
        
        List<JugadorJuego> result = instance.getJugadores(n);
        assertEquals(jugadores, result);
    }

}
