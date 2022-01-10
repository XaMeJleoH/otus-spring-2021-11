DROP TABLE IF EXISTS AUTHOR;
DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS BOOK_GENRE;
DROP TABLE IF EXISTS GENRE;
DROP TABLE IF EXISTS COMMENT;

CREATE TABLE AUTHOR
(
    ID   bigserial PRIMARY KEY,
    NAME VARCHAR(255)
);

create table avatars
(
    id        bigserial,
    photo_url varchar(8000),
    primary key (id)
);

CREATE TABLE BOOK
(
    ID        bigserial PRIMARY KEY,
    NAME      VARCHAR(255),
    AUTHOR_ID BIGINT references AUTHOR (ID)
);

CREATE TABLE GENRE
(
    ID   bigserial PRIMARY KEY,
    NAME VARCHAR(255)
);

CREATE TABLE BOOK_GENRE
(
    BOOK_ID  BIGINT NOT NULL,
    GENRE_ID BIGINT NOT NULL,
    CONSTRAINT `BOOK_ID_FK`
        FOREIGN KEY (`BOOK_ID`)
            REFERENCES `BOOK` (`ID`),
    CONSTRAINT `GENRE_ID_FK`
        FOREIGN KEY (`GENRE_ID`)
            REFERENCES `GENRE` (`ID`)
);

CREATE TABLE COMMENT
(
    ID      bigserial PRIMARY KEY,
    BOOK_ID BIGINT references BOOK (ID),
    MESSAGE VARCHAR(255)
);


/*alter table BOOK_GENRE
    add foreign key (BOOK_ID) references BOOK;

alter table BOOK_GENRE
    add foreign key (GENRE_ID) references GENRE;
*/