CREATE TABLE items
(
  item_id bigint auto_increment,
  item_name VARCHAR (50),
  item_price double precision,
  PRIMARY KEY (item_id)
);
CREATE TABLE users
(
  user_id bigint auto_increment,
  user_login varchar (50),
  user_password varchar (20),
  PRIMARY KEY (user_id)
);
CREATE TABLE orders
(
  order_id bigint auto_increment,
  user_id bigint,
  total_price double precision,
  PRIMARY KEY (order_id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id)
  REFERENCES users (user_id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE ordered_items
(
  ordered_item_id bigint auto_increment,
  order_id bigint,
  item_id bigint,
  PRIMARY KEY (ordered_item_id),
  CONSTRAINT fk_item_id FOREIGN KEY (item_id)
  REFERENCES items (item_id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_order_id FOREIGN KEY (order_id)
  REFERENCES orders (order_id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO items(item_name, item_price) VALUES ('Mobile phone', 10.5);
INSERT INTO items(item_name, item_price) VALUES ('Book', 5.5);
INSERT INTO items(item_name, item_price) VALUES ('Pen', 6.8);
INSERT INTO items(item_name, item_price) VALUES ('Watches', 15.0);
INSERT INTO items(item_name, item_price) VALUES ('Shoes', 30.0);
INSERT INTO items(item_name, item_price) VALUES ('Sunglasses', 25.4);
INSERT INTO items(item_name, item_price) VALUES ('Hat', 13.8);