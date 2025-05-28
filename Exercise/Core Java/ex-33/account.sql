CREATE TABLE accounts (
    id INTEGER PRIMARY KEY,
    account_holder TEXT NOT NULL,
    balance REAL NOT NULL
);
INSERT INTO accounts (id, account_holder, balance) VALUES (1, 'Alice', 1000.00);
INSERT INTO accounts (id, account_holder, balance) VALUES (2, 'Bob', 500.00);