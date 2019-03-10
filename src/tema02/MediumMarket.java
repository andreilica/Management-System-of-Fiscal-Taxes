/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author andre
 */
public class MediumMarket extends Magazin{

    public MediumMarket(String nume, TipMagazin Tip) {
        super(nume, Tip);
    }
    
    @Override
    public double calculScutiriTaxe(){
        double procent = 0;
        Vector<String> categorii = new Vector<>();
        
        for(Iterator itr1 = facturi.iterator(); itr1.hasNext();){
            Factura f = (Factura) itr1.next();
            for(Iterator itr2 = f.pc.iterator(); itr2.hasNext();)
            {
                ProdusComandat p = (ProdusComandat) itr2.next();
                if(!categorii.contains(p.getProdus().getCategorie()))
                    categorii.add(p.getProdus().getCategorie());
            }
        }
        
        for(Iterator itr3 = categorii.iterator(); itr3.hasNext();){
            double total = 0;
            String categorie = (String) itr3.next();
            
            for(Iterator itr1 = facturi.iterator(); itr1.hasNext();){
                Factura f = (Factura) itr1.next();
                for(Iterator itr2 = f.pc.iterator(); itr2.hasNext();){
                    ProdusComandat p = (ProdusComandat) itr2.next();
                    if(p.getProdus().getCategorie().equals(categorie))
                        total += (p.getProdus().getPret() + p.getProdus().getPret() * p.getTaxa()/100 ) * p.getCantitate(); 
                }
            }
            
            if(total > 0.05 * getTotalCuTaxe())
                procent = 5;
        }
        
        return procent;
    }
}
