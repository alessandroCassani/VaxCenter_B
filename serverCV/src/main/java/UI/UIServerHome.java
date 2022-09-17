 package UI;

 import javax.swing.*;
 import javax.swing.border.Border;
 import javax.swing.border.LineBorder;
 import java.awt.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;

 /**
  * classe che rappresenta l'interfaccia grafica che permette di gestire l'accensione e lo spegnimento del server
  * @author Alessandro Cassani
  */
 public class UIServerHome extends JFrame implements ActionListener {

     /**
      * bottone di accensione server
      */
    JButton startBtn = new JButton("START");

     /**
      * campo di testo per UI per informare l'utente sullo scopo dell'interfaccia grafica
      */
    JLabel textField = new JLabel("manage the server!");

     /**
      * bottone di spegnimento dle server
      */
    JButton stopBtn = new JButton("STOP");

     /**
      * container di interfaccia
      */
    Container container = getContentPane();

     /**
      *campo di testo in cui viene visuaizzato lo stato del server (on/off)
      */
    JLabel status = new JLabel();

     /**
      * costruttore che contiene le informazioni per il caricamento dei componenti di interfaccia grafica
      *
      * @author Alessandro Cassani
      */
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

     /**
      * metodo che permette di gestire gli eventi associati ai listener dei componenti di UI attivati dall'utente
      * @param e the event to be processed
      *
      * @author Alessandro Cassani
      */
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
