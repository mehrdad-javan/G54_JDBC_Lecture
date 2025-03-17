package se.lexicon;

import se.lexicon.dao.StudentDaoImpl;
import se.lexicon.db.DatabaseConnection;
import se.lexicon.model.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try (
                Connection connection = DatabaseConnection.getConnection();
        ) {
            //Student student1 = new Student("Test", "G3");
            StudentDaoImpl studentDao = new StudentDaoImpl(connection);
            //Student savedStudent = studentDao.save(student1);
            //System.out.println(savedStudent);
            List<Student> studentList = studentDao.findAll();

            studentList.forEach(student -> System.out.println(student));
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}