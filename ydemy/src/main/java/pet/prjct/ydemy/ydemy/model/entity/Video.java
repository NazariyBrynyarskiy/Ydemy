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

    @Column(name = "videoPath")
    private String videoPath;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    public Video(int views, String title, String videoPath) {
        this.views = views;
        this.title = title;
        this.videoPath = videoPath;
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
