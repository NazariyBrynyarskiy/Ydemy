package pet.prjct.ydemy.ydemy.dao;


import pet.prjct.ydemy.ydemy.model.entity.User;

import java.util.List;

public interface UserRepository {
    User findByUsername(String username);

    void save(User user);

    boolean containsUserByUsername(String username);

    boolean containsUserByEmail(String email);

    List<User> findAll();
}
