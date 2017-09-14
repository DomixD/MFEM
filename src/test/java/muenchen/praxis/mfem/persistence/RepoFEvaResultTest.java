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
@ActiveProfiles("local")
public class RepoFEvaResultTest {
/*
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

    private Framework framework = new Framework(1, "Framework1", "Beschreibung1");
    private Classification classification;
    private FrameworkEvaluation feva;
    private Question question;
    private Answer answer;
    private FEvaResult fevaResult;

    @Before
    public void setUp() {
        repoFramework.save(framework);
        classification = repoClassification.findOne(1);
        feva = new FrameworkEvaluation(1, framework, classification);
        repoFrameworkEvaluation.save(feva);

        question = repoQuestion.findOne(1);
        answer = repoAnswer.findOne(1);
    }


    @Test
    public void testfindByFrameworkEvaluation() {
        assertEquals(0, repoFEvaResult.findByFrameworkEvaluation(feva).size());
        fevaResult = new FEvaResult(1, feva, question, answer);
        repoFEvaResult.save(fevaResult);
        assertEquals(1, repoFEvaResult.findByFrameworkEvaluation(feva).size());
        assertEquals(fevaResult.toString(), repoFEvaResult.findByFrameworkEvaluation(feva).get(0).toString());
    }
*/
}