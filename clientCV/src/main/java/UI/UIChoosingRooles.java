package UI;

import database.RoundButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.Objects;

/**
 * La classe UIChoosingRooles crea l'interfaccia grafica dove venegono presentate le due sezioni di funzionalita' fornite dal sistema, quella dedicata agli operatori
 * vaccinali e quella dedicata ai cittadini
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
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
     * Label Esci che permette di uscire dal programma
     */

    JLabel esci = new JLabel("Esci");


    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica della schermata di scelta della tipologia di utente
     *
     * @author @Paolo Bruscagin
     * @author Alessandro Cassani
     */

    public UIChoosingRooles(){

        //Label cliccabile che ti permette di uscire dal programma

        esci.setFont(new Font("Georgia", Font.ITALIC, 21));
        Font font = esci.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        esci.setFont(font.deriveFont(attributes));
        esci.setForeground(new Color(205, 221, 255));
        esci.setBounds(40,30,100,20);
        esci.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);

            }
        });

        //immagine programma

        immagine.setBounds(50, 80, 520, 430);
        immagine.setBackground(new Color(181, 226, 232));
        JLabel  lblPic = new JLabel();
        lblPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/sfondoVac.png"))));
        immagine.add(lblPic);

        JLabel scelta = new JLabel("SELEZIONA LA TIPOLOGIA DI UTENTE");
        scelta.setFont(new Font("roman_baseline", Font.PLAIN, 19));
        scelta.setBounds(590, 100, 400, 30);
        //scelta.setForeground(new Color(7, 114, 88));
        scelta.setForeground(new Color(19, 47, 164));

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

        add(scelta);
        add(operatoreVaccinale);
        add(cittadino);
        add(immagine);
        add(esci);
        setTitle("VaxCenter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize (1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setForeground(Color.WHITE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
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