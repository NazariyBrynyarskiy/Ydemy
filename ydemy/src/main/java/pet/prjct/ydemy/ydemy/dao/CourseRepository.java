package pet.prjct.ydemy.ydemy.dao;

import pet.prjct.ydemy.ydemy.model.CourseCreation;
import pet.prjct.ydemy.ydemy.model.entity.Course;

public interface CourseRepository {

    void save(Course course);

    void save(int price, String username, String title, String description);

    boolean courseExisting(String title, String username);
}
