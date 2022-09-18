package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * classe che rappresenta l'interfaccia grafica per accedere al dataBase Postgre e alle funzionalita' del server
 *
 *  @author Alessandro Cassani
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
     * container dei componenti di interfaccia grafica
     */
    Container container = getContentPane();

    /**
     * checkBox che permette di mostrare o nascondere la password inserita
     */
    JCheckBox showPassword = new JCheckBox("show password");

    public static void main(String[] args) {
        new UILoginToServer();
    }

    /**
     * costruttore che permette il caricamento dei componenti di interfaccia grafica
     */
    public UILoginToServer(){
        Border bordo = new LineBorder(new Color(0x808080, true), 2, true);

        container.setLayout(null);

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


        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        JLabel background = new JLabel(logo);
        background.setBounds(0,-20,600,600);

        container.add(hostLabel);
        container.add(hostName);
        container.add(userLabel);
        container.add(pswLabel);
        container.add(userTextField);
        container.add(pswTextField);
        container.add(loginButton);
        container.add(background);
        container.add(showPassword);


        setTitle("Login to server");
        setSize(1000,600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    /**
     * metodo che permette la gestione degli eventi associati ai listener legati ai componenti di interfaccia grafica
     * @param e the event to be processed
     *
     * @author Alessandro Cassani
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
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
