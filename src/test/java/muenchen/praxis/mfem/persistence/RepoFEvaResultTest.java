package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepoFEvaResultTest {
    @Autowired
    private RepoFEvaResult repoFEvaResult;
    @Autowired
    private RepoFrameworkEvaluation repoFrameworkEvaluation;
    @Autowired
    private RepoFramework repoFramework;
    @Autowired
    private RepoClassification repoClassification;
    @Autowired
    private RepoQuestion repoQuestion;
    @Autowired
    private RepoAnswer repoAnswer;
    @Autowired
    private RepoCategory repoCategory;
    @Autowired
    private RepoMetric repoMetric;
    @Autowired
    private RepoRequirement repoRequirement;

    private Category category;
    private Requirement requirement;
    private List<Requirement> reqList = new ArrayList<>();
    private Question question;
    private Metric metric;
    private List<Question> questList = new ArrayList<>();
    private List<Answer> answerList = new ArrayList<>();
    private Answer answer;
    private Classification classification;
    private Framework framework;
    private FrameworkEvaluation feva;
    private FEvaResult fevaResult;

    @Before
    public void setUp() {
        repoFEvaResult.deleteAll();
        answer = new Answer(1, "Antwort", 1.0, metric);
        answerList.add(answer);
        metric = new Metric(1, "Metrik", answerList);
        question = new Question(1, "Frage1", metric, requirement);
        questList.add(question);
        requirement = new Requirement(1, "Anforderung1", questList, classification, category, Priority.A);
        reqList.add(requirement);
        category = new Category(1, "Kategorie1", reqList);
        repoCategory.save(category);
        category = new Category(2, "Kategorie2", reqList);
        classification = new Classification(1, "Klassi", "Beschreibung", reqList);
        framework = new Framework(1, "Framework1", "Beschreibung");
        feva = new FrameworkEvaluation(1, framework, classification);
        repoAnswer.save(answer);
        repoMetric.save(metric);
        repoQuestion.save(question);
        repoRequirement.save(requirement);
        repoCategory.save(category);
        repoClassification.save(classification);
        repoFramework.save(framework);
        repoFrameworkEvaluation.save(feva);
    }


    @Test
    public void testfindByFrameworkEvaluation() {
        assertEquals(0, repoFEvaResult.findByFrameworkEvaluation(feva).size());
        fevaResult = new FEvaResult(1, feva, question, answer);
        repoFEvaResult.save(fevaResult);
        assertEquals(1, repoFEvaResult.findByFrameworkEvaluation(feva).size());
        assertEquals(fevaResult.toString(), repoFEvaResult.findByFrameworkEvaluation(feva).get(0).toString());
    }

}