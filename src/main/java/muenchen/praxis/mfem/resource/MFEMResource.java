package muenchen.praxis.mfem.resource;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.services.IMFEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@ExposesResourceFor(Requirement.class)
@RequestMapping(value = "/")
@ComponentScan
public class MFEMResource {

	@Autowired
	private IMFEMService service;

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public String test() {
		return service.testPrint();
	}

	@RequestMapping(method =  RequestMethod.GET, value = "/getPrio/{prio}", produces = MediaType.APPLICATION_JSON)
	public List<Requirement> findAllByPrior(@PathVariable("prio")Priority prio) {
		return service.findAllByPrior(prio);
	}

}
