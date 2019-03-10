/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

import java.util.Iterator;

/**
 *
 * @author andre
 */
public class HyperMarket extends Magazin{
    
    public HyperMarket(String nume, TipMagazin Tip) {
        super(nume, Tip);
    }
    
    @Override
    public double calculScutiriTaxe(){
        double procent = 0;
        
        for(Iterator itr = facturi.iterator(); itr.hasNext();){
            Factura f = (Factura) itr.next();
            if(f.getTotalCuTaxe() > 0.01 * getTotalCuTaxe())
                procent = 1;
        }
        
        return procent;
    }
}
