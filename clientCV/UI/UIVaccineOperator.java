package clientCV.UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * La classe UIVaccineOperator crea l'interfaccia dove l'operatore vaccinale sceglie se inserire
 * un nuovo centro vaccinale oppure se inserire un nuovo cittadino vaccinato
 *
 * @author Paolo Bruscagin
 */

public class UIVaccineOperator extends JFrame implements ActionListener {


    /**
     * Bottone per accedere alla sezione registra centro vaccinale
     */
    JButton registraCentroVaccinale ;

    /**
     * Bottone per accedere alla sezione registra vaccinato
     */
    JButton registraVaccinato;

    /**
     * Bottone per tornare nell'interfaccia grafica UIChoosingRooles
     */
    JButton backtoChoosingRooles;

    public UIVaccineOperator(){



        Border bordo = new LineBorder(new Color(0xFF37C47A, true), 4, true);
        Border bordobtnInd = new LineBorder(new Color(0xFFF68E3B, true), 4, true);


        //Personalizzazione bottone registra centro vaccinale

        ImageIcon cv = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/centrivaccinali.png")));

        registraCentroVaccinale =  new JButton("REGISTRA CENTRO VACCINALE", cv);
        registraCentroVaccinale.setBounds(1000, 270, 500, 120);
        registraCentroVaccinale.setFont(new Font("Georgia", Font.BOLD, 20));
        registraCentroVaccinale.setBackground(new Color(0xA059E3B3));
        registraCentroVaccinale.setForeground(Color.WHITE);
        registraCentroVaccinale.setBorder(bordo);
        registraCentroVaccinale.setFocusable(false);
        registraCentroVaccinale.addActionListener(this);
        registraCentroVaccinale.setOpaque(true);


        //Personalizzazione bottone rigistra vaccinato

        ImageIcon cr = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/registravaccinato.png")));


        registraVaccinato =  new JButton("REGISTRA VACCINATO", cr);
        registraVaccinato.setBounds(1000, 470, 500, 120);
        registraVaccinato.setFont(new Font("Georgia", Font.BOLD, 20));
        registraVaccinato.setBackground(new Color(0xA059E3B3));
        registraVaccinato.setForeground(Color.WHITE);
        registraVaccinato.setBorder(bordo);
        registraVaccinato.setFocusable(false);
        registraVaccinato.addActionListener(this);
        registraVaccinato.setOpaque(true);


        //Personalizzazione bottone indietro

        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/iconaindietro.png")));


        backtoChoosingRooles =  new JButton("     INDIETRO", ind);
        backtoChoosingRooles.setBounds(1075, 670, 350, 120);
        backtoChoosingRooles.setFont(new Font("Georgia", Font.BOLD, 20));
        backtoChoosingRooles.setBackground(new Color(0xFA4723));
        backtoChoosingRooles.setForeground(Color.WHITE);
        backtoChoosingRooles.setBorder(bordobtnInd);
        backtoChoosingRooles.setFocusable(false);
        backtoChoosingRooles.addActionListener(this);
        backtoChoosingRooles.setOpaque(true);

        JLabel sfondo = new JLabel();
        sfondo.setBounds(0, 0, 1600, 900);
        sfondo.add(registraCentroVaccinale);
        sfondo.add(registraVaccinato);
        sfondo.add(backtoChoosingRooles);

        JPanel vo = new JPanel();
        vo.setBounds(0, 0, 1600, 900);
        vo.setLayout(null);
        vo.add(sfondo);

        //Icona avvio del programma

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        this.add(sfondo);
        this.setTitle("Operatore Vaccinale");
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
        if(e.getSource() == backtoChoosingRooles){
            this.dispose();
            new UIChoosingRooles();
        } else if(e.getSource() == registraCentroVaccinale){
            this.dispose();
            new UIRegisterVaxCenter();
        } else if(e.getSource() == registraVaccinato){
            this.dispose();
            //new UIRegisterVaccinated();
        }

    }
}
