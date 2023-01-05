
DROP SEQUENCE IF EXISTS menu_items_id_seq;
CREATE SEQUENCE menu_items_id_seq INCREMENT BY 11;

DROP TABLE IF EXISTS menu_items;
CREATE TABLE menu_items
(
    id              integer PRIMARY KEY DEFAULT nextval('menu_items_id_seq'),
    name            varchar(255)        DEFAULT NULL,
    price           numeric(10, 2) NOT NULL,
    active          boolean             DEFAULT NULL,
    createddatetime timestamp           DEFAULT NULL
);

INSERT INTO menu_items (id, name, price, active, createddatetime)
VALUES (1, 'pares', 100.00, NULL, NULL),
       (2, 'tapsilog', 100.00, NULL, NULL),
       (3, 'chicksilog', 100.00, NULL, NULL),
       (4, 'porksilog', 100.00, NULL, NULL),
       (5, 'hotsilog', 100.00, NULL, NULL),
       (6, 'hamsilog', 100.00, NULL, NULL),
       (7, 'siomai silog', 100.00, NULL, NULL),
       (8, 'tosilog', 100.00, NULL, NULL),
       (9, 'liemposilog', 100.00, NULL, NULL),
       (10, 'shanghai silog', 100.00, NULL, NULL);

DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items
(
    id      serial PRIMARY KEY,
    menu    integer DEFAULT NULL,
    qty     integer DEFAULT NULL,
    "order" integer DEFAULT NULL
);

DROP SEQUENCE IF EXISTS order_items_id_seq;
CREATE SEQUENCE orders_id_seq INCREMENT BY 2;

DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id       integer PRIMARY KEY DEFAULT nextval('orders_id_seq'),
    uuid     varchar(32)         DEFAULT NULL,
    discount integer             DEFAULT NULL,
    total    numeric(10, 2)      DEFAULT NULL,
    status   varchar(11)         DEFAULT NULL,
    datetime timestamptz         DEFAULT NULL,
    "user"   integer             DEFAULT NULL
);

DROP SEQUENCE IF EXISTS orders_id_seq;
CREATE SEQUENCE users_id_seq INCREMENT BY 3;

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id              integer PRIMARY KEY   DEFAULT nextval('users_id_seq'),
    name            varchar(255)          DEFAULT NULL,
    username        varchar(255) NOT NULL DEFAULT '',
    password        varchar(255) NOT NULL DEFAULT '',
    createddatetime timestamp             DEFAULT current_timestamp,
    UNIQUE (username)
);

INSERT INTO users (id, name, username, password, createddatetime)
VALUES (1, 'Foo Bar', 'foobar', '3cd17d79c6fe4069c7b919b9987f5303', '2022-11-08 23:35:04');
