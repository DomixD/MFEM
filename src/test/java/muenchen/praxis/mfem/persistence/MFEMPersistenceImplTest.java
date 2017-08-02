package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
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
        Iterable<Requirement> asdf = persistence.findAll();
        int count = 0;
        while (asdf.iterator().next() != null){
            count++;
        }
        assertEquals(1, count);
    }

}