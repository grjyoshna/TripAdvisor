/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tripadvisor_1640227;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author reddyjyoshnagurrala
 */
public class AccountCreator {
    
    public static void CreateAccount()
    {
        Connection conn =null;
        Statement stat = null;
        ResultSet rs=null;
    
        
        //declare local variables
        Scanner input=new Scanner(System.in);
        String user_ID="";
        String user_pwd="";
        String emailID="";
        String tag="";
        String s=""; //temp variable 
        boolean userFound=false;
        //prompt and inpu
        while(1!=2)
        {
         System.out.println("Please enter your Account username.(Should have atleast one letter and one digit with length between 3-10 characters) :");
         s=input.nextLine();
           if( s.matches("(?=.*[a-zA-Z])(?=.*\\d).{3,10}"))
           {
              
               //check whether the user exists in the database
                try
                {
                     
                    final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537"; // name of db
                    //connection to the db
                    conn=(Connection) DriverManager.getConnection(db_url, "gurralar9537", "1640227"); 
                    //create a statement
                    stat=conn.createStatement();

                    //get the user accountID
                    rs=stat.executeQuery("select * from User");

                    //int nextNum=0;
                    userFound=false;
                    while(rs.next())
                    {
                        
                        if(s.equals(rs.getString(1)))
                        {
                            //if user already exists
                            userFound=true;
                            break;
                        }
                       
                    }
                   if(userFound)
                   {
                       System.out.println("User already Exists..");
                       //Main_Menu.DisplayMenu();
                      // break;
                   }
                   else
                   {
                       user_ID=s;
                       break;
                   }


                }
                catch(SQLException e)
                {
                    e.printStackTrace();

                }
                finally
                {

                    try
                    {
                        conn.close();
                        stat.close();
                        rs.close();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }

                }
        
           }
           else
           {
                System.out.println("Please enter a valid username between 3-10 characters atleast contains one digit and one letter");
           }
       
        }
        
        //if user does not exists make user enter password, email.. to create account
        if(!userFound)
        {
            while(1!=2)
            {
                System.out.println("Please enter your Account Password.(should not be same as login ID):");
                s=input.nextLine();
                if(s.matches("(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]).{5,10}"))
                {
                    user_pwd=s;
                    break;
                }
                else
                {
                    System.out.println("please enter a valid password");
                }

            }




            System.out.println("Please enter your Email ID:");
            emailID=input.nextLine();
           Map<Integer,String>tags=new HashMap<>();
           tags.put(1,"History Buff");
           tags.put(2, "Shopping Fanatic");
           tags.put(3, "Beach Goer");
           tags.put(4, "Urban Explorer");
           tags.put(5, "Nature Lover");
           tags.put(6, "Family Vacationer");
            int countTag=0;
            System.out.println(tags.get(1));
            while(1!=2)
            {
                System.out.println("Please enter atleast one tag number: ");
                
                System.out.println("1. History Buff");
                System.out.println("2. Shopping Fanatic");
                System.out.println("3. Beach Goer");
                System.out.println("4. Urban Explorer");    
                System.out.println("5. Nature Lover");
                System.out.println("6. Family Vacationer");
                        
                //System.out.println("1. Enter a tag(History Buff, Shopping Fanatic, Beach Goer, Urban Explorer, Nature Lover , Family Vacationer. )");
                System.out.println("x. Finish entering tags");
                s=input.nextLine();
               
                if(!(s.equals("x")||s.equals("X") ))
                for(int i=0;i<tags.size();i++)
                {
                    if((i+1)==Integer.parseInt(s))
                    {
                        tag+=tags.get(i+1)+"#";
                    countTag++;
                    }
                }
               
                
               if( s.equals("x")||s.equals("X"))
                {
                    if(countTag>0)
                    break;
                       
                }
            }
            //Insert the user details into database
            
            try
            {

                final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537"; // name of db
                //connection to the db
                conn=DriverManager.getConnection(db_url, "gurralar9537", "1640227");
                //create a statement
                stat=conn.createStatement();

                //get the user accountID
                rs=stat.executeQuery("select * from User");

                int r=stat.executeUpdate("Insert into User values('"+user_ID+"','"+user_pwd+"','"+emailID+"','"+tag+"','"+"regular user"+"')");

                System.out.println("Your account has been created Successfully!");
                Main_Menu.DisplayMenu();
            }
            catch(SQLException e)
            {
                 System.err.println("Account creation was failed!");
                      e.printStackTrace();
            }
            finally
            {
                 try
                {
                    conn.close();
                    stat.close();
                    rs.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        
       
    
        
    }
    
   
}
