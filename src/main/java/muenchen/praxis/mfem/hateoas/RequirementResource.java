package muenchen.praxis.mfem.hateoas;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class RequirementResource extends ResourceSupport {

    private Object resource;

    public RequirementResource(Object resource) {
        this.resource = resource;
    }

}
