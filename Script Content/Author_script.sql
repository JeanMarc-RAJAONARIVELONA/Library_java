create TYPE if not exists Sex AS ENUM ('M', 'F');
create table if not exists author(
  id_author serial primary key,
  author_name varchar(250) not null,
  author_sex Sex
);

insert into author (author_name, author_sex) values ('Alyssa Corthes', 'F'),('Britte Rayson', 'M'),('Vinson Skillanders', 'M');