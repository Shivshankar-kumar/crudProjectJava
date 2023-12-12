package crud_Application.adminPannel;

import java.util.Scanner;

public class adminModule {
    public static void admin(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\n-------Admin module-------");
        System.out.println("-------            -------");
        int option=0;
        System.out.println("--------1. CREATE --------\n--------2. READ ----------\n--------3. UPDATE --------\n--------4. DELETE --------\n--------5. Exit ----------");
        System.out.println("---------        ---------\n");

        //handle the input-MismatchException
        try{
        System.out.print("---- Choose option ---->> ");
        option=sc.nextInt();
        }catch(Exception e){
            System.out.println("Please Enter valid input Under Given option like.. [1,2,3,4,5]");
            admin();
        }
            if(option<=5 || option>=1){
        if(option==1){
            createQuery.create();
        }else if(option==2){
            readQuery.read();
        }else if(option==3){
            updateQuery.upadte();
        }else if(option==4){
            deleteQuery.deleteQ();
        }else if(option==5){

            //handle the input-MismatchException
            try{
            System.out.print("Do you want exit? [yes/no]: ");
            String ch=sc.next();
            if (ch.equals("yes") || ch.equals("YES")) {
                System.out.println("Thanks for Visiting :) the admin module. have a nice day?\n");
                System.exit(0);
            }else if(ch.equals("no") || ch.equals("NO")){
                admin();;
            }else{
                System.out.println("--Exit choice is wrong..!-- you're redirecting..");
                admin();
            }
        }catch(Exception e){
            System.out.println("--Exit choice is misMatch, Kindly enter the appropriate input."+"\n--- redirecting..[Admin-Page]");
            admin();
        }
        }else{
            System.out.println("Input is bigger, please enter the valid option");
            admin();
        }
    }
        sc.close();
}
public static void main(String[] args) {
    admin();
}
}
