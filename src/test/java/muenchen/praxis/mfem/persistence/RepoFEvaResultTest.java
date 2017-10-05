package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.MfemApplicationTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import static org.junit.Assert.*;

public class RepoFEvaResultTest extends MfemApplicationTests{

    @Autowired
    private RepoFEvaResult repoFEvaResult;
    @Autowired
    private RepoFrameworkEvaluation repoFrameworkEvaluation;

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
    public void testFindByFrameworkEvaluation() {
        assertEquals(6, repoFEvaResult.findByFrameworkEvaluation(repoFrameworkEvaluation.findOne(1)).size());
    }

}
