DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY, NUMEROMATRICULA INT NOT NULL, NOMBRE VARCHAR(100) NOT NULL,APELLIDO VARCHAR(100) NOT NULL);

-- para test --
INSERT INTO ODONTOLOGOS(NUMEROMATRICULA, NOMBRE, APELLIDO) VALUES ('63497', 'Maria', 'Perez'), ('63678', 'Marta', 'Prado') ;