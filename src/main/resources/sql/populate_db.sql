-- run this SQL script to populate DB with initial data
-- it is required at this moment, cause there are no endpoints with such logic provided yet

-- CLEANING
DELETE FROM public.photo_link WHERE 1 = 1;
DELETE FROM public.room WHERE 1 = 1;
DELETE FROM public.room_type WHERE 1 = 1;

-- INSERT INTO ROOM_TYPE
INSERT INTO public.room_type (id, base_price, capacity, description, name)
VALUES (1, 200.00, 2, 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium...', 'Blue sky');

INSERT INTO public.room_type (id, base_price, capacity, description, name)
VALUES (2, 250.00, 2, 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium...', 'Purple sky');

INSERT INTO public.room_type (id, base_price, capacity, description, name)
VALUES (3, 300.00, 2, 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium...', 'Red sky');

INSERT INTO public.room_type (id, base_price, capacity, description, name)
VALUES (4, 200.00, 4, 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium...', 'Wide sky');

INSERT INTO public.room_type (id, base_price, capacity, description, name)
VALUES (5, 150.00, 2, 'At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium...', 'Wise sky');


-- INSERT INTO ROOM
INSERT INTO public.room(id, name, number, type_id) VALUES (1, '', 101, 5);
INSERT INTO public.room(id, name, number, type_id) VALUES (2, '', 102, 5);
INSERT INTO public.room(id, name, number, type_id) VALUES (3, '', 103, 5);
INSERT INTO public.room(id, name, number, type_id) VALUES (4, '', 104, 4);
INSERT INTO public.room(id, name, number, type_id) VALUES (5, '', 105, 4);
INSERT INTO public.room(id, name, number, type_id) VALUES (6, '', 201, 1);
INSERT INTO public.room(id, name, number, type_id) VALUES (7, '', 202, 1);
INSERT INTO public.room(id, name, number, type_id) VALUES (8, '', 203, 1);
INSERT INTO public.room(id, name, number, type_id) VALUES (9, '', 204, 2);
INSERT INTO public.room(id, name, number, type_id) VALUES (10, '', 205, 2);
INSERT INTO public.room(id, name, number, type_id) VALUES (11, '', 301, 3);
INSERT INTO public.room(id, name, number, type_id) VALUES (12, '', 302, 3);


-- INSERT INTO PHOTO_LINK
INSERT INTO public.photo_link(id, link, room_id) VALUES(1,  'http://sample.photo/v0.org', 1);
INSERT INTO public.photo_link(id, link, room_id) VALUES(2,  'http://sample.photo/v1.org', 1);
INSERT INTO public.photo_link(id, link, room_id) VALUES(3,  'http://sample.photo/v2.org', 1);
INSERT INTO public.photo_link(id, link, room_id) VALUES(4,  'http://sample.photo/v3.org', 1);
INSERT INTO public.photo_link(id, link, room_id) VALUES(5,  'http://sample.photo/v4.org', 1);
INSERT INTO public.photo_link(id, link, room_id) VALUES(6,  'http://sample.photo/v5.org', 2);
INSERT INTO public.photo_link(id, link, room_id) VALUES(7,  'http://sample.photo/v6.org', 2);
INSERT INTO public.photo_link(id, link, room_id) VALUES(8,  'http://sample.photo/v7.org', 2);
INSERT INTO public.photo_link(id, link, room_id) VALUES(9,  'http://sample.photo/v8.org', 2);
INSERT INTO public.photo_link(id, link, room_id) VALUES(10, 'http://sample.photo/v9.org', 2);
INSERT INTO public.photo_link(id, link, room_id) VALUES(11, 'http://sample.photo/v10.org', 3);
INSERT INTO public.photo_link(id, link, room_id) VALUES(12, 'http://sample.photo/v11.org', 3);
INSERT INTO public.photo_link(id, link, room_id) VALUES(13, 'http://sample.photo/v12.org', 3);
INSERT INTO public.photo_link(id, link, room_id) VALUES(14, 'http://sample.photo/v13.org', 3);
INSERT INTO public.photo_link(id, link, room_id) VALUES(15, 'http://sample.photo/v14.org', 4);
INSERT INTO public.photo_link(id, link, room_id) VALUES(16, 'http://sample.photo/v15.org', 4);
INSERT INTO public.photo_link(id, link, room_id) VALUES(17, 'http://sample.photo/v16.org', 4);
INSERT INTO public.photo_link(id, link, room_id) VALUES(18, 'http://sample.photo/v17.org', 4);
INSERT INTO public.photo_link(id, link, room_id) VALUES(19, 'http://sample.photo/v18.org', 4);
