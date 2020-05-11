CREATE TABLE category (
    id DECIMAL (10,0) IDENTITY(1,1) NOT NULL PRIMARY KEY,
    name NVARCHAR(50) NOT NULL,
);

INSERT INTO category (name) VALUES ('Lazer');
INSERT INTO category (name) VALUES ('Alimentação');
INSERT INTO category (name) VALUES ('Supermercado');
INSERT INTO category (name) VALUES ('Farmácia');
INSERT INTO category (name) VALUES ('Outros');