package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.*;
import org.junit.BeforeClass;
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
@ActiveProfiles("test")
public class RepoFrameworkEvaluationTest {

    @Autowired
    private RepoFrameworkEvaluation repoFrameworkEvaluation;
    @Autowired
    private RepoClassification repoClassification;


    private static Framework framework;

    @BeforeClass
    public static void setUp() {
        framework = new Framework(2, "TestBeschreibung", "TestFramework");
    }

    @Test
    public void testFindByFrameworkInAndClassificationIn() {
        Classification classification = repoClassification.findOne(1);
        assertNull(repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification));
        FrameworkEvaluation feva = new FrameworkEvaluation(2, framework, classification);
        repoFrameworkEvaluation.save(feva);
        assertEquals(feva.toString(), repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification).toString());
    }

    @Test
    public void testFindByClassification() {
        Classification classification = new Classification(2, "TestKlassifikation", "TestBeschreibung", new ArrayList<>());
        assertEquals(0, repoFrameworkEvaluation.findByClassification(classification).size());
        FrameworkEvaluation feva = new FrameworkEvaluation(2, framework, classification);
        repoFrameworkEvaluation.save(feva);
        List<FrameworkEvaluation> expect = new ArrayList<>();
        expect.add(feva);
        assertEquals(expect.size(), repoFrameworkEvaluation.findByClassification(classification).size());
        assertEquals(expect, repoFrameworkEvaluation.findByClassification(classification));
    }
}