DROP TABLE IF EXISTS AUTHOR;
DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS GENRE;

CREATE TABLE AUTHOR
(
    ID   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255)
);
CREATE TABLE BOOK
(
    ID   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    NAME      VARCHAR(255),
    AUTHOR_ID BIGINT             NOT NULL
);
CREATE TABLE GENRE
(
    ID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    NAME     VARCHAR(255)
);
CREATE TABLE BOOK_GENRE
(
    BOOK_ID  BIGINT NOT NULL,
    GENRE_ID BIGINT NOT NULL,
    PRIMARY KEY (`BOOK_ID`, `GENRE_ID`),
    CONSTRAINT `BOOK_ID_FK`
        FOREIGN KEY (`BOOK_ID`)
            REFERENCES `BOOK` (`ID`),
    CONSTRAINT `GENRE_ID_FK`
        FOREIGN KEY (`GENRE_ID`)
            REFERENCES `GENRE` (`ID`)
);

alter table BOOK
    add foreign key (AUTHOR_ID) references AUTHOR;

/*alter table BOOK_GENRE
    add foreign key (BOOK_ID) references BOOK;

alter table BOOK_GENRE
    add foreign key (GENRE_ID) references GENRE;
*/