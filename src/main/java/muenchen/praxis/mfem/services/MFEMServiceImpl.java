package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.*;
import muenchen.praxis.mfem.persistence.*;
import muenchen.praxis.mfem.security.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MFEMServiceImpl implements IMFEMService, UserDetailsService {

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
	@Autowired
	private RepoUser repoUser;


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

	@Override
	public List<Integer> getFrames(int classiID) {
		Classification classi = repoClassification.findOne(classiID);
		List<FrameworkEvaluation> list = repoframeworkEvaluation.findByClassification(classi);
		List<Integer> result = new ArrayList<>();
		for(FrameworkEvaluation feva : list) {
			result.add(feva.getFramework().getId());
		}
		return result;
	}

	@Override
	public Integer checkUser() {
		int result = 1;
		//if (!Authentication.hasPermission(AccessType.READ_ACCESS)) {
		//	result = 0;
		//}
		return result;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repoUser.findByUsername(username);
		if (user == null) {
			return null;
		}
		List<RoleAccess> rolos = user.getRoleList();
		Set<String> permissionSet = new HashSet<>();
		for(RoleAccess rolo:rolos) {
			for(Permission permission:rolo.getPermissionList()){
				permissionSet.add(permission.getPermission());
			}
		}
		List<GrantedAuthority> auth = AuthorityUtils.createAuthorityList(permissionSet.toArray(new String[permissionSet.size()]));
		String password = user.getPassword();
		Authentication.setUserID(user.getId());
		return new org.springframework.security.core.userdetails.User(username, password, auth);
	}

}
