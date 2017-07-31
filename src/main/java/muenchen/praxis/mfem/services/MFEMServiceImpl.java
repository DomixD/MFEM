package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.Requirement;

import org.springframework.stereotype.Service;

@Service
public class MFEMServiceImpl implements IMFEMService{

	@Override
	public String testPrint() {
		System.out.println("get hat geklappt :)!");
		return "Irgendwas";
	}

	@Override
	public String doPost(Requirement r) {
		System.out.println("post hat geklappt :)!");
		return r.toString();
	}

}
