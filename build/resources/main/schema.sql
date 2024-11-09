CREATE TABLE offers (
    offer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_wallet VARCHAR(100) NOT NULL,
    owner_username VARCHAR(50) NOT NULL,
    kwh_quantity FLOAT NOT NULL,
    price FLOAT NOT NULL,
    kwh_unit_price FLOAT NOT NULL,
    energy_source VARCHAR(100) NOT NULL
);