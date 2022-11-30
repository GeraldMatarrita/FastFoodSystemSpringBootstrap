CREATE DATABASE db_project

DELIMITER //
/*Inserta en la bitácora los CRUDS que se hacen en foodlines*/
CREATE TRIGGER trig_insert_food_lines AFTER  INSERT ON food_lines
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert foodline ',CAST(NEW.id AS CHAR))), 10);
END//

CREATE TRIGGER trig_update_food_lines AFTER  UPDATE ON food_lines
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update foodline ',CAST(OLD.id AS CHAR))), 10);
END//

CREATE TRIGGER trig_delete_food_lines AFTER  DELETE ON food_lines
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete foodline ',CAST(OLD.id AS CHAR))), 10);
END//

SELECT * FROM USERS
INSERT INTO Food_lines VALUES(1, 'food1')
/*INSERT INTO ROLES VALUES (1, 'administrator')
//INSERT INTO FOOD_LINES VALUES (1,'papas')*/

/*Inserta en la bitácora los CRUDS que se hacen en payment_types*/
CREATE TRIGGER trig_insert_payment_types AFTER  INSERT ON payment_types
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert payment_types ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_update_payment_types AFTER UPDATE ON payment_types
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update payment_types ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_delete_payment_types AFTER UPDATE ON payment_types
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete payment_types ',CAST(OLD.id AS CHAR))), 1);
END//

/*Inserta en la bitácora los CRUDS que se hacen en prices*/
CREATE TRIGGER trig_insert_prices AFTER  INSERT ON prices
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert price',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_update_prices AFTER UPDATE ON prices
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update price ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_delete_prices AFTER UPDATE ON prices
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete price ',CAST(OLD.id AS CHAR))), 1);
END//

/*Inserta en la bitácora los CRUDS que se hacen en payment_processors*/
CREATE TRIGGER trig_insert_payment_processors AFTER  INSERT ON  payment_processors
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert payment processor',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_update_payment_processors AFTER UPDATE ON payment_processors
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update payment processor ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_delete_payment_processors AFTER UPDATE ON payment_processors
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete payment processors ',CAST(OLD.id AS CHAR))), 1);
END//

/*Inserta en la bitácora los CRUDS que se hacen en user*/
CREATE TRIGGER trig_insert_user AFTER  INSERT ON users
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert user',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_update_user AFTER UPDATE ON users
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update user ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_delete_user AFTER UPDATE ON users
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete user ',CAST(OLD.id AS CHAR))), 1);
END//

/*Inserta en la bitácora los CRUDS que se hacen en product_price*/
CREATE TRIGGER trig_insert_product_price AFTER  INSERT ON product_price
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert product_price ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_update_product_price AFTER UPDATE ON product_price
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update product_price ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_delete_product_price AFTER UPDATE ON product_price
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete product_price ',CAST(OLD.id AS CHAR))), 1);
END//

/*Inserta en la bitácora los CRUDS que se hacen en products*/
CREATE TRIGGER trig_insert_products AFTER  INSERT ON products
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert products ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_update_products AFTER UPDATE ON products
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update products ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_delete_products AFTER UPDATE ON products
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete products ',CAST(OLD.id AS CHAR))), 1);
END//

/*Inserta en la bitácora los CRUDS que se hacen en roles*/
CREATE TRIGGER trig_insert_roles AFTER  INSERT ON roles
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert roles ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_update_roles AFTER UPDATE ON roles
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update roles ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_delete_roles AFTER UPDATE ON roles
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete roles ',CAST(OLD.id AS CHAR))), 1);
END//

/*Inserta en la bitácora los CRUDS que se hacen en card_types*/
CREATE TRIGGER trig_insert_card_types AFTER  INSERT ON card_types
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert card_types ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_update_card_types AFTER UPDATE ON card_types
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update card_types ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_delete_card_types AFTER UPDATE ON card_types
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete card_types ',CAST(OLD.id AS CHAR))), 1);
END//

/*Inserta en la bitácora los CRUDS que se hacen en card_types*/
CREATE TRIGGER trig_insert_card_types AFTER  INSERT ON card_types
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert card_types ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_update_card_types AFTER UPDATE ON card_types
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update card_types ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_delete_card_types AFTER UPDATE ON card_types
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete card_types ',CAST(OLD.id AS CHAR))), 1);
END//

/*Inserta en la bitácora los CRUDS que se hacen en discount_tickets*/
CREATE TRIGGER trig_insert_discount_tickets AFTER  INSERT ON discount_tickets
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('insert discount_tickets ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_update_discount_tickets AFTER UPDATE ON discount_tickets
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('update discount_tickets ',CAST(NEW.id AS CHAR))), 1);
END//

CREATE TRIGGER trig_delete_discount_tickets AFTER UPDATE ON discount_tickets
FOR EACH ROW
BEGIN 
	INSERT INTO log_book VALUES (LAST_INSERT_ID(),current_timestamp(),
    (SELECT CONCAT('delete discount_tickets ',CAST(OLD.id AS CHAR))), 1);
END//
DELIMITER ;

/*
SELECT * FROM USERS
DROP TRIGGER trig_insert_food_lines;
DROP TRIGGER trig_update_food_lines;
DROP TRIGGER trig_delete_food_lines;
SELECT * FROM USERS
SELECT * FROM log_book

INSERT INTO Food_lines VALUES(1,'food1')
INSERT INTO LOG_BOOK VALUES (1,CURRENT_TIMESTAMP, 'insert on foodlines', 1);
*/