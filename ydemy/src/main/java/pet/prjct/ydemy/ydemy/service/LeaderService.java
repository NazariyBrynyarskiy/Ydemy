package pet.prjct.ydemy.ydemy.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.prjct.ydemy.ydemy.dao.Crud;
import pet.prjct.ydemy.ydemy.dao.jparepo.UserJpaRepository;
import pet.prjct.ydemy.ydemy.model.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class LeaderService {

    private Crud<User, String> userRepository;
    private UserJpaRepository userJpaRepository;

    @Autowired
    public LeaderService(Crud<User, String> userRepository,
                         UserJpaRepository userJpaRepository) {
        this.userRepository = userRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getAuthority().getAuthority().equals("ROLE_USER"))
                .toList();
    }

    public Optional<User> findByUsername(String username) {
        Optional<User> user = userJpaRepository.findById(username);

        if (user.isPresent()) {
            if (!user.get().getAuthority().getAuthority().equals("ROLE_USER")) {
                return userJpaRepository.findById("");
            }
        }

        return userJpaRepository.findById(username);
    }

}
