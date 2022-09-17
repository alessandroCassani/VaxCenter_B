package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * La classe UIChoosingRooles crea l'interfaccia dove il cittadino sceglie se visualizzare le info di un centro vaccinale,
 * di registrarsi presso un centro vaccinale, di accedere e di inserire eventuali eventi avversi avuto dopo la somministrazione del vaccino
 *
 * @author Paolo Bruscagin
 */

public class UICitizen extends JFrame implements ActionListener {

    /**
     * Bottone per accedere alla sezione info centro vaccinale
     */
    JButton cercaCentroVaccinale;

    /**
     * Bottone per accedere alla sezione registra centro cittadino
     */

    JButton registraCittadino;

    /**
     * Bottone per accedere alla sezione login del cittadino
     */

    JButton login;

    /**
     * Bottone per tornare nell'interfaccia grafica UIChoosingRooles
     */

    JButton backToChoosingRooles;

    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica
     */

    public UICitizen() {

        Border bordo = new LineBorder(new Color(0xFF37C47A, true), 4, true);
        Border bordobtnInd = new LineBorder(new Color(0xFFF68E3B, true), 4, true);
        Border bordoLogin = new LineBorder(new Color(0xFFF63BE6, true), 4, true);


        ImageIcon info = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/info_vaxcenter.png")));


        cercaCentroVaccinale = new JButton("info CENTRI VACCINALE", info); //mettere foto
        cercaCentroVaccinale.setBounds(1000, 150, 500, 120);
        cercaCentroVaccinale.setFont(new Font("Georgia", Font.BOLD, 20));
        cercaCentroVaccinale.setBackground(new Color(0xA059E3B3));
        cercaCentroVaccinale.setForeground(Color.WHITE);
        cercaCentroVaccinale.setBorder(bordo);
        cercaCentroVaccinale.setFocusable(false);
        cercaCentroVaccinale.addActionListener(this);
        cercaCentroVaccinale.setOpaque(true);

        ImageIcon reg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/registraCittadino_vaxcenter.png")));


        registraCittadino = new JButton("REGISTRATI PRESSO UN CENTRO VACCINALE", reg); //mettere foto
        registraCittadino.setBounds(975, 300, 550, 120);
        registraCittadino.setFont(new Font("Georgia", Font.BOLD, 15));
        registraCittadino.setBackground(new Color(0xA059E3B3));
        registraCittadino.setForeground(Color.WHITE);
        registraCittadino.setBorder(bordo);
        registraCittadino.setFocusable(false);
        registraCittadino.addActionListener(this);
        registraCittadino.setOpaque(true);

        login = new JButton("LOGIN"); //mettere foto
        login.setBounds(1075, 450, 350, 120);
        login.setFont(new Font("Georgia", Font.BOLD, 25));
        login.setBackground(new Color(0xAB33BE));
        login.setForeground(Color.WHITE);
        login.setBorder(bordoLogin);
        login.setFocusable(false);
        login.addActionListener(this);
        login.setOpaque(true);


        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/iconaindietro.png")));


        backToChoosingRooles = new JButton("     INDIETRO", ind);
        backToChoosingRooles.setBounds(1075, 600, 350, 120);
        backToChoosingRooles.setFont(new Font("Georgia", Font.BOLD, 20));
        backToChoosingRooles.setBackground(new Color(0xFA4723));
        backToChoosingRooles.setForeground(Color.WHITE);
        backToChoosingRooles.setBorder(bordobtnInd);
        backToChoosingRooles.setFocusable(false);
        backToChoosingRooles.addActionListener(this);
        backToChoosingRooles.setOpaque(true);


        setBounds(0, 0, 1600, 900);
        setLayout(null);
        add(cercaCentroVaccinale);
        add(registraCittadino);
        add(login);
        add(backToChoosingRooles);


        //Icona avvio del programma

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        setTitle("Cittadino");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setForeground(Color.WHITE);


    }

    /**
     * metodo che permette la gestione degli eventi associati ai listener legati ai componenti d'interfaccia grafica
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToChoosingRooles) {
            this.dispose();
            new UIChoosingRooles();
        } else if (e.getSource() == cercaCentroVaccinale) {
            this.dispose();
            //new
        } else if (e.getSource() == registraCittadino) {
            this.dispose();
            //new
        } else if (e.getSource() == login) {
            this.dispose();
            //new

        }
    }
}
