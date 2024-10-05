CREATE DATABASE BD_PLAYBOX;
USE BD_PLAYBOX;

CREATE TABLE tb_consola (
    id_consola INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL
);

SELECT id_consola, descripcion nombreGenero FROM tb_consola;
INSERT INTO tb_consola (descripcion) VALUES
('PlayStation 5'),
('PlayStation 4'),
('Xbox Series'),
('Xbox One'),
('Nintendo Switch');

CREATE TABLE tb_genero (
    id_genero INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL
);

INSERT INTO tb_genero (descripcion) VALUES
('Acción'),
('Aventura'),
('Rol'),
('Simulación'),
('Disparos'),
('Deportes'),
('Carreras'),
('Estrategia'),
('Lucha');

SELECT id_genero, descripcion nombreGenero FROM tb_genero;


/*CREATE TABLE tb_videojuego (
    id_VideoJuego INT AUTO_INCREMENT PRIMARY KEY,
    codigoVideoJuego VARCHAR(25) NOT NULL,
    nombreVideoJuego VARCHAR(100) NOT NULL,
    id_consola INT,
    id_genero INT,
    descripcionVideoJuego VARCHAR(300) NOT NULL,
    stock INT,
    precio DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_consola) REFERENCES tb_consola(id_consola),
    FOREIGN KEY (id_genero) REFERENCES tb_genero(id_genero)
);*/

drop table tb_videojuego;

CREATE TABLE tb_videojuego (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(25) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    consola VARCHAR(100),
    genero VARCHAR(100),
    descripcion VARCHAR(300) NOT NULL,
    stock INT,
    precio DECIMAL(10, 2) NOT NULL
);

INSERT INTO tb_videojuego (codigo, nombre, consola, genero, descripcion, stock, precio) VALUES
('GOWRAG', 'God of War: Ragnarök', 'PlayStation 5', 'Acción', 'God of War: Ragnarök es el próximo capítulo de la legendaria saga de Kratos, donde los jugadores se sumergirán en una épica aventura nórdica llena de acción, combates intensos y una narrativa apasionante que explorará el mito del Ragnarok', 50, 150.00),
('TLZTTK', 'The Legend of Zelda: Tears of the Kingdom', 'Nintendo Switch', 'Aventura', 'The Legend of Zelda: Tears of the Kingdom te invita a una aventura mágica llena de desafíos y secretos. Explora un mundo encantado, resuelve acertijos y lucha contra peligrosos enemigos mientras buscas restaurar el equilibrio perdido en el reino', 25, 180.00),
('HALOIN', 'Halo Infinite', 'Xbox Series', 'Disparos', 'Halo Infinite lleva la acción y la emoción a nuevas alturas en el universo de Halo, ofreciendo épicas batallas y experiencias multiplayer emocionantes con el icónico Spartan Master Chief.', 10, 130.00),
('FFVIIRE', 'Final Fantasy VII Remake', 'PlayStation 4', 'RPG', 'Revive la épica historia de Final Fantasy VII en este remake completamente reimaginado con gráficos de última generación, un sistema de combate mejorado y una narrativa ampliada.', 40, 120.00),
('ACVALH', 'Assassin\'s Creed Valhalla', 'PlayStation 5', 'Aventura', 'Asume el papel de Eivor, un legendario guerrero vikingo, en su búsqueda de gloria en Assassin\'s Creed Valhalla. Explora un vasto mundo abierto lleno de desafíos, combates épicos y una rica narrativa histórica.', 30, 140.00),
('SMODYS', 'Super Mario Odyssey', 'Nintendo Switch', 'Plataformas', 'Únete a Mario en una aventura de plataformas en 3D a través de mundos increíbles en Super Mario Odyssey. Usa tu sombrero mágico, Cappy, para tomar el control de objetos y enemigos mientras buscas rescatar a la Princesa Peach.', 45, 100.00),
('CYPUNK', 'Cyberpunk 2077', 'Xbox Series', 'RPG', 'En Cyberpunk 2077, adéntrate en el oscuro y futurista mundo de Night City, donde cada elección que hagas afectará la narrativa. Personaliza tu personaje y enfrenta desafíos en este extenso RPG de mundo abierto.', 20, 160.00),
('RE8VIL', 'Resident Evil Village', 'PlayStation 5', 'Terror', 'Sumérgete en el horror con Resident Evil Village, el octavo título principal de la aclamada serie de terror de supervivencia. Enfrenta criaturas aterradoras y resuelve acertijos mientras exploras una misteriosa aldea.', 15, 150.00),
('MKT11U', 'Mortal Kombat 11 Ultimate', 'PlayStation 4', 'Lucha', 'Disfruta de la acción brutal y los combates intensos de Mortal Kombat 11 Ultimate, que incluye todos los personajes y modos de juego lanzados hasta la fecha, así como nuevas adiciones al elenco de luchadores.', 35, 90.00),
('RDR2PS', 'Red Dead Redemption 2', 'PlayStation 4', 'Aventura', 'Red Dead Redemption 2 es una épica aventura de mundo abierto en el Lejano Oeste, donde cada detalle cobra vida. Vive la historia de Arthur Morgan, un forajido que lucha por su lugar en un mundo cambiante.', 25, 110.00);

select*from tb_videojuego;

