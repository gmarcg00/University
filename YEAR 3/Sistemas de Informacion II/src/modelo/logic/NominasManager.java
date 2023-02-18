/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.vo.CategoriasVO;
import modelo.vo.NominaVO;
import modelo.vo.TrabajadorbbddVO;
import modelo.logic.CambioTrienio;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author guill
 */
public class NominasManager {
    private List<String[][]> listaHojasExcel;
    private List<TrabajadorbbddVO> listaTrabajadores;
    private List<CategoriasVO> listaCategorias;
    private List<ArrayList<Double>> listaValoresIRPF;
    private List<NominaVO> listaNominas;
    private List<ArrayList<Integer>> listaValoresTrienios;
    private final int indiceNombreCategoria=0;
    private final int indiceComplementos=1;
    private final int indiceSalarioBase=2;
    private final int indiceValores=1;
    private final int indiceSeguridadSocial=5;
    private final int indiceFormacion=7;
    private final int indiceDesempleo=6;
    private final int indiceFormacionEmpresario=4;
    private final int indiceDesempleoEmpresario=3;
    private final int indiceFogasaEmpresario=2;
    private final int indiceSSempresario=1;
    private final int indiceAccidentesEmpresario=0;
    private final int divisorProrrateo=12;
    private final int divisorSinProrrateo=14;
    private final int totalMesesAño=14;
    private final String mes;
    private final String year;
    private final String mesJunio="06";
    private final String mesDiciembre="12";
    private double seguridadSocial;
    private double formacion;
    private double desempleo;
    private double ssEmpresario;
    private double formacionEmpresario;
    private double desempleoEmpresario;
    private double fogasaEmpresario;
    private double accidentesTrabajoEmpresario;
    private final HashMap<Double,Integer> hash_mapValoresIRPF = new HashMap<>();
    
    
    
    
    
   public NominasManager(List<String[][]> listaHojasExcel, List<TrabajadorbbddVO> listaTrabajadores,String fecha){
       this.listaHojasExcel=listaHojasExcel;
       this.listaTrabajadores=listaTrabajadores;
       this.listaCategorias=new ArrayList<>();
       this.listaValoresIRPF=new ArrayList<>();
       this.listaNominas=new ArrayList<>();
       this.listaValoresTrienios=new ArrayList<>();
       this.mes=fecha.substring(0,2);
       this.year=fecha.substring(3);
       
   }
   
