package muenchen.praxis.mfem.resource;

import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.services.IMFEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

@RestController
@ExposesResourceFor(Requirement.class)
@RequestMapping(value = "/")
@ComponentScan
public class MFEMResource {

	@Autowired
	private IMFEMService service;

	@RequestMapping(method = RequestMethod.GET, value = "/getRes/{frameId}/{classiId}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getResult (@PathVariable("frameId") int frameId, @PathVariable("classiId") int classiId) {
		return new ResponseEntity<Double>(new Double(service.getResult(frameId, classiId)), HttpStatus.OK);
	}

}