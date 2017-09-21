package muenchen.praxis.mfem.services;

import muenchen.praxis.mfem.entities.User;
import muenchen.praxis.mfem.persistence.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RepoUser repoUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repoUser.findByUsername(username);
        if (user == null) {
            return null;
        }
        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());
        String password = user.getPassword();
        Authentication.setUserID(user.getId());
        return new org.springframework.security.core.userdetails.User(username, password, auth);
    }

}
