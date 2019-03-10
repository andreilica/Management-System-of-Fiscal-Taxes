/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author andre
 */

class Logare{
    public static boolean autentificare(String user, String parola){
        BufferedReader b = null;
        FileReader f = null;
        Vector<String> utilizatori = new Vector<>();
        Vector<String> parole = new Vector<>();
        try{
            f = new FileReader("users.txt");
            b = new BufferedReader(f);
            
            String sCurrentLine;
           
            while ((sCurrentLine = b.readLine()) != null) {
                String words[] = sCurrentLine.split(":");
                utilizatori.add(words[0]);
                parole.add(words[1]);
            }

        }catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {

                if (b != null)
                    b.close();
                if (f != null)
                    f.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
        for(int i = 0; i < utilizatori.size(); i++){
            if(user.equals(utilizatori.get(i)) && parola.equals(parole.get(i)))
                return true;
        }
        return false;
    }
}

public class FereastraLogare extends JDialog implements ActionListener{
    private JTextField userField;
    private JPasswordField passField;
    private JLabel userLabel;
    private JLabel passLabel;
    private JButton logare;
    private JButton cancel;
    private boolean flag;
    
    public FereastraLogare(Frame parinte){
        super(parinte, "Logare", true);
        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel(new FlowLayout());
        userLabel = new JLabel("User:");
        passLabel = new JLabel("Pass:");
        userField = new JTextField(10);
        passField = new JPasswordField(10);
        logare = new JButton("Logare");
        cancel = new JButton("Cancel");

        logare.addActionListener(this);
        cancel.addActionListener(this);
        
        panel1.add(userLabel);
        panel1.add(userField);
        panel2.add(passLabel);
        panel2.add(passField);
        panel3.add(logare);
        panel3.add(cancel);
        
        getContentPane().add(panel1, BorderLayout.PAGE_START);
        getContentPane().add(panel2, BorderLayout.CENTER);
        getContentPane().add(panel3, BorderLayout.PAGE_END);
        getRootPane().setDefaultButton(logare);
        setSize(300,160);
        setResizable(false);
        setLocationRelativeTo(parinte);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String nume = e.getActionCommand();
        if(nume.equals("Logare")){
            if(Logare.autentificare(userField.getText(), new String(passField.getPassword()))){
                flag = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(FereastraLogare.this, 
                    "Numele de utilizator sau parola incorecta!","Eroare", JOptionPane.ERROR_MESSAGE);
                userField.setText("");
                passField.setText("");
                flag = false;
            }
        } else if(nume.equals("Cancel")){
            dispose();
        }
                         
    }
    
    public boolean verificareAutentificare(){
        return flag;
    }
}
