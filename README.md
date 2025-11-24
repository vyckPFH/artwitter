https://prod.liveshare.vsengsaas.visualstudio.com/join?C70166EA882DC70941D01D4C8F4150615E9C

Link documento
https://docs.google.com/document/d/1so7kuPEv5khzN2Ed-kbaJltDUe46RjPzqPxtrhS8jNI/edit?usp=sharing

Script SQL

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS artwitterBD;
USE artwitterBD;
-- -----------------------------------------------------
-- Table `Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Usuario` ;

CREATE TABLE IF NOT EXISTS `Usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `Perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Perfil` ;

CREATE TABLE IF NOT EXISTS `Perfil` (
  `Usuario_id` INT NOT NULL,
  `descricao` TEXT(500) NULL,
  `foto` VARCHAR(255) NULL,
  INDEX `fk_Perfil_Usuario_idx` (`Usuario_id` ASC) VISIBLE,
  PRIMARY KEY (`Usuario_id`),
  CONSTRAINT `fk_Perfil_Usuario`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Post` ;

CREATE TABLE IF NOT EXISTS `Post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `imagemURL` VARCHAR(255) NOT NULL,
  `descricao` TEXT(500) NULL,
  `likes` INT NULL,
  `Perfil_Usuario_id1` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idPost_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Post_Perfil1_idx` (`Perfil_Usuario_id1` ASC) VISIBLE,
  CONSTRAINT `fk_Post_Perfil1`
    FOREIGN KEY (`Perfil_Usuario_id1`)
    REFERENCES `Perfil` (`Usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Comentario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Comentario` ;

CREATE TABLE IF NOT EXISTS `Comentario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `texto` TEXT(500) NOT NULL,
  `Perfil_Usuario_id` INT NOT NULL,
  `Post_idPost` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idComentario_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Comentario_Perfil1_idx` (`Perfil_Usuario_id` ASC) VISIBLE,
  INDEX `fk_Comentario_Post1_idx` (`Post_idPost` ASC) VISIBLE,
  CONSTRAINT `fk_Comentario_Perfil1`
    FOREIGN KEY (`Perfil_Usuario_id`)
    REFERENCES `Perfil` (`Usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comentario_Post1`
    FOREIGN KEY (`Post_idPost`)
    REFERENCES `Post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `listSeguido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `listSeguid` ;

CREATE TABLE IF NOT EXISTS `listSeguid` (
  `Perfil_Usuario_id` INT NOT NULL,
  `Perfil_Usuario_id1` INT NOT NULL,
  PRIMARY KEY (`Perfil_Usuario_id`, `Perfil_Usuario_id1`),
  INDEX `fk_Perfil_has_Perfil_Perfil2_idx` (`Perfil_Usuario_id1` ASC) VISIBLE,
  INDEX `fk_Perfil_has_Perfil_Perfil1_idx` (`Perfil_Usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_Perfil_has_Perfil_Perfil1`
    FOREIGN KEY (`Perfil_Usuario_id`)
    REFERENCES `Perfil` (`Usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Perfil_has_Perfil_Perfil2`
    FOREIGN KEY (`Perfil_Usuario_id1`)
    REFERENCES `Perfil` (`Usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
