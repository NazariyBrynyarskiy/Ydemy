package pet.prjct.ydemy.ydemy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.prjct.ydemy.ydemy.dao.Crud;
import pet.prjct.ydemy.ydemy.dao.jparepo.CourseJpaRepository;
import pet.prjct.ydemy.ydemy.dao.cookie.Cookie;
import pet.prjct.ydemy.ydemy.model.CourseCreation;
import pet.prjct.ydemy.ydemy.model.entity.Course;
import pet.prjct.ydemy.ydemy.model.entity.User;
import pet.prjct.ydemy.ydemy.service.search.CourseSearch;
import pet.prjct.ydemy.ydemy.service.search.CourseSearchImpl;

import java.util.*;


@Service
public class CourseService {

    private Crud<Course, String> courseCrudImpl;
    private Crud<User, String> userCrudImpl;
    private CourseJpaRepository courseJpaRepository;
    private Cookie cookie;

    @Autowired
    public CourseService(Crud<Course, String> courseCrudImpl,
                         Crud<User, String> userCrudImpl,
                         CourseJpaRepository courseJpaRepository,
                         Cookie cookie) {
        this.courseCrudImpl = courseCrudImpl;
        this.userCrudImpl = userCrudImpl;
        this.courseJpaRepository = courseJpaRepository;
        this.cookie = cookie;
    }

    @Transactional
    public boolean save(CourseCreation courseCreation) {

        String cookieUsername = cookie.getCurrentUsername();
        String title = courseCreation.getTitle();
        String description = courseCreation.getDescription();
        int price = courseCreation.getPrice();
        User user = userCrudImpl.findById(cookieUsername).get();

        Course course = new Course();
        course.setPrice(price);
        course.setUser(user);
        course.setTitle(title);
        course.setDescription(description);

        if (!courseCrudImpl.existsByParameter(title)) {

            courseCrudImpl.save(course);

            return true;
        }

        return false;
    }

    public List<Course> findAll() {
        String cookieUsername = cookie.getCurrentUsername();

        return courseCrudImpl.findAllById(cookieUsername);
    }

    public Course findById(long id) {
        Optional<Course> optionalCourse = courseJpaRepository.findById(id);

        return optionalCourse.orElseGet(Course::new);
    }

    @Transactional
    public boolean update(Course course) {
        courseCrudImpl.update(course);

        return true;
    }

    @Transactional
    public boolean delete(Course course) {
        courseJpaRepository.delete(course);

        return true;
    }

    public long count() {
        return courseJpaRepository.count();
    }

    public List<Course> findAllByParameter(String title) {
        Set<Course> courses = new HashSet<>();

        CourseSearch courseSearch = new CourseSearchImpl();
        List<String> keywords = new ArrayList<>(courseSearch.searchCourseByKeywords(title));

        for (String keyword : keywords) {
            courses.addAll(courseCrudImpl.findAllByParameter(keyword));
        }

        return new ArrayList<>(courses);
    }
}
