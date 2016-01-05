package br.com.oisul.spring.reports;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class PDFBoxUtils {
	
    public static void setField(String name, String value, PDDocument _pdfDocument) throws IOException {
        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        PDField field = acroForm.getField( name );
        if( field != null ) {
            field.setValue(value);
        }
        else {
            System.err.println( "No field found with name:" + name );
        }
    }
    
    @SuppressWarnings("rawtypes")
	public static void printFields(PDDocument _pdfDocument) throws IOException {
        PDDocumentCatalog docCatalog = _pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        List fields = acroForm.getFields();
        Iterator fieldsIter = fields.iterator();

        System.out.println(new Integer(fields.size()).toString() + " top-level fields were found on the form");

        while( fieldsIter.hasNext()) {
            PDField field = (PDField)fieldsIter.next();
               processField(field, "|--", field.getPartialName());
        }
    }
    
    @SuppressWarnings("rawtypes")
 	private static void processField(PDField field, String sLevel, String sParent) throws IOException
     {
         List kids = field.getKids();
         if(kids != null) {
             Iterator kidsIter = kids.iterator();
             if(!sParent.equals(field.getPartialName())) {
                sParent = sParent + "." + field.getPartialName();
             }
             
             System.out.println(sLevel + sParent);
             
             while(kidsIter.hasNext()) {
                Object pdfObj = kidsIter.next();
                if(pdfObj instanceof PDField) {
                    PDField kid = (PDField)pdfObj;
                    processField(kid, "|  " + sLevel, sParent);
                }
             }
          }
          else {
              String outputString = sLevel + sParent + "." + field.getPartialName() + ",  type=" + field.getClass().getName();
              System.out.println(outputString);
          }
     }


}
