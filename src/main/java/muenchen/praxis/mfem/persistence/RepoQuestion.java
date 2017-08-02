package muenchen.praxis.mfem.persistence;
import muenchen.praxis.mfem.entities.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepoQuestion extends CrudRepository<Question, Integer> {

    @Override
    Iterable<Question> findAll();

    @Override
    Question findOne(Integer id);

    @Override
    void delete(Integer id);

    @Override
    Question save(Question question);

    @Override
    boolean exists(Integer id);

}
