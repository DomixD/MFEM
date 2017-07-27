package muenchen.praxis.mfem.resource;

import muenchen.praxis.mfem.services.IMFEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MFEMResource {
	
	@Autowired
	private IMFEMService service;

//	public MFEMResource() {
//		service = new MFEMServiceImpl();
//	}

	@GetMapping("/test")
	public String test() {
		return service.testPrint();
	}
}
