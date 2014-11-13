package controller;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import model.universityBean;
 

 
@ManagedBean
public class RingView implements Serializable {
    private List<universityBean> universities;
    private universityBean selectedUniversity;
     
    @PostConstruct
    public void init() {
        setUniversities(new ArrayList<universityBean>());
         
        getUniversities().add(new universityBean("1", "Illinois State University", "Normal, IL", "$22,000"));
        getUniversities().add(new universityBean("2", "University of Illinois", "Champaign, IL", "$30,000"));
        getUniversities().add(new universityBean("4", "Illinois Wesleyan", "Bloomington, IL", "$45,000"));
        getUniversities().add(new universityBean("5", "Southern Illinois University", "Carbondale", "$25,000"));
        getUniversities().add(new universityBean("6", "Eastern Illinois University", "Charleston", "$28,000"));
    }

    public List<universityBean> getUniversities() {
        return universities;
    }

    public void setUniversities(List<universityBean> universities) {
        this.universities = universities;
    }

    public universityBean getSelectedUniversity() {
        return selectedUniversity;
    }

    public void setSelectedUniversity(universityBean selectedUniversity) {
        this.selectedUniversity = selectedUniversity;
    }
}

