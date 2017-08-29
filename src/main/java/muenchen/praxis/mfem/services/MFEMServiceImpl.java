package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Question;
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
	public void addToQuestList(String content) {
		//Über content auf Requirement kommen
		//List<Question> list = requirement.getQuestionList();
		//list.add(question);
		//zurück speichern?
	}

}
