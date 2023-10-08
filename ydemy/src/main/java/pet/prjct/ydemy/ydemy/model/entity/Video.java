package pet.prjct.ydemy.ydemy.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "videos")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "views")
    private int views;

    @Column(name = "title")
    private String title;

    @Column(name = "video_path")
    private String videoPath;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Course course;

    @Column(name = "course_id")
    private long courseId;

    public Video(int views, String title, String videoPath, long courseId) {
        this.views = views;
        this.title = title;
        this.videoPath = videoPath;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", views=" + views +
                ", title='" + title + '\'' +
                ", videoPath='" + videoPath + '\'' +
                '}';
    }
}
