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
-- Table `usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario` ;

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `senha` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `perfil` ;

CREATE TABLE IF NOT EXISTS `perfil` (
  `usuario_id` INT NOT NULL,
  `descricao` TEXT(500) NULL,
  `foto` VARCHAR(255) NULL,
  INDEX `fk_perfil_usuario_idx` (`usuario_id` ASC) VISIBLE,
  PRIMARY KEY (`usuario_id`),
  CONSTRAINT `fk_perfil_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `imagemURL` VARCHAR(255) NOT NULL,
  `descricao` TEXT(500) NULL,
  `likes` INT NULL,
  `perfil_usuario_id1` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idPost_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_post_perfil1_idx` (`perfil_usuario_id1` ASC) VISIBLE,
  CONSTRAINT `fk_post_perfil1`
    FOREIGN KEY (`perfil_usuario_id1`)
    REFERENCES `perfil` (`usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comentario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comentario` ;

CREATE TABLE IF NOT EXISTS `comentario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `texto` TEXT(500) NOT NULL,
  `perfil_usuario_id` INT NOT NULL,
  `post_idPost` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idComentario_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_comentario_perfil1_idx` (`perfil_usuario_id` ASC) VISIBLE,
  INDEX `fk_comentario_post1_idx` (`post_idPost` ASC) VISIBLE,
  CONSTRAINT `fk_comentario_perfil1`
    FOREIGN KEY (`perfil_usuario_id`)
    REFERENCES `perfil` (`usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comentario_post1`
    FOREIGN KEY (`post_idPost`)
    REFERENCES `post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `listSeguido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `listSeguido` ;

CREATE TABLE IF NOT EXISTS `listSeguido` (
  `perfil_usuario_id` INT NOT NULL,
  `perfil_usuario_id1` INT NOT NULL,
  PRIMARY KEY (`perfil_usuario_id`, `perfil_usuario_id1`),
  INDEX `fk_perfil_has_perfil_perfil2_idx` (`perfil_usuario_id1` ASC) VISIBLE,
  INDEX `fk_perfil_has_perfil_perfil1_idx` (`perfil_usuario_id` ASC) VISIBLE,
  CONSTRAINT `fk_perfil_has_perfil_perfil1`
    FOREIGN KEY (`perfil_usuario_id`)
    REFERENCES `perfil` (`usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_perfil_has_perfil_perfil2`
    FOREIGN KEY (`perfil_usuario_id1`)
    REFERENCES `perfil` (`usuario_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
