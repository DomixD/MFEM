package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Requirement;

import java.util.List;

public interface IMFEMService {
	
	public String testPrint();
	public List<Requirement> findAllByPrior(Priority prior);


}
