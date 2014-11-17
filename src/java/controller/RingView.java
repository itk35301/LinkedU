package controller;
 
import dao.universityDAO;
import dao.universityDAOImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
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
        universityDAO aUDAO = new universityDAOImpl();
        ArrayList<universityBean> highUni = aUDAO.findHighlighted();
        Iterator<universityBean> iterator = highUni.iterator();
        while(iterator.hasNext()){
            getUniversities().add(iterator.next());
        }
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

