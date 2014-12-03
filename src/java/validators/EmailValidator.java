/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author bjcox
 * This class validates that the email entered into the email box is at least
 * in the proper format for an email.
 */
@FacesValidator(value="emailValidator")
public class EmailValidator implements Validator{
    
    public boolean isValid(String emailStr) {
		int atPosition = emailStr.indexOf("@");
                int atPosition2 = emailStr.lastIndexOf("@");
                int periodPosition = emailStr.indexOf(".");
                int periodPosition2 = emailStr.lastIndexOf(".");
   
		if (atPosition == -1                                 // must have an @ sign
				|| atPosition == 0                   // no @ at the beginning
				|| atPosition == emailStr.length()-1 // no @ at the end
                                || atPosition != atPosition2         // can't have two @
                                || periodPosition == -1                  // must have a . in the email
				|| periodPosition == 0                   // no . at the beginning
				|| periodPosition2 == emailStr.length()-1 // no . at the end
                                || periodPosition == atPosition+1){      // no . directly after @ 
			return false;
                }
                //check for . after @ sign
                int periodCount = 0;
		for(int i = atPosition; i < emailStr.length(); i++){
			if(emailStr.charAt(i) == '.'){
				periodCount++;
			}
		}
		if(periodCount == 0){
			return false;
		}
                return true;
	}
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value){
        String email = (String) value;
        if(!isValid(email)){
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email is invalid!", "Email is invalid!");
              throw new ValidatorException(message);
        }
    }
}
