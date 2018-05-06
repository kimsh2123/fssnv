/*
 * 2016.04 daily base upload 대상
 */

desc ag113_tbl;
desc ag115_tbl;
desc ag116_tbl;
desc ag117_tbl;
desc ag124_tbl;
desc ag131_tbl;
desc ag133_tbl;
desc ag141_tbl;
desc ag142_tbl;
desc ag143_tbl;
desc ag161_tbl;

desc ag132_tbl;
desc ag162_tbl;
desc ag172_tbl;



-- 상품주식 거래 내역 ag113
create table ag113_tbl
(
    num            int       	not null,    /* 1.일련번호 */
    make_date      varchar(8)   not null,    /* 2.작성기준일자 YYYYMMDD 형식 */
    comp_code      varchar(7)   null,  /* 3.증권회사코드(감독원부여) */
    issues_no      varchar(12)  null,  /* 4.종목코드(거래소부여) */
    trade_type     varchar(2)   null,  /* 5.거래구분(11-시장(거래소,코스닥)매수, 12-장외매수,13-주식배당,14-유무상증자 */
                                       /*    15-전환권(신주인수권,권리행사 포함), 16-기타 상품주식 증가, 22-장외매도*/
                                       /*    21-시장(거래소,코스닥)매도, 23-기타 상품주식 감소 */
    trade_date     varchar(8)   null,  /* 6.거래일자 YYYYMMDD 형식 */
    comp_name      varchar(30)  null,  /* 7.증권회사명 */
    issu_name      varchar(40)  null,  /* 8.종목명(거래소부여) */
    publish_code   varchar(5)   null,  /* 9.발행사 코드 */
    publish_rel    varchar(1)   null,  /* 10.증권사,발행사간 관계(1-특수관계인,2-기타) */
    ordr_time      varchar(6)   null,  /* 11.주문시각 HHMMSS 형식 */
    ordr_qty       varchar(19)  null,  /* 12.주문수량 */
    ordr_price     varchar(19)  null,  /* 13.주문단가 */
    amd_rtn        varchar(19)  null,  /* 14.정정회수 */
    amd_qty        varchar(19)  null,  /* 15.정정수량 */
    last_ccl_time  varchar(6)   null,  /* 16.최종취소시각 HHMMSS 형식 */
    ccl_rtn        varchar(19)  null,  /* 17.취소회수 */
    ccl_qty        varchar(19)  null,  /* 18.취소수량 */
    last_cntr_time varchar(6)   null,  /* 19.최종체결시각 HHMMSS 형식 */
    cntr_qty       varchar(19)  null,  /* 20.체결수량 */
    cntr_price     varchar(19)  null,  /* 21.체결단가 */
    book_value     varchar(19)  null,  /* 22.장부가액(수량*단가)로 기재,취득원가 */
    counterpart    varchar(20)  null,  /* 23.상대방(매매상대방을 기재) */
    cpart_rel      varchar(1)   null,  /* 24.증권사,상대방간 관계(1-특수관계인,2-기타) 시장매매인 경우 "2"로 기재 */
    holding_qty    varchar(19)  null,  /* 25.보유수량/거래후 */
    market_mature  varchar(1)   null,  /* 26.시장조성관련 매매여부(1-시장조성,2-기타) */
    up_date        datetime  	null,  /* 27.Upload Date */
    up_id          varchar(15) 	null,  /* 28.Upload User id */
    PRIMARY KEY (make_date, num)
)

