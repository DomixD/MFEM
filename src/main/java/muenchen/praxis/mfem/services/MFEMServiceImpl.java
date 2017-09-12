package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.*;
import muenchen.praxis.mfem.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MFEMServiceImpl implements IMFEMService{

	@Autowired
	RepoFrameworkEvaluation frameworkEvaluation;
	@Autowired
	RepoFramework repoFramework;
	@Autowired
	RepoClassification repoClassification;
	@Autowired
	RepoFEvaResult repoFEvaResult;

	@Override
	public double getResult(int frameID, int classiID) {
		double soll = 0.0;
		double ist = 0.0;
		Framework frame = repoFramework.findOne(frameID);
		Classification classi = repoClassification.findOne(classiID);
		FrameworkEvaluation feva = frameworkEvaluation.findByFrameworkInAndClassificationIn(frame, classi);
		List<Requirement> reqs = classi.getRequirementList();
		List<FEvaResult> list = repoFEvaResult.findByFrameworkEvaluation(feva);

		for(int i = 0; i <classi.getRequirementList().size();i++) {
			Requirement r = reqs.get(i);
			List<Question> questionList = r.getQuestionList();
			double sollQuest = r.getPriority().getValue()/questionList.size();
			soll += r.getPriority().getValue();
				for(Question q : questionList) {
					for(FEvaResult res : list) {
						if (q.getId() == res.getQuestion().getId()) {
							ist += sollQuest*res.getAnswer().getValue();
						}
					}
				}
		}
		System.out.println("#######################SOLL: "+soll);
		System.out.println("#######################IST: "+ist);
		return ist/soll;
	}



}
