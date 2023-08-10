package pet.prjct.ydemy.ydemy.dao;

import jakarta.persistence.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import pet.prjct.ydemy.ydemy.model.entity.User;

import java.util.List;

public interface UserRepository {
    User findByUsername(String username);

    void save(User user);

    boolean containsUserByUsername(String username);

    List<User> findAll();
}
