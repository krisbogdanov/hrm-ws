/**
 * 
 */
package ws.security.Impl;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import ws.security.InputValidationManager;
import ws.utils.Impl.HRConstants;

/**
 * @author Kristiyan
 *
 */
public class InputValidationManagerImpl implements InputValidationManager {

	/* (non-Javadoc)
	 * @see ws.security.InputValidator#usernameValidation(java.lang.String)
	 */
	@Override
	public boolean usernameValidation(final String username) {
		if(username.equals(HRConstants.EMPTY_STRING)) {
			return false;
		}
		Pattern p = Pattern.compile("^([a-z]*)$");
		Matcher m = p.matcher(username);
		return m.matches();
	}

	/* (non-Javadoc)
	 * @see ws.security.InputValidator#passwordValidation(java.lang.String)
	 */
	@Override
	public boolean passwordValidation(final String password) {
		if(password.equals(HRConstants.EMPTY_STRING)) {
			return false;
		}
		Pattern p = Pattern.compile("^([a-z0-9A-Z%$&_#@]*)$");
		Matcher m = p.matcher(password);
		return m.matches();
	}

	@Override
	public boolean emailValidation(String email) {
		if(email.equals(HRConstants.EMPTY_STRING)) {
			return false;
		}
		Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	    Matcher m = p.matcher(email);
	    return m.matches();
	}

	@Override
	public boolean textValidation(String text) {
		if(text.equals(HRConstants.EMPTY_STRING)) {
			return false;
		}
		Pattern p = Pattern.compile("([\\w\\d\\s!.,$\"%^&()\\-_=\\+#~;*@'])*");
		Matcher m = p.matcher(text);
		return m.matches();
	}

	@Override
	public boolean integerValidation(int digit) {
		// TODO Auto-generated method stub
		//redundant need to remove it
		return true;
	}

	@Override
	public boolean dateValidation(Date date) {
		// TODO Auto-generated method stub
		//redundant need to remove it
		return true;
	}

}
