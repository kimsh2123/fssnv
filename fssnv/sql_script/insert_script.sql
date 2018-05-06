insert into code_tbl values('ag113', 1, '1', '증권사와 관계', '특수관계인','');
insert into code_tbl values('ag113', 1, '2', '증권사와 관계', '기타','');

insert into code_tbl values('ag113', 2, '11', '거래구분', '시장매수','');
insert into code_tbl values('ag113', 2, '12', '거래구분', '장외매수','');
insert into code_tbl values('ag113', 2, '13', '거래구분', '주식배당','');
insert into code_tbl values('ag113', 2, '14', '거래구분', '유무상증자','');
insert into code_tbl values('ag113', 2, '15', '거래구분', '전환권','');
insert into code_tbl values('ag113', 2, '16', '거래구분', '기타 상품주식 증가','');
insert into code_tbl values('ag113', 2, '21', '거래구분', '시장매도','');
insert into code_tbl values('ag113', 2, '22', '거래구분', '장외매도','');
insert into code_tbl values('ag113', 2, '23', '거래구분', '기타 상품주식 감소','');

insert into code_tbl values('ag113', 3, '1', '시장조성관련 매매여부', '시장조성','');
insert into code_tbl values('ag113', 3, '2', '시장조성관련 매매여부', '기타','');



insert into menu_tbl values (1,0,0,'일보고','','',0,'');
insert into menu_tbl values (1,1,0,'사용자입력','','',0,'');
insert into menu_tbl values (1,1,1,'채권인수내역','/fss/daily/ag132List','D',1,'ag132');
insert into menu_tbl values (1,1,2,'자금거래내역','/fss/daily/ag172List','D',1,'ag172');
insert into menu_tbl values (1,1,3,'특정금전신탁거래내역','/fss/daily/ag162List','D',1,'ag162');
insert into menu_tbl values (1,2,0,'Koscom제공','','',0,'');
insert into menu_tbl values (1,2,1,'FileUpload','','',0,'');
insert into menu_tbl values (1,2,2,'BaseFileUpload','','',0,'');
insert into menu_tbl values (1,2,3,'상품주식 거래내역','/fss/daily/ag113List','D',2,'ag113');
insert into menu_tbl values (1,2,4,'위탁자 대량매매','/fss/daily/ag115List','D',2,'ag115');
insert into menu_tbl values (1,2,5,'자사주위탁 매매내역','/fss/daily/ag116List','D',2,'ag116');
insert into menu_tbl values (1,2,6,'상품선물옵션 매매내역','/fss/daily/ag141List','D',2,'ag141');
insert into menu_tbl values (1,2,7,'위탁자선물옵션 매매내역','/fss/daily/ag142List','D',2,'ag142');
insert into menu_tbl values (1,2,8,'주식익일정정 및 선물옵션 정정내역','/fss/daily/ag143List','D',2,'ag143');
insert into menu_tbl values (1,2,9,'증권회사 추천종목현황','/fss/daily/ag117List','D',2,'ag117');
insert into menu_tbl values (1,2,10,'조건부 거래내역','/fss/daily/ag124List','D',2,'ag124');
insert into menu_tbl values (1,2,11,'상품채권등 거래내역','/fss/daily/ag131List','D',2,'ag131');
insert into menu_tbl values (1,2,12,'생품채권등 결제내역','/fss/daily/ag133List','D',2,'ag133');
insert into menu_tbl values (1,2,13,'상품계정 집합투자증권 보유내역','/fss/daily/ag161List','D',2,'ag161');
insert into menu_tbl values (1,3,0,'FileDownload','/fss/fileupdw/DailyBaseUpload','D',0,'');
insert into menu_tbl values (2,0,0,'월보고','','',0,'');
insert into menu_tbl values (2,1,0,'사용자입력','','',0,'');
insert into menu_tbl values (2,1,1,'역외펀드자산 운용내역','/fss/month/ag118List','M',1,'ag118');
insert into menu_tbl values (2,1,2,'상품외화증권인수 및 매매내역','/fss/month/ag119List','M',1,'ag119');
insert into menu_tbl values (2,1,3,'해외현지법인 및 역외펀드의 외화증권인수 및 매매내역','/fss/month/ag120List','M',1,'ag120');
insert into menu_tbl values (2,1,4,'해외현지법인 및 역외펀드의 국내주식 매매내역','/fss/month/ag121List','M',1,'ag121');
insert into menu_tbl values (2,1,5,'투자증권 변동내역','/fss/month/ag123List','M',1,'ag123');
insert into menu_tbl values (2,1,6,'소송내역','/fss/month/ag182List','M',1,'ag182');
insert into menu_tbl values (2,1,7,'주식인수 내역','/fss/month/ag125List','M',1,'ag125');
insert into menu_tbl values (2,1,8,'채무보증 내역','/fss/month/ag146List','M',1,'ag146');
insert into menu_tbl values (2,2,0,'Koscom제공','','',0,'');
insert into menu_tbl values (2,2,1,'FileUpload','','',0,'');
insert into menu_tbl values (2,2,2,'BaseFileUpload','','',0,'');
insert into menu_tbl values (2,2,3,'증권보유현황','/fss/month/ag114List','M',2,'ag114');
insert into menu_tbl values (2,2,4,'선물옵션 손실과다 계좌현황','/fss/month/ag145List','M',2,'ag145');
insert into menu_tbl values (2,2,5,'임직원 영업실적 등 내역','/fss/month/ag111List','M',2,'ag111');
insert into menu_tbl values (2,2,6,'점포별 특이계좌내역','/fss/month/ag112List','M',2,'ag112');
insert into menu_tbl values (2,3,0,'FileDownload','','M',0,'');
insert into menu_tbl values (3,0,0,'Authentication','','',0,'');
insert into menu_tbl values (3,1,0,'UserRegistry','','A',0,'');
insert into menu_tbl values (3,2,0,'UserModify','','A',0,'');
insert into menu_tbl values (3,3,0,'PasswordModify','','A',0,'');

