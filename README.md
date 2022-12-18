Progetto VaxCenter. 

Corso di Laboratorio Interdiscplinare B, Laurea Triennale in Informatica Università degli Studi dell'Insubria

PROGETTO REALIZZATO DA: 

CASSANI ALESSANDRO, matricola 744512 acassani@studenti.uninsubria.it sede: Varese

FICARA DAMIANO, matricola 744958 dficara@studenti.uninsubria.it sede: Varese

BRUSCAGIN PAOLO, matricola 744703 pbruscagin@studenti.uninsubria.it sede: Varese

PERFETTI LUCA, matricola 746581 lperfetti1@studenti.uninsubria.it sede: Varese

*********************************


VaxCenter ha come scopo primario il monitoraggio del numero di centri vaccinali presenti sul territorio italiano, 
così come il conteggio e l'identificazione delle persone che sono state vaccinate sia in termini di quale vaccino hanno ricevuto, 
sia in quale centro si sono recate perottenere la somministrazione. 
Inoltre, ai cittadini è consentito registrarsi e accedere alla propria area personale segnalando eventuali eventi avversi avvenuti dopo la somministrazione del vaccino.


*********************************

REQUISITI DI SISTEMA

Per poter eseguire l'applicazione è necessario aver installato sul
proprio computer:

▪ Java 8;

▪ PostgreSQL (solo per applicazione server);

▪ Maven (per compilare da sorgente);

▪ Connessione Internet stabile;

▪ Risoluzione: 1280 x 720 pixel (HD) o superiore;

▪ RAM: 4GB o superiore;

▪ Spazio su disco disponibile: 2 GB o superiore;

▪ Processore: 1 GHz o superiore.


Sistema operativo minimo richiesto

▪ Windows 10 (8u51 e versioni successive).

▪ Ubuntu Linux 14.x (8u25 e versioni successive).

▪ Mac OS X 10.8.3+ (e versioni successive).


*********************************

Installazione del programma

L'applicazione non necessita di installazione: si tratta infatti di unprogramma distribuito senza dipendenze esterne 
(a patto che ci sia installato il Maven). Per l'esecuzione dell'applicazione avviare direttamente clientCV-1.0.jar e serverCV-1.0.jar.

Avviare l'applicazione

Per avviare l'applicazione è sufficiente fare doppio click sul file nella cartella target/ del modulo che si vuole eseguire: 

▪serverCV-1.0.jar se si necessita utilizzare l'applicazione Server; 
▪clientCV-1.0.jar se si necessita utilizzare l'applicazione Client. 

o in alternativa è possibile avviare il programma da terminale, con il seguente comando:

▪ cd {nome del percorso in cui è presente il file Server .jar} java -jar serverCV-1.0.jar 
▪ cd {nome del percorso in cui è presente il file Client .jar} java -jar clientCV-1.0.jar


*********************************


Compilazione Maven tramite riga di comando Per compilazione Maven tramite riga di comando eseguire i comandi nell'ordine seguente:

mvn clean 

mnv validate 

mvn compile 

mvn package 

mvn javadoc:javadoc


Una volta fatto, nei moduli relativi, verrà generata una cartella "target" dove all'interno saranno presenti gli eseguibili .jar e la Javadoc del progetto.
