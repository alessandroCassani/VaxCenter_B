package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.Objects;

/**
 * classe che rappresenta l'interfaccia grafica nella quale inserire le credenziali per accedere al dataBase Postgres e alle funzionalita' del server
 *
 *  @author Alessandro Cassani
 *  @author Paolo Bruscagin
 */
public class UILoginToServer extends JFrame implements ActionListener {

    /**
     * label rappresentante la stringa host
     */
    JLabel hostLabel = new JLabel("host:");

    /**
     * label rappresentante la stringa di default uguale a localhost
     */
    JLabel hostName = new JLabel("localhost");

    /**
     * label rappresentante la stringa di default uguale a username
     */
    JLabel userLabel = new JLabel("username:");

    /**
     * campo di testo dove inserire lo user
     */
    JTextField userTextField = new JTextField();
    /**
     * campo di testo dove inserire lo password
     */
    JPasswordField pswTextField = new JPasswordField();

    /**
     * label rappresentante password
     */
    JLabel pswLabel = new JLabel("password:");

    /**
     * bottone di login
     */
    JButton loginButton=new JButton("LOGIN");

    /**
     * checkBox che permette di mostrare o nascondere la password inserita
     */
    JCheckBox showPassword = new JCheckBox("show password");


    /**
     * Label Esci che permette di uscire dal programma
     */

    JLabel esci = new JLabel("Esci");

    /**
     * Panel per inserire l'immagine d'interfaccia
     */
    JPanel immagine = new JPanel();

    public static void main(String[] args) {
        new UILoginToServer();
    }

    /**
     * costruttore che permette il caricamento dei componenti di interfaccia grafica
     */
    public UILoginToServer(){

        //Label cliccabile che ti permette di uscire dal programma

        esci.setFont(new Font("Georgia", Font.ITALIC, 18));
        Font font = esci.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        esci.setFont(font.deriveFont(attributes));
        esci.setForeground(new Color(0,49,83));
        esci.setBounds(60,30,100,20);
        esci.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);

            }
        });
        Border bordo = new LineBorder(new Color(0x808080, true), 2, true);

        //immagine programma

        immagine.setBounds(50, 80, 520, 430);
        immagine.setBackground(new Color(181, 226, 232));
        JLabel  lblPic = new JLabel();
        lblPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/scv_v2.png"))));
        immagine.add(lblPic);


        hostLabel.setFont(new Font("Arial",Font.ITALIC,20));
        hostLabel.setBounds(600,200,100,20);
        hostLabel.setBorder(bordo);

        hostName.setFont(new Font("Arial",Font.BOLD,20));
        hostName.setBounds(750,200,100,20);
        hostName.setBorder(bordo);

        userLabel.setFont(new Font("Arial",Font.ITALIC,20));
        userLabel.setBounds(600,250,100,20);
        userLabel.setBorder(bordo);

        userTextField.setFont(new Font("Arial",Font.BOLD,20));
        userTextField.setBounds(750,250,100,30);
        userTextField.setBackground(new Color(0Xf5bbcd));
        userTextField.setBorder(bordo);


        pswLabel.setFont(new Font("Arial",Font.ITALIC,20));
        pswLabel.setBounds(600,300,100,20);
        pswLabel.setBorder(bordo);

        pswTextField.setFont(new Font("Arial",Font.BOLD,20));
        pswTextField.setBounds(750,300,100,30);
        pswTextField.setBackground(new Color(0Xf5bbcd));
        pswTextField.setBorder(bordo);

        showPassword.setBounds(860,310,130,15);
        showPassword.addActionListener(this);

        loginButton.setBounds(675,380,90,40);
        loginButton.setOpaque(true);
        loginButton.addActionListener(this);
        loginButton.setForeground(new Color(0XFFE900));
        loginButton.setBackground(new Color(0Xe43e6f));


        add(hostLabel);
        add(hostName);
        add(userLabel);
        add(pswLabel);
        add(userTextField);
        add(pswTextField);
        add(loginButton);
        add(showPassword);
        add(esci);
        add(immagine);


        //Icona avvio del programma
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logoServer.png")));
        setIconImage(logo.getImage());

        setTitle("Login to Server");
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

        //Popup "Se sicuro di uscire?"
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                UIManager.put("OptionPane.yesButtonText", "Si");
                UIManager.put("OptionPane.noButtonText", "No");

                int resp = JOptionPane.showConfirmDialog(null, "Sei sicuro di uscire?",
                        "Esci?", JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    /**
     * metodo che permette la gestione degli eventi associati ai listener legati ai componenti di interfaccia grafica
     * @param e the event to be processed
     *
     * @author Alessandro Cassani
     * @author Paolo Bruscagin
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            this.dispose();
            //verifica accesso db con metodo connect
            new UIServerHome();
        }

        if(e.getSource() == showPassword){
            if (showPassword.isSelected())
                pswTextField.setEchoChar((char) 0);
                else
                pswTextField.setEchoChar('*');
        }
    }
}
