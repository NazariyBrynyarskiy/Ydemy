package pet.prjct.ydemy.ydemy.service;

import pet.prjct.ydemy.ydemy.model.CourseCreation;
import pet.prjct.ydemy.ydemy.model.entity.Course;

import java.util.List;

public interface CourseService {

    boolean save(CourseCreation courseCreation);

    List<Course> findAllByCookieUsername();

    Course findById(long id);

    boolean update(Course course);

    List<Course> findAllByTitle(String title);
}
