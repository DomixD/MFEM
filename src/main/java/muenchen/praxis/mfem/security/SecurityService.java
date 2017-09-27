package muenchen.praxis.mfem.security;

import org.springframework.stereotype.Component;

@Component("SecurityService")
public class SecurityService {
    public boolean hasPermission(String accessString) {
        AccessType accessType = AccessType.valueOf(accessString);
        return Authentication.hasPermission(accessType);
    }
}
