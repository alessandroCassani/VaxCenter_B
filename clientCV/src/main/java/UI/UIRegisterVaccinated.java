package UI;

import UI.graphics.RoundJTextField;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 *  La classe UiRegisterVaccinated crea l'interfaccia grafica dove e' possibile inserire le informazioni necessarie alla registrazione a sistema di un vaccinato
 *
 * @author Paolo Bruscagin
 * @author alessandor Cassani
 * @author Damiano Ficara
 */

public class UIRegisterVaccinated extends JFrame implements ActionListener {


    /**
     * Menu a tendina che indica un insieme di centri vaccinali registrati a sistema che l'utente puo' selezionare a seguito di una ricerca
     */
    JComboBox nomeCV = new JComboBox<>(new String[]{"Paolo", "Damiano", "Alessandro", "Luca","Paolo", "Damiano", "Alessandro", "Luca","Paolo", "Damiano", "Alessandro", "Luca","Paolo", "Damiano", "Alessandro", "Luca"}); // da fare in modo diverso

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
    JComboBox vaccinoSomministrato = new JComboBox<>(new String[]{"Pfizer", "AstraZeneca", "Moderna", "J&J"});

    /**
     * ID univoco generato al momento della vaccinazione
     */
    JLabel IDUnivoco = new JLabel();

    /**
     *label rappresentante un messaggio di avviso relativo all'ID univoco
     */
    JLabel warningIDUnivoco = new JLabel();

    /**
     * status dell'operazione
     */
    JLabel status = new JLabel();

    /**
     * bottone per l'avvio del processo di registrazione del vaccinato
     */
    JButton registraVaccinato = new JButton("REGISTRA");

    /**
     * bottone per tornare alla precedente pagina di UI (UIVaccineOperator)
     */
    JButton backToVaccineOperator;

    /**
     * bottone per eliminare le stringhe inserite in fase di registrazione
     */
    JButton pulisci = new JButton();

    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica UIRegisterVaccinated
     *
     * @author Paolo Bruscagin
     * @author Alessandro Cassani
     */

    public UIRegisterVaccinated(){

        Border bordobtn = new LineBorder(new Color(0,49,83), 4, true);
        Border bordobtnInd = new LineBorder(new Color(169,50, 38), 2, true);

        JLabel tiotoloVaccinato = new JLabel("Inserisci un nuovo Vaccinato");
        tiotoloVaccinato.setFont(new Font("Georgia", Font.BOLD, 20));
        add(tiotoloVaccinato).setBounds(350, 0, 550, 55);


        JLabel labelnomeVac = new JLabel("Nome:");
        labelnomeVac.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelnomeVac).setBounds(280, 35, 550, 55);

        nome.setFont(new Font("Arial", Font.ITALIC, 20));
        nome.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        nome.setPreferredSize(new Dimension(325, 55));
        nome.setBounds(140, 80, 325, 50);
        nome.setEchoChar((char) 0);

