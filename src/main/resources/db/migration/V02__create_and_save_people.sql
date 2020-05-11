
CREATE TABLE person (
id DECIMAL (10,0) IDENTITY(1,1) NOT NULL PRIMARY KEY,
name NVARCHAR(50) NOT NULL,
public_place NVarchar(50) NULL,
number NVarchar(50) NULL,
complement NVarchar(50) NULL,
neighborhood NVarchar(50) NULL,
CEP NVarchar(50) NULL,
city NVarchar(50) NULL,
state NVarchar(50) NULL,
active bit NOT NULL
);

INSERT INTO person (name, public_place, number, complement, neighborhood, CEP, city, state, active) VALUES ('Ricardo Polido', 'logradouro 1', '306', 'n/a', '160545-212', 'Hilda Mandarino', 'Araçatuba', 'São Paulo', 1);
INSERT INTO person (name, public_place, number, complement, neighborhood, CEP, city, state, active) VALUES ('Jeff Lima', 'logradouro 1', '1578', 'n/a', '17451-312', 'Hilda Mandarino', 'Araçatuba', 'São Paulo', 1);
INSERT INTO person (name, public_place, number, complement, neighborhood, CEP, city, state, active) VALUES ('Guilherme Suganame', 'logradouro 1', '306', 'n/a', '145877-548', 'Hilda Mandarino',  'São Carlos', 'São Paulo', 1);
INSERT INTO person (name, public_place, number, complement, neighborhood, CEP, city, state, active) VALUES ('Evandro Ferraz', 'logradouro 1', '306', 'n/a', '878451-232', 'Planalto',  'Birigui', 'São Paulo', 1);
INSERT INTO person (name, public_place, number, complement, neighborhood, CEP, city, state, active) VALUES ('Murilo Ferraz', 'logradouro 1', '306', 'n/a', '886784-253', 'Planalto',  'Curitiba', 'Paraná', 1);
INSERT INTO person (name, public_place, number, complement, neighborhood, CEP, city, state, active) VALUES ('Jacke', 'logradouro 1', '306', 'n/a', '985478-291', 'Higienopólis',  'Presidente Prudente', 'São Paulo', 1);
INSERT INTO person (name, public_place, number, complement, neighborhood, CEP, city, state, active) VALUES ('Gabriel Pandolfi', 'logradouro 1', '306', 'n/a', '987451-912', 'Hilda Mandarino',  'Araçatuba', 'São Paulo', 1);
INSERT INTO person (name, public_place, number, complement, neighborhood, CEP, city, state, active) VALUES ('Nataly Souza', 'logradouro 1', '306', 'n/a', '214574-512', 'Concórdia',  'Araçatuba', 'São Paulo', 1);
INSERT INTO person (name, public_place, number, complement, neighborhood, CEP, city, state, active) VALUES ('Caio Bottaro', 'logradouro 1', '306', 'n/a', '356587-555', 'Jussara',  'Araçatuba', 'São Paulo', 1);
INSERT INTO person (name, public_place, number, complement, neighborhood, CEP, city, state, active) VALUES ('João Borrachinha', 'logradouro 1', '306', 'n/a', '251479-651', 'Centro',  'Araçatuba', 'São Paulo', 1);

