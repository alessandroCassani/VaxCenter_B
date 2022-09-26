package UI;

import UI.graphics.CurvesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Interfaccia di animazione iniziale di avvio programma prima di accedere all'interfaccia UIChoosingRooles
 *
 * @author  Damiano Ficara
 * @author  Alessandro Cassani
 * @author  Paolo Bruscagin
 */


public class WelcomeScreen extends JFrame {

    Timer timer;
    public WelcomeScreen() {
        displayWelcomeScreen();
    }

    private void displayWelcomeScreen() {
        final JWindow win = new JWindow(this);

        win.setSize(1000,600);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
        CurvesPanel cp = new CurvesPanel();
        win.add(cp);
        // immagine di prova

        // da sistemare
        URL url = getClass().getResource("/images/sfondo.png");
        JLabel label = new JLabel(new ImageIcon(url));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        cp.add(label);
        cp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JProgressBar progressBar = new JProgressBar(0,60);


        progressBar.setForeground(Color.decode("#ff930f"));
        win.add(BorderLayout.PAGE_END,progressBar);
        // alla fine della pb viene aperta nella stessa posizione
        win.revalidate();

        timer = new Timer(60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = progressBar.getValue();
                if(value == 60) {
                    win.dispose();
                    new UIAdverseEvent();
                    timer.stop();
                } else {
                    progressBar.setValue(value+4);
                }
            }
        });
        timer.start();
    }
    public static void main(String[] args) {
        new WelcomeScreen();
    }
}
