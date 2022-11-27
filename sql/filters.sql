create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id), 
    expired_date timestamp, 
    price float
);

insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Мороженое');


insert into product(name, type_id, expired_date, price) values('сыр Пармезман', 1, date '2022-10-01', 654);
insert into product(name, type_id, expired_date, price) values('сыр Мараздам', 1, date '2022-12-12', 705);
insert into product(name, type_id, expired_date, price) values('сыр Сфета', 1, date '2022-11-25', 850);
insert into product(name, type_id, expired_date, price) values('сыр брОнза', 1, date '2022-12-26', 560);

insert into product(name, type_id, expired_date, price) values('Молоко Село Зеленое реально прям зеленое', 2, date '2022-12-12', 134);
insert into product(name, type_id, expired_date, price) values('Молоко Домик в какой-то там деревне', 2, date '2022-11-23', 152);
insert into product(name, type_id, expired_date, price) values('Молоко С Котом в тельянежке', 2, date '2022-07-06', 106);
insert into product(name, type_id, expired_date, price) values('Молоко от Молочника под кайфом', 2, date '2022-07-13', 104);

insert into product(name, type_id, expired_date, price) values('Мороженое шоколадное мое любимое', 3, date '2022-08-15', 56);
insert into product(name, type_id, expired_date, price) values('Мороженое Пломбик', 3, date '2022-09-12', 65);
insert into product(name, type_id, expired_date, price) values('Мороженое Ванилька', 3, date '2022-03-09', 78);
insert into product(name, type_id, expired_date, price) values('Мороженое Фисташечка', 3, date '2022-05-09', 98);

insert into product(name, type_id, expired_date, price) values('Мороженое Gold Dinger', 3, date '2022-12-12', 856);
insert into product(name, type_id, expired_date, price) values('Сыр дорогущий', 3, date '2022-12-25', 905);
insert into product(name, type_id, expired_date, price) values('Молоко из Азбуки Вкуса', 3, date '2022-12-27', 876);


select p.name as "Название продукта", t.name as "Тип продукта"
from product as p 
join type as t 
on p.type_id = t.id
where t.name like '%Сыр%';

select p.name as "Название продукта", p.expired_date as "Тип продукта", p.price as "Цена"
from product as p where p.name LIKE '%Мороженое%';

select p.name as "Название продукта", p.expired_date as "Тип продукта", p.price as "Цена"
from product as p where p.expired_date  < current_date;

select p.name as "Название продукта", max(p.price) as "Цена"
from product as p 
join type as t 
on p.type_id = t.id
group by p.name
having max(p.price) >= 850;

select t.name as "Тип продукта", count(p.id)
from product as p 
join type as t 
on p.type_id = t.id
group by t.name;

select p.name as "Название продукта", t.name as "Тип продукта", p.price as "Цена"
from product as p 
join type as t 
on p.type_id = t.id
where t.name like '%Сыр%' or t.name like '%Молоко%';

select t.name as "Тип продукта", count(p.id)
from product as p 
join type as t 
on p.type_id = t.id
group by t.name
having t.name like '%Сыр%' and count(p.id) < 10;

select p.name as "Название продукта", t.name as "Тип продукта"
from product as p 
join type as t 
on p.type_id = t.id;
