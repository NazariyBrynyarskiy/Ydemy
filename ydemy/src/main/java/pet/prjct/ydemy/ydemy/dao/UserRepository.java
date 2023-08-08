package pet.prjct.ydemy.ydemy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pet.prjct.ydemy.ydemy.model.entity.User;

public interface UserRepository {
    User findByUsername(String username);

    void save(User user);

    boolean containsUserByUsername(String username);
}
