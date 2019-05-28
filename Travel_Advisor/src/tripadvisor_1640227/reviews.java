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
public class reviews {
    
    //see reviews
    public static void seeReview( String a_name)
    {
         Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input=new Scanner(System.in);
        try{
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
            stat=conn.createStatement();
            
            rs=stat.executeQuery("select * from reviews");
            boolean reviewFound=false;
            while(rs.next())
            {
                if((rs.getString(2).toLowerCase()).equals(a_name.toLowerCase()))
                {
                    reviewFound=true;
                System.out.println("User : "+rs.getString(1)+" Review : "+rs.getString(3));
                }
            }
            if(!reviewFound)
            {
                System.out.println("Reviews not found!");
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
    
}
