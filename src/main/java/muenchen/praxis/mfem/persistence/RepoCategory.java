package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "cat")
@PreAuthorize("hasAuthority('READ_ACCESS')")
public interface RepoCategory extends CrudRepository<Category, Integer>{

    @Override
    @PreAuthorize("hasAuthority('CREATE_CAT')")
    Category save(Category category);

    @Override
    @PreAuthorize("hasAuthority('CREATE_CAT')")
    void deleteAll();

}
