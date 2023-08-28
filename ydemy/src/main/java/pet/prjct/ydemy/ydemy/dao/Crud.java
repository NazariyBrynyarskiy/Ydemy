package pet.prjct.ydemy.ydemy.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface Crud<T, ID> {
    boolean save(T entity);

    boolean existsById(ID id);

    boolean existsByParameter(ID id);

    boolean update(T entity);

    List<T> findAllById(ID id);

    List<T> findAllByParameter(ID id);

    Optional<T> findById(ID id);

    List<T> findAll();

}
