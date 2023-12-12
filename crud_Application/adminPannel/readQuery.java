package crud_Application.adminPannel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class readQuery {
    public static void read(){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Scanner sc=new Scanner(System.in);
        //getConnection from jdbcConnection class
        con=jdbcConnection.jdbcConn();
        if(con!=null){
            try {
                final String query1="select empid, name, mobile, city, dept from employee where empid=?";
                ps=con.prepareStatement(query1);
                 }catch (SQLException e) {
               System.out.println(e);
               adminModule.admin();
            }
                if(ps!=null){
                    System.out.println("\n----2. Fetch information----");
                    System.out.println("-------              -------");
                    
                    //handle InputMismatchException
                    try{
                        System.out.print("----Employee Id:---> ");
                    int empid=sc.nextInt();
                    ps.setInt(1, empid);
                    rs=ps.executeQuery();
                    }catch(Exception e){
                        System.out.println("---->> Id should be only integer ---");
                        read();
                    }
                }
                    if(rs!=null){
                        
                        System.out.println("\n----Employee detais----\n");
                        //ResultSet throw an SQLException
                        try {
                            
                            if(rs.next()){
                                //fetch data from resultSet
                                System.out.println("EmpId---->> "+rs.getInt(1));
                                System.out.println("Name ---->> "+rs.getString(2));
                                System.out.println("Mobile---->> "+rs.getString(3));
                                System.out.println("City ----->> "+rs.getString(4));
                                System.out.println("Department->> "+rs.getString(5));
                            }else{
                                System.out.println("--- Id doesn't exist in Database, please enter again---");
                            read();
                            }
                        } catch (SQLException e) {
                            System.out.println(e);
                            read();
                        }
                    }
                }  
                            //handle InputMismatchException
                            try{
                                System.out.print("Do you want exit? [yes/no]: ");
                                String ch=sc.next();
                            if(ch.equals("yes") || ch.equals("YES")) {
                                //call to admin Module
                                adminModule.admin();
                            }else if(ch.equals("no") || ch.equals("NO")){
                                read();
            
                            }
                        }catch(Exception e){
                            System.out.println("Invalid Exit option--> [redirecting.. on Fetch Data page] --");
                            adminModule.admin();
                        }

                        //close Resource
           finally{
                jdbcConnection.closeResourse(con, ps);
                sc.close();
            }
        }
    }
