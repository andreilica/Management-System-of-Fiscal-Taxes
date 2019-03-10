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
public class ProdusComandat {
    private Produs produs;
    private double taxa;
    private int cantitate;
    
    public ProdusComandat(Produs produs, double taxa, int cantitate){
        this.produs = produs;
        this.taxa = taxa;
        this.cantitate = cantitate;
    }
    
    public void setProdus(Produs produs){
        this.produs = produs;
    }
    
    public Produs getProdus(){
        return produs;
    }
    
    public void setTaxa(double taxa){
        this.taxa = taxa;
    }
    
    public double getTaxa(){
        return taxa;
    }
    
    public void setCantitate(int cantitate){
        this.cantitate = cantitate;
    }
    
    public int getCantitate(){
        return cantitate;
    }
    
    @Override
    public String toString(){
        return "\t" + produs + " " + taxa + " " + cantitate + '\n';
    }
}
