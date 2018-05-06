
insert into user_tbl values('fssadmin','abc123','sung','kosmos','N','Y',now());

select * from user_tbl;
select * from menu_tbl;
select * from accs_tbl;

select m.menu_code_1
	 , m.menu_code_2
	 , m.menu_code_3
	 , m.menu_name
	 , m.menu_uri
	 , m.menu_type
	 , m.support
from menu_tbl m, accs_tbl a
where m.menu_code_1 = a.menu_code_1
and m.menu_code_2 = a.menu_code_2
and m.menu_code_3 = a.menu_code_3
and a.user_id = 'fssadmin'
order by m.menu_code_1, m.menu_code_2, m.menu_code_3;

select menu_id, menu_name, menu_uri from menu_tbl where menu_type='D' and support=2 order by menu_uri;


update menu_tbl set menu_uri = '/fss/fileupdw/dailyBaseUpload' where menu_code_1=1 and menu_code_2=2 and menu_code_3=2;

select * from menu_tbl;






