package clientCV.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {

    Timer timer;
    public WelcomeScreen() {
        displayWelcomeScreen();
        //setVisible(true);
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JLabel label = new JLabel("Benvenuti");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);
    }

    private void displayWelcomeScreen() {
        final JWindow win = new JWindow(this);
        win.setSize(600,400);
        win.setLocationRelativeTo(null);
        win.setVisible(true);

        JPanel panel = new JPanel();
        win.add(panel);
        String logoPath = "C:\\Users\\damia\\Desktop\\ic.PNG";
        JLabel label = new JLabel(new ImageIcon(logoPath));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JProgressBar progressBar = new JProgressBar(0,100);
        progressBar.setForeground(Color.ORANGE);
        win.add(BorderLayout.PAGE_END,progressBar);
        // alla fine della pb viene aperta nella stessa posizione
        win.revalidate();

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = progressBar.getValue();
                if(value == 100) {
                    win.dispose();
                   UIChoosingRooles ui = new UIChoosingRooles("Benvenuti");
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
