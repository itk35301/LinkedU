package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author bjcox
 * This class validates that the Password and the Password Confirm fields match.
 * If not it throws and error.
 */
@FacesValidator(value="passwordValidator")
public class PasswordValidator implements Validator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value){
        UIInput passwordField = (UIInput) context.getViewRoot().findComponent("form:password");
        if (passwordField == null)
            throw new IllegalArgumentException(String.format("Unable to find component."));
        String password = (String) passwordField.getValue();
        String confirmPassword = (String) value;
        if (!confirmPassword.equals(password)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match!", "Passwords do not match!");
            throw new ValidatorException(message);
        }
    }
}
