package UI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;

/**
 *  La classe UISplashSceern crea l'interfaccia di animazione principale prima di passare alla classe UIChoosingRooles
 *
 *  @author Paolo Bruscagin
 */

public class UISplashScreen extends JWindow {

    /**
     * Conteiner della durata di 4 secondi utilizzato come intro per poi accedre all'interfaccia UIChoosingRoles
     */

    private static JProgressBar progressBar = new JProgressBar();
    private static int count;
    private static Timer timer1;
    Container container = getContentPane();
    JWindow j = new JWindow();

    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica
     */

    public UISplashScreen() {

        container.setLayout(null);
        JPanel panel = new JPanel();
        ImageIcon stemma = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo_stemma.png")));
        JLabel label = new JLabel("");
        label.setIcon(stemma);
        label.setIconTextGap(2);
        label.setBounds(100,20,800,450);
        panel.add(label);
        panel.setForeground(Color.WHITE);
        panel.setBounds(225, -10, 800, 450);
        panel.setLayout(null);
        container.add(panel);
        j.add(container);
        progressBar.setMaximum(30);
        progressBar.setForeground(Color.ORANGE);
        progressBar.setBounds(100, 470, 790, 20);
        j.setSize(1000, 600);
        j.setLocationRelativeTo(null);
        this.setIconImage(stemma.getImage());
        this.setVisible(true);
        this.add(progressBar);
        this.loadProgressBar();
        j.setVisible(true);
    }

    /**
     * metodo che permette la gestione degli eventi associati ai listener legati ai componenti d'interfaccia grafica
     */

    private void loadProgressBar() {
        ActionListener al = new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;

                progressBar.setValue(count);
                getContentPane();



                if (count == 30) {
                    j.setVisible(false);
                    new UIRegisterVaccinated();
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