   public List<NominaVO> calculaNominas(){
       rellenaListaCategorias();
       valoresIRPF();
       valoresImpuestos();
       valoresTrienios();
       double brutoAnual;
       double brutoMensualProrrateo;
       double brutoMensualSinProrrateo;
       double deduccionIRPF;
       double deduccionSS;
       double deduccionFormacion;
       double deduccionDesempleo;
       double deduccionSSempresario;
       double deduccionDesempleoEmpresario;
       double deduccionFormacionEmpresario;
       double deduccionFogasaEmpresario;
       double deduccionAccidentesEmpresario;
       double netoMensual;
       double importeSalarioMes;
       double costeTotalEmpresario;
       double importeAnualTrienio;
       double importeMensualTrienio;
       double valorProrrateo;
       double irpf;
       int numTrienios;
       double valor=calculaIRPF(13500.00);
       int j=0;
       boolean encontrado;
       for(int i=0;i<listaTrabajadores.size();i++){
           brutoAnual=0.0;
           brutoMensualProrrateo=0.0;
           brutoMensualSinProrrateo=0.0;
           deduccionIRPF=0.0;
           deduccionSS=0.0;
           deduccionFormacion=0.0;
           deduccionDesempleo=0.0;
           deduccionSSempresario=0.0;
           deduccionDesempleoEmpresario=0.0;
           deduccionFormacionEmpresario=0.0;
           deduccionFogasaEmpresario=0.0;
           deduccionAccidentesEmpresario=0.0;
           netoMensual=0.0;
           numTrienios=0;
           importeAnualTrienio=0.0;
           importeMensualTrienio=0.0;
           importeSalarioMes=0.0;
           irpf=0;
           j=0;
           encontrado=false;
           CambioTrienio wasCambioTrienio=wasCambioTrienio(listaTrabajadores.get(i));
           CambioTrienio newCambioTrienio=willBeCambioTrienio(listaTrabajadores.get(i));
           CambioTrienio cambioTrienioSinAntiguedad=CambioTrienioNoYears(listaTrabajadores.get(i));
           CambioTrienio newCambioTrienioNextYear=isCambioTrienioNextYear(listaTrabajadores.get(i));
           while(j<listaCategorias.size() && !encontrado){
                if(listaCategorias.get(j).getNombreCategoria().equals(listaTrabajadores.get(i).getCategorias().getNombreCategoria())){
                    encontrado=true;
                    if(listaTrabajadores.get(i).getNumTrienios()==0){
                        //TRABAJADORES SIN ANTIGUEDAD EN LA FECHA INTRODUCIDA
                        if(cambioTrienioSinAntiguedad.isIsCambioTrienio() ){
                            //CAMBIO DE ANTIGUEDAD DURANTE EL AÑO
                            int nuevoTrienio=cambioTrienioSinAntiguedad.getNewTrienio();
                            numTrienios=nuevoTrienio;
                            int valorNuevoTrienio=calculaValorTrienio(nuevoTrienio);
                            int mesCambioTrienio=wasCambioTrienio.getMes();
                            importeAnualTrienio=((this.totalMesesAño-(mesCambioTrienio-1)*valorNuevoTrienio));
                            importeMensualTrienio=calculaValorTrienio(nuevoTrienio);
                            brutoAnual=listaCategorias.get(j).getSalarioBaseCategoria() + listaCategorias.get(j).getComplementoCategoria()+importeAnualTrienio;
                        }else{
                            //SIN CAMBIO DE ANTIGUEDAD DURANTE EL AÑO
                            brutoAnual = listaCategorias.get(j).getSalarioBaseCategoria() + listaCategorias.get(j).getComplementoCategoria();
                            importeAnualTrienio=0.0;
                            importeMensualTrienio=0.0;
                        }
                        if (listaTrabajadores.get(i).isProrrataExtra()) {
                            //NOMINA PRORRATEADA -> 12 NOMINAS
                            if(mes.equals(mesDiciembre)){
                                if(newCambioTrienioNextYear.isIsCambioTrienio()){
                                    int nuevoTrienio=newCambioTrienioNextYear.getNewTrienio();
                                    int valorNuevoTrienio=calculaValorTrienio(nuevoTrienio);
                                    int mesCambioTrienio=newCambioTrienioNextYear.getMes();
                                    double brutoAnualNextYear=listaCategorias.get(j).getSalarioBaseCategoria()+ listaCategorias.get(j).getComplementoCategoria()+((this.totalMesesAño-mesCambioTrienio)*valorNuevoTrienio);
                                    brutoAnual=listaCategorias.get(j).getSalarioBaseCategoria() + listaCategorias.get(j).getComplementoCategoria()+((brutoAnualNextYear/totalMesesAño)/6);
                                }else{
                                    brutoAnual=listaCategorias.get(j).getSalarioBaseCategoria() + listaCategorias.get(j).getComplementoCategoria()+((brutoAnual/totalMesesAño)/6);
                                }
                                //brutoMensualProrrateo+=;
                            }
                            importeSalarioMes=listaCategorias.get(j).getSalarioBaseCategoria()/divisorSinProrrateo;
                            brutoMensualProrrateo = brutoAnual / divisorProrrateo;
                            
                            //DEDUCCIONES TRABAJADOR
                            deduccionSS = brutoMensualProrrateo * seguridadSocial;
                            deduccionFormacion = brutoMensualProrrateo * formacion;
                            deduccionDesempleo = brutoMensualProrrateo * desempleo;
                            
                            irpf=calculaIRPF(brutoAnual);
                            deduccionIRPF = brutoMensualProrrateo * irpf;

                            netoMensual = brutoMensualProrrateo - deduccionSS - deduccionFormacion - deduccionDesempleo - deduccionIRPF;

                            //COSTES EMPRESARIO
                            deduccionSSempresario = brutoMensualProrrateo * ssEmpresario;
                            deduccionDesempleoEmpresario = brutoMensualProrrateo * desempleoEmpresario;
                            deduccionFormacionEmpresario = brutoMensualProrrateo * formacionEmpresario;
                            deduccionFogasaEmpresario = brutoMensualProrrateo * fogasaEmpresario;
                            deduccionAccidentesEmpresario = brutoMensualProrrateo * accidentesTrabajoEmpresario;

                            costeTotalEmpresario = brutoMensualProrrateo + deduccionSSempresario + deduccionDesempleoEmpresario + deduccionFormacionEmpresario + deduccionFogasaEmpresario + deduccionAccidentesEmpresario;
                           
                            
                            
                            valorProrrateo=(brutoAnual/divisorSinProrrateo)/6;
                            
                            
                            NominaVO nomina=new NominaVO(i+1,listaTrabajadores.get(i),Integer.parseInt(mes),Integer.parseInt(year),numTrienios,importeAnualTrienio,importeSalarioMes,listaCategorias.get(j).getComplementoCategoria()/divisorSinProrrateo,
                            valorProrrateo,brutoAnual,irpf*100,deduccionIRPF,brutoMensualSinProrrateo+valorProrrateo,ssEmpresario*100,deduccionSSempresario,desempleoEmpresario*100,deduccionDesempleoEmpresario,formacionEmpresario*100,deduccionFormacionEmpresario,
                            accidentesTrabajoEmpresario*100,deduccionAccidentesEmpresario,fogasaEmpresario*100,deduccionFogasaEmpresario,seguridadSocial*100,deduccionSS,desempleo*100,deduccionDesempleo,formacion*100,deduccionFormacion,brutoMensualProrrateo,netoMensual,
                            costeTotalEmpresario,false);
                            
                            listaNominas.add(nomina);

                        } else {
                            //NOMINA SIN PRORRATEAR -> 14 NOMINAS
                            brutoMensualProrrateo = brutoAnual / divisorProrrateo;
                            brutoMensualSinProrrateo = brutoAnual / divisorSinProrrateo;
                            importeSalarioMes=listaCategorias.get(j).getSalarioBaseCategoria()/divisorSinProrrateo;
                            if (mes.equals(mesJunio) || mes.equals(mesDiciembre)) {
                                //EXTRA JUNIO/DICIEMBRE

                                //DEDUCCIONES TRABAJADOR 
                                irpf=calculaIRPF(brutoAnual);
                                deduccionIRPF = brutoMensualSinProrrateo *irpf;

                                netoMensual = brutoMensualSinProrrateo - deduccionIRPF;

                                //COSTES EMPRESARIO
                                costeTotalEmpresario = brutoMensualSinProrrateo + deduccionSSempresario + deduccionDesempleoEmpresario + deduccionFormacionEmpresario + deduccionFogasaEmpresario + deduccionAccidentesEmpresario;
                                
                                NominaVO nomina=new NominaVO(Integer.MAX_VALUE-i,listaTrabajadores.get(i),Integer.parseInt(mes),Integer.parseInt(year),numTrienios,importeAnualTrienio,importeSalarioMes,listaCategorias.get(j).getComplementoCategoria()/divisorSinProrrateo,
                                0.0,brutoAnual,irpf*100,deduccionIRPF,brutoMensualSinProrrateo,ssEmpresario*100,0.0,desempleoEmpresario*100,0.0,formacionEmpresario*100,0.0,
                                accidentesTrabajoEmpresario*100,0.0,fogasaEmpresario*100,0.0,seguridadSocial*100,0.0,desempleo*100,0.0,formacion*100,0.0,brutoMensualSinProrrateo,netoMensual,
                                costeTotalEmpresario,true);
                            
                                listaNominas.add(nomina);
                            }

                            //DEDUCCIONES TRABAJADOR
                            deduccionSS = brutoMensualProrrateo * seguridadSocial;
                            deduccionFormacion = brutoMensualProrrateo * formacion;
                            deduccionDesempleo = brutoMensualProrrateo * desempleo;
                            irpf=calculaIRPF(brutoAnual);
                            deduccionIRPF = brutoMensualSinProrrateo * irpf;

                            netoMensual = brutoMensualSinProrrateo - deduccionSS - deduccionFormacion - deduccionDesempleo - deduccionIRPF;

                            //COSTES EMPRESARIO
                            deduccionSSempresario = brutoMensualProrrateo * ssEmpresario;
                            deduccionDesempleoEmpresario = brutoMensualProrrateo * desempleoEmpresario;
                            deduccionFormacionEmpresario = brutoMensualProrrateo * formacionEmpresario;
                            deduccionFogasaEmpresario = brutoMensualProrrateo * fogasaEmpresario;
                            deduccionAccidentesEmpresario = brutoMensualProrrateo * accidentesTrabajoEmpresario;

                            costeTotalEmpresario = brutoMensualSinProrrateo + deduccionSSempresario + deduccionDesempleoEmpresario + deduccionFormacionEmpresario + deduccionFogasaEmpresario + deduccionAccidentesEmpresario;
                            System.out.println("");

                            NominaVO nomina=new NominaVO(i+1,listaTrabajadores.get(i),Integer.parseInt(mes),Integer.parseInt(year),numTrienios,importeAnualTrienio,importeSalarioMes,listaCategorias.get(j).getComplementoCategoria()/divisorSinProrrateo,
                            0.0,brutoAnual,irpf*100,deduccionIRPF,brutoMensualSinProrrateo,ssEmpresario*100,deduccionSSempresario,desempleoEmpresario*100,deduccionDesempleoEmpresario,formacionEmpresario*100,deduccionFormacionEmpresario,
                            accidentesTrabajoEmpresario*100,deduccionAccidentesEmpresario,fogasaEmpresario*100,deduccionFogasaEmpresario,seguridadSocial*100,deduccionSS,desempleo*100,deduccionDesempleo,formacion*100,deduccionFormacion,brutoMensualSinProrrateo,netoMensual,
                            costeTotalEmpresario,false);
                            
                            listaNominas.add(nomina);
                        }
                    }else{
                        //TRABAJADORES CON ANTIGUEDAD
                         
                        numTrienios=listaTrabajadores.get(i).getNumTrienios();
                        if(wasCambioTrienio.isIsCambioTrienio() ){
                            //CAMBIO DE ANTIGUEDAD ANTES DEL MES DE ALTA
                            int nuevoTrienio=wasCambioTrienio.getNewTrienio();
                            int valorNuevoTrienio=calculaValorTrienio(nuevoTrienio);
                            //numTrienios=nuevoTrienio;
                            int mesCambioTrienio=wasCambioTrienio.getMes();
                          
                            //importeAnualTrienio=(calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios())*mesCambioTrienio+(valorNuevoTrienio*(totalMesesAño-mesCambioTrienio)));
                            importeAnualTrienio=valorNuevoTrienio*(mesCambioTrienio-1)+calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios())*(totalMesesAño-(mesCambioTrienio-1));
                            importeMensualTrienio=calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios());
                            brutoAnual=listaCategorias.get(j).getSalarioBaseCategoria() + listaCategorias.get(j).getComplementoCategoria()+importeAnualTrienio;
                            
                                    
                        }else{
                            if(newCambioTrienio.isIsCambioTrienio()){
                                //CAMBIO DE ANTIGUEDAD A PARTIR DEL MES DE ALTA
                                int nuevoTrienio=newCambioTrienio.getNewTrienio();
                                int valorNuevoTrienio=calculaValorTrienio(nuevoTrienio);
                                //numTrienios=nuevoTrienio;
                                int mesCambioTrienio=newCambioTrienio.getMes();

                                //importeAnualTrienio=(calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios())*mesCambioTrienio+(valorNuevoTrienio*(totalMesesAño-mesCambioTrienio)));
                                importeAnualTrienio=calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios())*(mesCambioTrienio-1)+(valorNuevoTrienio*(totalMesesAño-(mesCambioTrienio-1)));
                                importeMensualTrienio=calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios());
                                brutoAnual=listaCategorias.get(j).getSalarioBaseCategoria() + listaCategorias.get(j).getComplementoCategoria()+importeAnualTrienio;
                            }else{
                                //SIN CAMBIO DE ANTIGUEDAD DURANTE EL AÑO
                                //importeTrienios=(calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios())*(this.totalMesesAño));
                                importeAnualTrienio=calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios())*totalMesesAño;
                                importeMensualTrienio=calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios());
                                double salarioBaseDebug=listaCategorias.get(j).getSalarioBaseCategoria();
                                double complementoCategoriaDebug=listaCategorias.get(j).getComplementoCategoria();
                                brutoAnual = listaCategorias.get(j).getSalarioBaseCategoria() + listaCategorias.get(j).getComplementoCategoria()+importeAnualTrienio;
                            }
                        }
                        
                        if (listaTrabajadores.get(i).isProrrataExtra()) {
                            //NOMINA PRORRATEADA -> 12 NOMINAS
                            if(mes.equals(mesDiciembre)){
                                if(newCambioTrienioNextYear.isIsCambioTrienio()){
                                    int nuevoTrienio=newCambioTrienioNextYear.getNewTrienio();
                                    int valorNuevoTrienio=calculaValorTrienio(nuevoTrienio);
                                    int mesCambioTrienio=newCambioTrienioNextYear.getMes();
                                    double brutoAnualNextYear=listaCategorias.get(j).getSalarioBaseCategoria() + listaCategorias.get(j).getComplementoCategoria()+
                                        (calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios())*mesCambioTrienio+(valorNuevoTrienio*(totalMesesAño-mesCambioTrienio)));
                                    brutoAnual=listaCategorias.get(j).getSalarioBaseCategoria() + listaCategorias.get(j).getComplementoCategoria() + (calculaValorTrienio(listaTrabajadores.get(i).getNumTrienios())*(this.totalMesesAño)) +((brutoAnualNextYear/totalMesesAño)/6);
                                }else{
                                    brutoAnual=listaCategorias.get(j).getSalarioBaseCategoria() + listaCategorias.get(j).getComplementoCategoria()+((brutoAnual/totalMesesAño)/6);
                                }
                
                            }
                            importeSalarioMes=listaCategorias.get(j).getSalarioBaseCategoria()/divisorSinProrrateo;
                            brutoMensualProrrateo = brutoAnual / divisorProrrateo;

                            //DEDUCCIONES TRABAJADOR
                            deduccionSS = brutoMensualProrrateo * seguridadSocial;
                            deduccionFormacion = brutoMensualProrrateo * formacion;
                            deduccionDesempleo = brutoMensualProrrateo * desempleo;
                            irpf=calculaIRPF(brutoAnual);
                            deduccionIRPF = brutoMensualProrrateo * irpf;

                            netoMensual = brutoMensualProrrateo - deduccionSS - deduccionFormacion - deduccionDesempleo - deduccionIRPF;

                            //COSTES EMPRESARIO
                            deduccionSSempresario = brutoMensualProrrateo * ssEmpresario;
                            deduccionDesempleoEmpresario = brutoMensualProrrateo * desempleoEmpresario;
                            deduccionFormacionEmpresario = brutoMensualProrrateo * formacionEmpresario;
                            deduccionFogasaEmpresario = brutoMensualProrrateo * fogasaEmpresario;
                            deduccionAccidentesEmpresario = brutoMensualProrrateo * accidentesTrabajoEmpresario;

                            costeTotalEmpresario = brutoMensualProrrateo + deduccionSSempresario + deduccionDesempleoEmpresario + deduccionFormacionEmpresario + deduccionFogasaEmpresario + deduccionAccidentesEmpresario;
                            System.out.println("");
                            
                            valorProrrateo=(brutoAnual/divisorSinProrrateo)/6;
                            
                            NominaVO nomina=new NominaVO(i+1,listaTrabajadores.get(i),Integer.parseInt(mes),Integer.parseInt(year),numTrienios,importeMensualTrienio,importeSalarioMes,listaCategorias.get(j).getComplementoCategoria()/divisorSinProrrateo,
                            valorProrrateo,brutoAnual,irpf*100,deduccionIRPF,brutoMensualSinProrrateo+valorProrrateo,ssEmpresario*100,deduccionSSempresario,desempleoEmpresario*100,deduccionDesempleoEmpresario,formacionEmpresario*100,deduccionFormacionEmpresario,
                            accidentesTrabajoEmpresario*100,deduccionAccidentesEmpresario,fogasaEmpresario*100,deduccionFogasaEmpresario,seguridadSocial*100,deduccionSS,desempleo*100,deduccionDesempleo,formacion*100,deduccionFormacion,brutoMensualProrrateo,netoMensual,
                            costeTotalEmpresario,false);
                            
                            listaNominas.add(nomina);

                        } else {
                            //NOMINA SIN PRORRATEAR -> 14 NOMINAS
                            brutoMensualProrrateo = brutoAnual / divisorProrrateo;
                            brutoMensualSinProrrateo = brutoAnual / divisorSinProrrateo;
                            importeSalarioMes=listaCategorias.get(j).getSalarioBaseCategoria()/divisorSinProrrateo;

                            if (mes.equals(mesJunio) || mes.equals(mesDiciembre)) {
                                //EXTRA JUNIO/DICIEMBRE

                                //DEDUCCIONES TRABAJADOR
                                irpf=calculaIRPF(brutoAnual);
                                deduccionIRPF = brutoMensualSinProrrateo * calculaIRPF(brutoAnual);

                                netoMensual = brutoMensualSinProrrateo - deduccionIRPF;

                                //COSTES EMPRESARIO
                                costeTotalEmpresario = brutoMensualSinProrrateo + deduccionSSempresario + deduccionDesempleoEmpresario + deduccionFormacionEmpresario + deduccionFogasaEmpresario + deduccionAccidentesEmpresario;

                                NominaVO nomina=new NominaVO(Integer.MAX_VALUE-i,listaTrabajadores.get(i),Integer.parseInt(mes),Integer.parseInt(year),numTrienios,importeMensualTrienio,importeSalarioMes,listaCategorias.get(j).getComplementoCategoria()/divisorSinProrrateo,
                                0.0,brutoAnual,irpf*100,deduccionIRPF,brutoMensualSinProrrateo,ssEmpresario*100,0.0,desempleoEmpresario*100,0.0,formacionEmpresario*100,0.0,
                                accidentesTrabajoEmpresario*100,0.0,fogasaEmpresario*100,0.0,seguridadSocial*100,0.0,desempleo*100,0.0,formacion*100,0.0,brutoMensualSinProrrateo,netoMensual,
                                costeTotalEmpresario,true);
                            
                                listaNominas.add(nomina);
                            }

                            //DEDUCCIONES TRABAJADOR
                            deduccionSS = brutoMensualProrrateo * seguridadSocial;
                            deduccionFormacion = brutoMensualProrrateo * formacion;
                            deduccionDesempleo = brutoMensualProrrateo * desempleo;
                            deduccionIRPF = brutoMensualSinProrrateo * calculaIRPF(brutoAnual);

                            netoMensual = brutoMensualSinProrrateo - deduccionSS - deduccionFormacion - deduccionDesempleo - deduccionIRPF;

                            //COSTES EMPRESARIO
                            deduccionSSempresario = brutoMensualProrrateo * ssEmpresario;
                            deduccionDesempleoEmpresario = brutoMensualProrrateo * desempleoEmpresario;
                            deduccionFormacionEmpresario = brutoMensualProrrateo * formacionEmpresario;
                            deduccionFogasaEmpresario = brutoMensualProrrateo * fogasaEmpresario;
                            deduccionAccidentesEmpresario = brutoMensualProrrateo * accidentesTrabajoEmpresario;

                            costeTotalEmpresario = brutoMensualSinProrrateo + deduccionSSempresario + deduccionDesempleoEmpresario + deduccionFormacionEmpresario + deduccionFogasaEmpresario + deduccionAccidentesEmpresario;
                            System.out.println("");

                            NominaVO nomina=new NominaVO(i+1,listaTrabajadores.get(i),Integer.parseInt(mes),Integer.parseInt(year),numTrienios,importeMensualTrienio,importeSalarioMes,listaCategorias.get(j).getComplementoCategoria()/divisorSinProrrateo,
                            0.0,brutoAnual,irpf*100,deduccionIRPF,brutoMensualSinProrrateo,ssEmpresario*100,deduccionSSempresario,desempleoEmpresario*100,deduccionDesempleoEmpresario,formacionEmpresario*100,deduccionFormacionEmpresario,
                            accidentesTrabajoEmpresario*100,deduccionAccidentesEmpresario,fogasaEmpresario*100,deduccionFogasaEmpresario,seguridadSocial*100,deduccionSS,desempleo*100,deduccionDesempleo,formacion*100,deduccionFormacion,brutoMensualSinProrrateo,netoMensual,
                            costeTotalEmpresario,false);
                            
                            listaNominas.add(nomina);
                        }
             
                    }
                            
                }else{
                    j++;
                }
            }        
       }
       return listaNominas;
   }
   
   public void rellenaListaCategorias(){
       String [][] hojaCategorias=listaHojasExcel.get(2);
       for(int i=1;i<hojaCategorias.length;i++){
           CategoriasVO newCategoria=new CategoriasVO(i,hojaCategorias[i][indiceNombreCategoria],Double.parseDouble(hojaCategorias[i][indiceSalarioBase]),Double.parseDouble(hojaCategorias[i][indiceComplementos]));
           listaCategorias.add(newCategoria);
       }
   }
   
   public void valoresImpuestos(){
       String [][] hojaCategorias=listaHojasExcel.get(0);
       seguridadSocial=Double.parseDouble(hojaCategorias[indiceSeguridadSocial][indiceValores].replace(',', '.'))/100;
       desempleo=Double.parseDouble(hojaCategorias[indiceDesempleo][indiceValores].replace(',', '.'))/100;
       formacion=Double.parseDouble(hojaCategorias[indiceFormacion][indiceValores].replace(',', '.'))/100;
       ssEmpresario=Double.parseDouble(hojaCategorias[indiceSSempresario][indiceValores].replace(',', '.'))/100;
       desempleoEmpresario=Double.parseDouble(hojaCategorias[indiceDesempleoEmpresario][indiceValores].replace(',', '.'))/100;
       formacionEmpresario=Double.parseDouble(hojaCategorias[indiceFormacionEmpresario][indiceValores].replace(',', '.').replace(',', '.'))/100;
       fogasaEmpresario=Double.parseDouble(hojaCategorias[indiceFogasaEmpresario][indiceValores].replace(',', '.'))/100;
       accidentesTrabajoEmpresario=Double.parseDouble(hojaCategorias[indiceAccidentesEmpresario][indiceValores].replace(',', '.'))/100;
       
   }
   
   public void valoresTrienios(){
        String [][] hojaCategorias=listaHojasExcel.get(1);
        for(int i=1;i<hojaCategorias.length;i++){
             String h=hojaCategorias[i][indiceValores];
             ArrayList<Integer> lista=new ArrayList<>();
             lista.add(Integer.parseInt(hojaCategorias[i][indiceValores-1]));
             lista.add(Integer.parseInt(h));
             listaValoresTrienios.add(lista);
        }
   }
   
   public int calculaValorTrienio(int numTrienios){
       for(int i=0;i<listaValoresTrienios.size();i++){
           if(listaValoresTrienios.get(i).get(0).equals(numTrienios)){
               return listaValoresTrienios.get(i).get(1);
           }
       }
       return -1;
   }
   
   public void valoresIRPF(){
       String [][] hojaCategorias=listaHojasExcel.get(3);
       for(int i=1;i<hojaCategorias.length;i++){
           String h=hojaCategorias[i][indiceValores];
           h=h.replace(',', '.');
           ArrayList<Double> lista=new ArrayList<>();
           lista.add(Double.parseDouble(hojaCategorias[i][indiceValores-1]));
           lista.add(Double.parseDouble(h));
           
          listaValoresIRPF.add(lista);
       }
   }
   
   public double calculaIRPF(double brutoAnual){
       String [][] hojaCategorias=listaHojasExcel.get(3);
       for(int i=0;i<listaValoresIRPF.size();i++){
           if(brutoAnual<listaValoresIRPF.get(i).get(0)){
               return listaValoresIRPF.get(i-1).get(1)/100;
           }
       }
       return 0.0;
   }
   
   public CambioTrienio wasCambioTrienio(TrabajadorbbddVO trabajador){
       int numTrienios=trabajador.getNumTrienios();
       CambioTrienio newCambioTrienio=new CambioTrienio();
       ExcelManager excel =new ExcelManager();
       SimpleDateFormat sdf = new SimpleDateFormat();
       sdf.applyPattern("MM/dd/yyyy");
       String fechaComoCadena = sdf.format(trabajador.getFechaAlta());
       String mesAlta=fechaComoCadena.substring(3,5);
       String añoAlta=fechaComoCadena.substring(6);
       int numMesAlta=Integer.parseInt(mesAlta);
       int numYearAlta=Integer.parseInt(añoAlta);
       //System.out.println(fechaComoCadena);
       for(int i=1;i<numMesAlta+1;i++){
           int res=excel.calculaTrienios(numMesAlta,numYearAlta,i,Integer.parseInt(this.year));
           if(res>numTrienios){
               newCambioTrienio.setNewTrienio(res);
               newCambioTrienio.setMes(i);
               newCambioTrienio.setIsCambioTrienio(true);
               
               return newCambioTrienio;
           }
           if(res<numTrienios){
               newCambioTrienio.setNewTrienio(res);
               newCambioTrienio.setMes(Integer.parseInt(mesAlta)+1);
               newCambioTrienio.setIsCambioTrienio(true);
               
               return newCambioTrienio;
           }
       }
       newCambioTrienio.setIsCambioTrienio(false);
       return newCambioTrienio;
   }
   
   
   public CambioTrienio CambioTrienioNoYears(TrabajadorbbddVO trabajador){
       int numTrienios=trabajador.getNumTrienios();
       CambioTrienio newCambioTrienio=new CambioTrienio();
       ExcelManager excel =new ExcelManager();
       SimpleDateFormat sdf = new SimpleDateFormat();
       sdf.applyPattern("MM/dd/yyyy");
       String fechaComoCadena = sdf.format(trabajador.getFechaAlta());
       String mesAlta=fechaComoCadena.substring(3,5);
       String añoAlta=fechaComoCadena.substring(6);
       int numMesAlta=Integer.parseInt(mesAlta);
       int numYearAlta=Integer.parseInt(añoAlta);
       //System.out.println(fechaComoCadena);
       for(int i=1;i<13;i++){
           int res=excel.calculaTrienios(numMesAlta,numYearAlta,i,Integer.parseInt(this.year));
           if(res>numTrienios){
               newCambioTrienio.setNewTrienio(res);
               newCambioTrienio.setMes(i);
               newCambioTrienio.setIsCambioTrienio(true);
               
               return newCambioTrienio;
           }
          
       }
       newCambioTrienio.setIsCambioTrienio(false);
       return newCambioTrienio;
   }
   
   
   public CambioTrienio willBeCambioTrienio(TrabajadorbbddVO trabajador){
       int numTrienios=trabajador.getNumTrienios();
       CambioTrienio newCambioTrienio=new CambioTrienio();
       ExcelManager excel =new ExcelManager();
       SimpleDateFormat sdf = new SimpleDateFormat();
       sdf.applyPattern("MM/dd/yyyy");
       String fechaComoCadena = sdf.format(trabajador.getFechaAlta());
       String mesAlta=fechaComoCadena.substring(3,5);
       String añoAlta=fechaComoCadena.substring(6);
       int numMesAlta=Integer.parseInt(mesAlta);
       int numYearAlta=Integer.parseInt(añoAlta);
       //System.out.println(fechaComoCadena);
       for(int i=numMesAlta+1;i<13;i++){
           int res=excel.calculaTrienios(numMesAlta,numYearAlta,i,Integer.parseInt(this.year));
           if(res>numTrienios){
               newCambioTrienio.setNewTrienio(res);
               newCambioTrienio.setMes(i);
               newCambioTrienio.setIsCambioTrienio(true);
               
               return newCambioTrienio;
           }
           if(res<numTrienios){
               newCambioTrienio.setNewTrienio(res);
               newCambioTrienio.setMes(Integer.parseInt(mesAlta)+1);
               newCambioTrienio.setIsCambioTrienio(true);
               
               return newCambioTrienio;
           }
       }
       newCambioTrienio.setIsCambioTrienio(false);
       return newCambioTrienio;
   }
   
   public CambioTrienio isCambioTrienioNextYear(TrabajadorbbddVO trabajador){
       int numTrienios=trabajador.getNumTrienios();
       CambioTrienio newCambioTrienio=new CambioTrienio();
       ExcelManager excel =new ExcelManager();
       SimpleDateFormat sdf = new SimpleDateFormat();
       sdf.applyPattern("MM/dd/yyyy");
       String fechaComoCadena = sdf.format(trabajador.getFechaAlta());
       String mesAlta=fechaComoCadena.substring(3,5);
       String añoAlta=fechaComoCadena.substring(6);
       int numMesAlta=Integer.parseInt(mesAlta);
       int numYearAlta=Integer.parseInt(añoAlta);
       System.out.println(fechaComoCadena);
       for(int i=1;i<6;i++){
           int res=excel.calculaTrienios(numMesAlta,numYearAlta,i,Integer.parseInt(this.year)+1);
           if(res>numTrienios){
               newCambioTrienio.setNewTrienio(res);
               newCambioTrienio.setMes(i);
               newCambioTrienio.setIsCambioTrienio(true);
               
               return newCambioTrienio;
           }
       }
       newCambioTrienio.setIsCambioTrienio(false);
       
       return newCambioTrienio;
    }
   
    public void escribeXmlNominas(List<NominaVO> listaNominas){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            //Elemento raíz
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Nominas");
            doc.appendChild(rootElement);
            
            Attr attrFecha = doc.createAttribute("fechaNomina");
            attrFecha.setValue(String.valueOf(listaNominas.get(0).getMes())+"/"+String.valueOf(listaNominas.get(0).getAnio()));
            rootElement.setAttributeNode(attrFecha);
            
            for(int i=0;i<listaNominas.size();i++){
                Element elemento = doc.createElement("Nomina");
                rootElement.appendChild(elemento);
                
                Attr attr = doc.createAttribute("idNomina");
                attr.setValue(String.valueOf(listaNominas.get(i).getIdNomina()));
                elemento.setAttributeNode(attr);
                
                Element elemento2 = doc.createElement("Extra");
                if(listaNominas.get(i).isExtra()){
                    elemento2.setTextContent("S");
                    elemento.appendChild(elemento2);
                }else{
                    elemento2.setTextContent("N");
                    elemento.appendChild(elemento2);
                }
                
                Element elementoFila = doc.createElement("idFilaExcel");
                elementoFila.setTextContent(String.valueOf(listaNominas.get(i).getIdNomina()+1));
                elemento.appendChild(elementoFila);
                
                Element elemento3 = doc.createElement("Nombre");
                elemento3.setTextContent(String.valueOf(listaNominas.get(i).getTrabajadorbbdd().getNombre()));
                elemento.appendChild(elemento3);
                
                Element elemento4 = doc.createElement("NIF");
                elemento4.setTextContent(String.valueOf(listaNominas.get(i).getTrabajadorbbdd().getNifnie()));
                elemento.appendChild(elemento4);
                
                Element elemento5 = doc.createElement("IBAN");
                elemento5.setTextContent(String.valueOf(listaNominas.get(i).getTrabajadorbbdd().getIban()));
                elemento.appendChild(elemento5);
                
                Element elemento6 = doc.createElement("Categoria");
                elemento6.setTextContent(String.valueOf(listaNominas.get(i).getTrabajadorbbdd().getCategorias().getNombreCategoria()));
                elemento.appendChild(elemento6); 
                
                Element elemento7 = doc.createElement("BrutoAnual");
                elemento7.setTextContent(String.valueOf(listaNominas.get(i).getBrutoAnual()));
                elemento.appendChild(elemento7);
                
                Element elemento8 = doc.createElement("ImporteIrpf");
                elemento8.setTextContent(String.valueOf(listaNominas.get(i).getImporteIrpf()));
                elemento.appendChild(elemento8); 
                
                Element elemento9 = doc.createElement("BaseEmpresario");
                elemento9.setTextContent(String.valueOf(listaNominas.get(i).getBaseEmpresario()));
                elemento.appendChild(elemento9);
                
                Element elemento10 = doc.createElement("BrutoNomina");
                elemento10.setTextContent(String.valueOf(listaNominas.get(i).getBrutoNomina()));
                elemento.appendChild(elemento10); 
                
                Element elemento11 = doc.createElement("LiquidoNomina");
                elemento11.setTextContent(String.valueOf(listaNominas.get(i).getLiquidoNomina()));
                elemento.appendChild(elemento11);
                
                Element elemento12 = doc.createElement("CosteTotalEmpresario");
                elemento12.setTextContent(String.valueOf(listaNominas.get(i).getCosteTotalEmpresario()));
                elemento.appendChild(elemento12); 
            }
            
            //listaTrabajadoresError.clear();

            //Se escribe el contenido del XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("resources\\Nominas"+"_"+String.valueOf(listaNominas.get(0).getMes())+"-"+String.valueOf(listaNominas.get(0).getAnio())+".xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException pce) {
            pce.getMessage();
        }
    }
}
