create table cars(
    id serial primary key,
    seria int,
    number int
);

create table vincodes(
    id serial primary key,
    name varchar(255)
);

create table cars_vincodes(
    id serial primary key,
    cars_id int references cars(id) unique,
    vincodes_id int references vincodes(id) unique
);
