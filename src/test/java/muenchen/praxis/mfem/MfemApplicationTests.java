package muenchen.praxis.mfem;

import muenchen.praxis.mfem.entities.*;
import muenchen.praxis.mfem.persistence.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MfemApplicationTests {

	@Autowired
	private RepoCategory repoCategory;
	@Autowired
	private RepoMetric repoMetric;
	@Autowired
	private RepoAnswer repoAnswer;
	@Autowired
	private RepoClassification repoClassification;
	@Autowired
	private RepoRequirement repoRequirement;
	@Autowired
	private RepoQuestion repoQuestion;
	@Autowired
	private RepoFramework repoFramework;
	@Autowired
	private RepoFrameworkEvaluation repoFrameworkEvaluation;
	@Autowired
	private RepoFEvaResult repoFEvaResult;

	private List<Answer> answerList = new ArrayList<>();
	private List<Question> questionList = new ArrayList<>();
	private List<Question> questionListFeva = new ArrayList<>();
	private List<Requirement> requirementList = new ArrayList<>();
	private List<Requirement> requirementListClassi = new ArrayList<>();

	public void setUpDatabase() {
		//Metrik mit Antwortmöglichkeiten
		Metric metric = new Metric(1, "Metrik1", answerList);
		Answer answer1 = new Answer(1, "Antwort1", 1.0, metric);
		Answer answer2 = new Answer(2, "Antwort2", 0.5, metric);
		Answer answer3 = new Answer(3, "Antwort3", 0.0, metric);
		answerList.add(answer1);
		answerList.add(answer2);
		answerList.add(answer3);
		repoMetric.save(metric);
		repoAnswer.save(answer1);
		repoAnswer.save(answer2);
		repoAnswer.save(answer3);
		//Klassifizierung
		Classification classification = new Classification(1, "Klassifizierung1", "Beschreibung1", requirementListClassi);
		repoClassification.save(classification);
		//Kategorien mit Anforderungen und Fragen
		for (int i = 1; i <= 6; i++) {
			Category category = new Category(i, "Kategorie" + i, requirementList);
			Requirement requirement = new Requirement(i, "Anforderung" + i, questionList, classification, category, Priority.A);
			requirementList.add(requirement);
			requirementListClassi.add(requirement);
			Question question = new Question(i, "Frage" + i, metric, requirement);
			questionList.add(question);
			questionListFeva.add(question);
			repoCategory.save(category);
			repoRequirement.save(requirement);
			repoQuestion.save(question);
			requirementList.clear();
			questionList.clear();
		}
		//Evaluation
		Framework framework = new Framework(1, "Framework1", "Beschreibung1");
		FrameworkEvaluation frameworkEvaluation = new FrameworkEvaluation(1, framework, classification);
		repoFramework.save(framework);
		repoFrameworkEvaluation.save(frameworkEvaluation);
		FEvaResult fEvaResult = new FEvaResult(1, frameworkEvaluation, questionListFeva.get(0), answer1);
		repoFEvaResult.save(fEvaResult);
		fEvaResult = new FEvaResult(2, frameworkEvaluation, questionListFeva.get(1), answer2);
		repoFEvaResult.save(fEvaResult);
		fEvaResult = new FEvaResult(3, frameworkEvaluation, questionListFeva.get(2), answer3);
		repoFEvaResult.save(fEvaResult);
		fEvaResult = new FEvaResult(4, frameworkEvaluation, questionListFeva.get(3), answer1);
		repoFEvaResult.save(fEvaResult);
		fEvaResult = new FEvaResult(5, frameworkEvaluation, questionListFeva.get(4), answer2);
		repoFEvaResult.save(fEvaResult);
		fEvaResult = new FEvaResult(6, frameworkEvaluation, questionListFeva.get(5), answer3);
		repoFEvaResult.save(fEvaResult);
	}

	public void cleanUpDatabase() {
		repoFEvaResult.deleteAll();
		repoFrameworkEvaluation.deleteAll();
		repoFramework.deleteAll();
		repoQuestion.deleteAll();
		repoRequirement.deleteAll();
		repoClassification.deleteAll();
		repoCategory.deleteAll();
		repoAnswer.deleteAll();
		repoMetric.deleteAll();
	}

}
