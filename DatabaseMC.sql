DROP DATABASE if exists persistenzaMC;
CREATE DATABASE persistenzaMC;
USE persistenzaMC;


CREATE TABLE Persona(
ID int PRIMARY KEY auto_increment,
Email varchar(50) UNIQUE NOT NULL,
Password VARCHAR(44) NOT NULL,
Nome varchar(30) NOT NULL,
Cognome varchar(30) NOT NULL,
Admin boolean NOT NULL
);

CREATE TABLE Film(
ID int PRIMARY KEY auto_increment,
Titolo varchar(60) NOT NULL,
Descrizione varchar(220) NOT NULL,
DataUscita date,
Regista varchar(60),
Durata time, 
Genere varchar(80) NOT NULL,
Copertina varchar(150), 
Trailer varchar(100)
);

CREATE TABLE watchlist(
ID_Persona int,
ID_Film int,
PRIMARY KEY (ID_Persona, ID_Film),
FOREIGN KEY (ID_Persona) references Persona (ID),
FOREIGN KEY (ID_Film) references Film (ID)
);

CREATE TABLE Lista(
ID int PRIMARY KEY,
Nome varchar(30) NOT NULL,
Visibilita boolean NOT NULL,
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



