/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

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
public class CategoriasDAO {


    SessionFactory sf;
    Session sesion;
    Transaction tx;

    public CategoriasDAO(){
        this.sf=null;
        this.sesion=null;
        this.tx=null;
    }

    public CategoriasVO getCategorias(TrabajadorbbddVO trabajador){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();

            String consultaHQL="FROM CategoriasVO t WHERE t.nombreCategoria = :param1";
            Query query=sesion.createQuery(consultaHQL);
            query.setParameter("param1",trabajador.getCategorias().getNombreCategoria());

            //List <EmpresasVO> listaEmpresas=query.list();
            List <CategoriasVO> listaCategorias=query.list();

            sesion.close();
            return listaCategorias.get(0);



        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
        //sesion.close();
        return null;
    }
    
    
    public List <CategoriasVO> getAllCategorias(){
        try{
            sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();

            String consultaHQL="FROM CategoriasVO";
            Query query=sesion.createQuery(consultaHQL);
           
            List <CategoriasVO> listaCategorias=query.list();

            sesion.close();
            return listaCategorias;



        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
        sesion.close();
        return null;
    }
    
    public int updateSaveCategoria(CategoriasVO categoria){
        try{
            //sf=HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            this.tx=sesion.beginTransaction();
            sesion.saveOrUpdate(categoria);
            tx.commit();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            tx.rollback();
        }
        sesion.close();
        return -1;
    }
}