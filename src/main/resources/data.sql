-- USERS

INSERT INTO users (id, email, first_name, last_name, password)
VALUES (111, 'test@test.com', 'sayal', 'adhikari', '$2a$10$zJIH0vqVbEiie5qXKLv2/OSSBhfOFcQeKG6jbShEFRKfom84vCuU2'); --123

INSERT INTO users (id, email, first_name, last_name, password)
VALUES (112, 'john@miu.edu', 'john', 'doe', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

INSERT INTO users (id, email, first_name, last_name, password)
VALUES (113, 'muhyidean@miu.edu', 'muhyidean', 'al-tarawneh', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

INSERT INTO users (id, email, first_name, last_name, password)
VALUES (114, 'levi@miu.edu', 'keith', 'levi', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

INSERT INTO users (id, email, first_name, last_name, password)
VALUES (115, 'okalu@miu.edu', 'obinna', 'kalu', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

INSERT INTO users (id, email, first_name, last_name, password)
VALUES (116, 'tina@miu.edu', 'tina', 'xing', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123


-- PRODUCTS

INSERT INTO Post (id, title, content, author, id_user)
VALUES (111, 'iPhone', 'this is content', 'sayal', 111);

INSERT INTO Post (id, title, content, author, id_user)
VALUES (112, 'iPad', 'this is content', 'sayal', 111);

INSERT INTO Post (id, title, content, author, id_user)
VALUES (113, 'pixel', 'this is content', 'sayal', 112);

INSERT INTO Post (id, title, content, author, id_user)
VALUES (114, 'iPad', 'this is content', 'sayal', 114);

-- REVIEWS

INSERT INTO Comment (id,name,id_post)
VALUES (100,'awesome phone',111);

INSERT INTO Comment (id,name,id_post)
VALUES (200,'amazing phone',111);

INSERT INTO Comment (id,name,id_post)
VALUES (300,'awesome tablet',112);

--ROLES
INSERT INTO role (id, role)
VALUES (1, 'ADMIN');
INSERT INTO role (id, role)
VALUES (2, 'CLIENT');


INSERT INTO users_roles (user_id, roles_id)
VALUES (111, 1);
INSERT INTO users_roles (user_id, roles_id)
VALUES (112, 2);
INSERT INTO users_roles (user_id, roles_id)
VALUES (111, 2);