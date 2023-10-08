package pet.prjct.ydemy.ydemy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.prjct.ydemy.ydemy.dao.impl.VideoRepository;
import pet.prjct.ydemy.ydemy.model.VideoCreation;
import reactor.core.publisher.Mono;


@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private static final String FORMAT = "classpath:video/%s.mp4";
    private final ResourceLoader resourceLoader;

    @Autowired
    public VideoService(VideoRepository videoRepository,
                        ResourceLoader resourceLoader) {
        this.videoRepository = videoRepository;
        this.resourceLoader = resourceLoader;
    }

    public Mono<Resource> getVideo(String title) {
        return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(FORMAT, title)));
    }

    @Transactional
    public void save(VideoCreation videoCreation, long id) {
        videoRepository.save(videoCreation, id);
    }

}
