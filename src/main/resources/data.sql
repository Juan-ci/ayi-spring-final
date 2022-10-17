INSERT INTO `spring_final_ayidb`.`marker` (`id_marker`, `name`, `lastname`, `longitude`, `latitude`, `soft_delete`)
SELECT 1, 'Jose', 'Canseco', 52.520007, 13.404954, 0
WHERE NOT EXISTS (SELECT * FROM `marker` WHERE id_marker = 1);