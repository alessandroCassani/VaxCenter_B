package database;

import database.UI.UILoginToServer;

import javax.swing.*;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.*;

import static database.ServerImpl.*;

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
     * @return istanza del db
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
                    + "nome_centro_vaccinale VARCHAR(80) REFERENCES centri_vaccinali,"
                    + "nome VARCHAR(50),"
                    + "cognome VARCHAR(50),"
                    + "codice_fiscale VARCHAR(50),"
                    + "data_vaccino VARCHAR(40),"
                    + "tipo_vaccino VARCHAR(50));"

                    + "create table if not exists centri_vaccinali("
                    + "nome_centro_vaccinale VARCHAR(80) PRIMARY KEY,"
                    + "qualificatore VARCHAR(7),"
                    + "nome_via VARCHAR(30),"
                    + "civico VARCHAR(6),"
                    + "provincia CHAR(2),"
                    + "comune VARCHAR(30),"
                    + "cap INTEGER,"
                    + "tipologia VARCHAR(20);"
                    + "FOREIGN KEY (comune,provincia) REFERENCES dataset_comuni);"

                    + "create table if not exists cittadini("
                    + "id VARCHAR(16) PRIMARY KEY REFERENCES vaccinati,"
                    + "nome VARCHAR(50),"
                    + "cognome VARCHAR(50),"
                    + "codice_fiscale VARCHAR(50),"
                    + "email VARCHAR(50),"
                    + "username VARCHAR(50),"
                    + "password VARCHAR(150),"
                    + "nome_centro_vaccinale VARCHAR(80) REFERENCES centri_vaccinali);"

                    + "create table if not exists eventi_avversi("
                    + "id VARCHAR(16) PRIMARY KEY REFERENCES cittadini,"
                    + "mal_di_testa INTEGER,"
                    + "febbre INTEGER,"
                    + "tachicardia INTEGER,"
                    + "dolori_muscolari INTEGER,"
                    + "linfoadenopatia INTEGER,"
                    + "crisi_ipertensiva INTEGER,"
                    + "note VARCHAR(256));"

                    +"create table if not exists dataset_comuni("
                    +"comune VARCHAR(40),"
                    +"provincia VARCHAR(2),"
                    +"cap INTEGER,"
                    +"regione VARCHAR(21)"
                    +"PRIMARY KEY (comune,provincia);";

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

    /**
     * il metodo permette d'inserire il dataSet di test
     * @throws SQLException eccezione sql
     * @author Luca Perfetti
     */
    public static void insertTestDataSet() throws SQLException{
        PreparedStatement ps = DBManagement.getDB().connection.prepareStatement(
                "DELETE FROM eventi_avversi;"
                        + "DELETE FROM vaccinati;"
                        + "DELETE FROM cittadini;"
                        +"DELETE FROM centri_vaccinali;"

                +"INSERT INTO centri_vaccinali(nome_centro_vaccinale,qualificatore,nome_via,civico,provincia,comune,cap,tipologia) "+
                "VALUES ('TRADATEOSPEDALE', 'VIA', 'ZANABONI', 1, 'VA', 'TRADATE', 21050, 'OSPEDALIERO');\n" +
                "insert into centri_vaccinali(nome_centro_vaccinale, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('MALPENSA FIERE', 'VIA', 'XI SETTEMBRE', 16, 'VA', 'BUSTO ARSIZIO', 21052, 'HUB');\n" +
                "insert into centri_vaccinali(nome_centro_vaccinale, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('HUB LURATE CACCIVIO', 'PIAZZA', 'ALPINI', 10, 'CO', 'LURATE CACCIVIO', 22075, 'HUB');\n" +
                "insert into centri_vaccinali(nome_centro_vaccinale, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('OSPEDALE FORNAROLI', 'VIA', 'AL DONATORE DI SANGUE', 50, 'MI', 'MAGENTA', 20013, 'OSPEDALIERO');\n" +
                "insert into centri_vaccinali(nome_centro_vaccinale, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('LARIOFIERE', 'VIALE', 'RESEGONE', 9, 'CO', 'ERBA', 22036, 'HUB');\n" +
                "insert into centri_vaccinali(nome_centro_vaccinale, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('OSPEDALE DI CIVITA CASTELLANA', 'VIA', 'FERRETTI', 169,  'VT', 'CIVITA CASTELLANA', 01033, 'OSPEDALIERO');\n" +
                "insert into centri_vaccinali(nome_centro_vaccinale, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('HUB DELLA SABINA', 'VIA', 'DELLA MECCANICA', 32, 'RI', 'PASSO CORESE', 02032, 'HUB');\n" +
                "insert into centri_vaccinali(nome_centro_vaccinale, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('UNIVERSITARIA SANT ANDREA', 'VIA', 'DI GROTTAROSSA', 1035, 'RM', 'ROMA', 00109, 'AZIENDALE');\n" +
                "insert into centri_vaccinali(nome_centro_vaccinale, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('OSPEDALE CITTA DI CASTELLO', 'VIA', 'LUIGI ANGELINI', 10, 'PG', 'CITTA DI CASTELLO', 06012, 'OSPEDALIERO');\n" +
                "insert into centri_vaccinali(nome_centro_vaccinale, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('UNIONE MONTANTA DEI SETTE COMUNI', 'VIA', 'STAZIONE', 1,'VI', 'ASIAGO', 36012, 'AZIENDALE');\n" +
                "insert into centri_vaccinali(nome_centro_vaccinale, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)" +
                "values('CENTRO MAROSTICA', 'VIA', '4 NOVEMBRE', 43, 'VI', 'MAROSTICA', 36063, 'HUB');");

        PreparedStatement psC1 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC1.setString(1, idPadding(BigInteger.valueOf(00000000000000000)));
        psC1.setString(2, encrypt("ALESSANDRO",SECRETKEY));
        psC1.setString(3,encrypt("MULINO",SECRETKEY));
        psC1.setString(4,encrypt("MLNLSS11G02C478D",SECRETKEY));
        psC1.setString(5, encrypt("amulino@gmail.com",SECRETKEY));
        psC1.setString(6,encrypt("Alemulino",SECRETKEY));
        psC1.setString(7,sha256("Postgres23@"));
        psC1.setString(8,"HUB LURATE CACCIVIO");

        PreparedStatement psC2 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC2.setString(1, idPadding(BigInteger.valueOf(00000000000000001)));
        psC2.setString(2, encrypt("GIANLUCA",SECRETKEY));
        psC2.setString(3,encrypt("LENOVO",SECRETKEY));
        psC2.setString(4,encrypt("LNVGNL07H65D987V",SECRETKEY));
        psC2.setString(5, encrypt("glenovo@gmail.com",SECRETKEY));
        psC2.setString(6,encrypt("GianlucaLe",SECRETKEY));
        psC2.setString(7,sha256("Postgres23@"));
        psC2.setString(8,"OSPEDALE CITTA DI CASTELLO");

        PreparedStatement psC3 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC3.setString(1, idPadding(BigInteger.valueOf(00000000000000002)));
        psC3.setString(2, encrypt("MARCO",SECRETKEY));
        psC3.setString(3,encrypt("BERETTA",SECRETKEY));
        psC3.setString(4,encrypt("BRTMRC89P30P432P",SECRETKEY));
        psC3.setString(5, encrypt("mberetta@gmail.com",SECRETKEY));
        psC3.setString(6,encrypt("'MarcoB'",SECRETKEY));
        psC3.setString(7,sha256("Postgres23@"));
        psC3.setString(8,"OSPEDALE FORNAROLI");

        PreparedStatement psC4 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC4.setString(1, idPadding(BigInteger.valueOf(00000000000000003)));
        psC4.setString(2, encrypt("MARIA",SECRETKEY));
        psC4.setString(3,encrypt("RANA",SECRETKEY));
        psC4.setString(4,encrypt("RNAMRI09P12J462D",SECRETKEY));
        psC4.setString(5, encrypt("mariarana@gmail.com",SECRETKEY));
        psC4.setString(6,encrypt("MariaR",SECRETKEY));
        psC4.setString(7,sha256("Postgres23@"));
        psC4.setString(8,"UNIVERSITARIA SANT ANDREA");

        PreparedStatement psC5 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC5.setString(1, idPadding(BigInteger.valueOf(00000000000000004)));
        psC5.setString(2, encrypt("ANNA",SECRETKEY));
        psC5.setString(3,encrypt("GAROFALO",SECRETKEY));
        psC5.setString(4,encrypt("GRFANN08L04J398P",SECRETKEY));
        psC5.setString(5, encrypt("agarofalo@gmail.com",SECRETKEY));
        psC5.setString(6,encrypt("AnnaG",SECRETKEY));
        psC5.setString(7,sha256("Postgres23@"));
        psC5.setString(8,"LARIOFIERE");

        PreparedStatement psC6 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC6.setString(1, idPadding(BigInteger.valueOf(00000000000000005)));
        psC6.setString(2, encrypt("PAOLO",SECRETKEY));
        psC6.setString(3,encrypt("BARILLA",SECRETKEY));
        psC6.setString(4,encrypt("BRLPLA09L30J456P",SECRETKEY));
        psC6.setString(5, encrypt("pbarilla@gmail.com",SECRETKEY));
        psC6.setString(6,encrypt("PaoloB",SECRETKEY));
        psC6.setString(7,sha256("Postgres23@"));
        psC6.setString(8,"TRADATEOSPEDALE");

        PreparedStatement psC7 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC7.setString(1, idPadding(BigInteger.valueOf(00000000000000006)));
        psC7.setString(2, encrypt("MIRKO",SECRETKEY));
        psC7.setString(3,encrypt("BAROLO",SECRETKEY));
        psC7.setString(4,encrypt("BRLMRK12L24J192T",SECRETKEY));
        psC7.setString(5, encrypt("mbarolo@gmail.com",SECRETKEY));
        psC7.setString(6,encrypt("MirkoB",SECRETKEY));
        psC7.setString(7,sha256("Postgres23@"));
        psC7.setString(8,"UNIONE MONTANTA DEI SETTE COMUNI");

        PreparedStatement psC8 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC8.setString(1, idPadding(BigInteger.valueOf(00000000000000007)));
        psC8.setString(2, encrypt("SAMUELE",SECRETKEY));
        psC8.setString(3,encrypt("IENOPOLI",SECRETKEY));
        psC8.setString(4,encrypt("NPLSML96R25P132K",SECRETKEY));
        psC8.setString(5, encrypt("sienopoli@gmail.com",SECRETKEY));
        psC8.setString(6,encrypt("SamueleI",SECRETKEY));
        psC8.setString(7,sha256("Postgres23@"));
        psC8.setString(8,"OSPEDALE DI CIVITA CASTELLANA");

        PreparedStatement psC9 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC9.setString(1, idPadding(BigInteger.valueOf(00000000000000016)));
        psC9.setString(2, encrypt("ANTONIO",SECRETKEY));
        psC9.setString(3,encrypt("BANDERA",SECRETKEY));
        psC9.setString(4,encrypt("BNDNTN64T28P192J",SECRETKEY));
        psC9.setString(5, encrypt("abandera@gmail.com",SECRETKEY));
        psC9.setString(6,encrypt("AntonioB",SECRETKEY));
        psC9.setString(7,sha256("Postgres23@"));
        psC9.setString(8,"HUB DELLA SABINA");

        PreparedStatement psC10 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC10.setString(1, idPadding(BigInteger.valueOf(00000000000000015)));
        psC10.setString(2, encrypt("ALESSANDRO",SECRETKEY));
        psC10.setString(3,encrypt("DEL PIERO",SECRETKEY));
        psC10.setString(4,encrypt("DLPLSS02Y06I198P",SECRETKEY));
        psC10.setString(5, encrypt("adelpiero@gmail.com",SECRETKEY));
        psC10.setString(6,encrypt("'AleDelPiero'",SECRETKEY));
        psC10.setString(7,sha256("Postgres23@"));
        psC10.setString(8,"MALPENSA FIERE");

        PreparedStatement psC11 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC11.setString(1, idPadding(BigInteger.valueOf(00000000000000010)));
        psC11.setString(2, encrypt("ANDREA",SECRETKEY));
        psC11.setString(3,encrypt("VIERI",SECRETKEY));
        psC11.setString(4,encrypt("VRINDR89M14L145P",SECRETKEY));
        psC11.setString(5, encrypt("avieri@gmail.com",SECRETKEY));
        psC11.setString(6,encrypt("AndreaV",SECRETKEY));
        psC11.setString(7,sha256("Postgres23@"));
        psC11.setString(8,"CENTRO MAROSTICA");

        PreparedStatement psC12 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC12.setString(1, idPadding(BigInteger.valueOf(00000000000000011)));
        psC12.setString(2, encrypt("LUCA",SECRETKEY));
        psC12.setString(3,encrypt("TONI",SECRETKEY));
        psC12.setString(4,encrypt("TNOLCU99P29F531R",SECRETKEY));
        psC12.setString(5, encrypt("ltoni@gmail.com",SECRETKEY));
        psC12.setString(6,encrypt("LucaT",SECRETKEY));
        psC12.setString(7,sha256("Postgres23@"));
        psC12.setString(8,"MALPENSA FIERE");

        PreparedStatement psC13 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC13.setString(1, idPadding(BigInteger.valueOf(00000000000000012)));
        psC13.setString(2, encrypt("ELISABETTA",SECRETKEY));
        psC13.setString(3,encrypt("ANELLI",SECRETKEY));
        psC13.setString(4,encrypt("NLLLSB91T05B412V",SECRETKEY));
        psC13.setString(5, encrypt("eanelli@gmail.com",SECRETKEY));
        psC13.setString(6,encrypt("ElisaAnelli",SECRETKEY));
        psC13.setString(7,sha256("Postgres23@"));
        psC13.setString(8,"OSPEDALE FORNAROLI");

        PreparedStatement psC14 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC14.setString(1, idPadding(BigInteger.valueOf(00000000000000013)));
        psC14.setString(2, encrypt("ROBERTA",SECRETKEY));
        psC14.setString(3,encrypt("RIVA",SECRETKEY));
        psC14.setString(4,encrypt("RVIRBR01O30U325M",SECRETKEY));
        psC14.setString(5, encrypt("rriva@gmail.com",SECRETKEY));
        psC14.setString(6,encrypt("RobyRiva",SECRETKEY));
        psC14.setString(7,sha256("Postgres23@"));
        psC14.setString(8,"LARIOFIERE");

        PreparedStatement psC15 = DBManagement.getDB().connection.prepareStatement(
                "insert into cittadini(id, nome, cognome, codice_fiscale, email, username, password, nome_centro_vaccinale) " +
                "values(?,?,?,?,?,?,?,?);");
        psC15.setString(1, idPadding(BigInteger.valueOf(00000000000000014)));
        psC15.setString(2, encrypt("FRANCESCA",SECRETKEY));
        psC15.setString(3,encrypt("COLOMBO",SECRETKEY));
        psC15.setString(4,encrypt("CLMFRN99R16G654J",SECRETKEY));
        psC15.setString(5, encrypt("fcolombo@gmail.com",SECRETKEY));
        psC15.setString(6,encrypt("FrancyColo",SECRETKEY));
        psC15.setString(7,sha256("Postgres23@"));
        psC15.setString(8,"MALPENSA FIERE");

        PreparedStatement psV1 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV1.setString(1, String.valueOf(0000000000000000));
        psV1.setString(2,encrypt("HUB LURATE CACCIVIO",SECRETKEY));
        psV1.setString(3,encrypt("ALESSANDRO",SECRETKEY));
        psV1.setString(4,encrypt("MULINO",SECRETKEY));
        psV1.setString(5,encrypt("MLNLSS11G02C478D",SECRETKEY));
        psV1.setString(6,encrypt(String.valueOf(23-9-2022),SECRETKEY));
        psV1.setString(7,encrypt("PFIZER",SECRETKEY));

        PreparedStatement psV2 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV2.setString(1, String.valueOf(0000000000000001));
        psV2.setString(2,encrypt("OSPEDALE CITTA DI CASTELLO",SECRETKEY));
        psV2.setString(3,encrypt("GIANLUCA",SECRETKEY));
        psV2.setString(4,encrypt("LENOVO",SECRETKEY));
        psV2.setString(5,encrypt("LNVGNL07H65D987V",SECRETKEY));
        psV2.setString(6,encrypt(String.valueOf(10-10-2022),SECRETKEY));
        psV2.setString(7,encrypt("PFIZER",SECRETKEY));

        PreparedStatement psV3 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV3.setString(1, String.valueOf(0000000000000002));
        psV3.setString(2,encrypt("OSPEDALE FORNAROLI",SECRETKEY));
        psV3.setString(3,encrypt("MARCO",SECRETKEY));
        psV3.setString(4,encrypt("BERETTA",SECRETKEY));
        psV3.setString(5,encrypt("BRTMRC89P30P432P",SECRETKEY));
        psV3.setString(6,encrypt(String.valueOf(14-10-2022),SECRETKEY));
        psV3.setString(7,encrypt("ASTRAZENECA",SECRETKEY));

        PreparedStatement psV4 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV4.setString(1, String.valueOf(0000000000000003));
        psV4.setString(2,encrypt("UNIVERSITARIA SANT ANDREA",SECRETKEY));
        psV4.setString(3,encrypt("MARIA",SECRETKEY));
        psV4.setString(4,encrypt("RANA",SECRETKEY));
        psV4.setString(5,encrypt("RNAMRI09P12J462D",SECRETKEY));
        psV4.setString(6,encrypt(String.valueOf(15-10-2022),SECRETKEY));
        psV4.setString(7,encrypt("MODERNA",SECRETKEY));

        PreparedStatement psV5 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV5.setString(1, String.valueOf(0000000000000004));
        psV5.setString(2,encrypt("LARIOFIERE",SECRETKEY));
        psV5.setString(3,encrypt("ANNA",SECRETKEY));
        psV5.setString(4,encrypt("GAROFALO",SECRETKEY));
        psV5.setString(5,encrypt("GRFANN08L04J398P",SECRETKEY));
        psV5.setString(6,encrypt(String.valueOf(17-10-2022),SECRETKEY));
        psV5.setString(7,encrypt("J&J",SECRETKEY));

        PreparedStatement psV6 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV6.setString(1, String.valueOf(0000000000000005));
        psV6.setString(2,encrypt("TRADATEOSPEDALE",SECRETKEY));
        psV6.setString(3,encrypt("PAOLO",SECRETKEY));
        psV6.setString(4,encrypt("BARILLA",SECRETKEY));
        psV6.setString(5,encrypt("BRLPLA09L30J456P",SECRETKEY));
        psV6.setString(6,encrypt(String.valueOf(17-10-2022),SECRETKEY));
        psV6.setString(7,encrypt("ASTRAZENECA",SECRETKEY));

        PreparedStatement psV7 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV7.setString(1, String.valueOf(0000000000000006));
        psV7.setString(2,encrypt("UNIONE MONTANTA DEI SETTE COMUNI",SECRETKEY));
        psV7.setString(3,encrypt("MIRKO",SECRETKEY));
        psV7.setString(4,encrypt("BAROLO",SECRETKEY));
        psV7.setString(5,encrypt("BRLMRK12L24J192T",SECRETKEY));
        psV7.setString(6,encrypt(String.valueOf(27-10-2022),SECRETKEY));
        psV7.setString(7,encrypt("J&J",SECRETKEY));

        PreparedStatement psV8 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV8.setString(1, String.valueOf(0000000000000007));
        psV8.setString(2,encrypt("OSPEDALE DI CIVITA CASTELLANA",SECRETKEY));
        psV8.setString(3,encrypt("SAMUELE",SECRETKEY));
        psV8.setString(4,encrypt("IENOPOLI",SECRETKEY));
        psV8.setString(5,encrypt("NPLSML96R25P132K",SECRETKEY));
        psV8.setString(6,encrypt(String.valueOf(1-11-2022),SECRETKEY));
        psV8.setString(7,encrypt("PFIZER",SECRETKEY));

        PreparedStatement psV9 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV9.setString(1, String.valueOf(0000000000000016));
        psV9.setString(2,encrypt("HUB DELLA SABINA",SECRETKEY));
        psV9.setString(3,encrypt("ANTONIO",SECRETKEY));
        psV9.setString(4,encrypt("BANDERA",SECRETKEY));
        psV9.setString(5,encrypt("BNDNTN64T28P192J",SECRETKEY));
        psV9.setString(6,encrypt(String.valueOf(3-11-2022),SECRETKEY));
        psV9.setString(7,encrypt("ASTRAZENECA",SECRETKEY));

        PreparedStatement psV10 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV10.setString(1, String.valueOf(0000000000000015));
        psV10.setString(2,encrypt("MALPENSA FIERE",SECRETKEY));
        psV10.setString(3,encrypt("ALESSANDRO",SECRETKEY));
        psV10.setString(4,encrypt("DEL PIERO",SECRETKEY));
        psV10.setString(5,encrypt("DLPLSS02Y06I198P",SECRETKEY));
        psV10.setString(6,encrypt(String.valueOf(8-11-2022),SECRETKEY));
        psV10.setString(7,encrypt("PFIZER",SECRETKEY));

        PreparedStatement psV11 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV11.setString(1, String.valueOf(0000000000000010));
        psV11.setString(2,encrypt("CENTRO MAROSTICA",SECRETKEY));
        psV11.setString(3,encrypt("ANDREA",SECRETKEY));
        psV11.setString(4,encrypt("VIERI",SECRETKEY));
        psV11.setString(5,encrypt("VRINDR89M14L145P",SECRETKEY));
        psV11.setString(6,encrypt(String.valueOf(11-11-2022),SECRETKEY));
        psV11.setString(7,encrypt("MODERNA",SECRETKEY));

        PreparedStatement psV12 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV12.setString(1, String.valueOf(0000000000000011));
        psV12.setString(2,encrypt("MALPENSA FIERE",SECRETKEY));
        psV12.setString(3,encrypt("LUCA",SECRETKEY));
        psV12.setString(4,encrypt("TONI",SECRETKEY));
        psV12.setString(5,encrypt("TNOLCU99P29F531R",SECRETKEY));
        psV12.setString(6,encrypt(String.valueOf(14-11-2022),SECRETKEY));
        psV12.setString(7,encrypt("ASTRAZENECA",SECRETKEY));

        PreparedStatement psV13 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV13.setString(1, String.valueOf(0000000000000012));
        psV13.setString(2,encrypt("OSPEDALE FORNAROLI",SECRETKEY));
        psV13.setString(3,encrypt("ELISABETTA",SECRETKEY));
        psV13.setString(4,encrypt("ANELLI",SECRETKEY));
        psV13.setString(5,encrypt("NLLLSB91T05B412V",SECRETKEY));
        psV13.setString(6,encrypt(String.valueOf(20-11-2022),SECRETKEY));
        psV13.setString(7,encrypt("J&J",SECRETKEY));

        PreparedStatement psV14 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV14.setString(1, String.valueOf(0000000000000013));
        psV14.setString(2,encrypt("LARIOFIERE",SECRETKEY));
        psV14.setString(3,encrypt("ROBERTA",SECRETKEY));
        psV14.setString(4,encrypt("RIVA",SECRETKEY));
        psV14.setString(5,encrypt("RVIRBR01O30U325M",SECRETKEY));
        psV14.setString(6,encrypt(String.valueOf(23-11-2022),SECRETKEY));
        psV14.setString(7,encrypt("PFIZER",SECRETKEY));

        PreparedStatement psV15 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO vaccinati(id,nome_centro_vaccinale,nome,cognome,codice_fiscale,data_vaccino,tipo_vaccino) \n" +
                "VALUES(?,?,?,?,?,?,?)");
        psV15.setString(1, String.valueOf(0000000000000014));
        psV15.setString(2,encrypt("MALPENSA FIERE",SECRETKEY));
        psV15.setString(3,encrypt("FRANCESCA",SECRETKEY));
        psV15.setString(4,encrypt("COLOMBO",SECRETKEY));
        psV15.setString(5,encrypt("CLMFRN99R16G654J",SECRETKEY));
        psV15.setString(6,encrypt(String.valueOf(24-11-2022),SECRETKEY));
        psV15.setString(7,encrypt("MODERNA",SECRETKEY));

        PreparedStatement psE1 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE1.setInt(1, 0000000000000000);
        psE1.setInt(2, 0);
        psE1.setInt(3, 2);
        psE1.setInt(4, 4);
        psE1.setInt(5, 5);
        psE1.setInt(6, 2);
        psE1.setInt(7, 5);
        psE1.setString(8, encrypt("GONFIORE AL BRACCIO NEL PUNTO DI INIEZIONE",SECRETKEY));

        PreparedStatement psE2 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE2.setInt(1, 0000000000000001);
        psE2.setInt(2, 2);
        psE2.setInt(3, 5);
        psE2.setInt(4, 3);
        psE2.setInt(5, 2);
        psE2.setInt(6, 3);
        psE2.setInt(7, 4);
        psE2.setString(8, encrypt("FEBBRE ALTA",SECRETKEY));

        PreparedStatement psE3 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE3.setInt(1, 0000000000000002);;
        psE3.setInt(2, 4);
        psE3.setInt(3, 4);
        psE3.setInt(4, 2);
        psE3.setInt(5, 1);
        psE3.setInt(6, 4);
        psE3.setInt(7, 3);
        psE3.setString(8, encrypt("SENSO DI VOMITO",SECRETKEY));

        PreparedStatement psE4 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE4.setInt(1, 0000000000000003);
        psE4.setInt(2, 3);
        psE4.setInt(3, 4);
        psE4.setInt(4, 1);
        psE4.setInt(5, 1);
        psE4.setInt(6, 1);
        psE4.setInt(7, 2);
        psE4.setString(8, encrypt("STANCHEZZA",SECRETKEY));

        PreparedStatement psE5 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE5.setInt(1, 0000000000000004);
        psE5.setInt(2, 0);
        psE5.setInt(3, 1);
        psE5.setInt(4, 0);
        psE5.setInt(5, 3);
        psE5.setInt(6, 0);
        psE5.setInt(7, 1);
        psE5.setString(8, encrypt("DOLORE AL BRACCIO NEL PUNTO DI INIEZIONE",SECRETKEY));

        PreparedStatement psE6 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE6.setInt(1, 0000000000000005);
        psE6.setInt(2, 1);
        psE6.setInt(3, 3);
        psE6.setInt(4, 4);
        psE6.setInt(5, 1);
        psE6.setInt(6, 1);
        psE6.setInt(7, 0);
        psE6.setString(8, encrypt("BATTITO CARDIACO FORTE",SECRETKEY));

        PreparedStatement psE7 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE7.setInt(1, 0000000000000005);
        psE7.setInt(2, 1);
        psE7.setInt(3, 5);
        psE7.setInt(4, 2);
        psE7.setInt(5, 2);
        psE7.setInt(6, 4);
        psE7.setInt(7, 3);
        psE7.setString(8, encrypt("SENSO DI NAUSEA",SECRETKEY));

        PreparedStatement psE8 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE8.setInt(1, 0000000000000006);
        psE8.setInt(2, 2);
        psE8.setInt(3, 5);
        psE8.setInt(4, 3);
        psE8.setInt(5, 5);
        psE8.setInt(6, 2);
        psE8.setInt(7, 2);
        psE8.setString(8, encrypt("FEBBRE ALTA",SECRETKEY));

        PreparedStatement psE9 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE9.setInt(1, 0000000000000016);
        psE9.setInt(2, 5);
        psE9.setInt(3, 4);
        psE9.setInt(4, 5);
        psE9.setInt(5, 4);
        psE9.setInt(6, 4);
        psE9.setInt(7, 3);
        psE9.setString(8, encrypt("GONFIORE AL BRACCIO NEL PUNTO DI INIEZIONE",SECRETKEY));

        PreparedStatement psE10 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE10.setInt(1, 0000000000000015);
        psE10.setInt(2, 3);
        psE10.setInt(3, 1);
        psE10.setInt(4, 0);
        psE10.setInt(5, 2);
        psE10.setInt(6, 2);
        psE10.setInt(7, 5);
        psE10.setString(8, encrypt("SENSO DI VOMITO",SECRETKEY));

        PreparedStatement psE11 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE11.setInt(1, 0000000000000014);
        psE11.setInt(2, 1);
        psE11.setInt(3, 2);
        psE11.setInt(4, 5);
        psE11.setInt(5, 1);
        psE11.setInt(6, 5);
        psE11.setInt(7, 1);
        psE11.setString(8, encrypt("BRIVIDI",SECRETKEY));

        PreparedStatement psE12 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE12.setInt(1, 0000000000000013);
        psE12.setInt(2, 0);
        psE12.setInt(3, 5);
        psE12.setInt(4, 4);
        psE12.setInt(5, 5);
        psE12.setInt(6, 5);
        psE12.setInt(7, 0);
        psE12.setString(8, encrypt("FEBBRE ALTA",SECRETKEY));

        PreparedStatement psE13 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE13.setInt(1, 0000000000000012);
        psE13.setInt(2, 2);
        psE13.setInt(3, 3);
        psE13.setInt(4, 4);
        psE13.setInt(5, 0);
        psE13.setInt(6, 1);
        psE13.setInt(7, 3);
        psE13.setString(8, encrypt("STANCHEZZA",SECRETKEY));

        PreparedStatement psE14 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE14.setInt(1, 0000000000000011);
        psE14.setInt(2, 4);
        psE14.setInt(3, 5);
        psE14.setInt(4, 2);
        psE14.setInt(5, 1);
        psE14.setInt(6, 0);
        psE14.setInt(7, 0);
        psE14.setString(8, encrypt("DOLORE AL BRACCIO NEL PUNTO DI INIEZIONE",SECRETKEY));

        PreparedStatement psE15 = DBManagement.getDB().connection.prepareStatement(
                "INSERT INTO eventi_avversi(id,mal_di_testa,febbre,tachicardia,dolori_muscolari,linfoadenopatia,crisi_ipertensiva,note) " +
                        " VALUES (?,?,?,?,?,?,?,?)");
        psE15.setInt(1, 0000000000000010);
        psE15.setInt(2, 3);
        psE15.setInt(3, 4);
        psE15.setInt(4, 2);
        psE15.setInt(5, 0);
        psE15.setInt(6, 5);
        psE15.setInt(7, 5);
        psE15.setString(8, encrypt("SENSO DI NAUSEA",SECRETKEY));

        ps.executeUpdate();

        psC1.executeUpdate();
        psC2.executeUpdate();
        psC3.executeUpdate();
        psC4.executeUpdate();
        psC5.executeUpdate();
        psC6.executeUpdate();
        psC7.executeUpdate();
        psC8.executeUpdate();
        psC9.executeUpdate();
        psC10.executeUpdate();
        psC11.executeUpdate();
        psC12.executeUpdate();
        psC13.executeUpdate();
        psC14.executeUpdate();
        psC15.executeUpdate();

        psV1.executeUpdate();
        psV2.executeUpdate();
        psV3.executeUpdate();
        psV4.executeUpdate();
        psV5.executeUpdate();
        psV6.executeUpdate();
        psV7.executeUpdate();
        psV8.executeUpdate();
        psV9.executeUpdate();
        psV10.executeUpdate();
        psV11.executeUpdate();
        psV12.executeUpdate();
        psV13.executeUpdate();
        psV14.executeUpdate();
        psV15.executeUpdate();

        psE1.executeUpdate();
        psE2.executeUpdate();
        psE3.executeUpdate();
        psE4.executeUpdate();
        psE5.executeUpdate();
        psE6.executeUpdate();
        psE7.executeUpdate();
        psE8.executeUpdate();
        psE9.executeUpdate();
        psE10.executeUpdate();
        psE11.executeUpdate();
        psE12.executeUpdate();
        psE13.executeUpdate();
        psE14.executeUpdate();
        psE15.executeUpdate();

        ps.close();

        psC1.close();
        psC2.close();
        psC3.close();
        psC4.close();
        psC5.close();
        psC6.close();
        psC7.close();
        psC8.close();
        psC9.close();
        psC10.close();
        psC11.close();
        psC12.close();
        psC13.close();
        psC14.close();
        psC15.close();

        psV1.close();
        psV2.close();
        psV3.close();
        psV4.close();
        psV5.close();
        psV6.close();
        psV7.close();
        psV8.close();
        psV9.close();
        psV10.close();
        psV11.close();
        psV12.close();
        psV13.close();
        psV14.close();
        psV15.close();

        psE1.close();
        psE2.close();
        psE3.close();
        psE4.close();
        psE5.close();
        psE6.close();
        psE7.close();
        psE8.close();
        psE9.close();
        psE10.close();
        psE11.close();
        psE12.close();
        psE13.close();
        psE14.close();
        psE15.close();
    }
}