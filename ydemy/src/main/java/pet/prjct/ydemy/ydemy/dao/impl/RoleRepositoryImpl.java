package pet.prjct.ydemy.ydemy.dao.impl;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.dao.RoleRepository;
import pet.prjct.ydemy.ydemy.model.entity.Authority;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private EntityManager entityManager;

    @Autowired
    public RoleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Authority findByUsername(String username) {
        TypedQuery<Authority> authorityTypedQuery = entityManager.createQuery(
                "FROM Authority WHERE username =: usname", Authority.class
        );
        Authority authority = null;
        try {
            authority = authorityTypedQuery.getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return authority;
    }

    @Override
    @Transactional
    public void save(Authority authority) {
        entityManager.merge(authority);
    }
}
