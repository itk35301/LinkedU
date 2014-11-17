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
    
    public universityBean(String uid, String password, String profilePic, String uName, String location, String avgTuition, String highlighted, String about, String vidgalleryid, String photogalleryid){
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
}
