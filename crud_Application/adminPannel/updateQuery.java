package crud_Application.adminPannel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class updateQuery {
    public static void upadte(){
    Connection con=null;
		PreparedStatement ps=null;
		int row=0,empid;
		Scanner sc=new Scanner(System.in);
		//Connection Built
		con=jdbcConnection.jdbcConn();
		//check connection is built or not?
		if(con!=null) {

			//Handle SQLException which is throw by PreparedStatement[ps].
            try {
                final String query="update employee set name=?, mobile=?, city=?, dept=? where empid=?";
			ps=con.prepareStatement(query);
			}catch(SQLException sq) {
			System.out.println(sq);
            adminModule.admin();
		}
			if(ps!=null) {
				System.out.println("\n----3. Update information----");
                System.out.println("-------                -------");

				//Handle InputMismatchException
				try{
				System.out.print("----Employee Id:---> ");
				empid=sc.nextInt();
				ps.setInt(5, empid);
				}catch(Exception e){
					System.out.println(e);
					upadte();
				}
				System.out.print("Name----->> ");
				String name=sc.next();
				System.out.print("Mobile -->> ");
			    String mobile=sc.next();
				System.out.print("City---->> ");
				String city=sc.next();
                System.out.print("Department-->> ");
                String dept=sc.next();

				//handle SQLException throws by PreparedStatement[ps]
				try {
				ps.setString(1, name);
				ps.setString(2, mobile);
				ps.setString(3, city);
                ps.setString(4, dept);
				row=ps.executeUpdate();
				} catch (Exception e) {
					System.out.println(e);
					upadte();
				}
				if(row==1) {
					System.out.println("---- Row Updated ----");
					}else {
					System.out.println("Row not Updated due to wrong empid..!");
					upadte();
				}
					//Handle InputMismatchException
					try {
                    System.out.print("Do you want exit? [yes/no]: ");
                            String ch=sc.next();
                    if(ch.equals("yes") || ch.equals("YES")) {
                        //call to admin Module
                        adminModule.admin();
                    }else if(ch.equals("no") || ch.equals("NO")){
                        upadte();
                    }
					} catch (Exception e) {
						System.out.println("Please! type[yes/no]");
						adminModule.admin();
					}
					//Close resource
					finally{
						jdbcConnection.closeResourse(con, ps);
						sc.close();
					}
			}
		}
	}
}