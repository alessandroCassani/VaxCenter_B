package UI;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 * Libreria utilizzata per cercare tramite caratteri nella JComboBox i nomi dei centri vaccinali
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.time.LocalDate;





/**
 *  La classe UIRegisterVaccinated crea l'interfaccia dove l'operatore vaccinale è in gardo di inserire i dati di un vaccinato
 *
 * @author Paolo Bruscagin
 * @author Damiano Ficara
 */

public class UIRegisterVaccinated extends JFrame implements ActionListener {



    JComboBox nomeCV = new JComboBox<>(new String[]{"Paolo", "Damiano", "Alessandro", "Luca","Paolo", "Damiano", "Alessandro", "Luca","Paolo", "Damiano", "Alessandro", "Luca","Paolo", "Damiano", "Alessandro", "Luca"}); // da fare in modo diverso

    JTextField nome = new JTextField(30);

    JTextField cognome = new JTextField(30);

    JTextField codiceFiscale = new JTextField(16);

    LocalDate todaysDate = LocalDate.now();

    JTextField data = new JTextField(String.valueOf(todaysDate));

    JComboBox vaccinoSomministrato = new JComboBox<>(new String[]{"Pfizer", "AstraZeneca", "Moderna", "J&J"});

    JLabel IDUnivoco = new JLabel();
    JLabel warningIDUnivoco = new JLabel();
    JLabel status = new JLabel();

    JButton registraVaccinato = new JButton("REGISTRA");

    JButton backToVaccineOperator;

    JButton pulisci = new JButton();

    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica
     */

    public UIRegisterVaccinated(){

        Border bordo = new LineBorder(new Color(0xFF000000, true), 2, true);
        Border bordobtn = new LineBorder(new Color(0xFF37C47A, true), 4, true);
        Border bordobtnInd = new LineBorder(new Color(0xFFF68E3B, true), 4, true);

        JLabel tiotoloVaccinato = new JLabel("Inserisci un nuovo Vaccinato:");
        tiotoloVaccinato.setFont(new Font("Georgia", Font.BOLD, 20));
        add(tiotoloVaccinato).setBounds(50, 10, 550, 75);

        JLabel labelNome = new JLabel("Nome Centro Vaccinale");
        labelNome.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelNome).setBounds(50, 55, 550, 75);

        nomeCV.setFont(new Font("Arial", Font.BOLD, 20));
        nomeCV.setBorder(bordo);
        nomeCV.setBounds(50, 100, 675, 75);
        nomeCV.setBackground(Color.WHITE);
        AutoCompleteDecorator.decorate(nomeCV);


        JLabel labelnomeVac = new JLabel("Nome");
        labelnomeVac.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelnomeVac).setBounds(50, 155, 550, 75);

        nome.setFont(new Font("Arial", Font.BOLD, 20));
        nome.setBorder(bordo);
        nome.setPreferredSize(new Dimension(325, 75));
        nome.setBounds(50, 200, 325, 75);

        JLabel labelcognomeVac = new JLabel("Cognome");
        labelcognomeVac.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelcognomeVac).setBounds(400, 155, 550, 75);

        cognome.setFont(new Font("Arial", Font.BOLD, 20));
        cognome.setBorder(bordo);
        cognome.setPreferredSize(new Dimension(325, 75));
        cognome.setBounds(400, 200, 325, 75);

        JLabel labelcf = new JLabel("Codice Fiscale");
        labelcf.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelcf).setBounds(50, 255, 550, 75);

        codiceFiscale.setFont(new Font("Arial", Font.BOLD, 20));
        codiceFiscale.setBorder(bordo);
        codiceFiscale.setPreferredSize(new Dimension(325, 75));
        codiceFiscale.setBounds(50, 300, 325, 75);

        JLabel labeldata = new JLabel("Data somministrazione (aaaa-mm-gg)");
        labeldata.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labeldata).setBounds(400, 255, 550, 75);

        data.setFont(new Font("Arial", Font.BOLD, 20));
        data.setBorder(bordo);
        data.setPreferredSize(new Dimension(325, 75));
        data.setBounds(400, 300, 325, 75);

        JLabel labelTipVac = new JLabel("Tipologia");
        labelTipVac.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelTipVac).setBounds(50, 355, 550, 75);

        vaccinoSomministrato.setFont(new Font("Arial", Font.BOLD, 20));
        vaccinoSomministrato.setBorder(bordo);
        vaccinoSomministrato.setBounds(50, 400, 325, 75);
        vaccinoSomministrato.setBackground(Color.WHITE);

        registraVaccinato.setBounds(475, 400, 250, 75);
        registraVaccinato.setFont(new Font("Georgia", Font.BOLD, 20));
        registraVaccinato.setBackground(new Color(0x07AF45));
        registraVaccinato.setForeground(Color.WHITE);
        registraVaccinato.setBorder(bordobtn);
        registraVaccinato.setFocusable(false);
        registraVaccinato.addActionListener(this);
        registraVaccinato.setOpaque(true);



        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/iconaindietro.png")));
        backToVaccineOperator =  new JButton("     INDIETRO", ind);
        backToVaccineOperator.setBounds(50, 700, 300, 100);
        backToVaccineOperator.setFont(new Font("Georgia", Font.BOLD, 20));
        backToVaccineOperator.setBackground(new Color(0xFA4723));
        backToVaccineOperator.setForeground(Color.WHITE);
        backToVaccineOperator.setBorder(bordobtnInd);
        backToVaccineOperator.setFocusable(false);
        backToVaccineOperator.addActionListener(this);
        backToVaccineOperator.setOpaque(true);

        pulisci = new JButton("PULISCI");
        pulisci.setBounds(400, 700, 200, 100);
        pulisci.setFont(new Font("Georgia", Font.BOLD, 15));
        pulisci.setBackground(new Color(0xEF0808));
        pulisci.setForeground(Color.WHITE);
        pulisci.setBorder(bordobtnInd);
        pulisci.setFocusable(false);
        pulisci.addActionListener(this);
        pulisci.setOpaque(true);

        status.setFont(new Font("Georgia", Font.BOLD, 18));
        status.setBounds(125, 500, 400, 75);

        IDUnivoco.setFont(new Font("Georgia", Font.BOLD, 18));
        IDUnivoco.setBounds(125, 550, 400, 75);
        warningIDUnivoco.setFont(new Font("Georgia", Font.BOLD, 18));
        warningIDUnivoco.setBounds(125, 575, 600, 75);

        setBounds(0, 0, 1600, 900);
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
                data.setText(String.valueOf(todaysDate));
                vaccinoSomministrato.setSelectedItem("Pfizer");
                status.setText("");
                IDUnivoco.setText("");
                warningIDUnivoco.setText("");

            }
        }

    }

