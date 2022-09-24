package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import UI.graphics.RoundJTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


/**
 *
 * @author Paolo Bruscagin
 * @author Alessandro Cassani
 */

public class UIRegisterCitizen extends JFrame implements ActionListener {

    JComboBox<String> nomeCV = new JComboBox<>(new String[]{"Paolo", "Damiano", "Alessandro", "Luca"}); // ricerca in db

    RoundJTextField nomeCittadino = new RoundJTextField(30);

    RoundJTextField cognomeCittadino = new RoundJTextField(30);

    RoundJTextField email = new RoundJTextField(30);

    RoundJTextField userID =new RoundJTextField(20);

    RoundJTextField password = new RoundJTextField(20);

    RoundJTextField ripetiPassword = new RoundJTextField(20);

    RoundJTextField IDUnivoco = new RoundJTextField(16);

    JButton registraCittadino = new JButton("REGISTRA");

    JButton pulisci;

    JCheckBox showPassword = new JCheckBox("show password");

    JButton backToCitizen;

    JLabel status = new JLabel();

    public UIRegisterCitizen(){

        Border bordobtn = new LineBorder(new Color(0,49,83), 4, true);
        Border bordobtnInd = new LineBorder(new Color(169,50, 38), 2, true);

        JLabel titoloCittadino = new JLabel("Registrati presso un Centro Vaccinale");
        titoloCittadino.setFont(new Font("Georgia", Font.BOLD, 20));
        add(titoloCittadino).setBounds(320, 0, 550, 75);

        JLabel labelnomeCit = new JLabel("Nome:");
        labelnomeCit.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelnomeCit).setBounds(260, 40, 550, 75);

        nomeCittadino.setFont(new Font("Arial", Font.ITALIC, 20));
        nomeCittadino.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        nomeCittadino.setPreferredSize(new Dimension(325, 75));
        nomeCittadino.setBounds(160, 95, 250, 50);
        nomeCittadino.setHorizontalAlignment(JTextField.CENTER);
        nomeCittadino.setEchoChar((char) 0);

        JLabel labelcognomeCit = new JLabel("Cognome:");
        labelcognomeCit.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelcognomeCit).setBounds(630, 40, 550, 75);

        cognomeCittadino.setFont(new Font("Arial", Font.ITALIC, 20));
        cognomeCittadino.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        cognomeCittadino.setPreferredSize(new Dimension(325, 75));
        cognomeCittadino.setBounds(540, 95, 250, 50);
        cognomeCittadino.setHorizontalAlignment(JTextField.CENTER);
        cognomeCittadino.setEchoChar((char) 0);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelEmail).setBounds(260, 150, 550, 75);

        email.setFont(new Font("Arial", Font.ITALIC, 20));
        email.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        email.setPreferredSize(new Dimension(325, 75));
        email.setBounds(160, 200, 250, 50);
        email.setHorizontalAlignment(JTextField.CENTER);
        email.setEchoChar((char) 0);

        JLabel labelUserID = new JLabel("User ID:");
        labelUserID.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelUserID).setBounds(630, 150, 550, 75);

        userID.setFont(new Font("Arial", Font.ITALIC, 20));
        userID.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        userID.setPreferredSize(new Dimension(325, 75));
        userID.setBounds(540, 200, 250, 50);
        userID.setHorizontalAlignment(JTextField.CENTER);
        userID.setEchoChar((char) 0);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelPassword).setBounds(240, 255, 550, 75);

        password.setFont(new Font("Arial", Font.ITALIC, 20));
        password.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        password.setBounds(160, 305, 250, 50);
        password.setBackground(Color.WHITE);
        password.setHorizontalAlignment(JTextField.CENTER);


        JLabel labelRipetiPassword = new JLabel("Ripeti Password:");
        labelRipetiPassword.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelRipetiPassword).setBounds(600, 255, 550, 75);

        ripetiPassword.setFont(new Font("Arial", Font.ITALIC, 20));
        ripetiPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        ripetiPassword.setBounds(540, 305, 250, 50);
        ripetiPassword.setBackground(Color.WHITE);
        ripetiPassword.setHorizontalAlignment(JTextField.CENTER);

        showPassword.setFont(new Font("Arial", Font.BOLD, 15));
        showPassword.setBackground(new Color(181, 226, 232));
        showPassword.setBounds(820,325,160,15);
        showPassword.addActionListener(this);

        JLabel labelInfopsw = new JLabel("a\nb\nc\ne"); // serve per i requisiti password
        labelInfopsw.setFont(new Font("Georgia", Font.BOLD, 8));
        labelInfopsw.setForeground(new Color(0xEC0909));
        add(labelInfopsw).setBounds(550, 375, 100, 150);

        JLabel labelIDUnivoco = new JLabel("ID Vaccinazione:");
        labelIDUnivoco.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelIDUnivoco).setBounds(220, 360, 550, 75);

        IDUnivoco.setFont(new Font("Arial", Font.ITALIC, 20));
        IDUnivoco.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(65, 102, 245)));
        IDUnivoco.setBounds(160, 410, 250, 50);
        IDUnivoco.setBackground(Color.WHITE);
        IDUnivoco.setHorizontalAlignment(JTextField.CENTER);
        IDUnivoco.setEchoChar((char) 0);

        JLabel labelNome = new JLabel("Nome Centro Vaccinale:");
        labelNome.setFont(new Font("Georgia", Font.ITALIC, 17));
        add(labelNome).setBounds(570, 360, 550, 75);

        nomeCV.setFont(new Font("Arial", Font.ITALIC, 20));
        nomeCV.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(65, 102, 245)));
        nomeCV.setBounds(540, 410, 250, 50);
        nomeCV.setBackground(Color.WHITE);
        ((JLabel)nomeCV.getRenderer()).setHorizontalAlignment(JLabel.RIGHT);
        AutoCompleteDecorator.decorate(nomeCV);

        registraCittadino.setBounds(410, 485, 190, 60);
        registraCittadino.setFont(new Font("Georgia", Font.BOLD, 17));
        registraCittadino.setBackground(new Color(0,0,128));
        registraCittadino.setForeground(Color.WHITE);
        registraCittadino.setBorder(bordobtn);
        registraCittadino.setFocusable(false);
        registraCittadino.addActionListener(this);
        registraCittadino.setOpaque(true);

        status.setFont(new Font("Georgia", Font.BOLD, 18));
        status.setBounds(75, 700, 400, 75);

        backToCitizen = new JButton("INDIETRO");
        backToCitizen.setBounds(70, 485, 190 , 60);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 17));
        backToCitizen.setBackground(new Color(248, 9, 55));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnInd);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);

        pulisci = new JButton("PULISCI");
        pulisci.setBounds(750, 485, 190, 60);
        pulisci.setFont(new Font("Georgia", Font.BOLD, 17));
        pulisci.setBackground(new Color(0xEF0808));
        pulisci.setForeground(Color.WHITE);
        pulisci.setBorder(bordobtnInd);
        pulisci.setFocusable(false);
        pulisci.addActionListener(this);
        pulisci.setOpaque(true);

        setLayout(null);
        add(nomeCV);
        add(nomeCittadino);
        add(cognomeCittadino);
        add(email);
        add(userID);
        add(password);
        add(showPassword);
        add(ripetiPassword);
        add(IDUnivoco);
        add(registraCittadino);
        add(status);
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
