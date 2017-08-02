package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Priority;
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
import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class RepoRequirementTest {

    @Autowired
    private RepoRequirement persistence;

    private static Requirement[] requirements = {
            new Requirement("Anforderung1", Priority.A, new ArrayList<Question>()),
            new Requirement("Anforderung2", Priority.B, new ArrayList<Question>()),
    };

    @Before
    public void setUp() {
        persistence.save(requirements[0]);
        persistence.save(requirements[1]);
    }

    @Test
    public void testSave() {
        Iterable<Requirement> iter = persistence.findAll();
        Iterator<Requirement> it = iter.iterator();
        int count = 0;
        while (it.hasNext()){
            count++;
            it.next();
        }
        assertEquals(2, count);
    }

    @Test
    public void testFindAll() {
        Iterable<Requirement> it = persistence.findAll();
        for (Requirement r : it) {
            System.out.println("FindAll: " + r.toString());
        }
    }

    @Test
    public void testFindOne() {
        Iterable<Requirement> it = persistence.findAll();
        int id = it.iterator().next().getId();
        System.out.println("FindOne: " + persistence.findOne(id).toString());
    }

}