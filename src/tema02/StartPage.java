package tema02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends JFrame implements ActionListener{
    public StartPage () {
        super("ShopFactory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b1 = new JButton("Gestiune");
        JButton b2 = new JButton("Produse");
        JButton b3 = new JButton("Statistici");
        JButton exit = new JButton("Iesire");
        JPanel panel = new JPanel(new GridLayout(2,2));
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(exit);
        add(panel);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        exit.addActionListener(this);
        
        setSize(400,300);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String nume = e.getActionCommand();
        switch (nume) {
            case "Gestiune":
                GestiuneInterface gi = new GestiuneInterface();
                dispose();
                break;
            case "Produse":
                ProduseInterface pi = new ProduseInterface();
                dispose();
                break;
            case "Statistici":
                StatisticiInterface si = new StatisticiInterface();
                dispose();
                break;
            case "Iesire":
                System.exit(0);       
            default:
                break;
        }
    }
}

