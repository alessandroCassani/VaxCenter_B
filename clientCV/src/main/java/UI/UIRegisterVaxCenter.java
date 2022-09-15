package UI;

// da completare graficamnete

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
    JTextField nomeCentroVaccinale = new JTextField("Nome Centro Vaccinale");

    /**
     * Menù a tendina per scegliere il qualificatore del Centro Vaccinale
     */

    JComboBox qualificatore = new JComboBox();

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

    JComboBox siglaProvincia = new JComboBox();

    /**
     * Area di testo per scrivere il CAP del Centro Vaccinale
     */

    JTextField cap = new JTextField();

    /**
     * Menù a tendina per scegliere la tipologia del Centro Vaccinale (tipologia (ospedaliero, aziendale, hub)
     */

    JComboBox tipologia = new JComboBox();

    /**
     * Bottone per la verifica dei dati scritti
     */

    JButton verificaDati = new JButton();

    /**
     * Bottone per tornare nell'interfaccia UIVaccineOperator
     */

    JButton backToUIVaccineOperator = new JButton();

    public UIRegisterVaxCenter(){

        Border bordo = new LineBorder(new Color(0xFF000000, true), 2, true);

        nomeCentroVaccinale.setText ("Inserisci il nome del Centro Vaccinale ...");
        nomeCentroVaccinale.setBounds(1055, 270, 400, 120);
        nomeCentroVaccinale.setFocusable(false);
        nomeCentroVaccinale.setBorder(bordo);


        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());


        JPanel cv = new JPanel();
        cv.setBounds(0, 0, 1600, 900);
        cv.setLayout(null);
        cv.add(nomeCentroVaccinale);




        this.setTitle("VaxCenter");
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

    }

}
