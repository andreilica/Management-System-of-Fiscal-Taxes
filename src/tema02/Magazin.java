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
public abstract class Magazin implements IMagazin{
    String nume;
    TipMagazin Tip;
    Vector<Factura> facturi;
    
    public Magazin(String nume, TipMagazin Tip){
        this.nume = nume;
        facturi = new Vector<>();
        this.Tip = Tip;
    }

    @Override
    public double getTotalFaraTaxe() {
        double total = 0;
         for(Iterator itr = facturi.iterator(); itr.hasNext();){
            Factura f = (Factura) itr.next();
            total += f.getTotalFaraTaxe();
         }
         return (double)Math.round(total * 10000d) / 10000d;
    }

    @Override
    public double getTotalCuTaxe() {
        double total = 0;
        for(Iterator itr = facturi.iterator(); itr.hasNext();){
            Factura f = (Factura) itr.next();
            total += f.getTotalCuTaxe();
        }
        return (double)Math.round(total * 10000d) / 10000d;
    }

    @Override
    public double getTotalCuTaxeScutite() {
        double total;
        if(calculScutiriTaxe() != 0){
            total = getTotalCuTaxe() - getTotalCuTaxe() * calculScutiriTaxe() / 100;
        }
        else
            total = getTotalCuTaxe();
        return (double)Math.round(total * 10000d) / 10000d;
    }

    @Override
    public double getTotalTaraFaraTaxe(String tara) {
        double total = 0;
        for(Iterator itr = facturi.iterator(); itr.hasNext();){
            Factura f = (Factura) itr.next();
            total += f.getTotalTaraFaraTaxe(tara);
        }
        return (double)Math.round(total * 10000d) / 10000d;
    }

    @Override
    public double getTotalTaraCuTaxe(String tara) {
        double total = 0;
        for(Iterator itr = facturi.iterator(); itr.hasNext();){
            Factura f = (Factura) itr.next();
            total += f.getTotalTaraCuTaxe(tara);
        }
        return (double)Math.round(total * 10000d) / 10000d;
    }
    
    public double getTotalCategorieCuTaxe(String categorie) {
        double total = 0;
        for(Iterator itr = facturi.iterator(); itr.hasNext();){
            Factura f = (Factura) itr.next();
            total += f.getTotalCategorieCuTaxe(categorie);
        }
        return (double)Math.round(total * 10000d) / 10000d;
    }

    @Override
    public double getTotalTaraCuTaxeScutite(String tara) {
        double total;
        if(calculScutiriTaxe() != 0){
            total = getTotalTaraCuTaxe(tara) - getTotalTaraCuTaxe(tara) * calculScutiriTaxe() / 100;
        }
        else
            total = getTotalTaraCuTaxe(tara);
        return (double)Math.round(total * 10000d) / 10000d;
    }
    
    
    @Override
    public String toString(){
        String ret = "";
        ret = ret + nume + '\n';
        ret = ret + facturi;
        ret = ret + '\n';
        return ret;
    }
}
