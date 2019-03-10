/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfataGrafica extends JFrame implements ActionListener{
    public InterfataGrafica () {
        super("ShopFactory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        JButton login = new JButton("Login");
        JButton exit = new JButton("Iesire");
        JLabel icon = new JLabel(new ImageIcon("rsz_lock.png"));
        JLabel welcome = new JLabel("Bine ati venit!");
        JPanel buttons = new JPanel(new FlowLayout());
        JPanel panel1 = new JPanel();
        
        welcome.setFont(new Font("Welcome", Font.BOLD, 15));

        panel1.add(welcome);
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.CENTER);
        add(icon, BorderLayout.NORTH);
        buttons.add(login);
        buttons.add(exit);
        add(buttons, BorderLayout.SOUTH);
        
        login.addActionListener(this);
        exit.addActionListener(this);
        getRootPane().setDefaultButton(login);

        setSize(250,230);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String nume = e.getActionCommand();
        if(nume.equals("Login")){
            FereastraLogare logare= new FereastraLogare(this);
            logare.setVisible(true);
            if(logare.verificareAutentificare()){
                StartPage start = new StartPage();
                dispose();
            }
        } else if(nume.equals("Iesire")){
            System.exit(0);
        }
                              
    }
}
