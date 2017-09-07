package muenchen.praxis.mfem.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.services.IMFEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@ExposesResourceFor(Requirement.class)
@RequestMapping(value = "/")
@ComponentScan
public class MFEMResource {

	@Autowired
	private IMFEMService service;

	@RequestMapping(method = RequestMethod.GET, value = "/getEvaID/{frameId}/{reqId}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getEvaID (@PathVariable("frameId") int frameId, @PathVariable("reqId") int reqId) {
		return new ResponseEntity<Integer>(new Integer(service.getEvaID(frameId, reqId)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getResult/{frameId}/{classiId}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getResult (@PathVariable("frameId") int frameId, @PathVariable("classiId") int classiId) {
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}