CREATE TABLE IF NOT EXISTS users
(
    id   bigint      NOT NULL AUTO_INCREMENT,
    name varchar(10) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS articles
(
    id      bigint      NOT NULL AUTO_INCREMENT,
    user_id bigint      NOT NULL,
    title   varchar(50) NOT NULL,
    content clob        NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_article_to_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);