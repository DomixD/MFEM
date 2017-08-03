package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;

public interface IMFEMService {
	
	public String testPrint();
	public String doPost(Requirement r);
	public void saveReq (Requirement r);
	public String getReq(int id);
	public void saveQuest (Question q);
	public String getQuest(int id);

}
