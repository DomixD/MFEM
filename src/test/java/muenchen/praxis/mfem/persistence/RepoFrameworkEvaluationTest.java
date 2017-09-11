package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Framework;
import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class RepoFrameworkEvaluationTest {

    @Autowired
    private RepoFrameworkEvaluation repoFrameworkEvaluation;

    private static Framework[] frameworks = {
            new Framework(1,"Framework1", "Beschreibung1"),
            new Framework(2, "Framwork2", "Beschreibung2"),
    };

    private static List<Question> questionList = new ArrayList<>();

    private static Requirement[] requirements = {
            new Requirement("Anforderung1", questionList)
    };

    @Before
    public void setUp() {
//       questionList.add(new Question("Frage1"));
//       questionList.add(new Question("Frage2"));
//     //  repoFrameworkEvaluation.save();
    }

    @Test
    public void testfindByFrameworkInAndRequirementIn() throws Exception {
        Iterable<Framework> iterFrame;
        Iterable<Requirement> iterReq;
    }

    @Test
    public void testfindByFrameworkInAndClassificationIn() throws Exception {

    }

}