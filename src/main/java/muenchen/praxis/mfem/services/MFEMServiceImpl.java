package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.*;
import muenchen.praxis.mfem.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MFEMServiceImpl implements IMFEMService{

	@Autowired
	RepoFrameworkEvaluation frameworkEvaluation;
	@Autowired
	RepoFramework repoFramework;
	@Autowired
	RepoRequirement repoRequirement;
	@Autowired
	RepoClassification repoClassification;
	@Autowired
	RepoFEvaResult repoFEvaResult;

	@Override
	public int getEvaID(int frameID, int reqID) {
		Framework frame = repoFramework.findOne(frameID);
		Requirement req = repoRequirement.findOne(reqID);
		return frameworkEvaluation.findByFrameworkInAndRequirementIn(frame, req).getId();
	}

	@Override
	public double getResult(int frameID, int classiID) {
		double soll = 0.0;
		double ist = 0.0;
		Framework frame = repoFramework.findOne(frameID);
		Classification classi = repoClassification.findOne(classiID);
		List<FrameworkEvaluation> feva = frameworkEvaluation.findByFrameworkInAndClassificationIn(frame, classi);
		List<FEvaResult> results = new ArrayList<>();
		for(FrameworkEvaluation f:feva){
			List<FEvaResult> list = repoFEvaResult.findByFrameworkEvaluation(f);
			for (FEvaResult re : list) {
				results.add(re);
			}
		}
		for(FrameworkEvaluation f:feva) {
			double sollQuest = f.getPriority().getValue()/f.getRequirement().getQuestionList().size();
			soll += f.getPriority().getValue();
			List<FEvaResult> list = repoFEvaResult.findByFrameworkEvaluation(f);
			for (FEvaResult re : list) {
				ist += sollQuest*re.getAnswer().getValue();
			}
		}
		System.out.println("#######################SOLL: "+soll);
		System.out.println("#######################IST: "+ist);
		return ist/soll;
	}



}
