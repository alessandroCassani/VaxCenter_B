package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Interfaccia che permette all'utente di registrare gli eventi avversi
 * avvenuti dopo la somministrazione del vaccino. Inoltre è possibile visualizzare il riepilogo
 * dei propri dati personali: Nome, Cognome, Codice Fiscale, Email, UserID, Nome del Centro Vaccinale dove
 * è stata effettuata la vaccinazione.
 *
 * Se gli Eventi avversi sono già stati registrati è possibile visualizzare il riepilogo.
 *
 *
 * @author Paolo Bruscagin
 */

public class UIAdverseEvent extends JFrame implements ActionListener {

    //Controllo dei 3 Panel
    /**
     * Panel per visualizzare il riepilogo dati dell'utente
     */
    JPanel infoUtente = new JPanel();

    /**
     * Panel per registrare gli eventi avversi
     */

    JPanel inserisciEventiAvversi = new JPanel();
    /**
     * Panel pervisualizzare gli eventi avversi già registrati
     */
    JPanel riepilogoEventiAvversiPersonali = new JPanel();

    //Bottoni

    /**
     * Bottone per pulire tutti gli elementi della JPanel inserisciEventiAvversi
     */
    JButton pulisciEventiAvversi;

    /**
     * Bottone per registrare gli eventi avversi
     */
    JButton registraEA = new JButton("REGISTRA");

    /**
     * Bottone per tornare alle UICitizen
     */
    JButton backToCitizen;

    //Status "Pop-up" per indicare se la registrazione è avvenuta con successo
    /**
     * Status che indica se gli eventi avversi sono stati registrati con successo o meno (pop-up)
     */
    JLabel status = new JLabel("Eventi Avversi Registrati!");



    //CheckBox temporanea che andrà cancellata per verificare il cambiamento del Panel
    JCheckBox switcha = new JCheckBox();



    //Labels Titoli Panel Inserisci Eventi Avversi / Visualizza Eventi Avversi Registrati
    /**
     * Label Titolo situata nel Panelper registrare gli Eventi Avversi
     */
    JLabel labelinsEventiAvversi = new JLabel("Inserisci Eventi avversi:");

    /**
     * Label Titolo situata nel Panel di riepilogo degli Eventi Avversi
     */
    JLabel giaRegistrati = new JLabel("Eventi avversi già registrati");


    // Sintomatologia Mal di Testa

    /**
     * Label Mal di testa
     */
    JLabel labelmalDiTesta = new JLabel("Mail di Testa");

    /**
     * CheckBox Mal di testa (flag)
     */

    JCheckBox checkBoxMaldiTesta = new JCheckBox();

    /**
     * Severità Mal di testa (da 1 a 5)
     */

    JComboBox severitaMDT = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

    /**
     * Note per l'evento avverso "Mal di Testa" max. 256 caratteri
     */

    JTextField noteMalditesta = new JTextField();


    // Sintomatologia Febbre

    /**
     * Label Febbre
     */
    JLabel labelFebbre = new JLabel("Febbre");

    /**
     * CheckBox Febbre (flag)
     */

    JCheckBox checkBoxFebbre = new JCheckBox();

    /**
     * Severità Mal di testa (da 1 a 5)
     */

    JComboBox severitaFebbre = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

    /**
     * Note per l'evento avverso "Febbre" max. 256 caratteri
     */

    JTextField noteFebbre = new JTextField();


    // Sintomatologia Dolori Muscolari e/o Articolari

    /**
     * Label Dolori Muscolari e/o Articolari
     */

    JLabel labelDMA = new JLabel("Dolori Mus. e Art.");

    /**
     * CheckBox Dolori Muscolari e/o Articolari (flag)
     */

    JCheckBox checkBoxDMA = new JCheckBox();

    /**
     * Severità Dolori Muscolari e/o Articolari (da 1 a 5)
     */

    JComboBox severitaDMA = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

    /**
     * Note per l'evento avverso "Dolori Muscolari e/o Articolari" max. 256 caratteri
     */

    JTextField noteDMA = new JTextField();


    // Sintomatologia Linfoadenopatia

    /**
     * Label Linfoadenopatia
     */
    JLabel labellinfoadenopatia = new JLabel("Linfoadenopatia");

    /**
     * CheckBox Linfoadenopatia (flag)
     */

    JCheckBox checkBoxlinfoadenopatia = new JCheckBox();

    /**
     * Severità Linfoadenopatia (da 1 a 5)
     */

