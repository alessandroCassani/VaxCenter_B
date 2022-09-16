package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIServerHome extends JFrame implements ActionListener {

    JButton startBtn = new JButton("START");

    JLabel textField = new JLabel("manage the server!");

    JButton stopBtn = new JButton("STOP");

    Container container = getContentPane();

    public UIServerHome(){
        Border bordo = new LineBorder(new Color(0x808080, true), 2, true);

        container.setLayout(null);

        textField.setFont(new Font("Arial",Font.ITALIC,50));
        textField.setBounds(260,80,600,100);
        textField.setBorder(bordo);

        startBtn.setBorder(bordo);
        startBtn.setBounds(225,250,130,60);
        startBtn.setBackground(Color.GREEN);
        startBtn.setOpaque(true);

        stopBtn.setBorder(bordo);
        stopBtn.setBounds(635,250,130,60);
        stopBtn.setBackground(Color.red);
        stopBtn.setOpaque(true);

        container.add(startBtn);
        container.add(stopBtn);
        container.add(textField);


        this.setTitle("Server Home");
        this.setSize(1000,600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UIServerHome();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
