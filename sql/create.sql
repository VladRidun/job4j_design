create table roles(
     id serial primary key,
     role_name varchar(255)
);

create table users (
    id serial primary key,
    name varchar(255),
    roles_id int references roles(id),
);
 
 create table rules(
     id serial primary key,
     rule_name varchar(255)
);
 
 create table roles_rules(
     id serial primary key,
     roles_id int references roles(id),
     rules_id int references rules(id)
);

 create table category(
     id serial primary key,
     category_name varchar(255)
);

 create table state(
     id serial primary key,
     state_name varchar(255)
);

 create table item(
     id serial primary key,
     item_number int,
     users_id int references users(id)
     category_id int references category(id),
     state_id int references state(id)
 );

create table comments(
     id serial primary key,
     comment text,
     item_id int references item(id)
);

create table attachs(
     id serial primary key,
     attach text,
     item_id int references item(id)
);