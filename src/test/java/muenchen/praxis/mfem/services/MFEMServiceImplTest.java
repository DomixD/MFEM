package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.RestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestConfig.class)
@ActiveProfiles("test")
public class MFEMServiceImplTest {

    @Autowired
    private IMFEMService service;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer server;

    private List<Double> expectedList = new ArrayList<>(Arrays.asList(1.0, 0.5, 0.0, 1.0, 0.5, 0.0));

    @Before
    public void setUp() {
        this.server = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetResult() {
        this.server.expect(requestTo("http://localhost:8080/getRes/1/1")).andRespond(withSuccess());
        List<Double> result = this.service.getResult(1,1);
        assertThat(result, is(equalTo(expectedList)));
    }

    @Test
    public void testGetFrames() {
        this.server.expect(requestTo("http://localhost:8080/getFrames/1")).andRespond(withSuccess());
        List<Integer> result = this.service.getFrames(1);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testCheckUserAdmin() {
        this.server.expect(requestTo("http://localhost:8080/authenticate")).andRespond(withSuccess());
        Integer result = this.service.checkUser();
        assertThat(result, is(equalTo(1)));
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testCheckUserUser() {
        this.server.expect(requestTo("http://localhost:8080/authenticate")).andRespond(withSuccess());
        Integer result = this.service.checkUser();
        assertThat(result, is(equalTo(0)));
    }

}