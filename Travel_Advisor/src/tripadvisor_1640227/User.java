package tripadvisor_1640227;
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package tripadvisor_1640227;
//
///**
// *
// * @author reddyjyoshnagurrala
// */
//public class User {
//    private String user_ID;
//    private String pwd;
//    private String email;
//    private String tag;
//    private String role;
//    public User(String a_User, String a_pwd,String emailID, String a_tag, String a_role)
//    {
//        user_ID=a_User;
//        pwd=a_pwd;
//        email=emailID;
//        tag=a_tag;
//        role=a_role;
//        createUserObject(user_ID, pwd,email,tag, role);
//       
//    }
//   public void createUserObject(String id, String pwd,String email, String tag, String role)
//   {
//       if(role.equals("admin"))
//       {
//           Admin admin=new Admin(id, pwd);
//       }
//       if(role.equals("regular user"))
//       {
//           //System.out.println(role);
//           Regular_User user = new Regular_User(id, pwd,email,tag,role);
//           user.YouMayLike(id,tag);
//           user.user_Menu();
//       }
//   }
//   
//    public String getUser_ID() {
//        return user_ID;
//    } 
//   public void setUser_ID(String user_ID) {
//        this.user_ID = user_ID;
//    }
//
//    public String getPwd() {
//        return pwd;
//    }
//
//    public void setPwd(String pwd) {
//        this.pwd = pwd;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//}
