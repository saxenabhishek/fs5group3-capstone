DROP TABLE ft_price;
DROP TABLE ft_trade;
DROP TABLE ft_order;
DROP TABLE ft_instrument;
DROP TABLE ft_preference;
DROP TABLE FT_CLIENT;
DROP TABLE FT_CLIENT_IDENTIFICATION;
DROP TABLE FT_PERSON;


CREATE TABLE FT_PERSON (
    id VARCHAR(255) PRIMARY KEY,
    country VARCHAR(255) NOT NULL,
    postalCode VARCHAR(255) NOT NULL,
    dob DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
-- Inserting values into FT_PERSON with matching date format
INSERT INTO FT_PERSON (id, country, postalCode, dob, email, password) VALUES ('UID001', 'United States', '12345', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 'john@example.com', 'securepassword');
INSERT INTO FT_PERSON (id, country, postalCode, dob, email, password) VALUES ('UID002', 'Canada', 'A1A 1A1', TO_DATE('1985-03-15', 'YYYY-MM-DD'), 'jane@example.com', 'strongpass123');
INSERT INTO FT_PERSON (id, country, postalCode, dob, email, password) VALUES ('UID003', 'United Kingdom', 'SW1A 1AA', TO_DATE('1995-07-20', 'YYYY-MM-DD'), 'william@example.com', 'mypassword123');


CREATE TABLE FT_CLIENT_IDENTIFICATION (
    id NUMBER(6) PRIMARY KEY,
    person_id VARCHAR(255),
    identification_type VARCHAR(255) NOT NULL,
    identification_value VARCHAR(255) NOT NULL,
    FOREIGN KEY (person_id) REFERENCES FT_PERSON(id)
);

INSERT INTO FT_CLIENT_IDENTIFICATION (id, person_id, identification_type, identification_value) VALUES (456788, 'UID002', 'Passport', 'AB123456');
INSERT INTO FT_CLIENT_IDENTIFICATION (id, person_id, identification_type, identification_value) VALUES (259708, 'UID003', 'Passport', 'CD456789');
INSERT INTO FT_CLIENT_IDENTIFICATION (id, person_id, identification_type, identification_value) VALUES (794280, 'UID001', 'SSN', '123456789');

CREATE TABLE FT_CLIENT ( 
    	id VARCHAR(255) PRIMARY KEY, 
  	    identification_id NUMBER(6),  
    	FOREIGN KEY (id) REFERENCES FT_PERSON(id), 
    	FOREIGN KEY (identification_id) REFERENCES FT_CLIENT_IDENTIFICATION(id) 
 );
 
INSERT INTO FT_CLIENT (id, identification_id) VALUES ('UID001', 794280);
INSERT INTO FT_CLIENT (id, identification_id) VALUES  ('UID002', 456788);
INSERT INTO FT_CLIENT (id, identification_id) VALUES  ('UID003', 259708);

CREATE TABLE ft_instrument (
    id VARCHAR2(10) PRIMARY KEY,
    externalIdType VARCHAR2(10),
    externalId VARCHAR2(20),
    categoryId VARCHAR2(10),
    instrumentDescription VARCHAR2(255),
    maxQuantity NUMBER(10),
    minQuantity NUMBER(10)
);

CREATE TABLE ft_price (
    id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,
    askPrice NUMBER(10, 5),
    bidPrice NUMBER(10, 5),
    priceTimestamp TIMESTAMP,
    instrumentId VARCHAR2(10),
    CONSTRAINT fk_instrument FOREIGN KEY (instrumentId) REFERENCES ft_instrument(id)
);

-- Insert data into the Instrument table
INSERT into ft_instrument (id, externalIdType, externalId, categoryId, instrumentDescription, maxQuantity, minQuantity) VALUES ('T67890', 'CUSIP', '9128285M8', 'GOVT', 'USA, Note 3.125 15nov2028 10Y', 10000, 100);
INSERT into ft_instrument (id, externalIdType, externalId, categoryId, instrumentDescription, maxQuantity, minQuantity) VALUES ('T67894', 'CUSIP', '9128285Z9', 'GOVT', 'USA, Note 2.5 31jan2024 5Y', 10000, 100);
INSERT into ft_instrument (id, externalIdType, externalId, categoryId, instrumentDescription, maxQuantity, minQuantity) VALUES ('T67895', 'CUSIP', '9128286A3', 'GOVT', 'USA, Note 2.625 31jan2026 7Y', 10000, 100);
INSERT into ft_instrument (id, externalIdType, externalId, categoryId, instrumentDescription, maxQuantity, minQuantity) VALUES ('T67897', 'CUSIP', '9128285X4', 'GOVT', 'USA, Note 2.5 31jan2021 2Y', 10000, 100);
INSERT into ft_instrument (id, externalIdType, externalId, categoryId, instrumentDescription, maxQuantity, minQuantity) VALUES ('T67899', 'CUSIP', '9128285V8', 'GOVT', 'USA, Notes 2.5% 15jan2022 3Y', 10000, 100);
INSERT into ft_instrument (id, externalIdType, externalId, categoryId, instrumentDescription, maxQuantity, minQuantity) VALUES ('T67880', 'CUSIP', '9128285U0', 'GOVT', 'USA, Note 1.5 31dec2023 5Y', 10000, 100);
INSERT into ft_instrument (id, externalIdType, externalId, categoryId, instrumentDescription, maxQuantity, minQuantity) VALUES ('T67883', 'CUSIP', '912810SE9', 'GOVT', 'USA, Bond 3.375 15nov2048 30Y', 10000, 100);
INSERT into ft_instrument (id, externalIdType, externalId, categoryId, instrumentDescription, maxQuantity, minQuantity) VALUES ('T67878', 'CUSIP', '912810SD1', 'GOVT', 'USA, Bond 3 15aug2048 30Y', 10000, 100);
INSERT into ft_instrument (id, externalIdType, externalId, categoryId, instrumentDescription, maxQuantity, minQuantity) VALUES ('Q123', 'CUSIP', '02079K107', 'STOCK', 'Alphabet Inc. Class C Capital Stock', 10000, 1);

INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (1.03375, 1.03390625, TO_TIMESTAMP('21-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'T67890');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (0.998125, 0.99828125, TO_TIMESTAMP('21-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'T67894');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (1.0, 1.00015625, TO_TIMESTAMP('21-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'T67895');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (6.0, 1.234, TO_TIMESTAMP('22-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'T67895');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (0.999375, 0.999375, TO_TIMESTAMP('21-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'T67897');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (0.999375, 0.999375, TO_TIMESTAMP('21-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'T67899');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (1.00375, 1.00375, TO_TIMESTAMP('21-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'T67880');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (1.0596875, 1.0596875, TO_TIMESTAMP('21-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'T67883');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (0.9853125, 0.98546875, TO_TIMESTAMP('21-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'T67878');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (1162.42, 1161.42, TO_TIMESTAMP('21-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'Q123');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (1172.42, 1162.42, TO_TIMESTAMP('22-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'Q123');
INSERT INTO ft_price (askPrice, bidPrice, priceTimestamp, instrumentId) VALUES (1182.42, 1175.42, TO_TIMESTAMP('23-AUG-2019 10.00.02.000000000', 'DD-MON-YYYY HH.MI.SS.FF'), 'Q123');

ALTER TABLE "SCOTT"."FT_PRICE" MODIFY ("PRICETIMESTAMP" NOT NULL ENABLE);
ALTER TABLE "SCOTT"."FT_PRICE" ADD CONSTRAINT "FT_PRICE_PK" PRIMARY KEY ("ID", "PRICETIMESTAMP");

-- Create a new table for order data named ft_order with lowercase column names
CREATE TABLE ft_order (
    id VARCHAR2(10) PRIMARY KEY,
    instrumentid VARCHAR2(10) NOT NULL,
    quantity NUMBER(10) NOT NULL,
    targetprice NUMBER(10, 5) NOT NULL,
    direction VARCHAR2(4) NOT NULL,
    clientid VARCHAR2(10) NOT NULL,
    ordertimestamp TIMESTAMP NOT NULL
);

-- Add CHECK constraint for quantity
ALTER TABLE ft_order
ADD CONSTRAINT check_order_quantity CHECK (quantity >= 1);

-- Add CHECK constraint for targetPrice
ALTER TABLE ft_order
ADD CONSTRAINT check_order_target_price CHECK (targetprice > 0);

-- Add FOREIGN KEY constraint for clientId
ALTER TABLE ft_order
ADD CONSTRAINT fk_order_clientid
FOREIGN KEY (clientid)
REFERENCES ft_client(id);

-- Add FOREIGN KEY constraint for instrumentid referencing ft_instrument
ALTER TABLE ft_order
ADD CONSTRAINT fk_order_instrumentid
FOREIGN KEY (instrumentid)
REFERENCES ft_instrument(id);

-- Insert individual rows into the ft_order table one by one
INSERT INTO ft_order (id, instrumentid, quantity, targetprice, direction, clientid, ordertimestamp) 
VALUES ('OID001', 'Q123', 50, 1161.42, 'BUY', 'UID001', TIMESTAMP '2023-09-20 10:30:00');
INSERT INTO ft_order (id, instrumentid, quantity, targetprice, direction, clientid, ordertimestamp) 
VALUES ('OID002', 'T67878', 8000, 0.98546875, 'BUY', 'UID001', TIMESTAMP '2023-09-20 11:15:00');
INSERT INTO ft_order (id, instrumentid, quantity, targetprice, direction, clientid, ordertimestamp) 
VALUES ('OID003', 'T67880', 6500, 1.00375, 'BUY', 'UID001', TIMESTAMP '2023-09-20 12:00:00');
INSERT INTO ft_order (id, instrumentid, quantity, targetprice, direction, clientid, ordertimestamp) 
VALUES ('OID004', 'T67880', 6400, 1.00375, 'SELL', 'UID001', TIMESTAMP '2023-09-20 12:00:00');

-- Create a new table for trade data named ft_trade with lowercase column names and NOT NULL constraints
CREATE TABLE ft_trade (    
    id VARCHAR2(10) PRIMARY KEY,
    orderid VARCHAR2(10) NOT NULL,
    instrumentid VARCHAR2(10) NOT NULL,
    quantity NUMBER(10) NOT NULL,
    targetprice NUMBER(10, 5) NOT NULL,
    direction VARCHAR2(4) NOT NULL,
    clientid VARCHAR2(10) NOT NULL,
    cashvalue NUMBER(10, 2) NOT NULL,
    executionprice NUMBER(10, 5) NOT NULL,
    tradetimestamp TIMESTAMP NOT NULL
);

-- Add CHECK constraint for quantity
ALTER TABLE ft_trade
ADD CONSTRAINT check_trade_target_price CHECK (targetprice > 0);

-- Add CHECK constraint for targetPrice
ALTER TABLE ft_trade
ADD CONSTRAINT check_trade_execution_price CHECK (executionprice > 0);

-- Add CHECK constraint for cashvalue to ensure it's greater than 0
ALTER TABLE ft_trade
ADD CONSTRAINT check_trade_cashvalue
CHECK (cashvalue > 0);

-- Add FOREIGN KEY constraint for orderid referencing ft_order
ALTER TABLE ft_trade
ADD CONSTRAINT fk_trade_orderid
FOREIGN KEY (orderid)
REFERENCES ft_order(id);

-- Add FOREIGN KEY constraint for clientid referencing ft_client
ALTER TABLE ft_trade
ADD CONSTRAINT fk_trade_clientid
FOREIGN KEY (clientid)
REFERENCES ft_client(id);

-- Add FOREIGN KEY constraint for instrumentid referencing ft_instrument
ALTER TABLE ft_trade
ADD CONSTRAINT fk_trade_instrumentid
FOREIGN KEY (instrumentid)
REFERENCES ft_instrument(id);

-- Insert trade data into the ft_trade table one by one
INSERT INTO ft_trade (id, orderid, instrumentid, quantity, targetprice, direction, clientid, cashvalue, executionprice, tradetimestamp) 
VALUES ('TID001', 'OID001', 'Q123', 50, 1161.42, 'BUY', 'UID001', 58071, 1161.42, TIMESTAMP '2023-09-20 10:30:00');
INSERT INTO ft_trade (id, orderid, instrumentid, quantity, targetprice, direction, clientid, cashvalue, executionprice, tradetimestamp) 
VALUES ('TID002', 'OID002', 'T67878', 8000, 0.98546875, 'BUY', 'UID001', 7883.75, 0.98546875, TIMESTAMP '2023-09-20 11:15:09');
INSERT INTO ft_trade (id, orderid, instrumentid, quantity, targetprice, direction, clientid, cashvalue, executionprice, tradetimestamp) 
VALUES ('TID003', 'OID003', 'T67880', 6500, 1.00375, 'BUY', 'UID001', 6524.375, 1.00375, TIMESTAMP '2023-09-20 12:00:50');
INSERT INTO ft_trade (id, orderid, instrumentid, quantity, targetprice, direction, clientid, cashvalue, executionprice, tradetimestamp) 
VALUES ('TID004', 'OID004', 'T67880', 6400, 1.00375, 'SELL', 'UID001', 6424.0, 1.00375, TIMESTAMP '2023-09-20 12:00:50');

CREATE TABLE ft_preference (
    client_id VARCHAR2(10) PRIMARY KEY,
    investment_purpose VARCHAR2(10),
    risk_tolerance VARCHAR2(20),
    income_category VARCHAR2(20),
    length_of_investment VARCHAR2(20), 
    is_checked VARCHAR2(1)
    
);
 
ALTER TABLE ft_preference
ADD CONSTRAINT fk_preference_clientid
FOREIGN KEY (client_id)
REFERENCES ft_client(id);

INSERT INTO ft_preference (client_id, investment_purpose, risk_tolerance, income_category, length_of_investment, is_checked)
VALUES ('UID001','College','AVERAGE','SixtyKToEigthyK','ZeroToFiveYears','T');
INSERT INTO ft_preference (client_id, investment_purpose, risk_tolerance, income_category, length_of_investment, is_checked)
VALUES ('UID002','Retirement','ABOVE_AVERAGE','EigthyKToOneL','FiveToSevenYears','T');

