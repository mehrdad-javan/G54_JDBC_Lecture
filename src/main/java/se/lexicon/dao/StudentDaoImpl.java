package se.lexicon.dao;

import se.lexicon.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    private final Connection connection;

    public StudentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Student save(Student student) {
        String sql = "INSERT INTO student (name, class_group) VALUES (?, ?)";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            // set params to the preparedStatement
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getClassGroup());

            int rows = preparedStatement.executeUpdate();
            // executeUpdate used for INSERT, UPDATE and DELETE statements
            if (rows > 0) {
                System.out.println("Done");
                try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                    if (keys.next()) {
                        int firstId = keys.getInt(1);
                        student.setId(firstId);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }

        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try(
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student");
                ResultSet resultSet = preparedStatement.executeQuery()
                ){
            while (resultSet.next()){
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("class_group"),
                        resultSet.getTimestamp("create_date").toLocalDateTime());
                students.add(student);
            }
        } catch (SQLException e){
            System.out.println("Error retrieving all students: " + e.getMessage());
        }
        return students;
    }

    @Override
    public Optional<Student> findById(int id) {
        // todo: needs completion
        return Optional.empty();
    }

    @Override
    public Student update(Student student) {
        // todo: needs completion
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        // todo: needs completion
        return false;
    }
}
