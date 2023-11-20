CREATE TABLE departamento (id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, nombre varchar(50) NOT NULL UNIQUE);
CREATE TABLE empleado (id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, nombre varchar(100)
NOT NULL, salario decimal(10,2) DEFAULT NULL, departamento int(11) DEFAULT NULL,
FOREIGN KEY (departamento) REFERENCES hib_departamento(id) ON DELETE SET NULL ON UPDATE CASCADE);