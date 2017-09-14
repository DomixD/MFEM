package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.*;
import muenchen.praxis.mfem.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MFEMServiceImpl implements IMFEMService{

	@Autowired
	private RepoFrameworkEvaluation repoframeworkEvaluation;
	@Autowired
	private RepoFramework repoFramework;
	@Autowired
	private RepoClassification repoClassification;
	@Autowired
	private RepoFEvaResult repoFEvaResult;
	@Autowired
	private RepoCategory repoCategory;

	@Override
	public List<Double> getResult(int frameID, int classiID) {
		List<Double> result = new ArrayList<>();
		double soll = 0.0;
		double ist = 0.0;
		Iterable<Category> cat = repoCategory.findAll();
		Iterator<Category> it = cat.iterator();

		Framework frame = repoFramework.findOne(frameID);
		Classification classi = repoClassification.findOne(classiID);
		FrameworkEvaluation feva = repoframeworkEvaluation.findByFrameworkInAndClassificationIn(frame, classi);
		List<FEvaResult> list = repoFEvaResult.findByFrameworkEvaluation(feva);

		while (it.hasNext()) {
			Category c = it.next();
			List<Requirement> reqs = c.getRequirementList();
			for(int i = 0; i<reqs.size(); i++) {
				Requirement r = reqs.get(i);
				if(r.getClassi().getId() == classiID){
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
			}
			result.add(ist/soll);
			soll = 0;
			ist = 0;
		}
		return result;
	}



}
