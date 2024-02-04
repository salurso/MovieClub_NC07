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
Genere varchar(100) NOT NULL,
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
Nome varchar(30) NOT NULL,
Privata boolean NOT NULL,
Descrizione varchar(100),
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

INSERT INTO Persona (Email, Password, Nome, Cognome, Admin)
VALUES ('prova1@gmail.com', sha1('user1234'), 'Mario', 'Rossi', 0),
       ('prova2@gmail.com', sha1('user1234'), 'Luca', 'Bianchi', 0),
       ('prova3@gmail.com', sha1('user1234'), 'Giovanna', 'Verdi', 0),
       ('admin@gmail.com', sha1('admin1234'), 'Admin', 'Admin', 1);

INSERT INTO Film (Titolo, Descrizione, DataUscita, Regista, Durata, Genere, Copertina, Trailer)
VALUES ('Il Signore degli Anelli: La Compagnia dell''Anello', 'Un film fantastico basato sul romanzo di J.R.R. Tolkien', '2001-12-19', 'Peter Jackson', '02:58:00', 'Fantasy', 'https://esempio.com/copertina1.jpg', 'trailer1.mp4'),
       ('Forrest Gump', 'La straordinaria vita di Forrest Gump, dalla sua infanzia fino alla sua età adulta', '1994-07-06', 'Robert Zemeckis', '02:22:00', 'Drama', 'https://esempio.com/copertina2.jpg', 'trailer2.mp4');

INSERT INTO watchlist (ID_Persona, ID_Film)
VALUES (1, 1),
       (2, 1),
       (3, 2);

INSERT INTO Lista (Nome, Privata, Descrizione, Email_Persona)
VALUES ('Lista1', 1, 'Questa è una lista privata dell''utente 1', 'prova1@gmail.com'),
       ('Lista2', 0, 'Questa è una lista privata dell''utente 1', 'prova1@gmail.com'),
       ('Lista1', 1, 'Questa è una lista privata dell''utente 2', 'prova2@gmail.com');

INSERT INTO Include (ID_Lista, ID_Film)
VALUES (1, 1),
       (1, 2),
       (2, 1);

INSERT INTO Recensione (Valutazione, Descrizione, DataInserimento, Email_Persona, ID_Film)
VALUES (5, 'Uno dei migliori film di tutti i tempi', '2024-01-25', 'prova1@gmail.com', 1),
       (4, 'Una storia toccante e emozionante', '2024-01-26', 'prova2@gmail.com', 1),
       (4, 'Consigliato a tutti gli appassionati di cinema', '2024-01-26', 'prova3@gmail.com', 2);


-- INSERT INTO Film (ID, Titolo, Descrizione, DataUscita, Regista, Durata, Genere, Copertina, Trailer)
-- VALUES (1, 'Film1', 'Descrizione Film1', '2024-01-25', 'Regista Uno', '02:30:00', 'Action', 'copertina1.jpg', 'trailer1.mp4'),
--        (2, 'Film2', 'Descrizione Film2', '2024-01-26', 'Regista Due', '02:15:00', 'Comedy', 'copertina2.jpg', 'trailer2.mp4');
--
-- INSERT INTO Persona (Email, Password, Nome, Cognome, Admin)
-- VALUES ('user1@gmail.com', sha1('user1234'), 'UserUno', 'User', 0),
--        ('user2@gmail.com', sha1('user1234'), 'UserDue', 'User', 0),
--        ('mariorossi@gmail.com', sha1('user1234'), 'Mario', 'Rossi', 0),
--        ('admin@gmail.com', sha1('admin1234'), 'Admin', 'Admin', 1);
--
-- INSERT INTO Lista (Nome, Privata, Descrizione, Email_Persona)
-- VALUES ('Horror', 1, 'prova', 'user1@gmail.com');
--
-- INSERT INTO Recensione (Valutazione, Descrizione, DataInserimento, Email_Persona, ID_Film)
-- VALUES (5, 'film bellissimo', '2023-01-31', 'user1@gmail.com', 2),
--        (4, 'film unico', '2021-01-10', 'user2@gmail.com', 1),
--        (1, 'sconsiglio questo film', '2022-10-10', 'user2@gmail.com', 2);
--        (2, 'sconsiglio', '2022-10-10', 'mariorossi@gmail.com', 2);