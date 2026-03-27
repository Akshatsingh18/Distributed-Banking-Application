CREATE TABLE LoginModel (
    aid PRIMARY KEY,
    username VARCHAR(255),
    fullname VARCHAR(255),
    password VARCHAR(255) NOT NULL, 
);

INSERT INTO LoginModel (aid,username,fullname, password) VALUES ('1', 'admin','akshat','admin');
