CREATE TYPE Topic AS ENUM ('COMEDY', 'ROMANCE', 'OTHER');

create table if not exists books(
  id_books serial primary key,
  bookName varchar(200) not null,
  pageNumber int not null,
  topic Topic,
  is_borrowed boolean,
  id_author int  REFERENCES author(id_author)
);

insert into books (bookname, pagenumber, topic, is_borrowed, id_author) values ('East of Eden', 1, 'ROMANCE', true, 1),('Their Eyes Were Watching God', 2, 'COMEDY', true, 2),('White-tailed deer', 3, 'OTHER', true, 3);

