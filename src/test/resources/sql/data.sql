INSERT INTO categories (title_category) VALUES ('Roman');
INSERT INTO categories (title_category) VALUES ('Esse');
INSERT INTO categories (title_category) VALUES ('Detective');
INSERT INTO categories (title_category) VALUES ('Fantasy');
INSERT INTO categories (title_category) VALUES ('Poetry');
INSERT INTO categories (title_category) VALUES ('Stories');
INSERT INTO categories (title_category) VALUES ('Biography');
INSERT INTO categories (title_category) VALUES ('History');
INSERT INTO categories (title_category) VALUES ('Fantastic');
INSERT INTO categories (title_category) VALUES ('Adventures');
INSERT INTO categories (title_category) VALUES ('Fairy tales');
INSERT INTO categories (title_category) VALUES ('Publicity');
INSERT INTO categories (title_category) VALUES ('Documentary prose');
INSERT INTO categories (title_category) VALUES ('Humor');
INSERT INTO categories (title_category) VALUES ('Horrors');
INSERT INTO categories (title_category) VALUES ('Education');

INSERT INTO languages (title) VALUES ('English');
INSERT INTO languages (title) VALUES ('German');
INSERT INTO languages (title) VALUES ('French');
INSERT INTO languages (title) VALUES ('Russian');
INSERT INTO languages (title) VALUES ('Italian');
INSERT INTO languages (title) VALUES ('Ukrainian');

INSERT INTO city (postal_code, title_city) VALUES ('13599', 'Berlin');
INSERT INTO city (postal_code, title_city) VALUES ('01109', 'Hellerau');
INSERT INTO city (postal_code, title_city) VALUES ('01067', 'Dresden');
INSERT INTO city (postal_code, title_city) VALUES ('55743', 'Fischbach');
INSERT INTO city (postal_code, title_city) VALUES ('60308', 'Frankfurt am Main');

INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test@gmail.com', 'Joanne', 'Rowling', '$2a$10$umFgAvD5TcsCh0CK5uaSAOXbXek1d.7NrEqmsZzuLoA.hCypaxZlS', 'USER', 'CONFIRMED', 1);
INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test2@gmail.com', 'Boris', 'Johnson', '$2a$10$OkmkhWacyebrWeis2e89bOqWiUh9e7WFHTCdgXNsgt9zgkTiEaoW.', 'USER', 'CONFIRMED', 2);
INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test3@gmail.com', 'Frank', 'Henry', '$2a$10$umFgAvD5TcsCh0CK5uaSAOXbXek1d.7NrEqmsZzuLoA.hCypaxZlS', 'USER', 'CONFIRMED', 1);
INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test4@gmail.com', 'Anna', 'Müller', '$2a$10$umFgAvD5TcsCh0CK5uaSAOXbXek1d.7NrEqmsZzuLoA.hCypaxZlS', 'USER', 'CONFIRMED', 3);
INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test5@gmail.com', 'Frank', 'Bachmann', '$2a$10$umFgAvD5TcsCh0CK5uaSAOXbXek1d.7NrEqmsZzuLoA.hCypaxZlS', 'USER', 'CONFIRMED', 4);
INSERT INTO users (agreement, email, first_name, last_name, password, role, state, city_id) VALUES (true, 'test6@gmail.com', 'Bernar', 'Wolff', '$2a$10$umFgAvD5TcsCh0CK5uaSAOXbXek1d.7NrEqmsZzuLoA.hCypaxZlS', 'USER', 'CONFIRMED', 5);

INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Murphy Raymond', 'bit.ly/3Z3qrIh', '2023-09-06', 'Lewis''s immortal prose have left many a lasting memory. For here is a world where a witch decrees eternal winter; where there are more talking animals than people; and where battles are fought by Centaurs, Giants and Fauns', 556, '2019-04-11', 'AVAILABLE', 'The Chronicles of Narnia', 2, 1, 1);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Sebastian Fitzek', 'bit.ly/3sG8tzn', '2020-04-11', 'Ein lebenskluger und hinreißend komischer Roman im Stil von Sebastian Fitzeks', 95, '2020-04-11', 'AVAILABLE', 'Elternabend', 6, 2, 5);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Акунин', 'bit.ly/3Pvr8XS', '2023-09-06', 'Большое приключение Эраста Петровича Фандорина, рассказанное его бессменным помощником Масахиро Сибатой, началось в первый день двадцатого столетия.', 108, '2015-04-11', 'AVAILABLE', 'Яма', 3, 4, 2);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Murphy Raymond', 'bit.ly/45BSyAI', '2023-09-06', 'This book has clear explanations and practice exercises that have helped millions of people around the world improve their English', 256, '2017-04-11', 'AVAILABLE', 'English Grammar in Use. Book with Answers', 16, 1, 1);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('T. C. Boyle', 'bit.ly/3PsMqVH', '2023-09-06', 'Welcome to America. On the east coast, homes are being swallowed by the ocean; on the west coast, California is engulfed with wildfire. But for one family, the impending environmental disaster is the least of their worries. ', 236, '2023-05-10', 'AVAILABLE', 'Blue Skies', 10, 1, 2);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Richard Flanagan', 'bit.ly/3RbiDSY', '2023-09-06', e'An ember storm of a novel, this is Booker Prize-winning novelist Richard Flanagan at his most moving-and astonishing-best.
Anna\'s aged mother is dying - if her three children would just allow it.ow into visions of horror and delight.', 543, '2022-09-01', 'AVAILABLE', 'The Living Sea of Waking Dreams', 8, 1, 6);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('David Sedaris', 'bit.ly/3RbwFE1', '2023-09-06', 'There''s no right way to keep a diary, but if there''s an entertaining way, David Sedaris seems to have mastered it.', 155, '2022-07-01', 'AVAILABLE', 'A Carnival of Snackery', 4, 1, 5);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('J. D. Robb', 'bit.ly/44IvUFu', '2023-09-06', 'A devilishly disturbing new case for Lieutenant Eve Dallas from the Sunday Times bestseller J.D. Robb. As Eve investigates this shocking case, she soon discovers a disturbing pattern', 321, '2017-08-10', 'AVAILABLE', 'Echoes in Death', 15, 1, 4);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('George R. R. Martin', 'https://bilder.buecher.de/produkte/34/34961/34961218n.jpg', '2023-09-06', 'Daenerys Targaryen, die Königin der Drachen, muss sich entscheiden, welchen ihrer adligen Freier sie heiraten wird. Wer wird der mächtigste Verbündete für die Eroberung von Westeros sein? ', 321, '2023-05-15', 'AVAILABLE', 'Ein Tanz mit Drachen', 4, 2, 3);
INSERT INTO books (author, cover, date_create, description, pages, publisher_date, state, title, category_id, language_id, user_id) VALUES ('Оксана Забужко', 'bit.ly/3EubUvO', '2020-09-06', e'Це історія трьох поколінь українців – від часів Другої світової війни до сучасності. Сюжет складається із кількох часових ліній.
', 856, '2023-02-10', 'AVAILABLE', 'Музей покинутих секретів', 8, null, null);




