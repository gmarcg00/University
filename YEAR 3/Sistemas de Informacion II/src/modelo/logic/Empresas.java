/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logic;


import java.util.List;
import modelo.dao.EmpresasDAO;
import modelo.vo.EmpresasVO;

/**
 *
 * @author guill
 */
public class Empresas {
    private EmpresasDAO empresa;
    public Empresas(){
        this.empresa=new EmpresasDAO();
    }
    
    /**
     *
     * @param cif
     * @return
     */
    public EmpresasVO getEmpresa(String cif){
        return this.empresa.getEmpresa(cif);
    }
    
    public List <EmpresasVO> getRestoEmpresas(String cif){
        return this.empresa.getRestoEmpresas(cif);
    }
    
    public int updateEmpresa(EmpresasVO empresa){
        return this.empresa.updateEmpresa(empresa);
    }
}
