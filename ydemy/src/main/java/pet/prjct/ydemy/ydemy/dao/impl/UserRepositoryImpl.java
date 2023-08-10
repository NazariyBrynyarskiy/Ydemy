package pet.prjct.ydemy.ydemy.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.dao.UserRepository;
import pet.prjct.ydemy.ydemy.model.entity.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(EntityManager manager) {
        entityManager = manager;
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> result = entityManager.createQuery(
                "FROM User WHERE username =: usname", User.class);

        result.setParameter("usname", username);

        return result.getSingleResult();
    }

    @Override
    public boolean containsUserByUsername(String username) {
        TypedQuery<User> users = entityManager.createQuery(
                "FROM User WHERE username =: usname", User.class
        );
        users.setParameter("usname", username);

        for (User user : users.getResultList()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }
}
