DROP TABLE IF EXISTS images CASCADE;
DROP TABLE IF EXISTS pets CASCADE;
DROP TABLE IF EXISTS volunteers CASCADE;
DROP TABLE IF EXISTS approval_statuses CASCADE;
ALTER TABLE users DROP COLUMN IF EXISTS is_new_user;

BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


COMMIT TRANSACTION;


BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS pets (
        pet_id serial PRIMARY KEY,
        pet_name VARCHAR (16) NOT NULL,
        age_in_months INT NOT NULL,
        gender VARCHAR (1) NOT NULL,
        species VARCHAR (16) NOT NULL,
        description VARCHAR (256) NOT NULL
);

CREATE TABLE IF NOT EXISTS images (
        image_id serial PRIMARY KEY,
        pet_id INT NOT NULL,
        url VARCHAR (255),
        image_description VARCHAR (255), 
        FOREIGN KEY (pet_id)
                REFERENCES pets (pet_id)    
);

CREATE TABLE IF NOT EXISTS approval_statuses (
        approval_status_id INT PRIMARY KEY,
        approval_status_description VARCHAR (8)
);

CREATE TABLE IF NOT EXISTS volunteers (
        volunteer_id serial PRIMARY KEY,
        first_name VARCHAR (32) NOT NULL,
        last_name VARCHAR (32) NOT NULL,
	username VARCHAR (32) NOT NULL,
        email_address VARCHAR (32) NOT NULL,
        has_adopted_before BOOLEAN NOT NULL,
        has_any_certifications BOOLEAN NOT NULL,
        approval_status_id INT NOT NULL,
        FOREIGN KEY (approval_status_id)
                REFERENCES approval_statuses (approval_status_id)
);

ALTER TABLE volunteers
ADD CONSTRAINT unique_username UNIQUE (username);

ALTER TABLE users
ADD COLUMN is_new_user BOOLEAN;
UPDATE users SET is_new_user = false;
ALTER TABLE users
ALTER COLUMN is_new_user set NOT NULL;
ALTER TABLE users
ALTER COLUMN is_new_user SET DEFAULT true;

COMMIT;


BEGIN TRANSACTION;

INSERT INTO approval_statuses (approval_status_id, approval_status_description)
VALUES (0, 'Pending');

INSERT INTO approval_statuses (approval_status_id, approval_status_description)
VALUES (1, 'Approved');

INSERT INTO approval_statuses (approval_status_id, approval_status_description)
VALUES (2, 'Declined');

COMMIT;


BEGIN TRANSACTION;

INSERT INTO pets (pet_name, age_in_months, gender, species, description)
VALUES ('Cookie', 18, 'F', 'dog', 'Cookie is a shy but friendly pup who loves treats! Spend a little quality time with her and she will warm up to you quickly.');

INSERT INTO pets (pet_name, age_in_months, gender, species, description)
VALUES ('Vincent', 9, 'M', 'cat', 'Say hello to Vincent! This little furball is looking for a new home with a friendly owner. His happy place is a gentle ear scratch and he loves classical music!');

INSERT INTO pets (pet_name, age_in_months, gender, species, description)
VALUES ('Buster', 11, 'M', 'rabbit', 'Buster is an inquisitive young fella who needs a loving home with a new friend! Interestingly, he does not seem to like carrots, but is a big cucumber fan!');

INSERT INTO pets (pet_name, age_in_months, gender, species, description)
VALUES ('Waffles', 10, 'M', 'dog', 'Waffles is an energetic, adorable little scruffy boy with a huge heart who is looking for a friend whom he can love unconditionally!');

COMMIT;


BEGIN TRANSACTION;

INSERT INTO images (pet_id, url, image_description)
VALUES (1, 'https://i.ibb.co/mN7d67c/COOKIE-01.jpg', 'Golden brown short haired dog licking nose.');

INSERT INTO images (pet_id, url, image_description)
VALUES (2, 'https://i.ibb.co/m4HJ0Zp/VINCENT-01.jpg', 'Young gray and black kitten.');

INSERT INTO images (pet_id, url, image_description)
VALUES (3, 'https://i.ibb.co/47hFxQ0/BUSTER-01.jpg', 'Cute white fluffy bunny.');

INSERT INTO images (pet_id, url, image_description)
VALUES (4, 'https://i.ibb.co/x5Gvb7L/WAFFLES-01.jpg', 'Young chocolate lab.');

INSERT INTO volunteers (first_name, last_name, username, email_address, has_adopted_before, has_any_certifications, approval_status_id)
VALUES ('henry','johnson','hencutJohnson','hencutJohnson@gmail.com',false,false,0),
('other','test','othertestguy','othertestguy@gmail.com',true,false,0)
;


COMMIT;

CREATE USER final_capstone_owner
WITH PASSWORD 'finalcapstone';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

CREATE USER final_capstone_appuser
WITH PASSWORD 'finalcapstone';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;