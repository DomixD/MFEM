package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.*;
import muenchen.praxis.mfem.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
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
	public int getResult(int frameID, int classiID) {
		Framework frame = repoFramework.findOne(frameID);
		Classification classi = repoClassification.findOne(classiID);
		List<FrameworkEvaluation> feva = frameworkEvaluation.findByFrameworkInAndClassificationIn(frame, classi);
		List<FEvaResult> results = new ArrayList<>();
		for(FrameworkEvaluation f:feva){
			results.add(repoFEvaResult.findByFrameworkEvaluation(frameworkEvaluation.findOne(f.getId())));
		}
		return 0;
	}



}
