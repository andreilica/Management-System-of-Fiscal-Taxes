/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

import java.util.Comparator;

/**
 *
 * @author andre
 */
public class ComparatorMagazine implements Comparator {
    @Override
    public int compare(Object o1, Object o2){
        if(o1 == null || o2 == null)
            throw new NullPointerException();
        if(!(o1 instanceof Magazin && o2 instanceof Magazin))
            throw new ClassCastException("Obiectele nu sunt instante ale clasei Magazin!");
        Magazin m1 = (Magazin) o1;
        Magazin m2 = (Magazin) o2;
        double total1 = m1.getTotalFaraTaxe();
        double total2 = m2.getTotalFaraTaxe();
        if(total1 < total2){
            return -1;
        }
        else if(total1 > total2){
            return 1;
        }
        else{
            return 0;
        }
    }
}