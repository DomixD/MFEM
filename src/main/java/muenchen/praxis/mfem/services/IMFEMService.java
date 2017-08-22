package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface IMFEMService {
	
	public String testPrint();
	public String doPost(Requirement r);
	public HttpStatus saveReq (Requirement r);
	public String getReq(int id);
	public List<Requirement> findAllByPrior(Priority prior);
	public List allRequirements();
	public HttpStatus saveQuest (Question q);
	public String getQuest(int id);
	public List allQuestions();

}
