create table people(
    id serial primary key,
    name varchar(255)
);

create table phonenumber(
    id serial primary key,
    number int,
	people_id int references people(id) unique
);

insert into people(name) values ('Borka'), ('Masha'), ('Natasha'), ('Oleg'), ('Vika');

insert into phonenumber(number, people_id) values (67434, 1);
insert into phonenumber(number, people_id) values (64532, 2);
insert into phonenumber(number, people_id) values (67424, 3);

select pp.name as Имя,  pn.number as Номер
from people as pp join phonenumber as pn on pn.people_id = pp.id;

select  pn.number as "Номер телефона"
from people as pp join phonenumber as pn on pn.people_id = pp.id;

select pp.name as "Имя человека"
from people as pp join phonenumber as pn on pn.people_id = pp.id;
