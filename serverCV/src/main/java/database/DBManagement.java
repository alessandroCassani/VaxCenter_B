package database;

import database.UI.UILoginToServer;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.*;

/**
 * La classe DBManagement permette di creare la connessione al server Postgres, il database e le tabelle
 *
 * @author Alessandro Cassani - Matricola: 744512 - Sede: VA
 * @author Paolo Bruscagin - Matricola: 744703 - Sede: VA
 * @author Damiano Ficara - Matricola 744958 - Sede: VA
 * @author Luca Perfetti - Matricola 746581 - Sede: VA
 *
 */

public class DBManagement {
    /**
     * Nome del database
     */
    static String nameDB = "vaxcenter";

    /**
     * Rappresenta l'istanza corrente del databse
     */
    static DBManagement instanceDB = null;

    /**
     * Indirizzo di rete del dbms
     */
    static String hostDB = "localhost";

    /**
     * Porta del server Postgres
     */
    static int portDB = 5432;

    /**
     * Username dell'utente per accedere al server Postgres
     */
    static String userDB;

    /**
     * Password dell'utente per accedere al server Postgres
     */
    static String passwordDB;

    /**
     * Oggetto per gestire la connessione
     */
    public static Connection connection = null;

    /**
     * Url per la connessione al server Postgres
     */
    static String url = "jdbc:postgresql://"+ hostDB + ":" + portDB + "/";

    /**
     * Metodo che ritorna l'istanza del database
     *
     * @author Luca Perfetti
     */
    public static DBManagement getDB(){
        if(instanceDB == null) {
            instanceDB = new DBManagement();
           connect(UILoginToServer.getHostTextField(), UILoginToServer.getPortTextField(), UILoginToServer.getUserTextField(),
                   UILoginToServer.getPswTextField()
           );
        }
        return instanceDB;
    }

