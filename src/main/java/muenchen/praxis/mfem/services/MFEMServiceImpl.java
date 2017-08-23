package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.persistence.RepoQuestion;
import muenchen.praxis.mfem.persistence.RepoRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MFEMServiceImpl implements IMFEMService{

	@Autowired
	RepoRequirement persistenceReq;
	@Autowired
	RepoQuestion persistenceQuest;

	@Override
	public String testPrint() {
		System.out.println("get hat geklappt :)!");
		return "Irgendwas";
	}

	/*@Override
	public List<Requirement> findAllByPrior (Priority prior) {
		return persistenceReq.findAllByPrior(prior);
	}*/


}
