
-- Neue Metrik hinzufügen
insert into metric (metricid, description) VALUES (1,'Metrik1');
insert into answer (answerid, content, value) values (1,'Antwort1.1',1.0);
insert into answer (answerid, content, value) values (2,'Antwort1.2',0.5);
insert into answer (answerid, content, value) values (3,'Antwort1.3',0.0);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (1,1);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (1,2);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (1,3);

insert into metric (metricid, description) VALUES (2,'Metrik2');
insert into answer (answerid, content, value) values (4,'Antwort2.1',1.0);
insert into answer (answerid, content, value) values (5,'Antwort2.2',0.5);
insert into answer (answerid, content, value) values (6,'Antwort2.3',0.0);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (2,4);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (2,5);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (2,6);

insert into metric (metricid, description) VALUES (3,'Metrik3');
insert into answer (answerid, content, value) values (7,'Antwort3.1',1.0);
insert into answer (answerid, content, value) values (8,'Antwort3.2',0.5);
insert into answer (answerid, content, value) values (9,'Antwort3.3',0.0);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (3,7);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (3,8);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (3,9);

insert into metric (metricid, description) VALUES (4,'Metrik4');
insert into answer (answerid, content, value) values (10,'Antwort4.1',1.0);
insert into answer (answerid, content, value) values (11,'Antwort4.2',0.5);
insert into answer (answerid, content, value) values (12,'Antwort4.3',0.0);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (4,10);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (4,11);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (4,12);

insert into metric (metricid, description) VALUES (5,'Metrik5');
insert into answer (answerid, content, value) values (13,'Antwort5.1',1.0);
insert into answer (answerid, content, value) values (14,'Antwort5.2',0.5);
insert into answer (answerid, content, value) values (15,'Antwort5.3',0.0);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (5,13);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (5,14);
insert into metric_answer_list (metric_metricid, answer_list_answerid) values (5,15);


-- Neue Klassifizierung hinzufügen
insert into classification (classificationid, description, name) values (1,'KlassDes1','KlassName1');
insert into classification (classificationid, description, name) values (2,'KlassDes2','KlassName2');
insert into classification (classificationid, description, name) values (3,'KlassDes3','KlassName3');

-- Anforderung hinzufügen
insert into requirement (requirementid, content) values (1, 'Anforderung1.1');
insert into classification_requirement_list (classification_classificationid, requirement_list_requirementid) values (1,1);
insert into requirement (requirementid, content) values (2, 'Anforderung1.2');
insert into classification_requirement_list (classification_classificationid, requirement_list_requirementid) values (1,2);

insert into requirement (requirementid, content) values (3, 'Anforderung2.1');
insert into classification_requirement_list (classification_classificationid, requirement_list_requirementid) values (2,3);
insert into requirement (requirementid, content) values (4, 'Anforderung2.2');
insert into classification_requirement_list (classification_classificationid, requirement_list_requirementid) values (2,4);

insert into requirement (requirementid, content) values (5, 'Anforderung3.1');
insert into classification_requirement_list (classification_classificationid, requirement_list_requirementid) values (3,5);
insert into requirement (requirementid, content) values (6, 'Anforderung3.2');
insert into classification_requirement_list (classification_classificationid, requirement_list_requirementid) values (3,6);

-- Neue Frage hinzufügen
insert into question (questionid, question, metricid) values (1,'Frage1.1',1);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (1,1);
insert into question (questionid, question, metricid) values (2,'Frage1.2',1);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (1,2);

insert into question (questionid, question, metricid) values (3,'Frage2.1',2);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (2,3);
insert into question (questionid, question, metricid) values (4,'Frage2.2',2);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (2,4);

insert into question (questionid, question, metricid) values (5,'Frage3.1',3);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (3,5);
insert into question (questionid, question, metricid) values (6,'Frage3.2',3);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (3,6);

insert into question (questionid, question, metricid) values (7,'Frage4.1',4);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (4,7);

insert into question (questionid, question, metricid) values (8,'Frage4.1',4);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (5,8);

insert into question (questionid, question, metricid) values (9,'Frage5.1',5);
insert into requirement_question_list (requirement_requirementid, question_list_questionid) values (6,9);

