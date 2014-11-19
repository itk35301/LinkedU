/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.universityBean;

/**
 *
 * @author bjcox
 */
public interface applicationDAO {
    
    public int createUniversity(universityBean aUniversity);
    public ArrayList findByUID(String aUID);
    public int updatePassword(universityBean aUniversity);
    public int updateName(universityBean aUniversity);
    public ArrayList findHighlighted();
}
