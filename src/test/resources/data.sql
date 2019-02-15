
INSERT INTO user (user_id, email, active, password) VALUES (111, 'test@test.dev', 1, 'test');

INSERT INTO role (role_id, role_name) VALUES (111, 'ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (111, 111);

