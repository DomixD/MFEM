package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.MfemApplicationTests;
import muenchen.praxis.mfem.entities.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class RepoUserTest extends MfemApplicationTests {

    @Autowired
    private RepoUser repoUser;

    @Before
    public void setUp() {
        super.setUpDatabase();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("READ_ACCESS");
        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @After
    public void cleanUp() {
        super.cleanUpDatabase();
    }

    @Test
    public void testFindByUsername() throws Exception {
        User user = new User(1, "user", "user", new ArrayList<>());
        assertEquals(user,repoUser.findByUsername("user"));
    }

}