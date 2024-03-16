create table myfile(
    idx number primary key,
    title varchar2(200) not null,
    cate varchar2(100),
    ofile varchar2(100) not null,
    sfile varchar2(30) not null,
    postdate date default sysdate not null
);

desc myfile;

create sequence seq_board_num;

select * from myfile;

alter table myfile modify cate varchar2(100);