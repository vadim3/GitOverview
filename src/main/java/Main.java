import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "admin";

    public static void main(String[] args) {
        Connection connection;

        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            statement.execute("insert into animals (name, age, type) values('Lolo', 15, '56')");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM animals");
            List<Animal> animals = new ArrayList<Animal>();
            while (resultSet.next()){
                animals.add(new Animal(resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("type")));

            }
            System.out.println(animals.size());
            statement.execute("DELETE FROM animals WHERE name='Lolo'");

            statement.close();
            connection.close();

//            if (!connection.isClosed()){
//                System.out.println("Not closed");
//            }
//            connection.close();
//            if (connection.isClosed()){
//                System.out.println("Closed");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