    /**
     * Metodo che crea la connessione al server Postgres tramite il driver JDBC
     *
     * @param host host del database
     * @param port porta del database
     * @param user username per accedere al server di Postgres
     * @param password password per accedere al server di Postgres
     * @return true o false, in base all'esito dell'operazione
     *
     * @author Luca Perfetti
     */
    public static boolean connect(String host, int port, String user, String password){
        hostDB = host;
        portDB = port;
        userDB = user;
        passwordDB = password;

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url + nameDB, userDB, passwordDB);
            if(connection==null) {
                createTable();
                insertDataSet();
                return true;
            }
        }catch (Exception e){
            createDB();
            return false;
        }
        return true;
    }

    /**
     * Metodo che permette di creare il database con le relative tabelle nel caso in cui quest'ultimo non esiste già
     *
     * @return true o false, in base all'esito dell'operazione
     *
     * @author Luca Perfetti
     */
    private static boolean createDB(){
        PreparedStatement preparedstmt;

        try{
            connection = DriverManager.getConnection(url, userDB, passwordDB);
            String query = "create database " + nameDB;
            preparedstmt = connection.prepareStatement(query);
            preparedstmt.execute();
            preparedstmt.close();
            createTable();
            insertDataSet();
            return true;
        }catch(Exception e){
            System.out.println(e);
        }
        JOptionPane.showMessageDialog(null, "Username e/o Password sono errati!", "Messaggio", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    /**
     * Metodo che permette di creare le tabelle nel database nel caso in cui esse non esistono gia'
     * @return true o false, in base all'esito dell'operazione
     *
     * @author Luca Perfetti
     */
    private static boolean createTable(){
        PreparedStatement preparedstmt;

        try{
            connection = DriverManager.getConnection(url + nameDB, userDB, passwordDB);

            String query = "create table if not exists vaccinati("
                    + "id VARCHAR(16) PRIMARY KEY,"
                    + "nome_centro_vaccinale VARCHAR(50),"
                    + "nome VARCHAR(50),"
                    + "cognome VARCHAR(50),"
                    + "codice_fiscale VARCHAR(50),"
                    + "data_vaccino VARCHAR(40),"
                    + "tipo_vaccino VARCHAR(50));"

                    + "create table if not exists centri_vaccinali("
                    + "nome_centro_vaccinale VARCHAR(50) PRIMARY KEY,"
                    + "qualificatore VARCHAR(7),"
                    + "nome_via VARCHAR(30),"
                    + "civico VARCHAR(6),"
                    + "provincia CHAR(2),"
                    + "comune VARCHAR(30),"
                    + "cap INTEGER,"
                    + "tipologia VARCHAR(20));"

                    + "create table if not exists cittadini("
                    + "id VARCHAR(16) PRIMARY KEY,"
                    + "nome VARCHAR(50),"
                    + "cognome VARCHAR(50),"
                    + "codice_fiscale VARCHAR(50),"
                    + "email VARCHAR(50),"
                    + "username VARCHAR(50),"
                    + "password VARCHAR(150),"
                    + "nome_centro_vaccinale VARCHAR(50) REFERENCES centri_vaccinali);"

                    + "create table if not exists eventi_avversi("
                    + "username VARCHAR(50) PRIMARY KEY,"
                    + "mal_di_testa INTEGER,"
                    + "febbre INTEGER,"
                    + "tachicardia INTEGER,"
                    + "dolori_muscolari INTEGER,"
                    + "linfoadenopatia INTEGER,"
                    + "crisi_ipertensiva INTEGER,"
                    + "note VARCHAR(256));"

                    +"create table if not exists dataset_comuni("
                    +"comune VARCHAR(40) PRIMARY KEY,"
                    +"provincia VARCHAR(2),"
                    +"cap INTEGER,"
                    +"regione VARCHAR(21));";

            preparedstmt = connection.prepareStatement(query);
            preparedstmt.execute();
            preparedstmt.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();}
        return false;}


    /**
     * il metodo permette di inserire il dataSet di comuni province cap e regione italiane
     *
     * @author Alessandro Cassani
     */
    private static void insertDataSet() {
        try {
            PreparedStatement ps;
            URL resource = DBManagement.class.getResource("/dataset/dataset_comuni");
            File dataset = null;
            String ds = "";
            assert resource != null;
            dataset = Paths.get(resource.toURI()).toFile();
            assert dataset != null;
            BufferedReader br = new BufferedReader(new FileReader(dataset));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            int i = 0;

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
                i++;
            }
            ds = sb.toString();
            getDB().connection.prepareStatement(ds).executeUpdate();
        }catch(Exception e){e.printStackTrace();}
    }

    public void insertDataSetTest() throws SQLException{
        PreparedStatement preparedStatement = (PreparedStatement) connection.createStatement();
        String query = "DELETE FROM centri_vaccinali;"
                + "DELETE FROM eventi_avversi"
                + "DELETE FROM cittadini"
                + "DELETE FROM vaccinati"

                + "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia) " +
                "values('TRADATEOSPEDALE', 'VIA', 'ZANABONI', 1, 'VA', 'TRADATE', 21049, 'OSPEDALIERO');\n" +
                "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia) " +
                "values('MALPENSA FIERE', 'VIA', 'XI SETTEMBRE', 16, 'VA', 'BUSTO ARSIZIO', 21052, 'HUB');\n" +
                "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia) " +
                "values('HUB LURATE CACCIVIO', 'PIAZZA', 'ALPINI', 10, 'CO', 'LURATE CACCIVIO', 22075, 'HUB');\n" +
                "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('OSPEDALE FORNAROLI', 'VIA', 'AL DONATORE DI SANGUE', 50, 'MI', 'MAGENTA', 20013, 'OSPEDALIERO');\n" +
                "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia) " +
                "values('LARIOFIERE', 'VIALE', 'RESEGONE', 9, 'CO', 'ERBA', 22036, 'HUB');\n" +
                "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia) " +
                "values('OSPEDALE DI CIVITA CASTELLANA', 'VIA', 'FERRETTI', 169,  'VT', 'CIVITA CASTELLANA', 01033, 'OSPEDALIERO');\n" +
                "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia) " +
                "values('HUB DELLA SABINA', 'VIA', 'DELLA MECCANICA', 32, 'RI', 'PASSO CORESE', 02032, 'HUB');\n" +
                "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia) " +
                "values('UNIVERSITARIA SANT ANDREA', 'VIA', 'DI GROTTAROSSA', 1035, 'RM', 'ROMA', 00109, 'AZIENDALE');\n" +
                "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia) " +
                "values('OSPEDALE CITTA DI CASTELLO', 'VIA', 'LUIGI ANGELINI', 10, 'PG', 'CITTA DI CASTELLO', 06012, 'OSPEDALIERO');\n" +
                "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia) " +
                "values('UNIONE MONTANTA DEI SETTE COMUNI', 'VIA', 'STAZIONE', 1,'VI', 'ASIAGO', 36012, 'AZIENDALE');\n" +
                "insert into centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia) " +
                "values('CENTRO MAROSTICA', 'VIA', '4 NOVEMBRE', 43, 'VI', 'MAROSTICA', 36063, 'HUB');"+
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000000, 'ALESSANDRO', 'MULINO', 'MLNLSS11G02C478D', 'AMULINO@GMAIL.COM', 'ALEMULINO', 'MULINOALE11@', 'HUB LURATE CACCIVIO');\n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv)" +
                " values(0000000000000001, 'GIANLUCA', 'LENOVO', 'LNVGNL07H65D987V', 'GLENOVO@GMAIL.COM', 'GIANLUCAL', 'LENOVOGI98@', 'OSPEDALE CITTA DI CASTELLO');\n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000002, 'MARCO', 'BERETTA', 'BRTMRC89P30P432P', 'MBERETTA@GMAIL.COM', 'MARCOB', 'BERETTAMA23@', 'OSPEDALE FORNAROLI'); \n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000003, 'MARIA', 'RANA', 'RNAMRI09P12J462D', 'MARIARANA@GMAIL.COM', 'MARIAR', 'RANAMARIA56@', 'UNIVERSITARIA SANT ANDREA'); \n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000004, 'ANNA', 'GAROFALO', 'GRFANN08L04J398P', 'AGAROFALO@GMAIL.COM','ANNAG', 'GAROFALO45@', 'LARIOFIERE');\n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000005, 'PAOLO', 'BARILLA', 'BRLPLA09L30J456P', 'PBARILLA@GMAIL.COM', 'PAOLOB', 'BARILLAM', 'TRADATEOSPEDALE'); \n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000006, 'MIRKO', 'BAROLO', 'BRLMRK12L24J192T', 'MBAROLO@GMAIL.COM', 'MIRKOB', 'BAROLO34@', 'UNIONE MONTANTA DEI SETTE COMUNI');\n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000007, 'SAMUELE', 'IENOPOLI', 'NPLSML96R25P132K', 'SIENOPOLI@GMAIL.COM', 'SAMUI', 'IENOPOLIS@34', 'OSPEDALE DI CIVITA CASTELLANA'); \n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000008, 'ANTONIO', 'BANDERA', 'BNDNTN64T28P192J', 'ABANDERA@GMAIL.COM', 'ANTONIOB', 'BANDERAA98@', 'HUB DELLA SABINA');\n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000009, 'ALESSANDRO', 'DEL PIERO', 'DLPLSS02Y06I198P', 'ADELPIERO@GMAIL.COM', 'ALEDELPIERO', 'DELPIERO10@', 'MALPENSA FIERE');\n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000010, 'ANDREA', 'VIERI', 'VRINDR89M14L145P', 'AVIERI@GMAIL.COM', 'AVIERI', 'VIERIAND@45', 'CENTRO MAROSTICA');\n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000011, 'LUCA', 'TONI', 'TNOLCU99P29F531R', 'LTONI@GMAIL.COM', 'LTONI', 'LUCATONI@45', 'MALPENSA FIERE');\n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000012, 'ELISABETTA', 'ANELLI', 'NLLLSB91T05B412V', 'EANELLI@GMIAL.COM', 'EANELLI', 'ANELLIE52@', 'OSPEDALE FORNAROLI');\n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000013, 'ROBERTA', 'RIVA', 'RVIRBR01O30U325M', 'RRIVA@GMAIL.COM', 'RRIVA', 'RIVAROBY96@', 'LARIOFIERE');\n" +
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv) " +
                "values(0000000000000014, 'FRANCESCA', 'COLOMBO', 'CLMFRN99R16G654J', 'FCOLOMBO@GMAIL.COM', 'FCOLOMBO', 'COLMBO59@', 'MALPENSA FIERE');"+
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                "values(0000000000000000, 'HUB LURATE CACCIVIO',  'ALESSANDRO', 'MULINO', 'MLNLSS11G02C478D', '01-01-2021', 'PFIZER');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                "values(0000000000000001, 'OSPEDALE CITTA DI CASTELLO','GIANLUCA', 'LENOVO', 'LNVGNL07H65D987V',  '01-01-2021', 'PFIZER');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000002, 'OSPEDALE FORNAROLI', 'MARCO', 'BERETTA', 'BRTMRC89P30P432P', '02-08-2021', 'ASTRAZENECA');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000003, 'UNIVERSITARIA SANT ANDREA', 'MARIA', 'RANA', 'RNAMRI09P12J462D', '05-04-2021', 'MODERNA');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                "values(0000000000000004, 'LARIOFIERE', 'ANNA', 'GAROFALO', 'GRFANN08L04J398P', '09-06-2021', 'J&J');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000005, 'TRADATEOSPEDALE', 'PAOLO', 'BARILLA', 'BRLPLA09L30J456P', '12-10-2021', 'ASTRAZENECA');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000006, 'UNIONE MONTANTA DEI SETTE COMUNI', 'MIRKO', 'BAROLO', 'BRLMRK12L24J192T', '16-08-2021', 'J&J');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000007, 'OSPEDALE DI CIVITA CASTELLANA', 'SAMUELE', 'IENOPOLI', 'NPLSML96R25P132K', '30-01-2021', 'PFIZER');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000008, 'HUB DELLA SABINA', 'ANTONIO', 'BANDERA', 'BNDNTN64T28P192J', '01-02-2021', 'ASTRAZENECA');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000009, 'MALPENSA FIERE', 'ALESSANDRO', 'DEL PIERO', 'DLPLSS02Y06I198P', '15-03-2021', 'PFIZER');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000010, 'CENTRO MAROSTICA', 'ANDREA', 'VIERI', 'VRINDR89M14L145P', '18-07-2021', 'MODERNA');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000011, 'MALPENSA FIERE', 'LUCA', 'TONI', 'TNOLCU99P29F531R', '30-06-2021', 'ASTRAZENECA');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000012, 'OSPEDALE FORNAROLI', 'ELISABETTA', 'ANELLI', 'NLLLSB91T05B412V', '03-07-2021', 'J&J');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000013, 'LARIOFIERE', 'ROBERTA', 'RIVA', 'RVIRBR01O30U325M', '15-07-2021', 'PFIZER');\n" +
                "insert into vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)" +
                " values(0000000000000014, 'MALPENSA FIERE', 'FRANCESCA', 'COLOMBO', 'CLMFRN99R16G654J', '16-08-2021', 'MODERNA');"+
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('ALEMULINO', 0, 2, 4, 5, 2, 5, 'GONFIORE AL BRACCIO NEL PUNTO DI INIEZIONE');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('GIANLUCAL', 2, 5, 3, 2, 3, 4, 'FEBBRE ALTA');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('MARCOB', 4, 4, 2, 1, 4, 3, 'SENSO DI VOMITO');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('MARIAR', 3, 4, 1, 1, 1, 2, 'STANCHEZZA');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('ANNAG', 0, 1, 0, 3, 0, 1, 'DOLORE AL BRACCIO NEL PUNTO DI INIEZIONE');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('PAOLOB', 1, 3, 4, 1, 1, 0, 'BATTITO CARDIACO FORTE');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('MIRKOB', 1, 5, 2, 2, 3, 4, 'SENSO DI NAUSEA');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('SAMUI', 2, 5, 3, 5, 2, 2, 'FEBBRE ALTA');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('ANTONIOB', 5, 4, 5, 4, 4, 3, 'GONFIORE AL BRACCIO NEL PUNTO DI INIEZIONE');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('ALEDELPIERO', 3, 1, 0, 2, 2, 5, 'SENSO DI VOMITO');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note) " +
                "values('AVIERI', 1, 2, 5, 1, 5, 1, 'BRIVIDI');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('LTONI', 0, 5, 4, 5, 5, 0, 'FEBBRE ALTA');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('EANELLI', 2, 3, 4, 0, 1, 3, 'STANCHEZZA');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('RRIVA', 4, 5, 2, 1, 0, 0, 'DOLORE AL BRACCIO NEL PUNTO DI INIEZIONE');\n" +
                "insert into eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)" +
                " values('FCOLOMBO', 3, 4, 2, 0, 5, 5, 'SENSO DI NAUSEA');";

        preparedStatement.executeUpdate(query);
    }
}