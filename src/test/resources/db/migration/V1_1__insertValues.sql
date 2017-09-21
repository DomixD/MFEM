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

-- Neue Klassifizierung hinzufügen
insert into classification (classificationid, description, name) values (1, 'KlassDes1', 'KlassName1');

-- Anforderung hinzufügen
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (1, 'Anforderung1.1', 1, 1, 2);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (2, 'Anforderung1.2', 1, 2, 2);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (3, 'Anforderung1.3', 1, 3, 1);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (4, 'Anforderung1.4', 1, 4, 1);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (5, 'Anforderung1.5', 1, 5, 0);
insert into requirement (requirementid, content, classi_classificationid, category_categoryid, priority) values (6, 'Anforderung1.6', 1, 6, 0);

-- Neue Frage hinzufügen
insert into question (questionid, question, metricid, require_requirementid) values (1, 'Frage1.1.1', 1, 1);
insert into question (questionid, question, metricid, require_requirementid) values (2, 'Frage1.2.1', 1, 2);
insert into question (questionid, question, metricid, require_requirementid) values (3, 'Frage1.3.1', 1, 3);
insert into question (questionid, question, metricid, require_requirementid) values (4, 'Frage1.4.1', 1, 4);
insert into question (questionid, question, metricid, require_requirementid) values (5, 'Frage1.5.1', 1, 5);
insert into question (questionid, question, metricid, require_requirementid) values (6, 'Frage1.6.1', 1, 6);

-- Beispiel Evaluation
insert into framework (frameworkid, descriptionfw, namefw) values (1, 'Beschreibung1','Framework1');
insert into framework_evaluation (framework_evaluationid, classificationid, frameworkid) values (1, 1, 1);
insert into feva_result (feva_resultid, framework_evaluationid, questionid, answerid) values (1, 1, 1, 1);
insert into feva_result (feva_resultid, framework_evaluationid, questionid, answerid) values (2, 1, 2, 2);
insert into feva_result (feva_resultid, framework_evaluationid, questionid, answerid) values (3, 1, 3, 3);
insert into feva_result (feva_resultid, framework_evaluationid, questionid, answerid) values (4, 1, 4, 1);
insert into feva_result (feva_resultid, framework_evaluationid, questionid, answerid) values (5, 1, 5, 2);
insert into feva_result (feva_resultid, framework_evaluationid, questionid, answerid) values (6, 1, 6, 3);

insert into user (userid , username ,password, role ) values (1, 'user', 'userPw', 'USER');
insert into user (userid , username ,password, role ) values (2, 'admin', 'admin', 'ADMIN');

insert into  role_access (role_accessid, role, read_access, write_access) values (1, 'USER', 1, 0);
insert into  role_access (role_accessid, role, read_access, write_access) values (2, 'ADMIN', 1, 1);