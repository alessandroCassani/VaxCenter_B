package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * @author Paolo Bruscagin
 */

public class UIHomeCitizen extends JFrame implements ActionListener {

    JButton backToCitizen;

    JButton inserisciEventiAvversi;

    public UIHomeCitizen() {

        Border bordo = new LineBorder(new Color(0xFF000000, true), 2, true);
        Border bordobtn = new LineBorder(new Color(0xFF37C47A, true), 4, true);
        Border bordobtnInd = new LineBorder(new Color(0xFFF68E3B, true), 4, true);

        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/iconaindietro.png")));

        backToCitizen =  new JButton("     INDIETRO", ind);
        backToCitizen.setBounds(1075, 670, 350, 120);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 20));
        backToCitizen.setBackground(new Color(0xFA4723));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnInd);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);

        inserisciEventiAvversi =  new JButton("INSERISCI EVENTI AVVERSI");
        inserisciEventiAvversi.setBounds(500, 670, 350, 120);
        inserisciEventiAvversi.setFont(new Font("Georgia", Font.BOLD, 20));
        inserisciEventiAvversi.setBackground(new Color(0x234EFA));
        inserisciEventiAvversi.setForeground(Color.WHITE);
        inserisciEventiAvversi.setBorder(bordobtn);
        inserisciEventiAvversi.setFocusable(false);
        inserisciEventiAvversi.addActionListener(this);
        inserisciEventiAvversi.setOpaque(true);

        setBounds(0, 0, 1600, 900);
        setLayout(null);

        add(inserisciEventiAvversi);
        add(backToCitizen);

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setForeground(Color.WHITE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == inserisciEventiAvversi) {
            this.dispose();
            new UIAdverseEvent();
        }else  if(e.getSource() == backToCitizen) {
            this.dispose();
            new UICitizen();
        }

    }
}
