package muenchen.praxis.mfem.resource;

import muenchen.praxis.mfem.services.IMFEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping(value = "/")
@ComponentScan
public class MFEMResource {

	@Autowired
	private IMFEMService service;

	@RequestMapping(method = RequestMethod.GET, value = "/getRes/{frameId}/{classiId}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getResult (@PathVariable("frameId") int frameId, @PathVariable("classiId") int classiId) {
		return new ResponseEntity<List<Double>>((service.getResult(frameId, classiId)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getFrames/{classiId}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> getFrames (@PathVariable("classiId") int classiId) {
		return new ResponseEntity<List<Integer>>(service.getFrames(classiId),HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value="/authenticate", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<?> authenticate() {
		return new ResponseEntity<Integer>(service.checkUser(), HttpStatus.OK);
	}

}