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

public class UISearchVaxCenter extends JFrame implements ActionListener {

    JButton backToCitizen;

    public UISearchVaxCenter(){

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

        setBounds(0, 0, 1600, 900);
        setLayout(null);
        add(backToCitizen);

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        setTitle("Info Centri Vaccinali");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim.width / 2, dim.height / 2);
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setForeground(Color.WHITE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backToCitizen) {
            this.dispose();
            new UICitizen();
        }

    }
}
