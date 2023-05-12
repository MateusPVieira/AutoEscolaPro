package br.edu.ifsp.model.entities.student;

/*import java.util.List;

public class StudentSQLDAO {
    private Connection connection;

    public StudentSQLDAO(String dbURL, String username, String password) {
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS students ("
                    + "id INT PRIMARY KEY,"
                    + "name VARCHAR(50),"
                    + "cpf VARCHAR(11),"
                    + "rg VARCHAR(20),"
                    + "address VARCHAR(100),"
                    + "phone VARCHAR(20)"
                    + ")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveStudent(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO students (id, name, cpf, rg, address, phone) VALUES (?, ?, ?, ?, ?, ?)"
            );
            statement.setLong(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getCpf());
            statement.setString(4, student.getRg());
            statement.setString(5, student.getAddress());
            statement.setString(6, student.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE students SET name = ?, cpf = ?, rg = ?, address = ?, phone = ? WHERE id = ?"
            );
            statement.setString(1, student.getName());
            statement.setString(2, student.getCpf());
            statement.setString(3, student.getRg());
            statement.setString(4, student.getAddress());
            statement.setString(5, student.getPhone());
            statement.setLong(6, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudentById(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createStudentFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Student createStudentFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String cpf = resultSet.getString("cpf");
        String rg = resultSet.getString("rg");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");
        return new Student(id, name, cpf, rg, address, phone);
    }
}*/