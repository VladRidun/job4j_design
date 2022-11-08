 create table cars(
     id serial primary key,
     name varchar(255)
 );
 
 create table people(
     id serial primary key,
     name varchar(255)
 );
 
 create table people_cars(
     id serial primary key,
     people_id int references people(id),
     cars_id int references cars(id)
 );


insert into cars(name) values ('lada kalina');
insert into cars(name) values ('opel kadet');
insert into cars(name) values ('lada vesta');

insert into people(name) values ('Vlad Ridun');
insert into people(name) values ('Marina Bykova');
insert into people(name) values ('Artem Ridun');

insert into people_cars(people_id, cars_id) values (1, 1);
insert into people_cars(people_id, cars_id) values (1, 2);
insert into people_cars(people_id, cars_id) values (1, 3);
insert into people_cars(people_id, cars_id) values (2, 1);
insert into people_cars(people_id, cars_id) values (2, 2);
insert into people_cars(people_id, cars_id) values (3, 3);
select * from people_cars;
