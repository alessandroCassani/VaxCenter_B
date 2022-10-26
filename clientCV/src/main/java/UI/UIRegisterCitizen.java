package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Objects;



import CheckData.EmailValidator;
import CheckData.CFValidator;
import CheckData.PasswordValidator;
import database.RoundButton;
import UI.graphics.RoundJTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import util.CentroVaccinale;
import util.Cittadino;
import util.Qualificatore;
import util.Tipologia;


/**
 * La classe UICitizen crea l'interfaccia dove viene presentato il menu' di funzionalita' a cui l'utente cittadino puo' accedervi
 *
 * @author Paolo Bruscagin
 * @author Alessandro Cassani
 */

public class UIRegisterCitizen extends JFrame implements ActionListener {



    /**
     *  Menu a tendina che indica un insieme di centri vaccinali registrati a sistema che l'utente puo' selezionare a seguito di una ricerca nel DB
     */
    JComboBox<String> nomeCV = new JComboBox<>(new String[]{""}); // ricerca in db

    /**
     * nome del cittadino
     */
    RoundJTextField nomeCittadino = new RoundJTextField(30);

    /**
     * cognome del cittadino
     */
    RoundJTextField cognomeCittadino = new RoundJTextField(30);

    /**
     * codice fiscale del cittadino
     */
    RoundJTextField codiceFiscale = new RoundJTextField(16);

    /**
     * email del cittadino
     */
    RoundJTextField email = new RoundJTextField(30);

    /**
     * user ID del cittadino
     */
    RoundJTextField userID =new RoundJTextField(20);

    /**
     * password del cittadino
     */
    RoundJTextField password = new RoundJTextField(20);

    /**
     * conferma password del cittadino
     */
    RoundJTextField ripetiPassword = new RoundJTextField(20);

    /**
     * ID univoco del cittadino fornito al momento della vaccinazione
     */
    RoundJTextField IDUnivoco = new RoundJTextField(16);

    /**
     * bottone per l'avvio del processo di registrazione a sistema di un cittadino
     */
    RoundButton registraCittadino = new RoundButton("REGISTRA");

    /**
     * bottone che permette l'eliminazione delle stringhe inserite nei campi per la registrazione
     */
    JButton pulisci;

    /**
     * checkBox che rende visibili le stringhe inserite come password
     */
    JCheckBox showPassword = new JCheckBox("mostra password");

    /**
     * bottone che permette il ritorno alla UI precedente (UICitizen)
     */
    JButton backToCitizen;




    /**
     * costruttore che permette la creazione dei componenti di interfaccia grafica della schermata di registrazione del cittadino
     *
     * @author Paolo Bruscagin
     * @author Alessandro cassani
     */
    public UIRegisterCitizen(){

        Border bordobtnInd = new LineBorder(new Color(181, 226, 232), 2, true);

        JLabel titoloCittadino = new JLabel("Registrati presso un Centro Vaccinale");
        titoloCittadino.setFont(new Font("Georgia", Font.BOLD, 20));
        add(titoloCittadino).setBounds(320, 0, 550, 75);

        JLabel labelNome = new JLabel("Nome Centro Vaccinale:");
        labelNome.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelNome).setBounds(100, 40, 550, 75);

        nomeCV.setFont(new Font("Arial", Font.ITALIC, 20));
        nomeCV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        nomeCV.setBounds(100, 95, 790, 55);
        nomeCV.setBackground(Color.WHITE);
        ((JLabel)nomeCV.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        AutoCompleteDecorator.decorate(nomeCV);

        JLabel labelnomeCit = new JLabel("Nome:");
        labelnomeCit.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelnomeCit).setBounds(100, 140, 550, 75);

        nomeCittadino.setFont(new Font("Arial", Font.ITALIC, 20));
        nomeCittadino.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        nomeCittadino.setPreferredSize(new Dimension(325, 75));
        nomeCittadino.setBounds(100, 195, 250, 55);

