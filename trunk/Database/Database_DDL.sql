
drop table if exists POINTS ;
drop table if exists KEYWORDS ;
drop table if exists IMAGE_DESC ;
drop table if exists MAPS ;
drop table if exists USER_LOGIN ;



create table USER_LOGIN ( 
User_ID int not null auto_increment, 
name varchar(32) not null,
password varchar(32) not null,
primary key(User_ID)) engine=innodb;

create table MAPS (
User_ID int not null auto_increment,
Image_ID int not null unique key,
Time_Stamp timestamp,
Visible bool,
Image_Source varchar(256),
primary key(Image_ID, User_ID),
foreign key(User_ID) references USER_LOGIN(User_ID)) engine=innodb;

create table POINTS (
Image_ID int not null auto_increment,
Point_X float,
Point_Y float,
primary key (Image_ID),
foreign key(Image_ID) references MAPS(Image_ID)
) engine=innodb;

create table KEYWORDS (
Image_ID int not null auto_increment,
Keywords varchar(128),
primary key(Image_ID),
foreign key(Image_ID) references MAPS(Image_ID)) engine=innodb;

create table IMAGE_DESC (
Image_ID int not null auto_increment,
Description varchar(512),
primary key(Image_ID),
foreign key(Image_ID) references MAPS(Image_ID)) engine=innodb;