INSERT INTO role (id, name) VALUE (1, 'GUEST');
INSERT INTO role (id, name) VALUE (2, 'USER');
INSERT INTO role (id, name) VALUE (3, 'ADMIN');
INSERT INTO role (id, name) VALUE (4, 'SUPER_ADMIN');
INSERT INTO user (mail, role_id) VALUES ('isa.debesciaki@gmail.com', 4);
INSERT INTO user (mail, role_id) VALUES ('bartraw23@gmail.com', 3);
INSERT INTO user (mail, role_id) VALUES ('jakub.cichacki@gmail.com', 3);