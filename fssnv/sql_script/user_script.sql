
create table user_tbl
(
	user_id			varchar(15)		not null,
	user_passwd		varchar(100)	not null,
	user_name		varchar(30)		not null,
	user_office		varchar(30)		not null,
	user_lock		char(1)			not null,
	user_check		char(1)			not null,
	up_date			datetime		not null,
	PRIMARY KEY (user_id)
)

drop table menu_tbl;

create table menu_tbl
(
	menu_code_1	int not null,
	menu_code_2	int not null,
	menu_code_3	int not null,
	menu_name	varchar(80) not null,
	menu_uri    varchar(80) default "",
	menu_type	char(1) default "", 	/* D:daily, M:month, A:admin */
	support		int default 0, 			/* 1:download 대상, 2:koscom제공  */
	menu_id     varchar(8)  default "", /* menu id */
	PRIMARY KEY (menu_code_1, menu_code_2, menu_code_3)
)

create table accs_tbl
(
	user_id		varchar(15)		not null,
	menu_code_1		int			not null,
	menu_code_2		int			not null,
	menu_code_3		int			not null,
	PRIMARY KEY (user_id, menu_code_1, menu_code_2, menu_code_3)
)

-- 공통 코드 관리
create table code_tbl
(
	subject	char(5) 	not null,	/* menu name */
	types   int 		not null,	/* type */
	param   varchar(3) 	not null,	/* type value */
	t_name  varchar(40)	default "",	/* type name */
	p_name  varchar(40)	default "",	/* param name */
	remark  varchar(80) default "",	/* memo */
	PRIMARY KEY (subject, types, param)
)







