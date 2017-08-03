package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;
import org.springframework.http.HttpStatus;

public interface IMFEMService {
	
	public String testPrint();
	public String doPost(Requirement r);
	public HttpStatus saveReq (Requirement r);
	public String getReq(int id);
	public HttpStatus saveQuest (Question q);
	public String getQuest(int id);

}
