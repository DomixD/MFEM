package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.MfemApplicationTests;
import muenchen.praxis.mfem.entities.Classification;
import muenchen.praxis.mfem.entities.Framework;
import muenchen.praxis.mfem.entities.FrameworkEvaluation;
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


public class RepoFrameworkEvaluationTest extends MfemApplicationTests{

    @Autowired
    private RepoFrameworkEvaluation repoFrameworkEvaluation;
    @Autowired
    private RepoFramework repoFramework;
    @Autowired
    private RepoClassification repoClassification;

    private Classification classification;

    @Before
    public void setUp() {
        super.setUpDatabase();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("READ_ACCESS");
        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "test", authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        classification = repoClassification.findOne(1);
    }

    @After
    public void cleanUp() {
        super.cleanUpDatabase();
    }

    @Test
    public void testFindByFrameworkInAndClassificationIn() {
        Framework framework = repoFramework.findOne(1);
        FrameworkEvaluation frameworkEvaluation = new FrameworkEvaluation(1, framework, classification);
        assertEquals(frameworkEvaluation.getId(), repoFrameworkEvaluation.findByFrameworkInAndClassificationIn(framework, classification).getId());
    }

    @Test
    public void testFindByClassification() {
        assertEquals(1, repoFrameworkEvaluation.findByClassification(classification).size());
    }
}
