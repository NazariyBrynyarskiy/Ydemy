package pet.prjct.ydemy.ydemy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.prjct.ydemy.ydemy.dao.CourseRepository;
import pet.prjct.ydemy.ydemy.dao.cookie.Cookie;
import pet.prjct.ydemy.ydemy.model.CourseCreation;
import pet.prjct.ydemy.ydemy.model.entity.Course;
import pet.prjct.ydemy.ydemy.service.CourseService;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private Cookie cookie;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,
                             Cookie cookie) {
        this.courseRepository = courseRepository;
        this.cookie = cookie;
    }

    @Override
    @Transactional
    public boolean save(CourseCreation courseCreation) {

        String cookieUsername = cookie.getCurrentUsername();
        String title = courseCreation.getTitle();
        String description = courseCreation.getDescription();
        int price = courseCreation.getPrice();

        if (!courseRepository.courseExisting(title, cookieUsername)) {

            courseRepository.save(price, cookieUsername, title, description);

            return true;
        }

        return false;
    }

    @Override
    public List<Course> findAllByCookieUsername() {
        String cookieUsername = cookie.getCurrentUsername();

        return courseRepository.findAllByUsername(cookieUsername);
    }
}
