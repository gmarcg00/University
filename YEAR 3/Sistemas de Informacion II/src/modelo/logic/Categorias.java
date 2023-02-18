/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logic;

import java.util.List;
import modelo.dao.CategoriasDAO;
import modelo.vo.CategoriasVO;
import modelo.vo.TrabajadorbbddVO;

/**
 *
 * @author guill
 */
public class Categorias {

    private CategoriasDAO categoria;

    public Categorias(){

        categoria = new CategoriasDAO();
    }

    public CategoriasVO getCategorias(TrabajadorbbddVO trabajador){
        return categoria.getCategorias(trabajador);
    }
    
    public List<CategoriasVO> getAllCategorias(){
        return categoria.getAllCategorias();
    }
    
    public int saveUpdateCategoria(CategoriasVO categoria){
        return this.categoria.updateSaveCategoria(categoria);
    }

}