package crud_Application.adminPannel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcConnection {
    public static Connection jdbcConn(){
        String url="jdbc:postgresql://localhost:5432/collegestaff";
        String user="postgres";
        String pass="123";
        Connection con=null; //variable declaration
        //get connection from DriverManager Class OR Establish Conection
        try{
            //loading Driver
            con=DriverManager.getConnection(url, user, pass);
        }catch(SQLException sq){
            System.out.println("Line 21: "+sq);
        }
        return con;
    }
    //close the resource

    public static void closeResourse(Connection con, PreparedStatement ps){
        try {
            con.close();
            ps.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
}
