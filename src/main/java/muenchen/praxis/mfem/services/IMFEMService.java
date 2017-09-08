package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.FEvaResult;
import muenchen.praxis.mfem.entities.FrameworkEvaluation;
import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Requirement;

import java.util.List;

public interface IMFEMService {

    public int getEvaID (int frameID, int reqID);

    public double getResult (int frameID, int classiID);

}
