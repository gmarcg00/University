/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.logic.Categorias;
import modelo.logic.Empresas;
import modelo.logic.ExcelManager;
import modelo.logic.Nomina;
import modelo.logic.NominasManager;
import modelo.logic.PDFManager;
import modelo.logic.Trabajadorbbdd;
import modelo.vo.CategoriasVO;
import modelo.vo.EmpresasVO;
import modelo.vo.NominaVO;
import modelo.vo.TrabajadorbbddVO;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import vista.JFrameInicio;


/**
 *
 * @author guill
 */
public class ControladorInicio implements ActionListener{
    
    private final JFrameInicio vista;
    private final Empresas modeloEmpresa;
    private final Nomina modeloNomina;
    private final Categorias modeloCategoria;
    private EmpresasVO empresa;
    private NominaVO nomina;
    private TrabajadorbbddVO trabajador;
    private CategoriasVO categoria;
    private final Trabajadorbbdd modeloTrabajador;
    private List <TrabajadorbbddVO> listaTrabajadores;
    private List <TrabajadorbbddVO> listaTrabajadoresExamen;
    private List <NominaVO> listaNominasExamen;
    private List <CategoriasVO> listaCategoriasExamen;
    private List <CategoriasVO> listaCategoriasExamenC;
    private List <Integer> listaRepeticionesCategoria;
    private int contadorCategorias[];
    private int contadorCategoriasC[];
    private List <EmpresasVO> listaEmpresas;
    private List <NominaVO> listaNominas;
    private List <NominaVO> listaNominaMinimo;
    private final List<List<NominaVO>> listaNominasTrabajador;
    private List<TrabajadorbbddVO> listaTrabajadoresNomina;
    private List<String[][]> listaHojasExcel;
    private String[][] matrizHoja;
    private int contador;
    
    
    
    public ControladorInicio(JFrameInicio ventana) {
        this.listaNominasTrabajador = new ArrayList<>();
        this.listaEmpresas = new ArrayList<>();
        this.listaTrabajadores = new ArrayList<>();
        this.listaTrabajadoresExamen = new ArrayList<>();
        this.listaNominaMinimo = new ArrayList<>();
        this.listaRepeticionesCategoria = new ArrayList<>();
        this.listaCategoriasExamen = new ArrayList<>();
        this.listaCategoriasExamenC = new ArrayList<>();
        this.listaNominasExamen = new ArrayList<>();
        this.listaTrabajadoresNomina = new ArrayList<>();
        this.listaHojasExcel=new ArrayList<>();
        this.modeloTrabajador = new Trabajadorbbdd();
        this.modeloEmpresa = new Empresas();
        this.empresa = new EmpresasVO();
        this.nomina = new NominaVO();
        this.modeloCategoria = new Categorias();
        this.modeloNomina = new Nomina();
        this.vista=ventana;
        
        this.
        vista.getjButtonNominas().setEnabled(false);
        
    }
    
