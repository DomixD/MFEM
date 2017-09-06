package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.persistence.RepoRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MFEMServiceImpl implements IMFEMService{

	@Autowired
	RepoRequirement persistenceReq;

}
