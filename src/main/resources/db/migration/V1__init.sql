create table users
(
    id bigserial,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email varchar(50) unique,
    primary key (id)
);

create table roles
(
    id serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)

);

create table categories
(
    id bigserial primary key not null,
    title varchar(255) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table product
(
    id bigserial not null
        constraint product_pk
            primary key,
    name varchar(255) not null,
    price numeric(8, 2) not null,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table orders
(
    id bigserial not null primary key,
    price numeric(8, 2) not null,
    user_id bigint references users (id),
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

insert into roles (name)
    values
        ('ROLE_USER'),
        ('ROLE_ADMIN');

insert into users (username, password, email)
    values
        ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
        ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
    values
        (1, 1),
        (1, 2);

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