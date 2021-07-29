create table categories
(
    id         bigserial primary key not null,
    title      varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table product
(
    id bigserial not null
        constraint product_pk
            primary key,
    name varchar(255) not null,
    price bigint not null,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table orders
(
    id bigserial not null primary key,
    price numeric(8, 2) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table order_items
(
    id bigserial not null primary key,
    price numeric(8, 2) not null,
    price_per_product numeric(8, 2) not null,
    product_id bigint references product (id),
    order_id bigint references product (id),
    quantity int not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create unique index product_name_uindex
	on product (name);

insert into categories (title, id)
    values
           ('Food', 1);

insert into product (name, price, id, category_id)
    values
           ('Milk', 85, 1, 1),
           ('Bread', 25, 2, 1),
           ('Cheese', 450, 3, 1),
           ('Coffee', 100, 4, 1),
           ('Snacks', 340, 5, 1),
           ('Porridge', 136, 6, 1),
           ('Cherry', 340, 7, 1),
           ('Meat', 570, 8, 1),
           ('Avocado', 360, 9, 1),
           ('Garlic', 12, 10, 1),
           ('Onion', 9, 11, 1),
           ('Almond', 89, 12, 1),
           ('Blackberry', 157, 13, 1),
           ('Lemon', 86, 14, 1),
           ('Lime', 150, 15, 1),
           ('Pecan', 273, 16, 1),
           ('Plum', 138, 17, 1),
           ('Walnut', 391, 18, 1),
           ('Barley', 2, 19, 1),
           ('Eggs', 50, 20, 1);