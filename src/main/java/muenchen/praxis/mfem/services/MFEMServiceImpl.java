package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.Framework;
import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.persistence.RepoFramework;
import muenchen.praxis.mfem.persistence.RepoFrameworkEvaluation;
import muenchen.praxis.mfem.persistence.RepoRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class MFEMServiceImpl implements IMFEMService{

	@Autowired
	RepoFrameworkEvaluation frameworkEvaluation;
	@Autowired
	RepoFramework repoFramework;
	@Autowired
	RepoRequirement repoRequirement;

	@Override
	public int getEvaID(int frameID, int reqID) {
		Framework frame = repoFramework.findOne(frameID);
		Requirement req = repoRequirement.findOne(reqID);
		return frameworkEvaluation.findByFrameworkInAndRequirementIn(frame, req).getId();
	}
}
