package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


/**
 *
 * @author Paolo Bruscagin
 * @author Alessandro Cassani
 */

public class UIRegisterCitizen extends JFrame implements ActionListener {

    JComboBox<String> nomeCV = new JComboBox<>(new String[]{"Paolo", "Damiano", "Alessandro", "Luca"}); // ricerca in db

    JTextField nomeCittadino = new JTextField(30);

    JTextField cognomeCittadino = new JTextField(30);

    JTextField codiceFiscale = new JTextField(16);

    JTextField email = new JTextField(30);

    JTextField userID =new JTextField(20);

    JPasswordField password = new JPasswordField(20);

    JPasswordField ripetiPassword = new JPasswordField(20);

    JTextField IDUnivoco = new JTextField(16);

    JButton registraCittadino = new JButton("REGISTRA");

    JButton pulisci;

    JCheckBox showPassword = new JCheckBox("show password");

    JButton backToCitizen;

    JLabel status = new JLabel();

    public UIRegisterCitizen(){

        Border bordobtn = new LineBorder(new Color(0,49,83), 4, true);
        Border bordobtnInd = new LineBorder(new Color(181, 226, 232), 2, true);
        Border bordobtnPul = new LineBorder(new Color(169,50, 38), 2, true);


        JLabel titoloCittadino = new JLabel("Registrati presso un Centro Vaccinale");
        titoloCittadino.setFont(new Font("Georgia", Font.BOLD, 20));
        add(titoloCittadino).setBounds(320, 0, 550, 75);

        JLabel labelNome = new JLabel("Nome Centro Vaccinale:");
        labelNome.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelNome).setBounds(100, 40, 550, 75);

        nomeCV.setFont(new Font("Arial", Font.ITALIC, 20));
        nomeCV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        nomeCV.setBounds(100, 95, 790, 55);
        nomeCV.setBackground(Color.WHITE);
        AutoCompleteDecorator.decorate(nomeCV);


