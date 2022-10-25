package UI;

import UI.graphics.GradientPanel;
import UI.graphics.InfoSearch;
import UI.graphics.SearchField;
import database.ServerInterface;
import util.CentroVaccinale;
import util.Tipologia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 * La classe UISearchVaxCenter permette di ricercare i centri vaccinale tramite due tipologie di RICERCA:
 *   - Ricerca per nome
 *   - Ricerca per comune e tipologia
 *
 * @author Damiano Ficara
 * @author Paolo Bruscagin
 */
public class UISearchVaxCenter extends JFrame {

    /**
     * Creates new form UISearchVaxCenter
     */
    public UISearchVaxCenter() {
        initComponents();
        // pernette du evidenziare il tipo di ricerca compiuta
        jComboBox1.setVisible(false);
        search.addEventOptionSelected((option, index) -> search.setHint("Ricerca per " + option.getName() + "..."));
        search.addOption(new InfoSearch("Nome", new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/nome.png")))));
        ImageIcon ic = new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/cityhall.png")));
        ic = resizeImage(ic,20,20);
        search.addOption(new InfoSearch("Comune e Tipologia",  ic));
        search.setSelectedIndex(0); // evidenzia primo elemento
        jTable1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,12));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(32,136,203));
        jTable1.getTableHeader().setForeground(new Color(255,255,255));
        jTable1.setRowHeight(25);
        setSize(1000,600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Ricerca Centro Vaccinale - Info");
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/logo.png")));
        setIconImage(logo.getImage());
        setResizable(false);

        //Popup "Se sicuro di uscire?"
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                UIManager.put("OptionPane.yesButtonText", "Si");
                UIManager.put("OptionPane.noButtonText", "No");

                int resp = JOptionPane.showConfirmDialog(null, "Sei sicuro di uscire?",
                        "Esci?", JOptionPane.YES_NO_OPTION);

                if (resp == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    System.exit(0);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });




    }

    /**
     *
     *Questo metodo viene chiamato dall'interno del costruttore per inizializzare il form.
     *ATTENZIONE: NON modificare questo codice. Il contenuto di questo metodo è sempre
     *rigenerato dal Forditor.
     *
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        var a = new LinkedList<CentroVaccinale>();
        jPanel1 = new GradientPanel(Color.decode("#cad0ff"),Color.decode("#e3e3e3"));
        search = new SearchField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        try {
             a = ServerPointer.getStub().getCentriVaccinali();
             loadData(a,new Object[indici.length]);
        }catch (Exception e) {
            e.printStackTrace();
        }

        jTable1.setFont(new Font("sansserif", Font.BOLD, 15));
        jTable1.setFocusable(false);
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(232, 47, 95));
        jTable1.setShowHorizontalLines(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jScrollPane1.setOpaque(false);

        jScrollPane1.getViewport().setOpaque(false);
        jTable1.setShowGrid(false);


        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/color50ind.png")));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                                                .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox1))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

         }// </editor-fold>

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {
        this.dispose();
        new UICitizen();
    }

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {
        var a = new LinkedList<CentroVaccinale>();
        if(search.isSelected()) {

            int option = search.getSelectedIndex();
            String info = "%" + search.getText().trim() + "%";
            if(option == 0) {
                jComboBox1.setVisible(false);
                try {
                     a = ServerPointer.getStub().getCentriVaccinali(info);
                    loadData(a,new Object[indici.length]);
                }catch (Exception e) {
                    e.printStackTrace();

                }

            } else if(option == 1) {
                jComboBox1.setVisible(true);
                String tipologiaCentro  = Objects.requireNonNull(jComboBox1.getSelectedItem()).toString();
                try {
                     a = ServerPointer.getStub().getCentriVaccinali(info,Tipologia.getTipo(tipologiaCentro));
                    loadData(a,new Object[indici.length]);
                }catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }
    }

    private void loadData(LinkedList<CentroVaccinale> list, Object[] tuple) {
        DefaultTableModel model = new DefaultTableModel();
        jTable1.setModel(model);
        model.setColumnIdentifiers(indici);

        for (CentroVaccinale c : list) {
            tuple[0] = c.getNome();
            tuple[1] = c.getComune().toLowerCase();
            tuple[2] = c.getQualificatore();
            tuple[3] = c.getNomeVia();
            tuple[4] = c.getCivico();
            tuple[5] = c.getProvincia();
            tuple[6] = c.getCap();
            tuple[7] = c.getTipologia();
            model.addRow(tuple);
        }
    }

    public ImageIcon resizeImage(ImageIcon ic, int x, int y) {
        Image img = ic.getImage() ;
        Image newimg = img.getScaledInstance( x, y,  java.awt.Image.SCALE_SMOOTH ) ;
        ic = new ImageIcon( newimg );
        return ic;
    }


    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private SearchField search;
    JComboBox<String> jComboBox1 = new JComboBox(new String[]{"","HUB", "OSPEDALIERO", "AZIENDALE"});
    private String[] indici = {"Nome", "Comune", "Qualificatore", "Via", "Civico", "Sigla", "Cap", "Tipologia"};


    /**
     * metodo che permette di gestire gli eventi associati ai listener dei componenti di UI attivati dall'utente
     * @param e the event to be processed
     *
     * @author Paolo Bruscagin
     */



}