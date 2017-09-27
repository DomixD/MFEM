package muenchen.praxis.mfem.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class MFEMPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        System.out.println("hasPermission");
        if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)){
            return false;
        }
        System.out.println("hasPermission2");
        AccessType accessType = (AccessType) permission;
        System.out.println(accessType);

        return muenchen.praxis.mfem.security.Authentication.hasPermission(AccessType.CREATE_QUEST);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
