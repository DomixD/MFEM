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
@Transactional
@ActiveProfiles("local")
public class RepoFrameworkEvaluationTest {

    @Autowired
    private RepoFrameworkEvaluation repoFrameworkEvaluation;
    @Autowired
    private RepoFramework repoFramework;
    @Autowired
    private RepoClassification repoClassification;


    private Framework framework = new Framework(1, "Framework1", "Beschreibung1");
    private Classification classification;
    private FrameworkEvaluation feva;

    @Before
    public void setUp() {
        repoFramework.save(framework);
        classification = repoClassification.findOne(1);
    }

    @Test
    public void testfindByFrameworkInAndClassificationIn() {
        assertNull(repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification));
        feva = new FrameworkEvaluation(1, framework, classification);
        repoFrameworkEvaluation.save(feva);
        assertEquals(feva, repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification));
        Iterator<FrameworkEvaluation> iterFeva = repoFrameworkEvaluation.findAll().iterator();
        iterFeva.next();
        assertFalse(iterFeva.hasNext());
    }
}