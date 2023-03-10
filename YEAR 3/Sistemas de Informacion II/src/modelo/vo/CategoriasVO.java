package modelo.vo;
// Generated 28-feb-2022 19:22:05 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Categorias generated by hbm2java
 */
public class CategoriasVO  implements java.io.Serializable {

     private int idCategoria;
     private String nombreCategoria;
     private Double salarioBaseCategoria;
     private Double complementoCategoria;

    public CategoriasVO(int idCategoria, String nombreCategoria, Double salarioBaseCategoria, Double complementoCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.salarioBaseCategoria = salarioBaseCategoria;
        this.complementoCategoria = complementoCategoria;
    }
     private Set trabajadorbbdds = new HashSet(0);

    public CategoriasVO() {
    }

	
    public CategoriasVO(int idCategoria,String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria=nombreCategoria;
    }
    public CategoriasVO(int idCategoria, String nombreCategoria, Double salarioBaseCategoria, Double complementoCategoria, Set trabajadorbbdds) {
       this.idCategoria = idCategoria;
       this.nombreCategoria = nombreCategoria;
       this.salarioBaseCategoria = salarioBaseCategoria;
       this.complementoCategoria = complementoCategoria;
       this.trabajadorbbdds = trabajadorbbdds;
    }
   
    public int getIdCategoria() {
        return this.idCategoria;
    }
    
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    public String getNombreCategoria() {
        return this.nombreCategoria;
    }
    
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    public Double getSalarioBaseCategoria() {
        return this.salarioBaseCategoria;
    }
    
    public void setSalarioBaseCategoria(Double salarioBaseCategoria) {
        this.salarioBaseCategoria = salarioBaseCategoria;
    }
    public Double getComplementoCategoria() {
        return this.complementoCategoria;
    }
    
    public void setComplementoCategoria(Double complementoCategoria) {
        this.complementoCategoria = complementoCategoria;
    }
    public Set getTrabajadorbbdds() {
        return this.trabajadorbbdds;
    }
    
    public void setTrabajadorbbdds(Set trabajadorbbdds) {
        this.trabajadorbbdds = trabajadorbbdds;
    }




}


