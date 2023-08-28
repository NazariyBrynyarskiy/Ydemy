package pet.prjct.ydemy.ydemy.dao.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.model.entity.Authority;

@Repository
public interface RoleJpaRepository extends JpaRepository<Authority, String> {

    @Modifying
    @Query("UPDATE Authority a SET a.authority = :authority WHERE a.username = :username")
    void updateUserRole(@Param("authority") String authority,
                        @Param("username") String username);
}
