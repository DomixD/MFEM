package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.Requirement;

import muenchen.praxis.mfem.persistence.RepoRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MFEMServiceImpl implements IMFEMService{

	@Autowired
	RepoRequirement persistence;

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
	public void saveReq(Requirement requirement) {
		persistence.save(requirement);
		System.out.println("saved");
	}

	@Override
	public String getReq(int id) {
		return persistence.findOne(id).toString();
	}

}
