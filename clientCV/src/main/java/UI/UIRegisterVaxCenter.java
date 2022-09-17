package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 *  La classe UIRegisterVaxCenter crea l'interfaccia dove l'operatore vaccinale è in gardo di inserire i dati di un nuovo centro vaccinale
 *
 * @author Paolo Bruscagin
 */

public class UIRegisterVaxCenter extends JFrame implements ActionListener {


    /**
     * Area di testo per scrivere il nome del Centro vaccinale
     */
    JTextField nomeCentroVaccinale = new JTextField(50);

    /**
     * Menù a tendina per scegliere il qualificatore del Centro Vaccinale
     */

    JComboBox qualificatore =new JComboBox<>(new String[]{"Via", "Viale", "Piazza"});

    /**
     * Area di testo per scrivere il nome della via/viale/piazza del Centro vaccinale
     */

    JTextField nomeVia = new JTextField(30);

    /**
     * Area di testo per scrivere il numero civico del Centro vaccinale
     */

    JTextField numeroCivico = new JTextField(10);

    /**
     * Area di testo per scrivere il comune del Centro vaccinale
     */

    JTextField comune = new JTextField(30);

    /**
     * Menù a tendina per scegliere la sigla della provincia del Centro Vaccinale
     */

    JTextField siglaProvincia = new JTextField(2);
    /**
     * Area di testo per scrivere il CAP del Centro Vaccinale
     */

    JTextField cap = new JTextField(5);

    /**
     * Menù a tendina per scegliere la tipologia del Centro Vaccinale (tipologia (ospedaliero, aziendale, hub)
     */

    JComboBox<String> tipologia = new JComboBox(new String[]{"HUB", "OSPEDALIERO", "AZIENDALE"});

    /**
     * Bottone per la verifica dei dati scritti
     */

    JButton registra = new JButton("REGISTRA");

    /**
     * Bottone per tornare nell'interfaccia UIVaccineOperator
     */

    JButton backToUIVaccineOperator;


    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica
     */
    public UIRegisterVaxCenter(){

        Border bordo = new LineBorder(new Color(0xFF000000, true), 2, true);
        Border bordobtn = new LineBorder(new Color(0xFF37C47A, true), 4, true);
        Border bordobtnInd = new LineBorder(new Color(0xFFF68E3B, true), 4, true);

        JLabel tiotoloCV = new JLabel("Inserisci un nuovo Centro Vaccinale:");
        tiotoloCV.setFont(new Font("Georgia", Font.BOLD, 20));
        add(tiotoloCV).setBounds(50, 10, 550, 75);

        JLabel labelNome = new JLabel("Nome Centro Vaccinale");
        labelNome.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelNome).setBounds(50, 55, 550, 75);

        nomeCentroVaccinale.setFont(new Font("Arial", Font.BOLD, 20));
        nomeCentroVaccinale.setPreferredSize(new Dimension(550, 75));
        nomeCentroVaccinale.setBounds(50, 100, 550, 75);

        JLabel labelIndirizzo = new JLabel("Nome Via/Viale/Piazza (Completo)");
        labelIndirizzo.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelIndirizzo).setBounds(180, 155, 550, 75);

        qualificatore.setFont(new Font("Arial", Font.BOLD, 20));
        qualificatore.setBounds(50, 200, 100, 75);


        nomeVia.setFont(new Font("Arial", Font.BOLD, 20));
        nomeVia.setPreferredSize(new Dimension(325, 75));
        nomeVia.setBounds(175, 200, 325, 75);

        JLabel labelNumeroCivico = new JLabel("N°");
        labelNumeroCivico.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelNumeroCivico).setBounds(530, 155, 550, 75);

        numeroCivico.setFont(new Font("Arial", Font.BOLD, 20));
        numeroCivico.setPreferredSize(new Dimension(75, 75));
        numeroCivico.setBounds(525, 200, 75, 75);

        JLabel labelComune = new JLabel("Comune");
        labelComune.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelComune).setBounds(50, 255, 550, 75);

        comune.setFont(new Font("Arial", Font.BOLD, 20));
        comune.setPreferredSize(new Dimension(325, 75));
        comune.setBounds(50, 300, 325, 75);

        JLabel labelSigla = new JLabel("Provincia");
        labelSigla.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelSigla).setBounds(402, 255, 550, 75);

        siglaProvincia.setFont(new Font("Arial", Font.BOLD, 20));
        siglaProvincia.setPreferredSize(new Dimension(100, 75));
        siglaProvincia.setBounds(400, 300, 75, 75);

        JLabel labelCAP = new JLabel("CAP");
        labelCAP.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelCAP).setBounds(505, 255, 550, 75);

        cap.setFont(new Font("Arial", Font.BOLD, 20));
        cap.setPreferredSize(new Dimension(100, 75));
        cap.setBounds(500, 300, 100, 75);

        tipologia.setFont(new Font("Arial", Font.BOLD, 20));
        tipologia.setBounds(50, 400, 275, 75);

        JLabel labelTip = new JLabel("Tipologia");
        labelTip.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelTip).setBounds(50, 355, 550, 75);

        registra.setBounds(350, 400, 250, 75);
        registra.setFont(new Font("Georgia", Font.BOLD, 20));
        registra.setBackground(new Color(0x07AF45));
        registra.setForeground(Color.WHITE);
        registra.setBorder(bordobtn);
        registra.setFocusable(false);
        registra.addActionListener(this);
        registra.setOpaque(true);


        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/iconaindietro.png")));
        backToUIVaccineOperator =  new JButton("     INDIETRO", ind);
        backToUIVaccineOperator.setBounds(50, 700, 300, 100);
        backToUIVaccineOperator.setFont(new Font("Georgia", Font.BOLD, 20));
        backToUIVaccineOperator.setBackground(new Color(0xFA4723));
        backToUIVaccineOperator.setForeground(Color.WHITE);
        backToUIVaccineOperator.setBorder(bordobtnInd);
        backToUIVaccineOperator.setFocusable(false);
        backToUIVaccineOperator.addActionListener(this);
        backToUIVaccineOperator.setOpaque(true);



        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());


        //JPanel cv = new JPanel();
        setBounds(0, 0, 1600, 900);
        setLayout(null);

        add(nomeCentroVaccinale);
        add(qualificatore);
        add(nomeVia);
        add(numeroCivico);
        add(comune);
        add(siglaProvincia);
        add(cap);
        add(tipologia);
        add(registra);
        add(backToUIVaccineOperator);




        setTitle("Registra Centro Vaccinale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600,900);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    /**
     * metodo che permette la gestione degli eventi associati ai listener legati ai componenti d'interfaccia grafica
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backToUIVaccineOperator){
            this.dispose();
            new UIVaccineOperator();
        } else if(e.getSource() == registra){
            this.dispose();



        }

    }

}
