USE persistenzaMC;
INSERT INTO Persona (Email, Password, Nome, Cognome, Admin)
VALUES ('prova4@gmail.com', sha1('user1234'), 'Mario', 'Rossi', 0),
       ('prova5@gmail.com', sha1('user1234'), 'Luca', 'Bianchi', 0),
       ('prova6@gmail.com', sha1('user1234'), 'Giovanna', 'Verdi', 0);
       
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