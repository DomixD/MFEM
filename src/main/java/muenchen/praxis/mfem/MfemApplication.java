package muenchen.praxis.mfem;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EntityScan(basePackages = "muenchen.praxis.mfem")
@ComponentScan("muenchen.praxis.mfem")
public class MfemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfemApplication.class, args);
	}

	@Configuration
	public class WebConfiguration {
		@Bean
		ServletRegistrationBean h2servletRegistration(){
			ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
			registrationBean.addUrlMappings("/console/*");
			return registrationBean;
		}
	}

}