-- 위탁자 대량 매매 ag115
create table ag115_tbl
(
	num				int			not null,	/* 1.일련번호 */
	make_date		varchar(8)	not null,	/* 2.작성대상일자 */
	comp_code		varchar(7)	not null,	/* 3.증권회사코드(감독원부여) */
	issues_no		varchar(12)	not null,	/* 4.종목코드(거래소부여) */
	buysell			varchar(1)	not null,	/* 5.거래구분 (1-매수,2-매도) */
	trade_date		varchar(8)	not null,	/* 6.거래일자 YYYYMMDD 형식 */

	comp_name		varchar(30)	null,	/* 7.증권회사명 */
	issu_name		varchar(40)	null,	/* 8.종목명(거래소부여) */
	account			varchar(15)	null,	/* 9.계좌번호 */
	registry_no		varchar(8)	null,	/* 10.계좌정보(개인-주민번호증 뒤 7자리, - 신설*/
										/*    법인-사업자등록번호 앞 8자리, */
										/*    외국인-투자자등록번호 마지막1자리 제외 번호 */
	invest_code		varchar(1)	null,	/* 11.투자자분류(1-가목,5-마목) */
	acnt_rel		varchar(1)	null,	/* 12.증권사,계좌주간 관계(1-특수관계인,2-기타) */
	publish_code	varchar(5)	null,	/* 13.발행사 코드 */
	ordr_time		varchar(6)	null,	/* 14.주문시각 HHMMSS 형식 */
	ordr_qty		varchar(19)	null,	/* 15.주문수량 */
	ordr_price		varchar(19)	null,	/* 16.주문단가 */
	amd_rtn			varchar(19)	null,	/* 17.정정회수 */
	amd_qty			varchar(19)	null,	/* 18.정정수량 */
	last_ccl_time	varchar(6)	null,	/* 19.최종취소시각 HHMMSS 형식 */
	ccl_rtn			varchar(19)	null,	/* 20.취소회수 */
	ccl_qty			varchar(19)	null,	/* 21.취소수량 */
	last_cntr_time	varchar(6)	null,	/* 22.최종체결시각 HHMMSS 형식 */
	cntr_qty		varchar(19)	null,	/* 23.체결수량 */
	cntr_price		varchar(19)	null,	/* 24.체결단가 */
	cntr_amt		varchar(19)	null,	/* 25.체결금액,체결수량*체결단가 - 신설 */
	manager			varchar(30)	null,	/* 26.관리자 */
	cyb_ordr_yn		varchar(1)	null,	/* 27.사이버주문 여부(1-사이버주문,2-기타) */
	dpst_exp_acnt	varchar(1)	null,	/* 28.증거금면제계좌여부(1-면제계좌,2-기타) */
	up_date			datetime	null,	/* 29.Upload Date */
	up_id			varchar(15)	null,	/* 30.Upload User id */
	PRIMARY KEY (make_date, num)
)

-- 자사주 위탁매매 내역 ag116
create table ag116_tbl
(
    num            int       	not null,	/* 1.일련번호 */
    make_date      varchar(8)   not null,   /* 2.작성기준일자 YYYYMMDD 형식 */
    comp_code      varchar(7)   null,       /* 3.증권회사코드(감독원부여) */
    issues_no      varchar(12)  null,       /* 4.종목코드(거래소부여) */
    buysell        varchar(1)   null,       /* 5.거래구분 (1-매수,2-매도) */
    trade_date     varchar(8)   null,       /* 6.거래일자 YYYYMMDD 형식 */
    comp_name      varchar(30)  null,       /* 7.증권회사명 */
    issu_name      varchar(40)  null,       /* 8.종목명(거래소부여) */
    account        varchar(15)  null,       /* 9.계좌번호 */
    publish_code   varchar(5)   null,       /* 10.발행사 코드 */
    publish_rel    varchar(1)   null,       /* 11.증권사,발행사간 관계(1-특수관계인,2-기타) */
    ordr_time      varchar(6)   null,       /* 12.주문시각 HHMMSS 형식 */
    ordr_qty       varchar(19)  null,       /* 15.주문수량 */
    ordr_price     varchar(19)  null,       /* 16.주문단가 */
    cntr_qty       varchar(19)  null,       /* 17.체결수량 */
    cntr_price     varchar(19)  null,       /* 18.체결단가 */
    submit_date    varchar(8)   null,       /* 19.신고서제출일 YYYYMMDD 형식 */
    up_date        datetime  	null,       /* 20.Upload Date */
    up_id          varchar(15)  null,       /* 21.Upload User id */
    PRIMARY KEY (make_date, num)
)

-- 증권회사 추천종목 현황 ag117
create table ag117_tbl
(
	num				int			not null,	/* 1.일련번호 */
	make_date		varchar(8)	not null,	/* 2.작성대상일자 */
	comp_code		varchar(7)	not null,	/* 3.증권회사코드(감독원부여) */
	issues_no		varchar(12)	not null,	/* 4.종목코드(거래소부여) */
	release_date	varchar(8)	not null,	/* 5.외부공개일자 YYYYMMDD 형식 */
	comp_name		varchar(30)	null,	    /* 6.증권회사명 */
	issu_name		varchar(40)	null,	    /* 7.종목명(거래소부여) */
	release_time	varchar(4)	null,	    /* 8.외부공개시간 HHMM 형식 */
	rcmnd_cf_date	varchar(8)	null,	    /* 9.추천확정일자 YYYYMMDD 형식 */
	rcmnd_cf_time	varchar(4)	null,	    /* 10.추천확저시간 HHMM 형식 */
	rcmnd_type		varchar(1)	null,	    /* 11.추천구분(1-매수,2-매도,3-유지,6-기타, */
										    /*    4-관심종목 편입,5-관심종목 제외, */
	rcmnd_history	varchar(1)	null,	    /* 12.추천연혁(1-신규,2-상향조정,3-하향조정) */
	release_way		varchar(1)	null,	    /* 13.공개방법(1-사내Daily 지,2-본점단위부서에서 */
										    /*    자체전산망 등재,3-지점단위에서 자체전산망 */
										    /*    등재,4-신문 등 일간지 기고,5-인터넷증권 */
										    /*    사이트 등재,6-특정인에 대한 리서치자료 등 */
										    /*    제공,7-기타) */
	dptmt_name		varchar(30)	null,	    /* 14.추천자 또는 리서치 담당자 부서명 */
	registry_no		varchar(13)	null,	    /* 15.추천자 또는 리서치담당자의 주민번호를 기재하되 부서인 경우는 Space제외 */
	up_date			datetime	null,	    /* 16.Upload Date */
	up_id			varchar(15)	null,	    /* 17.Upload User id */
	PRIMARY KEY (make_date, num)
)

