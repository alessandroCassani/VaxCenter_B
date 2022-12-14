DELETE FROM cittadini;
DELETE FROM vaccinati;
DELETE FROM eventi_avversi;
DELETE FROM centri_vaccinali;

cittadini(id, nome, cognome, codice_fiscale, email, username, password, nomecv)
insert into cittadini values(0000000000000000, 'ALESSANDRO', 'MULINO', 'MLNLSS11G02C478D', 'amulino@gmail.com', 'Alemulino', 'Postgres23@', 'HUB LURATE CACCIVIO');
insert into cittadini values(0000000000000001, 'GIANLUCA', 'LENOVO', 'LNVGNL07H65D987V', 'glenovo@gmail.com', 'GianlucaLe', 'Postgres23@', 'OSPEDALE CITTA DI CASTELLO');
insert into cittadini values(0000000000000002, 'MARCO', 'BERETTA', 'BRTMRC89P30P432P', 'mberetta@gmail.com', 'MarcoB', 'Postgres23@', 'OSPEDALE FORNAROLI'); 
insert into cittadini values(0000000000000003, 'MARIA', 'RANA', 'RNAMRI09P12J462D', 'mariarana@gmail.com', 'MariaR', 'Postgres23@', 'UNIVERSITARIA SANT ANDREA'); 
insert into cittadini values(0000000000000004, 'ANNA', 'GAROFALO', 'GRFANN08L04J398P', 'agarofalo@gmail.com','AnnaG', 'Postgres23@', 'LARIOFIERE');
insert into cittadini values(0000000000000005, 'PAOLO', 'BARILLA', 'BRLPLA09L30J456P', 'pbarilla@gmail.com', 'PaoloB', 'Postgres23@', 'TRADATEOSPEDALE'); 
insert into cittadini values(0000000000000006, 'MIRKO', 'BAROLO', 'BRLMRK12L24J192T', 'mbarolo@gmail.com', 'MirkoB', 'Postgres23@', 'UNIONE MONTANTA DEI SETTE COMUNI');
insert into cittadini values(0000000000000007, 'SAMUELE', 'IENOPOLI', 'NPLSML96R25P132K', 'sienopoli@gmail.com', 'SamueleI', 'Postgres23@', 'OSPEDALE DI CIVITA CASTELLANA'); 
insert into cittadini values(0000000000000016, 'ANTONIO', 'BANDERA', 'BNDNTN64T28P192J', 'abandera@gmail.com', 'AntonioB', 'Postgres23@', 'HUB DELLA SABINA');
insert into cittadini values(0000000000000015, 'ALESSANDRO', 'DEL PIERO', 'DLPLSS02Y06I198P', 'adelpiero@gmail.com', 'AleDelPiero', 'Postgres23@', 'MALPENSA FIERE');
insert into cittadini values(0000000000000010, 'ANDREA', 'VIERI', 'VRINDR89M14L145P', 'avieri@gmail.com', 'AndreaV', 'Postgres23@', 'CENTRO MAROSTICA');
insert into cittadini values(0000000000000011, 'LUCA', 'TONI', 'TNOLCU99P29F531R', 'ltoni@gmail.com', 'LucaT', 'Postgres23@', 'MALPENSA FIERE');
insert into cittadini values(0000000000000012, 'ELISABETTA', 'ANELLI', 'NLLLSB91T05B412V', 'eanelli@gmail.com', 'ElisaAnelli', 'Postgres23@', 'OSPEDALE FORNAROLI');
insert into cittadini values(0000000000000013, 'ROBERTA', 'RIVA', 'RVIRBR01O30U325M', 'rriva@gmail.com', 'RobyRiva', Postgres23@', 'LARIOFIERE');
insert into cittadini values(0000000000000014, 'FRANCESCA', 'COLOMBO', 'CLMFRN99R16G654J', 'fcolombo@gmail.com', 'FrancyColo', 'Postgres23@', 'MALPENSA FIERE');

vaccinati(id, nomecv, nome, cognome, cf, data_vaccino, tipo_vaccino)
insert into vaccinati values(0000000000000000, 'HUB LURATE CACCIVIO',  'ALESSANDRO', 'MULINO', 'MLNLSS11G02C478D', '01-01-2021', 'PFIZER');
insert into vaccinati values(0000000000000001, 'OSPEDALE CITTA DI CASTELLO','GIANLUCA', 'LENOVO', 'LNVGNL07H65D987V',  '01-01-2021', 'PFIZER');
insert into vaccinati values(0000000000000002, 'OSPEDALE FORNAROLI', 'MARCO', 'BERETTA', 'BRTMRC89P30P432P', '02-08-2021', 'ASTRAZENECA');
insert into vaccinati values(0000000000000003, 'UNIVERSITARIA SANT ANDREA', 'MARIA', 'RANA', 'RNAMRI09P12J462D', '05-04-2021', 'MODERNA');
insert into vaccinati values(0000000000000004, 'LARIOFIERE', 'ANNA', 'GAROFALO', 'GRFANN08L04J398P', '09-06-2021', 'J&J');
insert into vaccinati values(0000000000000005, 'TRADATEOSPEDALE', 'PAOLO', 'BARILLA', 'BRLPLA09L30J456P', '12-10-2021', 'ASTRAZENECA');
insert into vaccinati values(0000000000000006, 'UNIONE MONTANTA DEI SETTE COMUNI', 'MIRKO', 'BAROLO', 'BRLMRK12L24J192T', '16-08-2021', 'J&J');
insert into vaccinati values(0000000000000007, 'OSPEDALE DI CIVITA CASTELLANA', 'SAMUELE', 'IENOPOLI', 'NPLSML96R25P132K', '30-01-2021', 'PFIZER');
insert into vaccinati values(0000000000000016, 'HUB DELLA SABINA', 'ANTONIO', 'BANDERA', 'BNDNTN64T28P192J', '01-02-2021', 'ASTRAZENECA');
insert into vaccinati values(0000000000000015, 'MALPENSA FIERE', 'ALESSANDRO', 'DEL PIERO', 'DLPLSS02Y06I198P', '15-03-2021', 'PFIZER');
insert into vaccinati values(0000000000000010, 'CENTRO MAROSTICA', 'ANDREA', 'VIERI', 'VRINDR89M14L145P', '18-07-2021', 'MODERNA');
insert into vaccinati values(0000000000000011, 'MALPENSA FIERE', 'LUCA', 'TONI', 'TNOLCU99P29F531R', '30-06-2021', 'ASTRAZENECA');
insert into vaccinati values(0000000000000012, 'OSPEDALE FORNAROLI', 'ELISABETTA', 'ANELLI', 'NLLLSB91T05B412V', '03-07-2021', 'J&J');
insert into vaccinati values(0000000000000013, 'LARIOFIERE', 'ROBERTA', 'RIVA', 'RVIRBR01O30U325M', '15-07-2021', 'PFIZER');
insert into vaccinati values(0000000000000014, 'MALPENSA FIERE', 'FRANCESCA', 'COLOMBO', 'CLMFRN99R16G654J', '16-08-2021', 'MODERNA');

eventi_avversi(username, malditesta, febbre, tachicardia, dolorimuscolari, linfoadenopatia, crisi_ipertensiva, note)
insert into eventi_avversi values('Alemulino', 0, 2, 4, 5, 2, 5, 'GONFIORE AL BRACCIO NEL PUNTO DI INIEZIONE');
insert into eventi_avversi values('GianlucaLe', 2, 5, 3, 2, 3, 4, 'FEBBRE ALTA');
insert into eventi_avversi values('MarcoB', 4, 4, 2, 1, 4, 3, 'SENSO DI VOMITO');
insert into eventi_avversi values('MariaR', 3, 4, 1, 1, 1, 2, 'STANCHEZZA');
insert into eventi_avversi values('AnnaG', 0, 1, 0, 3, 0, 1, 'DOLORE AL BRACCIO NEL PUNTO DI INIEZIONE');
insert into eventi_avversi values('PaoloB', 1, 3, 4, 1, 1, 0, 'BATTITO CARDIACO FORTE');
insert into eventi_avversi values('MirkoB', 1, 5, 2, 2, 3, 4, 'SENSO DI NAUSEA');
insert into eventi_avversi values('SamueleI', 2, 5, 3, 5, 2, 2, 'FEBBRE ALTA');
insert into eventi_avversi values('AntonioB', 5, 4, 5, 4, 4, 3, 'GONFIORE AL BRACCIO NEL PUNTO DI INIEZIONE');
insert into eventi_avversi values('AleDelPiero', 3, 1, 0, 2, 2, 5, 'SENSO DI VOMITO');
insert into eventi_avversi values('AndreaV', 1, 2, 5, 1, 5, 1, 'BRIVIDI');
insert into eventi_avversi values('LucaT', 0, 5, 4, 5, 5, 0, 'FEBBRE ALTA');
insert into eventi_avversi values('ElisaAnelli', 2, 3, 4, 0, 1, 3, 'STANCHEZZA');
insert into eventi_avversi values('RobyRiva', 4, 5, 2, 1, 0, 0, 'DOLORE AL BRACCIO NEL PUNTO DI INIEZIONE');
insert into eventi_avversi values('FrancyColo', 3, 4, 2, 0, 5, 5, 'SENSO DI NAUSEA');

centri_vaccinali(nomecv, qualificatore, nome_via, civico, provincia, comune, cap, tipologia)
insert into centri_vaccinali values('TRADATEOSPEDALE', 'VIA', 'ZANABONI', 1, 'VA', 'TRADATE', 21049, 'OSPEDALIERO');
insert into centri_vaccinali values('MALPENSA FIERE', 'VIA', 'XI SETTEMBRE', 16, 'VA', 'BUSTO ARSIZIO', 21052, 'HUB');
insert into centri_vaccinali values('HUB LURATE CACCIVIO', 'PIAZZA', 'ALPINI', 10, 'CO', 'LURATE CACCIVIO', 22075, 'HUB');
insert into centri_vaccinali values('OSPEDALE FORNAROLI', 'VIA', 'AL DONATORE DI SANGUE', 50, 'MI', 'MAGENTA', 20013, 'OSPEDALIERO');
insert into centri_vaccinali values('LARIOFIERE', 'VIALE', 'RESEGONE', 9, 'CO', 'ERBA', 22036, 'HUB');
insert into centri_vaccinali values('OSPEDALE DI CIVITA CASTELLANA', 'VIA', 'FERRETTI', 169,  'VT', 'CIVITA CASTELLANA', 01033, 'OSPEDALIERO');
insert into centri_vaccinali values('HUB DELLA SABINA', 'VIA', 'DELLA MECCANICA', 32, 'RI', 'PASSO CORESE', 02032, 'HUB');
insert into centri_vaccinali values('UNIVERSITARIA SANT ANDREA', 'VIA', 'DI GROTTAROSSA', 1035, 'RM', 'ROMA', 00109, 'AZIENDALE');
insert into centri_vaccinali values('OSPEDALE MAGGIORE', 'VIALE', 'ANTONIO GRAMISCI', 14, 'PR', 'PARMA', 43126, 'OSPEDALIERO');
insert into centri_vaccinali values('UNIONE MONTANTA DEI SETTE COMUNI', 'VIA', 'STAZIONE', 1,'VI', 'ASIAGO', 36012, 'AZIENDALE');
insert into centri_vaccinali values('CENTRO MAROSTICA', 'VIA', '4 NOVEMBRE', 43, 'VI', 'MAROSTICA', 36063, 'HUB');

