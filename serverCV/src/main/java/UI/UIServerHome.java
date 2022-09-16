package UI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class UIServerHome extends JFrame implements ActionListener {

    JButton startBtn = new JButton("START");

    JLabel textField = new JLabel("manage the server!");

    JButton stopBtn = new JButton("STOP");

    Container container = getContentPane();

    JLabel status = new JLabel();

    public UIServerHome(){
        Border bordo = new LineBorder(new Color(0x808080, true), 2, true);

        container.setLayout(null);

        textField.setFont(new Font("Arial",Font.ITALIC,50));
        textField.setBounds(260,80,600,100);
        textField.setBorder(bordo);

        status.setFont(new Font("Arial",Font.ITALIC,20));
        status.setBounds(420,400,200,30);
        status.setBorder(bordo);

        startBtn.setBorder(bordo);
        startBtn.setBounds(225,250,130,60);
        startBtn.setBackground(Color.GREEN);
        startBtn.setOpaque(true);
        startBtn.addActionListener(this);

        stopBtn.setBorder(bordo);
        stopBtn.setBounds(635,250,130,60);
        stopBtn.setBackground(Color.red);
        stopBtn.setOpaque(true);
        stopBtn.addActionListener(this);


        container.add(startBtn);
        container.add(stopBtn);
        container.add(textField);
        container.add(status);


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

        if(e.getSource() == startBtn){
            // caricamento oggetto server nel registry
             status.setText("server running...");
        }

        if(e.getSource() == stopBtn){
            //eliminazione oggetto server dal registry
            status.setText("server offline!");

        }

    }
}
