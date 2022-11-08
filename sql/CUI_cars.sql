create table cars (
id serial primary key,
	producer varchar(255),
	model varchar(255),
	gosnumber text
);
insert into cars(producer, model, gosnumber) values('Рено', 'Меган3', 'Т869УР163');
select * from cars;
update cars set model = 'Меган4';
select * from cars;
delete from cars;
select * from cars;