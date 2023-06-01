CREATE TABLE worker (
                        ID INT NOT NULL AUTO_INCREMENT,
                        NAME VARCHAR(1000) NOT NULL,
                        BIRTHDAY DATE CHECK (YEAR(BIRTHDAY) > 1900),
    LEVEL ENUM('Trainee', 'Junior', 'Middle', 'Senior') NOT NULL,
    SALARY INT CHECK (SALARY >= 100 AND SALARY <= 100000),
    PRIMARY KEY (ID)
);

CREATE TABLE client (
                        ID INT NOT NULL AUTO_INCREMENT,
                        NAME VARCHAR(1000) NOT NULL,
                        PRIMARY KEY (ID)
);

CREATE TABLE project (
                         ID INT NOT NULL AUTO_INCREMENT,
                         CLIENT_ID INT NOT NULL,
                         START_DATE DATE NOT NULL,
                         FINISH_DATE DATE NOT NULL,
                         PRIMARY KEY (ID),
                         FOREIGN KEY (CLIENT_ID) REFERENCES client(ID) ON DELETE CASCADE
);

ALTER TABLE client ALTER COLUMN ID RESTART WITH 1;

CREATE TABLE project_worker (
                                PROJECT_ID INT NOT NULL,
                                WORKER_ID INT NOT NULL,
                                PRIMARY KEY (PROJECT_ID, WORKER_ID),
                                FOREIGN KEY (PROJECT_ID) REFERENCES project(ID) ON DELETE CASCADE,
                                FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)
);