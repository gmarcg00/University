/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.vo.EmpresasVO;
import modelo.vo.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author guill
 */
public class EmpresasDAO {
    
    SessionFactory sf;
    Session sesion;
    Transaction tx;
    
    public EmpresasDAO(){
        this.sf=null;
        this.sesion=null;
        this.tx=null;
    }
    
    public EmpresasVO getEmpresa(String cif){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            
            String consultaHQL="FROM EmpresasVO t WHERE t.cif = :param1";
            Query query=sesion.createQuery(consultaHQL);
            query.setParameter("param1",cif);
            
            List <EmpresasVO> listaEmpresas=query.list();
            
            
            if(!listaEmpresas.isEmpty()){
                sesion.close();
                return listaEmpresas.get(0);
            }

        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
        
        sesion.close();
        return null;
    }
    
    public List<EmpresasVO> getRestoEmpresas(String cif){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();

            String consultaHQL="FROM EmpresasVO t WHERE t.cif != :param1";
            Query query=sesion.createQuery(consultaHQL);
            query.setParameter("param1",cif);
            
            List <EmpresasVO> listaEmpresas=query.list();
            
            if(!listaEmpresas.isEmpty()){
                sesion.close();
                return listaEmpresas;
            }
            
            
            //HibernateUtil.shutdown();
            
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public int updateEmpresa(EmpresasVO empresa){
        try{
            //sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            this.tx=sesion.beginTransaction();
            sesion.saveOrUpdate(empresa);
            tx.commit();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            tx.rollback();
        }
        sesion.close();
        return -1;
    }
}
