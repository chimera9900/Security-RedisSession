create table users(
	username varchar(255) not null ,
	password varchar(255) not null,
	enabled int       not null default 1,
	primary key(username)
	
	
);

create table user_roles(
	user_role_id int(11) not null auto_increment,
	username     varchar(255) not null,
	role         varchar(255) not null,
	primary key(user_role_id),
	constraint uni_username_role unique (role, username),
	constraint fk_username foreign key (username) references users (username)
	on delete cascade on update cascade
)