        JLabel labelcognomeCit = new JLabel("Cognome:");
        labelcognomeCit.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelcognomeCit).setBounds(370, 140, 250, 75);

        cognomeCittadino.setFont(new Font("Arial", Font.ITALIC, 20));
        cognomeCittadino.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        cognomeCittadino.setPreferredSize(new Dimension(325, 75));
        cognomeCittadino.setBounds(370, 195, 250, 55);

        JLabel labelCF = new JLabel("Codice Fiscale:");
        labelCF.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelCF).setBounds(640, 140, 250, 75);

        codiceFiscale.setFont(new Font("Arial", Font.ITALIC, 20));
        codiceFiscale.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        codiceFiscale.setPreferredSize(new Dimension(325, 75));
        codiceFiscale.setBounds(640, 195, 250, 55);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelEmail).setBounds(100, 240, 250, 75);

        email.setFont(new Font("Arial", Font.ITALIC, 20));
        email.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        email.setPreferredSize(new Dimension(325, 75));
        email.setBounds(100, 295, 250, 55);

        JLabel labelUserID = new JLabel("User ID:");
        labelUserID.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelUserID).setBounds(370, 240, 250, 75);

        userID.setFont(new Font("Arial", Font.ITALIC, 20));
        userID.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        userID.setPreferredSize(new Dimension(325, 75));
        userID.setBounds(370, 295, 250, 55);

        JLabel labelIDUnivoco = new JLabel("ID Vaccinazione:");
        labelIDUnivoco.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelIDUnivoco).setBounds(640, 240, 250, 75);

        IDUnivoco.setFont(new Font("Arial", Font.ITALIC, 20));
        IDUnivoco.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        IDUnivoco.setBounds(640, 295, 250, 55);
        IDUnivoco.setBackground(Color.WHITE);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelPassword).setBounds(100, 350, 250, 55);

        password.setFont(new Font("Arial", Font.ITALIC, 20));
        password.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        password.setBounds(100, 390, 250, 55);
        password.setBackground(Color.WHITE);
        password.setEchoChar('*');

        JLabel labelRipetiPassword = new JLabel("Ripeti Password:");
        labelRipetiPassword.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelRipetiPassword).setBounds(100, 435, 550, 55);

        ripetiPassword.setFont(new Font("Arial", Font.ITALIC, 20));
        ripetiPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        ripetiPassword.setBounds(100, 475, 250, 55);
        ripetiPassword.setBackground(Color.WHITE);
        ripetiPassword.setEchoChar('*');

        showPassword.setFont(new Font("Arial", Font.BOLD, 15));
        showPassword.setBackground(new Color(181, 226, 232));
        showPassword.setBounds(370,400,160,15);
        showPassword.addActionListener(this);

        JLabel labelInfopsw = new JLabel("Requisiti Password:"); // serve per i requisiti password
        labelInfopsw.setFont(new Font("Georgia", Font.BOLD, 11));
        labelInfopsw.setForeground(new Color(65,102,245));
        add(labelInfopsw).setBounds(373, 420, 250, 15);

        JLabel labelInfopsw1 = new JLabel("-Lunghezza compresa tra 8 e 20 caratteri"); // serve per i requisiti password
        labelInfopsw1.setFont(new Font("Georgia", Font.BOLD, 11));
        labelInfopsw1.setForeground(new Color(65,102,245));
        add(labelInfopsw1).setBounds(372, 435, 290, 11);

        JLabel labelInfopsw2 = new JLabel("-Almeno una lettera maiuscola e una minuscola"); // serve per i requisiti password
        labelInfopsw2.setFont(new Font("Georgia", Font.BOLD, 11));
        labelInfopsw2.setForeground(new Color(65,102,245));
        add(labelInfopsw2).setBounds(372, 447, 340, 11);

        JLabel labelInfopsw3 = new JLabel("-Almeno un numero");
        labelInfopsw3.setFont(new Font("Georgia", Font.BOLD, 11));
        labelInfopsw3.setForeground(new Color(65,102,245));
        add(labelInfopsw3).setBounds(372, 459, 290, 11);

        JLabel labelInfopsw4 = new JLabel("-Almeno un carattere speciale come ! @ # & ( )");
        labelInfopsw4.setFont(new Font("Georgia", Font.BOLD, 11));
        labelInfopsw4.setForeground(new Color(65,102,245));
        add(labelInfopsw4).setBounds(372, 471, 370, 11);

        registraCittadino.setBounds(675, 380, 150, 50);
        registraCittadino.setFont(new Font("Georgia", Font.BOLD, 17));
        registraCittadino.setBackground(new Color(0,0,128));
        registraCittadino.setForeground(Color.WHITE);
        registraCittadino.setFocusable(false);
        registraCittadino.addActionListener(this);


        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/color50ind.png")));
        backToCitizen = new JButton(ind);
        backToCitizen.setBounds(10, 5, 55 , 55);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 17));
        backToCitizen.setBackground(new Color(181, 226, 232));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnInd);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);
        backToCitizen.setContentAreaFilled(false);


        ImageIcon pul = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/coloroPul50.png")));
        pulisci = new JButton(pul);
        pulisci.setBounds(850, 380, 50, 50);
        pulisci.setFont(new Font("Georgia", Font.BOLD, 17));
        pulisci.setBackground(new Color(181, 226, 232));
        pulisci.setForeground(Color.WHITE);
        pulisci.setBorder(bordobtnInd);
        pulisci.setFocusable(false);
        pulisci.addActionListener(this);
        pulisci.setOpaque(true);

        setLayout(null);
        add(nomeCV);
        add(nomeCittadino);
        add(cognomeCittadino);
        add(codiceFiscale);
        add(email);
        add(userID);
        add(password);
        add(showPassword);
        add(ripetiPassword);
        add(IDUnivoco);
        add(registraCittadino);
        add(pulisci);
        add(backToCitizen);



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
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setTitle("Registrazione");
        setForeground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize (1000,600);
        getContentPane().setBackground(new Color(181, 226, 232));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    private void registra(){
        String nomeCentro = Objects.requireNonNull(nomeCV.getSelectedItem().toString());
        String nome = nomeCittadino.getText();
        String cognome = cognomeCittadino.getText();
        String CF = codiceFiscale.getText();
        String emailCittadino = email.getText();
        String username = userID.getText();
        String IDVaccinazione = IDUnivoco.getText();
        String psw = password.getText();
        String conferma_psw = ripetiPassword.getText();

        try {
            ServerPointer.getStub().registraCentroVaccinale(new Cittadino();
            JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo!", "Messaggio",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | RemoteException ex) {
            throw new RuntimeException(ex);

        }
    }

    /**
     * metodo che permette di gestire gli eventi associati ai listener dei componenti di UI attivati dall'utente
     * @param e the event to be processed
     *
     * @author Paolo Bruscagin
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backToCitizen) {
            this.dispose();
            new UICitizen();
        } else if (e.getSource() == registraCittadino) {
            EmailValidator emailValidator = new EmailValidator();
            CFValidator cfvalidator = new CFValidator();
            PasswordValidator pswvalidator = new PasswordValidator();

            if(!emailValidator.validate(email.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Errore! Riprovare ...", "Messaggio",JOptionPane.ERROR_MESSAGE);

            } else if (!cfvalidator.validate(codiceFiscale.getText().toUpperCase().trim())) {
                JOptionPane.showMessageDialog(null, "Errore! Riprovare ...", "Messaggio",JOptionPane.ERROR_MESSAGE);

            } else if (!pswvalidator.validate(password.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Errore! Riprovare ...", "Messaggio",JOptionPane.ERROR_MESSAGE);
            }else{
                registra();
                nomeCV.setEnabled(false);
                nomeCittadino.setEditable(false);
                cognomeCittadino.setEditable(false);
                codiceFiscale.setEditable(false);
                email.setEditable(false);
                userID.setEditable(false);
                IDUnivoco.setEditable(false);
                password.setEditable(false);
                ripetiPassword.setEditable(false);
            }

        }else if(e.getSource() == pulisci) {
            nomeCV.setSelectedItem("");
            nomeCittadino.setText("");
            cognomeCittadino.setText("");
            email.setText("");
            userID.setText("");
            password.setText("");
            ripetiPassword.setText("");
            IDUnivoco.setText("");
            showPassword.setSelected(false);
            nomeCV.setEnabled(true);
            nomeCittadino.setEditable(true);
            cognomeCittadino.setEditable(true);
            codiceFiscale.setEditable(true);
            email.setEditable(true);
            userID.setEditable(true);
            IDUnivoco.setEditable(true);
            password.setEditable(true);
            ripetiPassword.setEditable(true);

        }else if(e.getSource() == showPassword){
            if (showPassword.isSelected()) {
                password.setEchoChar((char) 0);
                ripetiPassword.setEchoChar((char) 0);
            }
            else{
                password.setEchoChar('*');
                ripetiPassword.setEchoChar('*');
            }
        }
    }
}