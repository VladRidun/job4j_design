select pp.name as Имя,  pn.number as Номер
from people as pp join phonenumber as pn on pp.phonenumber_id = pn.id;

select  pn.number as "Номер телефона"
from people as pp join phonenumber as pn on pp.phonenumber_id = pn.id;

select pp.name as "Имя человека"
from people as pp join phonenumber as pn on pp.phonenumber_id = pn.id;