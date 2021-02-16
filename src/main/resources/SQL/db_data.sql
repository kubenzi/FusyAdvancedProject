INSERT INTO public.currency (id, name, signature) VALUES (1, 'Euro', 'EUR');
INSERT INTO public.currency (id, name, signature) VALUES (2, 'Polish zloty', 'PLN');

INSERT INTO public.account_types (id, name) VALUES (1, 'Savings');
INSERT INTO public.account_types (id, name) VALUES (2, 'Current');

INSERT INTO public.banks (id, name) VALUES (1, 'ING');
INSERT INTO public.banks (id, name) VALUES (2, 'Millenium');

INSERT INTO public.users (id, enabled, locked, username, password) VALUES (1, true, false, 'bj', 'dupa');
INSERT INTO public.users (id, enabled, locked, username, password) VALUES (2, true, false, 'jch', 'okon');

-- INSERT INTO public.users (id, username, password) VALUES (1, 'bj', 'dupa');
-- INSERT INTO public.users (id, username, password) VALUES (2, 'jch', 'okon');

INSERT INTO public.user_data (email, first_name, last_name, user_id) VALUES ('barjakimko@gmail.com', 'Bartosz', 'Jakimko', 1);
INSERT INTO public.user_data (email, first_name, last_name, user_id) VALUES ('kchorazyk.97@gmail.com', 'Jakub', 'Chorążyk', 2);

INSERT INTO public.categories (id, builtin, name, user_id) VALUES (1, false, 'Whores', 1);
INSERT INTO public.categories (id, builtin, name, user_id) VALUES (2, false, 'Whores', 2);
INSERT INTO public.categories (id, builtin, name, user_id) VALUES (3, false, 'Alcohol', 1);
INSERT INTO public.categories (id, builtin, name, user_id) VALUES (4, false, 'Alcohol', 2);
INSERT INTO public.categories (id, builtin, name, user_id) VALUES (5, false, 'Drugs', 1);
INSERT INTO public.categories (id, builtin, name, user_id) VALUES (6, false, 'Drugs', 2);
INSERT INTO public.categories (id, builtin, name, user_id) VALUES (7, false, 'Cigarettes', 1);
INSERT INTO public.categories (id, builtin, name, user_id) VALUES (8, false, 'Cigarettes', 2);
INSERT INTO public.categories (id, builtin, name, user_id) VALUES (9, false, 'ORZO', 1);
INSERT INTO public.categories (id, builtin, name, user_id) VALUES (10, false, 'ORZO', 2);

INSERT INTO public.accounts (id, account_number, balance, builtin, name, account_type_id, currency_id, user_id) VALUES (1, '1234567812345678', 100000, false, 'ING', 2, 2, 1);
INSERT INTO public.accounts (id, account_number, balance, builtin, name, account_type_id, currency_id, user_id) VALUES (2, '0000111122223333', 10000000, false, 'PKO BP', 1, 2, 1);
INSERT INTO public.accounts (id, account_number, balance, builtin, name, account_type_id, currency_id, user_id) VALUES (3, '4444555566667777', 25000000, false, 'Bank Millenium', 1, 2, 2);
INSERT INTO public.accounts (id, account_number, balance, builtin, name, account_type_id, currency_id, user_id) VALUES (4, '8888999911112222', 100000, false, 'mBank', 2, 2, 2);

INSERT INTO public.operations (id, date, description, value, category_id, account_id) VALUES (1, '2020-11-29', 'Dinner', 21, 9, 1);
INSERT INTO public.operations (id, date, description, value, category_id, account_id) VALUES (2, '2020-11-29', 'Dinner ', 21, 10, 4);
INSERT INTO public.operations (id, date, description, value, category_id, account_id) VALUES (3, '2020-11-20', '-> ATM', 100, 10, 4);
INSERT INTO public.operations (id, date, description, value, category_id, account_id) VALUES (4, '2020-11-20', 'ATM ->', -100, 10, 3)
