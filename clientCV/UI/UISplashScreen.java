package clientCV.UI;


import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;

public class UISplashScreen extends JWindow {

    private static JProgressBar progressBar = new JProgressBar();
    private static UISplashScreen execute;
    private static int count;
    private static Timer timer1;

    public UISplashScreen() {

        /**
         *  La classe UISplashSceern crea l'interfaccia di animazione principale prima di passare alla classe UIChoosingRooles
         */



        Container container = getContentPane();
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

        progressBar.setMaximum(50);
        progressBar.setForeground(Color.ORANGE);
        progressBar.setBounds(5, 430, 790, 15);
        this.add(progressBar);
        this.loadProgressBar();
        this.setSize(800, 450);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setIconImage(stemma.getImage());
    }

    private void loadProgressBar() {
        ActionListener al = new ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                count++;

                progressBar.setValue(count);


                if (count == 50) {
                    new UIChoosingRooles();
                    timer1.stop();
                }

            }


        };
        timer1 = new Timer(50, al);
        timer1.start();
    }

    public static void main(String[] args) {
        new UISplashScreen();
    }
};
