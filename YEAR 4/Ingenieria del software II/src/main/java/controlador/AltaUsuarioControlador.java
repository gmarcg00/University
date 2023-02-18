/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.EquipoJuegoFacadeLocal;
import EJB.LigaFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;
import modelo.Enum;
import java.util.regex.*;
import modelo.EquipoJuego;
import modelo.Liga;

/**
 *
 * @author Jose Maria
 */
@Named
@ViewScoped

public class AltaUsuarioControlador implements Serializable {

    private Usuario use;
    private Liga liga;

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    @EJB
    private LigaFacadeLocal ligaEJB;

    @EJB
    private EquipoJuegoFacadeLocal equipoEJB;

    @PostConstruct
    public void init() {
        use = new Usuario();
        liga = new Liga();
    }

    public void insertar() throws SQLIntegrityConstraintViolationException {
        String nombrePropio = "[A-Z]+[a-z]{1,73}";
        String nombreCompuesto = nombrePropio + " " + nombrePropio;
        String ID = "[a-zA-Z_]+[a-zA-Z0-9_]*";
        String dominio = "(([A-Za-z]([A-Za-z\\d-]{1,61}[A-Za-z\\d])*[.])+[A-Za-z]{1}[A-Za-z0-9-]{1,62})+$";
        String correo = "[A-Za-z0-9+_.-]+[@]{1}".concat(dominio);
        String fechaNacimiento = "\\d{2}/\\d{2}/\\d{4}";
        String contraseña = ".{8,50}";
        try {
            if (Pattern.matches(nombrePropio, use.getNombre()) || Pattern.matches(nombreCompuesto, use.getNombre())) {
                if (Pattern.matches(ID, use.getNombreUsuario())) {
                    if (Pattern.matches(fechaNacimiento, use.getFechaNacimiento())) {
                        if (Pattern.matches(contraseña, use.getContraseña())) {
                            if (Pattern.matches(correo, use.getEmail())) {
                                if (use.getApellido1() == null && use.getApellido2() == null) {
                                    usuarioEJB.create(use);
                                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Done", "Usuario registrado"));
                                } else if (use.getApellido1() != null && use.getApellido2() != null) {
                                    if (Pattern.matches(nombrePropio, use.getApellido1()) && Pattern.matches(nombrePropio, use.getApellido2())) {
                                        usuarioEJB.create(use);
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Done", "Usuario registrado"));
                                        resetUse();
                                    } else if (Pattern.matches(nombrePropio, use.getApellido1())) {
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Segundo apellido erroneo: (Xxxxx)"));
                                    } else {
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Primer apellido erroneo: (Xxxxx)"));
                                    }
                                } else if (use.getApellido1() != null && use.getApellido2() == null) {
                                    if (Pattern.matches(nombrePropio, use.getApellido1())) {
                                        usuarioEJB.create(use);
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + use.getNombreUsuario()));
                                        resetUse();
                                    } else {
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Primer apellido erroneo: (Xxxxx)"));
                                    }
                                } else if (use.getApellido2() != null && use.getApellido1() == null) {
                                    if (Pattern.matches(nombrePropio, use.getApellido2())) {
                                        usuarioEJB.create(use);
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + use.getNombreUsuario()));
                                        resetUse();
                                    } else {
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Primer apellido erroneo: (Xxxxx)"));
                                    }
                                }
                            } else {
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Email erroneo: (xxxx@x.x)"));
                            }
                        } else {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Contraseña erroneo: Debe tener al menos 8 caracteres"));
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fecha de Nacimiento erronea:(DD/MM/YYYY)"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Nombre usuario erroneo"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Nombre erroneo: (Xxxxx)"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre de usuario ya existe, prueba con otro"));
        }

    }

    public String pedir() {
        List<Usuario> users = usuarioEJB.findAll();
        boolean autenticado = false;
        int contador = 0;

        while (!autenticado && contador < users.size()) {
            if (use.getNombreUsuario().equals(users.get(contador).getNombreUsuario()) && use.getContraseña().equals(users.get(contador).getContraseña())) {
                autenticado = true;
                use = users.get(contador);
            }
            contador++;
        }

        if (autenticado == true) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", use);
            if (use.getNombreLiga() != null) {
                liga = ligaEJB.find(use.getNombreLiga().getNombre());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("liga", liga);

                List<EquipoJuego> equipos = equipoEJB.findAll();
                int i = 0;
                boolean encontrado = false;

                while (!encontrado && i < equipos.size()) {
                    if (use.getNombre().equals(equipos.get(i).getNombreUsuario().getNombre())) {
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("miequipo", equipos.get(i));
                        encontrado = true;
                    }
                    i++;
                }
                return "vista/privado/usuario/paginaPrincipal.xhtml?faces-redirect=true";
            } else {
                return "vista/privado/usuario/ingresoLiga.xhtml?faces-redirect=true";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre de usuario y la contraseña no coinciden"));
            return "index.xhtml";
        }
    }

    public void resetUse() {
        use.setApellido1("");
        use.setApellido2("");
        use.setEmail("");
        use.setFechaNacimiento("");
        use.setNombre("");
        use.setNombreUsuario("");
        use.setSexo(null);
    }
    
    public boolean mayorEdad(Usuario user){
        String ano = user.getFechaNacimiento().substring(6, 10);
        int edad = 2022 - Integer.parseInt(ano);
        return edad >=18;
    }

    public Usuario getUse() {
        return use;
    }

    public void setUse(Usuario use) {
        this.use = use;
    }

    public void usuarioM() {
        use.setSexo(Enum.M);
    }

    public void usuarioF() {
        use.setSexo(Enum.F);
    }

}
