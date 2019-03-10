/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author andre
 */


class ListaProduse extends AbstractListModel{
    ArrayList<Produs> produse;

    public ListaProduse(ArrayList<Produs> produse){
        this.produse = produse;
    }

    @Override
    public int getSize(){
        return produse.size();
    }

    @Override
    public Object getElementAt(int index){
        return (Produs)produse.get(index);
    }
    
    public void remove(int index) {
        produse.remove(index);
        fireContentsChanged(this, 0, produse.size());
    }
    
    public void addProdus(Produs prod){
        produse.add(prod);
        fireContentsChanged(this, 0, produse.size());
    }
    
    public void editProdus(Produs prodInit, Produs prodNew){
        prodInit.setDenumire(prodNew.getDenumire());
        prodInit.setCategorie(prodNew.getCategorie());
        prodInit.setTaraOrigine(prodNew.getTaraOrigine());
        prodInit.setPret(prodNew.getPret());
        fireContentsChanged(this, 0, produse.size());
    }
    public void setList(ArrayList<Produs> produse){
        this.produse = produse;
    }

    public void sortDenumire(){
        Collections.sort(produse, new ComparatorProduseDenumire());
        fireContentsChanged(this, 0, produse.size());
    }
    
    public ArrayList<Produs> getProduse(){
        return produse;
    }
    
    public void sortTara(){
        Collections.sort(produse, new ComparatorProduseTara());
        fireContentsChanged(this, 0, produse.size());
    }

    
}

