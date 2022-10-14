package UI;

import UI.graphics.RoundButton;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * La classe UIChoosingRooles crea l'interfaccia grafica dove venegono presentate le due sezioni di funzionalita' fornite dal sistema, quella dedicata agli operatori
 * vaccinali e quella dedicata ai cittadini
 *
 * @author Paolo Bruscagin
 * @author Alessandro Cassani
 */
public class UIChoosingRooles extends JFrame implements ActionListener {

    /**
     * Bottone per accedere alla sezione operatore vaccinale
     */
    RoundButton operatoreVaccinale = new RoundButton();

    /**
     * Bottone per accedere alla sezione cittadino
     */
    RoundButton cittadino = new RoundButton();

    /**
     * Panel per inserire l'immagine d'interfaccia
     */
    JPanel immagine = new JPanel();




    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica della schermata di scelta della tipologia di utente
     *
     * @author @Paolo Bruscagin
     * @author Alessandro Cassani
     */

    public UIChoosingRooles(){

        immagine.setBounds(50, 80, 520, 430);
        immagine.setBackground(new Color(181, 226, 232));
        JLabel  lblPic = new JLabel();
        lblPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Vac_v1.png"))));
        immagine.add(lblPic);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        JLabel scelta = new JLabel("SELEZIONA LA TIPOLOGIA DI UTENTE");
        scelta.setFont(new Font("Georgia", Font.BOLD, 17));
        scelta.setBounds(590, 100, 400, 30);

        ImageIcon op = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/operatorevaccinale.png")));
        op = operatoreVaccinale.resizeImage(op,70,80);
        JLabel iconOV = new JLabel(op);
        JLabel operator = new JLabel("    OPERATORE VACCINALE");
        operator.setFont(new Font("Georgia", Font.BOLD, 15));
        operator.setForeground(Color.WHITE);



        operatoreVaccinale.setLayout(new BorderLayout());
        operatoreVaccinale.setBorder(new EmptyBorder(0,24,0,0));
        operatoreVaccinale.add(iconOV,BorderLayout.WEST);
        operatoreVaccinale.add(operator,BorderLayout.CENTER);
        operatoreVaccinale.setBounds(600, 180, 350, 95);
        operatoreVaccinale.setFont(new Font("Georgia", Font.BOLD, 15));
        operatoreVaccinale.setBackground(new Color(65, 102, 245));
        operatoreVaccinale.setHorizontalTextPosition(SwingConstants.RIGHT);
        operatoreVaccinale.setForeground(Color.WHITE);
        operatoreVaccinale.setFocusable(false);
        operatoreVaccinale.addActionListener(this);


        //Personalizzazione bottone cittadino

        ImageIcon cit = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cittadino.png")));
        cit = cittadino.resizeImage(cit,60,70);
        JLabel icon = new JLabel(cit);
        JLabel citizen = new JLabel("                 CITTADINO");
        citizen.setForeground(Color.WHITE);
        citizen.setFont(new Font("Georgia", Font.BOLD, 15));
        cittadino.setLayout(new BorderLayout());
        cittadino.setBorder(new EmptyBorder(0,29,0,0));
        cittadino.add(icon,BorderLayout.WEST);
        cittadino.add(citizen,BorderLayout.CENTER);
        cittadino.setBounds(600, 335, 350, 95);
        cittadino.setFont(new Font("Georgia", Font.BOLD, 15));
        cittadino.setBackground(new Color(65, 102, 245));
        cittadino.setForeground(Color.WHITE);
        cittadino.setIconTextGap(80);
        cittadino.setFocusable(false);
        cittadino.addActionListener(this);

        //Icona avvio del programma
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        setLayout(null);
        add(scelta);
        add(operatoreVaccinale);
        add(cittadino);
        add(immagine);
        setTitle("VaxCenter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize (1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setForeground(Color.WHITE);
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(181, 226, 232));
        setForeground(Color.WHITE);
        setVisible(true);
    }

    /**
     * metodo che permette di gestire gli eventi associati ai listener dei componenti di UI attivati dall'utente
     * @param e the event to be processed
     *
     * @author Paolo Bruscagin
     */

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