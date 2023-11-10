create database emp
character set utf8
collate utf8_hungarian_ci;

use emp;

create table employee (
    id int not null primary key auto_increment,
    name varchar(50),
    city varchar(50),
    salary double
);

grant all privileges 
on emp.*
to emp@localhost
identified by 'titok';
