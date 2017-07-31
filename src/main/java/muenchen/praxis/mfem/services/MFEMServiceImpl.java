package muenchen.praxis.mfem.services;

import org.springframework.stereotype.Service;

@Service
public class MFEMServiceImpl implements IMFEMService{

	@Override
	public String testPrint() {
		System.out.println("hat geklappt :)!");
		return "Irgendwas";
	}

	@Override
	public String doPost(String name) {
		return name.toUpperCase();
	}

}
