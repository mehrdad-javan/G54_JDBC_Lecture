package se.lexicon;

import se.lexicon.dao.AttendanceDaoImpl;
import se.lexicon.dao.StudentDaoImpl;
import se.lexicon.db.DatabaseConnection;
import se.lexicon.model.Attendance;
import se.lexicon.model.AttendanceStatus;
import se.lexicon.model.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try(Connection connection = DatabaseConnection.getConnection();) {

            try {
                connection.setAutoCommit(false);

                StudentDaoImpl studentDao = new StudentDaoImpl(connection);
                AttendanceDaoImpl attendanceDao = new AttendanceDaoImpl(connection);

                Student student1 = new Student("Test", "G3");
                Student savedStudent = studentDao.save(student1); // inserts student1
                System.out.println("savedStudent = " + savedStudent); // prints inserted student

                Attendance attendanceForStudent1 = new Attendance(savedStudent, AttendanceStatus.PRESENT);
                Attendance savedAttendance = attendanceDao.save(attendanceForStudent1); // inserts attendance records
                System.out.println("savedAttendance = " + savedAttendance);


                connection.commit(); // save both records permanently
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Error: " + e.getMessage());
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}