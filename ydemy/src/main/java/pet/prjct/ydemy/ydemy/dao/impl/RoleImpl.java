package pet.prjct.ydemy.ydemy.dao.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.model.entity.Authority;

import java.util.Optional;

@Repository
public class RoleImpl {

    private EntityManager entityManager;

    @Autowired
    public RoleImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public boolean save(Authority authority) {
        entityManager.merge(authority);

        return true;
    }

    public Optional<Authority> findById(String username) {
        TypedQuery<Authority> authorityTypedQuery = entityManager.createQuery(
                "FROM Authority WHERE username =: usname", Authority.class
        );
        authorityTypedQuery.setParameter("usname", username);

        return Optional.ofNullable(authorityTypedQuery.getSingleResult());
    }

}
