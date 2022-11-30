create table car_bodies(
    id serial primary key,
   name varchar(255)
);


create table car_engines(
    id serial primary key,
   name varchar(255)
);

create table car_transmissions(
    id serial primary key,
   name varchar(255)
);

create table cars(
    id serial primary key,
   name varchar(255),
body_id int references car_bodies(id),
engine_id int references car_engines(id),
transmission_id int references car_transmissions(id)
);

insert into car_bodies(name)
values ('седан');
insert into car_bodies(name) 
values ('универсал');
insert into car_bodies(name) 
values ('хэтчбек');
insert into car_bodies(name) 
values ('кроссовер');
insert into car_bodies(name) 
values ('внедорожник');
insert into car_bodies(name) 
values ('пикап');
insert into car_bodies(name) 
values ('купе');


insert into car_engines(name) 
values ('бензин');
insert into car_engines(name) 
values ('дизель');
insert into car_engines(name) 
values ('гибрид');
insert into car_engines(name) 
values ('электро');
insert into car_engines(name) 
values ('квантовый');

insert into car_transmissions(name) values ('механика');
insert into car_transmissions(name) values ('робот');
insert into car_transmissions(name) values ('вариатор');
insert into car_transmissions(name) values ('автомат');
insert into car_transmissions(name) values ('гибридная');

insert into cars(name, body_id, engine_id, transmission_id) 
values ('Рено Меган', 3, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('лада веста', 3, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('лада веста св', 2, 1, 3);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('тойота королла', 1, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('киа сид', 2, 1, 4);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('рено колеос', 4, 2, 4);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('митсубиши паджеро спорт', 5, 2, 4);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('митсубиши аутлендер', 4, 3, 3);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('форд ф-150', 5, 1, 4);
insert into cars(name, body_id, engine_id, transmission_id) 
values ('тесла', 2, 4, 3);
insert into cars(name, body_id, engine_id) 
values ('чери электро', 2, 4);
insert into cars(name, engine_id, transmission_id) 
values ('хавал', 4, 3);
insert into cars(name, body_id, transmission_id)
values ('субару', 4, 3);

select c.name as Машина, cb.name as Кузов, ce.name as Двигатель, ct.name as Трансмиссия 
from cars as c 
left join car_bodies as cb on cb.id = c.body_id
left join car_engines as ce on ce.id = c.body_id
left join car_transmissions as ct  on ct.id = c.body_id;

select c.name as Машина, cb.name as Кузов, ce.name as Двигатель, ct.name as Трансмиссия 
from cars as c 
left join car_bodies as cb on cb.id = c.body_id
left join car_engines as ce on ce.id = c.engine_id
left join car_transmissions as ct  on ct.id = c.transmission_id;

select cb.name as Кузов from car_bodies as cb
left join cars as c 
on cb.id = c.body_id 
where c.body_id is null;

select ce.name as Двигатель from car_engines as ce 
left join cars as c 
on ce.id = c.engine_id 
where c.engine_id is null;

select ct.name as Трансмиссия from car_transmissions as ct 
left join cars as c 
on ct.id = c.transmission_id 
where c.transmission_id is null;