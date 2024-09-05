DROP TABLE IF EXISTS member;

CREATE TABLE member
(
    id          BINARY(16) PRIMARY KEY,
    nickname    VARCHAR(100) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    is_admin    BOOLEAN      NOT NULL,
    status      BOOLEAN      NOT NULL,
    created_at  DATETIME     NOT NULL,
    updated_at  DATETIME     NOT NULL
);

CREATE UNIQUE INDEX idx_member_email_unique ON member(email);