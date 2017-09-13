package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.*;
import muenchen.praxis.mfem.persistence.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class MFEMServiceImplTest {

    @MockBean
    private IMFEMService service;

    @Autowired
    private RepoFrameworkEvaluation frameworkEvaluation;
    @Autowired
    private RepoFramework repoFramework;
    @Autowired
    private RepoFEvaResult repoFEvaResult;

    @Before
    public void setUp() {
        Framework framework = new Framework(1, "Framework1", "Beschreibung1");
        repoFramework.save(framework);

    }

    @Test
    public void getResult() throws Exception {


        List<Double> exp = new ArrayList<>();
        exp.add(0.0);
        assertEquals(exp, service.getResult(1,1));
    }

}