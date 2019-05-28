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
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author reddyjyoshnagurrala
 */
public class Attractions{
    
    //attributes
    int attraction_id;
    String attrName;
    
    String attrTag;
    String attrDescription;
    String attrCity;
    String attrState;
    String attrZipcode;
    String attrCountry;
    double score;
    
    //constructor
    public Attractions(int a_id, String a_Name,  String a_Tag, String a_desc, String a_City, String a_State, String a_Zipcode, String a_Country, double a_score)
    {
        attraction_id=a_id;
        attrName=a_Name;
        attrTag=a_Tag;
        attrDescription=a_desc;

        attrCity=a_City;
        attrState=a_State;
        attrZipcode=a_Zipcode;
        attrCountry=a_Country;
        score=a_score;
        
    }
    
    public Attractions(int a_id, String a_Name, String a_City, double a_Score)
    {
        attraction_id=a_id;
        attrName=a_Name;
        
        attrCity=a_City;
       
        score=a_Score;
    }
    //Add Attraction
    public void addAttraction()
    {
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        Scanner input=new Scanner(System.in);
        try{
            
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url,"gurralar9537","1640227");
            stat=conn.createStatement();
            
            rs=stat.executeQuery("select * from nextattractionid");
                   int next_att_ID=0;
                   while(rs.next())
                   {
                       next_att_ID=rs.getInt(1);
                       next_att_ID+=1;
                   }
                   
                   int t = stat.executeUpdate("Update nextattractionid set "
                     + "attr_id = '" + next_att_ID + "'");
                   
                   
                   rs=stat.executeQuery("select * from attraction");
                   System.out.println(attrZipcode);
                   
                   int r=stat.executeUpdate("insert into attraction values"
                   +"('"+next_att_ID+"','"+attrName+"','"+attrTag+"',"
                   +"'"+attrDescription+"','"+attrCity+"','"+attrState+"','"+attrZipcode+"'"
                   +",'"+attrCountry+"','"+score+"')");
                   
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
    
    //search Attraction
    public static void searchAttraction(String a_tag, String user_ID)
    {
        
        Scanner input=new Scanner(System.in);
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        
        
        try
        {
            final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            conn=DriverManager.getConnection(db_url, "gurralar9537", "1640227");
            stat=conn.createStatement();
            rs=stat.executeQuery("select * from attraction where tag like '%"+a_tag+"%' or city like '%"+a_tag+"%' or attraction_id like '%"+a_tag+"%' order by score");
            while(rs.next())
            {
//                String all=rs.getString("*");
//                System.out.println(all);
                System.out.println("Attractions list based on the rating: ");
                System.out.println("Attraction ID: "+rs.getString(1)+": Attraction Name: "+rs.getString(2));
                
            }
            System.out.println("Please enter the attraction id to view: ");
            String sel=input.nextLine();
            rs=stat.executeQuery("select * from attraction");
           String a_name="";
            while(rs.next())
            {
                if(sel.equals(rs.getString(1)))
                {
                     a_name=rs.getString(2);
                   System.out.println(rs.getString(1)+", "+rs.getString(2)+", ");
                   System.out.println("Details..");
                   System.out.println("Type: "+rs.getString(3)
                +", Description: "+rs.getString(4));
                   System.out.println(" Location: "+ rs.getString(5)+", "+rs.getString(6)+", "+rs.getString(8)+", "+rs.getString(7));
                   System.out.println("Score : "+rs.getString(9));
                   System.out.println("******");
                 break;
                }
            }
            
            rs=stat.executeQuery("select * from reviews where attraction_name like '"+a_name+"'");
            System.out.println("Reviews: ");
            System.out.println("****************");
            while(rs.next())
            {
            System.out.println(rs.getString(3));
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
    
    
    
    //Using compare to method to get attractions based on the tag and score in descending order

    public int getAttraction_id() {
        return attraction_id;
    }

    public void setAttraction_id(int attraction_id) {
        this.attraction_id = attraction_id;
    }

    public String getAttrTag() {
        return attrTag;
    }

    public void setAttrTag(String attrTag) {
        this.attrTag = attrTag;
    }

    public String getAttrZipcode() {
        return attrZipcode;
    }

    public void setAttrZipcode(String attrZipcode) {
        this.attrZipcode = attrZipcode;
    }

    public String getAttrCountry() {
        return attrCountry;
    }

    public void setAttrCountry(String attrCountry) {
        this.attrCountry = attrCountry;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
   
    
    
    public static void Review()
    {
        
    }
    
    public static void Score()
    {
        
    }
    
    //properties

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrDescription() {
        return attrDescription;
    }

    public void setAttrDescription(String attrDescription) {
        this.attrDescription = attrDescription;
    }

    public String getAttrCity() {
        return attrCity;
    }

    public void setAttrCity(String attrCity) {
        this.attrCity = attrCity;
    }

    public String getAttrState() {
        return attrState;
    }

    public void setAttrState(String attrState) {
        this.attrState = attrState;
    }
    
    
    
}