        JLabel labelnomeCit = new JLabel("Nome:");
        labelnomeCit.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelnomeCit).setBounds(100, 140, 550, 75);

        nomeCittadino.setFont(new Font("Arial", Font.ITALIC, 20));
        nomeCittadino.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        nomeCittadino.setPreferredSize(new Dimension(325, 75));
        nomeCittadino.setBounds(100, 195, 250, 55);

        JLabel labelcognomeCit = new JLabel("Cognome:");
        labelcognomeCit.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelcognomeCit).setBounds(370, 140, 250, 75);

        cognomeCittadino.setFont(new Font("Arial", Font.ITALIC, 20));
        cognomeCittadino.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        cognomeCittadino.setPreferredSize(new Dimension(325, 75));
        cognomeCittadino.setBounds(370, 195, 250, 55);

        JLabel labelCF = new JLabel("Codice Fiscale:");
        labelCF.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelCF).setBounds(640, 140, 250, 75);

        codiceFiscale.setFont(new Font("Arial", Font.ITALIC, 20));
        codiceFiscale.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        codiceFiscale.setPreferredSize(new Dimension(325, 75));
        codiceFiscale.setBounds(640, 195, 250, 55);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelEmail).setBounds(100, 240, 250, 75);

        email.setFont(new Font("Arial", Font.BOLD, 20));
        email.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        email.setPreferredSize(new Dimension(325, 75));
        email.setBounds(100, 295, 250, 55);

        JLabel labelUserID = new JLabel("User ID:");
        labelUserID.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelUserID).setBounds(370, 240, 250, 75);

        userID.setFont(new Font("Arial", Font.BOLD, 20));
        userID.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        userID.setPreferredSize(new Dimension(325, 75));
        userID.setBounds(370, 295, 250, 55);

        JLabel labelIDUnivoco = new JLabel("ID Vaccinazione:");
        labelIDUnivoco.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelIDUnivoco).setBounds(640, 240, 250, 75);

        IDUnivoco.setFont(new Font("Arial", Font.ITALIC, 20));
        IDUnivoco.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        IDUnivoco.setBounds(640, 295, 250, 55);
        IDUnivoco.setBackground(Color.WHITE);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Georgia", Font.BOLD, 17));
        add(labelPassword).setBounds(100, 350, 250, 55);

        password.setFont(new Font("Arial", Font.ITALIC, 20));
        password.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        password.setBounds(100, 390, 250, 55);
        password.setBackground(Color.WHITE);


        JLabel labelRipetiPassword = new JLabel("Ripeti Password:");
        labelRipetiPassword.setFont(new Font("Georgia", Font.BOLD, 17));
        add(labelRipetiPassword).setBounds(100, 435, 550, 55);

        ripetiPassword.setFont(new Font("Arial", Font.ITALIC, 20));
        ripetiPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        ripetiPassword.setBounds(100, 475, 250, 55);
        ripetiPassword.setBackground(Color.WHITE);

        showPassword.setFont(new Font("Arial", Font.BOLD, 15));
        showPassword.setBackground(new Color(181, 226, 232));
        showPassword.setBounds(370,400,160,15);
        showPassword.addActionListener(this);


        JLabel labelInfopsw = new JLabel("Requisiti Password:"); // serve per i requisiti password
        labelInfopsw.setFont(new Font("Georgia", Font.BOLD, 8));
        labelInfopsw.setForeground(new Color(0xEC0909));
        add(labelInfopsw).setBounds(370, 420, 200, 15);


        JLabel labelInfopsw1 = new JLabel("-Lunghezza compresa tra 8 e 20 caratteri"); // serve per i requisiti password
        labelInfopsw1.setFont(new Font("Georgia", Font.BOLD, 8));
        labelInfopsw1.setForeground(new Color(0xEC0909));
        add(labelInfopsw1).setBounds(370, 435, 200, 10);

        JLabel labelInfopsw2 = new JLabel("-Almeno una lettera maiuscola ed una minuscola"); // serve per i requisiti password
        labelInfopsw2.setFont(new Font("Georgia", Font.BOLD, 8));
        labelInfopsw2.setForeground(new Color(0xEC0909));
        add(labelInfopsw2).setBounds(370, 445, 200, 10);

        JLabel labelInfopsw3 = new JLabel("-Almeno un numero"); // serve per i requisiti password
        labelInfopsw3.setFont(new Font("Georgia", Font.BOLD, 8));
        labelInfopsw3.setForeground(new Color(0xEC0909));
        add(labelInfopsw3).setBounds(370, 455, 200, 10);

        JLabel labelInfopsw4 = new JLabel("-Almeno un carattere speciale tra: ! # $ % & @ * + / - ? "); // serve per i requisiti password
        labelInfopsw4.setFont(new Font("Georgia", Font.BOLD, 8));
        labelInfopsw4.setForeground(new Color(0xEC0909));
        add(labelInfopsw4).setBounds(370, 465, 250, 10);


        registraCittadino.setBounds(675, 380, 150, 50);
        registraCittadino.setFont(new Font("Georgia", Font.BOLD, 17));
        registraCittadino.setBackground(new Color(0,0,128));
        registraCittadino.setForeground(Color.WHITE);
        registraCittadino.setBorder(bordobtn);
        registraCittadino.setFocusable(false);
        registraCittadino.addActionListener(this);
        registraCittadino.setOpaque(true);

        status.setFont(new Font("Georgia", Font.BOLD, 18));
        status.setBounds(75, 700, 400, 75);

        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/color50ind.png")));

        backToCitizen = new JButton(ind);
        backToCitizen.setBounds(10, 5, 55 , 55);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 17));
        backToCitizen.setBackground(new Color(181, 226, 232));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnInd);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);

        ImageIcon pul = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/coloroPul50.png")));

        pulisci = new JButton(pul);
        pulisci.setBounds(850, 380, 50, 50);
        pulisci.setFont(new Font("Georgia", Font.BOLD, 17));
        pulisci.setBackground(new Color(181, 226, 232));
        pulisci.setForeground(Color.WHITE);
        pulisci.setBorder(bordobtnInd);
        pulisci.setFocusable(false);
        pulisci.addActionListener(this);
        pulisci.setOpaque(true);


        setLayout(null);
        add(nomeCV);
        add(nomeCittadino);
        add(cognomeCittadino);
        add(codiceFiscale);
        add(email);
        add(userID);
        add(password);
        add(showPassword);
        add(ripetiPassword);
        add(IDUnivoco);
        add(registraCittadino);
        //add(status);
        add(pulisci);
        add(backToCitizen);

        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setTitle("Registrazione");
        setForeground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize (1000,600);
        getContentPane().setBackground(new Color(181, 226, 232));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backToCitizen) {
            this.dispose();
            new UICitizen();
        } else if (e.getSource() == registraCittadino) {
            if (!nomeCittadino.getText().equals("Paolo")){
                status.setForeground(new Color(0xEC0909));
                status.setText("I dati inseriti non sono corretti! Riprovare ...");
            } else{
                status.setForeground(new Color(0x077507));
                status.setText("Registrato con successo!");
            }

        }else if(e.getSource() == pulisci) {
            nomeCV.setSelectedItem("Paolo");
            nomeCittadino.setText("");
            cognomeCittadino.setText("");
            email.setText("");
            userID.setText("");
            password.setText("");
            ripetiPassword.setText("");
            IDUnivoco.setText("");
            status.setText("");
            showPassword.setSelected(false);

        }else if(e.getSource() == showPassword){
            if (showPassword.isSelected()) {
                 password.setEchoChar((char) 0);
                 ripetiPassword.setEchoChar((char) 0);
            }
            else{
                password.setEchoChar('*');
                ripetiPassword.setEchoChar('*');
            }
        }
    }
}
