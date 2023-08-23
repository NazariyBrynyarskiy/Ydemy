package pet.prjct.ydemy.ydemy.model.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long commentId;

    @Column(name = "username")
    private String username;

    @Column(name = "comment")
    private String comment;

    @Column(name = "likes")
    private int likes;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    public Comment(String username, String comment, int likes) {
        this.username = username;
        this.comment = comment;
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                ", likes=" + likes +
                '}';
    }
}
