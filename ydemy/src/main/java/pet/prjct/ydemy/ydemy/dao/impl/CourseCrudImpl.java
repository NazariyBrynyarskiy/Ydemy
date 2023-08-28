package pet.prjct.ydemy.ydemy.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.dao.Crud;
import pet.prjct.ydemy.ydemy.dao.cookie.Cookie;
import pet.prjct.ydemy.ydemy.model.entity.Course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseCrudImpl implements Crud<Course, String> {

    private EntityManager entityManager;
    private Cookie cookie;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;


    @Autowired
    public CourseCrudImpl(EntityManager entityManager,
                          Cookie cookie) {
        this.entityManager = entityManager;
        this.cookie = cookie;
    }

    @Override
    public boolean save(Course course) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO courses(price, username, title, description) " +
                             "VALUES (?, ?, ?, ?)")) {

            preparedStatement.setInt(1, course.getPrice());
            preparedStatement.setString(2, course.getUser().getUsername());
            preparedStatement.setString(3, course.getTitle());
            preparedStatement.setString(4, course.getDescription());

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            throw new RuntimeException("Error while saving course. ", e);
        }
    }

    @Override
    public List<Course> findAllById(String username) {
        TypedQuery<Course> query = entityManager.createQuery(
                "FROM Course WHERE user.username = :username", Course.class);
        query.setParameter("username", username);

        return query.getResultList();
    }

    @Override
    public List<Course> findAllByParameter(String title) {
        TypedQuery<Course> query = entityManager.createQuery(
                "FROM Course WHERE title = :title", Course.class);
        query.setParameter("title", title);

        return query.getResultList();
    }

    @Override
    public Optional<Course> findById(String username) {
        TypedQuery<Course> query = entityManager.createQuery(
                "FROM Course WHERE user.username = :username", Course.class);
        query.setParameter("username", username);

        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public boolean existsByParameter(String title) {
        String cookieUsername = cookie.getCurrentUsername();
        TypedQuery<Course> query = entityManager.createQuery(
                "FROM Course WHERE title = :title AND user.username = :username", Course.class);
        query.setParameter("title", title);
        query.setParameter("username", cookieUsername);

        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean update(Course course) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE courses SET title=?, price=?, description=? WHERE id=?")) {

            preparedStatement.setString(1, course.getTitle());
            preparedStatement.setInt(2, course.getPrice());
            preparedStatement.setString(3, course.getDescription());
            preparedStatement.setLong(4, course.getId());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving course. ", e);
        }
    }

    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("FROM Course", Course.class).getResultList();
    }
}
