create table phonenumber(
    id serial primary key,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    phonenumber_id int references phonenumber(id) unique
);

insert into phonenumber(number) values (67434);
insert into phonenumber(number) values (64532);
insert into phonenumber(number) values (67424);

insert into people(name,phonenumber_id) values ('Borka', 1);
insert into people(name,phonenumber_id) values ('Masha', 2);
insert into people(name,phonenumber_id) values ('Natasha', 3);
insert into people(name) values ('Oleg');
insert into people(name) values ('Vika');

select pp.name as Имя,  pn.number as Номер
from people as pp join phonenumber as pn on pp.phonenumber_id = pn.id;

select  pn.number as "Номер телефона"
from people as pp join phonenumber as pn on pp.phonenumber_id = pn.id;

select pp.name as "Имя человека"
from people as pp join phonenumber as pn on pp.phonenumber_id = pn.id;