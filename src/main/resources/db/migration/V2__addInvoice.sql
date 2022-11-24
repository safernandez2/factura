CREATE TABLE IF NOT EXISTS invoice(
	id SERIAL,
	code Varchar(100) NOT NULL,
	create_at DATE NOT NULL,
	total DECIMAL(8,2) NULL,
	client_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE(code),
    FOREIGN KEY (client_id) REFERENCES client (id)
);