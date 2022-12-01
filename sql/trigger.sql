create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products(name, producer, count, price) values('iphone 11', 'apple', 4, 45000);
insert into products(name, producer, count, price) values('mi 11 pro', 'xiaomi', 5, 40000);

create or replace function nalog()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted) ;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger nalog_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure nalog();

insert into products(name, producer, count, price) values('mi 11 pro', 'xiaomi', 5, 40000);

create or replace function nalog2()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id=new.id;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger nalog_before_trigger
    after insert on products
    referencing new table as inserted
    for each row
    execute procedure nalog2();

insert into products(name, producer, count, price) values('vivo 25', 'vivo', 2, 25000);

select * from products;

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);


create or replace function history()
    returns trigger as
$$
    BEGIN
        INSERT INTO history_of_price(name,price,date)
		 VALUES(new.name,new.price,now());
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';


create trigger history_trigger
    after insert on products
    referencing new table as inserted
    for each row
    execute procedure history();

insert into products(name, producer, count, price) values('vivo 25', 'vivo', 2, 25000);

select * from history_of_price;