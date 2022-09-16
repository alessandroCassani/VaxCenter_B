package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UILoginToServer extends JFrame implements ActionListener {

    JTextField host = new JTextField();

    JTextField user = new JTextField();

    JTextField psw = new JTextField();


    public UILoginToServer(){
        Border bordo = new LineBorder(new Color(0x808080, true), 2, true);
        host.setFont(new Font("Arial",Font.ITALIC,2));
        user.setFont(new Font("Arial",Font.ITALIC,2));
        psw.setFont(new Font("Arial",Font.ITALIC,2));

    }

















    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
