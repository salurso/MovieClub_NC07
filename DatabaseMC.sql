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
ID int auto_increment PRIMARY KEY,
Nome varchar(30) NOT NULL unique,
Privata boolean NOT NULL,
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
DataInserimento date NOT NULL,
Email_Persona varchar(50),
ID_Film int,
PRIMARY KEY (Email_Persona, ID_Film),
FOREIGN KEY (Email_Persona) references Persona (Email),
FOREIGN KEY (ID_Film) references Film (ID)
);

INSERT INTO Film (Titolo, Descrizione, DataUscita, Regista, Durata, Genere, Copertina, Trailer)
VALUES ('Film1', 'Descrizione Film1', '2024-01-25', 'Regista1', '02:30:00', 'Azione', 'copertina1.jpg', 'trailer1.mp4'),
       ('Film2', 'Descrizione Film2', '2024-01-26', 'Regista2', '02:15:00', 'Commedia', 'copertina2.jpg', 'trailer2.mp4')



