/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logic;

import java.util.List;
import modelo.dao.NominaDAO;
import modelo.vo.NominaVO;
import modelo.vo.TrabajadorbbddVO;

/**
 *
 * @author guill
 */
public class Nomina {
    
    private NominaDAO nomina;
    
    public Nomina(){
        this.nomina=new NominaDAO();
    }
    
    public List<NominaVO> getNominaTrabajador(TrabajadorbbddVO trabajador){
        return this.nomina.getNominaTrabajador(trabajador);
    }
    
    public List<NominaVO> getNominaMinimo(){
        return this.nomina.getNominaMinimoLiquido();
    }
    
    public List<NominaVO> getAll(){
        return this.nomina.getAllNomina();
    }
   
    
    public NominaVO getNomina(int mes, int year, TrabajadorbbddVO trabajador, double brutoMensual, double liquidoMensual){
        return this.nomina.getNomina(mes, year, trabajador, brutoMensual, liquidoMensual);
    }
    
    public int saveUpdateNomina(NominaVO nomina){
        return this.nomina.updateSaveNomina(nomina);
    }
    
    public boolean deleteNomina(double mediaNomina){
        return this.nomina.deleteNomina(mediaNomina);
    }
}
