package UI;

import UI.graphics.GradientPanel;
import UI.graphics.InfoSearch;
import UI.graphics.SearchField;
import util.CentroVaccinale;
import util.Tipologia;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


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
     * costruttore che permette la creazione dei componenti di interfaccia grafica della schermata di ricerca dei centri vaccinali
     *
     * @author Paolo Bruscagin
     * @author Damiano Ficara
     */

    public UISearchVaxCenter() {
        initComponents();
        jComboBox1.setVisible(false);
        // pernette du evidenziare il tipo di ricerca compiuta
        search.addEventOptionSelected(
                (option, index) -> {
                    search.setHint("Ricerca per " + option.getName() + "...");
                    if(option.getName().equals("Comune e Tipologia")) {
                        jComboBox1.setVisible(true);
                    } else {
                        jComboBox1.setVisible(false);
                    }
                }

        );

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
        panel = new GradientPanel(Color.decode("#cad0ff"),Color.decode("#e3e3e3"));
        search = new SearchField();
        back = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
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
        scrollPane.setViewportView(jTable1);
        scrollPane.setOpaque(false);

        scrollPane.getViewport().setOpaque(false);
        jTable1.setShowGrid(false);


        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/color50ind.png")));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE)
                                                .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox1))
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

    }// </editor-fold>

    /**
     * Metodo che permette di tornare alla schermata precedente mediante la freccia apposita
     * @param evt gestione dell'evento collegato al mouse
     * @author Damiano Ficara
     */
    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {
        this.dispose();
        new UICitizen();
    }

    /**
     * Metodo che permette di gestire le azioni legate alla box di ricerca con richiamo ai metodi del server
     * @param evt gestione dell'evento collegato al mouse
     * @author Damiano Ficara
     */
    private void searchKeyReleased(java.awt.event.KeyEvent evt) {
        var a = new LinkedList<CentroVaccinale>();
        if(search.isSelected()) {

            int option = search.getSelectedIndex();
            String info = "%" + search.getText().trim() + "%";
            if(option == 0) {

                try {
                    System.out.println(info);
                    a = ServerPointer.getStub().getCentriVaccinali(info.toUpperCase());
                    System.out.println(a);
                    loadData(a,new Object[indici.length]);
                }catch (Exception e) {
                    e.printStackTrace();

                }

            } else if(option == 1) {
                String tipologiaCentro  = Objects.requireNonNull(jComboBox1.getSelectedItem()).toString();
                try {
                    System.out.println(info.toUpperCase());
                    System.out.println(tipologiaCentro);
                    a = ServerPointer.getStub().getCentriVaccinali(info.toUpperCase(),Tipologia.getTipo(tipologiaCentro));
                    System.out.println(a);
                    loadData(a,new Object[indici.length]);
                }catch (Exception e) {
                    e.printStackTrace();

                }

            }
        }
    }
    /**
     * Metodo privato che permette di prelevare i dati dal db con l'utilizzo dei metodi di RMI
     * @param list lista dei centri vaccinali presenti nel database
     * @param tuple oggetto che rappresenta una singola riga della tabella
     * @author Damiano Ficara
     */
    private void loadData(LinkedList<CentroVaccinale> list, Object[] tuple) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(model);
        model.setColumnIdentifiers(indici);

        for (CentroVaccinale c : list) {
            tuple[0] = c.getNome();
            tuple[1] = c.getComune();
            tuple[2] = c.getQualificatore();
            tuple[3] = c.getNomeVia();
            tuple[4] = c.getCivico();
            tuple[5] = c.getProvincia();
            tuple[6] = c.getCap();
            tuple[7] = c.getTipologia();
            model.addRow(tuple);
        }
    }

    /**
     * Metodo che permette di modificare la dimensione dell'immagine
     * @param ic immagine in ingresso
     * @param x cordinata relativa alle ascisse
     * @param y cordinata relativa alle ordinate
     * @author Damiano Ficara
     */
    private ImageIcon resizeImage(ImageIcon ic, int x, int y) {
        Image img = ic.getImage() ;
        Image newimg = img.getScaledInstance( x, y,  java.awt.Image.SCALE_SMOOTH ) ;
        ic = new ImageIcon( newimg );
        return ic;
    }

    UISearchVaxCenterDialog jtRowData = new UISearchVaxCenterDialog();

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();

        String[] prospetto = new String[0];

        String nome = model.getValueAt(index, 0).toString();
        try {
            prospetto = ServerPointer.getStub().getProspettoRiassuntivo(nome);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        jtRowData.setVisible(true);
        jtRowData.pack();
        jtRowData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        jtRowData.jLabelinsNome.setText(nome);
        jtRowData.jLabelMalDiTesta.setText(prospetto[0]);
        jtRowData.jLabelfebbre.setText(prospetto[1]);
        jtRowData.jLabelTachicardia.setText(prospetto[2]);
        jtRowData.jLabeldolori.setText(prospetto[3]);
        jtRowData.jLabelLinfo.setText(prospetto[4]);
        jtRowData.jLabelcrisi.setText(prospetto[5]);

    }


    /**
     * Label per inserire l'immagine per tornare alla schermata precedente
     */
    private JLabel back;
    /**
     * Panel che contiene i componenti relativi all'interfaccia
     */
    private GradientPanel panel;
    /**
     * ScrollPane che contiene la tabella e permette di scorrere tra di essa
     */
    private JScrollPane scrollPane;
    /**
     * Tabella contenente i centri vaccinali
     */
    private JTable jTable1;
    /**
     * Campo che permette di aggiungere filtri di ricerca
     */
    private SearchField search;
    /**
     * Box di possibili selezioni della tipologia dei centri vaccinali
     */
    JComboBox<String> jComboBox1 = new JComboBox(new String[]{"","HUB", "OSPEDALIERO", "AZIENDALE"});
    /**
     * Intestazione dei campi della tabella
     */
    private String[] indici = {"Nome", "Comune", "Qualificatore", "Via", "Civico", "Sigla", "Cap", "Tipologia"};



}