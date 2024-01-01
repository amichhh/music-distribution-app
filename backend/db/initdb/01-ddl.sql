DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS artist CASCADE;
DROP TABLE IF EXISTS artist_favorite CASCADE;
DROP TABLE IF EXISTS balance CASCADE;
DROP TABLE IF EXISTS belong CASCADE;
DROP TABLE IF EXISTS company CASCADE;
DROP TABLE IF EXISTS music CASCADE;
DROP TABLE IF EXISTS playlist CASCADE;
DROP TABLE IF EXISTS playlist_item CASCADE;
DROP TABLE IF EXISTS sales_record CASCADE;

CREATE TABLE account (
  account_id VARCHAR(30) NOT NULL,
  name VARCHAR(30) NOT NULL,
  password VARCHAR(64) NOT NULL,
  authority VARCHAR(10) NOT NULL,
  status VARCHAR(10) NOT NULL,
  PRIMARY KEY (account_id)
);

CREATE TABLE company (
  id VARCHAR(40) NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(400) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE balance (
  account_id VARCHAR(30) NOT NULL,
  amount INT NOT NULL,
  PRIMARY KEY (account_id),
  FOREIGN KEY (account_id) REFERENCES account (account_id)
);

CREATE TABLE belong (
  account_id VARCHAR(30) NOT NULL,
  company_id VARCHAR(40) NOT NULL,
  PRIMARY KEY (account_id),
  FOREIGN KEY (account_id) REFERENCES account (account_id),
  FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE artist (
  id SERIAL NOT NULL,
  name VARCHAR(100) NOT NULL,
  outline VARCHAR(400) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE artist_favorite (
  id SERIAL NOT NULL,
  account_id VARCHAR(30) NOT NULL,
  artist_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account (account_id),
  FOREIGN KEY (artist_id) REFERENCES artist (id)
);

CREATE TABLE music (
  id SERIAL NOT NULL,
  title VARCHAR(100) NOT NULL,
  artist_id BIGINT NOT NULL,
  company_id VARCHAR(40) NOT NULL,
  price INT NOT NULL,
  release_day DATE NOT NULL,
  status VARCHAR(10) NOT NULL, 
  purchase_count INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (artist_id) REFERENCES artist (id),
  FOREIGN KEY (company_id) REFERENCES company (id)
);

CREATE TABLE playlist (
  id SERIAL NOT NULL,
  account_id VARCHAR(30) NOT NULL,
  title VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account (account_id)
);

CREATE TABLE playlist_item (
  id SERIAL NOT NULL,
  playlist_id BIGINT NOT NULL,
  music_id BIGINT NOT NULL,
  sort_order INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (playlist_id) REFERENCES playlist (id),
  FOREIGN KEY (music_id) REFERENCES music (id)
);

CREATE TABLE sales_record (
  id VARCHAR(40) NOT NULL,
  account_id VARCHAR(30) NOT NULL,
  music_id BIGINT NOT NULL,
  company_id VARCHAR(40) NOT NULL,
  purchase_price INT NOT NULL,
  purchase_date TIMESTAMP NOT NULL,
  payment_method VARCHAR(30) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (account_id) REFERENCES account (account_id),
  FOREIGN KEY (music_id) REFERENCES music (id),
  FOREIGN KEY (company_id) REFERENCES company (id)
);