-- 유가증권 조건부거래 내역 ag124
create table ag124_tbl
(
    num            int       	not null,	/* 1.일련번호 */
    make_date      varchar(8)   not null,  	/* 2.작성기준일자 YYYYMMDD 형식 */
    comp_code      varchar(7)   null,  /* 3.증권회사코드(감독원부여) */
    issues_no      varchar(12)  null,  /* 4.종목코드(거래소부여) */
    trade_type     varchar(2)   null,  /* 5.거래구분(11-대차거래차입,12-환매도조건부채권매수 */
                                       /*    13-결제부족분해소 위해 타인으로부터 차입 */
                                       /*    14-결제부족분해소 위해 예탁원 고객계좌분 */
                                       /*    으로부터 차입,15-대차거래대여분 회수,16-담보 */
                                       /*    제공분회수,24-신용거래대주,17-환매수조건부채권 */
                                       /*    매도분 재매수,18-신용거래대주분회수,19-기타 */
                                       /*    조건부의 상품유가증권 증가 사유,21-대차거래 */
                                       /*    대여,22-담보제공,23-환매수조건부채권 매도 */
                                       /*    (RP편입),25-대차거래 차입분 상환,26-환매도 */
                                       /*    조건부채권매수분 재매도,27-결제부족분해소를 */
                                       /*    위한 타인으로부터의 상환,28-결제부족분해소위한 */
                                       /*    예탁원으로부터 차입분 상환,29-기타 조건부의 */
                                       /*    상품유가증권 감소 사유 */
    trade_date     varchar(8)   null,  /* 6.거래일자 YYYYMMDD 형식 */
    cntr_date      varchar(8)   null,  /* 7.약정기일 */
    comp_name      varchar(30)  null,  /* 8.증권회사명 */
    issu_name      varchar(40)  null,  /* 9.종목명(거래소부여) */
    publish_code   varchar(5)   null,  /* 10.발행사 코드 */
    publish_rel    varchar(1)   null,  /* 11.증권사,발행사간 관계(1-특수관계인,2-기타) */
    security_kind  varchar(2)   null,  /* 12.유가증권 종류(11-주식,12-국채,13-지방채, */
                                       /*    14-특수채,15-통화채,16-금융채,17-회사채, */
                                       /*    18-ARS 등,19-CB,20-BW,21-CP,22-CD,23-기타 */
    quantity       varchar(19)  null,  /* 16.수량(주식은 "주", 기타는 액면가액을 기준  */
                                       /*    으로 10,000원 단위로 기재 */
    price          varchar(19)  null,  /* 17.단가(장부가액/수량) */
    book_value     varchar(19)  null,  /* 18.장부가액(변동사유발생시 대차대조표상 계상 */
                                       /*    금액으로 감가상각충담금 차감전 금액임 */
    cntr_rate      varchar(8)   null,  /* 19.약정이율 */
    cpart_acnt     varchar(30)  null,  /* 20.매매상대방계좌번호(증권사는 증권회사코드) */
    cpart_type     varchar(1)   null,  /* 21.매매상대방구분(1-증권,2-투신,3-기타기관투자자 */
                                       /*    4-일반법인,5-외국인,6-개인,7-저축계좌 등,8-기타 */
    cpart_rel      varchar(1)   null,  /* 22.증권사,매매상대방간 관계(1-특수관계인,2-기타) */
    afchg_bal      varchar(19)  null,  /* 23.변동후 잔고 */
    up_date        datetime  	null,  /* 24.Upload Date */
    up_id          varchar(15) 	null,  /* 25.Upload User id */
    PRIMARY KEY (make_date, num)
)

