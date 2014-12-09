/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author itmacuser
 */
import dao.studentDAOImpl;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.studentBean;
 
@ManagedBean
public class ImageView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<>();
        studentBean stuModel;
          studentDAOImpl stulogin = new studentDAOImpl();
         String  stuID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInStu");
                 ArrayList studentCollection = stulogin.findBySTUID(stuID);

        stuModel = (studentBean)studentCollection.get(0);
        String photo =  stuModel.getPhotogalleryid();
        int numPics = Integer.parseInt(photo);
        for (int i = 1; i <= numPics; i++) {
            images.add(stuID + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        init();
        return images;
    }
}
