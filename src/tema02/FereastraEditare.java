/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author andre
 */

public class FereastraEditare extends JDialog implements ActionListener{
    private String numeProd;
    private String categorieProd;
    private String taraProd;
    private double pretProd;
    private Gestiune g;
    private Vector<String> tari;
    private Vector<String> categorii;
    private JLabel numeLabel;
    private JLabel categLabel;
    private JLabel taraLabel;
    private JLabel pretLabel;
    private JTextField numeField;
    private JComboBox categField;
    private JComboBox taraField;
    private JTextField pretField;
    
    public FereastraEditare(Frame parinte, ArrayList<Produs> produse, Produs produs){
        super(parinte, "Editare Produs", true);
        
        tari = new Vector<>();
        categorii = new Vector<>();
        
        for(Iterator itr = produse.iterator(); itr.hasNext();){
            Produs aux = (Produs) itr.next();
            if(!tari.contains(aux.getTaraOrigine()))
                tari.add(aux.getTaraOrigine());
            if(!categorii.contains(aux.getCategorie()))
                categorii.add(aux.getCategorie());
        }     
        
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        JPanel panel5 = new JPanel(new FlowLayout());
        numeLabel = new JLabel("Nume:");
        categLabel = new JLabel("Categ:");
        taraLabel = new JLabel("Tara:");
        pretLabel = new JLabel("Pret:");
        numeField = new JTextField(10);
        categField = new JComboBox(categorii);
        taraField = new JComboBox(tari);
        pretField = new JTextField(10);
        JButton editare = new JButton("Editare");
        JButton cancel = new JButton("Cancel");
        
        numeField.setText(produs.getDenumire());
        categField.setSelectedItem(produs.getCategorie());
        taraField.setSelectedItem(produs.getTaraOrigine());
        pretField.setText(String.valueOf(produs.getPret()));
        
        editare.addActionListener(this);
        cancel.addActionListener(this);
        
        panel1.add(numeLabel);
        panel1.add(numeField);
        panel2.add(categLabel);
        panel2.add(categField);
        panel3.add(taraLabel);
        panel3.add(taraField);
        panel4.add(pretLabel);
        panel4.add(pretField);
        panel5.add(editare);
        panel5.add(cancel);
        setLayout(new FlowLayout());
        getContentPane().add(panel1);
        getContentPane().add(panel2);
        getContentPane().add(panel3);
        getContentPane().add(panel4);
        getContentPane().add(panel5);
        getRootPane().setDefaultButton(editare);
        setSize(280,210);
        setResizable(false);
        setLocationRelativeTo(parinte);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String nume = e.getActionCommand();
        if(nume.equals("Editare")){
            if(numeField.getText().isEmpty() || pretField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, 
                    "Nu au fost completate toate campurile!","Atentie", JOptionPane.WARNING_MESSAGE);
            }
            else{
                numeProd = numeField.getText();
                categorieProd = categField.getSelectedItem().toString();
                taraProd = taraField.getSelectedItem().toString();
                pretProd = Double.parseDouble(pretField.getText());
                returnProdus();
                dispose();
            }
        } else if(nume.equals("Cancel")){
            dispose();
        }
                         
    }
    
    public Produs returnProdus(){
        return new Produs(numeProd, categorieProd, taraProd, pretProd);
    }
}