-- 상품채권 등 거래내역 ag131
create table ag131_tbl
(
    num            int       	not null,    /* 1.일련번호 */
    make_date      varchar(8)   not null,    /* 2.작성기준일자 YYYYMMDD 형식 */
    comp_code      varchar(7)   null,  /* 3.증권회사코드(감독원부여) */
    issues_no      varchar(12)  null,  /* 4.종목코드(거래소부여) */
    buysell        varchar(1)   null,  /* 5.거래구분 (1-매수(CP인수 포함) */
    trade_date     varchar(8)   null,  /* 6.거래일자 YYYYMMDD 형식 */
    comp_name      varchar(30)  null,  /* 7.증권회사명 */
    issu_name      varchar(40)  null,  /* 8.종목명(거래소부여) */
    registry_no    varchar(8)   null,  /* 9.계좌정보(개인-주민번호증 뒤 7자리,법인-사업자등록번호 앞 8자리, */
                                       /*    외국인-투자자등록번호 맨뒤1자리 제외번호 */
    publish_code   varchar(5)   null,  /* 10.발행사 코드 */
    publish_rel    varchar(1)   null,  /* 11.증권사,발행사간 관계(1-특수관계인,2-기타) */
    cpart_type     varchar(1)   null,  /* 12.상대방구분(1-증권,2-투신,3-기타기관투자자 */
                                       /*    4-일반법인,5-외국인,6-개인,7-저축계좌 등,8-기타 */
    cpart_acnt     varchar(30)  null,  /* 13.상대방계좌번호 */
    bond_kind      varchar(2)   null,  /* 14.채권종류(11-국채,12-지방채,13-특수채,14-통화 */
                                       /*    안정증권,15-금융채,16-회사채,17-유동화채권 */
                                       /*    (ABS등),18-CB,19-BW,20-CP,21-CD,22-분리된 */
                                       /*    신주인수권증서,23-기타,2-기타상품채권증가 사유 */
                                       /*    3-매도,4-상환 */
    quantity       varchar(19)  null,  /* 15.수량(액면가액을 기준으로 10,000원 단위 기재 */
    price          varchar(19)  null,  /* 16.단가(10,000원당 단가로 장부가액/수량 산정) */
    book_value     varchar(19)  null,  /* 17.장부가액(거래구분사유발생시 대차대조표상 계상 */
                                       /*    금액으로 감가상각충담금 차감전 금액임 */
    earning_rate   varchar(8)   null,  /* 18.수익률 */
    trade_pnl      varchar(19)  null,  /* 19.매매손익(발생시 기재) */
    trade_kind     varchar(1)   null,  /* 20.매매유형(1-자기매매,2-브로커) */
    up_date        datetime  	null,  /* 21.Upload Date */
    up_id          varchar(15) 	null,  /* 22.Upload User id */
    PRIMARY KEY (make_date, num)
)

-- 상품채권 등 결제내역 ag133
create table ag133_tbl
(
    num            int       	not null,	/* 1.일련번호 */
    make_date      varchar(8)   not null,	/* 2.작성기준일자 YYYYMMDD 형식 */
    comp_code      varchar(7)   null,       /* 3.증권회사코드(감독원부여) */
    issues_no      varchar(12)  null,       /* 4.종목코드(거래소부여) */
    buysell        varchar(1)   null,       /* 5.거래구분(1-매수,3-매도) */
    setl_date      varchar(8)   null,       /* 6.결제일자 YYYYMMDD 형식 */
    comp_name      varchar(30)  null,       /* 7.증권회사명 */
    issu_name      varchar(40)  null,       /* 8.종목명(거래소부여) */
    registry_no    varchar(8)   null,       /* 9.계좌정보(개인-주민번호증 뒤 7자리, */
                                            /*    법인-사업자등록번호 앞 8자리, */
                                            /*    외국인-투자자등록번호 맨뒤1자리 제외번호 */
    cpart_type     varchar(1)   null,       /* 10.상대방구분(1-증권,2-투신,3-기타기관투자자 */
                                            /*    4-일반법인,5-외국인,6-개인,7-저축계좌 등,8-기타 */
    cpart_acnt     varchar(30)  null,       /* 11.상대방계좌번호 */
    quantity       varchar(19)  null,       /* 12.수량(액면가액을 기준으로 10,000원 단위 기재 */
    price          varchar(19)  null,       /* 13.단가(10,000원당 단가로 장부가액/수량 산정) */
    setl_amt       varchar(19)  null,       /* 14.결제가액(결제한 금액) */
    setl_type      varchar(1)   null,       /* 15.결제구분(1-동시결제,2-분리결제,3-개별처리 */
                                            /*    4-SAFE 단말기를 이용하지 않는 경우) */
    up_date        datetime  	null,       /* 16.Upload Date */
    up_id          varchar(15) 	null,       /* 17.Upload User id */
    PRIMARY KEY (make_date, num)
)

