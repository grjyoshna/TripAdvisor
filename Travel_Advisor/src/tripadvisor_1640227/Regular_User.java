/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tripadvisor_1640227;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author reddyjyoshnagurrala
 */
public class Regular_User 
{
    private String user_ID;
    private String pwd;
    private String email;
    private String tag;
    private String role;
   
    private String attractions;
    private String fav_destinations;
    //private String questions;
    
    public Regular_User(String a_User, String a_pwd,String emailID, String a_tag, String a_role)
    {
        user_ID=a_User;
        pwd=a_pwd;
        email=emailID;
        tag=a_tag;
        role=a_role;
        //createUserObject(user_ID, pwd,email,tag, role);
       
    }

    
    //Displaying the user menu
    public void user_Menu()
    {
        Scanner input=new Scanner(System.in);
        String sel="";
        boolean notificationsFound=false;
        ArrayList<answers>ans=new ArrayList<answers>();
        //Display the attractions user may like by his tags
        System.out.println("********************");
        System.out.println(" Attractions you may like: ");
        YouMayLike(user_ID,tag);
        System.out.println("*********************");
        
        //check whether the user has notifications
        notificationsFound=notificationFound();
                
        //Display the menu to perform actions
        System.out.println("Select the options below:  ");
        System.out.println("1. Create attraction.");
        System.out.println("2. Search attraction by Tag or City.");
        System.out.println("3. Post a review for attraction.");
         System.out.println("4. See reviews for attractions: ");
         System.out.println("5. Give score for an attraction");
        System.out.println("6. Go to my Favorite Destinations.");
        System.out.println("7. Add Favorite Destination.");
        System.out.println("8. Ask a question for attraction.");
        System.out.println("9. post answers for user's questions on attractions.");
        System.out.println("10. Log out");
        //To give notifications to the user about his questions
        if(notificationsFound)
        {
           System.out.println("You have notifications.....");
           System.out.println("N: see answers for the questions.");
        }

        
        sel=input.nextLine();
        
       switch(sel)
       {
           case "1": {
                        System.out.println("Please enter the attraction name");
                       String att_name=input.nextLine();
                      
                       createAttraction(user_ID,att_name);
                       user_Menu();
                       break;
                     }
               
           case "2":{
                        System.out.println("Enter the tag or city");
                        String a_tag=input.nextLine();
                        
                      Attractions.searchAttraction(a_tag,user_ID);
                      user_Menu();
                      break;
                    }
           case "3":{
                        System.out.println(" Please enter the attraction name: ");
                        String a_name=input.nextLine();
                        
                       postReview(user_ID,a_name);
                        user_Menu();
                        break;
                    }
               
           case "4":{
                       System.out.println("Please enter the attraction name: ");
                         String a_name=input.nextLine();
                         reviews.seeReview(a_name);
                         user_Menu();
                         break;
                    }
               
           case "5":{
                        System.out.println("Please enter the attraction name: ");
                        String a_name=input.nextLine();
                        addScore(user_ID, a_name);
                        user_Menu();
                        break;
                    }
           
           case "6": {
                       myFavoriteDestinations();
                       user_Menu();
                        break;
                     }
           case "7":{
                        add_fav_destination();
                        user_Menu();
                        break;
                    }
           case "8":{
                        askQuestions();
                        user_Menu();
                        break;
                    }
               
           case "9":{
                        answerQuestions();
                        user_Menu();
                        break;
                    } 
           case "10":{
                        Main_Menu.DisplayMenu();
                        break;
                     }
           case "N":{
                        see_answers();
                        user_Menu();
                        break;
                    }
               
           default: {
               System.out.println("Please select the valid option");
               user_Menu();
               break;
           }
               
       }
        
        
    }
   
