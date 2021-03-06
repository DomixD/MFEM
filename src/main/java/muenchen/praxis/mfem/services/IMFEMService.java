package muenchen.praxis.mfem.services;

import java.util.List;

public interface IMFEMService {

    List<Double> getResult (int frameID, int classiID);

    List<Integer> getFrames (int frameID);

    Integer checkUser();

}
