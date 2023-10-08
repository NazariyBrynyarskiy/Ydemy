package pet.prjct.ydemy.ydemy.dao.impl;


import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.model.VideoCreation;
import pet.prjct.ydemy.ydemy.model.entity.Course;
import pet.prjct.ydemy.ydemy.model.entity.Video;

@Repository
public class VideoRepository {

    private JpaRepository<Video, Long> repository;

    @Autowired
    public VideoRepository(JpaRepository<Video, Long> repository) {
        this.repository = repository;
    }


    public void save(VideoCreation videoCreation, long id) {
        Video video = new Video();

        video.setViews(0);
        video.setTitle(videoCreation.getTitle());
        video.setVideoPath(videoCreation.getPath());
        video.setCourseId(id);

        repository.save(video);
    }

}
