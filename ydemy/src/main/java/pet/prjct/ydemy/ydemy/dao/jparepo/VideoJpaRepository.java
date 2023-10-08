package pet.prjct.ydemy.ydemy.dao.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.model.entity.Video;

@Repository
public interface VideoJpaRepository extends JpaRepository<Video, Long> {
}
