package pet.prjct.ydemy.ydemy.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pet.prjct.ydemy.ydemy.dao.CourseRepository;
import pet.prjct.ydemy.ydemy.model.entity.Course;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb"
})
class CourseRepositoryImplTest {

    private CourseRepository repository;

    @Autowired
    public CourseRepositoryImplTest(CourseRepository courseRepository) {
        repository = courseRepository;
    }

}

//        this.price = price;
//        this.studentAmount = studentAmount;
//        this.lectures = lectures;
//        this.totalHours = totalHours;
//        this.rating = rating;
//        this.username = username;
//        this.title = title;
//        this.description = description;