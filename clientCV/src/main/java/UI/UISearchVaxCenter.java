package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/** Classe relativa all'interfaccia grafica relativa alla funzionalita' di ricerca di un centro vaccinale, per la conseguente
 * visione delle informazioni ad esso associate
 *
 * @author Paolo Bruscagin
 */

public class UISearchVaxCenter extends JFrame implements ActionListener {

    /**
     * bottone per tornare alla schermata di UI precedente (UiCitizen)
     */
    JButton backToCitizen;

    /**
     * costruttore che permette il caricamento dei componenti relativi all'interfccia grafica di ricerca di un centro vaccinale
     *
     * @author paolo Bruscagin
     */
    public UISearchVaxCenter(){

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

    /**
     * metodo che permette la gestione dei listener associati ai componenti di interfaccia grafica
     * @param e l'evento che permette l'attivazione del listener associato
     *
     * @author paolo Bruscagin
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backToCitizen) {
            this.dispose();
            new UICitizen();
        }
    }
}
