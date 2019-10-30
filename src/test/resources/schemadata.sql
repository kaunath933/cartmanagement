truncate table USER_DETAILS;
insert into USER_DETAILS(userId,gender,userName,password,email,firstName,lastName) values (1,'male','test12','12345','test12@gmail.com','testX','last');
insert into USER_DETAILS(userId,gender,userName,password,email,firstName,lastName) values (2, 'female','test23','xyz','test23@gmail.com','testY','last2');

truncate table USER_ACCOUNT;
insert into USER_ACCOUNT(userId,userName,balance) values (1,'testX',2000);