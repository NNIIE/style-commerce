DROP TABLE IF EXISTS member;

CREATE TABLE member
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname    VARCHAR(100) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    is_admin    BOOLEAN      NOT NULL,
    status      BOOLEAN      NOT NULL,
    created_at  DATETIME     NOT NULL,
    updated_at  DATETIME     NOT NULL
);