-- 상품 선물옵션 매매 내역 ag141
create table ag141_tbl
(
    num            int       	not null,   /* 1.일련번호 */
    make_date      varchar(8)   not null,	/* 2.작성기준일자 YYYYMMDD 형식 */
    comp_code      varchar(7)   null,       /* 3.증권회사코드(감독원부여) */
    issues_no      varchar(12)  null,       /* 4.종목코드(거래소부여) */
    buysell        varchar(1)   null,       /* 5.거래구분(1-매수,2-매도,3-환매수,4-전매도) */
    trade_date     varchar(8)   null,       /* 6.거래일자 YYYYMMDD 형식 */
    comp_name      varchar(30)  null,       /* 7.증권회사명 */
    issu_name      varchar(40)  null,       /* 8.종목명(선물 "KOSPI200선물 0109"형식) */
                                            /*    옵션 "KOSPI200 풋옵션 0109"또는 */
                                            /*    "KOSPI200 콜옵션 0109"로 기재 */
    ordr_time      varchar(6)   null,       /* 9.주문시각 HHMMSS 형식 */
    ordr_qty       varchar(19)  null,       /* 10.수량 */
    ordr_price     varchar(22)  null,       /* 11.단가 */
    amd_rtn        varchar(19)  null,       /* 12.정정회수 */
    amd_qty        varchar(19)  null,       /* 13.정정수량 */
    last_ccl_time  varchar(6)   null,       /* 14.최종취소시각 HHMMSS 형식 */
    ccl_rtn        varchar(19)  null,       /* 15.취소회수 */
    ccl_qty        varchar(19)  null,       /* 16.취소수량 */
    last_cntr_time varchar(6)   null,       /* 17.최종체결시각 HHMMSS 형식 */
    cntr_qty       varchar(19)  null,       /* 18.체결수량 */
    cntr_price     varchar(22)  null,       /* 19.체결단가 */
    unsetl_balnc   varchar(19)  null,       /* 20.미결제약정잔고 */
    up_date        datetime  	null,       /* 21.Upload Date */
    up_id          varchar(15) 	null,       /* 22.Upload User id */
    PRIMARY KEY (make_date, num)
)

-- 위탁자 선물옵션 매매 내역 ag142
create table ag142_tbl
(
    num            int       	not null,   /* 1.일련번호 */
    make_date      varchar(8)   not null,	/* 2.작성기준일자 YYYYMMDD 형식 */
    comp_code      varchar(7)   null,       /* 3.증권회사코드(감독원부여) */
    issues_no      varchar(12)  null,       /* 4.종목코드(거래소부여) */
    buysell        varchar(1)   null,       /* 5.거래구분(1-매수,2-매도,3-환매수,4-전매도) */
    trade_date     varchar(8)   null,       /* 6.거래일자 YYYYMMDD 형식 */
    comp_name      varchar(30)  null,       /* 7.증권회사명 */
    issu_name      varchar(40)  null,       /* 8.종목명(선물 "KOSPI200선물 0109"형식) */
                                            /*    옵션 "KOSPI200 풋옵션 0109"또는 */
                                            /*    "KOSPI200 콜옵션 0109"로 기재 */
    account        varchar(15)  null,       /* 9.계좌번호 */                                          
    registry_no    varchar(8)   null,       /* 10.계좌정보(개인-주민번호증 뒤 7자리, - 신설 */
                                            /*    법인-사업자등록번호 앞 8자리, */
                                            /*    외국인-투자자등록번호 맨뒤1자리 제외번호 */
    ordr_time      varchar(6)   null,       /* 11.주문시각 HHMMSS 형식 */
    ordr_qty       varchar(19)  null,       /* 12.주문수량 */
    ordr_price     varchar(22)  null,       /* 13.주문단가 */
    amd_rtn        varchar(19)  null,       /* 14.정정회수 */
    amd_qty        varchar(19)  null,       /* 15.정정수량 */
    last_ccl_time  varchar(6)   null,       /* 16.최종취소시각 HHMMSS 형식 */
    ccl_rtn        varchar(19)  null,       /* 17.취소회수 */
    ccl_qty        varchar(19)  null,       /* 18.취소수량 */
    last_cntr_time varchar(6)   null,       /* 19.최종체결시각 HHMMSS 형식 */
    cntr_qty       varchar(19)  null,       /* 20.체결수량 */
    cntr_price     varchar(22)  null,       /* 21.체결단가 */
    unsetl_balnc   varchar(19)  null,       /* 22.미결제약정잔고 */
    up_date        datetime  	null,       /* 23.Upload Date */
    up_id          varchar(15) 	null,       /* 24.Upload User id */
    PRIMARY KEY (make_date, num)
)

