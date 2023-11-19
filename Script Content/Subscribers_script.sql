create table if not exists subscribers(
  id_subscribers serial primary key,
  subscribers_name varchar(250) not null,
  subscribers_email varchar(150),
  references varchar(250)
);

insert into subscribers (subscribers_name, subscribers_email) values ('Tilly Banaszkiewicz', 'tbanaszkiewicz0@artisteer.com'),('Hurleigh Harland', 'hharland1@soundcloud.com'),('Arda Pleager', 'apleager2@vkontakte.ru');