    public void setActionBoton(){
        vista.getjButtonBuscar().addActionListener(this);
        vista.getjButtonIzquierda().addActionListener(this);
        vista.getjButtonDerecha().addActionListener(this);
        vista.getjButtonExcel().addActionListener(this);
        vista.getjButtonNominas().addActionListener(this);
        vista.getjButtonAyuda().addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        
        if(e.getSource().equals(vista.getjButtonBuscar())){
            empresa=modeloEmpresa.getEmpresa(vista.getjTextFieldCIF().getText());
            vista.getjButtonIzquierda().setEnabled(true);
            vista.getjButtonDerecha().setEnabled(true);
            limpiarListas();
            if(empresa!=null){
                //DATOS EMPRESA
                vista.getJLabelTextoEmpresa().setText(empresa.getNombre());
                vista.getJLabelTextoCIF().setText(empresa.getCif());
             
                //DATOS TRABAJADOR
                listaTrabajadores=modeloTrabajador.getTrabajadorEmpresa(empresa);
                if(listaTrabajadores!=null){
                    contador=0;
                    vista.getjButtonIzquierda().setEnabled(false);

                   // APARTADO 2
                   listaEmpresas=modeloEmpresa.getRestoEmpresas(empresa.getCif());
                   updateEmpresas(listaEmpresas);

                  //APARTADO 3
                    getNominasEmpresa(listaTrabajadores);
                    double media=calculaMedia();
                    BigDecimal bd = new BigDecimal(String.valueOf(media));
                    BigDecimal rounded = bd.setScale(2,RoundingMode.FLOOR);
                    vista.getJLabeTextoBrutoNomina().setText(String.valueOf(rounded+"€"));
                    modeloNomina.deleteNomina(calculaMedia());
                    actualizaDatos();
                }else{
                    vista.showMessageEmpresaTrabajadores();
                    vista.getjButtonIzquierda().setEnabled(false);
                    vista.getjButtonDerecha().setEnabled(false);
                }
                
            }else{
                //NO EXISTE EMPRESA CON EL CIF INTRODUCIDO
                vista.showMessageEmpresaCIF();
            }
        }
        
        if(e.getSource().equals(vista.getjButtonExcel())){
            String path="SistemasInformacionII.xlsx";
            try {
                Workbook libro=WorkbookFactory.create(new File(path));
                ExcelManager excel=new ExcelManager(libro);
                
                leeHojasExcel(excel);
                matrizHoja=listaHojasExcel.get(4);
                
                //DNI
                matrizHoja=excel.separaLetraDNI(matrizHoja);
                excel.emptyDNI(matrizHoja);
                excel.isRepetido(matrizHoja);
                List<TrabajadorbbddVO> listaTrabajadoresError=excel.getListaErrores();
                excel.escribirExcel(matrizHoja);
                
                //XML ERRORES NIF
                excel.escribeXMLNIF();
                
               
                //CODIGO CUENTA
                matrizHoja=excel.compruebaCC(matrizHoja);
                
                //IBAN
                matrizHoja=excel.generaIBAN(matrizHoja);
                excel.añadeIbanTrabajadoresErroneos(matrizHoja);
                
                //XML ERRORES CCC
                excel.escribeXMLCCC();
                
                //EMAIL
                excel.generaEmailSinRepeticion(matrizHoja);
                matrizHoja=excel.compruebaEmail(matrizHoja);
                
                excel.escribirExcel(matrizHoja);
                vista.showMessageFicherosGenerados();
                
                vista.getjButtonNominas().setEnabled(true);
                
                
            } catch (IOException | EncryptedDocumentException ex) {
                Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        if(e.getSource().equals(vista.getjButtonIzquierda())){
            contador--;
            actualizaDatos();
            compruebaBotones();
            
        } 
        
        if(e.getSource().equals(vista.getjButtonDerecha())){
            contador++;
            actualizaDatos();
            compruebaBotones();
        }
        
        if(e.getSource().equals(vista.getjButtonNominas())){
            try {
                String mes=vista.getjTextFieldNominas().getText();
                String path="SistemasInformacionII.xlsx";
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                
                Workbook libro=WorkbookFactory.create(new File(path));
                ExcelManager excel=new ExcelManager(libro);
                
                //COMPROBAMOS SI HAY QUE GENERAR NOMINA
                listaTrabajadoresNomina=excel.isNomina(matrizHoja, mes);

                NominasManager nominasManager=new NominasManager(listaHojasExcel,listaTrabajadoresNomina,mes);
                listaNominas=nominasManager.calculaNominas();
                
                
                //imprimeNominas();
                
                //generaPDF();
                actualizaBaseDatos();
                nominasManager.escribeXmlNominas(listaNominas);
                
                PDFManager pdf=new PDFManager("resources\\Examen_Guillermo_Javier.pdf");
                
                
                //EXAMEN PRIMERA CONVOCATORIA
                
                /*
                
                listaTrabajadoresExamen=modeloTrabajador.getAll();
               
                getMayorCategoria(listaCategoriasExamen);
                
                
                System.out.println("");
                
                pdf.pdfExamen(indiceMenor, indiceMayor, contadorCategorias, listaCategoriasExamen);
                
                //this.listaNominasExamen=modeloNomina.getAll();
                

                */
                
                
                //Examen SEGUNDA CONVOCATORIA
                
                listaTrabajadoresExamen=modeloTrabajador.getAll();
                listaCategoriasExamen=modeloCategoria.getAllCategorias();
                listaNominasExamen=modeloNomina.getAll();
                
                contadorCategorias=new int[listaCategoriasExamen.size()];
                
                for(int i=0;i<listaTrabajadoresExamen.size();i++){
                    for(int j=0;j<listaCategoriasExamen.size();j++){
                         if(listaTrabajadoresExamen.get(i).getCategorias().getIdCategoria()==listaCategoriasExamen.get(j).getIdCategoria()){
                            this.contadorCategorias[j]++;
                            
                        }   
                    }  
                }
                
                
                for(int i=0;i<listaCategoriasExamen.size();i++){
                    if(contadorCategorias[i]>=31 && contadorCategorias[i]<=60){
                        listaCategoriasExamenC.add(listaCategoriasExamen.get(i));
                        listaRepeticionesCategoria.add(contadorCategorias[i]);
                        //contadorCategoriasC[i]=contadorCategorias[i];
                    }
                }
                
               
                listaCategoriasExamen=modeloCategoria.getAllCategorias();
                this.contadorCategorias=new int [listaCategoriasExamen.size()];
                
                pdf.pdfExamenSegunda(listaCategoriasExamenC, listaRepeticionesCategoria);
                
                //APARTADO 2 SEGUNDA CONVOCATORIA
                
                
                
                
                
                vista.showMessageNominasGeneradas();
                
            } catch (IOException ex) {
                Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EncryptedDocumentException ex) {
                Logger.getLogger(ControladorInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getSource().equals(vista.getjButtonAyuda())){
            vista.showMessageAyuda();
        }
        
        
    }
    
    
    
    public void getMayorCategoria(List<CategoriasVO> listaCategorias){
        for(int i=0;i<listaTrabajadoresExamen.size();i++){
            for(int j=0;j<listaCategorias.size();j++){
                if(listaTrabajadoresExamen.get(i).getCategorias().getIdCategoria()==listaCategorias.get(j).getIdCategoria()){
                   this.contadorCategorias[j]++;
                }
            }
        }
    }
    
    public int buscaIndiceMayor(int arrayCategorias[]){
        int max=-1;
        int indice=-1;
        for(int i=0;i<arrayCategorias.length;i++){
            if(arrayCategorias[i]>max){
                max=arrayCategorias[i];
                indice=i;
            }
         }
        return indice;
    }
    
    public int buscaIndiceMenor(int arrayCategorias[]){
        int min=Integer.MAX_VALUE;
        int indice=-1;
        for(int i=0;i<arrayCategorias.length;i++){
            if(arrayCategorias[i]<min){
                min=arrayCategorias[i];
                indice=i;
            }
         }
        return indice;
    }
    
    public void rellenaContadorCategorias(){
        
    }
    
    public void actualizaBaseDatos(){
        for(int i=0;i<listaNominas.size();i++){
            Date fecha=listaNominas.get(i).getTrabajadorbbdd().getFechaAlta();
            empresa=modeloEmpresa.getEmpresa(listaNominas.get(i).getTrabajadorbbdd().getEmpresas().getCif());
            categoria=modeloCategoria.getCategorias(listaNominas.get(i).getTrabajadorbbdd());
            trabajador=modeloTrabajador.getTrabajador(listaNominas.get(i).getTrabajadorbbdd().getNifnie(),listaNominas.get(i).getTrabajadorbbdd().getNombre(),fecha);
            nomina=modeloNomina.getNomina(listaNominas.get(i).getMes(),listaNominas.get(i).getAnio(),listaNominas.get(i).getTrabajadorbbdd(),listaNominas.get(i).getBrutoNomina(),listaNominas.get(i).getLiquidoNomina());


            if(empresa!=null){
                 modeloCategoria.saveUpdateCategoria(listaNominas.get(i).getTrabajadorbbdd().getCategorias());
                 modeloTrabajador.saveUpdateTrabajador(listaNominas.get(i).getTrabajadorbbdd());
                 modeloNomina.saveUpdateNomina(listaNominas.get(i));
            }else{
                 modeloEmpresa.updateEmpresa(listaNominas.get(i).getTrabajadorbbdd().getEmpresas());
                 modeloCategoria.saveUpdateCategoria(listaNominas.get(i).getTrabajadorbbdd().getCategorias());
                 modeloTrabajador.saveUpdateTrabajador(listaNominas.get(i).getTrabajadorbbdd());
                 modeloNomina.saveUpdateNomina(listaNominas.get(i));
            }

        }
    }
    
    public void generaPDF(){
        for(int i=0;i<listaNominas.size();i++){
            if(listaNominas.get(i).isExtra()){
                if(listaNominas.get(i).getMes()==6){
                    PDFManager pdf=new PDFManager("resources\\nominas"+listaNominas.get(i).getTrabajadorbbdd().getNifnie()+listaNominas.get(i).getTrabajadorbbdd().getNombre()+" "+listaNominas.get(i).getTrabajadorbbdd().getApellido1()+"JunioExtra.pdf");
                    pdf.newPdf(listaNominas.get(i));
                }else{
                    PDFManager pdf=new PDFManager("resources\\nominas"+listaNominas.get(i).getTrabajadorbbdd().getNifnie()+listaNominas.get(i).getTrabajadorbbdd().getNombre()+" "+listaNominas.get(i).getTrabajadorbbdd().getApellido1()+"DiciembreExtra.pdf");
                    pdf.newPdf(listaNominas.get(i));

                }
                
            }else{
                PDFManager pdf=new PDFManager("resources\\nominas"+listaNominas.get(i).getTrabajadorbbdd().getNifnie()+listaNominas.get(i).getTrabajadorbbdd().getNombre()+" "+listaNominas.get(i).getTrabajadorbbdd().getApellido1()+".pdf");
                pdf.newPdf(listaNominas.get(i));
            }
            
        }
    }
    
   
    
    public void updateEmpresas(List<EmpresasVO> empresas){
        String anio="2022";
        for(int i=0;i<empresas.size();i++){
            empresas.get(i).setNombre(empresas.get(i).getNombre()+anio);
            modeloEmpresa.updateEmpresa(empresas.get(i));
            
        }
    }
    
    
    public void getNominasEmpresa(List<TrabajadorbbddVO> trabajador){
        for(int i=0;i<trabajador.size();i++){
            this.listaNominasTrabajador.add(modeloNomina.getNominaTrabajador(trabajador.get(i)));
        }
    }
    
    public double calculaMedia(){
        double suma=0.0;
        int tam=0;
        for(int i=0;i<this.listaNominasTrabajador.size();i++){
            for(int j=0;j<this.listaNominasTrabajador.get(i).size();j++){
                suma+=this.listaNominasTrabajador.get(i).get(j).getBrutoNomina();
                tam++;
            }
        }
        
        if(tam==0){
            return 0;
        }else{
            return suma/tam;
        }
    }
    
    public void leeHojasExcel(ExcelManager excel){
        for(int i=0;i<5;i++){
            listaHojasExcel.add(excel.getHojaExcel(i));
        }
    }
    
    public void actualizaDatos(){
        vista.getJLabeTextoNombre().setText(listaTrabajadores.get(contador).getNombre());
        vista.getJLabeTextoApellidos().setText(listaTrabajadores.get(contador).getApellido1()+" "+listaTrabajadores.get(contador).getApellido2());
        vista.getJLabeTextoNIF().setText(listaTrabajadores.get(contador).getNifnie());
        vista.getJLabeTextoCategoria().setText(modeloCategoria.getCategorias(listaTrabajadores.get(contador)).getNombreCategoria());
        vista.getJLabeTextoNumNominas().setText(String.valueOf(listaNominasTrabajador.get(contador).size()));
    }
    
    public void imprimeNominas(){
        System.out.println("||||||||| NOMINAS |||||||||");
        for(int i=0;i<listaNominas.size();i++){
            System.out.println("Nomina:"+listaNominas.get(i).getIdNomina());
            System.out.println("Nombre:"+listaNominas.get(i).getTrabajadorbbdd().getNombre());
            System.out.println("Apellidos:"+listaNominas.get(i).getTrabajadorbbdd().getApellido1()+" "+listaNominas.get(i).getTrabajadorbbdd().getApellido2());
            System.out.println("NIF:"+listaNominas.get(i).getTrabajadorbbdd().getNifnie());
            System.out.println("Categoria:"+listaNominas.get(i).getTrabajadorbbdd().getCategorias().getNombreCategoria());
            System.out.println("Mes:"+listaNominas.get(i).getMes());
            System.out.println("Año:"+listaNominas.get(i).getAnio());
            System.out.println("Numero de Trienios:"+listaNominas.get(i).getNumeroTrienios());
            System.out.println("Importe Trienios:"+listaNominas.get(i).getImporteTrienios());
            System.out.println("Importe Salario Mes:"+listaNominas.get(i).getImporteSalarioMes());
            System.out.println("Importe Complemento Mes:"+listaNominas.get(i).getImporteComplementoMes());
            System.out.println("Valor prorrateo:"+listaNominas.get(i).getValorProrrateo());
            System.out.println("Bruto Anual:"+listaNominas.get(i).getBrutoAnual());
            System.out.println("IRPF:"+listaNominas.get(i).getIrpf());
            System.out.println("Importe IRPF:"+listaNominas.get(i).getImporteIrpf());
            System.out.println("Base empresario:"+listaNominas.get(i).getBaseEmpresario());
            System.out.println("Seguridad Social Empresario:"+listaNominas.get(i).getSeguridadSocialEmpresario());
            System.out.println("Importe Seguridad Social Empresario:"+listaNominas.get(i).getImporteSeguridadSocialEmpresario());
            System.out.println("Desempleo Empresario:"+listaNominas.get(i).getDesempleoEmpresario());
            System.out.println("Importe Desempleo Empresario:"+listaNominas.get(i).getImporteDesempleoEmpresario());
            System.out.println("Formación Empresario:"+listaNominas.get(i).getFormacionEmpresario());
            System.out.println("Importe Formación Empresario:"+listaNominas.get(i).getImporteFormacionEmpresario());
            System.out.println("Accidentes Trabajo Empresario:"+listaNominas.get(i).getAccidentesTrabajoEmpresario());
            System.out.println("Importe Accidentes Trabajo Empresario:"+listaNominas.get(i).getImporteAccidentesTrabajoEmpresario());
            System.out.println("Fogasa:"+listaNominas.get(i).getFogasaempresario());
            System.out.println("Importe Fogasa:"+listaNominas.get(i).getImporteFogasaempresario());
            System.out.println("Seguridad Social:"+listaNominas.get(i).getSeguridadSocialTrabajador());
            System.out.println("Importe Seguridad Social:"+listaNominas.get(i).getImporteSeguridadSocialTrabajador());
            System.out.println("Desempleo:"+listaNominas.get(i).getDesempleoTrabajador());
            System.out.println("Importe Desempleo:"+listaNominas.get(i).getImporteDesempleoTrabajador());
            System.out.println("Formación:"+listaNominas.get(i).getFormacionTrabajador());
            System.out.println("Importe Formación:"+listaNominas.get(i).getImporteFormacionTrabajador());
            System.out.println("Bruto Nomina:"+listaNominas.get(i).getBrutoNomina());
            System.out.println("Liquido Nomina:"+listaNominas.get(i).getLiquidoNomina());
            System.out.println("Coste Total Empresario:"+listaNominas.get(i).getCosteTotalEmpresario());
            System.out.println("");
            System.out.println("");
            System.out.println("");
        }
    }
    
    public void compruebaBotones(){
        if(this.contador==0){
            vista.getjButtonIzquierda().setEnabled(false);
        }else{
            vista.getjButtonIzquierda().setEnabled(true);
        }
        
        if(this.contador==listaTrabajadores.size()-1){
            vista.getjButtonDerecha().setEnabled(false);
        }else{
            vista.getjButtonDerecha().setEnabled(true);
        }
    }
    
    public void limpiarListas(){
        listaTrabajadores.clear();
        listaEmpresas.clear();
        listaNominasTrabajador.clear();
    }
    
    
}
