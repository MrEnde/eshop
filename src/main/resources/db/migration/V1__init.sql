create table product
(
    id bigserial not null
        constraint product_pk
            primary key,
    name varchar not null,
    price bigint not null
);

create unique index product_name_uindex
	on product (name);

insert into product (name, price, id)
    values
           ('Milk', 85, 1),
           ('Bread', 25, 2),
           ('Cheese', 450, 3),
           ('Coffee', 100, 4),
           ('Snacks', 340, 5),
           ('Porridge', 136, 6),
           ('Meat', 570, 8),
           ('Avocado', 360, 9),
           ('Garlic', 12, 10),
           ('Onion', 9, 11),
           ('Almond', 89, 12),
           ('Blackberry', 157, 13),
           ('Lemon', 86, 14),
           ('Lime', 150, 15),
           ('Pecan', 273, 16),
           ('Plum', 138, 17),
           ('Walnut', 391, 18),
           ('Barley', 2, 19),
           ('Eggs', 50, 20);