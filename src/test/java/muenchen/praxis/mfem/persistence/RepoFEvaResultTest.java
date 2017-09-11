package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepoFEvaResultTest {


    @Autowired
    private RepoQuestion repoQuestion;
    @Autowired
    private RepoAnswer repoAnswer;
    @Autowired
    private RepoFrameworkEvaluation repoFrameworkEvaluation;
    @Autowired
    private RepoFEvaResult repoFEvaResult;
    @Autowired
    private RepoMetric repoMetric;
    @Autowired
    private RepoFramework repoFramework;
    @Autowired
    private RepoClassification repoClassification;


    @Before
    public void setUp() {

        List<Answer> answerList = new ArrayList<>();

        //Metric
        Metric metric = new Metric(1,"Test-Metrik1",answerList);
        repoMetric.save(metric);

        //Answer
        Answer answer = new Answer(1,"Test-Antwort1",1.0, metric);
        repoAnswer.save(answer);
        answerList.add(answer);

        //Question
        Question question = new Question(1,"Test-Frage1",metric,null);
        repoQuestion.save(question);

        Framework framework = new Framework(1,"Framework1","Beschreibung");
        repoFramework.save(framework);

        Classification classification = new Classification();
        repoClassification.save(classification);

        //FrameworkEvaluation
        //FrameworkEvaluation feva = new FrameworkEvaluation(1,framework,classification);
        //repoFrameworkEvaluation.save(feva);

    }

    @Test
    public void test() {
        System.out.println("##################################################");

        System.out.println(repoQuestion.findOne(1));
        System.out.println(repoMetric.findOne(1));
        System.out.println(repoAnswer.findOne(1));
        System.out.println(repoFrameworkEvaluation.findOne(1));
    }

    @Test
    public void testfindByFrameworkEvaluation() {
//        assertNull(repoFEvaResult.findAll());
//        assertNull(repoFEvaResult.findByFrameworkEvaluation(feva));
//        repoFEvaResult.save(fevaResult);
//        assertEquals(feva, repoFEvaResult.findByFrameworkEvaluation(feva));
    }

}