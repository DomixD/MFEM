package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.*;
import org.junit.After;
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
@Transactional
@ActiveProfiles("test")
public class RepoFrameworkEvaluationTest {

    @Autowired
    private RepoFrameworkEvaluation repoFrameworkEvaluation;
    @Autowired
    private RepoClassification repoClassification;


    private Framework framework;

    @Before
    public void setUp() {
        framework = new Framework(2, "TestBeschreibung", "TestFramework");
    }

    @After
    public void cleanUp() {
        repoFrameworkEvaluation.delete(2);
    }

    @Test
    public void testFindByFrameworkInAndClassificationIn() {
        Classification classification = repoClassification.findOne(1);
        assertNull(repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification));
        FrameworkEvaluation feva = new FrameworkEvaluation(2, framework, classification);
        repoFrameworkEvaluation.save(feva);
        assertEquals(feva, repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification));
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