package crud_Application.adminPannel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class createQuery {
    
    public static void create(){
        Connection con=null;
        PreparedStatement ps=null;
        Scanner sc=new Scanner(System.in);

        //variable declaration
            String name,mobile,city,dept;
            int row=0;
        //getConnection from jdbcConnection class
        con=jdbcConnection.jdbcConn();
        if(con!=null){
            //Handle SQLException which is throw by PreparedStatement[ps]
            try {
                String query="insert into employee (name, mobile, city, dept) Values (?, ?, ?, ?)";
                ps=con.prepareStatement(query);
                }catch (SQLException e) {
                System.out.println(e);
                create();
            }
                if(ps!=null){
                    System.out.println("\n----1. Insert information----");
                    System.out.println("-------                  -------");
                    try {
                    System.out.print("Name ---->> ");
                    name=sc.nextLine();
                    System.out.print("Mobile -->> ");
                    mobile=sc.nextLine();
                    System.out.print("City ----> ");
                    city=sc.nextLine();
                    System.out.print("Department ->> ");
                    dept=sc.nextLine();

                    //Handle SQLException which is throw by PreparedStatement[ps]
                    try {
                    ps.setString(1, name);
                    ps.setString(2, mobile);
                    ps.setString(3, city);
                    ps.setString(4, dept);
                    row=ps.executeUpdate();
                    }catch (SQLException e) {
                        System.out.println(e);
                        create();
                    }
                    }catch (Exception e) {
                    System.out.println("---- only text is acceptable[A-Z or a-z] ----");
                    create();
                    }
                }
                    if(row==1){
                        System.out.println("----Data Insereted Successfully!----");
                    }else{
                        System.out.println("--->> Data is not insert in databse----");
                    }
                }
                //InputMismatchException throws by user input
                try {
                    System.out.print("Do you want exit? [yes/no] ");
                        String ch=sc.next();
                        if (ch.equals("yes") || ch.equals("YES")) {
                           adminModule.admin();
                        }else if(ch.equals("no") || ch.equals("NO")){
                            create();
                        }
                    }catch (Exception e) {
                    System.out.println("type [yes/no] for Exit");
                    adminModule.admin();
                }finally{
                jdbcConnection.closeResourse(con, ps);
                sc.close();
            }
        }
    }

