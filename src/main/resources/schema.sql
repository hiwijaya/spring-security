create table user_auth (
	id serial primary key,
	username varchar not null unique,
	password varchar,
	role varchar not null
);
