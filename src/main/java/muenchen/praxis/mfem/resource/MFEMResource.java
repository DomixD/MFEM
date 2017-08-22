package muenchen.praxis.mfem.resource;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.hateoas.RequirementResource;
import muenchen.praxis.mfem.hateoas.RequirementAssembler;
import muenchen.praxis.mfem.services.IMFEMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@ExposesResourceFor(Requirement.class)
@RequestMapping(value = "/")
@ComponentScan
public class MFEMResource {

	@Autowired
	private IMFEMService service;

	private RequirementAssembler requirementAssembler = new RequirementAssembler();

//	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
//	public String test() throws JSONException {
//		return service.testPrint();
//	}
/*
	@RequestMapping(method = RequestMethod.POST, value = "/req", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<Requirement> post(@RequestBody Requirement requirement) {
		service.doPost(requirement);
		return new ResponseEntity<Requirement>(requirement, HttpStatus.OK);
	}
*/
	@RequestMapping(method = RequestMethod.POST, value = "/saveR", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
	public ResponseEntity<RequirementResource> saveReq(@RequestBody Requirement requirement) {
		service.saveReq(requirement);
		RequirementResource requirementResource = requirementAssembler.toResource(requirement);
		return new ResponseEntity<RequirementResource>(requirementResource, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/readR/{id}", produces = MediaType.APPLICATION_JSON)
	public ResponseEntity<RequirementResource> getReq(@PathVariable("id") int id) {
		Requirement requirement = service.getReq(id);
		RequirementResource requirementResource = requirementAssembler.toResource(requirement);
		return new ResponseEntity<RequirementResource>(requirementResource, HttpStatus.OK);
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

	@RequestMapping(method = RequestMethod.GET, value = "/allReq", produces = MediaType.APPLICATION_JSON)
    public List<ResponseEntity<RequirementResource>> getAllRequirements() {
		List<Requirement> allRequirements = service.allRequirements();
		List<ResponseEntity<RequirementResource>> result = new ArrayList<>();
		for (Requirement requirement : allRequirements) {
			RequirementResource requirementResource = requirementAssembler.toResource(requirement);
			ResponseEntity<RequirementResource> response = new ResponseEntity<RequirementResource>(requirementResource, HttpStatus.OK);
			result.add(response);
		}
		return result;
	}
	/*
	@RequestMapping(method = RequestMethod.GET, value = "/allQuest", produces = MediaType.APPLICATION_JSON)
	public List<Question> getAllQuestions() {
		List<Question> allQuest = service.allQuestions();
		for (Question question : allQuest) {
			Link selfLink = linkTo(MFEMResource.class).slash(question.getId()).withSelfRel();
			question.add(selfLink);
		}
		return allQuest;
	}
	*/
}
