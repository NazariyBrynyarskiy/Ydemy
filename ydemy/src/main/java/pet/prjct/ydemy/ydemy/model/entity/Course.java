package pet.prjct.ydemy.ydemy.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "price")
    private int price;

    @Column(name = "student_amount")
    private int studentAmount;

    @Column(name = "lectures")
    private int lectures;

    @Column(name = "total_hours")
    private int totalHours;

    @Column(name = "rating")
    private float rating;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Video> videos;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Comment> comments;

    public Course(int price,
                  int studentAmount,
                  int lectures,
                  int totalHours,
                  float rating,
                  String title,
                  String description) {
        this.price = price;
        this.studentAmount = studentAmount;
        this.lectures = lectures;
        this.totalHours = totalHours;
        this.rating = rating;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", price=" + price +
                ", studentAmount=" + studentAmount +
                ", lectures=" + lectures +
                ", totalHours=" + totalHours +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
