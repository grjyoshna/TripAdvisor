/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tripadvisor_1640227;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author reddyjyoshnagurrala
 */
public class Main_Menu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       DisplayMenu();
        
    }
    
    
    public  static void DisplayMenu()
    {
        
        Scanner input=new Scanner(System.in);
        //Display the main menu and get the input
        
        String sel="";
        
        while(sel!="x")
        {
            System.out.println("Welcome to TripAdvisor..Please enter your selection:");
            System.out.println("1. Create an account.");
            System.out.println("2. Login.");
            System.out.println("3. Search for an Attraction by tag or city.");
            System.out.println("4. See reviews for attraction ");
           // System.out.println("x. Exit");
            
            sel=input.nextLine();
           System.out.println(sel);
            
            if(sel.equals("1"))
            {
                AccountCreator.CreateAccount();
                break;
            }
            else if(sel.equals("2"))
            {
                login();
                break;
            }
            else if(sel.equals("3"))
            {
                System.out.println("Enter the tag or city");
                String a_tag=input.nextLine();
                Attractions.searchAttraction(a_tag,"");
                DisplayMenu();
                break;
            }
            else if(sel.equals("4"))
            {
                 System.out.println(" Please enter the attraction name: ");
                 String a_name=input.nextLine();
                 reviews.seeReview(a_name);
                 DisplayMenu();
                 break;
            }
            else if(sel.equals("x"))
            {
                DisplayMenu();
            }
        }
        
        
    }
    
  public static void login()
    {
         Scanner input=new Scanner(System.in);
        
        System.out.println("Please enter your username: ");
        String user_ID=input.nextLine();
        System.out.println("Please enter your Password: ");
        String user_pwd=input.nextLine();
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        
        try
        {
          final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537"; //name of db
          //connection to db
          conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
          //create a statement
          stat=conn.createStatement();
          
          //get the user_ID
          rs=stat.executeQuery("select * from User");
          boolean userFound=false;
          while(rs.next())
          {
              String user=rs.getString(1);
              
              if(user_ID.equals(user))
              {
                  userFound=true;
                  if(user_pwd.equals(rs.getString(2)))
                  {
                      String role= rs.getString(5);
                      
                      System.out.println("You have logged in successfully!");
                     if(role.equals("admin"))
                     {
                         Admin adm=new Admin(user_ID, user_pwd);
                         adm.Admin_Menu();
                     }
                     if(role.equals("regular user"))
                     {
                         Regular_User a_user=new Regular_User(user_ID, user_pwd,rs.getString(3),rs.getString(4),role);
                         a_user.user_Menu();
                     }
                      //User a_user=new User(user_ID, user_pwd,rs.getString(3),rs.getString(4),role);
                      
                  }
                  else
                  {
                      System.out.println("Please enter the correct password..");
                      login();
                  }
              }
             
          }
          
           if(!userFound)
              {
                 
                  System.out.println("Please enter a valid Username..");
                  login();
              }
          
          
        }
        catch(Exception e)
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
    
   
    
  
    
}
