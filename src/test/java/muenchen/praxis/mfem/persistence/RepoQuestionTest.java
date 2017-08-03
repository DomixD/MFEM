package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class RepoQuestionTest {

    @Autowired
    private RepoQuestion persistence;

    private static Question[] questions = {
            new Question("Frage1"),
            new Question("Frage2"),
    };

    @Before
    public void setUp() {
        persistence.save(questions[0]);
        persistence.save(questions[1]);
    }

    @Test
    public void testSave() {
        Iterable<Question> iter = persistence.findAll();
        Iterator<Question> it = iter.iterator();
        int count = 0;
        while (it.hasNext()){
            count++;
            it.next();
        }
        assertEquals(2, count);
    }

    @Test
    public void testFindAll() {
        Iterable<Question> it = persistence.findAll();
        for (Question r : it) {
            System.out.println("FindAll: " + r.toString());
        }
    }

    @Test
    public void testFindOne() {
        Iterable<Question> it = persistence.findAll();
        int id = it.iterator().next().getId();
        System.out.println("FindOne: " + persistence.findOne(id).toString());
    }
}
