package muenchen.praxis.mfem.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class MFEMPersistenceImpl implements IMFEMPersistence {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <T> void create(T elem) {
        entityManager.persist(elem);
    }

    @Override
    public <T> void update(T elem) {
        entityManager.merge(elem);
    }

    @Override
    public <T,K extends Serializable> void delete(Class<T> tClass, K key) {
        if(exist(tClass,key))
            entityManager.remove(get(tClass,key));
    }

    @Override
    public <T, K extends Serializable> boolean exist(Class<T> tClass, K key) {
        return get(tClass, key)!=null?true:false;
    }

    @Override
    public <T, K extends Serializable> T get(Class<T> tClass, K key) {
        return entityManager.find(tClass, key);
    }

    @Override
    public <T> List<T> getAll(Class<T> tClass) {
        String queryString = "from " + tClass.getSimpleName();
        List<T> list = entityManager.createQuery(queryString).getResultList();
        return list;
    }
}
