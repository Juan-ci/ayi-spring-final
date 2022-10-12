INSERT INTO `spring_final_ayidb`.`client` (`id_client`, `document_number`, `name`, `lastname`, `soft_delete`) SELECT 1, '30123456', 'Benjamín', 'Buttom', 0
WHERE NOT EXISTS (SELECT * FROM `client` WHERE id_client = 1);
INSERT INTO `spring_final_ayidb`.`client` (`id_client`, `document_number`, `name`, `lastname`, `soft_delete`) SELECT 2, '31234567', 'Flavia', 'Grey', 0
WHERE NOT EXISTS (SELECT * FROM `client` WHERE id_client = 2);
INSERT INTO `spring_final_ayidb`.`client` (`id_client`, `document_number`, `name`,  `lastname`, `soft_delete`) SELECT 3, '32345678', 'Alberto', 'Fredes', 0
WHERE NOT EXISTS (SELECT * FROM `client` WHERE id_client = 3);
INSERT INTO `spring_final_ayidb`.`client` (`id_client`, `document_number`, `name`, `lastname`, `soft_delete`) SELECT 4, '33568941', 'Claudia','Gonzalez', 0
WHERE NOT EXISTS (SELECT * FROM `client` WHERE id_client = 4);
INSERT INTO `spring_final_ayidb`.`client` (`id_client`, `document_number`, `name`, `lastname`, `soft_delete`) SELECT 5, '34652840', 'Hugo', 'Varela', 0
WHERE NOT EXISTS (SELECT * FROM `client` WHERE id_client = 5);
INSERT INTO `spring_final_ayidb`.`client` (`id_client`, `document_number`, `name`, `lastname`, `soft_delete`) SELECT 6, '35069054', 'Fabian', 'Castro', 0
WHERE NOT EXISTS (SELECT * FROM `client` WHERE id_client = 6);

--INSERT INTO `spring_final_ayidb`.`client_detail` (`id_client_detail`, `prime`, `acumulated_points`, `client_id`, `soft_delete`) SELECT 1, 1,'3000', 1, 0
--WHERE NOT EXISTS (SELECT * FROM `client_detail` WHERE id_client_detail = 1);
--INSERT INTO `spring_final_ayidb`.`client_detail` (`id_client_detail`, `prime`, `acumulated_points`, `client_id`, `soft_delete`) SELECT 2, 1,'3000', 2, 0
--WHERE NOT EXISTS (SELECT * FROM `client_detail` WHERE id_client_detail = 2);
--INSERT INTO `spring_final_ayidb`.`client_detail` (`id_client_detail`, `prime`, `acumulated_points`, `client_id`, `soft_delete`) SELECT 3, 1,'3000', 3, 0
--WHERE NOT EXISTS (SELECT * FROM `client_detail` WHERE id_client_detail = 3);
--INSERT INTO `spring_final_ayidb`.`client_detail` (`id_client_detail`, `prime`, `acumulated_points`, `client_id`, `soft_delete`) SELECT 4, 0,'3000', 4, 0
--WHERE NOT EXISTS (SELECT * FROM `client_detail` WHERE id_client_detail = 4);
--INSERT INTO `spring_final_ayidb`.`client_detail` (`id_client_detail`, `prime`, `acumulated_points`, `client_id`, `soft_delete`) SELECT 5, 0,'3000', 5, 0
--WHERE NOT EXISTS (SELECT * FROM `client_detail` WHERE id_client_detail = 5);
--INSERT INTO `spring_final_ayidb`.`client_detail` (`id_client_detail`, `prime`, `acumulated_points`, `client_id`, `soft_delete`) SELECT 6, 1,'3000', 6, 0
--WHERE NOT EXISTS (SELECT * FROM `client_detail` WHERE id_client_detail = 6);

