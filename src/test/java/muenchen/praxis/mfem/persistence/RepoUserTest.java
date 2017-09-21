package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepoUserTest {

    @Autowired
    private RepoUser repoUser;

    private User user;

    @Before
    public void setup() {
        user = new User(1,"testuser","test123","ADMIN");
    }

    @Test
    public void testFindByUsername() throws Exception {
        assertNull(repoUser.findByUsername("test"));
        repoUser.save(user);
        assertEquals(user,repoUser.findByUsername("testuser"));
        Iterator<User> iterFeva = repoUser.findAll().iterator();
        iterFeva.next();
        assertFalse(iterFeva.hasNext());
    }

}