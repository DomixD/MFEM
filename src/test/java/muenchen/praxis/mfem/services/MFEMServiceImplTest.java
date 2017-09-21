package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.RestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    @Before
    public void setUp() {
        this.server = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testGetResult() {
        this.server.expect(requestTo("http://localhost:8080/getRes/1/1")).andRespond(withSuccess());
        List<Double> result = this.service.getResult(1,1);
        List<Double> expected = new ArrayList<>();
        expected.add(1.0);
        expected.add(0.5);
        expected.add(0.0);
        expected.add(1.0);
        expected.add(0.5);
        expected.add(0.0);
        assertThat(result, is(equalTo(expected)));
    }

}