/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;

/**
 *
 * @author Administrateur
 */
public class Devis {
    private int accompt;
    private int caution;
    
    private String type;
    
    private double taxe;
    private double PUHT;
    private double TVA;
    private double TTC;
    
    private Date dateDebut;
    private Date dateFin;
    
    private String[] options;
    
    public Devis(Date cdateDebut, Date cdateFin, int caccompt, int ccaution, String ctype, 
                        double ctaxe, double cPUHT, double cTVA, double cTTC,
                        String[] coptions){
        
        this.dateDebut = cdateDebut;
        this.dateFin = cdateFin;
        this.accompt = caccompt;
        this.caution = ccaution;
        this.type = ctype;
        this.taxe = ctaxe;
        this.PUHT = cPUHT;
        this.TVA = cTVA;
        this.TTC = cTTC;
        this.options = coptions;
        
    }
    
    public static Double formatDouble(double x){
        double temp = 1.666666;
        System.out.println("pd"+temp);
        DecimalFormat df = new DecimalFormat("#.##");
        
        return(Double.valueOf(df.format(temp)));
    }
    
}