--UPDATE `spring_final_ayidb`.`client` SET `client_detail_id` = '1' WHERE (`id_client` = '1');
--UPDATE `spring_final_ayidb`.`client` SET `client_detail_id` = '2' WHERE (`id_client` = '2');
--UPDATE `spring_final_ayidb`.`client` SET `client_detail_id` = '3' WHERE (`id_client` = '3');
--UPDATE `spring_final_ayidb`.`client` SET `client_detail_id` = '4' WHERE (`id_client` = '4');
--UPDATE `spring_final_ayidb`.`client` SET `client_detail_id` = '5' WHERE (`id_client` = '5');
--UPDATE `spring_final_ayidb`.`client` SET `client_detail_id` = '6' WHERE (`id_client` = '6');


INSERT INTO `spring_final_ayidb`.`address` (`id_address`, `district`, `country`, `floor`,  `street_number`, `postal_code`, `city`, `street`, `client_id`, `soft_delete`)
SELECT '1', 'Rosario', 'Argentina', '3', '5561', '2000', 'Santa Fe', 'Uquiza', '1', 0
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id_address = 1);
INSERT INTO `spring_final_ayidb`.`address` (`id_address`, `district`, `country`, `floor`,  `street_number`, `postal_code`, `city`, `street`, `client_id`, `soft_delete`)
SELECT '2', 'Guaymallén', 'Argentina', '1', '5530', '2216', 'Mendoza', 'Uquiza', '2', 0
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id_address = 2);
INSERT INTO `spring_final_ayidb`.`address` (`id_address`, `district`, `country`, `floor`,  `street_number`, `postal_code`, `city`, `street`, `client_id`, `soft_delete`)
SELECT '3', 'La Paz', 'Argentina', '0', '5520', '1235', 'Cordoba', 'Belgrano', '3', 0
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id_address = 3);
INSERT INTO `spring_final_ayidb`.`address` (`id_address`, `district`, `country`, `floor`,  `street_number`, `postal_code`, `city`, `street`, `client_id`, `soft_delete`)
SELECT '4', 'Corrientes', 'Argentina', '6', '5513', '2090', 'Corrientes', 'Guemes', '4', 0
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id_address = 4);
INSERT INTO `spring_final_ayidb`.`address` (`id_address`, `district`, `country`, `floor`,  `street_number`, `postal_code`, `city`, `street`, `client_id`, `soft_delete`)
SELECT '5', 'Resistencia', 'Argentina', '3', '5561', '2000', 'Chaco', 'Saavedra', '5', 0
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id_address = 5);
INSERT INTO `spring_final_ayidb`.`address` (`id_address`, `district`, `country`, `floor`,  `street_number`, `postal_code`, `city`, `street`, `client_id`, `soft_delete`)
SELECT '6', 'Posadas', 'Argentina', '1', '5561', '960', 'Misiones', 'Irigoyen', '6', 0
WHERE NOT EXISTS (SELECT * FROM `address` WHERE id_address = 6);


INSERT INTO `spring_final_ayidb`.`client_addresses` (`client_id_client`, `addresses_id_address`) SELECT '1', '1'
WHERE NOT EXISTS (SELECT * FROM `client_addresses` WHERE addresses_id_address = 1);
INSERT INTO `spring_final_ayidb`.`client_addresses` (`client_id_client`, `addresses_id_address`) SELECT '2', '2'
WHERE NOT EXISTS (SELECT * FROM `client_addresses` WHERE addresses_id_address = 2);
INSERT INTO `spring_final_ayidb`.`client_addresses` (`client_id_client`, `addresses_id_address`) SELECT '3', '3'
WHERE NOT EXISTS (SELECT * FROM `client_addresses` WHERE addresses_id_address = 3);
INSERT INTO `spring_final_ayidb`.`client_addresses` (`client_id_client`, `addresses_id_address`) SELECT '4', '4'
WHERE NOT EXISTS (SELECT * FROM `client_addresses` WHERE addresses_id_address = 4);
INSERT INTO `spring_final_ayidb`.`client_addresses` (`client_id_client`, `addresses_id_address`) SELECT '5', '5'
WHERE NOT EXISTS (SELECT * FROM `client_addresses` WHERE addresses_id_address = 5);
INSERT INTO `spring_final_ayidb`.`client_addresses` (`client_id_client`, `addresses_id_address`) SELECT '6', '6'
WHERE NOT EXISTS (SELECT * FROM `client_addresses` WHERE addresses_id_address = 6);