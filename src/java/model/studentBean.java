/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author itk35301
 */
import dao.studentDAOImpl;
import java.util.HashMap;
import java.util.ArrayList;

public class studentBean {
    private String stuID;
    private String password;
    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String about;
    private String highschool;
    private String interests;
    private String major;
    private String experience;
    private String gpa;
    private String profilePic;
    private String photogalleryid;
    private String videogalleryid;

    public studentBean(){
        
    }
    
    public studentBean(String stuID, String password, String fname,String lname, String phone, String email,String about,String highschool, String interests,String major,String experience,String gpa,String profilePic,String photogalleryid,String videogalleryid){
        this.stuID = stuID;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.about = about;
        this.highschool = highschool;
        this.interests = interests;
        this.major = major;
        this.experience = experience;
        this.gpa = gpa;
        this.profilePic = profilePic;
        this.photogalleryid = photogalleryid;
        this.videogalleryid = videogalleryid;
     
        
        
    }
    
    
    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getHighschool() {
        return highschool;
    }

    public void setHighschool(String highschool) {
        this.highschool = highschool;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPhotogalleryid() {
        if(photogalleryid.equals("null"))
            return "0";
        return photogalleryid;
    }

    public void setPhotogalleryid(String photogalleryid) {
        this.photogalleryid = photogalleryid;
    }

    public String getVideogalleryid() {
        return videogalleryid;
    }

    public void setVideogalleryid(String videogaleryid) {
        this.videogalleryid = videogaleryid;
    }
    public static boolean authenticate(String username, String Password) {

        String user = username;
        String pass = Password;
        
        studentDAOImpl login = new studentDAOImpl();
        
      //  ArrayList userAuth = new ArrayList();
        
        ArrayList userAuth = login.findloginSTUID(user);

        HashMap<String, String> map = new HashMap<>();
        map.put(userAuth.get(0).toString(), userAuth.get(1).toString());
       
        
        if(map.containsKey(user)){
            if (map.get(user).equals(pass)) {
               return true;
             } else {
                return false;
            }
        }
        else
            return false;
    }
    
}
