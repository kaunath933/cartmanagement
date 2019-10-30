create TABLE if not exists USER_DETAILS(
userId int Primary Key,
gender varchar(20),
userName varchar(20),
password varchar(20),
email varchar(20),
firstName varchar(20),
lastName varchar(20),
);

create TABLE if not exists USER_CART(
userId int,
itemId int,
quantity int,
price float,
);


create TABLE if not exists USER_ACCOUNT(
userId int Primary key,
userName varchar(20),
balance varchar(20),
);
