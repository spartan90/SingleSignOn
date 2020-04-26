create table systemuser (
	USER_SEQ_NO number(20) not null,
	USER_NAME VARCHAR2(200),
	PASSWORD VARCHAR2(100) not null,
	EMAIL_ID VARCHAR2(100),
	MOBILE_NO VARCHAR2(12)
);
alter table systemuser
  add constraint systemuser_pk primary key (USER_SEQ_NO);
