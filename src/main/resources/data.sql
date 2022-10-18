INSERT INTO `spring_final_ayidb`.`marker` (`id_marker`, `name`, `lastname`, `longitude`, `latitude`, `soft_delete`)
SELECT 1, 'Berlim', 'Germany', 13.404954, 52.520007, 0
WHERE NOT EXISTS (SELECT * FROM `marker` WHERE id_marker = 1);
INSERT INTO `spring_final_ayidb`.`marker` (`id_marker`, `name`, `lastname`, `longitude`, `latitude`, `soft_delete`)
SELECT 2, 'Sao Paulo', 'Brazil', -46.633309, -23.55052, 0
WHERE NOT EXISTS (SELECT * FROM `marker` WHERE id_marker = 2);
INSERT INTO `spring_final_ayidb`.`marker` (`id_marker`, `name`, `lastname`, `longitude`, `latitude`, `soft_delete`)
SELECT 3, 'Moscow', 'Russia', 37.6173, 55.755826, 0
WHERE NOT EXISTS (SELECT * FROM `marker` WHERE id_marker = 3);