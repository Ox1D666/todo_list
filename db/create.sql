CREATE TABLE IF NOT EXISTS items(
	id serial primary key,
	description text,
	create_date timestamp,
	done boolean NOT NULL
);