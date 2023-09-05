CREATE TABLE MESSAGE (
    id INT AUTO_INCREMENT PRIMARY KEY,
    roomId VARCHAR(50),
    sender varchar(50),
    message VARCHAR(255),
    dateTimeSent varchar(50)
);