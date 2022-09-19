package UI;

import UI.Graphics.CurvesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomeScreen extends JFrame {

    Timer timer;
    public WelcomeScreen() {
        displayWelcomeScreen();
    }

    private void displayWelcomeScreen() {
        final JWindow win = new JWindow(this);

        win.setSize(900,600);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
        CurvesPanel cp = new CurvesPanel();
        win.add(cp);
        // immagine di prova

        String imPath = "C:\\Users\\damia\\Desktop\\Progetti\\VaxCenter_B\\clientCV\\src\\main\\resources\\images\\dottori.png";
        JLabel label = new JLabel(new ImageIcon(imPath));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        cp.add(label);
        cp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JProgressBar progressBar = new JProgressBar(0,100);


        progressBar.setForeground(Color.decode("#ff930f"));
        win.add(BorderLayout.PAGE_END,progressBar);
        // alla fine della pb viene aperta nella stessa posizione
        win.revalidate();

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = progressBar.getValue();
                if(value == 100) {
                    win.dispose();
                   UIChoosingRooles ui = new UIChoosingRooles("VAX");
                   ui.setVisible(true);
                    timer.stop();
                } else {
                    progressBar.setValue(value+2);
                }
            }
        });
        timer.start();

    }


    public static void main(String[] args) {
        new WelcomeScreen();
    }
}
