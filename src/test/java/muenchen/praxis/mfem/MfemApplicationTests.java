package muenchen.praxis.mfem;

import muenchen.praxis.mfem.entities.*;
import muenchen.praxis.mfem.persistence.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class MfemApplicationTests {

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
	@Autowired
	private RepoUser repoUser;

	//setUpDatabase
	private List<Answer> answerList = new ArrayList<>();
	private List<Question> questionList = new ArrayList<>();
	private List<Question> questionListFeva = new ArrayList<>();
	private List<Requirement> requirementList = new ArrayList<>();
	private List<Requirement> requirementListClassi = new ArrayList<>();

	private Classification classification;
	private ArrayList<Category> categories = new ArrayList<>();
	private ArrayList<FEvaResult> results = new ArrayList<>();
	private Framework framework;
	private FrameworkEvaluation frameworkEvaluation;
	private ArrayList<FrameworkEvaluation> frameworkEvaluations = new ArrayList<>();

	public Classification getClassification() {
		return classification;
	}

	public Framework getFramework() {
		return framework;
	}

	public FrameworkEvaluation getFrameworkEvaluation() {
		return frameworkEvaluation;
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public ArrayList<FEvaResult> getResults() {
		return results;
	}

	public ArrayList<FrameworkEvaluation> getFrameworkEvaluations() {
		return frameworkEvaluations;
	}

	public void setUpGetFrames() {
		ArrayList<Requirement> requirementListClassi = new ArrayList<>();
		classification = new Classification(1, "Klassifizierung1", "Beschreibung1", requirementListClassi);
		framework = new Framework(1, "Framework1", "Beschreibung1");
		frameworkEvaluation = new FrameworkEvaluation(1, framework, classification);
		frameworkEvaluations.add(frameworkEvaluation);
		framework = new Framework(2, "Framework2", "Beschreibung2");
		frameworkEvaluation = new FrameworkEvaluation(2, framework, classification);
		frameworkEvaluations.add(frameworkEvaluation);
		framework = new Framework(3, "Framework3", "Beschreibung3");
		frameworkEvaluation = new FrameworkEvaluation(3, framework, classification);
		frameworkEvaluations.add(frameworkEvaluation);

	}

	public void setUpGetRes() {
		ArrayList<Answer> answerList = new ArrayList<>();
		Metric metric = new Metric(1, "Metrik1", answerList);
		Answer answer1 = new Answer(1, "Antwort1", 1.0, metric);
		Answer answer2 = new Answer(2, "Antwort2", 0.5, metric);
		Answer answer3 = new Answer(3, "Antwort3", 0.0, metric);
		answerList.add(answer1);
		answerList.add(answer2);
		answerList.add(answer3);
		ArrayList<Requirement> requirementListClassi = new ArrayList<>();
		classification = new Classification(1, "Klassifizierung1", "Beschreibung1", requirementListClassi);
		ArrayList<Question> questionListFeva = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			ArrayList<Requirement> requirementList = new ArrayList<>();
			ArrayList<Question> questionList = new ArrayList<>();
			Category category = new Category(i, "Kategorie" + i, requirementList);
			categories.add(category);
			Requirement requirement = new Requirement(i, "Anforderung" + i, questionList, classification, category, Priority.A);
			requirementList.add(requirement);
			requirementListClassi.add(requirement);
			Question question = new Question(i, "Frage" + i, metric, requirement);
			questionList.add(question);
			questionListFeva.add(question);
		}
		framework = new Framework(1, "Framework1", "Beschreibung1");
		frameworkEvaluation = new FrameworkEvaluation(1, framework, classification);
		FEvaResult fEvaResult = new FEvaResult(1, frameworkEvaluation, questionListFeva.get(0), answer1);
		results.add(fEvaResult);
		fEvaResult = new FEvaResult(2, frameworkEvaluation, questionListFeva.get(1), answer2);
		results.add(fEvaResult);
		fEvaResult = new FEvaResult(3, frameworkEvaluation, questionListFeva.get(2), answer3);
		results.add(fEvaResult);
		fEvaResult = new FEvaResult(4, frameworkEvaluation, questionListFeva.get(3), answer1);
		results.add(fEvaResult);
		fEvaResult = new FEvaResult(5, frameworkEvaluation, questionListFeva.get(4), answer2);
		results.add(fEvaResult);
		fEvaResult = new FEvaResult(6, frameworkEvaluation, questionListFeva.get(5), answer3);
		results.add(fEvaResult);
	}

	public void setUpDatabase() {
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("CREATE_METRIC", "CREATE_CAT", "CREATE_CLASSI", "CREATE_QUEST", "CREATE_REQ", "EVALUATION", "ADMIN_ACCESS");
		Authentication authentication = new UsernamePasswordAuthenticationToken("auth", "auth", authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
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
		//User
		User user = new User(1, "user", "user", new ArrayList<>());
		repoUser.save(user);
	}

	public void cleanUpDatabase() {
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("CREATE_METRIC", "CREATE_CAT", "CREATE_CLASSI", "CREATE_QUEST", "CREATE_REQ", "EVALUATION", "ADMIN_ACCESS");
		Authentication authentication = new UsernamePasswordAuthenticationToken("auth", "auth", authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		repoFEvaResult.deleteAll();
		repoFrameworkEvaluation.deleteAll();
		repoFramework.deleteAll();
		repoQuestion.deleteAll();
		repoRequirement.deleteAll();
		repoClassification.deleteAll();
		repoCategory.deleteAll();
		repoAnswer.deleteAll();
		repoMetric.deleteAll();
		repoUser.deleteAll();
	}

}
