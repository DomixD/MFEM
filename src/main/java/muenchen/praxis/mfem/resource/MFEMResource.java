package muenchen.praxis.mfem.resource;

import javax.ws.rs.core.MediaType;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.services.IMFEMService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@RequestMapping(method =  RequestMethod.GET, value = "/getPrio/{prio}", produces = MediaType.APPLICATION_JSON)
	public List<Requirement> findAllByPrior(@PathVariable("prio")Priority prio) {
		return service.findAllByPrior(prio);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/saveQ", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Question> saveQuest(@RequestBody Question question) {
		service.saveQuest(question);
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/readQ/{id}", produces = MediaType.APPLICATION_JSON)
	public String getQuest(@PathVariable("id") int id) {
		return service.getQuest(id);
	}
	
}
