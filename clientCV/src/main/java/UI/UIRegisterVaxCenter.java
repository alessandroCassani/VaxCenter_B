package UI;

import UI.graphics.RoundJTextField;

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
 * @author Alessandro Cassani
 */

public class UIRegisterVaxCenter extends JFrame implements ActionListener {


    /**
     * Area di testo per scrivere il nome del Centro vaccinale
     */
    RoundJTextField nomeCentroVaccinale = new RoundJTextField(50);

    /**
     * Menù a tendina per scegliere il qualificatore del Centro Vaccinale
     */

    JComboBox qualificatore =new JComboBox<>(new String[]{"Via", "Viale", "Piazza"});

    /**
     * Area di testo per scrivere il nome della via/viale/piazza del Centro vaccinale
     */

    RoundJTextField nomeVia = new RoundJTextField(30);

    /**
     * Area di testo per scrivere il numero civico del Centro vaccinale
     */

    RoundJTextField numeroCivico = new RoundJTextField(10);

    /**
     * Area di testo per scrivere il comune del Centro vaccinale
     */

    RoundJTextField comune = new RoundJTextField(30);

    /**
     * Menù a tendina per scegliere la sigla della provincia del Centro Vaccinale
     */

    RoundJTextField siglaProvincia = new RoundJTextField(2);
    /**
     * Area di testo per scrivere il CAP del Centro Vaccinale
     */

    RoundJTextField cap = new RoundJTextField(5);

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
     * campo di testo in cui viene visuaizzato lo stato della registra centro vaccinale
      */

    JLabel status = new JLabel();


    /**
     * Bottone per pulire tutte le JTextField e le JComboBox
     */
    JButton pulisci;


    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica
     */
    public UIRegisterVaxCenter(){

        Border bordobtn = new LineBorder(new Color(0,49,83), 4, true);
        Border bordobtnInd = new LineBorder(new Color(169,50, 38), 2, true);

        JLabel tiotoloCV = new JLabel("Inserisci un nuovo Centro Vaccinale");
        tiotoloCV.setFont(new Font("Georgia", Font.BOLD, 20));
        add(tiotoloCV).setBounds(340, 10, 550, 55);

        JLabel labelNome = new JLabel("Nome Centro Vaccinale:");
        labelNome.setFont(new Font("Georgia",Font.ITALIC, 17));
        add(labelNome).setBounds(150, 55, 550, 75);

        nomeCentroVaccinale.setFont(new Font("Arial", Font.ITALIC, 20));
        nomeCentroVaccinale.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        nomeCentroVaccinale.setPreferredSize(new Dimension(550, 55));
        nomeCentroVaccinale.setBounds(130, 110, 225, 50);
        nomeCentroVaccinale.setHorizontalAlignment(JTextField.CENTER);
        nomeCentroVaccinale.setEchoChar((char) 0);

        JLabel labelTip = new JLabel("Tipologia:");
        labelTip.setFont(new Font("Georgia",Font.ITALIC, 17));
        add(labelTip).setBounds(450, 55, 550, 75);

        tipologia.setFont(new Font("Arial", Font.ITALIC, 20));
        tipologia.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        tipologia.setBounds(420, 110, 145, 50);
        tipologia.setBackground(Color.WHITE);

        JLabel labelComune = new JLabel("Comune:");
        labelComune.setFont(new Font("Georgia",Font.ITALIC, 17));
        add(labelComune).setBounds(700, 55, 550, 75);

        comune.setFont(new Font("Arial", Font.ITALIC, 20));
        comune.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        comune.setPreferredSize(new Dimension(325, 75));
        comune.setBounds(630, 110, 225, 50);
        comune.setHorizontalAlignment(JTextField.CENTER);
        comune.setEchoChar((char) 0);


        qualificatore.setFont(new Font("Arial", Font.ITALIC, 20));
        qualificatore.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        qualificatore.setBounds(195, 220, 75, 50);
        qualificatore.setBackground(Color.WHITE);

        JLabel labelIndirizzo = new JLabel("Nome Via/Viale/Piazza:");
        labelIndirizzo.setFont(new Font("Georgia",Font.ITALIC, 17));
        add(labelIndirizzo).setBounds(390, 165, 550, 75);

        nomeVia.setFont(new Font("Arial", Font.ITALIC, 20));
        nomeVia.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        nomeVia.setPreferredSize(new Dimension(325, 75));
        nomeVia.setBounds(375, 220, 225, 50);
        nomeVia.setHorizontalAlignment(JTextField.CENTER);
        nomeVia.setEchoChar((char) 0);

        JLabel labelNumeroCivico = new JLabel("N°");
        labelNumeroCivico.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelNumeroCivico).setBounds(735, 165, 550, 75);

