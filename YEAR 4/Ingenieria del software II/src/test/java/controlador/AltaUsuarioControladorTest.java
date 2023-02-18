/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.xml.registry.infomodel.User;
import junit.framework.TestCase;
import modelo.Usuario;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Jose Maria
 */
public class AltaUsuarioControladorTest extends TestCase {
    
    public AltaUsuarioControladorTest(String testName) {
        super(testName);
    }

    @Test
    public void testMayorEdad() {
        AltaUsuarioControlador instance = new AltaUsuarioControlador();
        Usuario user = new Usuario();
        user.setFechaNacimiento("10/11/2000");
        assertTrue(instance.mayorEdad(user));
    }
    
    @Test
    public void testNoMayorEdad() {
        AltaUsuarioControlador instance = new AltaUsuarioControlador();
        Usuario user = new Usuario();
        user.setFechaNacimiento("10/11/2015");
        assertFalse(instance.mayorEdad(user));
    }
}
