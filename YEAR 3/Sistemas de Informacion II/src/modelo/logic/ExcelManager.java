/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.vo.CategoriasVO;
import modelo.vo.EmpresasVO;
import modelo.vo.TrabajadorbbddVO;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author guill
 */
public class ExcelManager {
    private final Workbook libro;
    private final DataFormatter dataFormatter=new DataFormatter();
    private final int divisor=23;
    private final int tamDNI=9;
    private final int indiceDNI=0;
    private final int indiceNombreCategoria=0;
    private final int indiceNombre=1;
    private final int indiceApellido1=2;
    private final int indiceCIF=4;
    private final int indiceApellido2=3;
    private final int indiceNombreEmpresa=5;
    private final int indiceFechaAlta=6;
    private final int indiceProrrataExtra=8;
    private final int indiceCategoria=7;
    private final int indiceCodigoCuenta=9;
    private final int indiceComplementos=1;
    private final int indiceSalarioBase=2;
    private final int indiceOrigenCuenta=10;
    private final int indiceIBAN=11;
    private final int indiceEMAIL=12;
    private final int tamCodigoCuenta=20;
    private final int tamOrigenCuenta=2;
    private final int tamCodigoIBAN=24;
    private final int divisorCC=11;
    private final int divisorIBAN=97;
    private final int restadorRestoIBAN=98;
    private final int indiceC1=8;
    private final int indiceC2=9;
    private final int tamFechaExcel=6;
    private final int tamFechaCorrecta=10;
    private final String dobleCero="00";
    private String numDNI="";
    private final List<Character> listaLetras= new ArrayList<>();
    private List<CategoriasVO> listaCategorias;
    private List<EmpresasVO> listaEmpresas;
    private final List<Character> letrasDNI= new ArrayList<>();
    private final List<Integer> numerosCC=new ArrayList<>();
    private final List<ArrayList<String>> emailSinRepeticion;
    private final List<TrabajadorbbddVO> listaTrabajadoresNomina;
    private final List<TrabajadorbbddVO> listaTrabajadoresError=new ArrayList<>();
    private final HashMap<String, Integer> hash_mapLetrasIban = new HashMap<>();
    private final String OLD_FORMAT = "M/d/yy";
    private final String NEW_FORMAT = "dd/MM/yyyy";
    private final SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
    
    
    
    
    public ExcelManager(Workbook libro) {
        this.emailSinRepeticion = new ArrayList<>();
        this.listaTrabajadoresNomina = new ArrayList<>();
        this.listaCategorias=new ArrayList<>();
        this.listaEmpresas=new ArrayList<>();
        this.libro=libro;
        
    }
    
    public ExcelManager(){
        this.emailSinRepeticion = new ArrayList<>();
        this.listaTrabajadoresNomina = new ArrayList<>();
        this.listaCategorias=new ArrayList<>();
        this.listaEmpresas=new ArrayList<>();
        libro=null;
    }
    
    public int getFilas(Sheet hoja){
        Iterator<Row> iteradorFilas=hoja.rowIterator();
        int contador=0;
        while(iteradorFilas.hasNext()){
            iteradorFilas.next();
            contador++;
        }
        return contador;
    }
    
    public int getColumnas(Row fila){
        Iterator<Cell> iteradorCeldas=fila.cellIterator();
        int contador=0;
        while(iteradorCeldas.hasNext()){
            iteradorCeldas.next();
            contador++;
        }
        return contador;
    }
    
