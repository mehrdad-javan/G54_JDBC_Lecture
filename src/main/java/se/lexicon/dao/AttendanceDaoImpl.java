package se.lexicon.dao;

import se.lexicon.model.Attendance;

import java.sql.*;

public class AttendanceDaoImpl implements AttendanceDao {

    private final Connection connection;

    public AttendanceDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Attendance save(Attendance attendance) {

        String sql = "INSERT INTO attendance (student_id, status) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, attendance.getStudent().getId());
            preparedStatement.setString(2, attendance.getStatus().name());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                ResultSet keys = preparedStatement.getGeneratedKeys();
                if (keys.next()) {
                    attendance.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saving attendance: " + e.getMessage());
        }
        return attendance;

    }

}
