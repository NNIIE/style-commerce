INSERT INTO member (nickname, email, password, is_admin, status, created_at, updated_at) VALUES
('admin', 'admin@naver.com', '$2a$10$siI2NBC3kX4SEDYMmlmi2O3lyP84YGBnRL3pGHVLKJUaN7HxZSu1m', TRUE, TRUE, NOW(), NOW()),
('basic', 'basic@naver.com', '$2a$10$siI2NBC3kX4SEDYMmlmi2O3lyP84YGBnRL3pGHVLKJUaN7HxZSu1m', FALSE, TRUE, NOW(), NOW());