-- 주식 익일정정 및 선물옵션 정정 내역 ag143
create table ag143_tbl
(
    num            int       	not null,	/* 1.일련번호 */
    make_date      varchar(8)   not null,   /* 2.작성기준일자 YYYYMMDD 형식 */
    comp_code      varchar(7)   null,    	/* 3.증권회사코드(감독원부여) */
    issues_no      varchar(12)  not null,   /* 4.종목코드/체결(거래소부여) */
    buysell        varchar(1)   not null,   /* 5.거래구분/체결(1-매수,2-매도) */
    trade_date     varchar(8)   not null,   /* 6.거래일자 YYYYMMDD 형식 */
    comp_name      varchar(30)  null,       /* 7.증권회사명 */
    issu_name      varchar(40)  null,       /* 8.종목명/체결(거래소부여) */
                                            /*    선물 "KOSPI200선물 0109"형식) */
                                            /*    옵션 "KOSPI200 풋옵션 0109"또는 */
                                            /*    "KOSPI200 콜옵션 0109"로 기재 */
    stock_kind     varchar(1)   null,       /* 9.유가증권종류(1-주식,2-선물,3-옵션) */
    cntr_acnt_sect varchar(8)	null,       /* 10.위탁자구분기호/체결(개인-주민번호 뒤 7자리 */
                                            /*    사업자등록번호 뒤 2자리 제외 나머지 번호 */
                                            /*    외국인투자자등록번호 뒤 1자리 제외 번호 */
    cntr_acnt      varchar(15)  null,       /* 11.계좌번호/체결 */
    registry_no    varchar(8)   null,       /* 12.계좌정보(개인-주민번호증 뒤 7자리, - 신설 */
                                            /*    법인-사업자등록번호 앞 8자리, */
                                            /*    외국인-투자자등록번호 맨뒤1자리 제외번호 */
    cntr_qty       varchar(19)  null,       /* 13.수량/체결 */
    cntr_price     varchar(22)  null,       /* 14.단가/체결(동일주문이 체결단가가 다른 경우 */
                                            /*    단가별로 다른 레코드로 기재 */
    cntr_invst_cd  varchar(1)   null,       /* 15.투자자분류/체결(1-가목,5-마목) */
    cntr_acnt_rel  varchar(1)   null,       /* 16.증권사,계좌주간 관계/체결(1-특수관계인,2-기타) */
    amnd_acnt_sect varchar(12)  null,       /* 17.위탁자구분기호/정정(개인-주민번호 뒤 7자리 */
                                            /*    사업자등록번호 뒤 2자리 제외 나머지 번호 */
                                            /*    외국인투자자등록번호 뒤 1자리 제외 번호 */
    amnd_acnt      varchar(15)  null,       /* 18.계좌번호/정정(정정한 계좌번호) */
    amnd_type      varchar(1)   null,       /* 19.정정구분(1-상품정정,2-계좌정정) */
    amnd_qty       varchar(19)  null,       /* 20.수량/정정(실제상품/정정계좌로 정정한 수량) */
    amnd_price     varchar(22)  null,       /* 21.가격/정정(실제상품/정정계좌로 정정한 가격) */
    amnd_invst_cd  varchar(1)   null,       /* 22.투자자분류/정정(1-가목,5-마목) */
    amnd_acnt_rel  varchar(1)   null,       /* 23.증권사,계좌주간 관계/체결(1-특수관계인,2-기타) */
    orderer        varchar(20)  null,       /* 24.주문자(주문입력자 기재,사이버거래 경우 space) */
    cybordr_yn     varchar(1)   null,       /* 25.사이버주문 여부(1-사이버주문,2-기타) */
    up_date        datetime  	null,       /* 26.Upload Date */
    up_id          varchar(15) 	null,      /* 27.Upload User id */
    PRIMARY KEY (make_date, num)
)

