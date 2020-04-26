create table systemuser (
	iUserSeqNo number(20) not null,
    szName VARCHAR2(200),
	szPassword VARCHAR2(100) not null,
	szEmailId VARCHAR2(100),
	szMobileNo VARCHAR2(12)
);
alter table systemuser
  add constraint systemuser_pk primary key (iUserSeqNo);