package UI;

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
 * @author Alessandro Cassani
 */

public class UIVaccineOperator extends JFrame implements ActionListener {


    /**
     * Bottone per accedere alla sezione registra centro vaccinale
     */
    JButton registraCentroVaccinale = new JButton() ;

    /**
     * Bottone per accedere alla sezione registra vaccinato
     */
    JButton registraVaccinato = new JButton();

    /**
     * Bottone per tornare nell'interfaccia grafica UIChoosingRooles
     */
    JButton backtoChoosingRooles;

    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica
     */

    public UIVaccineOperator(){

        Border bordo = new LineBorder(new Color(0,49,83), 2, true);
        Border bordobtnInd = new LineBorder(new Color(169,50, 38), 2, true);

        //Personalizzazione bottone registra centro vaccinale

        ImageIcon cv = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/centrivaccinali.png")));

        JLabel iconCV = new JLabel(cv);
        JLabel registraCV = new JLabel("<html>REGISTRA<br> CENTRO VACCINALE</html>");
        registraCV.setForeground(Color.WHITE);
        registraCV.setFont(new Font("Georgia", Font.BOLD, 15));
        registraCentroVaccinale.setLayout(new BorderLayout());
        registraCentroVaccinale.add(iconCV,BorderLayout.WEST);
        registraCentroVaccinale.add(registraCV,BorderLayout.CENTER);
        registraCentroVaccinale.setHorizontalAlignment(SwingConstants.CENTER);
        registraCentroVaccinale.setBounds(600, 80, 350, 95);
        registraCentroVaccinale.setFont(new Font("Georgia", Font.BOLD, 15));
        registraCentroVaccinale.setBackground(new Color(65, 102, 245));
        registraCentroVaccinale.setForeground(Color.WHITE);
        registraCentroVaccinale.setBorder(bordo);
        registraCentroVaccinale.setFocusable(false);
        registraCentroVaccinale.addActionListener(this);
        registraCentroVaccinale.setOpaque(true);

        //Personalizzazione bottone rigistra vaccinato

        ImageIcon cr = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/registravaccinato.png")));

        JLabel iconRV = new JLabel(cr);
        JLabel registraVaccinatoLabel = new JLabel("   REGISTRA VACCINATO");
        registraVaccinatoLabel.setForeground(Color.WHITE);
        registraVaccinatoLabel.setFont(new Font("Georgia", Font.BOLD, 15));
        registraVaccinato.setLayout(new BorderLayout());
        registraVaccinato.add(iconRV,BorderLayout.WEST);
        registraVaccinato.add(registraVaccinatoLabel,BorderLayout.CENTER);
        registraVaccinato.setBounds(600, 220, 350, 95);
        registraVaccinato.setFont(new Font("Georgia", Font.BOLD, 15));
        registraVaccinato.setBackground(new Color(65, 102, 245));
        registraVaccinato.setForeground(Color.WHITE);
        registraVaccinato.setBorder(bordo);
        registraVaccinato.setFocusable(false);
        registraVaccinato.addActionListener(this);
        registraVaccinato.setOpaque(true);

        //Personalizzazione bottone indietro

        backtoChoosingRooles =  new JButton("INDIETRO");
        backtoChoosingRooles.setBounds(600, 400, 350, 95);
        backtoChoosingRooles.setFont(new Font("Georgia", Font.BOLD, 15));
        backtoChoosingRooles.setBackground(new Color(248, 9, 55));
        backtoChoosingRooles.setBorder(bordobtnInd);
        backtoChoosingRooles.setForeground(Color.WHITE);
        backtoChoosingRooles.setFocusable(false);
        backtoChoosingRooles.addActionListener(this);
        backtoChoosingRooles.setOpaque(true);

        setLayout(null);
        add(registraCentroVaccinale);
        add(registraVaccinato);
        add(backtoChoosingRooles);

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
     * metodo che permette la gestione degli eventi associati ai listener legati ai componenti d'interfaccia grafica
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
