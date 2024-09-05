INSERT INTO member (id, nickname, email, password, is_admin, status, created_at, updated_at) VALUES
(UUID(), 'admin', 'admin@naver.com', '$2a$10$siI2NBC3kX4SEDYMmlmi2O3lyP84YGBnRL3pGHVLKJUaN7HxZSu1m', TRUE, TRUE, NOW(), NOW()),
(UUID(), 'basic', 'basic@naver.com', '$2a$10$siI2NBC3kX4SEDYMmlmi2O3lyP84YGBnRL3pGHVLKJUaN7HxZSu1m', FALSE, TRUE, NOW(), NOW());