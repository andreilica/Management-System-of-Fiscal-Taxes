package tema02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GestiuneInterface extends JFrame{
    private String pathProd;
    private String pathTax;
    private String pathFact;
    private boolean flag = false;
    public GestiuneInterface () {
        super("ShopFactory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gestiune g = Gestiune.getInstance();
        
        JFileChooser fileChooser = new JFileChooser();
        File project = new File(System.getProperty("user.dir"));
        fileChooser.setCurrentDirectory(project);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        JLabel prodLabel = new JLabel("Incarcati fisierul cu produsele: ");
        JLabel taxLabel = new JLabel("Incarcati fisierul cu taxele: ");
        JLabel factLabel = new JLabel("Incarcati fisierul cu facturile: ");
        JButton prodButton = new JButton("Rasfoire");
        JButton taxButton = new JButton("Rasfoire");
        JButton factButton = new JButton("Rasfoire");
        JButton outButton = new JButton("Creare Fisier");
        JButton backButton = new JButton("Meniul Principal");
        JLabel title = new JLabel("Pagina de creare a fisierului de output");
        
        setLayout(new BorderLayout());
        JPanel top = new JPanel(new FlowLayout());
        JPanel center = new JPanel(new FlowLayout());
        JPanel left = new JPanel(new GridLayout(2,1));
        JPanel middle = new JPanel(new GridLayout(2,1));
        JPanel right = new JPanel(new GridLayout(2,1));
        JPanel bottom = new JPanel(new FlowLayout());
        
        top.add(title);
        
        bottom.add(outButton);
        bottom.add(backButton);
        
        left.add(prodLabel);
        left.add(prodButton);
        middle.add(taxLabel);
        middle.add(taxButton);
        right.add(factLabel);
        right.add(factButton);
        center.add(left);
        center.add(middle);
        center.add(right);
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        
        prodButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == prodButton)
                    fileChooser.setDialogTitle("Deschideti fisierul cu produsele");
                    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                        pathProd = fileChooser.getSelectedFile().getAbsolutePath();
                    }
            }
        });
        
        taxButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Deschideti fisierul cu taxele");
                if(e.getSource() == taxButton)
                    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                        pathTax = fileChooser.getSelectedFile().getAbsolutePath();
                    }
            }
        });
        
        factButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == factButton){
                    fileChooser.setDialogTitle("Deschideti fisierul cu facturile");
                    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                        pathFact = fileChooser.getSelectedFile().getAbsolutePath();
                        g.setMagazin(pathProd, pathTax, pathFact);
                    }
                }
            }
        });
        
        outButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == outButton){
                    if(pathProd == null || pathTax == null || pathFact == null){
                        JOptionPane.showMessageDialog(null, 
                    "Unul sau mai multe din cele 3 fisiere nu au fost selectate!","Eroare", JOptionPane.ERROR_MESSAGE);
                    } else if(!flag){
                        JOptionPane.showMessageDialog(null, 
                    "Fisierul a fost creat cu succes!","Succes", JOptionPane.INFORMATION_MESSAGE);
                        g.scriereFisier();
                        flag = true;
                        try {
                            Desktop.getDesktop().open(new File("out.txt"));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, 
                    "Fisierul a fost deja creat!","Atentie", JOptionPane.WARNING_MESSAGE);
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
        
        setSize(600,200);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
}

