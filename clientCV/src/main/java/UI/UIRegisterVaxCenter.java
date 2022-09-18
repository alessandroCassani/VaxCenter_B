package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

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

    JTextField nomeVia = new JTextField();

    /**
     * Area di testo per scrivere il numero civico del Centro vaccinale
     */

    JTextField numeroCivico = new JTextField();

    /**
     * Area di testo per scrivere il comune del Centro vaccinale
     */

    JTextField comune = new JTextField();

    /**
     * Menù a tendina per scegliere la sigla della provincia del Centro Vaccinale
     */

    JTextField siglaProvincia = new JTextField();
    /**
     * Area di testo per scrivere il CAP del Centro Vaccinale
     */

    JTextField cap = new JTextField();

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



    public UIRegisterVaxCenter(){

        Border bordo = new LineBorder(new Color(0xFF000000, true), 2, true);
        Border bordobtn = new LineBorder(new Color(0xFF37C47A, true), 4, true);
        Border bordobtnInd = new LineBorder(new Color(0xFFF68E3B, true), 4, true);



        nomeCentroVaccinale.setFont(new Font("Arial", Font.BOLD, 14));
        nomeCentroVaccinale.setText(" Nome Centro Vaccinale");

        nomeCentroVaccinale.setBounds(50, 100, 525, 75);
        nomeCentroVaccinale.setFocusable(false);
        nomeCentroVaccinale.setBorder(bordo);

        qualificatore.setFont(new Font("Arial", Font.BOLD, 14));
        qualificatore.setBounds(50, 200, 100, 75);
        qualificatore.setFocusable(false);
        qualificatore.setBorder(bordo);

        nomeVia.setFont(new Font("Arial", Font.BOLD, 14));
        nomeVia.setText(" Nome Via");
        nomeVia.setBounds(175, 200, 300, 75);
        nomeVia.setFocusable(false);
        nomeVia.setBorder(bordo);

        numeroCivico.setFont(new Font("Arial", Font.BOLD, 14));
        numeroCivico.setText(" N°");
        numeroCivico.setBounds(500, 200, 75, 75);
        numeroCivico.setFocusable(false);
        numeroCivico.setBorder(bordo);

        comune.setFont(new Font("Arial", Font.BOLD, 14));
        comune.setText(" Comune");
        comune.setBounds(50, 300, 350, 75);
        comune.setFocusable(false);
        comune.setBorder(bordo);

        cap.setFont(new Font("Arial", Font.BOLD, 14));
        cap.setText(" CAP");
        cap.setBounds(425, 300, 150, 75);
        cap.setFocusable(false);
        cap.setBorder(bordo);

        tipologia.setFont(new Font("Arial", Font.BOLD, 14));
        tipologia.setBounds(50, 400, 200, 75);
        tipologia.setFocusable(false);
        tipologia.setBorder(bordo);


        registra.setBounds(325, 400, 250, 75);
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


        JPanel cv = new JPanel();
        cv.setBounds(0, 0, 1600, 900);
        cv.setLayout(null);

        this.add(nomeCentroVaccinale);
        this.add(qualificatore);
        this.add(nomeVia);
        this.add(numeroCivico);
        this.add(comune);
        this.add(cap);
        this.add(tipologia);
        this.add(registra);
        this.add(backToUIVaccineOperator);



        this.setTitle("Registra Centro Vaccinale");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600,900);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setForeground(Color.WHITE);
    }


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
