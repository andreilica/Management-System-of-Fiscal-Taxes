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
public class MiniMarket extends Magazin{
   

    public MiniMarket(String nume, TipMagazin Tip) {
        super(nume, Tip);
    }
    
    @Override
    public double calculScutiriTaxe(){
        double procent = 0;
        Vector<String> tari = new Vector<>();
        
        for(Iterator itr1 = facturi.iterator(); itr1.hasNext();){
            Factura f = (Factura) itr1.next();
            for(Iterator itr2 = f.pc.iterator(); itr2.hasNext();)
            {
                ProdusComandat p = (ProdusComandat) itr2.next();
                if(!tari.contains(p.getProdus().getTaraOrigine()))
                    tari.add(p.getProdus().getTaraOrigine());
            }
        }
        
        for(Iterator itr3 = tari.iterator(); itr3.hasNext();)
        {
            String tara = (String) itr3.next();
            if(getTotalTaraCuTaxe(tara) > 0.5 * getTotalCuTaxe())
                procent = 10;
        }
        
        return procent;
    }
}
