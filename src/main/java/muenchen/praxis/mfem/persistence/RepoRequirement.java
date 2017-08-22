package muenchen.praxis.mfem.persistence;

import muenchen.praxis.mfem.entities.Priority;
import muenchen.praxis.mfem.entities.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource(path = "req")
public interface RepoRequirement extends CrudRepository<Requirement, Integer> {

    @Override
    Iterable<Requirement> findAll();

    @Override
    Requirement findOne (Integer id);

    @Override
    void delete(Integer id);

    @Override
    Requirement save(Requirement requirement);

    @Override
    boolean exists (Integer id);

    List<Requirement> findAllByPrior(@Param("priority") Priority priority);

}
