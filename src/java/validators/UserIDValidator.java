package validators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author bjcox
 * This class verifies that the requested userID does not already exist in the
 * database.
 */
@FacesValidator(value="userIDValidator")
public class UserIDValidator implements Validator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value){
        try{
            String userID = (String) value;
            String myDB = "jdbc:derby://gfish.it.ilstu.edu:1527/IT3530101Fall14_LinkedU";
            Connection DBConn = DriverManager.getConnection(myDB, "itkstu", "student");
            Statement stmt = DBConn.createStatement();
            
            ResultSet test = stmt.executeQuery("SELECT * FROM LinkedU.student WHERE stuid ='" + userID + "'");
            if(test.next()){
                System.out.println(test.toString());
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User Name already exists!", "User Name already exists!");
                throw new ValidatorException(message);
            }
            DBConn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
