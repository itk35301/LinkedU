/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.recruiter;

/**
 *
 * @author bjcox
 */
public interface recruiterDAO {
    
    public int createRecruiter(recruiter arecruiter);
    public ArrayList findBySTUID(String aSTUID);
    public int updatePassword(recruiter arecruiter);
    public int updateFName(recruiter arecruiter);
    
}
