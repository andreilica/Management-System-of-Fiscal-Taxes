/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author andre
 */
public class Parsare {
    private static ArrayList<Produs> produseOut;
    
    
    public static ArrayList<Produs> getProduseOut(){
        return produseOut;
    }
    
    public static ArrayList<Produs> citireProduse(String path){
        ArrayList<Produs> produse = new ArrayList<>();
        produseOut = new ArrayList<>();
        BufferedReader b = null;
        FileReader f = null;
        Vector<String> tari = new Vector<>();
        try{
            f = new FileReader(path);
            b = new BufferedReader(f);

            String sCurrentLine;
            
            if((sCurrentLine = b.readLine()) != null){
                String t[] = sCurrentLine.split("\\s");
                for (int i = 2; i < t.length; i++)
                    tari.add(t[i]);
            }
            while ((sCurrentLine = b.readLine()) != null) {
                String words[] = sCurrentLine.split("\\s");
                String denumire = words[0];
                String categorie = words[1];
                for(int i = 2, j = 0; i < words.length && j < tari.size(); i++, j++){
                    if(Double.parseDouble(words[i]) != 0){
                        Produs aux = new Produs(denumire, categorie, tari.get(j), Double.parseDouble(words[i]));
                        produse.add(aux);
                    }
                    Produs aux2 = new Produs(denumire, categorie, tari.get(j), Double.parseDouble(words[i]));
                    produseOut.add(aux2);
                }  
            }
            
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
        
        return produse;
    }
    
    public static HashMap<String,HashMap<String,Double>> citireTaxe(String path){
        HashMap<String,HashMap<String,Double>> taxe = new HashMap<>();
        BufferedReader b = null;
        FileReader f = null;
        Vector<String> tari = new Vector<>();
        try{
            f = new FileReader(path);
            b = new BufferedReader(f);

            String sCurrentLine;
            
            if((sCurrentLine = b.readLine()) != null){
                String t[] = sCurrentLine.split("\\s");
                for (int i = 1; i < t.length; i++){
                    tari.add(t[i]);
                    taxe.put(t[i], new HashMap<>());
                }
            }
            while ((sCurrentLine = b.readLine()) != null) {
               String words[] = sCurrentLine.split("\\s");
               String categorie = words[0];
               for(int i = 1, j = 0; i < words.length && j < tari.size(); i++, j++){
                   String tara = tari.get(j);
                   double procent = Double.parseDouble(words[i]);
                   taxe.get(tara).put(categorie, procent);
               }
            }

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
        
        return taxe;
    }
}
