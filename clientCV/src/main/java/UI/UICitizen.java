package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.Objects;

/**
 * La classe UIChoosingRooles crea l'interfaccia dove il cittadino sceglie se visualizzare le info di un centro vaccinale,
 * di registrarsi presso un centro vaccinale, di accedere e di inserire eventuali eventi avversi avuto dopo la somministrazione del vaccino
 *
 * @author Paolo Bruscagin
 * @author Alessandro Cassani
 */

public class UICitizen extends JFrame implements ActionListener {

    /**
     * Bottone per accedere alla sezione info centro vaccinale
     */
    JButton cercaCentroVaccinale = new JButton();

    /**
     * Bottone per accedere alla sezione registra centro cittadino
     */
    JButton registraCittadino = new JButton();

    /**
     * Label di login, quando premuto avvio UI di login
     */
    JLabel login = new JLabel("Login");


    /**
     * Bottone per tornare nell'interfaccia grafica UIChoosingRooles
     */

    JButton backToChoosingRooles;

    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica della schermata di menu' del cittadino
     *
     * @author Paolo Bruscagin
     * @author Alessandro Cassani
     */

    public UICitizen() {

        Border bordo = new LineBorder(new Color(0,49,83), 2, true);
        Border bordobtnInd = new LineBorder(new Color(181, 226, 232), 2, true);

        ImageIcon info = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/info_vaxcenter.png")));


        login.setFont(new Font("Georgia", Font.ITALIC, 20));
        Font font = login.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        login.setFont(font.deriveFont(attributes));
        login.setForeground(new Color(0,49,83));
        login.setBounds(890,20,100,30);
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new UILogin();
            }
        });

        JLabel cercaCV = new JLabel(info);
        JLabel cercaLabel = new JLabel("<html>RICERCA<br> CENTRO VACCINALE</html>");
        cercaLabel.setForeground(Color.WHITE);
        cercaLabel.setFont(new Font("Georgia", Font.BOLD, 15));
        cercaCentroVaccinale.setLayout(new BorderLayout());
        cercaCentroVaccinale.add(cercaCV,BorderLayout.WEST);
        cercaCentroVaccinale.add(cercaLabel,BorderLayout.CENTER);
        cercaCentroVaccinale.setBounds(600, 150, 350, 95);
        cercaCentroVaccinale.setFont(new Font("Georgia", Font.BOLD, 15));
        cercaCentroVaccinale.setBackground(new Color(65, 102, 245));
        cercaCentroVaccinale.setForeground(Color.WHITE);
        cercaCentroVaccinale.setBorder(bordo);
        cercaCentroVaccinale.setFocusable(false);
        cercaCentroVaccinale.addActionListener(this);
        cercaCentroVaccinale.setOpaque(true);

        ImageIcon reg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/registraCittadino_vaxcenter.png")));
        JLabel registraCitizen = new JLabel(reg);
        JLabel registralabel = new JLabel("<html>REGISTRATI PRESSO<br> UN CENTRO VACCINALE</html>");
        registralabel.setForeground(Color.WHITE);
        registralabel.setFont(new Font("Georgia", Font.BOLD, 15));
        registraCittadino.setLayout(new BorderLayout());
        registraCittadino.add(registraCitizen,BorderLayout.WEST);
        registraCittadino.add(registralabel,BorderLayout.CENTER);
        registraCittadino.setBounds(600, 300, 350, 95);
        registraCittadino.setFont(new Font("Georgia", Font.BOLD, 15));
        registraCittadino.setBackground(new Color(65, 102, 245));
        registraCittadino.setForeground(Color.WHITE);
        registraCittadino.setBorder(bordo);
        registraCittadino.setFocusable(false);
        registraCittadino.addActionListener(this);
        registraCittadino.setOpaque(true);

        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/color50ind.png")));
        backToChoosingRooles = new JButton(ind);
        backToChoosingRooles.setBounds(10, 10, 55 , 55);
        backToChoosingRooles.setFont(new Font("Georgia", Font.BOLD, 17));
        backToChoosingRooles.setBackground(new Color(181, 226, 232));
        backToChoosingRooles.setForeground(Color.WHITE);
        backToChoosingRooles.setBorder(bordobtnInd);
        backToChoosingRooles.setFocusable(false);
        backToChoosingRooles.addActionListener(this);
        backToChoosingRooles.setOpaque(true);

        setLayout(null);
        add(cercaCentroVaccinale);
        add(registraCittadino);
        add(login);
        add(backToChoosingRooles);

        //Icona avvio del programma
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());
        setTitle("Cittadino");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize (1000,600);
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(181, 226, 232));
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
        if (e.getSource() == backToChoosingRooles) {
            this.dispose();
            new UIChoosingRooles();
        } else if (e.getSource() == cercaCentroVaccinale) {
            this.dispose();
            new UISearchVaxCenter();
        } else if (e.getSource() == registraCittadino) {
            this.dispose();
            new UIRegisterCitizen();
        } else if (e.getSource() == login) {
            this.dispose();
            new UILogin();
        }

    }
}
