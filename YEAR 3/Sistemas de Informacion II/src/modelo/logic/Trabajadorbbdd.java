/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logic;

import java.util.Date;
import java.util.List;
import modelo.dao.TrabajadorbbddDAO;
import modelo.vo.CategoriasVO;
import modelo.vo.EmpresasVO;
import modelo.vo.TrabajadorbbddVO;

/**
 *
 * @author guill
 */
public class Trabajadorbbdd {
    
    private TrabajadorbbddDAO trabajador;
    
    public Trabajadorbbdd(){
        this.trabajador=new TrabajadorbbddDAO();
    }

    
    public TrabajadorbbddVO getTrabajadorNIF(String nif){
        return trabajador.getTrabajadorNIF(nif);
    }
    
    public TrabajadorbbddVO getTrabajador(String nif,String nombre, Date fechaAlta){
        return trabajador.getTrabajador(nif,nombre,fechaAlta);
    }
    
    public List<TrabajadorbbddVO> getTrabajadorEmpresa(EmpresasVO empresa){
        return trabajador.getTrabajadorEmpresa(empresa);
    }
    
    public List<TrabajadorbbddVO> getAll(){
        return trabajador.getAllTrabajador();
    }
    
    public int saveUpdateTrabajador(TrabajadorbbddVO trabajador){
        return this.trabajador.updateSaveTrabajador(trabajador);
    }
    
}
