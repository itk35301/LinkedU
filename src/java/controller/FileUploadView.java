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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
 import dao.studentDAOImpl;
import java.util.ArrayList;
import model.studentBean;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
@ManagedBean
public class FileUploadView {
     
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    
    public void upload(FileUploadEvent event) {
        
          //  FacesMessage message = new FacesMessage("Succesful", event.getFileName() + " is uploaded.");
        //    FacesContext.getCurrentInstance().addMessage(null, message);
             try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    
    
    public void uploadPicture(){
        System.out.println("hello");
    }
     public void copyFile(String fileName, InputStream in) {
           try {
                studentBean stuModel;
               String base;
               String fileType = fileName.substring(fileName.lastIndexOf('.'));
                    studentDAOImpl stulogin = new studentDAOImpl();
        String  stuID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInStu");
        System.out.print("The sessionID passed in searched is " + stuID);
        ArrayList studentCollection = stulogin.findBySTUID(stuID);

        stuModel = (studentBean)studentCollection.get(0);
        stulogin.updateImageID(stuModel);
        String photo =  stuModel.getPhotogalleryid();
         
    String destination= stuModel.getStuID()+photo+fileType;
    FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
    base = context.getRealPath("");

              
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(base+"\\resources\\images\\"+ destination));
             
                int read = 0;
                byte[] bytes = new byte[1024];
             
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
             
                in.close();
                out.flush();
                out.close();
             
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
}
