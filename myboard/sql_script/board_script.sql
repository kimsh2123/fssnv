
----- table -----
create table tbl_board(
	bno INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(200) NOT NULL,
	content TEXT NULL,
	writer VARCHAR(50) NOT NULL,
	regdate TIMESTAMP NOT NULL DEFAULT now(),
	viewcnt INT DEFAULT 0,
	replycnt int default 0,
	PRIMARY KEY (bno)
);

create table tbl_reply(
	rno int NOT NULL AUTO_INCREMENT,
	bno int not null default 0,
	replytext varchar(1000) not null,
	replyer varchar(50) not null,
	regdate TIMESTAMP NOT NULL DEFAULT now(),
	updatedate TIMESTAMP NOT NULL DEFAULT now(),
	primary key(rno)
);

alter table tbl_reply add constraint fk_board
foreign key(bno) references tbl_board(bno);

create table tbl_user(
	uid varchar(50) NOT NULL,
	upw varchar(50) NOT NULL,
	uname varchar(100) NOT NULL,
	upoint int NOT NULL DEFAULT 0,
	sessionkey varchar(50) not null default 'none',
	sessionlimit timestamp,
	primary key(uid)
)

create table tbl_message(
	mid int not null auto_increment,
	targetid varchar(50) not null,
	sender varchar(50) not null,
	message text not null,
	opendate timestamp,
	senddate timestamp not null default now(),
	primary key(mid)
)

alter table tbl_message add constraint fk_usertarget
foreign key (targetid) references tbl_user(uid);

alter table tbl_message add constraint fk_usersender
foreign key (sender) references tbl_user(uid);

create table tbl_attach(
	fullName varchar(150) not null,
	bno int not null,
	regdate timestamp default now(),
	primary key(fullName)
);

alter table tbl_attach add constraint fk_board_attach
foreign key(bno) references tbl_board(bno);

----- table -----

show tables;

desc tbl_board;
desc tbl_reply;
desc tbl_user;
desc tbl_message;
desc tbl_attach;

select LAST_INSERT_ID() from tbl_user;

insert into tbl_board(title, content, writer) (select title, content, writer from tbl_board);

select * from tbl_board order by bno limit 0,20;

update tbl_board set replycnt = (select count(rno) from tbl_reply where bno=tbl_board.bno) where bno > 0;

select * from tbl_user;


