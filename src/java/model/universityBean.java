/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import dao.universityDAOImpl;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author itk35301
 */
public class universityBean {
    private String name;
    private String uid;
    private String password;
    private String profilePic;
    private String location;
    private String avgTuition;
    private String highlighted;
    private String about;
    private String photogalleryid;
    private String vidgalleryid;
    private String phone;
    private String email;
    private String city;
    private String state;
    

    public universityBean(){
        
    }
    public universityBean(String uid, String password, String profilePic, String uName, String location, String avgTuition, String highlighted, String about, String vidgalleryid, String photogalleryid, String phone, String email, String city, String state){
        this.name = uName;
        this.location = location;
        this.avgTuition = avgTuition;
        this.uid = uid;
        this.about = about;
        this.profilePic = profilePic;
        this.highlighted = highlighted;
        this.password = password;
        this.photogalleryid = photogalleryid;
        this.vidgalleryid = vidgalleryid;
        this.phone = phone;
        this.email = email;
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String uName) {
        this.name = uName;
    }

    public String getUid() {
        return uid;
    }

    public void setId(String uid) {
        this.uid = uid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvgTuition() {
        return avgTuition;
    }

    public void setAvgTuition(String avgTuition) {
        this.avgTuition = avgTuition;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(String highlighted) {
        this.highlighted = highlighted;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhotogalleryid() {
        return photogalleryid;
    }

    public void setPhotogalleryid(String photogalleryid) {
        this.photogalleryid = photogalleryid;
    }

    public String getVidgalleryid() {
        return vidgalleryid;
    }

    public void setVidgalleryid(String vidgalleryid) {
        this.vidgalleryid = vidgalleryid;
    }
    public static boolean authenticate(String username, String Password) {

        String user = username;
        String pass = Password;
        
        universityDAOImpl login = new universityDAOImpl();
        
      //  ArrayList userAuth = new ArrayList();
        
        ArrayList userAuth = login.findloginUID(user);

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

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }
}
