CREATE TABLE artist(
id integer NOT NULL PRIMARY KEY,
"name" character varying(20) NOT NULL);

CREATE TABLE album(
id integer NOT NULL PRIMARY KEY,
"title" character varying(40) NOT NULL,
genre character varying(20) NOT NULL,
artist_id integer REFERENCES artist (id));

CREATE TABLE song (
id integer NOT NULL PRIMARY KEY,
"title" character varying(40) NOT NULL,
duration integer NOT NULL,
album_id integer REFERENCES album(id));