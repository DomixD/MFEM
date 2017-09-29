package muenchen.praxis.mfem;

import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.persistence.RepoRequirement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MfemApplicationTests {

	@Autowired
	RepoRequirement repoRequirement;

	@Before
	public void setUp() {
		repoRequirement.save(new Requirement());
	}

	@Test
	@WithMockUser(authorities = "CREATE_REQ")
	public void database() {
		repoRequirement.save(new Requirement());
	}

	@Test
	@WithMockUser(authorities = "READ_ACCESS")
	public void contextLoads() {
		System.out.print("Basis: "+repoRequirement.findAll());
	}

}
