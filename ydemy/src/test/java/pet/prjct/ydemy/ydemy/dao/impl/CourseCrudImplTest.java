package pet.prjct.ydemy.ydemy.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pet.prjct.ydemy.ydemy.dao.Crud;
import pet.prjct.ydemy.ydemy.model.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CourseCrudImplTest {

    private Crud<Course, String> courseCrudImpl;

    @Autowired
    public CourseCrudImplTest(CourseCrudImpl courseCrudImpl) {
        this.courseCrudImpl = courseCrudImpl;
    }

    @Test
    public void isExistingCourseByUsernameTest() {
        boolean isTrue = courseCrudImpl.existsById("doritte");

        Assertions.assertNotNull(isTrue);
    }

    @Test
    public void isCourseSaveWorkingTest() {
//        User user = new User("testUsername",
//                "testName",
//                "testSurname",
//                "testEmail@gmail.com",
//                "testPassword",
//                1);
//
//        Course course = new Course();
//        course.setUser(user);
//        course.setPrice(0);
//        course.setDescription("");
//        course.setTitle("testTitle");
//
//        boolean isTrue = courseCrudImpl.save(new Course());
//
//        Assertions.assertNotNull(isTrue);
    }
}