public class ProduseInterface extends JFrame {
    private String pathProd;
    private ArrayList<Produs> produse;
    private ListaProduse model;
    private int index = -1;
    private Writer writer = null;
    private boolean flag = true;
    private boolean flag2 = false;
    private Vector<String> tari;
    private ArrayList<Produs> produseOut;
    public ProduseInterface() {
        super("ShopFactory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JFileChooser fileChooser = new JFileChooser();
        File project = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(project);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        tari = new Vector<>();
        
        JButton backButton = new JButton("Meniul Principal");
        JButton sortButton = new JButton("Sortare");
        JButton prodButton = new JButton("Rasfoire");
        JButton adaugButton = new JButton("Adaugare Produs");
        JButton stergButton = new JButton("Stergere Produs");
        JButton editButton = new JButton("Editare Produs");
        JButton cautButton = new JButton("Cautare Produs");
        JLabel prodLabel = new JLabel("Incarcare fisier produse:");
        JLabel icon = new JLabel(new ImageIcon("documents.png"));
        JRadioButton denumButton = new JRadioButton("Denumire");
        JRadioButton taraButton = new JRadioButton("Tara");
        
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(denumButton);
        radioGroup.add(taraButton);
        
        JPanel rightSide = new JPanel(new FlowLayout());
        JPanel picture = new JPanel(new FlowLayout());
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        JPanel panel4 = new JPanel(new FlowLayout());
        final JList lista = new JList();
                        
        prodButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == prodButton)
                    fileChooser.setDialogTitle("Deschideti fisierul cu produsele");
                    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                        pathProd = fileChooser.getSelectedFile().getAbsolutePath();
                        if(pathProd.contains("produse.txt")){
                            produse = Parsare.citireProduse(pathProd);
                            model = new ListaProduse(produse);
                            lista.setModel(model);
                            
                            produseOut = Parsare.getProduseOut();
                            
                            for(Iterator itr = produse.iterator(); itr.hasNext();){
                                Produs aux = (Produs) itr.next();
                                if(!tari.contains(aux.getTaraOrigine()))
                                    tari.add(aux.getTaraOrigine());
                            }
                            
                        } else{
                            JOptionPane.showMessageDialog(null, 
                    "Fisierul incarcat nu este de tip produse!","Eroare", JOptionPane.ERROR_MESSAGE);
                        }
                    }
            }
        });
        
        sortButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == sortButton){
                    if(produse == null){
                        JOptionPane.showMessageDialog(null, 
                    "Nu a fost incarcat fisierul cu produsele!","Atentie", JOptionPane.WARNING_MESSAGE);
                    }else{
                        if(denumButton.isSelected()){
                            model.sortDenumire();
                        } else if(taraButton.isSelected()){
                            model.sortTara();
                        } else{
                            JOptionPane.showMessageDialog(null, 
                        "Nu a fost selectat niciun tip de sortare!","Atentie", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }       
            }
        });
        
        adaugButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == adaugButton){
                    if(produse == null){
                        JOptionPane.showMessageDialog(null, 
                    "Nu a fost incarcat fisierul cu produsele!","Atentie", JOptionPane.WARNING_MESSAGE);
                    }else{
                        FereastraAdaugare fa = new FereastraAdaugare(null, produse);
                        fa.setVisible(true);
                        if(fa.returnProdus().getDenumire() != null){
                            flag = true;
                            for(Iterator itr = produse.iterator(); itr.hasNext();){
                                Produs aux = (Produs) itr.next();
                                String n = aux.getDenumire();
                                String c = aux.getCategorie();
                                String t = aux.getTaraOrigine();
                                Double p = aux.getPret();
                                if(n.equals(fa.returnProdus().getDenumire()) && c.equals(fa.returnProdus().getCategorie()) 
                                        && t.equals(fa.returnProdus().getTaraOrigine())){
                                    flag = false;
                                }
                            }   
                                if(flag){
                                    Produs nou = fa.returnProdus();
                                    flag2 = false;
                                    model.addProdus(nou);
                                    Produs prodMod = null;
                                    for(Iterator itr = produseOut.iterator(); itr.hasNext();){
                                        Produs prod = (Produs) itr.next();
                                        if(prod.getDenumire().equals(nou.getDenumire()) && prod.getCategorie().equals(nou.getCategorie())
                                            && prod.getTaraOrigine().equals(nou.getTaraOrigine())){
                                            flag2 = true;
                                            prodMod = prod;
                                        }
                                            
                                    } 
                                    
                                    if(flag2 && prodMod != null){
                                        prodMod.setPret(nou.getPret());
                                    } else{
                                        for(Iterator itr3 = tari.iterator(); itr3.hasNext();){
                                            String tara = (String) itr3.next();
                                            if(!nou.getTaraOrigine().equals(tara)){
                                                Produs aux = new Produs(nou.getDenumire(), nou.getCategorie(), tara, 0);
                                                produseOut.add(aux);
                                            } else {
                                                produseOut.add(nou);
                                            }
                                        }
                                    }    
                                    try {
                                        String firstLine = "Produs Categorie ";
                                        for(Iterator itr1 = tari.iterator();itr1.hasNext();){
                                            String tara = (String) itr1.next();
                                            firstLine = firstLine + tara + " ";
                                        }
                                        
                                        writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(pathProd), "utf-8"));
                                        writer.write(firstLine);
                                        writer.write(System.getProperty( "line.separator" ));
                                        
                                        String line = "";
                                        Produs p = produseOut.get(0);
                                        String nume1 = p.getDenumire();
                                        line = line + nume1 + " " + p.getCategorie() + " ";
                                        
                                        for(Iterator itr = produseOut.iterator();itr.hasNext();){
                                            Produs aux = (Produs) itr.next();
                                            String nume = aux.getDenumire();
                                            if(nume.equals(nume1)){
                                                line = line + String.valueOf(aux.getPret()) + " ";
                                            }else{
                                                writer.write(line);
                                                writer.write(System.getProperty( "line.separator" ));
                                                line = "";
                                                p = aux;
                                                nume1 = p.getDenumire();
                                                line = line + nume1 + " " + p.getCategorie() + " " + String.valueOf(p.getPret()) + " ";
                                            }
                                            
                                        }
                                        writer.write(line);

                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    } finally {
                                        try {
                                            writer.close();
                                        } catch (IOException ex) {
                                            ex.printStackTrace();
                                        }
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, 
                                "Produsul exista deja in lista de produse!","Atentie", JOptionPane.WARNING_MESSAGE);
                                }
                        }
                    }
                }
            }
        });
        
        cautButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == cautButton){
                    if(produse == null){
                        JOptionPane.showMessageDialog(null, 
                    "Nu a fost incarcat fisierul cu produsele!","Atentie", JOptionPane.WARNING_MESSAGE);
                    }else{
                        FereastraCautare fc = new FereastraCautare(null, produse);
                        fc.setVisible(true);
                    }
                }
            }
        });
        
        
        editButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == editButton){
                    if(produse == null){
                        JOptionPane.showMessageDialog(null, 
                    "Nu a fost incarcat fisierul cu produsele!","Atentie", JOptionPane.WARNING_MESSAGE);
                    }else{
                        if(index == -1 || index >= model.getProduse().size()){
                            JOptionPane.showMessageDialog(null, 
                            "Nu a fost selectat niciun produs!","Atentie", JOptionPane.WARNING_MESSAGE);
                        } else{
                            Produs selected = model.getProduse().get(index);
                            Produs selectedcpy = new Produs(selected.getDenumire(), selected.getCategorie(), selected.getTaraOrigine(), selected.getPret());
                            FereastraEditare fe = new FereastraEditare(null, produse, selected);
                            fe.setVisible(true);
                            if(fe.returnProdus().getDenumire() != null)
                                model.editProdus(selected, fe.returnProdus());
                            
                            for(Iterator itr = produseOut.iterator(); itr.hasNext();){
                                Produs prd = (Produs) itr.next();
                                if(prd.getDenumire().equals(selectedcpy.getDenumire()) && prd.getCategorie().equals(selectedcpy.getCategorie())
                                    && prd.getTaraOrigine().equals(selectedcpy.getTaraOrigine())){
                                    prd.setPret(fe.returnProdus().getPret());
                                }
                            }
                            
                            try {
                            String firstLine = "Produs Categorie ";
                            for(Iterator itr1 = tari.iterator();itr1.hasNext();){
                                String tara = (String) itr1.next();
                                firstLine = firstLine + tara + " ";
                            }
                                        
                            writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(pathProd), "utf-8"));
                            writer.write(firstLine);
                            writer.write(System.getProperty( "line.separator" ));
                                        
                            String line = "";
                            Produs p = produseOut.get(0);
                            String nume1 = p.getDenumire();
                            line = line + nume1 + " " + p.getCategorie() + " ";
                                        
                            for(Iterator itr = produseOut.iterator();itr.hasNext();){
                                Produs pr = (Produs) itr.next();
                                String nume = pr.getDenumire();
                                if(nume.equals(nume1)){
                                    line = line + String.valueOf(pr.getPret()) + " ";
                                }else{
                                    writer.write(line);
                                    writer.write(System.getProperty( "line.separator" ));
                                    line = "";
                                    p = pr;
                                    nume1 = p.getDenumire();
                                    line = line + nume1 + " " + p.getCategorie() + " " + String.valueOf(p.getPret()) + " ";
                                }
                                            
                            }
                            writer.write(line);

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } finally {
                                try {
                                    writer.close();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            
                            
                        }
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
        
        stergButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == stergButton){
                    if(index == -1 || index >= model.getProduse().size()){
                        JOptionPane.showMessageDialog(null, 
                        "Nu a fost selectat niciun produs!","Atentie", JOptionPane.WARNING_MESSAGE);
                    } else if(model.getSize() != 0){
                        Produs aux = model.getProduse().get(index);
                        model.remove(index);
                        
                        
                        
                        for(Iterator itr = produseOut.iterator(); itr.hasNext();){
                            Produs prod = (Produs) itr.next();
                            if(prod.getDenumire().equals(aux.getDenumire()) && prod.getCategorie().equals(aux.getCategorie())
                                    && prod.getTaraOrigine().equals(aux.getTaraOrigine()))
                                prod.setPret(0);
                        }
                        
                        try {
                            String firstLine = "Produs Categorie ";
                            for(Iterator itr1 = tari.iterator();itr1.hasNext();){
                                String tara = (String) itr1.next();
                                firstLine = firstLine + tara + " ";
                            }
                                        
                            writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(pathProd), "utf-8"));
                            writer.write(firstLine);
                            writer.write(System.getProperty( "line.separator" ));
                                        
                            String line = "";
                            Produs p = produseOut.get(0);
                            String nume1 = p.getDenumire();
                            line = line + nume1 + " " + p.getCategorie() + " ";
                                        
                            for(Iterator itr = produseOut.iterator();itr.hasNext();){
                                Produs pr = (Produs) itr.next();
                                String nume = pr.getDenumire();
                                if(nume.equals(nume1)){
                                    line = line + String.valueOf(pr.getPret()) + " ";
                                }else{
                                    writer.write(line);
                                    writer.write(System.getProperty( "line.separator" ));
                                    line = "";
                                    p = pr;
                                    nume1 = p.getDenumire();
                                    line = line + nume1 + " " + p.getCategorie() + " " + String.valueOf(p.getPret()) + " ";
                                }
                                            
                            }
                            writer.write(line);

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } finally {
                                try {
                                    writer.close();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        
                    } else{
                        JOptionPane.showMessageDialog(null, 
                        "Lista este goala!","Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                        
                }
            }
        });
        
        
        lista.setSelectionMode ( ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        lista.setVisibleRowCount(-1);
        
        DefaultListCellRenderer renderer =  (DefaultListCellRenderer)lista.getCellRenderer();  
        renderer.setHorizontalAlignment(JLabel.CENTER);  
        
        JScrollPane listScroller = new JScrollPane(lista);
        listScroller.setPreferredSize(new Dimension(300, 150));
        
        lista.addListSelectionListener( new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                if(e.getValueIsAdjusting()){
                    index = lista.getSelectedIndex();
                }
            }
        });
        
        
        add(listScroller, BorderLayout.WEST);
        
        picture.add(icon);
        rightSide.add(picture);
        
        panel1.add(prodLabel);
        panel1.add(prodButton);
        rightSide.add(panel1);
        
        panel2.add(sortButton);
        panel2.add(denumButton);
        panel2.add(taraButton);
        rightSide.add(panel2);
       
        panel3.add(adaugButton);
        panel3.add(stergButton);
        panel3.add(editButton);
        panel3.add(cautButton);
        rightSide.add(panel3);
        
        
        panel4.add(backButton);
        rightSide.add(panel4);
        add(rightSide);
        
        setSize(800,410);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
