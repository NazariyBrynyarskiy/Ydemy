package pet.prjct.ydemy.ydemy.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.prjct.ydemy.ydemy.dao.UserJpaRepository;
import pet.prjct.ydemy.ydemy.dao.UserRepository;
import pet.prjct.ydemy.ydemy.model.entity.User;
import pet.prjct.ydemy.ydemy.service.LeaderService;

import java.util.List;
import java.util.Optional;

@Service
public class LeaderServiceImpl implements LeaderService {

    private UserRepository userRepository;
    private UserJpaRepository userJpaRepository;

    @Autowired
    public LeaderServiceImpl(UserRepository userRepository,
                             UserJpaRepository userJpaRepository) {
        this.userRepository = userRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getAuthority().getAuthority().equals("ROLE_USER"))
                .toList();
    }

    @Override
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
