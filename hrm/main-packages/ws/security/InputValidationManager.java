package ws.security;

import java.util.Date;

public interface InputValidationManager {
	public boolean usernameValidation(final String username);
	public boolean passwordValidation(final String password);
	public boolean emailValidation(final String email);
	public boolean textValidation(final String text);
	public boolean integerValidation(final int digit);
	public boolean dateValidation(final Date date);
}
