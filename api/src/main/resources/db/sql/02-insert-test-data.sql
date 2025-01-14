-- Вставка 20 новостей
DO $$
BEGIN
FOR i IN 1..20 LOOP
        INSERT INTO news (id, title, text, created_at)
        VALUES (
            uuid_generate_v4(),
            CONCAT('Test News ', i),
            CONCAT('This is the test content of news ', i),
            NOW()
        );
END LOOP;
END $$;

-- Вставка 10 комментариев для каждой новости
DO $$
DECLARE
news_record RECORD;
BEGIN
FOR news_record IN SELECT id FROM news LOOP
    FOR i IN 1..10 LOOP
                   INSERT INTO comment (id, news_id, username, text, created_at)
                   VALUES (
                       uuid_generate_v4(),
                       news_record.id,
                       CONCAT('Test user', i),
                       CONCAT('Test Comment ', i, ' for test news ', news_record.id),
                       NOW()
                       );
END LOOP;
END LOOP;
END $$;
