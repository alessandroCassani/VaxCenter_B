package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 *  La classe UIRegisterVaccinated crea l'interfaccia dove l'operatore vaccinale è in gardo di inserire i dati di un vaccinato
 *
 * @author Paolo Bruscagin
 */

public class UIRegisterVaccinated extends JFrame implements ActionListener {

    JComboBox nomeCV = new JComboBox<>(new String[]{}); // da fare in modo diverso

    JTextField nome = new JTextField(30);

    JTextField cognome = new JTextField(30);

    JTextField codiceFiscale = new JTextField(16);

    //la data

    JComboBox vaccinoSomministrato = new JComboBox<>(new String[]{"Pfizer", "AstraZeneca", "Moderna", "J&J"});

    JTextArea IDUnivoco = new JTextArea();

    JButton registraVaccinato = new JButton();

    JButton backToVaccineOperator = new JButton();


    public UIRegisterVaccinated(){

        Border bordobtnInd = new LineBorder(new Color(0xFFF68E3B, true), 4, true);


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

        setBounds(0, 0, 1600, 900);
        setLayout(null);

        add(backToVaccineOperator);



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
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToVaccineOperator) {
            this.dispose();
            new UIVaccineOperator();
        }

    }
}
