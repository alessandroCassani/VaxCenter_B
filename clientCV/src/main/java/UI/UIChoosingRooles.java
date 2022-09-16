package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;



/**
 * La classe UIChoosingRooles crea l'interfaccia dove l'utente decide il ruolo in cui vuole accedere
 *
 * @author Paolo Bruscagin
 */
public class UIChoosingRooles extends JFrame implements ActionListener {

    /**
     * Bottone per accedere alla sezione operatore vaccinale
     */
    JButton operatoreVaccinale;

    /**
     * Bottone per accedere alla sezione cittadino
     */
    JButton cittadino;



    public UIChoosingRooles(){


        Border bordo = new LineBorder(new Color(0xFF37C47A, true), 4, true);

        JLabel scelta = new JLabel("SELEZIONA LA TIPOLOGIA DI UTENTE");
        scelta.setFont(new Font("Georgia", Font.BOLD, 25));
        scelta.setBounds(975, 100, 700, 200);


        //Personalizzazione bottone operatore vaccinale

        ImageIcon op = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/operatorevaccinale.png")));

        operatoreVaccinale =  new JButton("OPERATORE VACCINALE", op);
        operatoreVaccinale.setBounds(1055, 270, 400, 120);
        operatoreVaccinale.setFont(new Font("Georgia", Font.BOLD, 20));
        operatoreVaccinale.setBackground(new Color(0xA059E3B3));
        operatoreVaccinale.setForeground(Color.WHITE);
        operatoreVaccinale.setBorder(bordo);
        operatoreVaccinale.setFocusable(false);
        operatoreVaccinale.addActionListener(this);
        operatoreVaccinale.setOpaque(true);


        //Personalizzazione bottone cittadino

        ImageIcon cit = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cittadino.png")));

        cittadino = new JButton("CITTADINO", cit);
        cittadino.setBounds(1055, 470, 400, 120);
        cittadino.setFont(new Font("Georgia", Font.BOLD, 20));
        cittadino.setBackground(new Color(0xA059E3B3));
        cittadino.setForeground(Color.WHITE);
        cittadino.setBorder(bordo);
        cittadino.setFocusable(false);
        cittadino.addActionListener(this);
        cittadino.setOpaque(true);


        //Icona avvio del programma

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        //JLabel sfondo = new JLabel();
        //sfondo.setBounds(0, 0, 1600, 900);
        //sfondo.add(scelta);
        //sfondo.add(operatoreVaccinale);
        //sfondo.add(cittadino);

        //JPanel home = new JPanel();
        setBounds(0, 0, 1600, 900);
        setLocationRelativeTo(null);
        setLayout(null);
        add(scelta);
        add(operatoreVaccinale);
        add(cittadino);
        //home.add(sfondo);

        //this.add(sfondo);
        setTitle("VaxCenter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600,900);

        setResizable(false);
        setVisible(true);
        setForeground(Color.WHITE);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == operatoreVaccinale){
            this.dispose();
            new UIVaccineOperator();
        } else if(e.getSource() == cittadino){
            this.dispose();
            new UICitizen();
        }



    }
}
