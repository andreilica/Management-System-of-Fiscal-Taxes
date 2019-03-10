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
public class ComparatorProduseDenumire implements Comparator {
    @Override
    public int compare(Object o1, Object o2){
        if(o1 == null || o2 == null)
            throw new NullPointerException();
        if(!(o1 instanceof Produs && o2 instanceof Produs))
            throw new ClassCastException("Obiectele nu sunt instante ale clasei Produs!");
        Produs p1 = (Produs) o1;
        Produs p2 = (Produs) o2;
        return p1.getDenumire().compareTo(p2.getDenumire());
    }
}