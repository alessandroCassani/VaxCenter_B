package database;

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
     JLabel hostLabel = new JLabel("Host:");

    /**
     * campo di testo dove inserire l'host
     */
    static JTextField hostTextField = new JTextField();

    /**
     * label rappresentante la stringa port
     */

     JLabel portLabel =new JLabel("Port");

    /**
     * campo di testo dove inserire la porta del database
     */

    static JTextField portTextField = new JTextField();

    /**
     * label rappresentante la stringa di default uguale a username
     */
    JLabel userLabel = new JLabel("Username:");

    /**
     * campo di testo dove inserire lo user
     */
    static JTextField userTextField = new JTextField();
    /**
     * campo di testo dove inserire lo password
     */
    static JPasswordField pswTextField = new JPasswordField();

    /**
     * label rappresentante password
     */
    JLabel pswLabel = new JLabel("Password:");

    /**
     * bottone di login
     */
    RoundButton loginButton=new RoundButton("LOGIN");

    /**
     * checkBox che permette di mostrare o nascondere la password inserita
     */
    JCheckBox showPassword = new JCheckBox("mostra password");


    /**
     * Label Esci che permette di uscire dal programma
     */

    JLabel esci = new JLabel("Esci");

    /**
     * Panel per inserire l'immagine d'interfaccia
     */
    JPanel immagine = new JPanel();

    /**
     * Status che indica che non è stato possibile accedere al Server
     */

    JLabel statusError = new JLabel("Non è stato possibile accedere al Server! Riprovare...");

    /**
     * Bottone per pulire tutte i campi
     */
    JButton pulisci;

    /**
     * JDialog check collegamento server
     */
    JDialog checkServerDialog = new JDialog();

    /**
     * Avvio del programma Server
     * @param args
     * @author Paolo Bruscagin
     */

    public static void main(String[] args) {
        new UILoginToServer();
    }

    /**
     * costruttore che permette il caricamento dei componenti d'interfaccia grafica
     */
    public UILoginToServer(){

        JLabel titolo = new JLabel("ACCEDI AL SERVER");
        titolo.setFont(new Font("Georgia", Font.BOLD, 17));
        titolo.setBounds(670, 80, 400, 30);

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
        Border bordobtnInd = new LineBorder(new Color(181, 226, 232), 2, true);


        //immagine programma

        immagine.setBounds(50, 80, 520, 430);
        immagine.setBackground(new Color(181, 226, 232));
        JLabel  lblPic = new JLabel();
        lblPic.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/scv_v2.png"))));
        immagine.add(lblPic);


        hostLabel.setFont(new Font("Arial",Font.ITALIC,20));
        hostLabel.setBounds(600,150,100,20);
        hostLabel.setBorder(bordo);

        hostTextField.setFont(new Font("Arial",Font.BOLD,16));
        hostTextField.setBounds(750,150,150,30);
        hostTextField.setBackground(new Color(0Xf5bbcd));
        hostTextField.setBorder(bordo);

        portLabel.setFont(new Font("Arial",Font.ITALIC,20));
        portLabel.setBounds(600,200,100,20);
        portLabel.setBorder(bordo);

        portTextField.setFont(new Font("Arial",Font.BOLD,16));
        portTextField.setBounds(750,200,150,30);
        portTextField.setBackground(new Color(0Xf5bbcd));
        portTextField.setBorder(bordo);

        userLabel.setFont(new Font("Arial",Font.ITALIC,20));
        userLabel.setBounds(600,250,120,20);
        userLabel.setBorder(bordo);

        userTextField.setFont(new Font("Arial",Font.BOLD,16));
        userTextField.setBounds(750,250,150,30);
        userTextField.setBackground(new Color(0Xf5bbcd));
        userTextField.setBorder(bordo);


        pswLabel.setFont(new Font("Arial",Font.ITALIC,20));
        pswLabel.setBounds(600,300,100,20);
        pswLabel.setBorder(bordo);

        pswTextField.setFont(new Font("Arial",Font.BOLD,16));
        pswTextField.setBounds(750,300,150,30);
        pswTextField.setBackground(new Color(0Xf5bbcd));
        pswTextField.setBorder(bordo);

        showPassword.setBounds(750,350,130,12);
        showPassword.setFont(new Font("Arial",Font.BOLD,12));
        showPassword.addActionListener(this);
        showPassword.setBackground(new Color(181, 226, 232));

        loginButton.setBounds(600,410,200,50);
        loginButton.setFont(new Font("Georgia", Font.BOLD, 20));
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);
        loginButton.setForeground(new Color(0XFFE900));
        loginButton.setBackground(new Color(0Xe43e6f));


        statusError.setFont(new Font("Georgia", Font.BOLD, 14));
        statusError.setForeground(new Color(0xEC0909));
        statusError.setBounds(500, 500, 400, 55);

        ImageIcon pul = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/coloroPul50.png")));
        pulisci = new JButton(pul);
        pulisci.setBounds(825, 410, 50, 50);
        pulisci.setFont(new Font("Georgia", Font.BOLD, 17));
        pulisci.setBackground(new Color(181, 226, 232));
        pulisci.setForeground(Color.WHITE);
        pulisci.setBorder(bordobtnInd);
        pulisci.setFocusable(false);
        pulisci.addActionListener(this);
        pulisci.setOpaque(true);


        add(hostLabel);
        add(hostTextField);
        add(portLabel);
        add(portTextField);
        add(userLabel);
        add(pswLabel);
        add(userTextField);
        add(pswTextField);
        add(loginButton);
        add(showPassword);
        add(esci);
        add(immagine);
        add(titolo);
        add(statusError).setVisible(false);
        add(pulisci);

        //Icona avvio del programma
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/serverdb.png")));
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
     * metodo che permette la gestione degli eventi associati ai listener legati ai componenti d'interfaccia grafica
     * @param e the event to be processed
     *
     * @author Alessandro Cassani
     * @author Paolo Bruscagin
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            if (pswTextField.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Login Negato! Riprovare", "Messaggio",JOptionPane.ERROR_MESSAGE);

            }else {this.dispose();
                DBManagement.connect(getHostTextField(),getPortTextField(),getUserTextField(),getPswTextField());
                new UIServerHome();}

        }else if(e.getSource() == showPassword){
            if (showPassword.isSelected())
                pswTextField.setEchoChar((char) 0);
                else
                pswTextField.setEchoChar('*');
        } else if (e.getSource() == pulisci) {
            hostTextField.setText("");
            portTextField.setText("");
            userTextField.setText("");
            pswTextField.setText("");
            pswTextField.setEchoChar('*');
            statusError.setVisible(false);
            showPassword.setSelected(false);
        }
    }

    /**
     * il metodo permette di ottenere dalla TextField raffigurante la porta il valore inserito dall'utente
     * @return porta scelta dall'utente
     * @author Damiano Ficara
     * @author Luca Perfetti
     */
     public static Integer getPortTextField() {
        String port =  portTextField.getText();
        return Integer.parseInt(port);


    }
    /**
     * il metodo permette di ottenere dalla TextField host inserito dall'utente
     * @return host scelto dall'utente
     * @author Damiano Ficara
     * @author Luca Perfetti
     */

    public static String getHostTextField() {
        return hostTextField.getText();
    }

    /**
     * il metodo permette di ottenere dalla TextField il nome utente inserito dall'utente
     * @return nome utente
     * @author Damiano Ficara
     * @author Luca Perfetti
     */

    public static String getUserTextField() {
        return userTextField.getText();
    }
    /**
     * il metodo permette di ottenere dalla PasswordField la password inserita dall'utente
     * @return nome utente
     * @author Damiano Ficara
     * @author Luca Perfetti
     */
    public static String getPswTextField() {
        String pwd = String.valueOf(pswTextField.getPassword());
        return pwd;
    }
}
