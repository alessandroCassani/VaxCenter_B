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
 */

public class UIRegisterCitizen extends JFrame implements ActionListener {

    JComboBox<String> nomeCV = new JComboBox<>(new String[]{"Paolo", "Damiano", "Alessandro", "Luca",}); // da fare in modo diverso

    JTextField nomeCittadino = new JTextField(30);

    JTextField cognomeCittadino = new JTextField(30);

    JTextField email = new JTextField(30);

    JTextField userID =new JTextField(20);

    JPasswordField password = new JPasswordField(20);

    JPasswordField ripetiPassword = new JPasswordField(20);

    JTextField IDUnivoco = new JTextField(16);

    JButton registraCittadino = new JButton("REGISTRA");

    JButton pulisci;

    JCheckBox showPassword = new JCheckBox("show password");

    //JCheckBox showRipetiPassword = new JCheckBox("show password");


    JButton backToCitizen;

    JLabel status = new JLabel();

    public UIRegisterCitizen(){

        Border bordo = new LineBorder(new Color(0xFF000000, true), 2, true);
        Border bordobtn = new LineBorder(new Color(0xFF37C47A, true), 4, true);
        Border bordobtnInd = new LineBorder(new Color(0xFFF68E3B, true), 4, true);


        JLabel tiotoloCittadino = new JLabel("Registrati presso un Centro Vaccinale:");
        tiotoloCittadino.setFont(new Font("Georgia", Font.BOLD, 20));
        add(tiotoloCittadino).setBounds(50, 10, 550, 75);

        JLabel labelNome = new JLabel("Nome Centro Vaccinale");
        labelNome.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelNome).setBounds(50, 55, 550, 75);

        nomeCV.setFont(new Font("Arial", Font.BOLD, 20));
        nomeCV.setBorder(bordo);
        nomeCV.setBounds(50, 100, 675, 75);
        nomeCV.setBackground(Color.WHITE);
        AutoCompleteDecorator.decorate(nomeCV);

        JLabel labelnomeCit = new JLabel("Nome");
        labelnomeCit.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelnomeCit).setBounds(50, 155, 550, 75);

        nomeCittadino.setFont(new Font("Arial", Font.BOLD, 20));
        nomeCittadino.setBorder(bordo);
        nomeCittadino.setPreferredSize(new Dimension(325, 75));
        nomeCittadino.setBounds(50, 200, 325, 75);

        JLabel labelcognomeCit = new JLabel("Cognome");
        labelcognomeCit.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelcognomeCit).setBounds(400, 155, 550, 75);

        cognomeCittadino.setFont(new Font("Arial", Font.BOLD, 20));
        cognomeCittadino.setBorder(bordo);
        cognomeCittadino.setPreferredSize(new Dimension(325, 75));
        cognomeCittadino.setBounds(400, 200, 325, 75);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelEmail).setBounds(50, 255, 550, 75);

        email.setFont(new Font("Arial", Font.BOLD, 20));
        email.setBorder(bordo);
        email.setPreferredSize(new Dimension(325, 75));
        email.setBounds(50, 300, 375, 75);

        JLabel labelUserID = new JLabel("User ID");
        labelUserID.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelUserID).setBounds(450, 255, 550, 75);

        userID.setFont(new Font("Arial", Font.BOLD, 20));
        userID.setBorder(bordo);
        userID.setPreferredSize(new Dimension(325, 75));
        userID.setBounds(450, 300, 275, 75);

        JLabel labelInfopsw = new JLabel("a\nb\nc\ne"); // serve per i requisiti password
        labelInfopsw.setFont(new Font("Georgia", Font.BOLD, 8));
        labelInfopsw.setForeground(new Color(0xEC0909));
        add(labelInfopsw).setBounds(550, 340, 100, 150);


        JLabel labelPassword = new JLabel("Password");
        labelPassword.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelPassword).setBounds(50, 355, 550, 75);

        password.setFont(new Font("Arial", Font.BOLD, 20));
        password.setBorder(bordo);
        password.setBounds(50, 400, 325, 75);
        password.setBackground(Color.WHITE);

        showPassword.setFont(new Font("Arial", Font.BOLD, 15));
        showPassword.setBounds(413,487,160,15);
        showPassword.addActionListener(this);

        JLabel labelRipetiPassword = new JLabel("Ripeti Password");
        labelRipetiPassword.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelRipetiPassword).setBounds(50, 455, 550, 75);

        ripetiPassword.setFont(new Font("Arial", Font.BOLD, 20));
        ripetiPassword.setBorder(bordo);
        ripetiPassword.setBounds(50, 500, 325, 75);
        ripetiPassword.setBackground(Color.WHITE);


        JLabel labelIDUnivoco = new JLabel("ID Univoco (fornito al momento della vaccinazione)");
        labelIDUnivoco.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelIDUnivoco).setBounds(50, 555, 550, 75);

        IDUnivoco.setFont(new Font("Arial", Font.BOLD, 20));
        IDUnivoco.setBorder(bordo);
        IDUnivoco.setBounds(50, 600, 375, 75);
        IDUnivoco.setBackground(Color.WHITE);

        registraCittadino.setBounds(500, 600, 225, 75);
        registraCittadino.setFont(new Font("Georgia", Font.BOLD, 20));
        registraCittadino.setBackground(new Color(0x07AF45));
        registraCittadino.setForeground(Color.WHITE);
        registraCittadino.setBorder(bordobtn);
        registraCittadino.setFocusable(false);
        registraCittadino.addActionListener(this);
        registraCittadino.setOpaque(true);

        status.setFont(new Font("Georgia", Font.BOLD, 18));
        status.setBounds(75, 700, 225, 75);


        ImageIcon ind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/iconaindietro.png")));

        backToCitizen =  new JButton("     INDIETRO", ind);
        backToCitizen.setBounds(1075, 670, 350, 120);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 20));
        backToCitizen.setBackground(new Color(0xFA4723));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnInd);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);

        pulisci = new JButton("PULISCI");
        pulisci.setBounds(875, 670, 170, 120);
        pulisci.setFont(new Font("Georgia", Font.BOLD, 15));
        pulisci.setBackground(new Color(0xEF0808));
        pulisci.setForeground(Color.WHITE);
        pulisci.setBorder(bordobtnInd);
        pulisci.setFocusable(false);
        pulisci.addActionListener(this);
        pulisci.setOpaque(true);

        setBounds(0, 0, 1600, 900);
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

        setTitle("Registrazione");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setForeground(Color.WHITE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backToCitizen) {
            this.dispose();
            new UICitizen();
        } else if (e.getSource() == registraCittadino) {
            if (!nomeCittadino.getText().equals("")){
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
