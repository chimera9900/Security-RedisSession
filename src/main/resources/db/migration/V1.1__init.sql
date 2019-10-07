create table store(
	uuid varchar(100) primary key,
	url varchar(255) not null,
	version int default 1
)