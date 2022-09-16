package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UILoginToServer extends JFrame implements ActionListener {

    JLabel hostLabel = new JLabel("host:");

    JLabel hostName = new JLabel("localhost");
    JLabel userLabel = new JLabel("username:");

    JTextField userTextField = new JTextField();

    JPasswordField pswTextField = new JPasswordField();

    JLabel pswLabel = new JLabel("password:");

    JButton loginButton=new JButton("LOGIN");

    Container container = getContentPane();

    public static void main(String[] args) {
        new UILoginToServer();
    }

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
        userTextField.setBorder(bordo);


        pswLabel.setFont(new Font("Arial",Font.ITALIC,20));
        pswLabel.setBounds(600,300,100,20);
        pswLabel.setBorder(bordo);

        pswTextField.setFont(new Font("Arial",Font.BOLD,20));
        pswTextField.setBounds(750,300,100,30);
        pswTextField.setBorder(bordo);

        loginButton.setBounds(675,380,90,40);





        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        JLabel background = new JLabel(logo);

        container.add(hostLabel);
        container.add(hostName);
        container.add(userLabel);
        container.add(pswLabel);
        container.add(userTextField);
        container.add(pswTextField);
        container.add(loginButton);



        setSize(1000,600);

        this.setTitle("login to server");
        this.setSize(1000,600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

















    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
