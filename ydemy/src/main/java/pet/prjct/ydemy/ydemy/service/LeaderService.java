package pet.prjct.ydemy.ydemy.service;


import pet.prjct.ydemy.ydemy.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface LeaderService {

    List<User> findAllUsers();

    Optional<User> findByUsername(String username);

}