-- 상품계정 수익증권 보유내역 ag161
create table ag161_tbl
(
	num				int			not null,	/* 1.일련번호 */
	make_date		varchar(8)	not null,	/* 2.작성대상일자 */
	comp_code		varchar(7)	not null,	/* 3.증권회사코드(감독원부여) */
	trade_date		varchar(8)	null,		/* 4.거래일자 */
	comp_name		varchar(30)	null,		/* 5.증권회사명 */
	fund_code		varchar(10)	null,		/* 6.펀드코드(회사 자체관리 코드) */
	fund_name		varchar(30)	null,		/* 7.펀드명(회사 자체관리 코드) */
	fund_kind		varchar(2)	null,		/* 8.펀드유형(01-주식형,02-주식혼합형,03-채권혼합형,04-채권형,05-MMF */
											/*  ,(단기금융간접투자),06-파생상품투자,07-부동산투자 */
											/*  ,08-실물간접투자,09-특별자산(선박펀드 등),10-재간접투자,), 11-기타 */
	holding_cnt		varchar(19)	null,		/* 9.보유좌수 */
	holding_amt		varchar(19)	null,		/* 10.보유금액=보유좌수*기준가격 */
	stand_price		varchar(19)	null,		/* 11.기준가격 */
	oper_code		varchar(7)	null,		/* 12.운용사코드(감독원부여) */
	oper_name		varchar(30)	null,		/* 13.운용사명 */
	fund_type		varchar(1)	null,		/* 14.시가평가펀드,장부가평가펀드(1-시가평가펀드,2-장부가평가펀드) */
	acnting_type	varchar(1)	null,		/* 15.계정과목 분류(1-미매각수익증권,2-기타수익증권) */
	collect_type	varchar(1)	null,		/* 16.공모/사모분류(1-공모펀드,2-사모펀드) */
	bbkcomm_excp	varchar(19)	null,		/* 17.환매수수료 면제일수 */
	up_date			datetime	null,		/* 18.Upload Date */
	up_id			varchar(15)	null,		/* 19.Upload User id */
	PRIMARY KEY (make_date, num)
)


-- 사용자 입력 - 채권 인수 내역
create table ag132_tbl
(
    num            int       	not null,    /* 1.일련번호 */
    make_date      varchar(8)   not null,    /* 2.작성기준일자 YYYYMMDD 형식 */
    comp_code      varchar(7)   null,  /* 3.증권회사코드(감독원부여) */
    issues_no      varchar(12)  null,  /* 4.종목코드(거래소부여) */
    undtak_date    varchar(8)   null,  /* 5.인수일자 YYYYMMDD 형식 */

    comp_name      varchar(30)  null,  /* 6.증권회사명 */
    issu_name      varchar(40)  null,  /* 7.종목명(거래소부여) */
    publish_code   varchar(5)   null,  /* 8.발행사 코드 */
    publish_rel    varchar(1)   null,  /* 9.증권사,발행사간 관계(1-특수관계인,2-기타) */
    collect_type   varchar(1)   null,  /* 10.모집구분(1-공모,2-사모) */
    bond_kind      varchar(2)   null,  /* 11.채권종류(11-국채,12-지방채,13-특수채, */
                                       /*    14-통화안정증권,15-금융채,16-회사채, */
                                       /*    17-유동화채권(ABS등),18-CB,19-BW,20-기타 */
    undtak_way     varchar(1)   null,  /* 12.인수방법(1-주간사,2-간사단,3-인수단, */
                                       /*    4-국채입찰 청약 대행,5-기타 */
    issue_totamt   varchar(19)  null,  /* 13.발행총액(특정회차 총발행액(액면가액)) */
    undtak_totamt  varchar(19)  null,  /* 14.인수총액(증권사 인수/입찰 청약대행한 */
                                       /*    액면총액) */
    undtak_rate    varchar(8)   null,  /* 15.인수(발행)수익률(국채입찰 청약대행 경우 */
                                       /*    낙찰수익률 기재 */
    undtak_price   varchar(19)  null,  /* 16.인수(발행) 단가 */
    undtak_comm    varchar(19)  null,  /* 17.인수수수료(입찰청약대행 경우 대행수수료) */
    publish_chrg   varchar(19)  null,  /* 18.발행제비용 */
    apply_inst     varchar(30)  null,  /* 19.청약기관명(국채입찰,청약대행 경우 배정한 */
                                       /*    청약기관명을 기재,주간사로 총액인수 경우 */
                                       /*    사전 청약단 or 인수단이 구성시 기재 */
    allot_qty      varchar(19)  null,  /* 20.배정수량 */
    allot_price    varchar(19)  null,  /* 21.배정단가 */
    up_date        datetime  	null,  /* 22.Upload Date */
    up_id          varchar(15) 	null,  /* 23.Upload User id */
    PRIMARY KEY (make_date, num)
)

