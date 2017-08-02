package muenchen.praxis.mfem.persistence;
import muenchen.praxis.mfem.entities.Requirement;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


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
