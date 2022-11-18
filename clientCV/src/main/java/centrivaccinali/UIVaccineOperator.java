package centrivaccinali;

import database.UI.RoundButton;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * La classe UIVaccineOperator crea l'interfaccia dove l'operatore vaccinale sceglie se inserire
 * un nuovo centro vaccinale oppure se inserire un nuovo cittadino vaccinato
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */

public class UIVaccineOperator extends JFrame implements ActionListener {

    /**
     * Panel per inserire l'immagine d'interfaccia
     */
    JPanel immagine = new JPanel();


    /**
     * Bottone per accedere alla sezione registra centro vaccinale
     */
    RoundButton registraCentroVaccinale = new RoundButton() ;

    /**
     * Bottone per accedere alla sezione registra vaccinato
     */
    RoundButton registraVaccinato = new RoundButton();

    /**
     * Bottone per tornare nell'interfaccia grafica UIChoosingRooles
     */
    JButton backtoChoosingRooles;

    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica relativa al menu delloperatore vaccinale
     *
     * @author Alessandro Cassani
     * @author paolo Bruscagin
     */

    public UIVaccineOperator(){


        Border bordobtnInd = new LineBorder(new Color(181, 226, 232), 2, true);

        //immagine programma

        immagine.setBounds(50, 80, 520, 430);
        immagine.setBackground(new Color(181, 226, 232));
        JLabel  lblPic = new JLabel();
        lblPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/OV.png"))));
        immagine.add(lblPic);



        //Personalizzazione bottone registra centro vaccinale

        ImageIcon cv = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/centrivaccinali.png")));
        cv = registraCentroVaccinale.resizeImage(cv,90,90);
        JLabel iconCV = new JLabel(cv);
        JLabel registraCV = new JLabel("<html>REGISTRA<br> CENTRO VACCINALE</html>");
        registraCV.setForeground(Color.WHITE);
        registraCV.setFont(new Font("Georgia", Font.BOLD, 15));
        registraCentroVaccinale.setLayout(new BorderLayout());
        registraCentroVaccinale.add(iconCV,BorderLayout.WEST);
        registraCentroVaccinale.add(registraCV,BorderLayout.CENTER);
        registraCentroVaccinale.setHorizontalAlignment(SwingConstants.CENTER);
        registraCentroVaccinale.setBounds(600, 150, 350, 95);
        registraCentroVaccinale.setFont(new Font("Georgia", Font.BOLD, 15));
        registraCentroVaccinale.setBackground(new Color(65, 102, 245));
        registraCentroVaccinale.setForeground(Color.WHITE);
        registraCentroVaccinale.setBorder(new EmptyBorder(0,24,10,0));
        registraCentroVaccinale.setFocusable(false);
        registraCentroVaccinale.addActionListener(this);

        //Personalizzazione bottone rigistra vaccinato

        ImageIcon cr = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/registravaccinato.png")));

        JLabel iconRV = new JLabel(cr);
        JLabel registraVaccinatoLabel = new JLabel("REGISTRA VACCINATO");
        registraVaccinatoLabel.setForeground(Color.WHITE);
        registraVaccinatoLabel.setFont(new Font("Georgia", Font.BOLD, 15));
        registraVaccinato.setLayout(new BorderLayout());
        registraVaccinato.add(iconRV,BorderLayout.WEST);
        registraVaccinato.add(registraVaccinatoLabel,BorderLayout.CENTER);
        registraVaccinato.setBounds(600, 300, 350, 95);
        registraVaccinato.setFont(new Font("Georgia", Font.BOLD, 15));
        registraVaccinato.setBackground(new Color(65, 102, 245));
        registraVaccinato.setForeground(Color.WHITE);
        registraVaccinato.setBorder(new EmptyBorder(0,24,0,0));
        registraVaccinato.setFocusable(false);
        registraVaccinato.addActionListener(this);


        //Personalizzazione bottone indietro

        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/color50ind.png")));
        backtoChoosingRooles = new JButton(ind);
        backtoChoosingRooles.setBounds(10, 10, 55 , 55);
        backtoChoosingRooles.setFont(new Font("Georgia", Font.BOLD, 17));
        backtoChoosingRooles.setBackground(new Color(181, 226, 232));
        backtoChoosingRooles.setForeground(Color.WHITE);
        backtoChoosingRooles.setBorder(bordobtnInd);
        backtoChoosingRooles.setFocusable(false);
        backtoChoosingRooles.addActionListener(this);
        backtoChoosingRooles.setOpaque(true);
        backtoChoosingRooles.setContentAreaFilled(false);

        setLayout(null);
        add(registraCentroVaccinale);
        add(registraVaccinato);
        add(backtoChoosingRooles);
        add(immagine);

        //Icona avvio del programma

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());
        setTitle("Operatore Vaccinale");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setSize (1000, 600);
        getContentPane().setBackground(new Color(181, 226, 232));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setForeground(Color.WHITE);
    }

    /**
     * metodo che permette di gestire gli eventi associati ai listener dei componenti di UI attivati dall'utente
     * @param e the event to be processed
     *
     * @author Paolo Bruscagin
     */
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
            new UIRegisterVaccinated();
        }
    }
}