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

    /**
     * Panel per registrare gli eventi avversi
     */

    JPanel inserisciEventiAvversi = new JPanel();
    /**
     * Panel pervisualizzare gli eventi avversi già registrati
     */
    JPanel riepilogoEventiAvversiPersonali = new JPanel();

    /**
     * Bottone per pulire tutti gli elementi della JPanel inserisciEventiAvversi
     */
    JButton pulisciEventiAvversi;

    JLabel status = new JLabel("Eventi Avversi Registrati!");


    JCheckBox switcha = new JCheckBox();

    /**
     * Bottone per registrare gli eventi avversi
     */
    JButton registraEA = new JButton("REGISTRA");

    /**
     * Bottone per tornare alle UICitizen
     */
    JButton backToCitizen;

    JLabel labelinsEventiAvversi = new JLabel("Inserisci Eventi avversi");
    JLabel giaRegistrati = new JLabel("Eventi avversi già registrati");


    JLabel labelmalDiTesta = new JLabel("Mail di Testa");

    JCheckBox checkBoxMaldiTesta = new JCheckBox();

    JComboBox severitaMDT = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

    JTextField noteMalditesta = new JTextField();

    JLabel labelFebbre = new JLabel("Febbre");

    JCheckBox checkBoxFebbre = new JCheckBox();

    JComboBox severitaFebbre = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

    JTextField noteFebbre = new JTextField();

    JLabel labelDMA = new JLabel("Dolori Mus. e Art.");

    JCheckBox checkBoxDMA = new JCheckBox();

    JComboBox severitaDMA = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});


    JTextField noteDMA = new JTextField();

    JLabel labellinfoadenopatia = new JLabel("Linfoadenopatia");

    JCheckBox checkBoxlinfoadenopatia = new JCheckBox();

    JComboBox severitalinfoadenopatia = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});


    JTextField notelinfoadenopatia = new JTextField();

    JLabel labeltachicardia = new JLabel("Tachicardia");

    JCheckBox checkBoxtachicardia = new JCheckBox();

    JComboBox severitatachicardia = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});


    JTextField notetachicardia = new JTextField();


    JLabel labelCrisiIpertensiva = new JLabel("Crisi Ipertensiva");

    JCheckBox checkBoxCrisiIpertensiva = new JCheckBox();

    JComboBox severitaCrisiIpertensiva = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});


    JTextField notetaCrisiIpertensiva = new JTextField();

    String data[][] = {{"Mal di testa", "0","/"},{"Febbre", "0","/"},{"Dolori Musc. Art.", "0","/"},{"Linfoanedopatia", "0","/"},{"Tachicardia", "0","/"},{"Crisi Ipertensiva", "0","/"}};
    String coloumn[]= {"NOME", "SEVERITA'", "NOTE"};

    /**
     * Tabella che mostra il riepilogo degli eventi avversi già registrati
     */
    JTable tabellaRiepilogo = new JTable(data, coloumn);






    public UIAdverseEvent(){

        Border bordobtn = new LineBorder(new Color(0,49,83), 4, true);
        Border bordobtnInd = new LineBorder(new Color(181, 226, 232), 2, true);
        Border bordobtnPul = new LineBorder(new Color(209, 245, 250), 2, true);


        JLabel titoloRiepilogo = new JLabel("Riepilogo Dati:");
        titoloRiepilogo.setFont(new Font("Georgia", Font.BOLD, 20));
        titoloRiepilogo.setBounds(100, 50, 200, 30);

        JLabel nomeUtente = new JLabel("Nome: ");
        nomeUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        nomeUtente.setBounds(20, 100, 200, 20);

        JLabel cognomeUtente = new JLabel("Cognome: ");
        cognomeUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        cognomeUtente.setBounds(20, 150, 200, 20);

        JLabel codiceFiscaleUtente = new JLabel("Codice Fiscale: ");
        codiceFiscaleUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        codiceFiscaleUtente.setBounds(20, 200, 200, 20);

        JLabel emailUtente = new JLabel("Email: ");
        emailUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        emailUtente.setBounds(20, 250, 200, 20);

        JLabel UserIDUtente = new JLabel("UserID: ");
        UserIDUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        UserIDUtente.setBounds(20, 300, 200, 20);

        JLabel IDUnivocoUtente = new JLabel("ID Univoco: ");
        IDUnivocoUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        IDUnivocoUtente.setBounds(20, 350, 200, 20);

        JLabel VaccinatoPresso = new JLabel("Vaccinato presso: ");
        VaccinatoPresso.setFont(new Font("Georgia", Font.BOLD, 15));
        VaccinatoPresso.setBounds(40, 400, 200, 20);




        //Mal di Testa

        labelmalDiTesta.setFont(new Font("Georgia", Font.BOLD, 12));
        labelmalDiTesta.setBounds(20, 50, 100, 20);


        checkBoxMaldiTesta.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxMaldiTesta.setBounds(130, 57, 20, 15);
        checkBoxMaldiTesta.addActionListener(this);
        checkBoxMaldiTesta.setBackground(new Color(209, 245, 250));

        severitaMDT.setFont(new Font("Arial", Font.BOLD, 12));
        severitaMDT.setBounds(160, 50, 60, 30);
        severitaMDT.setBackground(Color.WHITE);

        noteMalditesta.setFont(new Font("Arial", Font.BOLD, 10));
        noteMalditesta.setPreferredSize(new Dimension(325, 75));
        noteMalditesta.setBounds(250, 50, 250, 30);

        labelFebbre.setFont(new Font("Georgia", Font.BOLD, 12));
        labelFebbre.setBounds(20, 105, 100, 20);

        checkBoxFebbre.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxFebbre.setBounds(130, 107, 20, 15);
        checkBoxFebbre.addActionListener(this);
        checkBoxFebbre.setBackground(new Color(209, 245, 250));

        severitaFebbre.setFont(new Font("Arial", Font.BOLD, 12));
        severitaFebbre.setBounds(160, 105, 60, 30);
        severitaFebbre.setBackground(Color.WHITE);

        noteFebbre.setFont(new Font("Arial", Font.BOLD, 10));
        noteFebbre.setPreferredSize(new Dimension(325, 75));
        noteFebbre.setBounds(250, 105, 250, 30);

        labelDMA.setFont(new Font("Georgia", Font.BOLD, 12));
        labelDMA.setBounds(20, 155, 100, 20);

        checkBoxDMA.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxDMA.setBounds(130, 157, 20, 15);
        checkBoxDMA.addActionListener(this);
        checkBoxDMA.setBackground(new Color(209, 245, 250));

        severitaDMA.setFont(new Font("Arial", Font.BOLD, 12));
        severitaDMA.setBounds(160, 155, 60, 30);
        severitaDMA.setBackground(Color.WHITE);

        noteDMA.setFont(new Font("Arial", Font.BOLD, 10));
        noteDMA.setPreferredSize(new Dimension(325, 75));
        noteDMA.setBounds(250, 155, 250, 30);

        labellinfoadenopatia.setFont(new Font("Georgia", Font.BOLD, 12));
        labellinfoadenopatia.setBounds(20, 205, 100, 20);

        checkBoxlinfoadenopatia.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxlinfoadenopatia.setBounds(130, 207, 20, 15);
        checkBoxlinfoadenopatia.addActionListener(this);
        checkBoxlinfoadenopatia.setBackground(new Color(209, 245, 250));

        severitalinfoadenopatia.setFont(new Font("Arial", Font.BOLD, 12));
        severitalinfoadenopatia.setBounds(160, 205, 60, 30);
        severitalinfoadenopatia.setBackground(Color.WHITE);

        notelinfoadenopatia.setFont(new Font("Arial", Font.BOLD, 10));
        notelinfoadenopatia.setPreferredSize(new Dimension(325, 75));
        notelinfoadenopatia.setBounds(250, 205, 250, 30);

        labeltachicardia.setFont(new Font("Georgia", Font.BOLD, 12));
        labeltachicardia.setBounds(20, 255, 100, 20);

        checkBoxtachicardia.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxtachicardia.setBounds(130, 257, 20, 15);
        checkBoxtachicardia.addActionListener(this);
        checkBoxtachicardia.setBackground(new Color(209, 245, 250));

        severitatachicardia.setFont(new Font("Arial", Font.BOLD, 12));
        severitatachicardia.setBounds(160, 255, 60, 30);
        severitatachicardia.setBackground(Color.WHITE);

        notetachicardia.setFont(new Font("Arial", Font.BOLD, 10));
        notetachicardia.setPreferredSize(new Dimension(325, 75));
        notetachicardia.setBounds(250, 255, 250, 30);

        labelCrisiIpertensiva.setFont(new Font("Georgia", Font.BOLD, 12));
        labelCrisiIpertensiva.setBounds(20, 305, 100, 20);

        checkBoxCrisiIpertensiva.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxCrisiIpertensiva.setBounds(130, 307, 20, 15);
        checkBoxCrisiIpertensiva.addActionListener(this);
        checkBoxCrisiIpertensiva.setBackground(new Color(209, 245, 250));

        severitaCrisiIpertensiva.setFont(new Font("Arial", Font.BOLD, 12));
        severitaCrisiIpertensiva.setBounds(160, 305, 60, 30);
        severitaCrisiIpertensiva.setBackground(Color.WHITE);

        notetaCrisiIpertensiva.setFont(new Font("Arial", Font.BOLD, 10));
        notetaCrisiIpertensiva.setPreferredSize(new Dimension(325, 75));
        notetaCrisiIpertensiva.setBounds(250, 305, 250, 30);


        //
        inserisciEventiAvversi.setBounds(400, 25, 550, 500);
        inserisciEventiAvversi.setLayout(null);
        inserisciEventiAvversi.setBackground(new Color(209, 245, 250));


        riepilogoEventiAvversiPersonali.setBounds(400, 25, 550, 500);
        riepilogoEventiAvversiPersonali.setLayout(null);
        riepilogoEventiAvversiPersonali.setBackground(new Color(209, 245, 250));



        switcha.setFont(new Font("Arial", Font.BOLD, 15));
        switcha.setBounds(200, 487, 20, 15);
        switcha.addActionListener(this);
        switcha.setBackground(new Color(181, 226, 232));


        registraEA.setBounds(300, 425, 150, 50);
        registraEA.setFont(new Font("Georgia", Font.BOLD, 15));
        registraEA.setBackground(new Color(0,0,128));
        registraEA.setForeground(Color.WHITE);
        registraEA.setBorder(bordobtn);
        registraEA.setFocusable(false);
        registraEA.addActionListener(this);
        registraEA.setOpaque(true);

        ImageIcon pul = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/coloroPul50.png")));

        pulisciEventiAvversi = new JButton(pul);
        pulisciEventiAvversi.setBounds(470, 425, 50, 50);
        pulisciEventiAvversi.setFont(new Font("Georgia", Font.BOLD, 17));
        pulisciEventiAvversi.setBackground(new Color(209, 245, 250));
        pulisciEventiAvversi.setForeground(Color.WHITE);
        pulisciEventiAvversi.setBorder(bordobtnPul);
        pulisciEventiAvversi.setFocusable(false);
        pulisciEventiAvversi.addActionListener(this);
        pulisciEventiAvversi.setOpaque(true);



        giaRegistrati.setFont(new Font("Georgia", Font.BOLD, 15));
        riepilogoEventiAvversiPersonali.add(giaRegistrati).setBounds(175, 10, 300, 20);
        tabellaRiepilogo.setFont(new Font("Georgia", Font.BOLD, 15));
        tabellaRiepilogo.setBounds(50,50,450,420);
        tabellaRiepilogo.setBackground(new Color(209, 245, 250));

        tabellaRiepilogo.setRowHeight(60);
        tabellaRiepilogo.setBorder(bordobtn);
        getContentPane().add(tabellaRiepilogo);


        tabellaRiepilogo.setEnabled(false);
        riepilogoEventiAvversiPersonali.add(tabellaRiepilogo);



        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/color50ind.png")));

        backToCitizen = new JButton(ind);
        backToCitizen.setBounds(10, 5, 55 , 55);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 20));
        backToCitizen.setBackground(new Color(181, 226, 232));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnInd);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);

        status.setBounds(30, 400, 250, 30);
        status.setFont(new Font("Georgia", Font.BOLD, 15));
        status.setBackground(new Color(209, 245, 250));
        status.setForeground(new Color(0x07AF45));
        status.setVisible(false);
        inserisciEventiAvversi.add(status);




        add(backToCitizen);
        add(switcha);
        add(inserisciEventiAvversi).setVisible(false);
        add(riepilogoEventiAvversiPersonali).setVisible(true);
        add(titoloRiepilogo);
        add(nomeUtente);
        add(cognomeUtente);
        add(codiceFiscaleUtente);
        add(emailUtente);
        add(UserIDUtente);
        add(IDUnivocoUtente);
        add(VaccinatoPresso);

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setTitle("Eventi Avversi");
        setForeground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize (1000,600);
        getContentPane().setBackground(new Color(181, 226, 232));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    /**
     * metodo che permette di gestire gli eventi associati ai listener dei componenti di UI attivati dall'utente
     * @param e the event to be processed
     *
     * @author Paolo Bruscagin
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToCitizen) {
            this.dispose();
            new UICitizen();
        } else if (e.getSource() == switcha) {
            if (switcha.isSelected()) {

                riepilogoEventiAvversiPersonali.setVisible(false);
                inserisciEventiAvversi.setVisible(true);
                labelinsEventiAvversi.setFont(new Font("Georgia", Font.BOLD, 12));
                labelinsEventiAvversi.setBounds(20, 20, 200, 20);
                inserisciEventiAvversi.add(registraEA);
                inserisciEventiAvversi.add(pulisciEventiAvversi);
                inserisciEventiAvversi.add(labelinsEventiAvversi);
                inserisciEventiAvversi.add(labelmalDiTesta);
                inserisciEventiAvversi.add(checkBoxMaldiTesta);
                inserisciEventiAvversi.add(severitaMDT).setVisible(false);
                inserisciEventiAvversi.add(noteMalditesta).setVisible(false);
                inserisciEventiAvversi.add(labelFebbre);
                inserisciEventiAvversi.add(checkBoxFebbre);
                inserisciEventiAvversi.add(severitaFebbre).setVisible(false);
                inserisciEventiAvversi.add(noteFebbre).setVisible(false);
                inserisciEventiAvversi.add(labelDMA);
                inserisciEventiAvversi.add(checkBoxDMA);
                inserisciEventiAvversi.add(severitaDMA).setVisible(false);
                inserisciEventiAvversi.add(noteDMA).setVisible(false);
                inserisciEventiAvversi.add(labellinfoadenopatia);
                inserisciEventiAvversi.add(checkBoxlinfoadenopatia);
                inserisciEventiAvversi.add(severitalinfoadenopatia).setVisible(false);
                inserisciEventiAvversi.add(notelinfoadenopatia).setVisible(false);
                inserisciEventiAvversi.add(labeltachicardia);
                inserisciEventiAvversi.add(checkBoxtachicardia);
                inserisciEventiAvversi.add(severitatachicardia).setVisible(false);
                inserisciEventiAvversi.add(notetachicardia).setVisible(false);
                inserisciEventiAvversi.add(labelCrisiIpertensiva);
                inserisciEventiAvversi.add(checkBoxCrisiIpertensiva);
                inserisciEventiAvversi.add(severitaCrisiIpertensiva).setVisible(false);
                inserisciEventiAvversi.add(notetaCrisiIpertensiva).setVisible(false);
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
        else if (e.getSource() == checkBoxFebbre) {
            if (checkBoxFebbre.isSelected()) {
                severitaFebbre.setVisible(true);
                noteFebbre.setVisible(true);
                severitaFebbre.setSelectedItem("1");
                noteFebbre.setText("");

            }else{
                severitaFebbre.setVisible(false);
                noteFebbre.setVisible(false);
                severitaFebbre.setSelectedItem("1");
                noteFebbre.setText("");
            }
        }
        else if (e.getSource() == checkBoxDMA) {
            if (checkBoxDMA.isSelected()) {
                severitaDMA.setVisible(true);
                noteDMA.setVisible(true);
                severitaDMA.setSelectedItem("1");
                noteDMA.setText("");

            }else{
                severitaDMA.setVisible(false);
                noteDMA.setVisible(false);
                severitaDMA.setSelectedItem("1");
                noteDMA.setText("");
            }
        }
        else if (e.getSource() == checkBoxlinfoadenopatia) {
            if (checkBoxlinfoadenopatia.isSelected()) {
                severitalinfoadenopatia.setVisible(true);
                notelinfoadenopatia.setVisible(true);
                severitalinfoadenopatia.setSelectedItem("1");
                notelinfoadenopatia.setText("");

            }else{
                severitalinfoadenopatia.setVisible(false);
                notelinfoadenopatia.setVisible(false);
                severitalinfoadenopatia.setSelectedItem("1");
                notelinfoadenopatia.setText("");
            }
        }
        else if (e.getSource() == checkBoxtachicardia) {
            if (checkBoxtachicardia.isSelected()) {
                severitatachicardia.setVisible(true);
                notetachicardia.setVisible(true);
                severitatachicardia.setSelectedItem("1");
                notetachicardia.setText("");

            }else{
                severitatachicardia.setVisible(false);
                notetachicardia.setVisible(false);
                severitatachicardia.setSelectedItem("1");
                notetachicardia.setText("");
            }
        }
        else if (e.getSource() == checkBoxCrisiIpertensiva) {
            if (checkBoxCrisiIpertensiva.isSelected()) {
                severitaCrisiIpertensiva.setVisible(true);
                notetaCrisiIpertensiva.setVisible(true);
                severitaCrisiIpertensiva.setSelectedItem("1");
                notetaCrisiIpertensiva.setText("");

            }else{
                severitaCrisiIpertensiva.setVisible(false);
                notetaCrisiIpertensiva.setVisible(false);
                severitaCrisiIpertensiva.setSelectedItem("1");
                notetaCrisiIpertensiva.setText("");
            }
        }else if(e.getSource() == pulisciEventiAvversi){

            severitaMDT.setSelectedItem("1");
            noteMalditesta.setText("");
            checkBoxMaldiTesta.setSelected(false);
            severitaMDT.setVisible(false);
            noteMalditesta.setVisible(false);

            severitaFebbre.setSelectedItem("1");
            noteFebbre.setText("");
            checkBoxFebbre.setSelected(false);
            severitaFebbre.setVisible(false);
            noteFebbre.setVisible(false);

            severitaDMA.setSelectedItem("1");
            noteDMA.setText("");
            checkBoxDMA.setSelected(false);
            severitaDMA.setVisible(false);
            noteDMA.setVisible(false);

            severitalinfoadenopatia.setSelectedItem("1");
            notelinfoadenopatia.setText("");
            checkBoxlinfoadenopatia.setSelected(false);
            severitalinfoadenopatia.setVisible(false);
            notelinfoadenopatia.setVisible(false);

            severitatachicardia.setSelectedItem("1");
            notetachicardia.setText("");
            checkBoxtachicardia.setSelected(false);
            severitatachicardia.setVisible(false);
            notetachicardia.setVisible(false);

            severitaCrisiIpertensiva.setSelectedItem("1");
            notetaCrisiIpertensiva.setText("");
            checkBoxCrisiIpertensiva.setSelected(false);
            severitaCrisiIpertensiva.setVisible(false);
            notetaCrisiIpertensiva.setVisible(false);

            status.setVisible(false);



        }else if(e.getSource() == registraEA){
            status.setVisible(true);

        }
    }

}

