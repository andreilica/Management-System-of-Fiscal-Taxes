/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author andre
 */
public class Gestiune {
    private static Gestiune instance = new Gestiune();
    ArrayList<Produs> produse;
    ArrayList<Magazin> magazine;
    HashMap<String,HashMap<String,Double>> taxe;
    
    private Gestiune(){
        produse = new ArrayList<>();
        magazine = new ArrayList<>();
        taxe = new HashMap<>();
    }
    
    public static Gestiune getInstance(){
        return instance;
    }
    
    public void setProduse(String path){
        produse = Parsare.citireProduse(path);
    }
        
    public void setTaxe(String path){
        taxe = Parsare.citireTaxe(path);
    }
    
    public ArrayList<Magazin> getMagazine(){
        return magazine;
    }
    
    public ArrayList<Produs> getProduse(){
        return produse;
    }
    
    public void setMagazin(String pathProd, String pathTaxe, String pathFact){
        setProduse(pathProd);
        setTaxe(pathTaxe);
        BufferedReader b = null;
        FileReader f = null;
        Magazin m = null; 
        try{
            f = new FileReader(pathFact);
            b = new BufferedReader(f);

            String sCurrentLine;
                    
            while ((sCurrentLine = b.readLine()) != null) {
                if(sCurrentLine.contains("Magazin")){
                    if(m != null)
                        magazine.add(m);
                    String words[] = sCurrentLine.split(":");
                    TipMagazin type = TipMagazin.valueOf(words[1]);
                    String denumire = words[2];
                    m = MagazinFactory.creareMagazin(type, denumire);
                }
                
                if(sCurrentLine.contains("Factura")){
                    String denumire = sCurrentLine;
                    Factura fact = new Factura(denumire);
                    b.readLine();
                    while ((sCurrentLine = b.readLine()) != null){
                        if(sCurrentLine.trim().isEmpty())
                            break;
                        String parts[] = sCurrentLine.split("\\s");
                        String nume = parts[0];
                        String taraOrigine = parts[1];
                        int cantitate = Integer.parseInt(parts[2]);
                        for(int i = 0; i < produse.size(); i++){
                            Produs aux = produse.get(i);
                            if(aux.getDenumire().equals(nume) && aux.getTaraOrigine().equals(taraOrigine)){
                                String categorie = aux.getCategorie();
                                double taxa = taxe.get(aux.getTaraOrigine()).get(categorie);
                                ProdusComandat prod = new ProdusComandat(aux, taxa, cantitate);
                                fact.pc.add(prod);
                            }    
                        }
                    }
                    m.facturi.add(fact);
                }
            }
            magazine.add(m);

        }catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {

                if (b != null)
                    b.close();
                if (f != null)
                    f.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    
    
    public void scriereFisier(){
        Writer writer = null;
        Collections.sort(magazine, new ComparatorMagazine());
        for(Iterator itr = magazine.iterator(); itr.hasNext();){
            Magazin mag = (Magazin) itr.next();
            Collections.sort(mag.facturi, new ComparatorFacturi());
        }
        try {
            writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("out.txt"), "utf-8"));
            for (TipMagazin type : TipMagazin.values()) {
                writer.write(type.toString());
                writer.write(System.getProperty( "line.separator" ));
                
                for(Iterator itr1 = magazine.iterator(); itr1.hasNext();){
                    Magazin aux = (Magazin) itr1.next();
                    if(aux.Tip.equals(type)){
                        writer.write(aux.nume);
                        writer.write(System.getProperty( "line.separator" ));
                        writer.write(System.getProperty( "line.separator" ));
                        writer.write("Total ");
                        writer.write(String.valueOf(aux.getTotalFaraTaxe()) + " ");
                        writer.write(String.valueOf(aux.getTotalCuTaxe()) + " ");
                        writer.write(String.valueOf(aux.getTotalCuTaxeScutite()));
                        writer.write(System.getProperty( "line.separator" ));
                        writer.write(System.getProperty( "line.separator" ));
                        writer.write("Tara");
                        writer.write(System.getProperty( "line.separator" ));
                        for(Iterator itr2 = taxe.keySet().iterator(); itr2.hasNext();){
                            String tara = (String) itr2.next();
                            writer.write(tara + " ");
                            if(aux.getTotalTaraFaraTaxe(tara) == 0){
                                writer.write("0");
                                writer.write(System.getProperty( "line.separator" ));
                            } else{
                                writer.write(String.valueOf(aux.getTotalTaraFaraTaxe(tara)) + " ");
                                writer.write(String.valueOf(aux.getTotalTaraCuTaxe(tara)) + " ");
                                writer.write(String.valueOf(aux.getTotalTaraCuTaxeScutite(tara)));
                                writer.write(System.getProperty( "line.separator" ));
                            }
                        }
                        writer.write(System.getProperty( "line.separator" ));
                        
                        for(Iterator itr3 = aux.facturi.iterator(); itr3.hasNext();){
                            Factura fact = (Factura) itr3.next();
                            writer.write(fact.denumire);
                            writer.write(System.getProperty( "line.separator" ));
                            writer.write(System.getProperty( "line.separator" ));
                            writer.write("Total ");
                            writer.write(String.valueOf(fact.getTotalFaraTaxe()) + " ");
                            writer.write(String.valueOf(fact.getTotalCuTaxe()) + " ");
                            writer.write(System.getProperty( "line.separator" ));
                            writer.write(System.getProperty( "line.separator" ));
                            writer.write("Tara");
                            writer.write(System.getProperty( "line.separator" ));
                            for(Iterator itr4 = taxe.keySet().iterator(); itr4.hasNext();){
                                String tara = (String) itr4.next();
                                writer.write(tara + " ");
                                if(fact.getTotalTaraFaraTaxe(tara) == 0){
                                    writer.write("0");
                                    writer.write(System.getProperty( "line.separator" ));
                                } else{
                                    writer.write(String.valueOf(fact.getTotalTaraFaraTaxe(tara)) + " ");
                                    writer.write(String.valueOf(fact.getTotalTaraCuTaxe(tara)) + " ");
                                    writer.write(System.getProperty( "line.separator" ));
                                }
                            }
                            writer.write(System.getProperty( "line.separator" ));
                        }
                    }
                }
            }
            
            
            
        } catch (IOException e) {
             e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e){
                 e.printStackTrace();
            }
        }
        
    }
}
