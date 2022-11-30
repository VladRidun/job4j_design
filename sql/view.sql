create table pacients(
    id serial primary key,
    name varchar(255)
);

create table diseases(
    id serial primary key,
    name varchar(255)
);

create table specializations(
    id serial primary key,
    name varchar(255)
);
create table doctors(
    id serial primary key,
    name varchar(255),
    specialization_id int references specializations(id),
);

create table card(
    id serial primary key,
    pacient_id int references pacients(id),
    doctor_id int references doctors(id),
    disease_id int references diseases(id)
);

insert into pacients(name) values('Vlad');
insert into pacients(name) values('Marina');
insert into pacients(name) values('Artem');


insert into diseases(name) values('Allergy');
insert into diseases(name) values('ORVI');

insert into specializations(name) values('terapevt');
insert into specializations(name) values('allergolog');

insert into doctors(name, specialization_id) values('Esina Olga', 1);
insert into doctors(name, specialization_id) values('Osipov Alexey', 2);

insert into card(pacient_id, doctor_id, disease_id) values(1, 2, 1);
insert into card(pacient_id, doctor_id, disease_id) values(2, 1, 2);
insert into card(pacient_id, doctor_id, disease_id) values(3, 2, 1);


create view show_paceints_with_allergy
    as select p.name as Пациент,  dis.name as Заболевание, doc.name from card c
         join pacients p on p.id = c.pacient_id
         join diseases dis on dis.id = c.disease_id
         join doctors doc on doc.id = c.doctor_id
         group by (p.name, dis.name, doc.name) having dis.name = 'Allergy';