        numeroCivico.setFont(new Font("Arial", Font.ITALIC, 20));
        numeroCivico.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        numeroCivico.setPreferredSize(new Dimension(75, 75));
        numeroCivico.setBounds(705, 220, 75, 50);
        numeroCivico.setHorizontalAlignment(JTextField.CENTER);
        numeroCivico.setEchoChar((char) 0);

        JLabel labelSigla = new JLabel("Provincia:");
        labelSigla.setFont(new Font("Georgia",Font.ITALIC, 17));
        add(labelSigla).setBounds(195, 270, 550, 75);

        siglaProvincia.setFont(new Font("Arial", Font.ITALIC, 20));
        siglaProvincia.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        siglaProvincia.setPreferredSize(new Dimension(100, 75));
        siglaProvincia.setBounds(195, 325, 75, 50);
        siglaProvincia.setHorizontalAlignment(JTextField.CENTER);
        siglaProvincia.setEchoChar((char) 0);

        JLabel labelCAP = new JLabel("CAP:");
        labelCAP.setFont(new Font("Georgia",Font.ITALIC, 17));
        add(labelCAP).setBounds(475, 270, 550, 75);

        cap.setFont(new Font("Arial", Font.ITALIC, 20));
        cap.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        cap.setPreferredSize(new Dimension(100, 75));
        cap.setBounds(455, 325, 75, 50);
        cap.setHorizontalAlignment(JTextField.CENTER);
        cap.setEchoChar((char) 0);

        pulisci = new JButton("PULISCI");
        pulisci.setBounds(695, 325, 130, 50);
        pulisci.setFont(new Font("Georgia", Font.BOLD, 15));
        pulisci.setBackground(new Color(0xEF0808));
        pulisci.setForeground(Color.WHITE);
        pulisci.setBorder(bordobtnInd);
        pulisci.setFocusable(false);
        pulisci.addActionListener(this);
        pulisci.setOpaque(true);

        backToUIVaccineOperator =  new JButton("INDIETRO");
        backToUIVaccineOperator.setBounds(160, 440, 220, 55);
        backToUIVaccineOperator.setFont(new Font("Georgia", Font.BOLD, 20));
        backToUIVaccineOperator.setBackground(new Color(248, 9, 55));
        backToUIVaccineOperator.setForeground(Color.WHITE);
        backToUIVaccineOperator.setBorder(bordobtnInd);
        backToUIVaccineOperator.setFocusable(false);
        backToUIVaccineOperator.addActionListener(this);
        backToUIVaccineOperator.setOpaque(true);

        registra.setBounds(620, 440, 220, 55);
        registra.setFont(new Font("Georgia", Font.BOLD, 20));
        registra.setBackground(new Color(0,0,128));
        registra.setForeground(Color.WHITE);
        registra.setBorder(bordobtn);
        registra.setFocusable(false);
        registra.addActionListener(this);
        registra.setOpaque(true);

        status.setFont(new Font("Georgia", Font.BOLD, 18));
        status.setBounds(125, 500, 400, 75);

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

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
        add(status);
        add(pulisci);

        setTitle("Registra Centro Vaccinale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setSize (1000,600);
        getContentPane().setBackground(new Color(181, 226, 232));
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
            //this.dispose();
            if (!nomeCentroVaccinale.getText().equals("")){
                status.setForeground(new Color(0xEC0909));
                status.setText("I dati inseriti non sono corretti!");
            } else {
                status.setForeground(new Color(0x077507));
                status.setText("Centro Vaccinale registrato con successo!");
            }
        }else if(e.getSource() == pulisci){
            nomeCentroVaccinale.setText("");
            qualificatore.setSelectedItem("Via");
            nomeVia.setText("");
            numeroCivico.setText("");
            comune.setText("");
            siglaProvincia.setText("");
            cap.setText("");
            tipologia.setSelectedItem("HUB");
            status.setText("");
        }
    }
}
