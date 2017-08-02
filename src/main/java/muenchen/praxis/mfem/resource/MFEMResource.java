package muenchen.praxis.mfem.resource;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.services.IMFEMService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(method = RequestMethod.POST, value = "/save", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Requirement> saveReq(@RequestBody Requirement requirement) {
		service.saveReq(requirement);
		return new ResponseEntity<Requirement>(requirement, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/read/{id}", produces = MediaType.APPLICATION_JSON)
	public String getReq(@PathVariable("id") int id) {
		return service.getReq(id);
	}
	
}
