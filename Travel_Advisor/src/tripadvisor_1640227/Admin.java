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
import java.util.Scanner;

/**
 *
 * @author reddyjyoshnagurrala
 */
public class Admin {
    
    private String id;
    private String pwd;
    
    public Admin(String name, String password)
    {
        id=name;
        pwd=password;
    }
    
    public void Admin_Menu()
    {
        Scanner input=new Scanner(System.in);
        System.out.println("*** Please select an option to continue..  ***");
        System.out.println("1. see requests");
        System.out.println("x: Log out");
        String sel=input.nextLine();
        switch(sel)
        {
            case "1":{
                        requests();
                        Admin_Menu();
                        break;
                     }
        
            case "x":Main_Menu.DisplayMenu();
            
                
        }
    }
    public void requests()
    {
         Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input=new Scanner(System.in);
        try{
            
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
            stat=conn.createStatement();
            rs=stat.executeQuery("select * from requests where status= '"+"pending"+"' ");
            //Display all the requests from request table
            System.out.println("The pending requests are: ");
            boolean requestTheir=false;
             while(rs.next())
            {
                requestTheir=true;
                System.out.println(rs.getInt(1));
                System.out.println("Request ID : "+rs.getInt(1)+" by user  "+ rs.getString(2));
                //System.out.println("Details: ");
                System.out.println("Attarction Name:"+rs.getString(3)+"\n Details:"+rs.getString(4)+" , "+rs.getString(5)+" , "
                +rs.getString(6)+" , "+rs.getString(7)+" , "+rs.getString(8)+" , "+rs.getString(9)+", status: "+rs.getString(10));
                
                 System.out.println("*****");
            }
             
             if(requestTheir)
             {
             boolean requestFound=false;
         do{
                  
              
             System.out.println("select the option to approve requests: ");
                System.out.println("1. Approve Request");
                
                System.out.println("2. Home");
                System.out.println("x. Log out");
                String sel=input.nextLine();
                
             if(sel.equals("1"))
                {
                   System.out.println("Please enter the request id: ");
                   String rID=input.nextLine();
                   int reqNum=Integer.parseInt(rID);
                   rs=stat.executeQuery("select * from requests where status not like 'approved'");
                   
                   while(rs.next())
                   {
                       if(rs.getInt(1)==reqNum)
                       {
                           requestFound=true;
                         
                            Attractions new_att=new Attractions(rs.getInt(1)+10000,rs.getString(3),rs.getString(4),
                                    rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(9),rs.getString(8),0);
                            new_att.addAttraction();
                            
                            stat.executeUpdate("Update requests set status='approved' where request_id='"+reqNum+"'");
                           System.out.println("**Approved!**");
                            break;
                       }
                   }
                   
                   if(!requestFound)
                   {
                       System.out.println("Request Number not found!");
                   }
                }
                if(sel.equals("2"))
                {
                    Admin_Menu();
                    break;
                }
                if(sel.equals("x"))
                {
                    System.out.println("You have logged out successfully!");
                    System.out.println();
                    Main_Menu.DisplayMenu();
                    break;
                }
            }while(!requestFound);
             }
             else
             {
                 System.out.println("No requests found!");
                 Admin_Menu();
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
       
    public void requests1()
    {
        Connection conn=null;
        Statement stat=null;
        Statement stat1=null;
        Statement stat2=null;
        ResultSet rs=null;
        ResultSet r2=null;
        ResultSet nx=null;
        Scanner input=new Scanner(System.in);
        try{
            
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
            stat=conn.createStatement();
            rs=stat.executeQuery("select * from requests where status= '"+"pending"+"' ");
            System.out.println("The pending requests are: ");
            while(rs.next())
            {
                System.out.println(rs.getInt(1));
                System.out.println("Request ID : "+rs.getInt(1)+" by user  "+ rs.getString(2));
                System.out.println("Details: ");
                System.out.println(rs.getString(3)+", "+rs.getString(3)+",  "+rs.getString(4)+" , "+rs.getString(5)+" , "
                +rs.getString(6)+" , "+rs.getString(7)+" , "+rs.getString(8)+" , "+rs.getString(9)+", status: "+rs.getString(10));
                
                     System.out.println("select the option to approve requests: ");
                System.out.println("1. Approve Request");
                System.out.println("2. Home");
                System.out.println("x. Log out");
                String sel=input.nextLine();
               
                if(sel.equals("1"))
                {
                    Attractions new_att=new Attractions(rs.getInt(1)+10000,rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),0);
                    
                   System.out.println("Please enter the request id: ");
                   String rID=input.nextLine();
                   int reqNum=Integer.parseInt(rID);
                   nx=stat.executeQuery("select * from nextattractionid");
                   int next_att_ID=0;
                   while(nx.next())
                   {
                       next_att_ID=nx.getInt(1);
                       next_att_ID+=1;
                   }
                   
                   int t = stat.executeUpdate("Update nextattractionid set "
                     + "attr_id = '" + next_att_ID + "'");
                   rs=stat.executeQuery("select * from attraction");
                   while(rs.next())
                   {
                       
                   }
                   stat1=conn.createStatement();
                   
                   int r=stat1.executeUpdate("insert into attraction values"
                   +"('"+next_att_ID+"','"+new_att.getAttrName()+"','"+new_att.getAttrTag()+"',"
                   +"'"+new_att.getAttrDescription()+"','"+new_att.getAttrCity()+"','"+new_att.getAttrState()+"','"+new_att.getAttrZipcode()+"'"
                   +",'"+new_att.getAttrCountry()+"','"+new_att.getScore()+"')");
                   
                   stat2=conn.createStatement();
                   r2=stat2.executeQuery("select * from requests");
                   
                   int l=stat2.executeUpdate("Update table requests set status='"+"approved"+"' where request_id='"+reqNum+"'");
                
                }
                else if(sel.equals("2"))
                {
                    Admin_Menu();
                }
                else if(sel.equals("x"))
                {
                    Main_Menu.DisplayMenu();
                }
                else{
                    continue;
                }
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
                stat1.close();
                stat2.close();
                r2.close();
                rs.close();
                nx.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
}
