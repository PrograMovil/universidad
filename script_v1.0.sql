-- MySQL Script generated by MySQL Workbench
-- 03/27/17 16:58:00
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema universidad
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `universidad` ;

-- -----------------------------------------------------
-- Schema universidad
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `universidad` DEFAULT CHARACTER SET utf8 ;
USE `universidad` ;

-- -----------------------------------------------------
-- Table `universidad`.`Carrera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Carrera` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `titulo` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Curso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `creditos` INT NULL,
  `horas_semanales` INT NULL,
  `nivel` VARCHAR(45) NULL,
  `ciclo` VARCHAR(45) NULL,
  `Carrera_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `codigo_UNIQUE` (`codigo` ASC),
  INDEX `fk_Curso_Carrera1_idx` (`Carrera_id` ASC),
  CONSTRAINT `fk_Curso_Carrera1`
    FOREIGN KEY (`Carrera_id`)
    REFERENCES `universidad`.`Carrera` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Ciclo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Ciclo` (
  `anio` INT NOT NULL,
  `numero` VARCHAR(45) NOT NULL,
  `fecha_Inicio` VARCHAR(45) NULL,
  `fecha_Finalizacion` VARCHAR(45) NULL,
  PRIMARY KEY (`anio`, `numero`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Horario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Horario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dias` VARCHAR(45) NULL,
  `horaInicial` VARCHAR(45) NULL,
  `horaFinal` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Usuario` (
  `idUsuario` VARCHAR(45) NOT NULL,
  `clave` VARCHAR(45) NULL,
  `tipo` INT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Profesor` (
  `cedula` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `Usuario_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cedula`),
  INDEX `fk_Profesor_Usuario1_idx` (`Usuario_id` ASC),
  CONSTRAINT `fk_Profesor_Usuario1`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `universidad`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Grupo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Grupo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `numero` INT NULL,
  `Horario_id` INT NOT NULL,
  `Curso_id` INT NOT NULL,
  `Profesor_cedula` VARCHAR(45) NOT NULL,
  `Ciclo_anio` INT NOT NULL,
  `Ciclo_numero` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Grupo_Horario1_idx` (`Horario_id` ASC),
  INDEX `fk_Grupo_Curso1_idx` (`Curso_id` ASC),
  INDEX `fk_Grupo_Profesor1_idx` (`Profesor_cedula` ASC),
  INDEX `fk_Grupo_Ciclo1_idx` (`Ciclo_anio` ASC, `Ciclo_numero` ASC),
  CONSTRAINT `fk_Grupo_Horario1`
    FOREIGN KEY (`Horario_id`)
    REFERENCES `universidad`.`Horario` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Grupo_Curso1`
    FOREIGN KEY (`Curso_id`)
    REFERENCES `universidad`.`Curso` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Grupo_Profesor1`
    FOREIGN KEY (`Profesor_cedula`)
    REFERENCES `universidad`.`Profesor` (`cedula`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Grupo_Ciclo1`
    FOREIGN KEY (`Ciclo_anio` , `Ciclo_numero`)
    REFERENCES `universidad`.`Ciclo` (`anio` , `numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Estudiante` (
  `cedula` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `fechaNac` DATE NULL,
  `Usuario_id` VARCHAR(45) NOT NULL,
  `Carrera_id` INT NOT NULL,
  PRIMARY KEY (`cedula`),
  INDEX `fk_Estudiante_Usuario1_idx` (`Usuario_id` ASC),
  INDEX `fk_Estudiante_Carrera1_idx` (`Carrera_id` ASC),
  CONSTRAINT `fk_Estudiante_Usuario1`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `universidad`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Estudiante_Carrera1`
    FOREIGN KEY (`Carrera_id`)
    REFERENCES `universidad`.`Carrera` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Nota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Nota` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `calificacion` FLOAT NULL,
  `Estudiante_cedula` VARCHAR(45) NOT NULL,
  `Curso_id` INT NOT NULL,
  INDEX `fk_Nota_Estudiante1_idx` (`Estudiante_cedula` ASC),
  INDEX `fk_Nota_Curso1_idx` (`Curso_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Nota_Estudiante1`
    FOREIGN KEY (`Estudiante_cedula`)
    REFERENCES `universidad`.`Estudiante` (`cedula`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Nota_Curso1`
    FOREIGN KEY (`Curso_id`)
    REFERENCES `universidad`.`Curso` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Matriculador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Matriculador` (
  `cedula` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `Usuario_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cedula`),
  INDEX `fk_Matriculador_Usuario1_idx` (`Usuario_id` ASC),
  CONSTRAINT `fk_Matriculador_Usuario1`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `universidad`.`Usuario` (`idUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Grupo_has_Estudiante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Grupo_has_Estudiante` (
  `Grupo_id` INT NOT NULL,
  `Estudiante_cedula` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Grupo_id`, `Estudiante_cedula`),
  INDEX `fk_Grupo_has_Estudiante_Estudiante1_idx` (`Estudiante_cedula` ASC),
  INDEX `fk_Grupo_has_Estudiante_Grupo1_idx` (`Grupo_id` ASC),
  CONSTRAINT `fk_Grupo_has_Estudiante_Grupo1`
    FOREIGN KEY (`Grupo_id`)
    REFERENCES `universidad`.`Grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Grupo_has_Estudiante_Estudiante1`
    FOREIGN KEY (`Estudiante_cedula`)
    REFERENCES `universidad`.`Estudiante` (`cedula`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Estudiante_has_Curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Estudiante_has_Curso` (
  `Estudiante_cedula` VARCHAR(45) NOT NULL,
  `Curso_id` INT NOT NULL,
  PRIMARY KEY (`Estudiante_cedula`, `Curso_id`),
  INDEX `fk_Estudiante_has_Curso_Curso1_idx` (`Curso_id` ASC),
  INDEX `fk_Estudiante_has_Curso_Estudiante1_idx` (`Estudiante_cedula` ASC),
  CONSTRAINT `fk_Estudiante_has_Curso_Estudiante1`
    FOREIGN KEY (`Estudiante_cedula`)
    REFERENCES `universidad`.`Estudiante` (`cedula`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Estudiante_has_Curso_Curso1`
    FOREIGN KEY (`Curso_id`)
    REFERENCES `universidad`.`Curso` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universidad`.`Administrador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `universidad`.`Administrador` (
  `cedula` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `Usuario_id` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cedula`),
  INDEX `fk_Administrador_Usuario1_idx` (`Usuario_id` ASC),
  CONSTRAINT `fk_Administrador_Usuario1`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `universidad`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `universidad`.`Carrera`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Carrera` (`id`, `codigo`, `nombre`, `titulo`) VALUES (1, 'EIF', 'Informatica', 'Bachillerato');
INSERT INTO `universidad`.`Carrera` (`id`, `codigo`, `nombre`, `titulo`) VALUES (2, 'MAT', 'Matematica', 'Licenciatura');

COMMIT;


-- -----------------------------------------------------
-- Data for table `universidad`.`Curso`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Curso` (`id`, `codigo`, `nombre`, `creditos`, `horas_semanales`, `nivel`, `ciclo`, `Carrera_id`) VALUES (1, 'EIF-200', 'Funda', 10, 5, 'I NIVEL', 'I', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `universidad`.`Ciclo`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Ciclo` (`anio`, `numero`, `fecha_Inicio`, `fecha_Finalizacion`) VALUES (2016, 'I', '17 Febrero', '20 Junio');

COMMIT;


-- -----------------------------------------------------
-- Data for table `universidad`.`Horario`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Horario` (`id`, `dias`, `horaInicial`, `horaFinal`) VALUES (1, 'Lunes', '12:45', '16:10');

COMMIT;


-- -----------------------------------------------------
-- Data for table `universidad`.`Usuario`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('admin', 'admin', 1);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('111', 'profesor', 3);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('33345', 'matriculador', 2);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('222', 'estudiante', 4);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('1112', 'profesor2', 3);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('1113', 'profesor3', 3);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('223', 'estudiante2', 4);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('224', 'estudiante3', 4);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('33346', 'matriculador2', 2);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('33347', 'matriculador3', 2);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('9998', 'admin2', 1);
INSERT INTO `universidad`.`Usuario` (`idUsuario`, `clave`, `tipo`) VALUES ('9997', 'admin3', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `universidad`.`Profesor`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Profesor` (`cedula`, `nombre`, `telefono`, `email`, `Usuario_id`) VALUES ('111', 'Georges', '88995566', 'j@g.c', '111');
INSERT INTO `universidad`.`Profesor` (`cedula`, `nombre`, `telefono`, `email`, `Usuario_id`) VALUES ('1112', 'Steven', '8643213', 'st.g.c', '1112');
INSERT INTO `universidad`.`Profesor` (`cedula`, `nombre`, `telefono`, `email`, `Usuario_id`) VALUES ('1113', 'Camaño', '647382', 'camañin@g.c', '1113');

COMMIT;


-- -----------------------------------------------------
-- Data for table `universidad`.`Grupo`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Grupo` (`id`, `numero`, `Horario_id`, `Curso_id`, `Profesor_cedula`, `Ciclo_anio`, `Ciclo_numero`) VALUES (1, 5, 1, 1, '111', 2016, 'I');

COMMIT;


-- -----------------------------------------------------
-- Data for table `universidad`.`Estudiante`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Estudiante` (`cedula`, `nombre`, `telefono`, `email`, `fechaNac`, `Usuario_id`, `Carrera_id`) VALUES ('222', 'Juan', '88234563', 'v@g.c', '2000-02-03', '222', 1);
INSERT INTO `universidad`.`Estudiante` (`cedula`, `nombre`, `telefono`, `email`, `fechaNac`, `Usuario_id`, `Carrera_id`) VALUES ('223', 'Pedro', '43423443', 'p@f.c', '1900-05-05', '223', 1);
INSERT INTO `universidad`.`Estudiante` (`cedula`, `nombre`, `telefono`, `email`, `fechaNac`, `Usuario_id`, `Carrera_id`) VALUES ('224', 'Jaimito', '34242344', 'o.c', '1960-04-04', '224', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `universidad`.`Nota`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Nota` (`id`, `calificacion`, `Estudiante_cedula`, `Curso_id`) VALUES (1, 80, '222', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `universidad`.`Matriculador`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Matriculador` (`cedula`, `nombre`, `telefono`, `email`, `Usuario_id`) VALUES ('33345', 'Jaime', '88667788', 'j@c.d', '33345');
INSERT INTO `universidad`.`Matriculador` (`cedula`, `nombre`, `telefono`, `email`, `Usuario_id`) VALUES ('33346', 'Navas', '52543242', 'nava@jilla.com', '33346');
INSERT INTO `universidad`.`Matriculador` (`cedula`, `nombre`, `telefono`, `email`, `Usuario_id`) VALUES ('33347', 'Centeno', '23423424', 'pate@cintazul.c', '33347');

COMMIT;


-- -----------------------------------------------------
-- Data for table `universidad`.`Administrador`
-- -----------------------------------------------------
START TRANSACTION;
USE `universidad`;
INSERT INTO `universidad`.`Administrador` (`cedula`, `nombre`, `telefono`, `email`, `Usuario_id`) VALUES ('admin', 'Jhonny Araña', '666-999', 'johnypalpueblo@figueres.cr', 'admin');
INSERT INTO `universidad`.`Administrador` (`cedula`, `nombre`, `telefono`, `email`, `Usuario_id`) VALUES ('9998', 'Abel Pacheco', '999-666', 'muchasgracias@pacheco.c', '9998');
INSERT INTO `universidad`.`Administrador` (`cedula`, `nombre`, `telefono`, `email`, `Usuario_id`) VALUES ('9997', 'Laura', '696-969', 'lauritaSi@g.c', '9997');

COMMIT;

USE `universidad`;

DELIMITER $$
USE `universidad`$$
CREATE DEFINER = CURRENT_USER TRIGGER `universidad`.`Profesor_AFTER_DELETE` AFTER DELETE ON `Profesor` FOR EACH ROW
BEGIN
	DELETE FROM `universidad`.`usuario` WHERE `idUsuario`=old.Usuario_id;
END$$

USE `universidad`$$
CREATE DEFINER = CURRENT_USER TRIGGER `universidad`.`Estudiante_AFTER_DELETE` AFTER DELETE ON `Estudiante` FOR EACH ROW
BEGIN
	DELETE FROM `universidad`.`usuario` WHERE `idUsuario`=old.Usuario_id;
END$$

USE `universidad`$$
CREATE DEFINER = CURRENT_USER TRIGGER `universidad`.`Matriculador_AFTER_DELETE` AFTER DELETE ON `Matriculador` FOR EACH ROW
BEGIN
	DELETE FROM `universidad`.`usuario` WHERE `idUsuario`=old.Usuario_id;
END$$

USE `universidad`$$
CREATE DEFINER = CURRENT_USER TRIGGER `universidad`.`Administrador_AFTER_DELETE` AFTER DELETE ON `Administrador` FOR EACH ROW
BEGIN
	DELETE FROM `universidad`.`usuario` WHERE `idUsuario`=old.Usuario_id;
END$$


DELIMITER ;