    JComboBox severitalinfoadenopatia = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

    /**
     * Note per l'evento avverso "Linfoadenopatia" max. 256 caratteri
     */

    JTextField notelinfoadenopatia = new JTextField();

    // Sintomatologia Tachicardia

    /**
     * Label Tachicardia
     */
    JLabel labeltachicardia = new JLabel("Tachicardia");

    /**
     * CheckBox Tachicardia (flag)
     */
    JCheckBox checkBoxtachicardia = new JCheckBox();

    /**
     * Severità Tachicardia (da 1 a 5)
     */

    JComboBox severitatachicardia = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

    /**
     * Note per l'evento avverso "Tachicardia" max. 256 caratteri
     */

    JTextField notetachicardia = new JTextField();


    // Sintomatologia Crisi Ipertensiva

    /**
     * Label Crisi Ipertensiva
     */
    JLabel labelCrisiIpertensiva = new JLabel("Crisi Ipertensiva");

    /**
     * CheckBox Crisi Ipertensiva (flag)
     */

    JCheckBox checkBoxCrisiIpertensiva = new JCheckBox();


    /**
     * Severità Crisi Ipertensiva (da 1 a 5)
     */
    JComboBox severitaCrisiIpertensiva = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});

    /**
     * Note per l'evento avverso "Crisi Ipertensiva" max. 256 caratteri
     */

    JTextField notetaCrisiIpertensiva = new JTextField();


    //Tabella riassuntiva Eventi Avversi già registrati

    String nomeEvento = "NOME";
    String severitàEvento = "SEVERITA'";
    String noteEvento = "NOTE";

    //Dati che il DB automaticamente imposta e di conseguenza il valore della cella viene modificato
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;

    String note1;
    String note2;
    String note3;
    String note4;
    String note5;
    String note6;



    //Tabella 6x3 (righe x colonne)
    String[][] data = {{nomeEvento,severitàEvento,noteEvento },{"Mal di testa", s1,note1},{"Febbre", s2,note2},{"Dolori Musc. Art.", s3,note3},{"Linfoadenopatia", s4,note4},{"Tachicardia", s5,note5},{"Crisi Ipertensiva", s6,note6}};
    String[] coloumn = {"NOME", "SEVERITA'", "NOTE"};

    /**
     * Tabella che mostra il riepilogo degli eventi avversi già registrati
     */
    JTable tabellaRiepilogo = new JTable(data, coloumn);

    //JLabel per severità e note di ogni riga

    JLabel severitaEA1 = new JLabel("Severità");


    JLabel noteEA1 = new JLabel("Note (max. 256)");


    JLabel severitaEA2 = new JLabel("Severità");


    JLabel noteEA2 = new JLabel("Note (max. 256)");


    JLabel severitaEA3 = new JLabel("Severità");


    JLabel noteEA3 = new JLabel("Note (max. 256)");

    JLabel severitaEA4 = new JLabel("Severità");


    JLabel noteEA4 = new JLabel("Note (max. 256)");


    JLabel severitaEA5 = new JLabel("Severità");


    JLabel noteEA5 = new JLabel("Note (max. 256)");


    JLabel severitaEA6 = new JLabel("Severità");


    JLabel noteEA6 = new JLabel("Note (max. 256)");

    //Label informativa per far capire all'utente che deve schiacciare
    // il quadratino per inserire severità ed eventuali note per l'evento avverso selezionato


    /**
     * Label informazioni pulsante quadrato eventi avversi
     */
    JLabel premiQuadrato = new JLabel("Se hai avuto uno di questi sintomi clicca il quadratino");



    public UIAdverseEvent(){


        Border bordobtn = new LineBorder(new Color(0,49,83), 4, true);
        Border bordobtn_AE = new LineBorder(new Color(0,49,83), 2, true);
        Border bordobtnInd = new LineBorder(new Color(181, 226, 232), 2, true);
        Border bordobtnPul = new LineBorder(new Color(209, 245, 250), 2, true);



        infoUtente.setBounds(550, -5, 450, 570);
        infoUtente.setLayout(null);
        infoUtente.setBackground(new Color(181, 226, 232));
        infoUtente.setBorder(bordobtn_AE);




        //Informazioni InfoUtente
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
        VaccinatoPresso.setBounds(20, 400, 200, 20);

        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/color50ind.png")));

        backToCitizen = new JButton(ind);
        backToCitizen.setBounds(375, 7, 55 , 55);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 20));
        backToCitizen.setBackground(new Color(181, 226, 232));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnInd);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);

        switcha.setFont(new Font("Arial", Font.BOLD, 15));
        switcha.setBounds(400, 500, 20, 15);
        switcha.addActionListener(this);
        switcha.setBackground(new Color(181, 226, 232));

        infoUtente.add(titoloRiepilogo);
        infoUtente.add(nomeUtente);
        infoUtente.add(cognomeUtente);
        infoUtente.add(codiceFiscaleUtente);
        infoUtente.add(emailUtente);
        infoUtente.add(UserIDUtente);
        infoUtente.add(IDUnivocoUtente);
        infoUtente.add(VaccinatoPresso);

        infoUtente.add(backToCitizen);
        infoUtente.add(switcha);

        add(infoUtente);


        //Label informativa quadratino eventi avversi
        premiQuadrato.setFont(new Font("Georgia", Font.BOLD, 10));
        premiQuadrato.setBounds(20, 370, 350, 12);
        premiQuadrato.setForeground(new Color(0x0729AF));



        //Label severità e note
        severitaEA1.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA1.setBounds(160, 57, 60, 12);

        noteEA1.setFont(new Font("Georgia", Font.BOLD, 10));
        noteEA1.setBounds(250, 57, 250, 12);

        severitaEA2.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA2.setBounds(160, 107, 60, 12);

        noteEA2.setFont(new Font("Georgia", Font.BOLD, 10));
        noteEA2.setBounds(250, 107, 250, 12);

        severitaEA3.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA3.setBounds(160, 157, 60, 12);

        noteEA3.setFont(new Font("Georgia", Font.BOLD, 10));
        noteEA3.setBounds(250, 157, 250, 12);

        severitaEA4.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA4.setBounds(160, 207, 60, 12);

        noteEA4.setFont(new Font("Georgia", Font.BOLD, 10));
        noteEA4.setBounds(250, 207, 250, 12);

        severitaEA5.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA5.setBounds(160, 257, 60, 12);

        noteEA5.setFont(new Font("Georgia", Font.BOLD, 10));
        noteEA5.setBounds(250, 257, 250, 12);

        severitaEA6.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA6.setBounds(160, 307, 60, 12);

        noteEA6.setFont(new Font("Georgia", Font.BOLD, 10));
        noteEA6.setBounds(250, 307, 250, 12);

        //Mal di Testa
        labelmalDiTesta.setFont(new Font("Georgia", Font.BOLD, 12));
        labelmalDiTesta.setBounds(20, 75, 100, 20);

        checkBoxMaldiTesta.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxMaldiTesta.setBounds(130, 77, 20, 15);
        checkBoxMaldiTesta.addActionListener(this);
        checkBoxMaldiTesta.setBackground(new Color(209, 245, 250));

        severitaMDT.setFont(new Font("Arial", Font.BOLD, 12));
        severitaMDT.setBounds(160, 75, 60, 30);
        severitaMDT.setBackground(Color.WHITE);
        severitaMDT.setBorder(bordobtn_AE);

        noteMalditesta.setFont(new Font("Arial", Font.BOLD, 10));
        noteMalditesta.setPreferredSize(new Dimension(325, 75));
        noteMalditesta.setBounds(250, 75, 250, 30);
        noteMalditesta.setBorder(bordobtn_AE);


        //Febbre
        labelFebbre.setFont(new Font("Georgia", Font.BOLD, 12));
        labelFebbre.setBounds(20, 125, 100, 20);

        checkBoxFebbre.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxFebbre.setBounds(130, 127, 20, 15);
        checkBoxFebbre.addActionListener(this);
        checkBoxFebbre.setBackground(new Color(209, 245, 250));

        severitaFebbre.setFont(new Font("Arial", Font.BOLD, 12));
        severitaFebbre.setBounds(160, 125, 60, 30);
        severitaFebbre.setBackground(Color.WHITE);
        severitaFebbre.setBorder(bordobtn_AE);

        noteFebbre.setFont(new Font("Arial", Font.BOLD, 10));
        noteFebbre.setPreferredSize(new Dimension(325, 75));
        noteFebbre.setBounds(250, 125, 250, 30);
        noteFebbre.setBorder(bordobtn_AE);


        //Dolore Addominale e/o Articolare
        labelDMA.setFont(new Font("Georgia", Font.BOLD, 12));
        labelDMA.setBounds(20, 175, 100, 20);

        checkBoxDMA.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxDMA.setBounds(130, 177, 20, 15);
        checkBoxDMA.addActionListener(this);
        checkBoxDMA.setBackground(new Color(209, 245, 250));

        severitaDMA.setFont(new Font("Arial", Font.BOLD, 12));
        severitaDMA.setBounds(160, 175, 60, 30);
        severitaDMA.setBackground(Color.WHITE);
        severitaDMA.setBorder(bordobtn_AE);

        noteDMA.setFont(new Font("Arial", Font.BOLD, 10));
        noteDMA.setPreferredSize(new Dimension(325, 75));
        noteDMA.setBounds(250, 175, 250, 30);
        noteDMA.setBorder(bordobtn_AE);


        //Linfoadenopatia
        labellinfoadenopatia.setFont(new Font("Georgia", Font.BOLD, 12));
        labellinfoadenopatia.setBounds(20, 225, 100, 20);

        checkBoxlinfoadenopatia.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxlinfoadenopatia.setBounds(130, 227, 20, 15);
        checkBoxlinfoadenopatia.addActionListener(this);
        checkBoxlinfoadenopatia.setBackground(new Color(209, 245, 250));

        severitalinfoadenopatia.setFont(new Font("Arial", Font.BOLD, 12));
        severitalinfoadenopatia.setBounds(160, 225, 60, 30);
        severitalinfoadenopatia.setBackground(Color.WHITE);
        severitalinfoadenopatia.setBorder(bordobtn_AE);

        notelinfoadenopatia.setFont(new Font("Arial", Font.BOLD, 10));
        notelinfoadenopatia.setPreferredSize(new Dimension(325, 75));
        notelinfoadenopatia.setBounds(250, 225, 250, 30);
        notelinfoadenopatia.setBorder(bordobtn_AE);


        //Tachicardia
        labeltachicardia.setFont(new Font("Georgia", Font.BOLD, 12));
        labeltachicardia.setBounds(20, 275, 100, 20);

        checkBoxtachicardia.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxtachicardia.setBounds(130, 277, 20, 15);
        checkBoxtachicardia.addActionListener(this);
        checkBoxtachicardia.setBackground(new Color(209, 245, 250));

        severitatachicardia.setFont(new Font("Arial", Font.BOLD, 12));
        severitatachicardia.setBounds(160, 275, 60, 30);
        severitatachicardia.setBackground(Color.WHITE);
        severitatachicardia.setBorder(bordobtn_AE);

        notetachicardia.setFont(new Font("Arial", Font.BOLD, 10));
        notetachicardia.setPreferredSize(new Dimension(325, 75));
        notetachicardia.setBounds(250, 275, 250, 30);
        notetachicardia.setBorder(bordobtn_AE);


        //Crisi Ipertensiva
        labelCrisiIpertensiva.setFont(new Font("Georgia", Font.BOLD, 12));
        labelCrisiIpertensiva.setBounds(20, 325, 100, 20);

        checkBoxCrisiIpertensiva.setFont(new Font("Arial", Font.BOLD, 15));
        checkBoxCrisiIpertensiva.setBounds(130, 327, 20, 15);
        checkBoxCrisiIpertensiva.addActionListener(this);
        checkBoxCrisiIpertensiva.setBackground(new Color(209, 245, 250));

        severitaCrisiIpertensiva.setFont(new Font("Arial", Font.BOLD, 12));
        severitaCrisiIpertensiva.setBounds(160, 325, 60, 30);
        severitaCrisiIpertensiva.setBackground(Color.WHITE);
        severitaCrisiIpertensiva.setBorder(bordobtn_AE);

        notetaCrisiIpertensiva.setFont(new Font("Arial", Font.BOLD, 10));
        notetaCrisiIpertensiva.setPreferredSize(new Dimension(325, 75));
        notetaCrisiIpertensiva.setBounds(250, 325, 250, 30);
        notetaCrisiIpertensiva.setBorder(bordobtn_AE);

        //Panel info Eventi avversi già registrati

        riepilogoEventiAvversiPersonali.setBounds(0, 0, 550, 500);
        riepilogoEventiAvversiPersonali.setLayout(null);
        riepilogoEventiAvversiPersonali.setBackground(new Color(209, 245, 250));

        giaRegistrati.setFont(new Font("Georgia", Font.BOLD, 15));
        riepilogoEventiAvversiPersonali.add(giaRegistrati).setBounds(175, 10, 300, 20);
        tabellaRiepilogo.setFont(new Font("Georgia", Font.BOLD, 15));
        tabellaRiepilogo.setBounds(50,50,450,420);
        tabellaRiepilogo.setBackground(new Color(209, 245, 250));
        tabellaRiepilogo.setRowHeight(60);
        tabellaRiepilogo.setBorder(bordobtn_AE);
        tabellaRiepilogo.setEnabled(false);
        riepilogoEventiAvversiPersonali.add(tabellaRiepilogo);


        add(riepilogoEventiAvversiPersonali);


        //Panel Inserimento Eventi Avversi
        inserisciEventiAvversi.setBounds(0, 0, 550, 500);
        inserisciEventiAvversi.setLayout(null);
        inserisciEventiAvversi.setBackground(new Color(209, 245, 250));

        labelinsEventiAvversi.setFont(new Font("Georgia", Font.BOLD, 15));
        labelinsEventiAvversi.setBounds(20, 20, 200, 20);

        inserisciEventiAvversi.add(labelinsEventiAvversi);
        inserisciEventiAvversi.add(premiQuadrato);
        inserisciEventiAvversi.add(severitaEA1).setVisible(false);
        inserisciEventiAvversi.add(noteEA1).setVisible(false);
        inserisciEventiAvversi.add(severitaEA2).setVisible(false);
        inserisciEventiAvversi.add(noteEA2).setVisible(false);
        inserisciEventiAvversi.add(severitaEA3).setVisible(false);
        inserisciEventiAvversi.add(noteEA3).setVisible(false);
        inserisciEventiAvversi.add(severitaEA4).setVisible(false);
        inserisciEventiAvversi.add(noteEA4).setVisible(false);
        inserisciEventiAvversi.add(severitaEA5).setVisible(false);
        inserisciEventiAvversi.add(noteEA5).setVisible(false);
        inserisciEventiAvversi.add(severitaEA6).setVisible(false);
        inserisciEventiAvversi.add(noteEA6).setVisible(false);
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
        inserisciEventiAvversi.add(status);

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

        status.setBounds(30, 400, 250, 30);
        status.setFont(new Font("Georgia", Font.BOLD, 15));
        status.setBackground(new Color(209, 245, 250));
        status.setForeground(new Color(0x07AF45));
        status.setVisible(false);

        inserisciEventiAvversi.add(registraEA);
        inserisciEventiAvversi.add(pulisciEventiAvversi);
        inserisciEventiAvversi.add(status);



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
            //Passaggio da un Panel all'altro
            //in seguito inserire comando automatico che capisca se gli eventi avversi sono registrati o meno
            if (switcha.isSelected()) {
                //sistemare grafica
                add(riepilogoEventiAvversiPersonali).setVisible(false);
                add(inserisciEventiAvversi).setVisible(true);

            } else if (!switcha.isSelected()){
                add(inserisciEventiAvversi).setVisible(false);
                add(riepilogoEventiAvversiPersonali).setVisible(true);

            }

        }else if (e.getSource() == checkBoxMaldiTesta) {
            if (checkBoxMaldiTesta.isSelected()) {
                severitaMDT.setVisible(true);
                noteMalditesta.setVisible(true);
                noteEA1.setVisible(true);
                severitaEA1.setVisible(true);
                severitaMDT.setSelectedItem("1");
                noteMalditesta.setText("");

            }else{
                severitaMDT.setVisible(false);
                noteMalditesta.setVisible(false);
                noteEA1.setVisible(false);
                severitaEA1.setVisible(false);
                severitaMDT.setSelectedItem("1");
                noteMalditesta.setText("");
            }
        }
        else if (e.getSource() == checkBoxFebbre) {
            if (checkBoxFebbre.isSelected()) {
                severitaFebbre.setVisible(true);
                noteFebbre.setVisible(true);
                noteEA2.setVisible(true);
                severitaEA2.setVisible(true);
                severitaFebbre.setSelectedItem("1");
                noteFebbre.setText("");

            }else{
                severitaFebbre.setVisible(false);
                noteFebbre.setVisible(false);
                noteEA2.setVisible(false);
                severitaEA2.setVisible(false);
                severitaFebbre.setSelectedItem("1");
                noteFebbre.setText("");
            }
        }
        else if (e.getSource() == checkBoxDMA) {
            if (checkBoxDMA.isSelected()) {
                severitaDMA.setVisible(true);
                noteDMA.setVisible(true);
                noteEA3.setVisible(true);
                severitaEA3.setVisible(true);
                severitaDMA.setSelectedItem("1");
                noteDMA.setText("");

            }else{
                severitaDMA.setVisible(false);
                noteDMA.setVisible(false);
                noteEA3.setVisible(false);
                severitaEA3.setVisible(false);
                severitaDMA.setSelectedItem("1");
                noteDMA.setText("");
            }
        }
        else if (e.getSource() == checkBoxlinfoadenopatia) {
            if (checkBoxlinfoadenopatia.isSelected()) {
                severitalinfoadenopatia.setVisible(true);
                notelinfoadenopatia.setVisible(true);
                noteEA4.setVisible(true);
                severitaEA4.setVisible(true);
                severitalinfoadenopatia.setSelectedItem("1");
                notelinfoadenopatia.setText("");

            }else{
                severitalinfoadenopatia.setVisible(false);
                notelinfoadenopatia.setVisible(false);
                noteEA4.setVisible(false);
                severitaEA4.setVisible(false);
                severitalinfoadenopatia.setSelectedItem("1");
                notelinfoadenopatia.setText("");
            }
        }
        else if (e.getSource() == checkBoxtachicardia) {
            if (checkBoxtachicardia.isSelected()) {
                severitatachicardia.setVisible(true);
                notetachicardia.setVisible(true);
                noteEA5.setVisible(true);
                severitaEA5.setVisible(true);
                severitatachicardia.setSelectedItem("1");
                notetachicardia.setText("");

            }else{
                severitatachicardia.setVisible(false);
                notetachicardia.setVisible(false);
                noteEA5.setVisible(false);
                severitaEA5.setVisible(false);
                severitatachicardia.setSelectedItem("1");
                notetachicardia.setText("");
            }
        }
        else if (e.getSource() == checkBoxCrisiIpertensiva) {
            if (checkBoxCrisiIpertensiva.isSelected()) {
                severitaCrisiIpertensiva.setVisible(true);
                notetaCrisiIpertensiva.setVisible(true);
                noteEA6.setVisible(true);
                severitaEA6.setVisible(true);
                severitaCrisiIpertensiva.setSelectedItem("1");
                notetaCrisiIpertensiva.setText("");

            }else{
                severitaCrisiIpertensiva.setVisible(false);
                notetaCrisiIpertensiva.setVisible(false);
                noteEA6.setVisible(false);
                severitaEA6.setVisible(false);
                severitaCrisiIpertensiva.setSelectedItem("1");
                notetaCrisiIpertensiva.setText("");
            }
        }else if(e.getSource() == pulisciEventiAvversi){

            severitaMDT.setSelectedItem("1");
            noteMalditesta.setText("");
            checkBoxMaldiTesta.setSelected(false);
            severitaMDT.setVisible(false);
            noteMalditesta.setVisible(false);
            noteEA1.setVisible(false);
            severitaEA1.setVisible(false);

            severitaFebbre.setSelectedItem("1");
            noteFebbre.setText("");
            checkBoxFebbre.setSelected(false);
            severitaFebbre.setVisible(false);
            noteFebbre.setVisible(false);
            noteEA2.setVisible(false);
            severitaEA2.setVisible(false);

            severitaDMA.setSelectedItem("1");
            noteDMA.setText("");
            checkBoxDMA.setSelected(false);
            severitaDMA.setVisible(false);
            noteDMA.setVisible(false);
            noteEA3.setVisible(false);
            severitaEA3.setVisible(false);

            severitalinfoadenopatia.setSelectedItem("1");
            notelinfoadenopatia.setText("");
            checkBoxlinfoadenopatia.setSelected(false);
            severitalinfoadenopatia.setVisible(false);
            notelinfoadenopatia.setVisible(false);
            noteEA4.setVisible(false);
            severitaEA4.setVisible(false);

            severitatachicardia.setSelectedItem("1");
            notetachicardia.setText("");
            checkBoxtachicardia.setSelected(false);
            severitatachicardia.setVisible(false);
            notetachicardia.setVisible(false);
            noteEA5.setVisible(false);
            severitaEA5.setVisible(false);

            severitaCrisiIpertensiva.setSelectedItem("1");
            notetaCrisiIpertensiva.setText("");
            checkBoxCrisiIpertensiva.setSelected(false);
            severitaCrisiIpertensiva.setVisible(false);
            notetaCrisiIpertensiva.setVisible(false);
            noteEA6.setVisible(false);
            severitaEA6.setVisible(false);

            status.setVisible(false);



        }else if(e.getSource() == registraEA){
            status.setVisible(true);

        }
    }

}

