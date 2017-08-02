package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MFEMPersistenceImplTest {

    @Autowired
    private IMFEMPersistence persistence;

    private static Requirement[] requirements = {
            new Requirement("Anforderung1", Priority.A, new ArrayList<Question>()),
            new Requirement("Anforderung2", Priority.B, new ArrayList<Question>()),
    };

    @Test
    public void testSave() {
        persistence.save(requirements[0]);
        Iterable<Requirement> iter = persistence.findAll();
        Iterator<Requirement> it = iter.iterator();
        int count = 0;
        while (it.hasNext()){
            count++;
            it.next();
        }
        assertEquals(1, count);
    }

}