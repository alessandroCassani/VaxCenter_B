package clientCV.UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




/**
 * La classe UIChoosingRooles crea l'interfaccia dove l'utente decide il ruolo in cui vuole accedere
 *
 * @author Paolo Bruscagin
 */
public class UIChoosingRooles extends JFrame implements ActionListener {

    //utilizzato per richiamre le immagini associate ai bottoni
    java.net.URL url;

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

        JLabel scelta = new JLabel();
        scelta.setText("SELEZIONA LA TIPOLOGIA DI UTENTE");
        scelta.setFont(new Font("Georgia", Font.BOLD, 25));
        scelta.setBounds(975, 100, 700, 200);


        //Personalizzazione bottone operatore vaccinale

        ImageIcon op;
        url = ClassLoader.getSystemResource("images/operatorevaccinale.png");
        op = (url != null) ? new ImageIcon( url ) : null;

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

        ImageIcon cit;
        url = ClassLoader.getSystemResource("images/cittadino.png");
        cit = (url != null) ? new ImageIcon( url ) : null;

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

        ImageIcon logo;
        url = ClassLoader.getSystemResource("images/logo.png");
        logo = (url != null) ? new ImageIcon( url ) : null;
        setIconImage(logo.getImage());


        JLabel sfondo = new JLabel();
        sfondo.setBounds(0, 0, 1600, 900);
        sfondo.add(scelta);
        sfondo.add(operatoreVaccinale);
        sfondo.add(cittadino);

        JPanel home = new JPanel();
        home.setBounds(0, 0, 1600, 900);
        home.setLayout(null);
        home.add(sfondo);

        this.add(sfondo);
        this.setTitle("VaxCenter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600,900);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);


    }
    public static void main(String[] args){
        new UIChoosingRooles();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == operatoreVaccinale){
            this.dispose();
            new UIVaccineOperator();
        } else if(e.getSource() == cittadino){
            this.dispose();
            //new UICitizen();
        }



    }
}
