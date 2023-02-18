/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import modelo.EquipoJuego;
import modelo.Liga;

/**
 *
 * @author Jose Maria
 */
public class ListaEquiposJuegoTest extends TestCase {
    
    public ListaEquiposJuegoTest(String testName) {
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
     * Test of getEquiposLiga method, of class ListaEquiposJuego.
     */
    public void testGetEquiposLiga() {
        ListaEquiposJuego instance = new ListaEquiposJuego();
        
        EquipoJuego madrid = new EquipoJuego();
        madrid.setNombre("Madrid");
        Liga madrilena = new Liga();
        madrilena.setNombre("Madrilena");
        madrid.setNombreLiga(madrilena);
        
        EquipoJuego barca = new EquipoJuego();
        barca.setNombre("Barcelona");
        Liga catalana = new Liga();
        catalana.setNombre("Catalana");
        barca.setNombreLiga(catalana);
        
        List<EquipoJuego> equipos = new ArrayList<>();
        equipos.add(barca);
        equipos.add(madrid);
        instance.setEquipos(equipos);
        
        instance.setLiga(catalana);
        
        assertEquals("Catalana", instance.equiposLiga().get(0).getNombreLiga().getNombre());

    }

    
    
}
