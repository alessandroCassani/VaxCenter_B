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

    JComboBox<String> nomeCV = new JComboBox<>(new String[]{"Paolo", "Damiano", "Alessandro", "Luca"}); // da fare in modo diverso

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


        JLabel titoloCittadino = new JLabel("Registrati presso un Centro Vaccinale:");
        titoloCittadino.setFont(new Font("Georgia", Font.BOLD, 20));
        add(titoloCittadino).setBounds(320, 0, 550, 75);



        JLabel labelnomeCit = new JLabel("Nome:");
        labelnomeCit.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelnomeCit).setBounds(160, 40, 550, 75);

        nomeCittadino.setFont(new Font("Arial", Font.BOLD, 20));
        nomeCittadino.setBorder(bordo);
        nomeCittadino.setPreferredSize(new Dimension(325, 75));
        nomeCittadino.setBounds(160, 95, 250, 55);

        JLabel labelcognomeCit = new JLabel("Cognome:");
        labelcognomeCit.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelcognomeCit).setBounds(540, 40, 550, 75);

        cognomeCittadino.setFont(new Font("Arial", Font.BOLD, 20));
        cognomeCittadino.setBorder(bordo);
        cognomeCittadino.setPreferredSize(new Dimension(325, 75));
        cognomeCittadino.setBounds(540, 95, 250, 55);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelEmail).setBounds(160, 150, 550, 75);

        email.setFont(new Font("Arial", Font.BOLD, 20));
        email.setBorder(bordo);
        email.setPreferredSize(new Dimension(325, 75));
        email.setBounds(160, 200, 250, 55);

        JLabel labelUserID = new JLabel("User ID:");
        labelUserID.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelUserID).setBounds(540, 150, 550, 75);

        userID.setFont(new Font("Arial", Font.BOLD, 20));
        userID.setBorder(bordo);
        userID.setPreferredSize(new Dimension(325, 75));
        userID.setBounds(540, 200, 250, 55);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelPassword).setBounds(160, 255, 550, 75);

        password.setFont(new Font("Arial", Font.BOLD, 20));
        password.setBorder(bordo);
        password.setBounds(160, 305, 250, 55);
        password.setBackground(Color.WHITE);


        JLabel labelRipetiPassword = new JLabel("Ripeti Password:");
        labelRipetiPassword.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelRipetiPassword).setBounds(540, 255, 550, 75);

        ripetiPassword.setFont(new Font("Arial", Font.BOLD, 20));
        ripetiPassword.setBorder(bordo);
        ripetiPassword.setBounds(540, 305, 250, 55);
        ripetiPassword.setBackground(Color.WHITE);

        showPassword.setFont(new Font("Arial", Font.BOLD, 15));
        showPassword.setBounds(820,325,160,15);
        showPassword.addActionListener(this);


        JLabel labelInfopsw = new JLabel("a\nb\nc\ne"); // serve per i requisiti password
        labelInfopsw.setFont(new Font("Georgia", Font.BOLD, 8));
        labelInfopsw.setForeground(new Color(0xEC0909));
        add(labelInfopsw).setBounds(550, 375, 100, 150);

        JLabel labelIDUnivoco = new JLabel("ID Vaccinazione:");
        labelIDUnivoco.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelIDUnivoco).setBounds(160, 360, 550, 75);

        IDUnivoco.setFont(new Font("Arial", Font.BOLD, 20));
        IDUnivoco.setBorder(bordo);
        IDUnivoco.setBounds(160, 410, 250, 55);
        IDUnivoco.setBackground(Color.WHITE);


        JLabel labelNome = new JLabel("Nome Centro Vaccinale:");
        labelNome.setFont(new Font("Georgia", Font.BOLD, 12));
        add(labelNome).setBounds(540, 360, 550, 75);

        nomeCV.setFont(new Font("Arial", Font.BOLD, 20));
        nomeCV.setBorder(bordo);
        nomeCV.setBounds(540, 410, 250, 55);
        nomeCV.setBackground(Color.WHITE);
        AutoCompleteDecorator.decorate(nomeCV);

        registraCittadino.setBounds(410, 485, 190, 65);
        registraCittadino.setFont(new Font("Georgia", Font.BOLD, 17));
        registraCittadino.setBackground(new Color(0x07AF45));
        registraCittadino.setForeground(Color.WHITE);
        registraCittadino.setBorder(bordobtn);
        registraCittadino.setFocusable(false);
        registraCittadino.addActionListener(this);
        registraCittadino.setOpaque(true);

        status.setFont(new Font("Georgia", Font.BOLD, 18));
        status.setBounds(75, 700, 400, 75);


        backToCitizen = new JButton("INDIETRO");
        backToCitizen.setBounds(70, 485, 190 , 65);
        backToCitizen.setFont(new Font("Georgia", Font.BOLD, 17));
        backToCitizen.setBackground(new Color(0xFA4723));
        backToCitizen.setForeground(Color.WHITE);
        backToCitizen.setBorder(bordobtnInd);
        backToCitizen.setFocusable(false);
        backToCitizen.addActionListener(this);
        backToCitizen.setOpaque(true);

        pulisci = new JButton("PULISCI");
        pulisci.setBounds(750, 485, 190, 65);
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
