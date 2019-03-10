/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

/**
 *
 * @author andre
 */
public class MagazinFactory {
    
    public static Magazin creareMagazin(TipMagazin tip, String nume) {
        Magazin magazin = null;
        switch (tip) {
        case MiniMarket:
            magazin = new MiniMarket(nume, tip);
            break;
 
        case MediumMarket:
            magazin = new MediumMarket(nume, tip);
            break;
 
        case HyperMarket:
            magazin = new HyperMarket(nume, tip);
            break;
 
        default:
            throw new IllegalArgumentException("Tipul magazinului" + tip + " nu este recunoscut.");
        }
        
        return magazin;
    }
}
