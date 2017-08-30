package muenchen.praxis.mfem.resource;

import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.services.IMFEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@ExposesResourceFor(Requirement.class)
@RequestMapping(value = "/")
@ComponentScan
public class MFEMResource {

	@Autowired
	private IMFEMService service;

	/**
	 * Question zu questionList in Requirement hinzufügen
	 * @param requirement
	 */
	@RequestMapping(value = "/questToReq", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public void questToReq(@RequestBody Requirement requirement) {
		String content = requirement.getContent();
		service.addToQuestList(content);
		System.out.print("Funktioniert");
	}

}