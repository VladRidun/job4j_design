create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Смартфон INFINIX Note 12 Pro 8/256Gb, X676B, белый', 16490);
insert into devices(name, price) values ('Смартфон TECNO Camon 19 Pro 8/128Gb, черный', 15790);
insert into devices(name, price) values ('Смартфон REALME 10 4G RMX3630, черный', 18990);
insert into devices(name, price) values ('Смартфон vivo V25e 8/128Gb, восход солнца', 19990);
insert into devices(name, price) values ('Смартфон Huawei Nova 9 SE 8/128Gb, JLN-LX1, полуночный черный', 20390);
insert into devices(name, price) values ('Смартфон REALME 9 4G 8/128Gb, RMX3521, белый', 21990);


insert into people(name) values ('Vlad');
insert into people(name) values ('Marina');
insert into people(name) values ('Artem');


insert into devices_people(device_id, people_id) values (10,1);
insert into devices_people(device_id, people_id) values (11,1);
insert into devices_people(device_id, people_id) values (12,2);
insert into devices_people(device_id, people_id) values (13,2);
insert into devices_people(device_id, people_id) values (14,3);
insert into devices_people(device_id, people_id) values (15,3);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people as dp
join people p on dp.people_id = p.id 
join devices d on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price)
from devices_people as dp
join people p on dp.people_id = p.id 
join devices d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;