package muenchen.praxis.mfem.persistence;
import muenchen.praxis.mfem.entities.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IMFEMPersistence extends CrudRepository<Requirement, Integer> {

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

}
