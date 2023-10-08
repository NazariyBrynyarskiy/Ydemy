package pet.prjct.ydemy.ydemy.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.dao.Crud;
import pet.prjct.ydemy.ydemy.model.entity.Course;
import pet.prjct.ydemy.ydemy.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserCrudImpl implements Crud<User, String> {

    private EntityManager entityManager;

    @Autowired
    public UserCrudImpl(EntityManager manager) {
        entityManager = manager;
    }


    @Override
    @Transactional
    public boolean save(User user) {
        entityManager.merge(user);

        return true;
    }

    @Override
    public boolean existsById(String username) {
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
    public boolean existsByParameter(String email) {
        TypedQuery<User> users = entityManager.createQuery(
                "FROM User WHERE email = :email", User.class
        );
        users.setParameter("email", email);

        for (User user : users.getResultList()) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    // TODO: Implement this method in future
    public boolean update(User entity) {
        return false;
    }

    @Override
    public List<User> findAllById(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "FROM User WHERE username = :username", User.class);
        query.setParameter("username", username);

        return query.getResultList();
    }

    @Override
    // TODO: Implement this method in  future
    public List<User> findAllByParameter(String s) {
        return null;
    }

    @Override
    public Optional<User> findById(String username) {
        TypedQuery<User> result = entityManager.createQuery(
                "FROM User WHERE username =: usname", User.class);

        result.setParameter("usname", username);

        return Optional.ofNullable(result.getSingleResult());
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }
}
