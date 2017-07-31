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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MFEMPersistenceImplTest {

    @MockBean
    private MFEMPersistenceImpl persistence;

    private static Requirement[] requirements = {
            new Requirement("Anforderung1", Priority.A, new ArrayList<Question>()),
            new Requirement("Anforderung2", Priority.B, new ArrayList<Question>()),
    };
/*
    @BeforeClass
    public static void initialize() {
        IMFEMPersistence persist = new MFEMPersistenceImpl();
        persist.create(requirements[0]);
    }
*/
    @Before
    public void setUp() {
        //persistence = new MFEMPersistenceImpl();
        persistence.create(requirements[0]);
    }

    @Test
    public void testCreate() throws Exception {
        List<Requirement> requirement = persistence.getAll(Requirement.class);
        persistence.create(requirements[1]);
        assertEquals(requirement.size() + 1, persistence.getAll(Requirement.class).size());
    }

    @Test
    public void testUpdate() throws Exception {
    }

    @Test
    public void testDelete() throws Exception {
    }

    @Test
    public void testExist() throws Exception {
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(persistence.get(Requirement.class, requirements[0].getId()).getId(), requirements[0].getId());
    }

    @Test
    public void testGetAll() throws Exception {
        List<Requirement> list = persistence.getAll(Requirement.class);
        assertEquals(1, list.size());
    }

}