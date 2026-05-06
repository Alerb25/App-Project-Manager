--Base de Datos project-Manager
CREATE DATABASE proyectoPM;
USE proyectoPM;

--Tablas

--Project
CREATE TABLE Project(
    id_Pro INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    descr VARCHAR(100) NOT NULL,
    url VARCHAR(100) NOT NULL,
    dif INT NOT NULL,
    theme VARCHAR(50) NOT NULL,
    updated boolean NOT NULL
);

--User
CREATE TABLE User(
    id_U INT AUTO_INCREMENT PRIMARY KEY,
    name_tag VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    mail VARCHAR(100) NOT NULL,
    pass VARCHAR(50) NOT NULL,
    UNIQUE (name_tag, mail)
);

--Permission
CREATE TABLE Permission(
    id_Per INT AUTO_INCREMENT PRIMARY KEY,
    fk_Pro INT,
    fk_U INT,
    per INT NOT NULL, -- Los permisos son un codigo numerico 
    CONSTRAINT fk_Pro FOREIGN KEY (fk_Pro)
        REFERENCES Project(id_Pro),
    CONSTRAINT fk_U FOREIGN KEY (fk_U)
        REFERENCES User(id_U)
);