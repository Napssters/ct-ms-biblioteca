/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Nico
 * Created: 20/05/2023
 */

DROP TABLE PRESTAMO IF EXISTS;
CREATE TABLE PRESTAMO (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    isbn VARCHAR(10) NOT NULL,
    identificacionUsuario VARCHAR(10) NOT NULL,
    tipoUsuario INT NOT NULL,
    fechaMaximaDevolucion VARCHAR(10) NOT NULL
);

DROP TABLE USUARIOS IF EXISTS;
CREATE TABLE USUARIOS(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tipoUsuario INT NOT NULL
);

        
INSERT INTO usuarios(tipoUsuario)
VALUES (1);
INSERT INTO usuarios(tipoUsuario)
VALUES (2);
INSERT INTO usuarios(tipoUsuario)
VALUES (3);