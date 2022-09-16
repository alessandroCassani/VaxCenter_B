package UI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;

public class UISplashScreen extends JWindow {

    private static JProgressBar progressBar = new JProgressBar();
    private static int count;
    private static Timer timer1;
    Container container = getContentPane();
    JWindow j = new JWindow();


    public UISplashScreen() {

        /**
         *  La classe UISplashSceern crea l'interfaccia di animazione principale prima di passare alla classe UIChoosingRooles
         */



        container.setLayout(null);
        JPanel panel = new JPanel();
        ImageIcon stemma = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo_stemma.png")));
        JLabel label = new JLabel("");
        label.setIcon(stemma);
        label.setBounds(0,0,800,450);
        panel.add(label);
        panel.setForeground(Color.WHITE);
        panel.setBounds(225, -10, 800, 450);
        panel.setLayout(null);
        container.add(panel);
        j.add(container);
        progressBar.setMaximum(30);
        progressBar.setForeground(Color.ORANGE);
        progressBar.setBounds(5, 430, 790, 15);
        j.setSize(800, 450);
        j.setLocationRelativeTo(null);
        this.setIconImage(stemma.getImage());
        this.setVisible(true);
        this.add(progressBar);
        this.loadProgressBar();
        j.setVisible(true);

    }

    private void loadProgressBar() {
        ActionListener al = new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;

                progressBar.setValue(count);
                getContentPane();



                if (count == 30) {
                    j.setVisible(false);
                    new UIChoosingRooles();
                    timer1.stop();



                }



            }



        };

        timer1 = new Timer(30, al);
        timer1.start();


    }


    public static void main(String[] args) {
        new UISplashScreen();
    }
};