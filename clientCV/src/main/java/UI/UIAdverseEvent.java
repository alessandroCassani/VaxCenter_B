package UI;

import database.RoundButton;
import util.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    RoundButton registraEA = new RoundButton("REGISTRA");

    /**
     * Bottone per tornare alle UICitizen dalla inserisci eventi avversi
     */
    JButton backToCitizen;

    /**
     * Bottone per tornare alle UICitizen dal riepilogo
     */
    JButton backToCitizenR;


    //CheckBox temporanea che andrà cancellata per verificare il cambiamento del Panel
    //JCheckBox switcha = new JCheckBox();


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
    JLabel labelmalDiTesta = new JLabel("Mal di Testa");


    /**
     * Severità Mal di testa (da 1 a 5)
     */

    JComboBox severitaMDT = new JComboBox<>(new String[]{"0", "1", "2", "3", "4", "5"});


    // Sintomatologia Febbre

    /**
     * Label Febbre
     */
    JLabel labelFebbre = new JLabel("Febbre");



    /**
     * Severità Mal di testa (da 1 a 5)
     */

    JComboBox severitaFebbre = new JComboBox<>(new String[]{"0", "1", "2", "3", "4", "5"});


    // Sintomatologia Dolori Muscolari e/o Articolari

    /**
     * Label Dolori Muscolari e/o Articolari
     */

    JLabel labelDMA = new JLabel("Dolori Muscolari");

    /**
     * Continuzione Label
     */

    JLabel labelDMAc = new JLabel("e Articolari");



    /**
     * Severità Dolori Muscolari e/o Articolari (da 1 a 5)
     */

    JComboBox severitaDMA = new JComboBox<>(new String[]{"0", "1", "2", "3", "4", "5"});


    // Sintomatologia Linfoadenopatia

    /**
     * Label Linfoadenopatia
     */
    JLabel labellinfoadenopatia = new JLabel("Linfoadenopatia");



    /**
     * Severità Linfoadenopatia (da 1 a 5)
     */

    JComboBox severitalinfoadenopatia = new JComboBox<>(new String[]{"0", "1", "2", "3", "4", "5"});


    // Sintomatologia Tachicardia

    /**
     * Label Tachicardia
     */
    JLabel labeltachicardia = new JLabel("Tachicardia");



    /**
     * Severità Tachicardia (da 1 a 5)
     */

    JComboBox severitatachicardia = new JComboBox<>(new String[]{"0", "1", "2", "3", "4", "5"});


    // Sintomatologia Crisi Ipertensiva

    /**
     * Label Crisi Ipertensiva
     */
    JLabel labelCrisiIpertensiva = new JLabel("Crisi Ipertensiva");


    /**
     * Severità Crisi Ipertensiva (da 1 a 5)
     */
    JComboBox severitaCrisiIpertensiva = new JComboBox<>(new String[]{"0", "1", "2", "3", "4", "5"});


    //Text Area note (valevole per tutti i sintomi)
    /**
     * Text Area per le note generali max. 256 caratteri
     */

    JTextArea noteGenerali = new JTextArea();


    //Tabella riassuntiva Eventi Avversi già registrati
    /**
     * nome colonna 1
     */
    String nomeEvento = " EVENTO AVVERSO";
    /**
     * nome colonna 2
     */
    String severitàEvento = " SEVERITA'";



    /**
     * Label per il riepilogo note
     */
    JLabel riepilogoNote = new JLabel("Note:");

    /**
     * Text Area riepilogo note
     */
    JTextArea rn = new JTextArea();

    //JLabel per severità e note di ogni riga
    /**
     * Label Severità
     */
    JLabel severitaEA1 = new JLabel("Severità");

    /**
     * Label Severità
     */
    JLabel noteGeneraliLabel = new JLabel("Note (max. 256)");

    /**
     * Label Severità
     */
    JLabel severitaEA2 = new JLabel("Severità");

    /**
     * Label Severità
     */
    JLabel severitaEA3 = new JLabel("Severità");

    /**
     * Label Severità
     */
    JLabel severitaEA4 = new JLabel("Severità");

    /**
     * Label Severità
     */
    JLabel severitaEA5 = new JLabel("Severità");

    /**
     * Label Severità
     */
    JLabel severitaEA6 = new JLabel("Severità");




    static String user;
    public UIAdverseEvent(String username) throws RemoteException {


        Border bordobtn_AE = new LineBorder(new Color(0, 49, 83), 2, true);
        Border bordobtnPul = new LineBorder(new Color(209, 245, 250), 2, true);
        user = username;



        String [] info;
        info = ServerPointer.getStub().getPersonAE(user);

        String [] infoC;
        infoC = ServerPointer.getStub().getInfoCittadino(user);



        String[][] data = {{nomeEvento, severitàEvento}, {" Mal di testa", info[0]}, {" Febbre", info[1]}, {" Tachicardia", info[2]}, {" Dolori Musc. Art.", info[3]}, {" Linfoadenopatia", info[4]}, {" Crisi Ipertensiva", info[5]}};

        String[] coloumn = {"EVENTO AVVERSO", "SEVERITA'"};

        JTable tabellaRiepilogo = new JTable(data, coloumn);



        infoUtente.setBounds(550, -5, 450, 570);
        infoUtente.setLayout(null);
        infoUtente.setBackground(new Color(181, 226, 232));
        infoUtente.setBorder(bordobtn_AE);


        //Informazioni InfoUtente
        JLabel titoloRiepilogo = new JLabel("Riepilogo Dati:");
        titoloRiepilogo.setFont(new Font("Georgia", Font.BOLD, 20));
        titoloRiepilogo.setBounds(100, 50, 200, 30);

        JLabel nomeUtente = new JLabel("Nome: " + infoC[1]);
        nomeUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        nomeUtente.setBounds(20, 100, 400, 20);

        JLabel cognomeUtente = new JLabel("Cognome: " + infoC[2]);
        cognomeUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        cognomeUtente.setBounds(20, 160, 400, 20);

        JLabel codiceFiscaleUtente = new JLabel("Codice Fiscale: " + infoC[3]);
        codiceFiscaleUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        codiceFiscaleUtente.setBounds(20, 220, 400, 20);

        JLabel emailUtente = new JLabel("Email: " + infoC[4]);
        emailUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        emailUtente.setBounds(20, 280, 400, 20);

        JLabel UserIDUtente = new JLabel("UserID: " + infoC[5]);
        UserIDUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        UserIDUtente.setBounds(20, 340, 400, 20);

        JLabel IDUnivocoUtente = new JLabel("ID Univoco: " + infoC[0]);
        IDUnivocoUtente.setFont(new Font("Georgia", Font.BOLD, 15));
        IDUnivocoUtente.setBounds(20, 400, 400, 20);

        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/color50ind.png")));

        backToCitizen = new JButton(ind);
        backToCitizen.setBounds(10, 10, 55, 55);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 20));
        backToCitizen.setBackground(new Color(181, 226, 232));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnPul);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);
        backToCitizen.setContentAreaFilled(false);

        backToCitizenR = new JButton(ind);
        backToCitizenR.setBounds(10, 10, 55, 55);
        backToCitizenR.setFont(new Font("Georgia", Font.BOLD, 20));
        backToCitizenR.setBackground(new Color(181, 226, 232));
        backToCitizenR.setForeground(Color.WHITE);
        backToCitizenR.setBorder(bordobtnPul);
        backToCitizenR.setFocusable(false);
        backToCitizenR.addActionListener(this);
        backToCitizenR.setOpaque(true);
        backToCitizenR.setContentAreaFilled(false);


        infoUtente.add(titoloRiepilogo);
        infoUtente.add(nomeUtente);
        infoUtente.add(cognomeUtente);
        infoUtente.add(codiceFiscaleUtente);
        infoUtente.add(emailUtente);
        infoUtente.add(UserIDUtente);
        infoUtente.add(IDUnivocoUtente);


        add(infoUtente);


        //Label severità e note
        severitaEA1.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA1.setBounds(180, 57, 60, 12);

        noteGeneraliLabel.setFont(new Font("Georgia", Font.BOLD, 10));
        noteGeneraliLabel.setBounds(270, 57, 250, 12);

        severitaEA2.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA2.setBounds(180, 107, 60, 12);


        severitaEA3.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA3.setBounds(180, 157, 60, 12);

        severitaEA4.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA4.setBounds(180, 207, 60, 12);


        severitaEA5.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA5.setBounds(180, 257, 60, 12);


        severitaEA6.setFont(new Font("Georgia", Font.BOLD, 10));
        severitaEA6.setBounds(180, 307, 60, 12);


        //Mal di Testa
        labelmalDiTesta.setFont(new Font("Georgia", Font.BOLD, 12));
        labelmalDiTesta.setBounds(20, 75, 100, 20);


        severitaMDT.setFont(new Font("Arial", Font.BOLD, 12));
        severitaMDT.setBounds(180, 75, 60, 30);
        severitaMDT.setBackground(Color.WHITE);
        severitaMDT.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));


        //Febbre
        labelFebbre.setFont(new Font("Georgia", Font.BOLD, 12));
        labelFebbre.setBounds(20, 125, 100, 20);


        severitaFebbre.setFont(new Font("Arial", Font.BOLD, 12));
        severitaFebbre.setBounds(180, 125, 60, 30);
        severitaFebbre.setBackground(Color.WHITE);
        severitaFebbre.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));


        //Dolore Addominale e/o Articolare
        labelDMA.setFont(new Font("Georgia", Font.BOLD, 12));
        labelDMA.setBounds(20, 175, 160, 12);
        labelDMAc.setFont(new Font("Georgia", Font.BOLD, 12));
        labelDMAc.setBounds(20, 188, 160, 12);


        severitaDMA.setFont(new Font("Arial", Font.BOLD, 12));
        severitaDMA.setBounds(180, 175, 60, 30);
        severitaDMA.setBackground(Color.WHITE);
        severitaDMA.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        //

        //Linfoadenopatia
        labellinfoadenopatia.setFont(new Font("Georgia", Font.BOLD, 12));
        labellinfoadenopatia.setBounds(20, 225, 160, 20);


        severitalinfoadenopatia.setFont(new Font("Arial", Font.BOLD, 12));
        severitalinfoadenopatia.setBounds(180, 225, 60, 30);
        severitalinfoadenopatia.setBackground(Color.WHITE);
        severitalinfoadenopatia.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));


        //Tachicardia
        labeltachicardia.setFont(new Font("Georgia", Font.BOLD, 12));
        labeltachicardia.setBounds(20, 275, 100, 20);


        severitatachicardia.setFont(new Font("Arial", Font.BOLD, 12));
        severitatachicardia.setBounds(180, 275, 60, 30);
        severitatachicardia.setBackground(Color.WHITE);
        severitatachicardia.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));


        //Crisi Ipertensiva
        labelCrisiIpertensiva.setFont(new Font("Georgia", Font.BOLD, 12));
        labelCrisiIpertensiva.setBounds(20, 325, 160, 20);


        severitaCrisiIpertensiva.setFont(new Font("Arial", Font.BOLD, 12));
        severitaCrisiIpertensiva.setBounds(180, 325, 60, 30);
        severitaCrisiIpertensiva.setBackground(Color.WHITE);
        severitaCrisiIpertensiva.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));


        //note generali text area
        noteGenerali.setFont(new Font("Arial", Font.BOLD, 12));
        noteGenerali.setPreferredSize(new Dimension(325, 75));
        noteGenerali.setBounds(270, 75, 260, 270);
        noteGenerali.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        noteGenerali.setLineWrap(true);
        noteGenerali.setWrapStyleWord(true);

        //riepilogo note
        rn.setFont(new Font("Arial", Font.BOLD, 12));
        rn.setBounds(100, 450, 360, 70);
        rn.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        rn.setLineWrap(true);
        rn.setWrapStyleWord(true);
        rn.setEditable(false);


        //Panel info Eventi avversi già registrati


        riepilogoEventiAvversiPersonali.setBounds(0, 0, 550, 500);
        riepilogoEventiAvversiPersonali.setLayout(null);
        riepilogoEventiAvversiPersonali.setBackground(new Color(209, 245, 250));

        giaRegistrati.setFont(new Font("Georgia", Font.BOLD, 15));
        riepilogoEventiAvversiPersonali.add(giaRegistrati).setBounds(180, 25, 300, 20);
        tabellaRiepilogo.setFont(new Font("Georgia", Font.BOLD, 15));
        tabellaRiepilogo.setBounds(100, 70, 360, 350);
        tabellaRiepilogo.setBackground(new Color(209, 245, 250));
        tabellaRiepilogo.setRowHeight(50);
        tabellaRiepilogo.setBorder(bordobtn_AE);
        tabellaRiepilogo.setEnabled(false);
        riepilogoNote.setBounds(100, 430, 400, 15);
        riepilogoNote.setFont(new Font("Georgia", Font.BOLD, 12));
        riepilogoEventiAvversiPersonali.add(riepilogoNote);
        riepilogoEventiAvversiPersonali.add(tabellaRiepilogo);
        riepilogoEventiAvversiPersonali.add(rn);
        rn.setText(info[6]);
        riepilogoEventiAvversiPersonali.add(backToCitizenR);


        //Panel Inserimento Eventi Avversi
        inserisciEventiAvversi.setBounds(0, 0, 550, 500);
        inserisciEventiAvversi.setLayout(null);
        inserisciEventiAvversi.setBackground(new Color(209, 245, 250));

        labelinsEventiAvversi.setFont(new Font("Georgia", Font.BOLD, 15));
        labelinsEventiAvversi.setBounds(270, 20, 200, 20);

        inserisciEventiAvversi.add(labelinsEventiAvversi);
        inserisciEventiAvversi.add(severitaEA1);
        inserisciEventiAvversi.add(noteGeneraliLabel);
        inserisciEventiAvversi.add(severitaEA2);
        inserisciEventiAvversi.add(severitaEA3);
        inserisciEventiAvversi.add(severitaEA4);
        inserisciEventiAvversi.add(severitaEA5);
        inserisciEventiAvversi.add(severitaEA6);
        inserisciEventiAvversi.add(labelmalDiTesta);
        inserisciEventiAvversi.add(severitaMDT);
        inserisciEventiAvversi.add(noteGenerali);
        inserisciEventiAvversi.add(labelFebbre);
        inserisciEventiAvversi.add(severitaFebbre);
        inserisciEventiAvversi.add(labelDMA);
        inserisciEventiAvversi.add(labelDMAc);
        inserisciEventiAvversi.add(severitaDMA);
        inserisciEventiAvversi.add(labellinfoadenopatia);
        inserisciEventiAvversi.add(severitalinfoadenopatia);
        inserisciEventiAvversi.add(labeltachicardia);
        inserisciEventiAvversi.add(severitatachicardia);
        inserisciEventiAvversi.add(labelCrisiIpertensiva);
        inserisciEventiAvversi.add(severitaCrisiIpertensiva);
        inserisciEventiAvversi.add(backToCitizen);

        registraEA.setBounds(300, 425, 150, 50);
        registraEA.setFont(new Font("Georgia", Font.BOLD, 15));
        registraEA.setBackground(new Color(0, 0, 128));
        registraEA.setForeground(Color.WHITE);
        registraEA.setFocusable(false);
        registraEA.addActionListener(this);


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

        inserisciEventiAvversi.add(registraEA);
        inserisciEventiAvversi.add(pulisciEventiAvversi);

        add(inserisciEventiAvversi).setVisible(false);
        add(riepilogoEventiAvversiPersonali).setVisible(false);


        if (ServerPointer.getStub().isAERegistered(user)){
                inserisciEventiAvversi.setVisible(false);
                riepilogoEventiAvversiPersonali.setVisible(true);
        }else{
            inserisciEventiAvversi.setVisible(true);
            riepilogoEventiAvversiPersonali.setVisible(false);
        }


        //Popup "Se sicuro di uscire?"
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                UIManager.put("OptionPane.yesButtonText", "Si");
                UIManager.put("OptionPane.noButtonText", "No");

                int resp = JOptionPane.showConfirmDialog(null, "Sei sicuro di uscire?",
                        "Esci?", JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });


        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setTitle("Eventi Avversi");
        setForeground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        getContentPane().setBackground(new Color(181, 226, 232));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);



    }

    /**
     * metodo privato che permette di registrare degli eventi avversi nel db
     * @author Paolo Bruscagin
     */
    private void registraEventiAvversi(){
        String note = noteGenerali.getText();
        ArrayList<Sintomo> sintomi = new ArrayList<Sintomo>();
        sintomi.add(new Sintomo(severitaMDT.getSelectedIndex(),Sintomatologia.MALDITESTA));
        sintomi.add(new Sintomo(severitaFebbre.getSelectedIndex(),Sintomatologia.FEBBRE));
        sintomi.add(new Sintomo(severitatachicardia.getSelectedIndex(),Sintomatologia.TACHICARDIA));
        System.out.println(severitatachicardia.getSelectedIndex());
        sintomi.add(new Sintomo(severitaDMA.getSelectedIndex(),Sintomatologia.DOLORI_MA));
        sintomi.add(new Sintomo(severitalinfoadenopatia.getSelectedIndex(),Sintomatologia.LINFOADENOPATIA));
        sintomi.add(new Sintomo(severitaCrisiIpertensiva.getSelectedIndex(),Sintomatologia.CRISIPERTENSIVA));
        //System.out.println(sintomi);



        try {

            ServerPointer.getStub().inserisciEventiAvversi((new EventiAvversi(note, sintomi)), user);
            JOptionPane.showMessageDialog(null, "Eventi Avversi Registrati con Successo!", "Messaggio",JOptionPane.INFORMATION_MESSAGE);

        } catch (RemoteException ex) {
            throw new RuntimeException(ex);

        }
    }




    /**
     * metodo che permette di gestire gli eventi associati ai listener dei componenti di UI attivati dall'utente
     *
     * @param e the event to be processed
     * @author Paolo Bruscagin
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToCitizen) {
            this.dispose();
            new UICitizen();
        } else if (e.getSource() == backToCitizenR) {
            this.dispose();
            new UICitizen();
        } else if (e.getSource() == pulisciEventiAvversi) {
            noteGenerali.setText("");
            severitaMDT.setSelectedItem("0");
            severitaFebbre.setSelectedItem("0");
            severitaDMA.setSelectedItem("0");
            severitalinfoadenopatia.setSelectedItem("0");
            severitatachicardia.setSelectedItem("0");
            severitaCrisiIpertensiva.setSelectedItem("0");

        } else if (e.getSource() == registraEA) {
            registraEventiAvversi();
            severitaMDT.setEnabled(false);
            severitaFebbre.setEnabled(false);
            severitaDMA.setEnabled(false);
            severitatachicardia.setEnabled(false);
            severitalinfoadenopatia.setEnabled(false);
            severitaCrisiIpertensiva.setEnabled(false);
            noteGenerali.setEditable(false);
            registraEA.setEnabled(false);
            pulisciEventiAvversi.setEnabled(false);


        }

    }
}

