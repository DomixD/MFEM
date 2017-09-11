package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "cat")
public interface RepoCategory extends CrudRepository<Category, Integer>{
}
