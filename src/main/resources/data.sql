INSERT INTO ROLES(name) VALUES('ROLE_JOB_SEEKER');

INSERT INTO USERS(username, email, password, gender, first_name, last_name, birth_date, street_name, house_number,
                  box, city, postal_code, country, mobile_phone, profile_picture)
                VALUES ('user', 'user@user.com', '$2a$12$A2nukUwr5BUrBuvyvvLwEuWOLAzbcocseqK5V65hYe.j9yyhE4Fg6', 'male',
                        'user', 'user', '2021-11-26', 'street', '10', '1', 'brussels', 1000, 'belgium', '0123456789',
                        'picture');

INSERT INTO USER_ROLES(user_id, role_id) VALUES(1,1);