     //Methods
    public boolean notificationFound()
    {
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input =new Scanner(System.in);
        
        try{
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            //coonection to the database
            conn=DriverManager.getConnection(db_url, "gurralar9537", "1640227");
            //Create a statement
            stat=conn.createStatement();
            //get all the user questions and answers into the result set
            //System.out.println(user_ID);
            rs=stat.executeQuery("select * from question_answer where q_user_id like '"+user_ID+"' and status like '"+"not read"+"'");
            
            while(rs.next())
            {
                return true;
            }
        }
        catch(SQLException e)
        {
            
          e.printStackTrace();   
        }
        finally
        {
             //close the database
             try
             {
                 rs.close();
                 stat.close();
                 conn.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
        return false;
    }
    
    //Display answers
    public void see_answers()
    {
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input =new Scanner(System.in);
        
        try{
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            //coonection to the database
            conn=DriverManager.getConnection(db_url, "gurralar9537", "1640227");
            //Create a statement
            stat=conn.createStatement();
            //get all the user questions and answers into the result set
            System.out.println(user_ID);
            rs=stat.executeQuery("select * from question_answer where q_user_id like '"+user_ID+"' and status like '"+"not read"+"'");
            //check whether the user is found in the resultset
            boolean userFound=false;
            String attraction="";
            String seeNotifications="";
            //create an array list to store answers
            ArrayList<answers> ans=new ArrayList<answers>();
            while(rs.next())
            {
                
                userFound=true;
                
                attraction+=rs.getString(3)+", ";
                ans.add(new answers(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5)));
            }
            //Displaying from ArrayList of answers
            if(userFound)
            {
                for(answers a:ans)
                {
                    System.out.println("Answers for question on "+a.getAttraction()+" are:");
                    System.out.println("Your question: "+a.getQuestion());
                    String answer=a.getAnswer();
                    String[] answerArray=answer.split("#");
                    for(int i=0;i<answerArray.length;i++)
                    {
                        System.out.println(answerArray[i]);
                    }
                     //update database set status to read
            stat.executeUpdate("Update question_answer set status='"+"read"+"' where question_id like '"+a.getQuestion_id()+"'");

                }
            }
           
            
        }
         catch(SQLException e)
        {
            
          e.printStackTrace();   
        }
        finally
        {
             //close the database
             try
             {
                 rs.close();
                 stat.close();
                 conn.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
        
    }
    public void checkNotifications()
    {
        
         Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input =new Scanner(System.in);
        
        try{
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            //coonection to the database
            conn=DriverManager.getConnection(db_url, "gurralar9537", "1640227");
            //Create a statement
            stat=conn.createStatement();
            //get all the user questions and answers into the result set
            System.out.println(user_ID);
            rs=stat.executeQuery("select * from question_answer where q_user_id like '"+user_ID+"' and status like '"+"not read"+"'");
            //check whether the user is found in the resultset
            boolean userFound=false;
            String attraction="";
            String seeNotifications="";
            //create an array list to store answers
            ArrayList<answers> ans=new ArrayList<answers>();
            while(rs.next())
            {
                
                userFound=true;
                
                attraction+=rs.getString(3)+", ";
                ans.add(new answers(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5)));
            }
            System.out.println("Notification:");
                System.out.println("Your questions on  "+attraction+" are answered");
            
                if(userFound)
            {
                
                for(answers a:ans)
                {
                
                System.out.println("Please click '1' to see the answers");
                seeNotifications=input.nextLine();

                    if(seeNotifications.equals("1"))
                    {
                         //rs=stat.executeQuery("select * from question_answer where q_user_id like '"+user_ID+"' and status like'"+"not read"+"'");
                       // while(rs.next())
                        //    userFound=true;
                            System.out.println("Answers for ur questions on "+a.getAttraction()+"are: ");
                            System.out.println("Your question: ");
                             System.out.println(a.getQuestion());
                            //System.out.println("Please choose ur option: ");
                                                String[] answers=a.getAnswer().split("#");
                            for(int i=0;i<answers.length;i++)
                            {
                                System.out.println(answers[i]);
                            }

                            stat.executeUpdate("Update question_answer set status='"+"read"+"' where question_id like '"+a.getQuestion_id()+"'");


                        //}

                    }
                }
            }
                
            
            
          
            
            
        }
        catch(SQLException e)
        {
            
          e.printStackTrace();   
        }
        finally
        {
             //close the database
             try
             {
                 rs.close();
                 stat.close();
                 conn.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }

    }
  
     public void createAttraction(String id,String att_name)
    {
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input =new Scanner(System.in);
        
        try{
            
           
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            //coonection to the database
            conn=DriverManager.getConnection(db_url, "gurralar9537", "1640227");
            //Create a statement
            stat=conn.createStatement();
            
            rs=stat.executeQuery("select * from attraction");
            
            boolean attractionFound=false;
            while(rs.next())
            {
                if(att_name.equals(rs.getString(2)))
                {
                    attractionFound=true;
                    System.out.println("This attraction already exists..");
                    //suser_Menu(id);
                    break;
                }
                
            }
            if(!attractionFound)
            {
                System.out.println("Please enter a tag for this attraction like(History Buff, Shopping Fanatic, Beach Goer, Urban Explorer, Nature Lover , Family Vacationer. )");
                String att_Tag=input.nextLine();
                System.out.println("Please enter short description about this attraction: ");
                String att_desc=input.nextLine();
                System.out.println("Enter the city the attraction located:");
                String att_city=input.nextLine();
                System.out.println("Enter the state it is located: ");
                String att_State=input.nextLine();
                System.out.println("Enter the country it is located: ");
                String att_Country=input.nextLine();
                System.out.println("Enter the Zipcode: ");
                String att_zipcode=input.nextLine();
                
                rs=stat.executeQuery("select * from generate_next_id");
                int  nextRequestNum=0;
                int nextAttractionNum=0;
                while(rs.next())
                {
                      nextRequestNum=rs.getInt(1);
                     
                    nextRequestNum+=1;
                }
               
                
                //update the new nextAccountNum;
             int t = stat.executeUpdate("Update generate_next_id set "
                     + "request_id = '" + nextRequestNum + "'");
            
             //insert a record into bankAccount Table
             int r = stat.executeUpdate("insert into requests values"
                     + "('" + nextRequestNum + "', '" + id + "', '" 
                     + att_name+ "', '" + att_Tag + "','"+att_desc+"','"+att_city+"','"+att_State+"','"+att_Country+"','"+att_zipcode+"','pending')");
             
             
            }
        }
        catch(SQLException e)
        {
            
          e.printStackTrace();   
        }
        finally
        {
             //close the database
             try
             {
                 rs.close();
                 stat.close();
                 conn.close();
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
        }
                
    }
  
    public void postReview(String id, String a_name)
    {
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input=new Scanner(System.in);
        try{
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
            stat=conn.createStatement();
            
            //check whether the attraction to post review is found in the attractions table
            rs=stat.executeQuery("select * from attraction");
            
            boolean attractionFound=false;
            while(rs.next())
            {
                if((rs.getString(2).toLowerCase()).equals(a_name.toLowerCase()))
                {
                    attractionFound=true;
                    break;
                }
            }
            if(!attractionFound )
            {
                System.out.println("The attraction is not found!");
                
            }
            if(attractionFound)
            {
                String review="";
                System.out.println("Please enter your review about "+a_name+" : ");
                review=input.nextLine();
                
                rs=stat.executeQuery("select * from reviews");
               // System.out.println(id);
                int r=stat.executeUpdate("Insert into reviews values('"+id+"','"+a_name+"','"+review+"')");
            }
            
        }
        catch(SQLException e)
        {
                    e.printStackTrace();
        }
        finally
        {
            try{
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
    
    //add score for attraction
    public void addScore(String id, String a_name)
    {
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input=new Scanner(System.in);
        try{
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
            stat=conn.createStatement();
            
            //check whether the attraction to post review is found in the attractions table
            rs=stat.executeQuery("select * from attraction");
            
            boolean attractionFound=false;
            while(rs.next())
            {
                if((rs.getString(2).toLowerCase()).equals(a_name.toLowerCase()))
                {
                    attractionFound=true;
                    break;
                }
            }
            if(!attractionFound )
            {
                System.out.println("The attraction is not found!");
                
            }
            if(attractionFound)
            {
                 boolean validScore=false;
                do{
                System.out.println("Please enter your rating between 1 to 5: ");
                String sel=input.nextLine();
                int num= Integer.parseInt(sel);
                int c=1;
               
                while(c<=5)
                {
                    if(num==c)
                    {
                        validScore=true;
                        break;
                    }
                    c++;
                }
               
                if(validScore)
                {
                    int r=stat.executeUpdate("Insert into scores values('"+id+"','"+a_name+"','"+num+"')");
                    System.out.println("Thank you for your rating ! ");
                    
                   ResultSet av= stat.executeQuery("select avg(score) avg_score from scores where attraction_name='"+a_name+"'");
                   double avg=0;
                   while(av.next())
                   {
                       avg=av.getDouble("avg_score");
                   }
                    
                   stat.executeUpdate("Update attraction set Score='"+avg+"' where attraction_name='"+a_name+"'");

                  
                   
//                    rs=stat.executeQuery("select * from attraction");
//                    int score=0;
//                    int count=0;
//                    while(rs.next())
//                    {
//                        if((rs.getString(2).toLowerCase()).equals(a_name.toLowerCase()))
//                        {
//                            rs=stat.executeQuery("select * from scores");
//                            while(rs.next())
//                            {
//                                if((rs.getString(2).toLowerCase()).equals(a_name.toLowerCase()))
//                                {
//                                    score+=rs.getInt(3);
//                                    count++;
//                                }
//                            }
//                            
//                        }
//                        rs=stat.executeQuery("select * from attraction");
//                    }
//                    
//                    double avg=(double)(score/count);
//                    System.out.println(avg);
                     
                 //   int t=stat.executeUpdate("Update scores set score='"+avg+"' where attraction_name='"+a_name+"'");
                    
                }
                else
                {
                    System.out.println(" The score is not valid!");
                    System.out.println();
                    System.out.println("1. Enter the score again");
                    System.out.println("2. Go to Home page");
                    String s=input.nextLine();
                    if(s.equals("1"))
                    {
                        continue;
                    }
                    if(s.equals("2"))
                    {
                        break;
                    }
                }
                }while(!validScore);
            }
         }
        catch(SQLException e)
        {
                    e.printStackTrace();
        }
        finally
        {
            try{
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
    
    //add favorite destiantion
    public void add_fav_destination()
    {
         Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input=new Scanner(System.in);
        try{
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
            stat=conn.createStatement();
            
            System.out.println("select an option to add your favorite destinations : ");
            System.out.println("1. Add favorite place(city)");
            System.out.println("2. Add favorite attraction");
            String sel=input.nextLine();
            if(sel.equals("1"))
            {
                System.out.println("Enter your favorite place: ");
                String fav_city=input.nextLine();
                //checking whether the user has already added this favorite city
                rs=stat.executeQuery("select * from fav_city where user_id='"+user_ID+"'");
                boolean cityFound=false;
                
                while(rs.next())
                {
                    if(rs.getString(3).equals(fav_city))
                    {
                        cityFound=true;
                        break;
                    }
                }
                if(cityFound)
                {
                    System.out.println("Sorry..You have already have this city in your favorites..");
                    add_fav_destination();
                }
                else if(!cityFound)
                {
                    //Generating city ID
                    rs=stat.executeQuery("select * from next_fav_city");
                    int next_city_id=0;
                    while(rs.next())
                    {
                       next_city_id=rs.getInt(1);
                        next_city_id+=1;
                        
                    }
                     String city_id = "C"+next_city_id;
                     
                     stat.executeUpdate("Insert into fav_city values('"+city_id+"', '"+user_ID+"', '"+fav_city+"')");
                     System.out.println(" your favorite city has been added successfully");
                    
                }
            }
            else if(sel.equals("2"))
            {
                System.out.println("Enter your favorite attraction: ");
                String fav_att=input.nextLine();
                //checking whether the user has already added this favorite city
                rs=stat.executeQuery("select * from fav_attraction where user_id='"+user_ID+"'");
                boolean attractionFound=false;
                
                while(rs.next())
                {
                    if(rs.getString(3).equals(fav_att))
                    {
                        attractionFound=true;
                        break;
                    }
                }
                if(attractionFound)
                {
                    System.out.println("Sorry..You have already have this attraction in your favorites..");
                    add_fav_destination();
                }
                else if(!attractionFound)
                {
                    //Generating city ID
                    rs=stat.executeQuery("select * from next_fav_attraction");
                    int next_att_id=0;
                    while(rs.next())
                    {
                       next_att_id=rs.getInt(1);
                        next_att_id+=1;
                        
                    }
                     String attraction_id = "C"+next_att_id;
                     
                     stat.executeUpdate("Insert into fav_attraction values('"+attraction_id+"', '"+user_ID+"', '"+fav_att+"')");
                     System.out.println(" your favorite attraction has been added to your favorite destinations successfully");
                    
                }
            }
            else
            {
                System.out.println("Please select a valid option..");
                add_fav_destination();
            }
        }
        catch(SQLException e)
        {
                    e.printStackTrace();
        }
        finally
        {
            try{
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
    
    //view favorite destinations
    public void myFavoriteDestinations()
    {
         Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input=new Scanner(System.in);
        try{
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
            stat=conn.createStatement();
            
            System.out.println("select an option to view: ");
            System.out.println("1. view your favorite cities.");
            System.out.println("2. View your favorite attractions.");
            System.out.println("H: Home");
            String sel=input.nextLine();
            
            //view cities
            if(sel.equals("1"))
            {
                rs=stat.executeQuery(" select * from fav_city where user_id='"+user_ID+"'");
                System.out.println("Your favorite cities..");
                ArrayList<fav_destination> fav_list=new  ArrayList<fav_destination> ();
                while(rs.next())
                {
                    System.out.println(rs.getString(3));
                    //store favcity in arrayList
                    fav_list.add(new fav_destination(rs.getString(1),rs.getString(3)));
                }
                System.out.println("Please enter the city name to view attractions in the city:");
                String c_name=input.nextLine();
                //check whether the city found in array list
                boolean cityValid=false;
                for(fav_destination city: fav_list )
                {
                    if(city.getFav_city().equals(c_name))
                    {
                        cityValid=true;
                        break;
                    }
                }
                if(cityValid)
                {
                    rs=stat.executeQuery("select * from attraction where city like '"+c_name+"'");
                    System.out.println("List of attractions in the city "+c_name);
                    boolean attFound=false;
                    while(rs.next())
                    {
                        if((c_name.toLowerCase()).equals(rs.getString(5).toLowerCase()))
                        {
                            attFound=true;
                           System.out.println(rs.getString(1)+", "+rs.getString(2)+", ");
                           System.out.println("Details..");
                           System.out.println("Type: "+rs.getString(3)
                        +", Description: "+rs.getString(4));
                           System.out.println(" Location: "+ rs.getString(5)+", "+rs.getString(6)+", "+rs.getString(8)+", "+rs.getString(7));
                           System.out.println("Score : "+rs.getString(9));
                           System.out.println("******");

                        }           
                    }
                    if(!attFound)
                    {
                        System.out.println("No attractions are found in this city");
                    }
                }
                else
                {
                    System.out.println("city name is not valid");
                }
            }
            //view attractions
            else if(sel.equals("2"))
            {
                rs=stat.executeQuery("select * from fav_attraction where user_id='"+user_ID+"'");
                ArrayList<fav_attraction> fav_att_list= new ArrayList<fav_attraction>();
                //display favorute attractions
                System.out.println("Your favorite attractions: ");
                while(rs.next())
                {
                    System.out.println(rs.getString(3));
                    fav_att_list.add(new fav_attraction(rs.getString(1), rs.getString(3)));
                }
                
                System.out.println("Enter the attraction name to view details: ");
                String att_name=input.nextLine();
                //check whether the entered attraction is valid
                boolean attValid=false;
                for(fav_attraction att: fav_att_list)
                {
                    if((att_name.toLowerCase()).equals(att.getFav_attraction()))
                    {
                        attValid=true;
                    }
                }
                if(attValid)
                {
                    rs=stat.executeQuery("select * from attraction where attraction_name like '"+att_name+"'");
                    boolean attFound=false;
                    String a_name="";
                    while(rs.next())
                    {
                        if((rs.getString(2).toLowerCase()).equals(att_name.toLowerCase()))
                        {
                            attFound=true;
                            a_name=rs.getString(2);
                               System.out.println(rs.getString(1)+", "+rs.getString(2)+", ");
                               System.out.println("Details..");
                               System.out.println("Type: "+rs.getString(3)
                            +", Description: "+rs.getString(4));
                               System.out.println(" Location: "+ rs.getString(5)+", "+rs.getString(6)+", "+rs.getString(8)+", "+rs.getString(7));
                               System.out.println("Score : "+rs.getString(9));
                               System.out.println("******");
                              
                        }
                        
                    }
                    if(attFound)
                    {
                        rs=stat.executeQuery("select * from reviews where attraction_name like '"+a_name+"'");
                        System.out.println("Reviews: ");
                        while(rs.next())
                        {
                        System.out.println(rs.getString(3));
                        }

                    }
                    if(!attFound)
                    {
                        System.out.println("No details found");
                    }
                }
                else{
                    System.out.println("Attraction name is not valid");
                }
                
            }
            else if(sel.equals("H"))
            {
                user_Menu();
            }
            else
            {
                System.out.println("Please enter a valid option..");
                myFavoriteDestinations();
            }
        }
        catch(SQLException e)
        {
                    e.printStackTrace();
        }
        finally
        {
            try{
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
    
    //ask question
    public void ask_question()
    {
         Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input=new Scanner(System.in);
        try{
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
            stat=conn.createStatement();
            
            System.out.println("Please enter the attraction name: ");
            String a_name=input.nextLine();
            System.out.println("Please enter your question: ");
            
            
        }
        catch(SQLException e)
        {
                    e.printStackTrace();
        }
        finally
        {
            try{
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
    
    public void YouMayLike(String user_ID, String tags)
    {
        //System.out.println("*****  Attractions you may like..  *****");
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        
        
        try{
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
            stat=conn.createStatement();
             
         List<String>tagItem=Arrays.asList(tags.split("#"));
         List<Attractions>attr=new ArrayList<>();
         Map<String,Attractions> uniqueAttractions=new HashMap();
         Attractions a=null;
            //rs=stat.executeQuery("select attraction_id, attraction_name from attractions where tag=aTag group by tag order by Score desc");
            for(String t: tagItem)
            {
                rs=stat.executeQuery("Select * from attraction where tag like '"+t+"' order by Score desc limit 3");
                while(rs.next())
                {
                a=new Attractions(rs.getInt(1),rs.getString(2),rs.getString(5),rs.getDouble(9));
                attr.add(a);
                String att_Name=rs.getString(2);
                uniqueAttractions.put(att_Name,a);
                }
            }
            
            attr=new ArrayList<>(uniqueAttractions.values());
            attr.sort(Comparator.comparingDouble(Attractions::getScore));
            int i=0;
            for(Attractions aL:attr)
            {
                System.out.println("Attraction ID : "+aL.getAttraction_id()+" Attraction Name: "+aL.getAttrName()+"\n City: "+aL.getAttrCity()+"\n Score: "+aL.getScore());
                i++;
                if(i==2) break;
            }
           
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
         
            try{
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
    
//ask Questions
     public  void askQuestions()
    {
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input=new Scanner(System.in);
        try{
             final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            //coonection to the database
            conn=DriverManager.getConnection(db_url, "gurralar9537", "1640227");
            //Create a statement
            stat=conn.createStatement();
            rs=stat.executeQuery("select * from attraction");
             String a_name="";
             while(rs.next())
            {
                System.out.println("Attraction ID: "+rs.getString(1)+": Attraction Name: "+rs.getString(2));
                
            }
             System.out.println("Please enter the attraction_ID on which you have a question ");
             String sel=input.nextLine();
             boolean attractionFound=false;
             rs=stat.executeQuery("select * from attraction");
            
              while(rs.next())
            {
                if(sel.equals(rs.getString(1)))
                {
                     attractionFound=true;
                     a_name=rs.getString(2);
                }
            } 
             if(attractionFound)
             {
                 rs=stat.executeQuery("select * from user");
               boolean userFound=false;
               while(rs.next())
               {
                   if(rs.getString(1).toLowerCase().equals(user_ID.toLowerCase()))
                   {
                       userFound=true;
                       break;
                   }
               }
               if(userFound)
               {
                    System.out.println("Enter your question: ");
                    String qu=input.nextLine();
                    //get next question id
                    int next=0;
                    rs=stat.executeQuery("select * from next_question_id");
                    while(rs.next())
                    {
                       next = rs.getInt(1);
                       next+=1;
                    }
                    String q_id= "Q"+next;
                    stat.executeUpdate("Update next_question_id set next = '"+next+"'");
                    question user_question=new question(q_id,qu,a_name,user_ID);
                    user_question.addQuestion();
               }
               else if(!userFound)
               {
                   System.out.println("Sorry..You must register to post a question..");
                   
               }
             }
             else
             {
                 System.out.println("Please enter a valid attraction ID");
                 askQuestions();
                
             }
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
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
     public  void answerQuestions()
    {
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input=new Scanner(System.in);
        try{
             final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            //coonection to the database
            conn=DriverManager.getConnection(db_url, "gurralar9537", "1640227");
            //Create a statement
            stat=conn.createStatement();
            
            rs=stat.executeQuery("select * from question_answer where q_user_id not like '"+user_ID+"' and status not like '"+"read"+"'");
               //store all questions for the attraction in an arraylist
               ArrayList<question> ques=new ArrayList<question>();
               while(rs.next())
               {
                   ques.add(new question(rs.getString(1),rs.getString(4),rs.getString(3),rs.getString(2)));
               }
               
                //List all the questions for the attraction selected by the user

               System.out.println("Questions: ");
               for(int i=0;i<ques.size();i++)
               {
                  
                   System.out.println("Attraction: "+ques.get(i).getAttraction()+"   "+(i+1)+". "+ques.get(i).getQuestion());
               }
               System.out.println("Please enter question number: ");
                 String q=input.nextLine();
                 System.out.println("Please enter your answer: ");
                 String answer=input.nextLine();
                 int qu=0;
                  qu=Integer.parseInt(q);
                // answer+="#.By user: "+ ques.get(qu-1).getUser_ID()+"#";
                answer+=".By user: "+user_ID+"#";
                 stat.executeUpdate("Update question_answer set answer='"+answer+"', status='"+"not read"+"' where question_id like '"+ques.get(qu-1).getQuestion_id()+"'");
                   System.out.println("Thank you for your answer");
           
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try{
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
     
    public String getAttractions() {
        return attractions;
    }

    public void setAttractions(String attractions) {
        this.attractions = attractions;
    }

    public String getFav_destinations() {
        return fav_destinations;
    }

    public void setFav_destinations(String fav_destinations) {
        this.fav_destinations = fav_destinations;
    }
    
    
     
}
