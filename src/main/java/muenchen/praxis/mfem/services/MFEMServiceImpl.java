package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.entities.Requirement;

import muenchen.praxis.mfem.persistence.RepoQuestion;
import muenchen.praxis.mfem.persistence.RepoRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@Override
	public String doPost(Requirement r) {
		System.out.println("post hat geklappt :)!");
		System.out.println(r.getContent());
		System.out.println(r.getPrior());
		System.out.println(r.getQuestionList());
		System.out.println(r.toString());
		return r.toString();
	}

	@Override
	public HttpStatus saveReq(Requirement requirement) {
		persistenceReq.save(requirement);
		System.out.println("saved");
		return HttpStatus.OK;
	}

	@Override
	public String getReq(int id) {
		return persistenceReq.findOne(id).toString();
	}

	@Override
	public List<Requirement> findAllByPrior (Priority prior) {
		return persistenceReq.findAllByPrior(prior);
	}

	@Override
	public HttpStatus saveQuest(Question q) {
		persistenceQuest.save(q);
		System.out.println("saved");
		return HttpStatus.OK;
	}

	@Override
	public String getQuest(int id) {
		return persistenceQuest.findOne(id).toString();
	}

}
