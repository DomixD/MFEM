package muenchen.praxis.mfem;

import muenchen.praxis.mfem.services.IMFEMService;
import muenchen.praxis.mfem.services.MFEMServiceImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MfemApplication {
	
	private IMFEMService service;
	
	public MfemApplication() {
		service = new MFEMServiceImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(MfemApplication.class, args);
	}
	
	@GetMapping("/test")
	public void test(){
		service.testPrint();
	}
}
