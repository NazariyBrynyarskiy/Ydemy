package pet.prjct.ydemy.ydemy.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pet.prjct.ydemy.ydemy.dao.CourseRepository;
import pet.prjct.ydemy.ydemy.dao.UserRepository;
import pet.prjct.ydemy.ydemy.model.CourseCreation;
import pet.prjct.ydemy.ydemy.model.entity.Course;
import pet.prjct.ydemy.ydemy.model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    private EntityManager entityManager;
    private UserRepository userRepository;

    @Autowired
    public CourseRepositoryImpl(EntityManager entityManager,
                                UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }


    @Override
    public void save(Course course) {
        entityManager.merge(course);
    }

    @Override
    public void save(int price, String username, String title, String description) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/Ydemy";
        String dbUser = "springstudent";
        String dbPassword = "springstudent";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO courses(price, username, title, description) " +
                             "VALUES (?, ?, ?, ?)")) {

            preparedStatement.setInt(1, price);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, description);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving course", e);
        }
    }

    @Override
    public boolean courseExisting(String title, String username) {
        User user = userRepository.findByUsername(username);
        TypedQuery<Course> query = entityManager.createQuery(
                "FROM Course WHERE title = :title AND user.username = :username", Course.class);
        query.setParameter("title", title);
        query.setParameter("username", username);

        return !query.getResultList().isEmpty();
    }
}
