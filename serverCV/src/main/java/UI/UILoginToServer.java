package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UILoginToServer extends JFrame implements ActionListener {

    JLabel host = new JLabel();
    JLabel user = new JLabel();

    JPanel pnl = new JPanel();

    JPasswordField psw = new JPasswordField();

    public static void main(String[] args) {
        new UILoginToServer();
    }

    public UILoginToServer(){
        Border bordo = new LineBorder(new Color(0x808080, true), 2, true);
        host.setFont(new Font("Arial",Font.ITALIC,2));

        host.setFont(new Font("Arial",Font.ITALIC,20));
        host.setText("host: localhost");
        host.setBounds(400,200,150,20);
        host.setBorder(bordo);

        user.setFont(new Font("Arial",Font.ITALIC,20));
        user.setText("user: postgres");
        user.setBounds(400,250,150,20);
        user.setBorder(bordo);

        //psw.setFont(new Font("Arial",Font.ITALIC,20));
        psw.setText("password");
        psw.setBounds(400,300,150,20);
        psw.setBorder(bordo);


        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        JLabel imagine = new JLabel(logo);


        JLabel sfondo = new JLabel();
        sfondo.add(user);
        sfondo.add(host);
        sfondo.add(psw);
        sfondo.setIcon(logo);
        psw.setFont(new Font("Arial",Font.ITALIC,2));

        this.add(sfondo,BorderLayout.CENTER);
        setSize(1000,600);

        this.setTitle("Server VaxCenter");
        this.setSize(1000,600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

















    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
