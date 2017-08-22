package muenchen.praxis.mfem.hateoas;

import muenchen.praxis.mfem.entities.Requirement;
import muenchen.praxis.mfem.resource.MFEMResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class RequirementAssembler extends ResourceAssemblerSupport<Requirement, RequirementResource> {

    public RequirementAssembler() {
        super(Requirement.class, RequirementResource.class);
    }

    @Override
    public RequirementResource toResource(Requirement requirement) {
        RequirementResource requirementResource = new RequirementResource(requirement);
        Link selfLink = linkTo(methodOn(MFEMResource.class).getReq(requirement.getId())).withSelfRel();
        requirementResource.add(selfLink);
        return requirementResource;
    }
}
