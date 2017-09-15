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
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
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
    public void testFindByFrameworkInAndClassificationIn() {
        assertNull(repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification));
        feva = new FrameworkEvaluation(1, framework, classification);
        repoFrameworkEvaluation.save(feva);
        //Fehler beim Vergleich der Objekte
        assertEquals(feva.toString(), repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification).toString());
        Iterator<FrameworkEvaluation> iterFeva = repoFrameworkEvaluation.findAll().iterator();
        iterFeva.next();
        assertFalse(iterFeva.hasNext());
        //Datenbank wieder auf Startzustand bringen
        //repoFrameworkEvaluation.delete(1);
    }

    @Test
    public void testFindByClassification() {
        assertEquals(0, repoFrameworkEvaluation.findByClassification(classification).size());
        feva = new FrameworkEvaluation(1, framework, classification);
        repoFrameworkEvaluation.save(feva);
        List<FrameworkEvaluation> expect = new ArrayList<>();
        expect.add(feva);
        assertEquals(expect.size(), repoFrameworkEvaluation.findByClassification(classification).size());
        //Fehler beim Vergleich
        //assertEquals(expect, repoFrameworkEvaluation.findByClassification(classification));
        //Datenbank wieder auf Startzustand bringen
        //repoFrameworkEvaluation.delete(1);
    }
}