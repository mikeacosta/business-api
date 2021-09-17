USE dev_db;

# create user account
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'Abc12345';
CREATE USER 'admin'@'%' IDENTIFIED BY 'Abc12345';

# grant privledges to user
GRANT ALL PRIVILEGES ON dev_db.* TO 'admin'@'localhost';
GRANT ALL PRIVILEGES ON dev_db.* TO 'admin'@'%';

# check privledges
SHOW GRANTS FOR 'admin'@'localhost';
SHOW GRANTS FOR 'admin'@'%';