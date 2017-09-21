package muenchen.praxis.mfem;

import muenchen.praxis.mfem.services.IMFEMService;
import muenchen.praxis.mfem.services.MFEMServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@TestConfiguration
public class RestConfig {

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

}

