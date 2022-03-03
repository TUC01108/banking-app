create table login
(
	loginId SERIAL primary KEY,
	username VARCHAR(20) unique not null,
	password VARCHAR(20) not null

);

create table transactions
(
	sender VARCHAR(20),
	receiver VARCHAR(20),
	amount bigint default 0,
	transactiontime TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP

);

create table customers
(
	userid SERIAL primary KEY,
	firstname VARCHAR(20) not null,
	username VARCHAR(20) not null,
	password VARCHAR(20) not null,
	balance bigint default 0,
	accounttype VARCHAR(2) default 'C',
	status VARCHAR(2) default 'N',
	CONSTRAINT chk_status CHECK (status IN ('Y', 'N')),
	CONSTRAINT chk_accounttype CHECK (accounttype IN ('C', 'E')),
	constraint balance check(balance >= 0)
);

create table apply
(
	userid SERIAL primary KEY,
	firstname VARCHAR(20) not null,
	username VARCHAR(20) not null,
	password VARCHAR(20) not null,
	balance bigint default 0,
	accounttype VARCHAR(2) default 'C',
	accountName VARCHAR(20) not null,
	CONSTRAINT chk_accounttype CHECK (accounttype IN ('C', 'E')),
	constraint balance check(balance >= 0)
); 

create table employees
(
	userid SERIAL primary KEY,
	firstname VARCHAR(20) not null,
	username VARCHAR(20) unique not null,
	password VARCHAR(20) not null,
	accounttype VARCHAR(2) default 'E',
	CONSTRAINT chk_accounttype CHECK (accounttype IN ('C', 'E'))
);

select * from login;

select * from transactions;

select * from employees;

select * from customers;


update customers set username = 'cus2' where userid = 2 ;
update login set username = 'cus2' where loginid = 3 ;

select * from apply;

--DROP WARNING---------
drop table customers;

drop table employees;

drop table login;

drop table transactions;
--DROP WARNING

-- THIS IS FOR WITHDRAWING FROM OWN ACCOUNT
create or replace procedure withdraw(
	withdrawer varchar(20),
	amount dec
)
language plpgsql 
as $$
begin 
	-- subtracting the amount from the withdrawaler's account 
    update customers 
    set balance = balance - amount 
    where username = withdrawer;
 
    commit;
end;$$

-- THIS IS FOR DEPOSITING INTO OWN ACCOUNT
create or replace procedure deposit(
	depositer varchar(20),
	amount dec
)
language plpgsql 
as $$
begin 
	-- subtracting the amount from the withdrawaler's account 
    update customers 
    set balance = balance + amount 
    where username = depositer;
 
    commit;
end;$$

-- THIS IS FOR TRANSFERING
create or replace procedure transfer(
	sender varchar(20),
	receiver varchar(20),
	amount dec
)
language plpgsql 
as $$
begin 
	-- subtracting the amount from the withdrawaler's account 
    update customers 
    set balance = balance - amount 
    where username = sender;
   
   -- adding the amount to the receiver's account
    update customers 
    set balance = balance + amount 
    where username = receiver;
 
    commit;
end;$$

-- Transfer with INOUT
create or replace procedure transferAmountAndGetbalance
(
   sender varchar(20),
   receiver varchar(20),
   amount bigint,
  INOUT senderBalance bigint,
  INOUT receiverBalance bigint
)
language  plpgsql
as $$
begin 
		update customers set balance = balance - amount where username = sender;
		update customers set balance = balance + amount where username = receiver;
		select balance into senderBalance from customers where username = sender;
		select balance into receiverBalance from customers where username = receiver;
commit;
end;$$

drop procedure transferAmountAndGetbalance(
   sender varchar(20),
   receiver varchar(20),
   amount bigint,
  INOUT senderBalance bigint,
  INOUT receiverBalance bigint
) ;


select * from accounts;


