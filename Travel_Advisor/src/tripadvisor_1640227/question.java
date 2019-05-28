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

/**
 *
 * @author reddyjyoshnagurrala
 */
public class question {
    public String question_id;
    public String question;
    public String attraction;
    public String user_ID;
    public question(String question_id, String question, String attraction, String user_ID)
    {
        this.question_id=question_id;
        this.question=question;
        this.attraction=attraction;
        this.user_ID=user_ID;
    }
    public void addQuestion()
    {
        Connection conn=null;
        Statement stat=null;
        ResultSet rs=null;
        try{
             final String db_url="jdbc:mysql://mis-sql.uhcl.edu/gurralar9537";
            //coonection to the database
            conn=DriverManager.getConnection(db_url, "gurralar9537", "1640227");
            //Create a statement
            stat=conn.createStatement();
            
            //generate question id
            rs=stat.executeQuery("select * from next_question_id");
            int q_id=0;
            while(rs.next())
            {
                q_id=rs.getInt(1);
                q_id+=1;
            }
            String qu_id="Q"+q_id;
           
            stat.executeUpdate("Insert into question_answer values('"+qu_id+"','"+user_ID+"','"+attraction+"','"+question+"','"+" "+"','"+" "+"')");
            System.out.println("your question has been posted successfully!");
            System.out.println(" ");
            
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
   
    
   

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAttraction() {
        return attraction;
    }

    public void setAttraction(String attraction) {
        this.attraction = attraction;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }
    
}
