DROP DATABASE IF EXISTS Polisportiva;
CREATE DATABASE Polisportiva;
USE Polisportiva;

CREATE TABLE Utente(
                       email varchar(40) PRIMARY KEY,
                       pword char(40) NOT NULL,
                       nome varchar(20) NOT NULL,
                       cognome varchar(20) NOT NULL,
                       dateN varchar(10) NOT NULL,
                       is_admin boolean NOT NULL
);

CREATE TABLE Campo(
                      nome varchar(20) PRIMARY KEY,
                      descrizione varchar(100) NOT NULL,
                      tariffa float NOT NULL,
                      numGiocatori int NOT NULL

);

CREATE TABLE Prenotazione(
                             codice int AUTO_INCREMENT PRIMARY KEY,
                             oraStart int NOT NULL,
                             oraEnd 	int NOT NULL,
                             dateP datetime NOT NULL,
                             utente varchar(40) NOT NULL,
                             campo varchar(20) NOT NULL,
                             tariffaTotale float NOT NULL,
                             FOREIGN KEY (campo) references Campo (nome),
                             FOREIGN KEY (utente) references Utente (email)
);

CREATE TABLE Abbonamento(
                            codice int AUTO_INCREMENT PRIMARY KEY,
                            mesi int NOT NULL,
                            tariffa float NOT NULL,
                            tipologia varchar(20) NOT NULL
);

CREATE TABLE Attrezzatura(
                             codice int AUTO_INCREMENT PRIMARY KEY,
                             nome varchar(20) NOT NULL,
                             qta int NOT NULL,
                             tariffa float NOT NULL
);

CREATE TABLE Acquisto(
                         utente varchar(40) NOT NULL,
                         codiceAbb int NOT NULL,
                         dataAcquisto datetime NOT NULL,
                         FOREIGN KEY (utente) references Utente (email),
                         FOREIGN KEY (codiceAbb) references Abbonamento (codice)
);

CREATE TABLE Noleggio(
                         codicePren int NOT NULL,
                         codiceAttr int NOT NULL,
                         qta int NOT NULL,
                         FOREIGN KEY (codicePren) references Prenotazione (codice),
                         FOREIGN KEY (codiceAttr) references Attrezzatura (codice)
);

insert into Utente(email,pword,nome,cognome,dateN,is_admin)values
('rosabianchi@gmail.com','password1','rosa','bianchi','12-10-1998',false),
('admin1@gmail.com','password2','nome1','cognome1','13-04-1999',true),
('admin2@gmail.com','password3','nome2','cognome2','07-08-2000',true),
('admin3@gmail.com','password4','nome3','cognome3','15-06-1997',true);

insert into Campo(nome,descrizione,tariffa,numGiocatori) values
('Calcio','IL CAMPO È GRANDE 100 X 60, CON ERBETTA SINTETICA',20,22),
('Tennis','IL CAMPO È GRANDE 24 X 10 SINTETICO',10,4),
('Pallavolo','IL CAMPO È GRANDE 18 X 9. ALTEZZA RETE REGOLABILE, SI TROVA AL CHIUSO',15,12);

insert into Prenotazione(oraStart,oraEnd,dateP,utente,campo, tariffaTotale ) values
(10,12,'2022-02-12','rosabianchi@gmail.com','Pallavolo',30),
(15,16,'2022-02-12','rosabianchi@gmail.com','Pallavolo',15),
(19,20,'2022-02-12','rosabianchi@gmail.com','Pallavolo',15);