truncate table menu_tbl;



insert into accs_tbl values ('fssadmin',1,0,0);
insert into accs_tbl values ('fssadmin',1,1,0);
insert into accs_tbl values ('fssadmin',1,1,1);
insert into accs_tbl values ('fssadmin',1,1,2);
insert into accs_tbl values ('fssadmin',1,1,3);
insert into accs_tbl values ('fssadmin',1,2,0);
insert into accs_tbl values ('fssadmin',1,2,1);
insert into accs_tbl values ('fssadmin',1,2,2);
insert into accs_tbl values ('fssadmin',1,2,3);
insert into accs_tbl values ('fssadmin',1,2,4);
insert into accs_tbl values ('fssadmin',1,2,5);
insert into accs_tbl values ('fssadmin',1,2,6);
insert into accs_tbl values ('fssadmin',1,2,7);
insert into accs_tbl values ('fssadmin',1,2,8);
insert into accs_tbl values ('fssadmin',1,2,9);
insert into accs_tbl values ('fssadmin',1,2,10);
insert into accs_tbl values ('fssadmin',1,2,11);
insert into accs_tbl values ('fssadmin',1,2,12);
insert into accs_tbl values ('fssadmin',1,2,13);
insert into accs_tbl values ('fssadmin',1,3,0);
insert into accs_tbl values ('fssadmin',2,0,0);
insert into accs_tbl values ('fssadmin',2,1,0);
insert into accs_tbl values ('fssadmin',2,1,1);
insert into accs_tbl values ('fssadmin',2,1,2);
insert into accs_tbl values ('fssadmin',2,1,3);
insert into accs_tbl values ('fssadmin',2,1,4);
insert into accs_tbl values ('fssadmin',2,1,5);
insert into accs_tbl values ('fssadmin',2,1,6);
insert into accs_tbl values ('fssadmin',2,1,7);
insert into accs_tbl values ('fssadmin',2,1,8);
insert into accs_tbl values ('fssadmin',2,2,0);
insert into accs_tbl values ('fssadmin',2,2,1);
insert into accs_tbl values ('fssadmin',2,2,2);
insert into accs_tbl values ('fssadmin',2,2,3);
insert into accs_tbl values ('fssadmin',2,2,4);
insert into accs_tbl values ('fssadmin',2,2,5);
insert into accs_tbl values ('fssadmin',2,2,6);
insert into accs_tbl values ('fssadmin',2,3,0);
insert into accs_tbl values ('fssadmin',3,0,0);
insert into accs_tbl values ('fssadmin',3,1,0);
insert into accs_tbl values ('fssadmin',3,2,0);
insert into accs_tbl values ('fssadmin',3,3,0);

