DROP DATABASE if exists persistenzaMC;
CREATE DATABASE persistenzaMC;
USE persistenzaMC;


CREATE TABLE Watchlist(
ID int PRIMARY KEY
);

CREATE TABLE Persona(
Email varchar(50) PRIMARY KEY,
Nome varchar(30) NOT NULL,
Cognome varchar(30) NOT NULL,
Admin boolean NOT NULL,
ID_Watchlist int NOT NULL,
FOREIGN KEY (ID_Watchlist) references Watchlist (ID)
);

CREATE TABLE Film(
ID int PRIMARY KEY,
Titolo varchar(50) NOT NULL,
Descrizione varchar(200) NOT NULL,
DataUscita date,
Regista varchar(30),
Durata timestamp, 
Genere varchar(25) NOT NULL,
Copertina varchar(50), 
Trailer varchar(200)
);

CREATE TABLE Contiene(
ID_Watchlist int,
ID_Film int,
PRIMARY KEY (ID_Watchlist, ID_Film),
FOREIGN KEY (ID_Watchlist) references Watchlist (ID),
FOREIGN KEY (ID_Film) references Film (ID)
);

CREATE TABLE Lista(
ID int PRIMARY KEY,
Nome varchar(30) NOT NULL,
Descrizione varchar(100),
Immagine varchar(50),
Email_Persona varchar(50) NOT NULL,
FOREIGN KEY (Email_Persona) references Persona (Email)
);

CREATE TABLE Include(
ID_Lista int,
ID_Film int,
PRIMARY KEY (ID_Lista, ID_Film),
FOREIGN KEY (ID_Lista) references Lista (ID),
FOREIGN KEY (ID_Film) references Film (ID)
);

CREATE TABLE Recensione(
Valutazione int NOT NULL,
Descrizione varchar(250),
Data date NOT NULL,
Email_Persona varchar(50),
ID_Film int,
PRIMARY KEY (Email_Persona, ID_Film),
FOREIGN KEY (Email_Persona) references Persona (Email),
FOREIGN KEY (ID_Film) references Film (ID)
);


