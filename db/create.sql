CREATE TABLE IF NOT EXISTS items(
	id serial primary key,
	description text,
	create_date timestamp,
	done boolean NOT NULL,
	user_id int references users(id)
);
CREATE TABLE IF NOT EXISTS users(
	id serial primary key,
	login text,
	password text
);