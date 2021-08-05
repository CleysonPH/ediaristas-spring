CREATE TABLE usuario (
  id bigint NOT NULL AUTO_INCREMENT,
  email varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
  senha varchar(255) NOT NULL,
  tipo varchar(8) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_usuario_email (email)
);