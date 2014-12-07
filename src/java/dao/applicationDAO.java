/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.application;

/**
 *
 * @author bjcox
 */
public interface applicationDAO {
    
    public int createApplication(application application);
    public ArrayList findByUID(String aUID);
}
