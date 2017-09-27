package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "cat")
@PreAuthorize("@SecurityService.hasPermission('READ_ACCESS')")
public interface RepoCategory extends CrudRepository<Category, Integer>{

    @Override
    @PreAuthorize("@SecurityService.hasPermission('CREATE_CAT')")
    Category save(Category category);

}
