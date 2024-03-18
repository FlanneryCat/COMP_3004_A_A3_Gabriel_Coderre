package assignment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    static String url = "jdbc:postgresql://localhost:5432/<DATABASE_NAME>";
    static String user = "<USER_NAME>";
    static String password = "<PASSWORD>";

    
    public static void getAllStudents() {
        
        try { 
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT * FROM students";
                ResultSet result = stmt.executeQuery(query);

                while(result.next()) {
                    
                    int id = result.getInt("student_id");
                    String first = result.getString("first_name");
                    String last = result.getString("last_name");
                    String email = result.getString("email");
                    Date date = result.getDate("enrollment_date");

                    System.out.println("ID: " + id + ", Name: " + first + " " + last + ", Email: " + email + ", Date of Enrollment: " + date);
                }
            } else {
                System.out.println("Failed to establish connection.");
            } 
            conn.close();

        } catch (ClassNotFoundException | SQLException e) { e.printStackTrace(); }
    }

    public static void addStudent(int id, String first, String last, String mail, String date) {
        
        try { 
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            
            if (conn != null) {
                String query = "INSERT INTO students (student_id, first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.setString(2,first);
                stmt.setString(3,last);
                stmt.setString(4,mail);
                stmt.setDate(5,Date.valueOf(date));
                int rowInserted = stmt.executeUpdate();
                if(rowInserted > 0) {
                    System.out.println("Student added successfully.");
                }
                

            } else {
                System.out.println("Failed to establish connection.");
            } 
            conn.close();

        } catch (ClassNotFoundException | SQLException e) { e.printStackTrace(); }
    }


    public static void updateStudentEmail(int id, String mail) {
        
        try { 
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            
            if (conn != null) {
                String query = "UPDATE students SET email = ? WHERE student_id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);     
                stmt.setString(1,mail);
                stmt.setInt(2, id);
                int rowUpdated = stmt.executeUpdate();
                if(rowUpdated > 0) {
                    System.out.println("Student email updated successfully.");
                }
                

            } else {
                System.out.println("Failed to establish connection.");
            } 
            conn.close();

        } catch (ClassNotFoundException | SQLException e) { e.printStackTrace(); }
    }


    public static void deleteStudent(int id) {
        
        try { 
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            
            if (conn != null) {
                String query = "DELETE FROM students WHERE student_id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);     
                stmt.setInt(1, id);
                int rowDeleted = stmt.executeUpdate();
                if(rowDeleted > 0) {
                    System.out.println("Student was executed successfully.");
                }
                

            } else {
                System.out.println("Failed to establish connection.");
            } 
            conn.close();

        } catch (ClassNotFoundException | SQLException e) { e.printStackTrace(); }
    }

    
    public static void main(String[] args) {
        
        System.out.println("");
        getAllStudents();

        System.out.println("");
        addStudent(4, "Jane", "Doe", "janeDoesArt@example.com", "2022-09-01");
        System.out.println("");
        getAllStudents();
        
        System.out.println("");
        updateStudentEmail(4, "jane@example.com");
        System.out.println("");
        getAllStudents();
        
        System.out.println("");
        deleteStudent(4);
        System.out.println("");
        getAllStudents();
        
    }




}