package crud_Application.adminPannel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class deleteQuery {
    public static void deleteQ(){
        Connection con=null;
		PreparedStatement ps=null;
        int row=0;
		Scanner sc=new Scanner(System.in);

        //make the connection
        con=jdbcConnection.jdbcConn();
        if(con!=null){
            //Handle SQLException which is throw by PreparedStatement[ps]
            try{
                final String query="delete from employee where empid=?";
                ps=con.prepareStatement(query);
                }catch(SQLException sq){
                System.out.println(sq);
                System.out.println("----redirecting... on admin page----");
                adminModule.admin();
            }
                if (ps!=null) {
                    System.out.println("\n----4. Delete information----");
                    System.out.println("-------              -------");

                    //Handle InputMismatchException which is entered by User
                    try{
                    System.out.print("----Empld to delete--->> ");
                    int empid=sc.nextInt();
                    ps.setInt(1, empid);
                    row=ps.executeUpdate();
                    }catch(Exception e){
                        System.out.println("----Id Should be integer only----");
                        deleteQ();
                    }
                    if(row==1){
                        System.out.println("---Data Has been deleted..!---");
                    }else{
            System.out.println("Data has not deleted due to wrong empId..!");
            System.out.println("---- kindly enter again----");
            deleteQ();
                    }
                } 

                //Handle InputMismatchException
                try{
                        System.out.print("Do you want exit? [yes/no]: ");
                        String ch=sc.next();
                if(ch.equals("yes") || ch.equals("YES")) {
                    //call to admin Module
                    adminModule.admin();
                }else if(ch.equals("no") || ch.equals("NO")){
                    deleteQ();
                }
            }catch(Exception e){
                System.out.println("--Exit input is wrong..!--");
                adminModule.admin();
            }
            finally{
                jdbcConnection.closeResourse(con, ps);
                sc.close();
            }
        }
    }
}
