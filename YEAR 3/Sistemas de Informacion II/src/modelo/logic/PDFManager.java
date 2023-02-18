/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logic;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.vo.CategoriasVO;
import modelo.vo.NominaVO;
import modelo.vo.TrabajadorbbddVO;

/**
 *
 * @author guill
 */
public class PDFManager {
    private String path;
    
    public PDFManager(String path){
        this.path=path;
    }
    
    public void pdfExamen(int indiceMenor, int indiceMayor, int arrayCategorias[] ,List<CategoriasVO> listaCategorias){
        try {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc, PageSize.LETTER);

            Paragraph nombreAsignatura = new Paragraph("Sistemas de Información II");
            Paragraph nombreGuille = new Paragraph("Guillermo Marcos García 71715520W");
            Paragraph lineaBlanco = new Paragraph("\n");
            Paragraph nombreJavi = new Paragraph("Javier Álvarez Pintor 71467711H");
            
            doc.add(nombreAsignatura);
            doc.add(lineaBlanco);
            doc.add(nombreGuille);
            doc.add(lineaBlanco);
            doc.add(nombreJavi);
            
            Table tabla1 = new Table(2);
            tabla1.setWidth(500);
            
            
            Cell celda1 = new Cell();
            celda1.setBorder(new SolidBorder(1));
            celda1.setWidth(250);
            celda1.setTextAlignment(TextAlignment.CENTER);
            celda1.add(new Paragraph("Categoria con más trabajadores "+listaCategorias.get(indiceMayor).getNombreCategoria()));
            celda1.add(new Paragraph("Número de trabajadores: "+arrayCategorias[indiceMayor]));
            
            
            Cell celda2 = new Cell();
            celda2.setBorder(new SolidBorder(1));
            celda2.setPadding(10);
            celda2.setTextAlignment(TextAlignment.CENTER);
            celda2.add(new Paragraph("Categoria con menos trabajadores "+listaCategorias.get(indiceMenor).getNombreCategoria()));
            celda2.add(new Paragraph("Número de trabajadores: "+arrayCategorias[indiceMenor]));
            
            tabla1.addCell(celda1);
            tabla1.addCell(celda2);
            
            doc.add(tabla1);
            
            doc.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pdfExamenSegunda(List <CategoriasVO> listaCategoriasExamenC, List <Integer> listaRepeticionesCategoria){
        try {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc, PageSize.LETTER);

            Paragraph nombreAsignatura = new Paragraph("Sistemas de Información II");
            Paragraph nombreGuille = new Paragraph("Guillermo Marcos García 71715520W");
            Paragraph lineaBlanco = new Paragraph("\n");
            Paragraph nombreJavi = new Paragraph("Javier Álvarez Pintor 71467711H");
            
            doc.add(nombreAsignatura);
            doc.add(lineaBlanco);
            doc.add(nombreGuille);
            doc.add(lineaBlanco);
            doc.add(nombreJavi);
            
            Table tabla1 = new Table(2);
            tabla1.setWidth(500);
            
            for(int i=0;i<listaCategoriasExamenC.size();i++){
                Cell celda1 = new Cell();
                celda1.setBorder(new SolidBorder(1));
                celda1.setWidth(250);
                celda1.setTextAlignment(TextAlignment.CENTER);
                
                celda1.add(new Paragraph("Nombre de la categoria: "+listaCategoriasExamenC.get(i).getNombreCategoria()));
                celda1.add(new Paragraph("Número de trabajadores de la categoria: "+listaRepeticionesCategoria.get(i)));
                
                
                tabla1.addCell(celda1);
            }
           
            doc.add(tabla1);
            
            doc.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void newPdf(NominaVO nomina){
       
        try {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc, PageSize.LETTER);

            Paragraph empty = new Paragraph("");
            Table tabla1 = new Table(2);
            tabla1.setWidth(500);

            Paragraph nombreEmpresa = new Paragraph(nomina.getTrabajadorbbdd().getEmpresas().getNombre());
            Paragraph CIF = new Paragraph("CIF: "+nomina.getTrabajadorbbdd().getEmpresas().getCif());
            Paragraph direccion1 = new Paragraph("Avenida de la facultad - 6");
            Paragraph direccion2 = new Paragraph("24001 Leon");

            Cell celda1 = new Cell();
            celda1.setBorder(new SolidBorder(1));
            celda1.setWidth(250);
            celda1.setTextAlignment(TextAlignment.CENTER);

            celda1.add(nombreEmpresa);
            celda1.add(CIF);
            celda1.add(direccion1);
            celda1.add(direccion2);

            Cell celda2 = new Cell();
            celda2.setBorder(Border.NO_BORDER);
            celda2.setPadding(10);
            celda2.setTextAlignment(TextAlignment.RIGHT);
            celda2.add(new Paragraph("IBAN: " + nomina.getTrabajadorbbdd().getIban()));
            celda2.add(new Paragraph("Bruto Anual: " + nomina.getBrutoAnual()));
            celda2.add(new Paragraph("Categoria: " + nomina.getTrabajadorbbdd().getCategorias().getNombreCategoria()));
            celda2.add(new Paragraph("Fecha de alta:" + nomina.getTrabajadorbbdd().getFechaAlta()));

            Cell celda3 = new Cell();
            celda3.setBorder(new SolidBorder(1));
            celda3.setWidth(240);
            celda3.setTextAlignment(TextAlignment.CENTER);
            
            Cell celdaBlanco = new Cell();
            celdaBlanco.setBorder(Border.NO_BORDER);
            celdaBlanco.setWidth(250);
            celdaBlanco.setTextAlignment(TextAlignment.CENTER);
            celdaBlanco.add(new Paragraph(""));

            celda3.add(new Paragraph(nomina.getTrabajadorbbdd().getNombre() + " " + nomina.getTrabajadorbbdd().getApellido1() + " " + nomina.getTrabajadorbbdd().getApellido2()));
            celda3.add(new Paragraph(nomina.getTrabajadorbbdd().getNifnie()));
            celda3.add(direccion1);
            celda3.add(direccion2);

            Paragraph fechaNomina = new Paragraph("");

            tabla1.addCell(celda1);
            tabla1.addCell(celda2);
            
            
            Table tabla2 = new Table(2);
            tabla1.setWidth(500);
            
            tabla2.addCell(celdaBlanco);
            tabla2.addCell(celda3);
            
            Paragraph lineaBlanco = new Paragraph("");
            Paragraph mesNomina;
            if(nomina.isExtra()){
                 mesNomina = new Paragraph("Nómina: Extra de "+getNombreMes(nomina.getMes())+" de "+nomina.getAnio());
            }else{
                 mesNomina = new Paragraph("Nómina: "+getNombreMes(nomina.getMes())+" de "+nomina.getAnio());
            }
            
            mesNomina.setTextAlignment(TextAlignment.CENTER);
            mesNomina.setBold();
            
            Table tabla3 = new Table(5);
            tabla3.setWidth(500);
            
            Cell columnaConceptos = new Cell();
            columnaConceptos.setBorder(Border.NO_BORDER);
            columnaConceptos.setPadding(4);
            columnaConceptos.setTextAlignment(TextAlignment.LEFT);
            columnaConceptos.add(new Paragraph("Conceptos"));
            columnaConceptos.add(new Paragraph("Salario Base: "));
            columnaConceptos.add(new Paragraph("Prorrateo: "));
            columnaConceptos.add(new Paragraph("Complemento: "));
            columnaConceptos.add(new Paragraph("Antiguedad: "));
            columnaConceptos.add(new Paragraph("Contingencias generales: "));
            columnaConceptos.add(new Paragraph("Desempleo: "));
            columnaConceptos.add(new Paragraph("Cuota formación: "));
            columnaConceptos.add(new Paragraph("IRPF: "));
            columnaConceptos.add(new Paragraph("Total deducciones "));
            columnaConceptos.add(new Paragraph("Total devengos "));
            columnaConceptos.add(new Paragraph("\n"));
            columnaConceptos.add(new Paragraph("\n"));
            /*
            columnaConceptos.add(new Paragraph("Calculo empresario: BASE "));
            
            columnaConceptos.add(new Paragraph("\n"));
            columnaConceptos.add(new Paragraph("\n"));
            columnaConceptos.add(new Paragraph("Contingencias comunes empresario "+nomina.getSeguridadSocialEmpresario()+"%"));
            columnaConceptos.add(new Paragraph("Desempleo "+nomina.getDesempleoEmpresario()+"%"));
            columnaConceptos.add(new Paragraph("Formacion "+nomina.getFormacionEmpresario()+"%"));
            columnaConceptos.add(new Paragraph("Accidentes de trabajo "+nomina.getAccidentesTrabajoEmpresario()+"%"));
            columnaConceptos.add(new Paragraph("FOGASA "+nomina.getFogasaempresario()+"%"));
            columnaConceptos.add(new Paragraph("Total empresario"));
            
            columnaConceptos.add(new Paragraph("\n"));
            columnaConceptos.add(new Paragraph("\n"));
            
            columnaConceptos.add(new Paragraph("COSTE TOTAL TRABAJADOR: "));
            */
            
            Cell columnaCantidad = new Cell();
            columnaCantidad.setBorder(Border.NO_BORDER);
            columnaCantidad.setPadding(1);
            columnaCantidad.setTextAlignment(TextAlignment.CENTER);
            columnaCantidad.add(new Paragraph("Cantidad"));
            
            double brutoMensualProrrateo=nomina.getBrutoAnual()/12;
            double brutoMensualSinProrrateo=nomina.getBrutoAnual()/14;
            
            BigDecimal bd = new BigDecimal(String.valueOf(brutoMensualProrrateo));
            BigDecimal rounded = bd.setScale(2,RoundingMode.FLOOR);
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            
            if(nomina.isExtra()){
                rounded=new BigDecimal("0.0");
            }
            columnaCantidad.add(new Paragraph(getDiasMes(nomina.getMes())+" días"));
            columnaCantidad.add(new Paragraph(getDiasMes(nomina.getMes())+" días"));
            columnaCantidad.add(new Paragraph(getDiasMes(nomina.getMes())+" días"));
            columnaCantidad.add(new Paragraph(String.valueOf(nomina.getNumeroTrienios())+" Trienios"));
            columnaCantidad.add(new Paragraph(String.valueOf(nomina.getSeguridadSocialTrabajador())+"% de "+String.valueOf(rounded)+"€"));
            columnaCantidad.add(new Paragraph(String.valueOf(nomina.getDesempleoTrabajador())+"% de "+String.valueOf(rounded)+"€"));
            columnaCantidad.add(new Paragraph(String.valueOf(nomina.getFormacionTrabajador())+"% de "+String.valueOf(rounded)+"€"));
            
            if(nomina.getTrabajadorbbdd().isProrrataExtra()){
                bd=new BigDecimal(String.valueOf(brutoMensualProrrateo));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaCantidad.add(new Paragraph(String.valueOf(nomina.getIrpf())+"% de "+String.valueOf(rounded)+"€"));
            }else{
                bd=new BigDecimal(String.valueOf(brutoMensualSinProrrateo));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaCantidad.add(new Paragraph(String.valueOf(nomina.getIrpf())+"% de "+String.valueOf(rounded)+"€"));
            }
            
            
            Cell columnaImporte = new Cell();
            columnaImporte.setBorder(Border.NO_BORDER);
            columnaImporte.setPadding(1);
            columnaImporte.setTextAlignment(TextAlignment.CENTER);
            columnaImporte.add(new Paragraph("Imp.Unitario"));
            
            double salarioDia=nomina.getImporteSalarioMes()/getDiasMes(nomina.getMes());
            bd=new BigDecimal(String.valueOf(salarioDia));
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            
            columnaImporte.add(new Paragraph(String.valueOf(rounded)+"€"));
            if(nomina.getTrabajadorbbdd().isProrrataExtra()){
                double prorrateoDia=nomina.getValorProrrateo()/getDiasMes(nomina.getMes());
                bd=new BigDecimal(String.valueOf(prorrateoDia));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaImporte.add(new Paragraph(String.valueOf(rounded)+"€")); 
            }else{
                columnaImporte.add(new Paragraph("00,00€")); 
            }
            
            double complementoDia=nomina.getImporteComplementoMes()/getDiasMes(nomina.getMes());
            bd=new BigDecimal(String.valueOf(complementoDia));
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            columnaImporte.add(new Paragraph(String.valueOf(rounded)+"€"));
            
            double trieniosDia=-1;
            
            if(nomina.getNumeroTrienios()==0){
                columnaImporte.add(new Paragraph("00,00€"));
            }else{
                trieniosDia=nomina.getImporteTrienios()/nomina.getNumeroTrienios();
                bd=new BigDecimal(String.valueOf(trieniosDia));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaImporte.add(new Paragraph(String.valueOf(rounded)+"€"));
            }
            columnaImporte.add(new Paragraph("\n"));
            columnaImporte.add(new Paragraph("\n"));
            columnaImporte.add(new Paragraph("\n"));
            columnaImporte.add(new Paragraph("\n"));
            columnaImporte.add(new Paragraph("\n"));
            columnaImporte.add(new Paragraph("\n"));
            columnaImporte.add(new Paragraph("Liquido a percibir"));
            
            
            Cell columnaDevengo = new Cell();
            columnaDevengo.setBorder(Border.NO_BORDER);
            columnaDevengo.setPadding(1);
            columnaDevengo.setTextAlignment(TextAlignment.CENTER);
            columnaDevengo.add(new Paragraph("Devengo"));
            
            double devengoSalario=salarioDia*getDiasMes(nomina.getMes());
            bd=new BigDecimal(String.valueOf(devengoSalario));
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            
            columnaDevengo.add(new Paragraph(String.valueOf(rounded)+"€"));
            double prorrateoDia=0.0;
            
            if(nomina.getTrabajadorbbdd().isProrrataExtra()){
                prorrateoDia=nomina.getValorProrrateo();
                bd=new BigDecimal(String.valueOf(prorrateoDia));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaDevengo.add(new Paragraph(String.valueOf(rounded)+"€")); 
            }else{
                columnaDevengo.add(new Paragraph("00,00€")); 
            }
            
            double complementoDevengo=complementoDia*getDiasMes(nomina.getMes());
            bd=new BigDecimal(String.valueOf(complementoDevengo));
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            columnaDevengo.add(new Paragraph(String.valueOf(rounded)+"€"));
            
            
            double trieniosDevengo=trieniosDia*nomina.getNumeroTrienios();
            bd=new BigDecimal(String.valueOf(trieniosDevengo));
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            columnaDevengo.add(new Paragraph(String.valueOf(rounded)+"€"));
            columnaDevengo.add(new Paragraph("\n"));
            columnaDevengo.add(new Paragraph("\n"));
            columnaDevengo.add(new Paragraph("\n"));
            columnaDevengo.add(new Paragraph("\n"));
            columnaDevengo.add(new Paragraph("\n"));
            
            //FALTA SUMAR LA EXTRA
            double totalDevengo=devengoSalario+complementoDevengo+trieniosDevengo+prorrateoDia;
            bd=new BigDecimal(String.valueOf(totalDevengo));
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            columnaDevengo.add(new Paragraph(String.valueOf(rounded)+"€"));
            
            
            
            
            Cell columnaDeduccion = new Cell();
            columnaDeduccion.setBorder(Border.NO_BORDER);
            columnaDeduccion.setPadding(1);
            columnaDeduccion.setTextAlignment(TextAlignment.CENTER);
            columnaDeduccion.add(new Paragraph("Deduccion"));
            columnaDeduccion.add(new Paragraph("\n"));
            columnaDeduccion.add(new Paragraph("\n"));
            columnaDeduccion.add(new Paragraph("\n"));
            columnaDeduccion.add(new Paragraph("\n"));
            
            double deduccionSS;
            double deduccionDesempleo;
            double deduccionFormacion;
            if(nomina.isExtra()){
                
                deduccionSS=0.0;
                bd=new BigDecimal(String.valueOf(deduccionSS));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaDeduccion.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionDesempleo=0.0;
                bd=new BigDecimal(String.valueOf(deduccionDesempleo));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaDeduccion.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionFormacion=0.0;
                bd=new BigDecimal(String.valueOf(deduccionFormacion));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaDeduccion.add(new Paragraph(String.valueOf(rounded)+"€"));

                
            }else{
                deduccionSS=nomina.getImporteSeguridadSocialTrabajador();
                bd=new BigDecimal(String.valueOf(deduccionSS));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaDeduccion.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionDesempleo=nomina.getBrutoNomina()*(nomina.getDesempleoTrabajador()/100);
                bd=new BigDecimal(String.valueOf(deduccionDesempleo));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaDeduccion.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionFormacion=nomina.getBrutoNomina()*(nomina.getFormacionTrabajador()/100);
                bd=new BigDecimal(String.valueOf(deduccionFormacion));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaDeduccion.add(new Paragraph(String.valueOf(rounded)+"€"));
            }
            
            
            double deduccionIRPF=nomina.getBrutoNomina()*(nomina.getIrpf()/100);
            bd=new BigDecimal(String.valueOf(deduccionIRPF));
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            columnaDeduccion.add(new Paragraph(String.valueOf(rounded)+"€"));
            
            double deduccionTotal=deduccionSS+deduccionDesempleo+deduccionFormacion+deduccionIRPF;
            bd=new BigDecimal(String.valueOf(deduccionTotal));
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            columnaDeduccion.add(new Paragraph(String.valueOf(rounded)+"€"));
            columnaDeduccion.add(new Paragraph("\n"));
            
            double liquidoMensual=totalDevengo-deduccionTotal;
            bd=new BigDecimal(String.valueOf(liquidoMensual));
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            columnaDeduccion.add(new Paragraph(String.valueOf(rounded)+"€"));
            
            columnaDeduccion.add(new Paragraph("\n"));
            
           
            
            
            
            tabla3.addCell(columnaConceptos);
            tabla3.addCell(columnaCantidad);
            tabla3.addCell(columnaImporte);
            tabla3.addCell(columnaDevengo);
            tabla3.addCell(columnaDeduccion);
            
            
            Table tabla4 = new Table(2);
            tabla4.setWidth(500);
            
            Cell columnaConceptosEmpresario = new Cell();
            columnaConceptosEmpresario.setBorder(Border.NO_BORDER);
            columnaConceptosEmpresario.setPadding(1);
            columnaConceptosEmpresario.setTextAlignment(TextAlignment.LEFT);
            
            columnaConceptosEmpresario.add(new Paragraph("Calculo empresario: BASE "));
            
            
            columnaConceptosEmpresario.add(new Paragraph("Contingencias comunes empresario "+nomina.getSeguridadSocialEmpresario()+"%"));
            columnaConceptosEmpresario.add(new Paragraph("Desempleo "+nomina.getDesempleoEmpresario()+"%"));
            columnaConceptosEmpresario.add(new Paragraph("Formacion "+nomina.getFormacionEmpresario()+"%"));
            columnaConceptosEmpresario.add(new Paragraph("Accidentes de trabajo "+nomina.getAccidentesTrabajoEmpresario()+"%"));
            columnaConceptosEmpresario.add(new Paragraph("FOGASA "+nomina.getFogasaempresario()+"%"));
            columnaConceptosEmpresario.add(new Paragraph("Total empresario"));
            
            columnaConceptosEmpresario.add(new Paragraph("\n"));
            columnaConceptosEmpresario.add(new Paragraph("\n"));
            
            columnaConceptosEmpresario.add(new Paragraph("COSTE TOTAL TRABAJADOR: "));
            
            Cell columnaValoresEmpresario = new Cell();
            columnaValoresEmpresario.setBorder(Border.NO_BORDER);
            columnaValoresEmpresario.setPadding(1);
            columnaValoresEmpresario.setTextAlignment(TextAlignment.RIGHT);
            
            double baseEmpresario;
            double deduccionContingenciasEmpresario;
            double deduccionDesempleoEmpresario;
            double deduccionFormacionEmpresario;
            double deduccionAccidentes;
            double deduccionFogasa;
            double deduccionTotalEmpresario;
            
            if(nomina.isExtra()){
                baseEmpresario=0.0;
                bd=new BigDecimal(String.valueOf(baseEmpresario));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));


                deduccionContingenciasEmpresario=0.0;
                bd=new BigDecimal(String.valueOf(deduccionContingenciasEmpresario));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionDesempleoEmpresario=0.0;
                bd=new BigDecimal(String.valueOf(deduccionDesempleoEmpresario));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionFormacionEmpresario=0.0;
                bd=new BigDecimal(String.valueOf(deduccionFormacionEmpresario));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionAccidentes=0.0;
                bd=new BigDecimal(String.valueOf(deduccionAccidentes));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionFogasa=0.0;
                bd=new BigDecimal(String.valueOf(deduccionFogasa));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionTotalEmpresario=0.0;
                bd=new BigDecimal(String.valueOf(deduccionTotalEmpresario));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));
            }else{
                baseEmpresario=nomina.getBrutoAnual()/12;
                bd=new BigDecimal(String.valueOf(baseEmpresario));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));


                deduccionContingenciasEmpresario=baseEmpresario*(nomina.getSeguridadSocialEmpresario()/100);
                bd=new BigDecimal(String.valueOf(deduccionContingenciasEmpresario));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionDesempleoEmpresario=baseEmpresario*(nomina.getDesempleoEmpresario()/100);
                bd=new BigDecimal(String.valueOf(deduccionDesempleoEmpresario));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionFormacionEmpresario=baseEmpresario*(nomina.getFormacionEmpresario()/100);
                bd=new BigDecimal(String.valueOf(deduccionFormacionEmpresario));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionAccidentes=baseEmpresario*(nomina.getAccidentesTrabajoEmpresario()/100);
                bd=new BigDecimal(String.valueOf(deduccionAccidentes));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionFogasa=baseEmpresario*(nomina.getFogasaempresario()/100);
                bd=new BigDecimal(String.valueOf(deduccionFogasa));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));

                deduccionTotalEmpresario=deduccionContingenciasEmpresario+deduccionDesempleoEmpresario+deduccionFormacionEmpresario+deduccionAccidentes+deduccionFogasa;
                bd=new BigDecimal(String.valueOf(deduccionTotalEmpresario));
                rounded = bd.setScale(2,RoundingMode.FLOOR);
                columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));
            }
            
            
            
            columnaValoresEmpresario.add(new Paragraph("\n"));
            columnaValoresEmpresario.add(new Paragraph("\n"));
            
            double costeTotalEmpresario=deduccionTotalEmpresario+totalDevengo;
            bd=new BigDecimal(String.valueOf(costeTotalEmpresario));
            rounded = bd.setScale(2,RoundingMode.FLOOR);
            columnaValoresEmpresario.add(new Paragraph(String.valueOf(rounded)+"€"));
            
            tabla4.addCell(columnaConceptosEmpresario);
            tabla4.addCell(columnaValoresEmpresario);
            
            doc.add(tabla1);
            doc.add(tabla2);
            doc.add(lineaBlanco);
            doc.add(lineaBlanco);
            doc.add(lineaBlanco);
            doc.add(mesNomina);
            doc.add(tabla3);
            doc.add(tabla4);
            doc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getNombreMes(int num){
        switch (num) {
            case 1:
                return "Enero";

            case 2:
                return "Febrero";

            case 3:
                return "Marzo";

            case 4:
                return "Abril";

            case 5:
                return "Mayo";

            case 6:
                return "Junio";

            case 7:
                return "Julio";

            case 8:
                return "Agosto";

            case 9:
                return "Septiembre";

            case 10:
                return "Octubre";

            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";

        }
        return "Mes erroneo";
    }
    
    public int getDiasMes(int num){
        switch(num){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                return 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
        return -1;
    }
}
