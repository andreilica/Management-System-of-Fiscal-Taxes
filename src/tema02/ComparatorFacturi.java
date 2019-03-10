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
public class ComparatorFacturi implements Comparator {
    @Override
    public int compare(Object o1, Object o2){
        if(o1 == null || o2 == null)
            throw new NullPointerException();
        if(!(o1 instanceof Factura && o2 instanceof Factura))
            throw new ClassCastException("Obiectele nu sunt instante ale clasei Factura!");
        Factura f1 = (Factura) o1;
        Factura f2 = (Factura) o2;
        double total1 = f1.getTotalCuTaxe();
        double total2 = f2.getTotalCuTaxe();
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