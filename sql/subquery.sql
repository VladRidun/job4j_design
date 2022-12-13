CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);


insert into customers(first_name, last_name, age, country) 
values('Джонни', 'Дэп', 56, 'США');
insert into customers(first_name, last_name, age, country) 
values('Винсент', 'Касель', 52, 'Франция');
insert into customers(first_name, last_name, age, country) 
values('Михаил', 'Боярский', 60, 'Россия');
insert into customers(first_name, last_name, age, country) 
values('Владимир', 'Вдовиченков', 51, 'Россия');
insert into customers(first_name, last_name, age, country) 
values('Виктор', 'Хореняк', 32, 'Россия');
insert into customers(first_name, last_name, age, country) 
values('Дмитрий', 'Нагиев', 55, 'Россия');
insert into customers(first_name, last_name, age, country) 
values('Брэд', 'Пит', 58, 'США');


select * from customers 
where age = (select min(age) from customers);


CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values( 56, 1);
insert into orders(amount, customer_id) values( 52, 2);
insert into orders(amount, customer_id) values( 51, 4);
insert into orders(amount, customer_id) values(55, 6);
insert into orders(amount, customer_id) values(58, 7);

SELECT *
FROM customers 
WHERE customers.id 
NOT IN (SELECT orders.customer_id FROM orders);