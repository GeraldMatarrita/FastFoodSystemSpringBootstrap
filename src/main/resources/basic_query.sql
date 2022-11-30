use db_diseño;

INSERT INTO `db_diseño`.`payment_types` (`id`, `name`) VALUES ('1', 'Tarjeta de Crédito o Débito');
INSERT INTO `db_diseño`.`payment_types` (`id`, `name`) VALUES ('2', 'Cheque Eléctronico');
INSERT INTO `db_diseño`.`payment_types` (`id`, `name`) VALUES ('3', 'Efectivo');

INSERT INTO `db_diseño`.`roles` (`id`, `name`) VALUES ('1', 'administrator');
INSERT INTO `db_diseño`.`roles` (`id`, `name`) VALUES ('2', 'security');
INSERT INTO `db_diseño`.`roles` (`id`, `name`) VALUES ('3', 'maintenance');
INSERT INTO `db_diseño`.`roles` (`id`, `name`) VALUES ('4', 'consultation');

INSERT INTO `db_diseño`.`users` (`id`, `answer_security`, `ask_security`, `mail`, `name`, `password`, `password_confirmed`, `state`, `role_id`) VALUES (1, 'pregunta', 'respuesta', 'correo@gmail.com', 'admin', 'admin', 'admin', b'1', 1);
INSERT INTO `db_diseño`.`users` (`id`, `answer_security`, `ask_security`, `mail`, `name`, `password`, `password_confirmed`, `state`, `role_id`) VALUES (2, 'pregunta', 'respuesta', 'correo@gmail.com', 'maintenance', 'maintenance', 'maintenance', b'1', 3);
INSERT INTO `db_diseño`.`users` (`id`, `answer_security`, `ask_security`, `mail`, `name`, `password`, `password_confirmed`, `state`, `role_id`) VALUES (3, 'pregunta', 'respuesta', 'correo@gmail.com', 'security', 'security', 'security', b'1', 2);
