create table specialitet(
    id serial primary key,
    name varchar(255)
);

create table doctors(
    id serial primary key,
    name varchar(255),
    specialitet_id int references specialitet(id)
);

insert into specialitet(name) values ('surgeon');
insert into doctors(name, specialitet_id) VALUES ('Vlad', 1);

select * from doctors;

select * from specialitet where id in (select specialitet_id from doctors);
