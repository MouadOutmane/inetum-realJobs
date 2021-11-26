INSERT INTO vacancies (city, company_name, contract_type, country, function_description, function_title, industry, nr, offer, postal_code, posting_date, required_experience_skills_education, required_years_of_experience, street_name)
    VALUES ('Brussels', 'Inetum', 'Full-Time', 'Belgium', 'java programming', 'junior java consultant', 'IT', '64', '20000/month', '1000', '10/10/2021', 'bachelor it', 1, 'avenue de la liberte');
INSERT INTO vacancies (city, company_name, contract_type, country, function_description, function_title, industry, nr, offer, postal_code, posting_date, required_experience_skills_education, required_years_of_experience, street_name)
    VALUES ('Antwerpen', 'Realdolmen', 'Full-Time', 'Belgium', 'java programming', 'senior java consultant', 'IT', '45', '50000/month', '1020', '15/10/2021', 'bachelor it',3, 'avenue de la force');
INSERT INTO vacancies (city, company_name, contract_type, country, function_description, function_title, industry, nr, offer, postal_code, posting_date, required_experience_skills_education, required_years_of_experience, street_name)
    VALUES ('Paris', 'McDonald''s', 'Part-Time', 'France', 'flipping burgers', 'burger flipper', 'Fast Food', '16', '900000/month', '1030', '20/10/2021', '', 2, 'avenue de l''espoir');
INSERT INTO ROLES(name)
    VALUES('ROLE_JOB_SEEKER');
INSERT INTO USERS(username, email, password, gender, first_name, last_name, birth_date, street_name, house_number,
                  box, city, postal_code, country, mobile_phone, profile_picture)
    VALUES ('user', 'user@user.com', '$2a$12$A2nukUwr5BUrBuvyvvLwEuWOLAzbcocseqK5V65hYe.j9yyhE4Fg6', 'male',
        'user', 'user', '2021-11-26', 'street', '10', '1', 'brussels', 1000, 'belgium', '0123456789',
        'picture');
INSERT INTO USER_ROLES(user_id, role_id)
    VALUES(1,1);