    public String[][] getHojaExcel(int index){
        Sheet hoja=libro.getSheetAt(index);
        int columnas=getColumnas(hoja.rowIterator().next());
        Iterator<Row> iteradorFilas=hoja.rowIterator();
        String[][] hojaExcel=new String[getFilas(hoja)][getColumnas(hoja.rowIterator().next())];
        int i=0;
        while(iteradorFilas.hasNext()){
            Row fila=iteradorFilas.next();
            for(int j=0;j<columnas;j++){
                Cell celda=fila.getCell(j, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                hojaExcel[i][j]=dataFormatter.formatCellValue(celda);
            }
            i++;
        }
        return hojaExcel;
    }
    
    public void rellenaListaCategorias(){
       String [][] hojaCategorias=getHojaExcel(2);
       listaCategorias.add(new CategoriasVO(0,"Categoria falsa"));
       for(int i=1;i<hojaCategorias.length;i++){
           CategoriasVO newCategoria=new CategoriasVO(i,hojaCategorias[i][indiceNombreCategoria],Double.parseDouble(hojaCategorias[i][indiceSalarioBase]),Double.parseDouble(hojaCategorias[i][indiceComplementos]));
           listaCategorias.add(newCategoria);
       }
    }
    
    public boolean existeEmpresa(String cif){
        for(int i=0;i<listaEmpresas.size();i++){
            if(listaEmpresas.get(i).getCif().equals(cif)){
                return true;
            }
        }
        return false;
    }
    
    public void rellenaListaEmpresas(String cif, String nombreEmpresa, int id){
       if(!existeEmpresa(cif)){
           listaEmpresas.add(new EmpresasVO(id,nombreEmpresa,cif));
       }
    }
    
    public int devuelveIdCategoria(String nombreCategoria){
        for(int i=0;i<listaCategorias.size();i++){
            if(listaCategorias.get(i).getNombreCategoria().equals(nombreCategoria)){
                return i;
            }
        }
        return -1;
    }
    
    public int devuelveIdEmpresa(String cifEmpresa){
        for(int i=0;i<listaEmpresas.size();i++){
            if(listaEmpresas.get(i).getCif().equals(cifEmpresa)){
                return listaEmpresas.get(i).getIdEmpresa();
            }
        }
        return -1;

    }
    
    public List<TrabajadorbbddVO> isNomina(String [][] matriz, String mesNomina){
        rellenaListaCategorias();
        int j=indiceFechaAlta;
        for(int i=1;i<matriz.length;i++){
            String fechaAlta=matriz[i][j];
            if(fechaAlta!=null && tamFechaCorrecta==fechaAlta.length()){
                Date date=getDate(fechaAlta);
                String fechaAño=fechaAlta.substring(6);
                String fechaMes=fechaAlta.substring(3,5);
                String añoNomina=mesNomina.substring(3);
                String mes=mesNomina.substring(0,2);
                int excelDateYear=Integer.parseInt(fechaAño);
                int inputDateYear=Integer.parseInt(añoNomina);
                int excelDateMonth=Integer.parseInt(fechaMes);
                int inputDateMonth=Integer.parseInt(mes);

                if(excelDateYear-inputDateYear<=0){
                    String NIF=matriz[i][indiceDNI];
                    String nombre=matriz[i][indiceNombre];
                    String apellido1=matriz[i][indiceApellido1];
                    String apellido2=matriz[i][indiceApellido2];
                    String nombreEmpresa=matriz[i][indiceNombreEmpresa];
                    String cifEmpresa=matriz[i][indiceCIF];
                    String prorrataExtra=matriz[i][indiceProrrataExtra];
                    String codigoCuenta=matriz[i][indiceCodigoCuenta];
                    String iban=matriz[i][indiceIBAN];
                    String email=matriz[i][indiceEMAIL];
                    
                    int idCategoria=devuelveIdCategoria(matriz[i][indiceCategoria]);
                    double valoresCategoria[]=getDatosCategorias(getHojaExcel(2),matriz[i][indiceCategoria]);
                    rellenaListaEmpresas(cifEmpresa, nombreEmpresa, i);
                    if(excelDateYear-inputDateYear<0){
                    //SI EL AÑO ES MENOR -> EL TRABAJADOR YA ESTABA EN LA EMPRESA
                        if(prorrataExtra.equals("SI")){
                            listaTrabajadoresNomina.add(new TrabajadorbbddVO(i,new CategoriasVO(idCategoria,matriz[i][indiceCategoria],valoresCategoria[1],valoresCategoria[0]),new EmpresasVO(devuelveIdEmpresa(cifEmpresa),nombreEmpresa,cifEmpresa),nombre,apellido1,apellido2,NIF,email,date,codigoCuenta,iban,true,calculaTrienios(excelDateMonth,excelDateYear,inputDateMonth,inputDateYear)));  
                        }else{
                            listaTrabajadoresNomina.add(new TrabajadorbbddVO(i,new CategoriasVO(idCategoria,matriz[i][indiceCategoria],valoresCategoria[1],valoresCategoria[0]),new EmpresasVO(devuelveIdEmpresa(cifEmpresa),nombreEmpresa,cifEmpresa),nombre,apellido1,apellido2,NIF,email,date,codigoCuenta,iban,false,calculaTrienios(excelDateMonth,excelDateYear,inputDateMonth,inputDateYear)));  
                        }
                    }else if(excelDateYear-inputDateYear==0){
                        //SI EL AÑO COINCIDE -> COMPRUEBO SI EL MES ES MENOR O IGUAL
                        if(excelDateMonth-inputDateMonth<=0){
                            //EL TRABAJADOR NECESITA NOMINA
                            if(prorrataExtra.equals("SI")){
                                listaTrabajadoresNomina.add(new TrabajadorbbddVO(i,new CategoriasVO(idCategoria,matriz[i][indiceCategoria],valoresCategoria[1],valoresCategoria[0]),new EmpresasVO(devuelveIdEmpresa(cifEmpresa),nombreEmpresa,cifEmpresa),nombre,apellido1,apellido2,NIF,email,date,codigoCuenta,iban,true,calculaTrienios(excelDateMonth,excelDateYear,inputDateMonth,inputDateYear)));  
                            }else{
                                listaTrabajadoresNomina.add(new TrabajadorbbddVO(i,new CategoriasVO(idCategoria,matriz[i][indiceCategoria],valoresCategoria[1],valoresCategoria[0]),new EmpresasVO(devuelveIdEmpresa(cifEmpresa),nombreEmpresa,cifEmpresa),nombre,apellido1,apellido2,NIF,email,date,codigoCuenta,iban,false,calculaTrienios(excelDateMonth,excelDateYear,inputDateMonth,inputDateYear)));  
                            }
                        }
                    } 
                }
            } 
        }
        
        return this.listaTrabajadoresNomina;
    }
    
    public int calculaTrienios (int mesAlta, int añoAlta, int inputMonth, int inputYear){
        int diferenciaAño=Math.abs(añoAlta-inputYear);
        int totalMeses=0;
        if(mesAlta>inputMonth){
            totalMeses=diferenciaAño*12-(mesAlta-inputMonth);
        }else if(mesAlta==inputMonth){
            totalMeses=diferenciaAño*12;
        }else{
            totalMeses=diferenciaAño*12+Math.abs(mesAlta-inputMonth);
        }
        
        int numTrienios=(totalMeses/12)/3;
        return numTrienios;
    }
    
    public String dateConverter(String fecha){
        Date d;
        String newDateString="";
        sdf.applyPattern(OLD_FORMAT);
        try {
            d = sdf.parse(fecha);
            sdf.applyPattern(NEW_FORMAT);
            newDateString=sdf.format(d);
        } catch (ParseException ex) {
            Logger.getLogger(ExcelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newDateString;
    }
    
    public Date getDate(String fecha){
        Date d=new Date();
        try {
            d = sdf.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(ExcelManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
    public String[][] separaLetraDNI(String[][] matriz){
        int j=indiceDNI;
        for(int i=1;i<matriz.length;i++){
           String dni=matriz[i][j];
            if (dni!=null && dni.length()==tamDNI) {
                for (int k = 0; k < tamDNI; k++) {
                    if (!isNumero(dni.charAt(k))) {
                        letrasDNI.add(dni.charAt(k));
                    } else {
                        numDNI += String.valueOf(dni.charAt(k));
                    }
                }
                //AQUI YA HE LEIDO EL DNI/NIF ENTERO
                if (letrasDNI.size() == 1) {
                    //DNI
                    char letra = devuelveLetra(numDNI);
                    if (isError(letrasDNI.get(0), letra)) {
                        //Corrijo la letra y anoto el error
                        String nuevoDNI = numDNI + String.valueOf(letra);
                        matriz[i][j] = nuevoDNI;
                    }
                } else {
                    //NIE
                    String primerDigitoNIE=String.valueOf(letraNIE(letrasDNI.get(0)));
                    String nieOriginal=numDNI;
                    numDNI=primerDigitoNIE+numDNI;
                    char letra = devuelveLetra(numDNI);
                    if(isError(letrasDNI.get(1),letra)){
                        //Error
                        String nuevoDNI = String.valueOf(letrasDNI.get(0))+nieOriginal + String.valueOf(letra);
                        matriz[i][j] = nuevoDNI;
                    }
                }
                numDNI = "";
                letrasDNI.clear();
            }else{

            }
        }
        return matriz;
    }
    
    public String[][] compruebaCC(String[][] matriz){
        int j=indiceCodigoCuenta,digitoControl,digitoControl2;
        int times=0;
        boolean c1=true,c2=true;
        for(int i=1;i<matriz.length;i++){
            String codigoCuenta=matriz[i][j];
            c1=true;
            c2=true;
            if(codigoCuenta!=null && codigoCuenta.length()==tamCodigoCuenta){
                String codigoCuentaAmpliado=dobleCero+codigoCuenta;
                digitoControl=calculaCC(codigoCuentaAmpliado,MitadCodigoCuenta.PRIMERA);
                digitoControl2=calculaCC(codigoCuentaAmpliado,MitadCodigoCuenta.SEGUNDA);
                if(digitoControl!=Character.getNumericValue(codigoCuenta.charAt(indiceC1))){
                    //C1 INCORRECTO
                    c1=false;
                    if(digitoControl2!=Character.getNumericValue(codigoCuenta.charAt(indiceC2))){
                        //C1 Y C2 INCORRECTOS
                        c2=false;
                    }else{
                        //C1 INCORRECTO Y C2 CORRECTO
                    }
                }else{
                    //C1 CORRECTO
                    if(digitoControl2!=Character.getNumericValue(codigoCuenta.charAt(indiceC2))){
                        //C1 CORRECTO Y C2 INCORRECTO
                        c2=false;
                    }else{
                        //C1 Y C2 CORRECTOS
                    }
                }
                
                //CORREGIMOS LOS DIGITOS ERRONEOS
                if(c1 && c2){
                    //NO HAY ERRORES
                }else{
                    times++;
                    String nombre=matriz[i][indiceNombre];
                    String apellido1=matriz[i][indiceApellido1];
                    String apellido2=matriz[i][indiceApellido2];
                    String nombreEmpresa=matriz[i][indiceNombreEmpresa];
                    listaTrabajadoresError.add(new TrabajadorbbddVO(i,nombre,apellido1,apellido2,new EmpresasVO(i,nombreEmpresa),codigoCuenta,""));
                    if(!c1 && !c2){
                        matriz[i][j]=corrigeCodigoCuenta(ErrorDigitoControl.AMBOS, codigoCuenta,digitoControl,digitoControl2);
                    }else{
                       if(!c1){
                            matriz[i][j]=corrigeCodigoCuenta(ErrorDigitoControl.PRIMERO, codigoCuenta,digitoControl,digitoControl2);
                        }
                        
                        if(!c2){
                            matriz[i][j]=corrigeCodigoCuenta(ErrorDigitoControl.SEGUNDO, codigoCuenta,digitoControl,digitoControl2);
                        }
                    }
                }
            }
        }
        
        return matriz;
    }
    
    public void añadeIbanTrabajadoresErroneos(String matriz[][]){
        for(int i=0;i<listaTrabajadoresError.size();i++){
            listaTrabajadoresError.get(i).setIban(matriz[listaTrabajadoresError.get(i).getIdTrabajador()][indiceIBAN]);
        }
    }
    
    public String [][] generaIBAN (String [][] matriz){
        for(int i=1;i<matriz.length;i++){
            String codigoCuenta=matriz[i][indiceCodigoCuenta];
            String origenCuenta=matriz[i][indiceOrigenCuenta];
            System.out.println(codigoCuenta.length());
            if(codigoCuenta!=null && codigoCuenta.length()==tamCodigoCuenta){
                if(origenCuenta!=null && origenCuenta.length()==tamOrigenCuenta){
                    String codigoCuentaIBAN=codigoCuenta+origenCuenta+dobleCero;
                    if(codigoCuentaIBAN.length()==tamCodigoIBAN){
                        codigoCuentaIBAN=sustituyeLetrasIBAN(codigoCuentaIBAN);
                        codigoCuentaIBAN=calculaIBAN(codigoCuentaIBAN,origenCuenta,codigoCuenta);
                        matriz[i][indiceIBAN]=codigoCuentaIBAN;
                        
                    }
                }
            }
        }
        return matriz;
    }
    
    public void generaEmailSinRepeticion (String[][] matriz){
        for(int i=1;i<matriz.length;i++){
            if(matriz[i][indiceNombre]!=null && matriz[i][indiceNombre]!=""){
                String letraNombre=matriz[i][indiceNombre].substring(0,1);
                String letraApellido1=matriz[i][indiceApellido1].substring(0,1);
                String letraApellido2="";
                if(matriz[i][indiceApellido2]!=""){
                    letraApellido2=matriz[i][indiceApellido2].substring(0,1);
                }
                String repeticion="00";
                String arroba="@";
                String nombreEmpresa=matriz[i][indiceNombreEmpresa];
                System.out.println("");

                String email=letraApellido2+letraApellido1+letraNombre+arroba+nombreEmpresa+".es";
                ArrayList<String> datosEmail=new ArrayList<String>();
               
                datosEmail.add(email);
                datosEmail.add(letraNombre);
                datosEmail.add(letraApellido1);
                datosEmail.add(letraApellido2);
                datosEmail.add(repeticion);
                datosEmail.add(arroba);
                datosEmail.add(nombreEmpresa);

                emailSinRepeticion.add(datosEmail); 
            }else{
                ArrayList<String> datosEmail=new ArrayList<>();
                datosEmail.add("");
                datosEmail.add("");
                datosEmail.add("");
                datosEmail.add("");
                datosEmail.add("");
                datosEmail.add("");
                datosEmail.add("");
                emailSinRepeticion.add(datosEmail);
            }
            
        }
    }
    
    public String [][] compruebaEmail(String matriz[][]){
        int coincidencias;
        //AÑADO EL PRIMERO
        matriz[1][indiceEMAIL]=emailSinRepeticion.get(0).get(3)+emailSinRepeticion.get(0).get(2)+emailSinRepeticion.get(0).get(1)+"00"+emailSinRepeticion.get(0).get(5)+emailSinRepeticion.get(0).get(6)+".es";
        
        String actual="",anterior="";
        for(int i=1;i<matriz.length;i++){
            coincidencias=0;
            for(int j=i-1;j>0;j--){
                anterior=emailSinRepeticion.get(j-1).get(0);
                actual=emailSinRepeticion.get(i-1).get(0);
               if(emailSinRepeticion.get(j-1).get(0).equals(emailSinRepeticion.get(i-1).get(0))){
                   coincidencias++;
               }
            }
            if(!"".equals(actual)){
                    String coincidenciasTexto=String.valueOf(coincidencias);
                if(coincidenciasTexto.length()==1){
                    coincidenciasTexto="0"+coincidenciasTexto;
                }
                String email=emailSinRepeticion.get(i-1).get(3)+emailSinRepeticion.get(i-1).get(2)+emailSinRepeticion.get(i-1).get(1)+coincidenciasTexto+emailSinRepeticion.get(i-1).get(5)+emailSinRepeticion.get(i-1).get(6)+".es";
                matriz[i][indiceEMAIL]=email;
            }
            
        }
        return matriz;
    }
    
    public String sustituyeLetrasIBAN(String codigoCuentaIban){
        rellenaHasMap();
        String newCodigoIban;
        
        String letra1=codigoCuentaIban.substring(20, 21);
        String letra2=codigoCuentaIban.substring(21, 22);
        newCodigoIban=codigoCuentaIban.substring(0, 20)+hash_mapLetrasIban.get(letra1)+hash_mapLetrasIban.get(letra2)+dobleCero;
        
        return newCodigoIban;
    }
    
    public String calculaIBAN (String codigoCuentaIBAN, String origenCuenta ,String codigoCuenta){
        BigDecimal dividendo=new BigDecimal(codigoCuentaIBAN);
        BigDecimal divisor=new BigDecimal(divisorIBAN);
        BigDecimal resto=dividendo.remainder(divisor);
        
        int restoEntero=resto.intValue();
        
      
        int valorLetras=restadorRestoIBAN-restoEntero;
        String textoLetras=String.valueOf(valorLetras);
        String newIBAN;
        
        if(textoLetras.length()==1){
            newIBAN=origenCuenta+"0"+textoLetras+codigoCuenta;
        }else{
            newIBAN=origenCuenta+textoLetras+codigoCuenta;
        }
        
        return newIBAN;
    }
    
    public int calculaCC(String codigoCuenta,MitadCodigoCuenta mitad){
        int resultadoSuma=0,resultadoDivision=0,j=0,resultadoFinal;
        String resultadoFinalTexto="";
        rellenaListaCC();
        switch(mitad){
            case PRIMERA:
                for(int i=0;i<10;i++){
                    resultadoSuma+=Character.getNumericValue(codigoCuenta.charAt(i))*numerosCC.get(i);
                }
                resultadoDivision=resultadoSuma%divisorCC;
            break;
            case SEGUNDA:
                for(int i=12;i<codigoCuenta.length();i++){
                    resultadoSuma+=Character.getNumericValue(codigoCuenta.charAt(i))*numerosCC.get(j);
                    j++;
                }
                resultadoDivision=resultadoSuma%divisorCC;
            break;
             
        }
        resultadoFinal=divisorCC-resultadoDivision;
        resultadoFinalTexto=String.valueOf(resultadoFinal);
        
        if(resultadoFinalTexto.length()!= 1){
            return 0;
        }
        return divisorCC-resultadoDivision;
    }
    
    public String corrigeCodigoCuenta(ErrorDigitoControl digitoErroneo, String codigoOriginal, int c1, int c2){
        String newCodigoControl="";
        switch(digitoErroneo){
            case PRIMERO:
                newCodigoControl=codigoOriginal.substring(0, indiceC1);
                newCodigoControl+=String.valueOf(c1);
                newCodigoControl+=codigoOriginal.substring(indiceC2);
                break;
            case SEGUNDO:
                newCodigoControl=codigoOriginal.substring(0,indiceC1+1);
                newCodigoControl+=String.valueOf(c2);
                newCodigoControl+=codigoOriginal.substring(indiceC2+1);
                break;
            case AMBOS:
                newCodigoControl=codigoOriginal.substring(0,indiceC1);
                newCodigoControl+=String.valueOf(c1);
                newCodigoControl+=String.valueOf(c2);
                newCodigoControl+=codigoOriginal.substring(indiceC2+1);
                break;
        }
       
        return newCodigoControl;
    }
    
    
    
    public int letraNIE(char letra){
       switch(letra){
           case 'X':
               return 0;
           case 'Y':
               return 1;
            case 'Z':
               return 2;
            default:
                return -1;
       } 
    }
    
    public void escribirExcel(String[][] matriz){
        String nombreArchivo="resources\\SistemasInformacionIITrasEjecucion.xlsx";
        XSSFWorkbook newLibro=new XSSFWorkbook();
        libro.removeSheetAt(4);
        Sheet hoja=libro.createSheet("Hoja5");
        
        for(int i=0;i<matriz.length;i++){
            Row fila=hoja.createRow(i);
            for(int j=0;j<matriz[i].length;j++){
                if(j==indiceFechaAlta && i!=0){
                    if(matriz[i][j].length()==tamFechaExcel || matriz[i][j].length()==tamFechaExcel+1){
                        matriz[i][j]=dateConverter(matriz[i][j]);
                    }
                }
                Cell columna=fila.createCell(j);
                columna.setCellValue(matriz[i][j]);
            }
        }
       
       try{
          OutputStream file=new FileOutputStream(nombreArchivo);
          libro.write(file);
       }catch(IOException e){
           System.out.println(e.getMessage());
       }
        
    }
    
    public void emptyDNI(String[][] matriz){
        int j=0;
        for(int i=1;i<matriz.length;i++){
            //Comprobar si es formato dni
            String dni=matriz[i][j];
            String nombre=matriz[i][j+1];
            if(dni.length()!=tamDNI && nombre!=null){
                if(nombre!=""){
                    nombre=matriz[i][j+indiceNombre];
                    String apellido1=matriz[i][j+indiceApellido1];
                    String apellido2=matriz[i][j+indiceApellido2];
                    String nombreEmpresa=matriz[i][j+indiceNombreEmpresa];
                    String nombreCategoria=matriz[i][j+indiceCategoria];
                    listaTrabajadoresError.add(new TrabajadorbbddVO(i,nombre,apellido1,apellido2,new EmpresasVO(i,nombreEmpresa),new CategoriasVO(i,nombreCategoria)));
                }
                
            }
        }
    }
    
    public void isRepetido(String[][] matriz){
        int j=0;
        for(int i=1;i<matriz.length;i++){
            String dni=matriz[i][j];
            if(dni.length()==tamDNI){
                for(int k=i+1;k<matriz.length-1;k++){
                    String nextDNI=matriz[k][j];
                    if(dni.equals(nextDNI)){
                        //ESTA REPETIDO
                        String nombre=matriz[k][j+1];
                        String apellido1=matriz[k][j+2];
                        String apellido2=matriz[k][j+3];
                        String nombreEmpresa=matriz[k][j+5];
                        String nombreCategoria=matriz[k][j+7];
                        listaTrabajadoresError.add(new TrabajadorbbddVO(i,nombre,apellido1,apellido2,new EmpresasVO(i,nombreEmpresa),new CategoriasVO(i,nombreCategoria)));
                    } 
                }
            }  
        }
    }
    
    public double [] getDatosCategorias(String[][] hojaCategorias, String nombreCategoria){
        int j=0;
        double valores[]=new double[2];
        for(int i=1;i<hojaCategorias.length;i++){
            if(hojaCategorias[i][j].equals(nombreCategoria)){
                valores[0]=Double.parseDouble(hojaCategorias[i][1]);
                valores[1]=Double.parseDouble(hojaCategorias[i][2]);
            }
        }
        return valores;
    }
    
    public List<TrabajadorbbddVO> getListaErrores(){
        return this.listaTrabajadoresError;
    }
    
    public void escribeXMLNIF(){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            //Elemento raíz
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Trabajadores");
            doc.appendChild(rootElement);
            
            for(int i=0;i<listaTrabajadoresError.size();i++){
                Element elemento = doc.createElement("Trabajador");
                rootElement.appendChild(elemento);
                
                Attr attr = doc.createAttribute("id");
                attr.setValue(String.valueOf(listaTrabajadoresError.get(i).getIdTrabajador()));
                elemento.setAttributeNode(attr);
                
                Element elemento2 = doc.createElement("Nombre");
                elemento2.setTextContent(String.valueOf(listaTrabajadoresError.get(i).getNombre()));
                elemento.appendChild(elemento2);
                
                Element elemento3 = doc.createElement("PrimerApellido");
                elemento3.setTextContent(String.valueOf(listaTrabajadoresError.get(i).getApellido1()));
                elemento.appendChild(elemento3);
                
                Element elemento4 = doc.createElement("SegundoApellido");
                elemento4.setTextContent(String.valueOf(listaTrabajadoresError.get(i).getApellido2()));
                elemento.appendChild(elemento4);
                
                Element elemento5 = doc.createElement("Empresa");
                elemento5.setTextContent(String.valueOf(listaTrabajadoresError.get(i).getEmpresas().getNombre()));
                elemento.appendChild(elemento5);
                
                Element elemento6 = doc.createElement("Categoria");
                elemento6.setTextContent(String.valueOf(listaTrabajadoresError.get(i).getCategorias().getNombreCategoria()));
                elemento.appendChild(elemento6); 
            }
            
            listaTrabajadoresError.clear();

            //Se escribe el contenido del XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("resources\\errores.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException pce) {
            pce.getMessage();
        }
    }
    
    public void escribeXMLCCC(){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            //Elemento raíz
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Cuentas");
            doc.appendChild(rootElement);
            
            for(int i=0;i<listaTrabajadoresError.size();i++){
                Element elemento = doc.createElement("Cuenta");
                rootElement.appendChild(elemento);
                
                Attr attr = doc.createAttribute("id");
                attr.setValue(String.valueOf(listaTrabajadoresError.get(i).getIdTrabajador()));
                elemento.setAttributeNode(attr);
                
                
                Element elemento2 = doc.createElement("Nombre");
                elemento2.setTextContent(String.valueOf(listaTrabajadoresError.get(i).getNombre()));
                elemento.appendChild(elemento2);
                
                Element elemento3 = doc.createElement("Apellidos");
                elemento3.setTextContent(String.valueOf(listaTrabajadoresError.get(i).getApellido1()+" "+listaTrabajadoresError.get(i).getApellido2()));
                elemento.appendChild(elemento3);
                
                Element elemento5 = doc.createElement("Empresa");
                elemento5.setTextContent(String.valueOf(listaTrabajadoresError.get(i).getEmpresas().getNombre()));
                elemento.appendChild(elemento5);
                
                Element elemento6 = doc.createElement("CCCErroneo");
                elemento6.setTextContent(String.valueOf(listaTrabajadoresError.get(i).getCodigoCuenta()));
                elemento.appendChild(elemento6); 
                
                
                
                Element elemento7 = doc.createElement("IBANCorrecto");
                elemento7.setTextContent(String.valueOf(listaTrabajadoresError.get(i).getIban()));
                elemento.appendChild(elemento7);
            }
            
            listaTrabajadoresError.clear();

            //Se escribe el contenido del XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("resources\\erroresCCC.xml"));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException pce) {
            pce.getMessage();
        }
    }
    
    public boolean isNumero(char caracter){
        try{
           Integer.parseInt(String.valueOf(caracter)); 
           return true;
        }catch(NumberFormatException e){
            return false;
        }
        
    }
    
    public void rellenaListaDNI(){
        listaLetras.add('T');
        listaLetras.add('R');
        listaLetras.add('W');
        listaLetras.add('A');
        listaLetras.add('G');
        listaLetras.add('M');
        listaLetras.add('Y');
        listaLetras.add('F');
        listaLetras.add('P');
        listaLetras.add('D');
        listaLetras.add('X');
        listaLetras.add('B');
        listaLetras.add('N');
        listaLetras.add('J');
        listaLetras.add('Z');
        listaLetras.add('S');
        listaLetras.add('Q');
        listaLetras.add('V');
        listaLetras.add('H');
        listaLetras.add('L');
        listaLetras.add('C');
        listaLetras.add('K');
        listaLetras.add('E');
    }
    
    public void rellenaListaCC(){
        numerosCC.add(1);
        numerosCC.add(2);
        numerosCC.add(4);
        numerosCC.add(8);
        numerosCC.add(5);
        numerosCC.add(10);
        numerosCC.add(9);
        numerosCC.add(7);
        numerosCC.add(3);
        numerosCC.add(6);
    }
    
    public void rellenaHasMap(){
        hash_mapLetrasIban.put("A",10);
        hash_mapLetrasIban.put("B",11);
        hash_mapLetrasIban.put("C",12);
        hash_mapLetrasIban.put("D",13);
        hash_mapLetrasIban.put("E",14);
        hash_mapLetrasIban.put("F",15);
        hash_mapLetrasIban.put("G",16);
        hash_mapLetrasIban.put("H",17);
        hash_mapLetrasIban.put("I",18);
        hash_mapLetrasIban.put("J",19);
        hash_mapLetrasIban.put("K",20);
        hash_mapLetrasIban.put("L",21);
        hash_mapLetrasIban.put("M",22);
        hash_mapLetrasIban.put("N",23);
        hash_mapLetrasIban.put("O",24);
        hash_mapLetrasIban.put("P",25);
        hash_mapLetrasIban.put("Q",26);
        hash_mapLetrasIban.put("R",27);
        hash_mapLetrasIban.put("S",28);
        hash_mapLetrasIban.put("T",29);
        hash_mapLetrasIban.put("U",30);
        hash_mapLetrasIban.put("V",31);
        hash_mapLetrasIban.put("W",32);
        hash_mapLetrasIban.put("X",33);
        hash_mapLetrasIban.put("Y",34);
        hash_mapLetrasIban.put("Z",35);
    }
    
    
    
    public char devuelveLetra(String cadenaNumDNI){
        rellenaListaDNI();
        int newNumDNI=Integer.valueOf(cadenaNumDNI);
        int num=newNumDNI%this.divisor;
        return listaLetras.get(num);
    }
    
    public boolean isError(char letra, char letraComprobada){
        return letra != letraComprobada;
    }
    
}
