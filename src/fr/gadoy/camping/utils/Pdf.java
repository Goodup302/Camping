/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gadoy.camping.utils;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @author Administrateur
 */
public class Pdf {
    public static void create() throws DocumentException, FileNotFoundException, BadElementException, IOException{
        Document document = new Document();
        try{
            PdfWriter.getInstance(document, new FileOutputStream("sample1.pdf"));
            document.open();
            
            PdfPTable table = new PdfPTable(5);
            table.addCell("DESIGNATION");
            table.addCell("Quantité");
            table.addCell("PU HT");
            table.addCell("TVA");
            table.addCell("Total TTC");
            for(int i = 0; i < 16; i++){
                table.addCell("");
            }
            
            PdfPTable table2 = new PdfPTable(2);
            table2.addCell("Total T.T.C");
            table2.addCell("Acompte");
            table2.addCell("Caution");
            table2.addCell("Taxe de séjour");
            table2.addCell("Reste A Payer");

            
            Image logo = Image.getInstance("src/style/logoCamping.png");
            logo.setAbsolutePosition(20f, 670f);
            logo.scaleAbsolute(150,150);
            
            Image footer = Image.getInstance("src/style/footerCamping.png");
            footer.setAbsolutePosition(20f,20f);
            footer.scaleAbsolute(560, 120);
            
            document.add(table);
            document.add(footer);
            document.add(logo);
        }catch(Exception e){
            System.out.println(e);
        }
        document.close();
        System.out.println("Done");
    }
}
