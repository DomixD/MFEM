
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
insert into requirement (requirementid, content, classi_classificationid) values (1, 'Anforderung1.1', 1);
insert into requirement (requirementid, content, classi_classificationid) values (2, 'Anforderung1.2', 1);

insert into requirement (requirementid, content, classi_classificationid) values (3, 'Anforderung2.1', 2);
insert into requirement (requirementid, content, classi_classificationid) values (4, 'Anforderung2.2', 2);

insert into requirement (requirementid, content, classi_classificationid) values (5, 'Anforderung3.1', 3);
insert into requirement (requirementid, content, classi_classificationid) values (6, 'Anforderung3.2', 3);

-- Neue Frage hinzufügen
insert into question (questionid, question, metricid, require_requirementid) values (1, 'Frage1.1.1', 1, 1);
insert into question (questionid, question, metricid, require_requirementid) values (2, 'Frage1.1.2', 1, 1);

insert into question (questionid, question, metricid, require_requirementid) values (3, 'Frage1.2.1', 1, 2);
insert into question (questionid, question, metricid, require_requirementid) values (4, 'Frage1.2.2', 1, 2);
insert into question (questionid, question, metricid, require_requirementid) values (5, 'Frage1.2.3', 1, 2);

insert into question (questionid, question, metricid, require_requirementid) values (6, 'Frage2.1.1', 2, 3);
insert into question (questionid, question, metricid, require_requirementid) values (7, 'Frage2.1.2', 2, 3);

insert into question (questionid, question, metricid, require_requirementid) values (8, 'Frage2.2.1', 2, 4);
insert into question (questionid, question, metricid, require_requirementid) values (9, 'Frage2.2.2', 2, 4);

insert into question (questionid, question, metricid, require_requirementid) values (10, 'Frage3.1.1', 3, 5);
insert into question (questionid, question, metricid, require_requirementid) values (11, 'Frage3.1.2', 3, 5);
insert into question (questionid, question, metricid, require_requirementid) values (12, 'Frage3.1.3', 3, 5);

insert into question (questionid, question, metricid, require_requirementid) values (13, 'Frage3.2.1', 3, 6);
insert into question (questionid, question, metricid, require_requirementid) values (14, 'Frage3.2.2', 3, 6);

