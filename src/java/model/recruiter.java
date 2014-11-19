/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.recruiterDAOImpl;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author itk35301
 */
public class recruiter {
    
    private String recid;
    private String uid;
    private String vidgalleryid;
    private String photogalleryid;  
    private String password;
    private String fname;
    private String lname;
    private String address;
    private String phone;
    private String email;
    private String university;
    private String profilePic;

    public recruiter(){
        
    }
    
    
    public recruiter(String recid, String uid, String vidgalleryid, String photogalleryid, String password, String fname, String lname,  String address, String phone, String email, String university, String profilePic ){
       this.recid = recid; 
       this.uid = uid; 
       this.vidgalleryid = vidgalleryid; 
       this.photogalleryid = photogalleryid; 
       this.password = password; 
       this.fname = fname; 
       this.lname = lname; 
       this.address = address;  
       this.phone = phone; 
       this.email = email; 
       this.university = university;  
       this.profilePic = profilePic;           
             
    }
    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getVidgalleryid() {
        return vidgalleryid;
    }

    public void setVidgalleryid(String vidgalleryid) {
        this.vidgalleryid = vidgalleryid;
    }

    public String getPhotogalleryid() {
        return photogalleryid;
    }

    public void setPhotogalleryid(String photogalleryid) {
        this.photogalleryid = photogalleryid;
    }
    public static boolean authenticate(String username, String Password) {

        String user = username;
        String pass = Password;
        
        recruiterDAOImpl login = new recruiterDAOImpl();
        
      //  ArrayList userAuth = new ArrayList();
        
        ArrayList userAuth = login.findloginRECID(user);

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
