alter table store
add column description varchar(255) not null,
add column createOn timestamp default CURRENT_TIMESTAMP,
add column updateOn timestamp default CURRENT_TIMESTAMP;