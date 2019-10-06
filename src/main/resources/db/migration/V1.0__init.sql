create table store(
	uuid UUID primary key,
	url varchar(255) not null,
	version int default 1
)