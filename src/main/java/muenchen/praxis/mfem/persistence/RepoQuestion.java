package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Question;
import muenchen.praxis.mfem.security.AccessType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "quest")
public interface RepoQuestion extends CrudRepository<Question, Integer> {

    @Override
    @PreAuthorize("hasPermission('question','CREATE_QUEST')")
    Question save(Question question);

}
