package muenchen.praxis.mfem.resource;

import muenchen.praxis.mfem.services.IMFEMService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
//@WebMvcTest(MFEMResource.class)
@SpringBootTest
@ActiveProfiles("test")
public class MFEMResourceTest {

    @Autowired
    private MFEMResource mfemResource;
    @Autowired
    private RestTemplate restTemplate;
    private MockRestServiceServer server;
    private List<Double> resultList = new ArrayList<>(Arrays.asList(1.0, 0.5, 0.0, 1.0, 0.5, 0.0));

    @Before
    public void setup() {
        this.server = MockRestServiceServer.createServer(restTemplate);
    }


    @Test
    @WithMockUser(authorities = "USER")
    public void testGetResult() {
        this.server.expect(requestTo("http://localhost:8080/getRes/1/1"));
        ResponseEntity<?> result = this.mfemResource.getResult(1,1);
        assertEquals(HttpStatus.OK,result.getStatusCode());
        assertEquals(resultList,result.getBody());
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testGetFrames() {
        this.server.expect(requestTo("http://localhost:8080/getFrames/1"));
        ResponseEntity<?> result = this.mfemResource.getFrames(1);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(new ArrayList<>(Arrays.asList(1,2)), result.getBody());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testAuthenticateAdmin() {
        this.server.expect(requestTo("http://localhost:8080/authenticate"));
        ResponseEntity<?> result = this.mfemResource.authenticate();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody());
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void testAuthenticateUser() {
        this.server.expect(requestTo("http://localhost:8080/authenticate"));
        ResponseEntity<?> result = this.mfemResource.authenticate();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(0, result.getBody());
    }

//    @Test
//    @WithMockUser(authorities = "abc")
//    public void testInvalidAuthenticate() {
//        this.server.expect(requestTo("http://localhost:8080/authenticate"));
//        ResponseEntity<?> result = this.mfemResource.authenticate();
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//        assertEquals(1, result.getBody());
//    }

}
