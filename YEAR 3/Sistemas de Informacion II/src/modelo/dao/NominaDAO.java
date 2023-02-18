/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.Date;
import java.util.List;
import modelo.vo.HibernateUtil;
import modelo.vo.NominaVO;
import modelo.vo.TrabajadorbbddVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author guill
 */
public class NominaDAO {
    
    SessionFactory sf;
    Session sesion;
    Transaction tx;
    
    public NominaDAO(){
        this.sf=null;
        this.sesion=null;
        this.tx=null;
    }
    
    public List<NominaVO> getNominaTrabajador(TrabajadorbbddVO trabajador){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            
            String consultaHQL="FROM NominaVO t WHERE t.trabajadorbbdd = :param1";
            Query query=sesion.createQuery(consultaHQL);
            query.setParameter("param1",trabajador);
            
            List <NominaVO> listaNominas=query.list();
            
            sesion.close();
            return listaNominas;
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public List<NominaVO> getNominaMinimoLiquido(){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            
            String consultaHQL="Select min(liquidoNomina) from NominaVO";
            Query query=sesion.createQuery(consultaHQL);
            
            
            List <NominaVO> listaNominas=query.list();
            
            sesion.close();
            return listaNominas;
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public List<NominaVO> getAllNomina(){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            
            String consultaHQL="FROM NominaVO";
            Query query=sesion.createQuery(consultaHQL);
            
            
            List <NominaVO> listaNominas=query.list();
            
            sesion.close();
            return listaNominas;
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public NominaVO getNomina(int mes,int year,TrabajadorbbddVO trabajador, double brutoMensual, double liquidoMensual){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            
            String consultaHQL="FROM NominaVO t WHERE t.mes = :param1 AND t.anio = :param2 AND t.trabajadorbbdd = :param3 AND t.brutoNomina = :param4 AND t.liquidoNomina = :param5";
            Query query=sesion.createQuery(consultaHQL);
            query.setParameter("param1",mes);
            query.setParameter("param2",year);
            query.setParameter("param3",trabajador);
            query.setParameter("param4",brutoMensual);
            query.setParameter("param5",liquidoMensual);
            
            List <NominaVO> listaNomina=query.list();
            
            if(!listaNomina.isEmpty()){
                sesion.close();
                return listaNomina.get(0);
            }
            
            //HibernateUtil.shutdown();
            
            
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
        sesion.close();
        return null;
    }
    
    public int updateSaveNomina(NominaVO nomina){
        try{
            //sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            this.tx=sesion.beginTransaction();
            sesion.saveOrUpdate(nomina);
            tx.commit();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            tx.rollback();
        }
        sesion.close();
        return -1;
    }
    
    public boolean deleteNomina (double mediaNomina){
        try{
            sesion=sf.openSession();
            tx=sesion.beginTransaction();
            
            String consultaHQL="DELETE NominaVO t WHERE t.brutoNomina < :param1";
            Query query=sesion.createQuery(consultaHQL);
            query.setParameter("param1",mediaNomina);
            
            query.executeUpdate();
            tx.commit();
            
            return true;
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return false;
    }
}
