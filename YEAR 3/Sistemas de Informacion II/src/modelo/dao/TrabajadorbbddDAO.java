/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.Date;
import java.util.List;
import modelo.vo.CategoriasVO;
import modelo.vo.EmpresasVO;
import modelo.vo.HibernateUtil;
import modelo.vo.TrabajadorbbddVO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author guill
 */
public class TrabajadorbbddDAO {
    
     SessionFactory sf;
     Session sesion;
     Transaction tx;
     
     public TrabajadorbbddDAO(){
         this.sf=null;
         this.sesion=null;
         this.tx=null;
     }
    
    public TrabajadorbbddVO getTrabajadorNIF(String nif){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            
            String consultaHQL="FROM TrabajadorbbddVO t WHERE t.nifnie = :param1";
            Query query=sesion.createQuery(consultaHQL);
            query.setParameter("param1",nif);
            
            List <TrabajadorbbddVO> listaTrabajador=query.list();
            
            if(!listaTrabajador.isEmpty()){
                sesion.close();
                return listaTrabajador.get(0);
            }
            
            HibernateUtil.shutdown();
            
            
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public List <TrabajadorbbddVO> getAllTrabajador(){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            
            String consultaHQL="FROM TrabajadorbbddVO";
            Query query=sesion.createQuery(consultaHQL);
           
            
            List <TrabajadorbbddVO> listaTrabajador=query.list();
            
            if(!listaTrabajador.isEmpty()){
                sesion.close();
                return listaTrabajador;
            }
            
            //HibernateUtil.shutdown();
            
            
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
        sesion.close();
        return null;
    }
    
    
    
    public TrabajadorbbddVO getTrabajador(String nif,String nombre,Date fechaAlta){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            
            String consultaHQL="FROM TrabajadorbbddVO t WHERE t.nifnie = :param1 AND t.nombre = :param2 AND t.fechaAlta = :param3";
            Query query=sesion.createQuery(consultaHQL);
            query.setParameter("param1",nif);
            query.setParameter("param2",nombre);
            query.setParameter("param3",fechaAlta);
            
            List <TrabajadorbbddVO> listaTrabajador=query.list();
            
            if(!listaTrabajador.isEmpty()){
                sesion.close();
                return listaTrabajador.get(0);
            }
            
            //HibernateUtil.shutdown();
            
            
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
        sesion.close();
        return null;
    }
    
    public int updateSaveTrabajador(TrabajadorbbddVO trabajador){
        try{
            //sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            this.tx=sesion.beginTransaction();
            sesion.saveOrUpdate(trabajador);
            tx.commit();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            tx.rollback();
        }
        sesion.close();
        return -1;
    }
    
    public List<TrabajadorbbddVO> getTrabajadorEmpresa(EmpresasVO empresa){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            
            String consultaHQL="FROM TrabajadorbbddVO t WHERE t.empresas = :param1";
            Query query=sesion.createQuery(consultaHQL);
            query.setParameter("param1",empresa);
           
            
            List <TrabajadorbbddVO> listaTrabajador=query.list();
            
            
            
            if(!listaTrabajador.isEmpty()){
                sesion.close();
                return listaTrabajador;
            }else{
                return null;
            }
            
            
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    
}
