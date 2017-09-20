-- Kategorien hinzufügen
insert into category (categoryid, category) values (1, 'Funktionalität');
insert into category (categoryid, category) values (2, 'Performance');
insert into category (categoryid, category) values (3, 'Benutzbarkeit');
insert into category (categoryid, category) values (4, 'Sicherheit');
insert into category (categoryid, category) values (5, 'Wartbarkeit');
insert into category (categoryid, category) values (6, 'Zukunftssicherheit');

-- Neue Metrik hinzufügen
insert into metric (metricid, description) VALUES (1, 'Metrik1');
insert into answer (answerid, content, value, metri_metricid) values (1, 'Antwort1.1', 1.0, 1);
insert into answer (answerid, content, value, metri_metricid) values (2, 'Antwort1.2', 0.5, 1);
insert into answer (answerid, content, value, metri_metricid) values (3, 'Antwort1.3', 0.0, 1);

insert into metric (metricid, description) VALUES (2, 'Metrik2');
insert into answer (answerid, content, value, metri_metricid) values (4, 'Antwort2.1', 1.0, 2);
insert into answer (answerid, content, value, metri_metricid) values (5, 'Antwort2.2', 0.5, 2);
insert into answer (answerid, content, value, metri_metricid) values (6, 'Antwort2.3', 0.0, 2);

insert into metric (metricid, description) VALUES (3, 'Metrik3');
insert into answer (answerid, content, value, metri_metricid) values (7, 'Antwort3.1', 1.0, 3);
insert into answer (answerid, content, value, metri_metricid) values (8, 'Antwort3.2', 0.5, 3);
insert into answer (answerid, content, value, metri_metricid) values (9, 'Antwort3.3', 0.0, 3);

insert into metric (metricid, description) VALUES (4, 'Metrik4');
insert into answer (answerid, content, value, metri_metricid) values (10, 'Antwort4.1', 1.0, 4);
insert into answer (answerid, content, value, metri_metricid) values (11, 'Antwort4.2', 0.5, 4);
insert into answer (answerid, content, value, metri_metricid) values (12, 'Antwort4.3', 0.0, 4);

insert into metric (metricid, description) VALUES (5, 'Metrik5');
insert into answer (answerid, content, value, metri_metricid) values (13, 'Antwort5.1', 1.0, 5);
insert into answer (answerid, content, value, metri_metricid) values (14, 'Antwort5.2', 0.5, 5);
insert into answer (answerid, content, value, metri_metricid) values (15, 'Antwort5.3', 0.0, 5);

-- Neue Klassifizierung hinzufügen
insert into classification (classificationid, description, name) values (1, 'KlassDes1', 'KlassName1');
insert into classification (classificationid, description, name) values (2, 'KlassDes2', 'KlassName2');
insert into classification (classificationid, description, name) values (3, 'KlassDes3', 'KlassName3');

-- Anforderung hinzufügen
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (1, 'Anforderung1.1', 1, 1, 2);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (2, 'Anforderung1.2', 1, 2, 2);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (3, 'Anforderung1.3', 1, 3, 1);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (4, 'Anforderung1.4', 1, 4, 1);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (5, 'Anforderung1.5', 1, 5, 0);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (6, 'Anforderung1.6', 1, 6, 0);

insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (7, 'Anforderung2.1', 2, 1, 2);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (9, 'Anforderung2.2', 2, 2, 2);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (8, 'Anforderung2.3', 2, 3, 1);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (10, 'Anforderung2.4', 2, 4, 1);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (11, 'Anforderung2.5', 2, 5, 0);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (12, 'Anforderung2.6', 2, 6, 0);

insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (13, 'Anforderung3.1', 3, 1, 2);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (14, 'Anforderung3.2', 3, 2, 2);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (15, 'Anforderung3.3', 3, 3, 1);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (16, 'Anforderung3.4', 3, 4, 1);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (17, 'Anforderung3.5', 3, 5, 0);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (18, 'Anforderung3.6', 3, 6, 0);

-- Neue Frage hinzufügen
insert into question (questionid, question, metricid, require_requirementid) values (1, 'Frage1.1.1', 2, 1);
insert into question (questionid, question, metricid, require_requirementid) values (2, 'Frage1.1.2', 1, 1);

insert into question (questionid, question, metricid, require_requirementid) values (3, 'Frage1.2.1', 1, 2);
insert into question (questionid, question, metricid, require_requirementid) values (4, 'Frage1.2.2', 1, 2);
insert into question (questionid, question, metricid, require_requirementid) values (5, 'Frage1.2.3', 4, 2);

insert into question (questionid, question, metricid, require_requirementid) values (6, 'Frage1.3.1', 3, 3);

insert into question (questionid, question, metricid, require_requirementid) values (7, 'Frage1.4.1', 1, 4);

insert into question (questionid, question, metricid, require_requirementid) values (8, 'Frage1.5.1', 1, 5);

insert into question (questionid, question, metricid, require_requirementid) values (9, 'Frage1.6.1', 1, 6);


insert into question (questionid, question, metricid, require_requirementid) values (10, 'Frage2.1.1', 1, 7);
insert into question (questionid, question, metricid, require_requirementid) values (11, 'Frage2.1.2', 1, 7);

insert into question (questionid, question, metricid, require_requirementid) values (12, 'Frage2.2.1', 1, 8);
insert into question (questionid, question, metricid, require_requirementid) values (13, 'Frage2.2.2', 1, 8);
insert into question (questionid, question, metricid, require_requirementid) values (14, 'Frage2.2.3', 1, 8);

insert into question (questionid, question, metricid, require_requirementid) values (15, 'Frage2.3.1', 1, 9);

insert into question (questionid, question, metricid, require_requirementid) values (16, 'Frage2.4.1', 1, 10);

insert into question (questionid, question, metricid, require_requirementid) values (17, 'Frage2.5.1', 1, 11);

insert into question (questionid, question, metricid, require_requirementid) values (18, 'Frage2.6.1', 1, 12);


insert into question (questionid, question, metricid, require_requirementid) values (19, 'Frage3.1.1', 1, 13);
insert into question (questionid, question, metricid, require_requirementid) values (20, 'Frage3.1.2', 1, 13);

insert into question (questionid, question, metricid, require_requirementid) values (21, 'Frage3.2.1', 1, 14);
insert into question (questionid, question, metricid, require_requirementid) values (22, 'Frage3.2.2', 1, 14);
insert into question (questionid, question, metricid, require_requirementid) values (23, 'Frage3.2.3', 1, 14);

insert into question (questionid, question, metricid, require_requirementid) values (24, 'Frage3.3.1', 1, 15);

insert into question (questionid, question, metricid, require_requirementid) values (25, 'Frage3.4.1', 1, 16);

insert into question (questionid, question, metricid, require_requirementid) values (26, 'Frage3.5.1', 1, 17);

insert into question (questionid, question, metricid, require_requirementid) values (27, 'Frage3.6.1', 1, 18);

insert into user (userid , username ,password, role ) values (1,'user','userPw','USER');

insert into  role_access (role_accessid, role, read_access, write_access) values (1,'USER',1,1);