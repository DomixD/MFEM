package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.*;
import muenchen.praxis.mfem.persistence.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MFEMServiceImplTest {
    @InjectMocks
    private static IMFEMService service = new MFEMServiceImpl();

    @MockBean
    private RepoCategory repoCategory;
    @MockBean
    private RepoFramework repoFramework;
    @MockBean
    private RepoClassification repoClassification;
    @MockBean
    private RepoFrameworkEvaluation repoFrameworkEvaluation;
    @MockBean
    private RepoFEvaResult repoFEvaResult;

    private Category category;
    private List<Category> catList = new ArrayList<>();
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
    private List<FrameworkEvaluation> fevaList = new ArrayList<>();
    private FEvaResult fevaResult;
    private List<FEvaResult> fevaResultList = new ArrayList<>();
    private Iterable<Category> cat;

/*
    public MFEMServiceImplTest() {
        service = new MFEMServiceImpl();
        answer = new Answer(1, "Antwort", 1.0, metric);
        answerList.add(answer);
        metric = new Metric(1, "Metrik", answerList);
        question = new Question(1, "Frage1", metric, requirement);
        questList.add(question);
        requirement = new Requirement(1, "Anforderung1", questList, classification, category, Priority.A);
        reqList.add(requirement);
        category = new Category(1, "Kategorie1", reqList);
        catList.add(category);
        category = new Category(2, "Kategorie2", reqList);
        catList.add(category);
        classification = new Classification(1, "Klassi", "Beschreibung", reqList);
        framework = new Framework(1, "Framework1", "Beschreibung");
        feva = new FrameworkEvaluation(1, framework, classification);
        fevaList.add(feva);
        fevaResult = new FEvaResult(1, feva, question, answer);
        fevaResultList.add(fevaResult);
        cat = catList;
    }*/

    @Before
    public void setup () {
        answer = new Answer(1, "Antwort", 1.0, metric);
        answerList.add(answer);
        metric = new Metric(1, "Metrik", answerList);
        question = new Question(1, "Frage1", metric, requirement);
        questList.add(question);
        requirement = new Requirement(1, "Anforderung1", questList, classification, category, Priority.A);
        reqList.add(requirement);
        category = new Category(1, "Kategorie1", reqList);
        catList.add(category);
        category = new Category(2, "Kategorie2", reqList);
        catList.add(category);
        classification = new Classification(1, "Klassi", "Beschreibung", reqList);
        framework = new Framework(1, "Framework1", "Beschreibung");
        feva = new FrameworkEvaluation(1, framework, classification);
        fevaList.add(feva);
        fevaResult = new FEvaResult(1, feva, question, answer);
        fevaResultList.add(fevaResult);
        cat = catList;
    }

    @Test
    public void testGetResult() throws Exception {
        when(repoCategory.findAll()).thenReturn(cat);
        when(repoFramework.findOne(1)).thenReturn(framework);
        when(repoClassification.findOne(1)).thenReturn(classification);
        when(repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification)).thenReturn(feva);
        when(repoFEvaResult.findByFrameworkEvaluation(feva)).thenReturn(fevaResultList);
        System.out.println(category);
        System.out.println(catList);
        System.out.println(cat);
        System.out.println(service.getResult(1,1));
        List<Double> result = new ArrayList<>();
        result.add(0.5);
        assertEquals(result, service.getResult(1, 1));
    }

    @Test
    public void testGetFrames() {
        when(repoClassification.findOne(1)).thenReturn(classification);
        when(repoFrameworkEvaluation.findByClassification(classification)).thenReturn(fevaList);
        List<Integer> result = new ArrayList<>();
        result.add(1);
        assertEquals(result, service.getFrames(1));
    }
}