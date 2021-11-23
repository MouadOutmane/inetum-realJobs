CREATE TABLE USERS (
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    account_non_locked TYNIINT NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);

CREATE TABLE ATTEMPTS (
    id int(45) NOT NULL AUTO_INCREMENT,
    username varchar(45) NOT NULL,
    attempts varchar(45) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO USERS(username, password, account_non_locked) VALUES ('test','test',true)