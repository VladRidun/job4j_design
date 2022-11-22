insert into roles(role_name) values ('user');
insert into rules(rule_name) values ('read, write, edit');
insert into roles_rules (roles_id, rules_id) values (1, 1);

insert into users(name, roles_id) VALUES ('Vlad', 1);

insert into category(category_name) values ('SAP');
insert into state(state_name) values ('Подана');

insert into item(item_number, users_id, category_id, state_id) VALUES ('00001', 1, 1, 1 );

insert into comments(comment, item_id) values ('Не выгружается отчет', 1);
insert into attachs(attach, item_id) values ('C:\Users\OVROQ2R\Desktop\скрин_ошибки.jpg', 1);