        JLabel labelcognomeVac = new JLabel("Cognome:");
        labelcognomeVac.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelcognomeVac).setBounds(650, 35, 550, 55);

        cognome.setFont(new Font("Arial", Font.ITALIC, 20));
        cognome.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        cognome.setPreferredSize(new Dimension(325, 55));
        cognome.setBounds(520, 80, 325, 50);
        cognome.setEchoChar((char) 0);

        JLabel labelcf = new JLabel("Codice Fiscale:");
        labelcf.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelcf).setBounds(250, 150, 550, 55);

        codiceFiscale.setFont(new Font("Arial", Font.ITALIC, 20));
        codiceFiscale.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        codiceFiscale.setPreferredSize(new Dimension(325, 55));
        codiceFiscale.setBounds(140, 195, 325, 50);
        codiceFiscale.setEchoChar((char) 0);

        JLabel labeldata = new JLabel("Data somministrazione (aaaa-mm-gg):");
        labeldata.setFont(new Font("Georgia",Font.ITALIC, 17));
        add(labeldata).setBounds(530, 150, 550, 55);

        data.setFont(new Font("Arial", Font.ITALIC, 20));
        data.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        data.setPreferredSize(new Dimension(325, 55));
        data.setBounds(520, 195, 325, 50);

        JLabel labelTipVac = new JLabel("Tipologia:");
        labelTipVac.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelTipVac).setBounds(260, 265, 550, 55);

        vaccinoSomministrato.setFont(new Font("Arial", Font.ITALIC, 20));
        vaccinoSomministrato.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        vaccinoSomministrato.setBounds(140, 310, 325, 50);
        vaccinoSomministrato.setBackground(Color.WHITE);
        ((JLabel)vaccinoSomministrato.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        registraVaccinato.setBounds(380, 450, 230, 60);
        registraVaccinato.setFont(new Font("Georgia", Font.BOLD, 20));
        registraVaccinato.setBackground(new Color(0,0,128));
        registraVaccinato.setForeground(Color.WHITE);
        registraVaccinato.setBorder(bordobtn);
        registraVaccinato.setFocusable(false);
        registraVaccinato.addActionListener(this);
        registraVaccinato.setOpaque(true);

        JLabel labelNome = new JLabel("Nome Centro Vaccinale:");
        labelNome.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelNome).setBounds(590, 265, 550, 55);

        nomeCV.setFont(new Font("Arial", Font.ITALIC, 20));
        nomeCV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        nomeCV.setBounds(520, 310, 325, 50);
        nomeCV.setBackground(Color.WHITE);
        AutoCompleteDecorator.decorate(nomeCV);
        ((JLabel)nomeCV.getRenderer()).setHorizontalAlignment(JLabel.CENTER);

        backToVaccineOperator =  new JButton("INDIETRO");
        backToVaccineOperator.setBounds(90, 450, 230, 60);
        backToVaccineOperator.setFont(new Font("Georgia", Font.BOLD, 20));
        backToVaccineOperator.setBackground(new Color(248, 9, 55));
        backToVaccineOperator.setForeground(Color.WHITE);
        backToVaccineOperator.setBorder(bordobtnInd);
        backToVaccineOperator.setFocusable(false);
        backToVaccineOperator.addActionListener(this);
        backToVaccineOperator.setOpaque(true);

        pulisci = new JButton("PULISCI");
        pulisci.setBounds(670, 450, 230, 60);
        pulisci.setFont(new Font("Georgia", Font.BOLD, 20));
        pulisci.setBackground(new Color(0xEF0808));
        pulisci.setForeground(Color.WHITE);
        pulisci.setBorder(bordobtnInd);
        pulisci.setFocusable(false);
        pulisci.addActionListener(this);
        pulisci.setOpaque(true);

        status.setFont(new Font("Georgia", Font.BOLD, 18));
        status.setBounds(350, 370, 400, 55);

        IDUnivoco.setFont(new Font("Georgia", Font.BOLD, 18));
        IDUnivoco.setBounds(125, 540, 400, 50);
        warningIDUnivoco.setFont(new Font("Georgia", Font.BOLD, 18));
        warningIDUnivoco.setBounds(125, 565, 600, 55);

        setLayout(null);
        add(backToVaccineOperator);
        add(nomeCV);
        add(nome);
        add(cognome);
        add(codiceFiscale);
        add(vaccinoSomministrato);
        add(registraVaccinato);
        add(pulisci);
        add(status);
        add(IDUnivoco);
        add(warningIDUnivoco);
        add(data);

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
     * metodo che permette di gestire gli eventi associati ai listener dei componenti di UI attivati dall'utente
     * @param e the event to be processed
     *
     * @author Paolo Bruscagin
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToVaccineOperator) {
            this.dispose();
            new UIVaccineOperator();
        }else  if (e.getSource() == registraVaccinato) {
            IDUnivoco.setText("");
            warningIDUnivoco.setText("");
            if (!nome.getText().equals("Paolo")){
                status.setForeground(new Color(0xEC0909));
                status.setText("I dati inseriti non sono corretti!");
            } else {
                status.setForeground(new Color(0x077507));
                status.setText("Centro Vaccinale registrato con successo!");
                IDUnivoco.setText("ID Univoco: 100000001");
                warningIDUnivoco.setText("ATTENZIONE! Memorizzare immediatamente l'id univoco!");
            }
        }else if(e.getSource() == pulisci) {
                nomeCV.setSelectedItem("Paolo");
                nome.setText("");
                cognome.setText("");
                codiceFiscale.setText("");
                vaccinoSomministrato.setSelectedItem("Pfizer");
                status.setText("");
                IDUnivoco.setText("");
                warningIDUnivoco.setText("");
        }
    }
}

