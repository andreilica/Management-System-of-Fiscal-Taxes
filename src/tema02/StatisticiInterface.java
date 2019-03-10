/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author andre
 */
public class StatisticiInterface extends JFrame {
    private Gestiune g;
    private Vector<String> tari;
    private Vector<String> categorii;
    private ArrayList<Produs> produse;
    
    public StatisticiInterface() {
        super("ShopFactory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        g = Gestiune.getInstance();
        produse = g.getProduse();
        
        tari = new Vector<>();
        categorii = new Vector<>();
        
        for(Iterator itr = produse.iterator(); itr.hasNext();){
            Produs aux = (Produs) itr.next();
            if(!tari.contains(aux.getTaraOrigine()))
                tari.add(aux.getTaraOrigine());
            if(!categorii.contains(aux.getCategorie()))
                categorii.add(aux.getCategorie());
        } 
        
        
        JPanel leftSide = new JPanel(new FlowLayout());
        JPanel rightSide = new JPanel(new FlowLayout());
        
        
        JButton backButton = new JButton("Meniul Principal");
        JButton top1Button = new JButton("Top Magazin");
        JButton top2Button = new JButton("Top Magazin Tara");
        JButton top3Button = new JButton("Top Magazin Categorie");
        JButton top4Button = new JButton("Top Factura");
        JComboBox categField = new JComboBox(categorii);
        JComboBox taraField = new JComboBox(tari);
        JLabel choose1 = new JLabel("Alege:");
        JLabel choose2 = new JLabel("Alege:");
        JLabel numeMagazin = new JLabel("");
        JLabel t1 = new JLabel("");
        JLabel t2 = new JLabel("");
        JLabel t3 = new JLabel("");
        JLabel total1 = new JLabel("");
        JLabel total2 = new JLabel("");
        JLabel total3 = new JLabel("");
        
        Font f = new Font("Nume", Font.BOLD, 15);
        numeMagazin.setFont(f);
        t1.setFont(f);
        t2.setFont(f);
        t3.setFont(f);
        
        top1Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == top1Button){
                    if(!g.getMagazine().isEmpty()){
                        Magazin max = g.getMagazine().get(0);

                        for(Iterator itr = g.getMagazine().iterator(); itr.hasNext();){
                            Magazin aux = (Magazin) itr.next();
                            if(aux.getTotalCuTaxe() > max.getTotalCuTaxe())
                                max = aux;
                        }

                        numeMagazin.setText(max.nume);
                        total1.setText(String.valueOf(max.getTotalFaraTaxe()));
                        total2.setText(String.valueOf(max.getTotalCuTaxe()));
                        total3.setText(String.valueOf(max.getTotalCuTaxeScutite()));
                        t1.setText("Total fara taxe:");
                        t2.setText("Total cu taxe:");
                        t3.setText("Total cu taxe scutite:");
                    } else {
                        JOptionPane.showMessageDialog(null, 
                        "Magazinele nu au fost incarcate!","Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        top2Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == top2Button){
                    if(!g.getMagazine().isEmpty()){
                        Magazin max = g.getMagazine().get(0);
                        String tara = taraField.getSelectedItem().toString();
                        
                        
                        for(Iterator itr = g.getMagazine().iterator(); itr.hasNext();){
                            Magazin aux = (Magazin) itr.next();
                            if(aux.getTotalTaraCuTaxe(tara) > max.getTotalTaraCuTaxe(tara))
                                max = aux;
                        }

                        numeMagazin.setText(max.nume);
                        total1.setText(String.valueOf(max.getTotalFaraTaxe()));
                        total2.setText(String.valueOf(max.getTotalCuTaxe()));
                        total3.setText(String.valueOf(max.getTotalCuTaxeScutite()));
                        t1.setText("Total fara taxe:");
                        t2.setText("Total cu taxe:");
                        t3.setText("Total cu taxe scutite:");
                    } else {
                        JOptionPane.showMessageDialog(null, 
                        "Magazinele nu au fost incarcate!","Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        top3Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == top3Button){
                    if(!g.getMagazine().isEmpty()){
                        Magazin max = g.getMagazine().get(0);
                        String categorie = categField.getSelectedItem().toString();
                        
                        
                        for(Iterator itr = g.getMagazine().iterator(); itr.hasNext();){
                            Magazin aux = (Magazin) itr.next();
                            if(aux.getTotalCategorieCuTaxe(categorie) > max.getTotalCategorieCuTaxe(categorie))
                                max = aux;
                        }

                        numeMagazin.setText(max.nume);
                        total1.setText(String.valueOf(max.getTotalFaraTaxe()));
                        total2.setText(String.valueOf(max.getTotalCuTaxe()));
                        total3.setText(String.valueOf(max.getTotalCuTaxeScutite()));
                        t1.setText("Total fara taxe:");
                        t2.setText("Total cu taxe:");
                        t3.setText("Total cu taxe scutite:");
                    } else {
                        JOptionPane.showMessageDialog(null, 
                        "Magazinele nu au fost incarcate!","Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        top4Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == top4Button){
                    if(!g.getMagazine().isEmpty()){
                        Magazin max = g.getMagazine().get(0);
                        Factura fmax = g.getMagazine().get(0).facturi.get(0);
                        for(Iterator itr = g.getMagazine().iterator(); itr.hasNext();){
                            Magazin aux = (Magazin) itr.next();
                            for(Iterator itr2 = aux.facturi.iterator(); itr2.hasNext();){
                                Factura f = (Factura) itr2.next();
                                if(f.getTotalCuTaxe() > fmax.getTotalCuTaxe()){
                                    fmax = f;
                                    max = aux;
                                }
                            }
                        }

                        numeMagazin.setText(fmax.denumire);
                        total1.setText(String.valueOf(max.getTotalFaraTaxe()));
                        total2.setText(String.valueOf(max.getTotalCuTaxe()));
                        total3.setText(max.nume);
                        t1.setText("Total fara taxe:");
                        t2.setText("Total cu taxe:");
                        t3.setText("Magazin provenienta:");
                    } else {
                        JOptionPane.showMessageDialog(null, 
                        "Magazinele nu au fost incarcate!","Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == backButton){
                    StartPage start = new StartPage();
                    dispose();
                }
            }
        });
        
        leftSide.add(top1Button);
        leftSide.add(top2Button);
        leftSide.add(choose1);
        leftSide.add(taraField);
        leftSide.add(top3Button);
        leftSide.add(choose2);
        leftSide.add(categField);
        leftSide.add(top4Button);
        leftSide.add(backButton);
        
        rightSide.add(numeMagazin);
        rightSide.add(t1);
        rightSide.add(total1);
        rightSide.add(t2);
        rightSide.add(total2);
        rightSide.add(t3);
        rightSide.add(total3);
        
        leftSide.setPreferredSize(new Dimension(160, 265));

        add(leftSide, BorderLayout.WEST);
        add(rightSide);
        setSize(800,265);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
