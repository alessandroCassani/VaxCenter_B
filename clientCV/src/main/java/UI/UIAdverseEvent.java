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

    JLabel labelinsEventiAvversi = new JLabel("Inserisci Eventi avversi");

    JLabel labelmalDiTesta = new JLabel("Mail di Testa");

    JCheckBox checkBoxMaldiTesta = new JCheckBox();

    JComboBox severitaMDT = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});


    JTextArea noteMalditesta = new JTextArea();

    public UIAdverseEvent() {

        Border bordo = new LineBorder(new Color(0xFF000000, true), 2, true);
        Border bordobtn = new LineBorder(new Color(0xFF37C47A, true), 4, true);
        Border bordobtnInd = new LineBorder(new Color(0xFFF68E3B, true), 4, true);

        JLabel titoloRiepilogo = new JLabel("Riepilogo Dati:");
        titoloRiepilogo.setFont(new Font("Georgia", Font.BOLD, 12));
        titoloRiepilogo.setBounds(20, 20, 200, 20);

        JLabel nomeUtente = new JLabel("Nome: ");
        nomeUtente.setFont(new Font("Georgia", Font.BOLD, 12));
        nomeUtente.setBounds(20, 50, 200, 50);

        JLabel cognomeUtente = new JLabel("Cognome: ");
        cognomeUtente.setFont(new Font("Georgia", Font.BOLD, 12));
        cognomeUtente.setBounds(20, 80, 200, 50);

        JLabel codiceFiscaleUtente = new JLabel("Codice Fiscale: ");
        codiceFiscaleUtente.setFont(new Font("Georgia", Font.BOLD, 12));
        codiceFiscaleUtente.setBounds(20, 110, 200, 50);

        JLabel emailUtente = new JLabel("Email: ");
        emailUtente.setFont(new Font("Georgia", Font.BOLD, 12));
        emailUtente.setBounds(20, 140, 200, 50);

        JLabel UserIDUtente = new JLabel("UserID: ");
        UserIDUtente.setFont(new Font("Georgia", Font.BOLD, 12));
        UserIDUtente.setBounds(20, 170, 200, 50);

        JLabel IDUnivocoUtente = new JLabel("ID Univoco: ");
        IDUnivocoUtente.setFont(new Font("Georgia", Font.BOLD, 12));
        IDUnivocoUtente.setBounds(20, 200, 200, 50);

        JLabel cvUtente = new JLabel("Vaccinato presso: ");
        cvUtente.setFont(new Font("Georgia", Font.BOLD, 12));
        cvUtente.setBounds(20, 230, 200, 50);


        labelinsEventiAvversi.setFont(new Font("Georgia", Font.BOLD, 12));
        labelinsEventiAvversi.setBounds(20, 20, 200, 20);

        //Mal di Testa

        labelmalDiTesta.setFont(new Font("Georgia", Font.BOLD, 12));
        labelmalDiTesta.setBounds(20, 50, 100, 20);


        checkBoxMaldiTesta.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxMaldiTesta.setBounds(130, 57, 20, 15);
        checkBoxMaldiTesta.addActionListener(this);

        severitaMDT.setFont(new Font("Arial", Font.BOLD, 12));
        severitaMDT.setBorder(bordo);
        severitaMDT.setBounds(160, 50, 60, 20);
        severitaMDT.setBackground(Color.WHITE);

        noteMalditesta.setFont(new Font("Arial", Font.BOLD, 20));
        noteMalditesta.setBorder(bordo);
        noteMalditesta.setPreferredSize(new Dimension(325, 75));
        noteMalditesta.setBounds(250, 50, 150, 75);


        //
        inserisciEventiAvversi.setBounds(450, 50, 450, 450);
        inserisciEventiAvversi.setBorder(bordo);
        inserisciEventiAvversi.setLayout(null);

        riepilogoEventiAvversiPersonali.setBounds(450, 50, 450, 450);
        riepilogoEventiAvversiPersonali.setBorder(bordo);
        riepilogoEventiAvversiPersonali.setLayout(null);


        switcha.setFont(new Font("Arial", Font.BOLD, 15));
        switcha.setBounds(413, 487, 20, 15);
        switcha.addActionListener(this);

        registraEA.setBounds(50, 350, 100, 75);
        registraEA.setFont(new Font("Georgia", Font.BOLD, 20));
        registraEA.setBackground(new Color(0x07AF45));
        registraEA.setForeground(Color.WHITE);
        registraEA.setBorder(bordobtn);
        registraEA.setFocusable(false);
        registraEA.addActionListener(this);
        registraEA.setOpaque(true);


        JLabel giaRegistrati = new JLabel("Eventi avversi già registrati");
        giaRegistrati.setFont(new Font("Georgia", Font.BOLD, 12));
        riepilogoEventiAvversiPersonali.add(giaRegistrati).setBounds(20, 20, 300, 50);

        inserisciEventiAvversi.add(registraEA);
        inserisciEventiAvversi.add(labelinsEventiAvversi);
        inserisciEventiAvversi.add(labelmalDiTesta);
        inserisciEventiAvversi.add(checkBoxMaldiTesta);
        inserisciEventiAvversi.add(severitaMDT).setVisible(false);
        inserisciEventiAvversi.add(noteMalditesta).setVisible(false);


        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/iconaindietro.png")));

        backToCitizen = new JButton(ind);
        backToCitizen.setBounds(50, 400, 100, 100);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 20));
        backToCitizen.setBackground(new Color(0xFA4723));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnInd);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);


        add(backToCitizen);
        add(switcha);
        add(inserisciEventiAvversi).setVisible(false);
        add(riepilogoEventiAvversiPersonali).setVisible(true);
        add(titoloRiepilogo).setVisible(true);
        add(nomeUtente).setVisible(true);
        add(cognomeUtente).setVisible(true);
        add(codiceFiscaleUtente).setVisible(true);
        add(emailUtente).setVisible(true);
        add(UserIDUtente).setVisible(true);
        add(IDUnivocoUtente).setVisible(true);
        add(cvUtente).setVisible(true);


        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        setTitle("Eventi Avversi");
        setBounds(0, 0, 1600, 900);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setForeground(Color.WHITE);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToCitizen) {
            this.dispose();
            new UICitizen();
        } else if (e.getSource() == switcha) {
            if (switcha.isSelected()) {
                riepilogoEventiAvversiPersonali.setVisible(false);
                inserisciEventiAvversi.setVisible(true);
            } else {
                inserisciEventiAvversi.setVisible(false);
                riepilogoEventiAvversiPersonali.setVisible(true);


            }

        } else if (e.getSource() == checkBoxMaldiTesta) {
            if (checkBoxMaldiTesta.isSelected()) {
                severitaMDT.setVisible(true);
                noteMalditesta.setVisible(true);
                severitaMDT.setSelectedItem("1");
                noteMalditesta.setText("");

            }else{
                severitaMDT.setVisible(false);
                noteMalditesta.setVisible(false);
                severitaMDT.setSelectedItem("1");
                noteMalditesta.setText("");
            }
        }
    }
}

