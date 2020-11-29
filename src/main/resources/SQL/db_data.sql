INSERT INTO public.currency (id, name, signature) VALUES (1, 'Euro', 'EUR');
INSERT INTO public.currency (id, name, signature) VALUES (2, 'Polish zloty', 'PLN');

INSERT INTO public.account_types (id, name) VALUES (1, 'Savings');
INSERT INTO public.account_types (id, name) VALUES (2, 'Current')

INSERT INTO public.operation_types (id, name) VALUES (1, 'IN');
INSERT INTO public.operation_types (id, name) VALUES (2, 'OUT');
INSERT INTO public.operation_types (id, name) VALUES (3, 'TRANSFER');

INSERT INTO public.users (id, email, first_name, last_name, login, password) VALUES (1, 'barjakimko@gmail.com', 'Bartosz', 'Jakimko', 'bj', 'dupa');
INSERT INTO public.users (id, email, first_name, last_name, login, password) VALUES (2, 'kchorazyk.97@gmail.com', 'Jakub', 'Chorążyk', 'jch', 'okon');

INSERT INTO public.categories (id, name, user_id) VALUES (1, 'Whores', 1);
INSERT INTO public.categories (id, name, user_id) VALUES (2, 'Whores', 2);
INSERT INTO public.categories (id, name, user_id) VALUES (3, 'Alcohol', 1);
INSERT INTO public.categories (id, name, user_id) VALUES (4, 'Alcohol', 2);
INSERT INTO public.categories (id, name, user_id) VALUES (5, 'Drugs', 1);
INSERT INTO public.categories (id, name, user_id) VALUES (6, 'Drugs', 2);
INSERT INTO public.categories (id, name, user_id) VALUES (7, 'Cigarettes', 1);
INSERT INTO public.categories (id, name, user_id) VALUES (8, 'Cigarettes', 2);
INSERT INTO public.categories (id, name, user_id) VALUES (9, 'ORZO', 1);
INSERT INTO public.categories (id, name, user_id) VALUES (10, 'ORZO', 2);

INSERT INTO public.accounts (id, account_number, balance, name, account_type_id, currency_id, user_id) VALUES (1, '1234567812345678', 100000, 'ING', 2, 2, 1);
INSERT INTO public.accounts (id, account_number, balance, name, account_type_id, currency_id, user_id) VALUES (2, '0000111122223333', 10000000, 'PKO BP', 1, 2, 1);
INSERT INTO public.accounts (id, account_number, balance, name, account_type_id, currency_id, user_id) VALUES (3, '4444555566667777', 25000000, 'Bank Millenium', 1, 2, 2);
INSERT INTO public.accounts (id, account_number, balance, name, account_type_id, currency_id, user_id) VALUES (4, '8888999911112222', 100000, 'mBank', 2, 2, 2);

INSERT INTO public.operations (id, date, description, value, category_id, operation_type_id, account_id) VALUES (1, '2020-11-29 15:03:53.000000', 'Dinner', 21, 9, 2, 1);
INSERT INTO public.operations (id, date, description, value, category_id, operation_type_id, account_id) VALUES (2, '2020-11-29 15:05:51.000000', 'Dinner ', 21, 10, 2, 4);
INSERT INTO public.operations (id, date, description, value, category_id, operation_type_id, account_id) VALUES (3, '2020-11-20 00:00:00.000000', '-> ATM', 100, 10, 3, 4);
INSERT INTO public.operations (id, date, description, value, category_id, operation_type_id, account_id) VALUES (4, '2020-11-20 00:00:00.000000', 'ATM ->', 100, 10, 3, 3);





