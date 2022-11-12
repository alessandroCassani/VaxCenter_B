package UI;

import CheckData.CFValidator;
import database.RoundButton;
import UI.graphics.RoundJTextField;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


/**
 *  La classe UiRegisterVaccinated crea l'interfaccia grafica dove e' possibile inserire le informazioni necessarie alla registrazione a sistema di un vaccinato
 *
 * @author Paolo Bruscagin
 * @author Alessandro Cassani
 * @author Damiano Ficara
 * @author Luca perfetti
 */

public class UIRegisterVaccinated extends JFrame implements ActionListener {


    /**
     * Menu a tendina che indica un insieme di centri vaccinali registrati a sistema che l'utente puo' selezionare a seguito di una ricerca
     */
    JComboBox<String> nomeCV;

    /**
     * nome del vaccinato
     */
    RoundJTextField nome = new RoundJTextField(30);

    /**
     * cognome del vaccinato
     */
    RoundJTextField cognome = new RoundJTextField(30);

    /**
     * codice fiscale del vaccinato
     */
    RoundJTextField codiceFiscale = new RoundJTextField(16);

    /**
     * menu' dal quale e' possibile selezionare una data nel formtato gg/mm/aaaa
     */
    JXDatePicker data = new JXDatePicker();

    /**
     * Menu a tendina che indica le tipologie di vaccino somministrate che l'utente puo' selezionare
     */
    JComboBox vaccinoSomministrato = new JComboBox<>(new String[]{"","Pfizer", "AstraZeneca", "Moderna", "J&J"});




    /**
     * bottone per l'avvio del processo di registrazione del vaccinato
     */
    RoundButton registraVaccinato = new RoundButton("REGISTRA");

    /**
     * bottone per tornare alla precedente pagina di UI (UIVaccineOperator)
     */
    JButton backToVaccineOperator;

    /**
     * bottone per eliminare le stringhe inserite in fase di registrazione
     */
    JButton pulisci;
    /**
     * Label ID Univoco 16 bit
     */

    JLabel IDUnivoco = new JLabel();

    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica UIRegisterVaccinated
     *
     * @author Paolo Bruscagin
     * @author Alessandro Cassani
     */

    public UIRegisterVaccinated(){



        Border bordobtnInd = new LineBorder(new Color(181, 226, 232), 2, true);

        JLabel tiotoloVaccinato = new JLabel("Inserisci un nuovo Vaccinato");
        tiotoloVaccinato.setFont(new Font("Georgia", Font.BOLD, 20));
        add(tiotoloVaccinato).setBounds(350, 0, 550, 55);


        JLabel labelnomeVac = new JLabel("Nome:");
        labelnomeVac.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelnomeVac).setBounds(280, 45, 550, 55);

        nome.setFont(new Font("Arial", Font.ITALIC, 20));
        nome.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        nome.setPreferredSize(new Dimension(325, 55));
        nome.setBounds(140, 90, 325, 50);
        nome.setEchoChar((char) 0);