-- 사용자 입력 - 주식	인수내역
create table ag162_tbl
(
	num				int			not	null,	/* 1.일련번호 */
    make_date       varchar(8)  not null,   /* 2.작성기준일자 YYYYMMDD 형식 */
	comp_code		varchar(7)	not	null,	/* 3.증권회사코드(감독원부여) */
    account         varchar(20) not null,   /* 4.계좌번호 */
	comp_name		varchar(30)	null,		/* 5.증권회사명	*/
    kindof          varchar(1)  null,   	/* 6.계좌구분(1.개인,2.법인,3.외국인) */
    buysell         varchar(1)  null,   	/* 7.거래구분 (1-장내매수,2-장내매도,3-장외매수,4-장외매도,5-만기상환,6-기타) */
    issu_name       varchar(30) null,   	/* 8.거래종목명 */
	issues_no		varchar(12)	null,		/* 9.거래종목코드 */
    issu_type       varchar(2)  null,   	/* 10.종목구분(11-주식,12.집합투자증권,13.국공채,14.특수채,15.회사채 */
											/*		16.CB,17.BW,18.CP,19.ABCP,20.RP,21.발행어음,22.콜론 */
											/*		23.정기예금,24.대출채권,15.기타) */
    deal_date       varchar(8)  null,   	/* 11.거래일자 YYYYMMDD 형식 */
    due_date        varchar(8)  null,   	/* 12.만기일자 YYYYMMDD 형식 */

    par_value  		varchar(19) null,   	/* 13.액면금액 */
    opp_amt  		varchar(19) null,   	/* 14.매매금액 */
    opp_price       varchar(19) null,       /* 15.매매단가 */
	trd_rate        varchar(8)  null,       /* 16.거래수익률 */
	chk_rate        varchar(8)  null,       /* 17.민평수익률 */
	counterparty    varchar(30) null,       /* 18.거래상대방 */
	cntpy_gubun     varchar(1)  null,       /* 19.거래상대방구분(1.일반법인,2.일반개인,3.대내부서,4.특수관계인) */
	up_date			datetime	null,		/* 20.Upload Date */
	up_id			varchar(15)	null,		/* 21.Upload User id */
	PRIMARY KEY (make_date, num)
)

-- 사용자 입력 - 자금거래 내역
create table ag172_tbl
(
    num            int       	not null,    /* 1.일련번호 */
    make_date      varchar(8)   not null,    /* 2.작성기준일자 YYYYMMDD 형식 */
    comp_code      varchar(7)   null,   /* 3.증권회사코드(감독원부여) */
    deal_type      varchar(1)   null,   /* 4.거래구분(1-차입,2-인출,3-예치(대여포함),4-상환) */
    deal_date      varchar(8)   null,   /* 5.거래일자 YYYYMMDD 형식(거래구분코드에 해당하는 일자 기재) */

    comp_name      varchar(30)  null,  	/* 6.증권회사명 */
    due_date       varchar(8)   null,  	/* 7.만기(예정)일자 YYYYMMDD 형식 */
    dptmt_name     varchar(20)  null,  	/* 8.담당부서명(업무처리담당부서) */
    acnting_code   varchar(10)  null,  	/* 9.계정과목코드 */
    acnting_name   varchar(30)  null,  	/* 10.계정과목명 */
    own_prdct_name varchar(40)  null,  	/* 11.가입상품명(실제 차입예치(대여포함) 상품명FullName기재,상품명X->자금용도 */
    amount         varchar(19)  null,  	/* 12.금액 */
    interst_rate   varchar(8)   null,  	/* 13.이율(이율이 미확정시 목표수익율 기재) */
    rprst_rate     varchar(8)   null,  	/* 14.대표이율(거래성격반영 가능 대표금리) 예:정기예금금리,콜금리 */
    cpart_type     varchar(1)   null,  	/* 15.상대방구분(1-개인,2-임직원,3-금융기관) */
    cpart_code     varchar(13)  null,  	/* 16.상대방코드(1,2-주민등록번호,3-금융기관코드) */
    cpart_name     varchar(30)  null,  	/* 17.상대방명(이름 또는 금융기관명) */
    cpart_braname  varchar(30)  null,  	/* 18.상대방지점명(상대방구분이 금융기관인 경우 거래 지점명 기재) */
    cpart_rel      varchar(1)   null,  	/* 19.증권사,거래금융기관간 관계(상대방구분이 개인/임직원인 경우 미기재)(1-특수관계인,2-기타) */
    oper_code      varchar(7)   null,  	/* 20.운용사코드(가입상품이 투신(자산)운용사 */
                                        /*    운용하는 상품 경우 그 투신(자산)운용사 코드 기재(금융기관코드 참조) */
    up_date        datetime  	null,  	/* 18.Upload Date */
    up_id          varchar(15) 	null,	/* 19.Upload User id */
    PRIMARY KEY (make_date, num)
)
