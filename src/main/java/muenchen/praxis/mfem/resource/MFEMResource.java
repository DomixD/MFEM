package muenchen.praxis.mfem.resource;

import javax.ws.rs.core.MediaType;

import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.services.IMFEMService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MFEMResource {
	
	@Autowired
	private IMFEMService service;

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public String test() throws JSONException {
		return service.testPrint();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/req", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Requirement> post(@RequestBody Requirement requirement) {
		service.doPost(requirement);
		return new ResponseEntity<Requirement>(requirement, HttpStatus.OK);
	}
	
}
