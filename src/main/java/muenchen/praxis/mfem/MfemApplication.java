package muenchen.praxis.mfem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "muenchen.praxis.mfem")
public class MfemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfemApplication.class, args);
	}

}
