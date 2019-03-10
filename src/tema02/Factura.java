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
public class Factura {
    String denumire;
    Vector<ProdusComandat> pc;
    
    public Factura(String denumire){
        this.denumire = denumire;
        pc = new Vector<>();
    }
    
    public double getTotalFaraTaxe(){
        double total = 0;
        for(Iterator itr = pc.iterator(); itr.hasNext();){
            ProdusComandat p = (ProdusComandat) itr.next();
            total += p.getProdus().getPret() * p.getCantitate();
        }
        return (double)Math.round(total * 10000d) / 10000d;
    }
    
    
    public double getTotalCuTaxe(){
        double total = 0;
        for(Iterator itr = pc.iterator(); itr.hasNext();){
            ProdusComandat p = (ProdusComandat) itr.next();
            total += (p.getProdus().getPret() + p.getProdus().getPret() * p.getTaxa()/100 ) * p.getCantitate();
        }
        return (double)Math.round(total * 10000d) / 10000d;
    }
    
    public double getTaxe(){
        double total = 0;
        for(Iterator itr = pc.iterator(); itr.hasNext();){
            ProdusComandat p = (ProdusComandat) itr.next();
            total += p.getTaxa()/100;
        }
        return (double)Math.round(total * 10000d) / 10000d;
    }
    
    public double getTotalTaraFaraTaxe(String tara){
        double total = 0;
         for(Iterator itr = pc.iterator(); itr.hasNext();){
            ProdusComandat p = (ProdusComandat) itr.next();
            if(p.getProdus().getTaraOrigine().equals(tara))
                total += p.getProdus().getPret() * p.getCantitate();
         }
         return (double)Math.round(total * 10000d) / 10000d;
    }
    
    public double getTotalTaraCuTaxe(String tara){
        double total = 0;
         for(Iterator itr = pc.iterator(); itr.hasNext();){
            ProdusComandat p = (ProdusComandat) itr.next();
            if(p.getProdus().getTaraOrigine().equals(tara))
                total += (p.getProdus().getPret() + p.getProdus().getPret() * p.getTaxa()/100 ) * p.getCantitate();
         }
         return (double)Math.round(total * 10000d) / 10000d;
    }
    
    public double getTotalCategorieCuTaxe(String categorie){
        double total = 0;
         for(Iterator itr = pc.iterator(); itr.hasNext();){
            ProdusComandat p = (ProdusComandat) itr.next();
            if(p.getProdus().getCategorie().equals(categorie))
                total += (p.getProdus().getPret() + p.getProdus().getPret() * p.getTaxa()/100 ) * p.getCantitate();
         }
         return (double)Math.round(total * 10000d) / 10000d;
    }
    
    public double getTaxeTara(String tara){
        double total = 0;
        for(Iterator itr = pc.iterator(); itr.hasNext();){
            ProdusComandat p = (ProdusComandat) itr.next();
            if(p.getProdus().getTaraOrigine().equals(tara))
                total += p.getTaxa()/100;
        }
        return (double)Math.round(total * 10000d) / 10000d;
    }
    
    @Override
    public String toString(){
        String ret = "";
        ret = ret + denumire + '\n';
        ret = ret + pc;
        return ret;
    }
}