        JLabel labelcognomeVac = new JLabel("Cognome:");
        labelcognomeVac.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelcognomeVac).setBounds(650, 45, 550, 55);

        cognome.setFont(new Font("Arial", Font.ITALIC, 20));
        cognome.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        cognome.setPreferredSize(new Dimension(325, 55));
        cognome.setBounds(520, 90, 325, 50);
        cognome.setEchoChar((char) 0);

        JLabel labelcf = new JLabel("Codice Fiscale:");
        labelcf.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelcf).setBounds(250, 160, 550, 55);

        codiceFiscale.setFont(new Font("Arial", Font.ITALIC, 20));
        codiceFiscale.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        codiceFiscale.setPreferredSize(new Dimension(325, 55));
        codiceFiscale.setBounds(140, 205, 325, 50);
        codiceFiscale.setEchoChar((char) 0);

        JLabel labeldata = new JLabel("Data somministrazione:");
        labeldata.setFont(new Font("Georgia",Font.ITALIC, 17));
        add(labeldata).setBounds(530, 160, 550, 55);

        data.setFont(new Font("Arial", Font.ITALIC, 20));
        data.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        data.setPreferredSize(new Dimension(325, 55));
        data.setBounds(520, 205, 325, 50);
        data.setDate(new Date());

        JLabel labelTipVac = new JLabel("Tipologia:");
        labelTipVac.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelTipVac).setBounds(260, 275, 550, 55);

        vaccinoSomministrato.setFont(new Font("Arial", Font.ITALIC, 20));
        vaccinoSomministrato.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        vaccinoSomministrato.setBounds(140, 320, 325, 50);
        vaccinoSomministrato.setBackground(Color.WHITE);
        ((JLabel)vaccinoSomministrato.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        IDUnivoco.setBounds(350, 410, 350, 20);
        IDUnivoco.setFont(new Font("Georgia", Font.BOLD, 20));
        IDUnivoco.setBackground(new Color(0,0,128));
        IDUnivoco.setText("ID Univoco: ");



        registraVaccinato.setBounds(380, 460, 230, 60);
        registraVaccinato.setFont(new Font("Georgia", Font.BOLD, 20));
        registraVaccinato.setBackground(new Color(0,0,128));
        registraVaccinato.setForeground(Color.WHITE);
        registraVaccinato.setFocusable(false);
        registraVaccinato.addActionListener(this);

        JLabel labelNome = new JLabel("Nome Centro Vaccinale:");
        labelNome.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelNome).setBounds(590, 275, 550, 55);

        try{
            List<String> l = ServerPointer.getStub().getNomicentriVaccinali();
            l.add(0,"");
            String[] listCV = l.toArray((new String[l.size()]));
            nomeCV = new JComboBox(listCV);
        }catch (Exception e) {
            e.printStackTrace();
        }

        nomeCV.setFont(new Font("Arial", Font.ITALIC, 20));
        nomeCV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        nomeCV.setBounds(520, 320, 325, 50);
        nomeCV.setBackground(Color.WHITE);
        AutoCompleteDecorator.decorate(nomeCV);
        ((JLabel)nomeCV.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/color50ind.png")));
        backToVaccineOperator = new JButton(ind);
        backToVaccineOperator.setBounds(10, 10, 55 , 55);
        backToVaccineOperator.setFont(new Font("Georgia", Font.BOLD, 17));
        backToVaccineOperator.setBackground(new Color(181, 226, 232));
        backToVaccineOperator.setForeground(Color.WHITE);
        backToVaccineOperator.setBorder(bordobtnInd);
        backToVaccineOperator.setFocusable(false);
        backToVaccineOperator.addActionListener(this);
        backToVaccineOperator.setOpaque(true);
        backToVaccineOperator.setContentAreaFilled(false);


        ImageIcon pul = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/coloroPul50.png")));
        pulisci = new JButton(pul);
        pulisci.setBounds(650, 460, 50, 50);
        pulisci.setFont(new Font("Georgia", Font.BOLD, 17));
        pulisci.setBackground(new Color(181, 226, 232));
        pulisci.setForeground(Color.WHITE);
        pulisci.setBorder(bordobtnInd);
        pulisci.setFocusable(false);
        pulisci.addActionListener(this);
        pulisci.setOpaque(true);



        setLayout(null);
        add(backToVaccineOperator);
        add(nomeCV);
        add(nome);
        add(cognome);
        add(codiceFiscale);
        add(vaccinoSomministrato);
        add(registraVaccinato);
        add(pulisci);
        add(data);
        add(IDUnivoco);

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

        //Icona avvio del programma

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        setTitle("Registra Vaccinato");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setSize (1000,600);
        setLocationRelativeTo(null);
        setForeground(Color.WHITE);
        getContentPane().setBackground(new Color(181, 226, 232));
        setResizable(false);
        setVisible(true);
    }

    /**
     * metodo privato che permette di registrare le informazioni di un centro vaccinale nel DB
     * @author Paolo Bruscagin
     */

    private String registraVaccinato()  {
        String centrovaccinale = Objects.requireNonNull(nomeCV.getSelectedItem().toString().toUpperCase());
        String nomeVac = nome.getText().toUpperCase();
        String cognomeVac = cognome.getText().toUpperCase();
        String cfVac = codiceFiscale.getText().toUpperCase();
        String dataVaccino = getStringDate(data.getDate());
        String vacSommm  = Objects.requireNonNull(vaccinoSomministrato.getSelectedItem()).toString();
        BigInteger idunivoco = new BigInteger("-1");
        String id;
        try {

            id = ServerPointer.getStub().registraVaccinato(new Vaccinato(nomeVac, cognomeVac, cfVac, idunivoco , centrovaccinale, dataVaccino, Vaccino.getVaccino(vacSommm)));
            JOptionPane.showMessageDialog(null, "Vaccinato registrato con successo!", "Messaggio",JOptionPane.INFORMATION_MESSAGE);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
        return id;
    }

    /**
     * metodo che permette di ottenere la data con il formato italiano
     * @param d data scelta dall'utente
     *
     * @author Damiano Ficara
     */
    private String getStringDate(Date d) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = d.toInstant();
        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
        System.out.println(localDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = localDate.format(formatter);
        return formattedString;
    }

    /**
     * metodo che permette di gestire gli eventi associati ai listener dei componenti di UI attivati dall'utente
     * @param e the event to be processed
     *
     * @author Paolo Bruscagin
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean vac;
        try {
            vac = ServerPointer.getStub().isVaccinatedRegistrated(codiceFiscale.getText().toUpperCase());
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
        CFValidator cfvalidator = new CFValidator();

        if (e.getSource() == backToVaccineOperator) {
            this.dispose();
            new UIVaccineOperator();
        }else  if (e.getSource() == registraVaccinato) {

            if (nome.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Nome inserito non valido! Riprovare", "Messaggio",JOptionPane.ERROR_MESSAGE);

            }else if (cognome.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Cognome inserito non valido! Riprovare ...", "Messaggio",JOptionPane.ERROR_MESSAGE);

            }else if (!cfvalidator.validate(codiceFiscale.getText().toUpperCase().trim())) {
                JOptionPane.showMessageDialog(null, "Codice Fiscale inserito non valido! Riprovare ...", "Messaggio",JOptionPane.ERROR_MESSAGE);

            }else if (vaccinoSomministrato.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Vaccino selezionato non valido! Riprovare ...", "Messaggio",JOptionPane.ERROR_MESSAGE);

            }else if (nomeCV.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Centro Vaccinale selezionato non valido! Riprovare ...", "Messaggio",JOptionPane.ERROR_MESSAGE);

            }else if (vac){
                JOptionPane.showMessageDialog(null, "Cittadino già vaccinato!", "Messaggio",JOptionPane.ERROR_MESSAGE);
            } else {
                String id = registraVaccinato();
                if(!id.equals("-1")) {
                    IDUnivoco.setVisible(true);
                    JOptionPane.showMessageDialog(null, "Vaccinato registrato con successo! " +
                            "\n\n L'ID Univoco del Vaccinato è: \n\n" + (id), "Messaggio", JOptionPane.INFORMATION_MESSAGE);
                    nome.setEditable(false);
                    cognome.setEditable(false);
                    codiceFiscale.setEditable(false);
                    data.setEnabled(false);
                    vaccinoSomministrato.setEnabled(false);
                    nomeCV.setEnabled(false);
                    IDUnivoco.setVisible(true);
                    registraVaccinato.setEnabled(false);
                    IDUnivoco.setText("ID univoco: " + id);
                }
                else JOptionPane.showMessageDialog(null,"errore in fase di registrazione","errore",JOptionPane.ERROR_MESSAGE);
            }
        }
        if(e.getSource() == pulisci) {
            nomeCV.setSelectedItem("");
            nome.setText("");
            cognome.setText("");
            codiceFiscale.setText("");
            vaccinoSomministrato.setSelectedItem("");
            data.setDate(new Date());
            nome.setEditable(true);
            cognome.setEditable(true);
            codiceFiscale.setEditable(true);
            data.setEnabled(true);
            vaccinoSomministrato.setEnabled(true);
            nomeCV.setEnabled(true);
            registraVaccinato.setEnabled(true);
            IDUnivoco.setVisible(false);

        }

    }




}