INSERT INTO test (id, text) VALUES (1, 'sample');
ALTER SEQUENCE test_seq RESTART WITH 2;
INSERT INTO ROOM_TYPE (id, basePrice, capacity, name, description) VALUES (1, 1.0, 2, 'Test', null);

INSERT INTO ROOM (id, number, name, type_id) VALUES (1, 1, 'test name', 1);
INSERT INTO ROOM (id, number, name, type_id) VALUES (2, 2, 'test name', 1);
INSERT INTO ROOM (id, number, name, type_id) VALUES (3, 3, 'test name', 1);
INSERT INTO ROOM (id, number, name, type_id) VALUES (4, 4, 'test name', 1);
INSERT INTO ROOM (id, number, name, type_id) VALUES (5, 5, 'test name', 1);
INSERT INTO ROOM (id, number, name, type_id) VALUES (6, 6, 'test name', 1);
INSERT INTO ROOM (id, number, name, type_id) VALUES (7, 7, 'test name', 1);
INSERT INTO ROOM (id, number, name, type_id) VALUES (8, 8, 'test name', 1);
INSERT INTO ROOM (id, number, name, type_id) VALUES (9, 9, 'test name', 1);
INSERT INTO ROOM (id, number, name, type_id) VALUES (10, 10, 'test name', 1);
