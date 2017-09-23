CREATE TABLE security (
  id     BIGINT(20)     NOT NULL AUTO_INCREMENT,
  code   VARCHAR(255)   NOT NULL,
  price  DECIMAL(15, 8) NOT NULL,
  spread DECIMAL(15, 8) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE trader (
  id   BIGINT(20)   NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE trade (
  id         BIGINT(20)     NOT NULL AUTO_INCREMENT,
  date       DATETIME       NOT NULL,
  security_id BIGINT(20)     NOT NULL,
  trader_id   BIGINT(20)     NOT NULL,
  price      DECIMAL(15, 8) NOT NULL,
  quantity   BIGINT(20)     NOT NULL,
  volume     DECIMAL(15, 8),
  side       INT(11),
  PRIMARY KEY (id),
  FOREIGN KEY (trader_id) REFERENCES trader (id),
  FOREIGN KEY (security_id) REFERENCES security (id)
);