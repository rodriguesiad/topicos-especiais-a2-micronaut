CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(150) NOT NULL,
                         email VARCHAR(150) UNIQUE NOT NULL,
                         senha VARCHAR(255) NOT NULL -- para guardar senha hash
);

CREATE TABLE evento (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        data DATE NOT NULL,
                        local VARCHAR(255),
                        usuario_id BIGINT NOT NULL,
                        ativo BOOL NOT NULL,
                        FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE inscricao (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           evento_id BIGINT NOT NULL,
                           usuario_id BIGINT NOT NULL,
                           data_inscricao DATE NOT NULL,
                           ativo BOOL NOT NULL,
                           FOREIGN KEY (evento_id) REFERENCES evento(id),
                           FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);