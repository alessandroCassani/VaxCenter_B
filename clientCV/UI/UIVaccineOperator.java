package clientCV.UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        //utilizzato per richiamre le immagini associate ai bottoni
        java.net.URL url;

        Border bordo = new LineBorder(new Color(0xFF37C47A, true), 4, true);


        //Personalizzazione bottone registra centro vaccinale

        ImageIcon cv;
        url = ClassLoader.getSystemResource("images/centrivaccinali.png");
        cv = (url != null) ? new ImageIcon( url ) : null;

        registraCentroVaccinale =  new JButton("REGISTRA CENTRO VACCINALE", cv);
        registraCentroVaccinale.setBounds(1055, 270, 400, 120);
        registraCentroVaccinale.setFont(new Font("Georgia", Font.BOLD, 20));
        registraCentroVaccinale.setBackground(new Color(0xA059E3B3));
        registraCentroVaccinale.setForeground(Color.WHITE);
        registraCentroVaccinale.setBorder(bordo);
        registraCentroVaccinale.setFocusable(false);
        registraCentroVaccinale.addActionListener(this);
        registraCentroVaccinale.setOpaque(true);


        //Personalizzazione bottone rigistra vaccinato

        ImageIcon cr;
        url = ClassLoader.getSystemResource("images/registravaccinato.png");
        cr = (url != null) ? new ImageIcon( url ) : null;

        registraVaccinato =  new JButton("REGISTRA VACCINATO", cr);
        registraVaccinato.setBounds(1055, 470, 400, 120);
        registraVaccinato.setFont(new Font("Georgia", Font.BOLD, 20));
        registraVaccinato.setBackground(new Color(0xA059E3B3));
        registraVaccinato.setForeground(Color.WHITE);
        registraVaccinato.setBorder(bordo);
        registraVaccinato.setFocusable(false);
        registraVaccinato.addActionListener(this);
        registraVaccinato.setOpaque(true);


        //Personalizzazione bottone indietro

        ImageIcon ind;
        url = ClassLoader.getSystemResource("images/iconaindietro.png");
        ind = (url != null) ? new ImageIcon( url ) : null;

        backtoChoosingRooles =  new JButton("INDIETRO", ind);
        backtoChoosingRooles.setBounds(1055, 670, 400, 120);
        backtoChoosingRooles.setFont(new Font("Georgia", Font.BOLD, 20));
        backtoChoosingRooles.setBackground(new Color(0xA059E3B3));
        backtoChoosingRooles.setForeground(Color.WHITE);
        backtoChoosingRooles.setBorder(bordo);
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
        ImageIcon logo;
        url = ClassLoader.getSystemResource("images/logo.png");
        logo = (url != null) ? new ImageIcon( url ) : null;
        setIconImage(logo.getImage());

        this.add(sfondo);
        this.setTitle("VaxCenter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600,900);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backtoChoosingRooles){
            this.dispose();
            new UIChoosingRooles();
        } else if(e.getSource() == registraCentroVaccinale){
            this.dispose();
            //new UIRegisterVaxCenter();
        } else if(e.getSource() == registraVaccinato){
            this.dispose();
            //new UIRegisterVaccinated();
        }

    }


}
