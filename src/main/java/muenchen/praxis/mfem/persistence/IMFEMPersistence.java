package muenchen.praxis.mfem.persistence;
import java.io.Serializable;
import java.util.List;

public interface IMFEMPersistence {

    <T> void create(T elem);
    <T> void update(T elem);
    <T,K extends Serializable> void delete(Class<T> tClass, K key);
    <T, K extends Serializable> boolean exist(Class<T> tClass, K key);
    <T, K extends Serializable> T get(Class<T> tClass, K key);
    <T> List<T> getAll(Class<T> tClass);

}
