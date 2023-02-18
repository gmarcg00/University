package modelo.vo;
// Generated 28-feb-2022 19:22:05 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Empresas generated by hbm2java
 */
public class EmpresasVO  implements java.io.Serializable {


     private int idEmpresa;
     private String nombre;
     private String cif;

    public EmpresasVO(int idEmpresa, String nombre, String cif) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.cif = cif;
    }
     private Set trabajadorbbdds = new HashSet(0);

    public EmpresasVO() {
    }

	
    public EmpresasVO(int idEmpresa,String nombre) {
        this.idEmpresa = idEmpresa;
        this.nombre=nombre;
    }
    public EmpresasVO(int idEmpresa, String nombre, String cif, Set trabajadorbbdds) {
       this.idEmpresa = idEmpresa;
       this.nombre = nombre;
       this.cif = cif;
       this.trabajadorbbdds = trabajadorbbdds;
    }
   
    public int getIdEmpresa() {
        return this.idEmpresa;
    }
    
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCif() {
        return this.cif;
    }
    
    public void setCif(String cif) {
        this.cif = cif;
    }
    public Set getTrabajadorbbdds() {
        return this.trabajadorbbdds;
    }
    
    public void setTrabajadorbbdds(Set trabajadorbbdds) {
        this.trabajadorbbdds = trabajadorbbdds;
    }




}

