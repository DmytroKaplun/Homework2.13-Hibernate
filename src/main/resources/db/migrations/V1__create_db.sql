CREATE SEQUENCE IF NOT EXISTS seq_client_id
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS seq_ticket_id
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS clients
(
    id BIGINT DEFAULT nextval('seq_client_id'),
    passport VARCHAR(100) UNIQUE NOT NULL,
    name VARCHAR(200) NOT NULL,
    CONSTRAINT pk_clients_id PRIMARY KEY (id),
    CONSTRAINT chk_name_length CHECK (LENGTH(name) BETWEEN 3 AND 200)
);

CREATE TABLE IF NOT EXISTS planets
(
    id VARCHAR(100) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS tickets
(
    id BIGINT DEFAULT nextval('seq_ticket_id'),
    created_at TIMESTAMP DEFAULT now(),
    client_id BIGINT NOT NULL,
    from_planet_id VARCHAR(100),
    to_planet_id VARCHAR(100),
    CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES clients(id),
    CONSTRAINT fk_from_planet_id FOREIGN KEY (from_planet_id) REFERENCES planets(id),
    CONSTRAINT fk_to_planet_id FOREIGN KEY (to_planet_id) REFERENCES planets(id),
    CONSTRAINT chk_from_to_planet CHECK (from_planet_id != to_planet_id)
);

