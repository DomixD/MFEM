
-- Neue Metrik hinzufügen
insert into metric (metricid, description) VALUES (1,'Metrik1');
insert into answer (answerid, content, value) values (1,'Antwort1',1.0);
insert into answer (answerid, content, value) values (2,'Antwort2',0.5);
insert into answer (answerid, content, value) values (3,'Antwort3',0.0);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (1,1);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (1,2);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (1,3);

-- Neue Klassifizierung hinzufügen
insert into classification (classificationid, description, name) values (1,'KlassDes','KlassName');
-- Anforderung hinzufügen
insert into requirement (requirementid, content) values (1, 'Anforderung1');
insert into classification_requirement_list (classification_classificationid, requirement_list_requirementid) values (1,1);

-- Neue Frage hinzufügen
insert into question (questionid, question, metricid) values (1,'Frage1',1);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (1,1);
insert into question (questionid, question, metricid) values (2,'Frage2',1);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (1,2);


