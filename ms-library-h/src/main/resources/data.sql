insert into AUTHOR (`name`)
values ('masha');
insert into BOOK (`name`, `author_id`)
values ('3 medvedya', 1);
insert into AUTHOR_BOOK(`AUTHOR_ID`, `BOOK_ID`)
values (1, 1);
insert into GENRE (`name`)
values ('story');
insert into GENRE (`name`)
values ('fantasy');
insert into BOOK_GENRE(`BOOK_ID`, `GENRE_ID`)
values (1, 1);
insert into BOOK_GENRE(`BOOK_ID`, `GENRE_ID`)
values (1, 2);
insert into COMMENT (`BOOK_ID`, `MESSAGE`)
values (1, 'Perfect book'),
       (1, 'LIT!'),
       (1, 'Author is best!');