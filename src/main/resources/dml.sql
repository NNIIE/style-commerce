INSERT INTO member (member_id, email, nickname, password, role, status, created_at, updated_at) VALUES
(RANDOM_UUID(), 'admin@naver.com', 'admin', '$2a$10$18N4y7HsEr.uKsi5EX44Ae5w5RG4WCc1DNjmtu.uuG10pHm1RXbWi', 0, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(RANDOM_UUID(), 'basic@naver.com', 'basic', '$2a$10$18N4y7HsEr.uKsi5EX44Ae5w5RG4WCc1DNjmtu.uuG10pHm1RXbWi', 1, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
-- qwer1234!!

INSERT INTO address (member_id, city, district, province, created_at, updated_at) VALUES
((SELECT member_id FROM member WHERE email = 'basic@naver.com'), 'seoul', 'seoul', 'seoul', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
((SELECT member_id FROM member WHERE email = 'basic@naver.com'), 'busan', 'busan', 'busan', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO brand (member_id, name, license_number, phone_number, created_at, updated_at)VALUES
((SELECT member_id FROM member WHERE email = 'admin@naver.com'), 'nike', 1234567890, 9876543210, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
((SELECT member_id FROM member WHERE email = 'admin@naver.com'), 'adidas', 2345678901, 8765432109, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
