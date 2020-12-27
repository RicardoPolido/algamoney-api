CREATE TABLE accountposting(
  id DECIMAL(10, 0)IDENTITY(1, 1)NOT NULL PRIMARY KEY,
  description NVARCHAR(50)NOT NULL,
  due_date DATE NOT NULL,
  payment_date DATE,
  amount DECIMAL(10, 2) NOT NULL,
  observation NVARCHAR(100),
  type NVARCHAR(20) NOT NULL,
  category_id DECIMAL(10, 0) NOT NULL,
  person_id DECIMAL(10, 0) NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category(id),
  FOREIGN KEY (person_id) REFERENCES person(id)
);

INSERT INTO AccountPosting (description, due_date, payment_date, amount, observation, type, category_id, person_id) VALUES ('Salário Mensal', '2020-03-12', null, 2500.00, 'Salário Base', 'INCOME', 1, 1);
INSERT INTO AccountPosting (description, due_date, payment_date, amount, observation, type, category_id, person_id) VALUES ('Bahamas', '2020-02-20', '2020-03-01', 99.92, null, 'EXPENSE', 2, 2);
INSERT INTO AccountPosting (description, due_date, payment_date, amount, observation, type, category_id, person_id) VALUES ('Top Club', '2020-06-12', null, 120, null, 'INCOME', 3, 3);
INSERT INTO AccountPosting (description, due_date, payment_date, amount, observation, type, category_id, person_id) VALUES ('Café', '2020-03-10', null, 8.32, null, 'EXPENSE', 4, 2);
INSERT INTO AccountPosting (description, due_date, payment_date, amount, observation, type, category_id, person_id) VALUES ('Lanche', '2020-03-10', null, 8.32, null, 'EXPENSE', 4, 1);