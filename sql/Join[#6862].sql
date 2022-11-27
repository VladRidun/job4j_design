create table departments(
    id serial primary key,
   name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    departments_id int references departments(id)
);

insert into departments(name) values ('Дирекция');
insert into departments(name) values ('ФЭО');
insert into departments(name) values ('ПЭО');
insert into departments(name) values ('Бухгалтерия');
insert into departments(name) values ('Цех');
insert into departments(name) values ('ОТК');
insert into departments(name) values ('СГИ');
insert into departments(name) values ('СР');
insert into departments(name) values ('ИТ');

insert into employees(name, departments_id) values ('Кожин', 1);
insert into employees(name, departments_id) values ('Гречников', 1);
insert into employees(name, departments_id) values ('Логачева', 1);
insert into employees(name, departments_id) values ('Максимушкина', 2);
insert into employees(name, departments_id) values ('Ридун', 2);
insert into employees(name, departments_id) values ('Денисова', 2);
insert into employees(name, departments_id) values ('Эйгер', 2);
insert into employees(name, departments_id) values ('Савчук', 3);
insert into employees(name, departments_id) values ('Луганчкая', 3);
insert into employees(name, departments_id) values ('Казакова', 3);
insert into employees(name, departments_id) values ('Потапова', 4);
insert into employees(name, departments_id) values ('Ципленкова', 4);
insert into employees(name, departments_id) values ('Саблина', 4);
insert into employees(name, departments_id) values ('Пестерева', 4);
insert into employees(name, departments_id) values ('Ишмаева', 4);
insert into employees(name, departments_id) values ('Кузнецова', 4);
insert into employees(name, departments_id) values ('Шарапов', 5);
insert into employees(name, departments_id) values ('Храмов', 5);
insert into employees(name, departments_id) values ('Яковенко', 6);
insert into employees(name, departments_id) values ('Япарова', 6);
insert into employees(name, departments_id) values ('Мокеев', 7);
insert into employees(name, departments_id) values (' ', 8);
insert into employees(name, departments_id) values (' ', 9);


select * from employees e left join departments d on e.departments_id = d.id;
select * from departments d right join employees e on  d.id = e.departments_id;
select * from employees e full join departments d  on e.departments_id = d.id;
select * from employees e cross join departments d;

select * from departments d left join employees e on d.id = e.departments_id   where e.name is null;

select * from employees e left join departments d on e.departments_id = d.id;
select * from employees e right join departments d on e.departments_id = d.id;

create table teens(
   name varchar(255),
gender varchar(255)
);


insert into teens(name, gender) values ('Маша', 'Девочка');
insert into teens(name, gender) values ('Даша', 'Девочка');
insert into teens(name, gender) values ('Максим', 'Мальчик');
insert into teens(name, gender) values ('Денис', 'Мальчик');

select t1.name as teen1, t2.name as teen2, (t1.name , t2.name) as "Pairs" from teens t1 cross join teens t2 where t1.gender <> t2.gender;