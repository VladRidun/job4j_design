create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products(name, producer, count, price) values ('product1', 'producer1', 5, 50);
insert into products(name, producer, count, price) values ('product2', 'producer2', 6, 60);
insert into products(name, producer, count, price) values ('product3', 'producer3', 7, 70);
insert into products(name, producer, count, price) values ('product4', 'producer4', 8, 80);


create or replace procedure delete_by_id(u_id integer)
language 'plpgsql'
as $$
    BEGIN
           DELETE FROM products where id = u_id;
    END;
$$;

call delete_by_id(1);

select * from products;

insert into products(name, producer, count, price) values ('product5', 'producer5', 0, 90);

create or replace function f_delete(u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
              DELETE FROM products where id = u_id and products.count < 0 ;
return result;
    end;
$$;

select* from products;