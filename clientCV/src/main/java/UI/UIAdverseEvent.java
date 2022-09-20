package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 *
 * @author Paolo Bruscagin
 */

public class UIAdverseEvent extends JFrame implements ActionListener {

    JPanel inserisciEventiAvversi = new JPanel();

    JPanel riepilogoEventiAvversiPersonali = new JPanel();

    JButton pulisciEventiAvversi = new JButton();

    JCheckBox switcha = new JCheckBox();

    JButton registraEA = new JButton("REGISTRA");

    JButton backToCitizen;

    public UIAdverseEvent() {

        Border bordo = new LineBorder(new Color(0xFF000000, true), 2, true);
        Border bordobtn = new LineBorder(new Color(0xFF37C47A, true), 4, true);
        Border bordobtnInd = new LineBorder(new Color(0xFFF68E3B, true), 4, true);

        inserisciEventiAvversi.setBounds(801, 0, 750, 750);
        inserisciEventiAvversi.setBorder(bordo);

        switcha.setFont(new Font("Arial", Font.BOLD, 15));
        switcha.setBounds(413, 487, 160, 15);
        switcha.addActionListener(this);

        registraEA.setBounds(100, 200, 400, 200);
        registraEA.setFont(new Font("Georgia", Font.BOLD, 20));
        registraEA.setBackground(new Color(0x07AF45));
        registraEA.setForeground(Color.WHITE);
        registraEA.setBorder(bordobtn);
        registraEA.setFocusable(false);
        registraEA.addActionListener(this);
        registraEA.setOpaque(true);

        inserisciEventiAvversi.add(registraEA);


        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/iconaindietro.png")));

        backToCitizen = new JButton("     INDIETRO", ind);
        backToCitizen.setBounds(1075, 670, 350, 120);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 20));
        backToCitizen.setBackground(new Color(0xFA4723));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnInd);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);

        setBounds(0, 0, 1600, 900);
        setLayout(null);
        add(backToCitizen);
        add(switcha);
        add(inserisciEventiAvversi).setVisible(false);


        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        setTitle("Eventi Avversi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setForeground(Color.WHITE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToCitizen) {
            this.dispose();
            new UICitizen();
        } else if (e.getSource() == switcha) {
            if (switcha.isSelected()) {

                inserisciEventiAvversi.setVisible(true);
            } else {
                inserisciEventiAvversi.setVisible(false);


            }

        }
    }
}
