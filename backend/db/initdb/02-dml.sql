INSERT INTO account (account_id, name, password, authority, status) VALUES ('member', 'member', '$2a$10$rV5CtOuNldjWQyPhMrXQLOcIWDp58Q9F/ZU2Hn.sdpzxNrLjVqwG2', 'MEMBER', 'VALID');
INSERT INTO account (account_id, name, password, authority, status) VALUES ('business', 'business', '$2a$10$kJz3YtaX3s6EUzqcUdLn2.l4BPhFjaKghHl8EiahPRahxkCaKdRa2', 'BUSINESS', 'VALID');
INSERT INTO account (account_id, name, password, authority, status) VALUES ('staff', 'staff', '$2a$10$hQWHwiDItTNxjZ1f7r3DHuXuQHRyQooynkHPAtss.YRWi/cQalPAS', 'STAFF', 'VALID');

INSERT INTO balance (account_id, amount) VALUES ('member', 1000);

INSERT INTO company (id, name, description) VALUES ('COM-550e8400-e29b-41d4-a716-446655440000', 'SONY', '備考');

INSERT INTO belong (account_id, company_id) VALUES ('business', 'COM-550e8400-e29b-41d4-a716-446655440000');

INSERT INTO artist (id, name, outline) VALUES (1, 'YOASOBI', '日本の音楽ユニット。メンバーは、コンポーザーのAyaseとボーカルのikura。2019年に、ソニーミュージックが運営する小説&イラスト投稿サイト「monogatary.com」に投稿された小説を楽曲化するプロジェクトから誕生した。以降、同サイトに限らず様々な小説、タイアップで新たに書き下ろされた小説をもとに楽曲制作を行っている。');
SELECT SETVAL ('artist_id_seq', '1');

INSERT INTO music (id, title, artist_id, company_id, price, release_day, status, purchase_count) VALUES (1, '夜に駆ける', 1, 'COM-550e8400-e29b-41d4-a716-446655440000', 250, '2019-12-15', 'AVAILABLE', 0);
INSERT INTO music (id, title, artist_id, company_id, price, release_day, status, purchase_count) VALUES (2, '勇者', 1, 'COM-550e8400-e29b-41d4-a716-446655440000', 250, '2023-09-29', 'AVAILABLE', 0);
SELECT SETVAL ('music_id_seq', '2');