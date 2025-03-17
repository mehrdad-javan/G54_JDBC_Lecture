package se.lexicon;

import com.sun.source.tree.WhileLoopTree;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JdbcDemo {

    public static void main(String[] args) {

        ex2();

    }

    public static void ex1() {

        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "root");
                Statement statement = connection.createStatement();
        ) {

            String query = "SELECT id, name, class_group, create_date FROM student";

            ResultSet resultSet = statement.executeQuery(query);
            // executeQuery used for SELECT statements and returns the result as a ResultSet Object
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String classGroup = resultSet.getString("class_group");
                LocalDateTime createDateTime = resultSet.getTimestamp("create_date").toLocalDateTime();

                String formattedDate = createDateTime.format(DateTimeFormatter.ofPattern("EEEE MMMM dd yyyy"));

                System.out.println("Id: " + id + " | Name: " + name + " | Class Group: " + classGroup + " | Date Time: " + formattedDate);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database :" + e.getMessage());
        }

    }

    public static void ex2() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "root");
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name, class_group, create_date FROM student WHERE class_group LIKE ?");
        ) {

            preparedStatement.setString(1, "G1");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String classGroup = resultSet.getString("class_group");
                    LocalDateTime createDateTime = resultSet.getTimestamp("create_date").toLocalDateTime();

                    String formattedDate = createDateTime.format(DateTimeFormatter.ofPattern("EEEE MMMM dd yyyy"));

                    System.out.println("Id: " + id + " | Name: " + name + " | Class Group: " + classGroup + " | Date Time: " + formattedDate);
                }
            }


        } catch (SQLException e) {
            System.out.println("Error connecting to the database :" + e.getMessage());
        }
    }


}
