package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ActiveProfiles("local")
@Transactional
public class RepoFrameworkEvaluationTest {

    @Autowired
    private RepoFrameworkEvaluation repoFrameworkEvaluation;
    @Autowired
    private RepoFramework repoFramework;
    @Autowired
    private RepoClassification repoClassification;
    @Autowired
    private RepoAnswer repoAnswer;
    @Autowired
    private RepoQuestion repoQuestion;
    @Autowired
    private RepoRequirement repoRequirement;
    @Autowired
    private RepoCategory repoCategory;
    @Autowired
    private RepoMetric repoMetric;


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

    @Before
    public void setUp() {
        repoFrameworkEvaluation.deleteAll();
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
        repoAnswer.save(answer);
        repoMetric.save(metric);
        repoQuestion.save(question);
        repoRequirement.save(requirement);
        repoCategory.save(category);
        repoClassification.save(classification);
        repoFramework.save(framework);
    }

    @Test
    public void testFindByFrameworkInAndClassificationIn() {
        assertNull(repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification));
        feva = new FrameworkEvaluation(1, framework, classification);
        repoFrameworkEvaluation.save(feva);
        assertEquals(feva, repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification));
        Iterator<FrameworkEvaluation> iterFeva = repoFrameworkEvaluation.findAll().iterator();
        iterFeva.next();
        assertFalse(iterFeva.hasNext());
    }

    @Test
    public void testFindByClassification() {
        assertEquals(0, repoFrameworkEvaluation.findByClassification(classification).size());
        feva = new FrameworkEvaluation(1, framework, classification);
        repoFrameworkEvaluation.save(feva);
        List<FrameworkEvaluation> expect = new ArrayList<>();
        expect.add(feva);
        assertEquals(expect.size(), repoFrameworkEvaluation.findByClassification(classification).size());
        assertEquals(expect, repoFrameworkEvaluation.findByClassification(classification));
    }
}