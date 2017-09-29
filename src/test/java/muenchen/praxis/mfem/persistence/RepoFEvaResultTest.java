
package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.MfemApplicationTests;
import muenchen.praxis.mfem.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.ArrayList;

import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//@ActiveProfiles("test")
public class RepoFEvaResultTest extends MfemApplicationTests{
    @Autowired
    private RepoFEvaResult repoFEvaResult;
    @Autowired
    private RepoFramework repoFramework;
    @Autowired
    private RepoClassification repoClassification;
    @Autowired
    private RepoMetric repoMetric;
    @Autowired
    private RepoRequirement repoRequirement;

    private FrameworkEvaluation feva;
    private Question question;
    private Answer answer;
    private Metric metric;

    @Before
    public void setUp() {
    }


    @Test
    @WithMockUser(authorities = "READ_ACCESS")
    public void testFindByFrameworkEvaluation() {
        /*
        assertEquals(0, repoFEvaResult.findByFrameworkEvaluation(feva).size());
        FEvaResult fevaResult = new FEvaResult(7, feva, question, answer);
        repoFEvaResult.save(fevaResult);
        assertEquals(1, repoFEvaResult.findByFrameworkEvaluation(feva).size());
        assertEquals(fevaResult.toString(), repoFEvaResult.findByFrameworkEvaluation(feva).get(0).toString());
        */
        System.out.print("Extend: "+repoRequirement.findAll());
    }

}