INSERT INTO tb_videojuego (codigoVideoJuego, nombreVideoJuego, id_consola, id_genero, descripcionVideoJuego, stock, precio) VALUES
('GOWRAG', 'God of War: Ragnarök', 1, 1, 'God of War: Ragnarök es el próximo capítulo de la legendaria saga de Kratos, donde los jugadores se sumergirán en una épica aventura nórdica llena de acción, combates intensos y una narrativa apasionante que explorará el mito del Ragnarok', 50, 150.00),
('TLZTTK', 'The Legend of Zelda: Tears of the Kingdom', 5, 2, 'The Legend of Zelda: Tears of the Kingdom te invita a una aventura mágica llena de desafíos y secretos. Explora un mundo encantado, resuelve acertijos y lucha contra peligrosos enemigos mientras buscas restaurar el equilibrio perdido en el reino', 25, 180.00),
('HALOIN', 'Halo Infinite', 3, 5, 'Halo Infinite lleva la acción y la emoción a nuevas alturas en el universo de Halo, ofreciendo épicas batallas y experiencias multiplayer emocionantes con el icónico Spartan Master Chief.', 10, 130.00);

/*---------------------------*/
CREATE PROCEDURE sp_listar_videojuegos()
SELECT 
e.id_VideoJuego,
 e.codigoVideoJuego,
 e.nombreVideoJuego,
 c.descripcion AS consola,
g.descripcion AS genero,
 e.descripcionVideoJuego,
 e.stock,
 e.precio 
FROM tb_videojuego e INNER JOIN tb_consola c INNER JOIN tb_genero g 
ON e.id_consola=C.id_consola AND e.id_genero=g.id_genero ORDER BY e.id_VideoJuego;

call sp_listar_videojuegos();
/*----------------------------------*/
UPDATE tb_videojuego SET codigoVideoJuego = ?, nombreVideoJuego= ?, id_consola= ?, id_genero= ?, descripcionVideoJuego= ?, stock= ?, precio = ? WHERE id_VideoJuego = ?; 
/*---------------------------*/
drop procedure sp_buscar_videojuego;
CREATE PROCEDURE sp_buscar_videojuego(IN id INT)
SELECT v.id_VideoJuego, v.codigoVideoJuego, v.nombreVideoJuego, c.descripcion consola ,g.descripcion genero,v.descripcionVideoJuego, v.stock, v.precio FROM tb_videojuego v 
INNER JOIN tb_genero g 
ON v.id_genero = g.id_genero
INNER JOIN
tb_consola c
ON v.id_consola = c.id_consola
WHERE id_VideoJuego = id;

call sp_buscar_videojuego(1);

/*---------------------------*/
CREATE PROCEDURE sp_registrar_videojuego(
    IN p_codigoVideoJuego VARCHAR(25),
    IN p_nombreVideoJuego VARCHAR(100),
    IN p_id_consola INT,
    IN p_id_genero INT,
    IN p_descripcionVideoJuego VARCHAR(300),
    IN p_stock INT,
    IN p_precio DECIMAL(10, 2)
    )
    INSERT INTO tb_videojuego
    (codigoVideoJuego, nombreVideoJuego, id_consola, id_genero, descripcionVideoJuego, stock, precio)
    VALUES
    (p_codigoVideoJuego, p_nombreVideoJuego, p_id_consola, p_id_genero, p_descripcionVideoJuego, p_stock, p_precio);
;

call sp_registrar_videojuego("CHRO", "Chrono Trigger",3,2,"Chrono Trigger: Una épica aventura temporal donde cada decisión afecta al destino del mundo, 
lleno de misterios, batallas y personajes memorables.",20,50.00);
/*----------------------------------------*/
DELETE FROM tb_videojuego WHERE id_VideoJuego = ?;
/*----------------------------------------*/

CREATE TABLE `tb_persona` (
 `id_persona` bigint unsigned NOT NULL AUTO_INCREMENT,
 `nombres` varchar(150) NOT NULL,
 `apellidos` varchar(150) NOT NULL,
 PRIMARY KEY (`id_persona`)
);

CREATE TABLE `tb_rol` (
 `id_rol` bigint unsigned NOT NULL AUTO_INCREMENT,
 `rol_name` varchar(150) NOT NULL,
 PRIMARY KEY (`id_rol`)
);

CREATE TABLE `tb_usuario` (
 `id_usuario` bigint unsigned NOT NULL AUTO_INCREMENT,
 `usuario` varchar(150) NOT NULL,
 `clave` varchar(150) NOT NULL,
 `id_persona` bigint unsigned DEFAULT NULL,
 `id_rol` bigint unsigned DEFAULT NULL,
 PRIMARY KEY (`id_usuario`),
 KEY `id_persona` (`id_persona`),
 KEY `id_rol` (`id_rol`),
 CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `tb_persona` (`id_persona`),
 CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`id_rol`) REFERENCES `tb_rol` (`id_rol`)
);
/*Usuario 1*/
INSERT INTO `tb_persona` VALUES 
(1,'Sigmund','Gebhardt');
/*Usuario 2*/
INSERT INTO `tb_persona` VALUES 
(null,'Jhonny','Castillo');

INSERT INTO `tb_rol` VALUES 
(null,'admin'),(null,'user');
/*Usuario 1*/
INSERT INTO `tb_usuario` VALUES 
(null,'Fuhrer','123456',1,1);
/*Usuario 2*/
INSERT INTO `tb_usuario` VALUES 
(null,'Peluchin','654321',2,2);

SELECT u.usuario, u.clave, p.nombres, p.apellidos, r.rol_name FROM tb_usuario u
INNER JOIN tb_persona p ON u.id_persona=p.id_persona
INNER JOIN tb_rol r ON u.id_rol=r.id_rol
WHERE u.usuario='Fuhrer' AND u.clave='123456'



