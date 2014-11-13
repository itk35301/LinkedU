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
    private String id;
    private String location;
    private String avgTuition;
    
    public universityBean(String id, String uName, String location, String avgTuition){
        this.name = uName;
        this.location = location;
        this.avgTuition = avgTuition;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String uName) {
        this.name = uName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
