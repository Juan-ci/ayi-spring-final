INSERT INTO `spring_final_ayidb`.`client` (`id_client`, `document_number`, `name`, `lastname`, `soft_delete`) SELECT 1, '30123456', 'Benjamín', 'Buttom', 0
WHERE NOT EXISTS (SELECT * FROM `client` WHERE id_client = 1);