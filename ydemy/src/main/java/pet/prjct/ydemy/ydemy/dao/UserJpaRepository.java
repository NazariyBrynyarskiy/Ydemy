package pet.prjct.ydemy.ydemy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.model.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, String> {
}
